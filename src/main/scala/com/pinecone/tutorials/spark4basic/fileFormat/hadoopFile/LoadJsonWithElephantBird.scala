package com.pinecone.tutorials.spark4basic.fileFormat.hadoopFile

import com.twitter.elephantbird.mapreduce.input.LzoJsonInputFormat
import org.apache.hadoop.io.{BooleanWritable, LongWritable, MapWritable, Text}
import org.apache.hadoop.mapreduce.{InputFormat => NewInputFormat, Job => NewHadoopJob}
import org.apache.log4j.{Level, Logger}
import org.apache.spark._

import scala.collection.JavaConversions._

/**
  * Created by ivan on 2016/6/24.
  */
object LoadJsonWithElephantBird {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  System.setProperty("hadoop.home.dir", "C:\\winutils")

  def main(args: Array[String]) {
    val inputFile = "files/100.txt.lzo"
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("LoadJsonWithElephantBird"))
    val conf = sc.hadoopConfiguration
    //    val conf = new NewHadoopJob().getConfiguration
    conf.set("io.compression.codecs", "com.hadoop.compression.lzo.LzopCodec")
    conf.set("io.compression.codec.lzo.class", "com.hadoop.compression.lzo.LzoCodec")
    val input01 = sc.newAPIHadoopFile(inputFile, classOf[LzoJsonInputFormat], classOf[LongWritable], classOf[MapWritable], conf)
    val result01 = input01.map { case (x, y) =>
      (x.get, y.entrySet().map { entry =>
        (entry.getKey().asInstanceOf[Text].toString(),
          entry.getValue() match {
            case t: Text => t.toString()
            case b: BooleanWritable => b.get()
            case _ => throw new Exception("unexpected input")
          }
          )
      })
    }
    println("result01: " + result01.collect().mkString(" "))
    //    val input = sc.hadoopFile[Text, Text, KeyValueTextInputFormat](inputFile).map {
    //      case (x, y) => (x.toString, y.toString)
    //    }
    //    println("input: "+input.collect().toList)
  }
}
