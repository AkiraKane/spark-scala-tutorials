package tutorials.spark4.operations

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

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
    val rdd = sc.parallelize(List(1, 2, 3, 3), 2)
    println("-----------------------------")
    val result01 = rdd.collect()
    val result02 = rdd.count()
    val result03 = rdd.countByValue()
    val result04 = rdd.take(2)
    val result05 = rdd.top(2)
    val result06 = rdd.takeOrdered(2)(Ordering[Int].reverse)
    val result07 = rdd.takeSample(false, 1)
    println("result01: " + result01.mkString(" "))
    println("result02: " + result02.toString)
    println("result03: " + result03.mkString(" "))
    println("result04: " + result04.mkString(" "))
    println("result05: " + result05.mkString(" "))
    println("result06: " + result06.mkString(" "))
    println("result07: " + result07.mkString(" "))
    //set operations
    val result08 = rdd.reduce((x, y) => x + y)
    val result11 = rdd.partitions.length
    val result09 = rdd.fold(5)((x, y) => x + y)
    val result10 = rdd.aggregate((1, 1))((x, y) => (x._1 + y, x._2 + 1), (x, y) => (x._1 + y._1, x._2 + y._2))
    val result12 = result10._1 / result10._2.toDouble
    println("result08: " + result08.toString)
    println("result09: " + result09.toString)
    println("result10: " + result10.toString)
    println("result11: " + result11.toString)
    println("result12: " + result12.toString)
    val employeeRDD = sc.makeRDD((List(("Jack", 1000.0), ("Bob", 2000.0), ("Carl", 7000.0))))
    val dummyEmployee = ("dummy", 0.0);
    val maxSalaryEmployee = employeeRDD.fold(dummyEmployee)((acc, employee) => {
      if (acc._2 < employee._2) employee else acc
    })
    println("employee with maximum salary is" + maxSalaryEmployee)
  }
}
