package tutorials.spark4.operations

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by leeivan on 4/7/16.
  */
object OperationsOfFunction02 {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {
    val appName = "Test Spark basic functions of transformations and actions"
    val conf = new SparkConf().setMaster("local").setAppName(appName);
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(1, 2, 3, 3))
    rdd.cache()
    println("-----------------------------")
    rdd.collect().foreach(println)
    println("-----------------------------")
    println(rdd.count())
    println("-----------------------------")
    rdd.countByValue().foreach(println)
    println("-----------------------------")
    rdd.take(2).foreach(println)
    println("-----------------------------")
    rdd.top(2).foreach(println)
    println("-----------------------------")
    rdd.takeOrdered(2)(Ordering[Int].reverse).foreach(println)
    println("-----------------------------")
    rdd.takeSample(false, 1).foreach(println)
    println("-----------------------------")
    println(rdd.reduce((x, y) => x + y))
    println("-----------------------------")
    println(rdd.fold(1)((x, y) => x + y))
    println("-----------------------------")
    println(rdd.aggregate((1, 1))((x, y) =>
      (x._1 + y, x._2 + 1), (x, y) =>
      (x._1 + y._1, x._2 + y._2)))
    println("-----------------------------")
  }
}
