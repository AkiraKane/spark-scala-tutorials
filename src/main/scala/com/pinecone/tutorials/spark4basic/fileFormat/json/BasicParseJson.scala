package com.pinecone.tutorials.spark4basic.fileFormat.json

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import play.api.libs.json._


/**
  * Created by ivan on 2016/6/22.
  */
case class Person(name: String, lovesPandas: Boolean)

// Note: must be a top level class
object BasicParseJson {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  System.setProperty("hadoop.home.dir", "C:\\winutils")

  def main(args: Array[String]) {
    val inputFile = "files/pandainfo.json"
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("BasicParseJsonWithJackson"))
    val input = sc.textFile(inputFile)

    // Parse it into a specific case class. We use mapPartitions beacuse:
    // (a) ObjectMapper is not serializable so we either create a singleton object encapsulating ObjectMapper
    //     on the driver and have to send data back to the driver to go through the singleton object.
    //     Alternatively we can let each node create its own ObjectMapper but that's expensive in a map
    // (b) To solve for creating an ObjectMapper on each node without being too expensive we create one per
    //     partition with mapPartitions. Solves serialization and object creation performance hit.
    val rddParseJsonWithJackson = input.mapPartitions(records => {
      // mapper object created on each executor node
      val mapper = new ObjectMapper with ScalaObjectMapper
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      mapper.registerModule(DefaultScalaModule)
      // We use flatMap to handle errors
      // by returning an empty list (None) if we encounter an issue and a
      // list with one element if everything is ok (Some(_)).
      records.flatMap(record => {
        try {
          Some(mapper.readValue(record, classOf[Person]))
        } catch {
          case e: Exception => None
        }
      })
    }, true)
    val result01 = rddParseJsonWithJackson.filter(_.lovesPandas).mapPartitions(records => {
      val mapper = new ObjectMapper with ScalaObjectMapper
      mapper.registerModule(DefaultScalaModule)
      records.map(mapper.writeValueAsString(_))
    })
    println("result01" + result01.collect().mkString(" "))
    implicit val personReads = Json.format[Person]
    val rddParseJson = input.map(Json.parse(_))
    val result02 = rddParseJson.flatMap(record => personReads.reads(record).asOpt)
    result02.filter(_.lovesPandas).map(Json.toJson(_))
    println("result02" + result02.collect().mkString(" "))

  }
}
