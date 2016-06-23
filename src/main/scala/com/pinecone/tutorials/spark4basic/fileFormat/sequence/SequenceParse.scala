package com.pinecone.tutorials.spark4basic.fileFormat.sequence

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ivan on 2016/6/12.
  */
object SequenceParse {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  System.setProperty("hadoop.home.dir", "C:\\winutils")
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[3]").setAppName("SequenceParse")
    val sc = new SparkContext(conf)
    val data = List(("ABC", 1), ("BCD", 2), ("CDE", 3), ("DEF", 4), ("FGH", 5))
    val rdd01 = sc.parallelize(data, 1)
    val dir = "file:///D:/sequenceFile-" + System.currentTimeMillis()
    rdd01.saveAsSequenceFile(dir)
    val rdd02 = sc.sequenceFile[String, Int](dir + "/part-00000")
    println(rdd02.collect().map(elem => (elem._1 + ", " + elem._2)).toList)
    val rdd03 = sc.sequenceFile(dir + "/part-00000", classOf[Text], classOf[IntWritable]).map { case (x, y) => (x.toString, y.get()) }
    println("rdd3: " + rdd03.collect().mkString(" "))
    val rdd04 = sc.sequenceFile(dir + "/part-00000", classOf[Text], classOf[IntWritable])
    println("rdd4: " + rdd04.collect().mkString(" "))
  }

}
