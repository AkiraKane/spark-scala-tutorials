package com.pinecone.tutorials.spark4basic

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by leeivan on 7/4/16.
  */
object BasicAvgMapPartitions {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  System.setProperty("hadoop.home.dir", "C:\\winutils")

  case class AvgCount(var total: Int = 0, var num: Int = 0) {
    def merge(other: AvgCount): AvgCount = {
      total += other.total
      num += other.num
      this
    }

    def merge(input: Iterator[Int]): AvgCount = {
      input.foreach { elem =>
        total += elem
        num += 1
      }
      this
    }

    def avg(): Float = {
      total / num.toFloat;
    }
  }

  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("BasicAvgMapPartitions"))
    val input = sc.parallelize(List(1, 2, 3, 4))
    val result = input.mapPartitions(partition =>
      // Here we only want to return a single element for each partition, but mapPartitions requires that we wrap our return in an Iterator
      Iterator(AvgCount(0, 0).merge(partition)))
      .reduce((x, y) => x.merge(y))
    println("result: " + result.total + " and " + result.num)
    println("average: " + result.total.toFloat / result.num.toFloat)
  }
}
