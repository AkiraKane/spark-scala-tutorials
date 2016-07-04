package com.pinecone.tutorials.spark4basic

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by leeivan on 7/4/16.
  */
object BroadcastTest {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  System.setProperty("hadoop.home.dir", "C:\\winutils")
  def main(args: Array[String]) {
    val bcName = if (args.length > 2) args(2) else "Http"
    val blockSize = if (args.length > 3) args(3) else "4096"

    val sparkConf = new SparkConf().setMaster("local").setAppName("Broadcast Test")
      .set("spark.broadcast.factory", s"org.apache.spark.broadcast.${bcName}BroadcastFactory")
      .set("spark.broadcast.blockSize", blockSize)
    val sc = new SparkContext(sparkConf)

    val slices = if (args.length > 0) args(0).toInt else 2
    val num = if (args.length > 1) args(1).toInt else 1000000

    val arr1 = (0 until num).toArray

    val barr1 = sc.broadcast(arr1)
    for (i <- 0 until 3) {
      println("Iteration " + i)
      println("===========")
      val startTime = System.nanoTime
      val observedSizes = sc.parallelize(1 to 10, slices).map(_ => barr1.value.size)
      // Collect the small RDD so we can print the observed sizes locally.
      observedSizes.collect().foreach(i => println(i))
      println("Iteration %d took %.0f milliseconds".format(i, (System.nanoTime - startTime) / 1E6))
    }

    sc.stop()
  }

}
