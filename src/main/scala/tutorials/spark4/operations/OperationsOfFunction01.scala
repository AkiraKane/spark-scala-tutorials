package tutorials.spark4.operations

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.log4j.Logger
import org.apache.log4j.Level

/**
  * Created by leeivan on 4/7/16.
  */
object OperationsOfFunction01 {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {
    val appName = "Test Spark basic functions of transformations and actions"
    val conf = new SparkConf().setMaster("local").setAppName(appName);
    val sc = new SparkContext(conf)
    //    LogManager.getRootLogger.setLevel(Level.ALL)
    val lines = sc.parallelize(List(1, 2, 3, 3))
    lines.cache()
    val result01 = lines.map(line => line + 1)
    val result02 = lines.flatMap(line => line.to(3))
    val result03 = lines.filter(x => x != 1)
    val result04 = lines.distinct()
    val result05 = lines.sample(false, 0.5)

//    println("-----------------------------")
//    result01.collect()
//    println("-----------------------------")

    result01.foreach(println)
    println("-----------------------------")
    result02.foreach(println)
    println("-----------------------------")
    result03.foreach(println)
    println("-----------------------------")
    result04.foreach(println)
    println("-----------------------------")
    result05.foreach(println)
    println("-----------------------------")
    val lines01 = sc.parallelize(List(1, 2, 3))
    val lines02 = sc.parallelize(List(3, 4, 5))
    lines01.cache()
    lines02.cache()
    val result06 = lines01.union(lines02)
    val result07 = lines01.intersection(lines02)
    val result08 = lines01.subtract(lines02)
    val result09 = lines01.cartesian(lines02)
    println("-----------------------------")
    result06.foreach(println)
    println("-----------------------------")
    result07.foreach(println)
    println("-----------------------------")
    result08.foreach(println)
    println("-----------------------------")
    result09.foreach(println)
    println("-----------------------------")
  }
}
