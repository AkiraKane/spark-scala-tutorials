package com.pinecone.tutorials.spark4basic.fileFormat.json

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types.{LongType, StringType, StructType, TimestampType}
import org.apache.spark.{SparkConf, SparkContext}
import play.api.libs.json._

/**
  * Created by ivan on 2016/6/8.
  */
object JsonParse {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  implicit val eventReads = Json.format[Event]

  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("BasicParseJson")
    val sc = new SparkContext(conf)
    println("Processing JSON data with Spark SQL")
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    println("df01:")
    val events01 = sc.parallelize(
      """{"action":"create","timestamp":"2016-01-07T00:01:17Z"}""" :: Nil)
    val df01 = sqlContext.read.json(events01)
    df01.show()
    println("df02:")
    val schema01 = (new StructType).add("action", StringType).add("timestamp", TimestampType)
    val df02 = sqlContext.read.schema(schema01).json(events01)
    df02.show
    println("df03:")
    val events02 = sc.parallelize(
      """{"action":"create","timestamp":1452121277}""" ::
        """{"action":"create","timestamp":"1452121277"}""" ::
        """{"action":"create","timestamp":""}""" ::
        """{"action":"create","timestamp":null}""" ::
        """{"action":"create","timestamp":"null"}""" ::
        Nil
    )
    val schema02 = (new StructType).add("action", StringType).add("timestamp", LongType)
    val df03 = sqlContext.read.schema(schema02).json(events02)
    df03.show
    println("df04:")
    val schema03 = (new StructType).add("action", StringType).add("timestamp", StringType)
    val df04 = sqlContext.read.schema(schema03).json(events02)
    df04.select(df04("action"), df04("timestamp").cast(LongType)).show()
    //    val df05 = df04.withColumn("action",df04("timestamp").cast(LongType))
    println("df05:")
    val vals = sc.parallelize(
      """{"payload":{"event":{"action":"create","timestamp":1452121277}}}""" ::
        Nil
    )
    val schema = (new StructType)
      .add("payload", (new StructType)
        .add("event", (new StructType)
          .add("action", StringType)
          .add("timestamp", LongType)
        )
      )

    val df05 = sqlContext.read.schema(schema).json(vals)
    df05.select(df05("payload.event")).show()

    println("result:")
    val parsed = events02.map(Json.parse(_))
    val result = parsed.flatMap(record => eventReads.reads(record).asOpt)
    println(result.collect().mkString(" "))
  }

  case class Event(action: String, timestamp: String)
}
