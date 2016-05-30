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
    val result10 = lines.flatMap(line => line.until(3))
    val result03 = lines.filter(x => x < 2)
    val result04 = lines.distinct()
    val result05 = lines.sample(false, 0.5)

    println("result01: " + result01.collect().mkString(" "))
    println("result02: " + result02.collect().mkString(" "))
    println("result03: " + result03.collect().mkString(" "))
    println("result04: " + result04.collect().mkString(" "))
    println("result05: " + result05.collect().mkString(" "))
    println("result10: " + result10.collect().mkString(" "))
    val lines01 = sc.parallelize(List(1, 2, 3))
    val lines02 = sc.parallelize(List(3, 4, 5))
    lines01.cache()
    lines02.cache()
    val result06 = lines01.union(lines02)
    val result07 = lines01.intersection(lines02)
    val result08 = lines01.subtract(lines02)
    val result09 = lines01.cartesian(lines02)
    println("result06: " + result06.collect().mkString(" "))
    println("result07: " + result07.collect().mkString(" "))
    println("result08: " + result08.collect().mkString(" "))
    println("result09: " + result09.collect().mkString(" "))
  }
}
