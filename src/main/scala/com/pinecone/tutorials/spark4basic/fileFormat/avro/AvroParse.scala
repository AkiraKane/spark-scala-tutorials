package com.pinecone.tutorials.spark4basic.fileFormat.avro

import org.apache.avro.generic.GenericRecord
import org.apache.avro.mapred.{AvroInputFormat, AvroWrapper}
import org.apache.hadoop.io.NullWritable
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ivan on 2016/6/21.
  */
object AvroParse {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  System.setProperty("hadoop.home.dir", "C:\\winutils")

  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("AvroExample")
    val sc = new SparkContext(conf)
    val path = "files/avro/twitter.avro"
    val avroRDD = sc.hadoopFile[AvroWrapper[GenericRecord], NullWritable, AvroInputFormat[GenericRecord]](path)
//    println("avroRDD: " + avroRDD.collect().mkString(" "))
    println("avroRDD: " + avroRDD.map(record => new String(record._1.datum.get("username").toString())).first.mkString(" "))
  }
}

