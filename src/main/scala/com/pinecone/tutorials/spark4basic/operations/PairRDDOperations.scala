package com.pinecone.tutorials.spark4basic.operations

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by leeivan on 4/10/16.
  */
object PairRDDOperations {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {
    val appName = "Test Spark basic functions of transformations and actions"
    val conf = new SparkConf().setMaster("local").setAppName(appName)
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(('B', 1), ('B', 2), ('A', 3), ('A', 4), ('A', 5), ('D', 8)))
    val other = sc.parallelize(('B', 9) ::('A', 10) ::('A', 3) :: Nil)
    val result01 = rdd.reduceByKey((x, y) => x + y)
    val result02 = rdd.groupByKey()
    val result03 = rdd.mapValues(x => x + 1)
    val result04 = rdd.flatMapValues(x => (x to 5))
    val result05 = rdd.keys
    val result06 = rdd.values
    val result07 = rdd.sortByKey()
    val result08 = rdd.subtractByKey(other)
    val result09 = rdd.join(other)
    val result10 = rdd.rightOuterJoin(other)
    val result11 = rdd.leftOuterJoin(other)
    val result12 = rdd.cogroup(other)
    val result13 = rdd.countByKey()
    val result14 = rdd.countByValue()
    val result15 = rdd.filter { case (key, value) => value < 4 }

    println("result01: " + result01.collect.mkString(" "))
    println("result02: " + result02.collect.mkString(" "))
    println("result03: " + result03.collect.mkString(" "))
    println("result04: " + result04.collect.mkString(" "))
    println("result05: " + result05.collect.mkString(" "))
    println("result06: " + result06.collect.mkString(" "))
    println("result07: " + result07.collect.mkString(" "))
    println("result08: " + result08.collect.mkString(" "))
    println("result09: " + result09.collect.mkString(" "))
    println("result10: " + result10.collect.mkString(" "))
    println("result11: " + result11.collect.mkString(" "))
    println("result12: " + result12.collect.mkString(" "))
    println("result13: " + result13.mkString("   "))
    println("result14: " + result14.mkString("   "))
    println("result15: " + result15.collect.mkString(" "))

    val deptEmployees = List(
      ("cs", ("jack", 1000.0)),
      ("cs", ("bron", 1200.0)),
      ("phy", ("sam", 2200.0)),
      ("phy", ("ronaldo", 500.0))
    )
    val employeeRDD = sc.makeRDD(deptEmployees)

    val maxByDept = employeeRDD.foldByKey(("dummy", 0.0))((acc, element) => if (acc._2 > element._2) acc else element)
    println("maximum salaries in each dept" + maxByDept.collect().toList)
  }
}
