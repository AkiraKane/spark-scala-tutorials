package com.pinecone.tutorials.spark4basic.fileFormat

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by leeivan on 4/13/16.
  */
object Test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf
    conf.setMaster("local").setAppName("BasicTextFromFTP")
    val sc = new SparkContext(conf)
    val file = sc.textFile("ftp://ftp.funet.fi/pub/standards/RFC/rfc959.txt")
    println(file.collect().mkString("\n"))
  }
}
