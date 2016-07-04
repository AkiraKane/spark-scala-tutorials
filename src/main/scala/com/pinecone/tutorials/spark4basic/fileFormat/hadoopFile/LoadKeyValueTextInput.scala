package com.pinecone.tutorials.spark4basic.fileFormat.hadoopFile

import org.apache.hadoop.io.Text
import org.apache.hadoop.mapred.KeyValueTextInputFormat
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ivan on 2016/6/27.
  */
object LoadKeyValueTextInput {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  System.setProperty("hadoop.home.dir", "C:\\winutils")

  def main(args: Array[String]) {
    val inputFile = "files/favourite_animals.csv"
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("LoadKeyValueTextInput"))
    val result01 = sc.hadoopFile[Text, Text, KeyValueTextInputFormat](inputFile).map {
      case (x, y) => (x.toString, y.toString)
    }
    println("result01: "+ result01.collect().mkString(" "))
  }
}
