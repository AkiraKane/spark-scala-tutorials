package tutorials.spark4.operations

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

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
    val rdd = sc.parallelize(List(('B', 1), ('B', 2), ('A', 3), ('A', 4), ('A', 5), ('B', 1)))
    val other = sc.parallelize(('B', 9) ::('A', 10) ::('D', 3) :: Nil)
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
    val result12_1 = rdd.groupWith(other)
    val result13 = rdd.countByKey()
    val result14 = rdd.countByValue()
    val result15 = rdd.filter { case (key, value) => value < 4 }
    val result15_1 = rdd.filter { case (key, value) => key == 'B' }.distinct()
    val result15_2 = rdd.collectAsMap()
    val result15_3 = rdd.lookup('A')

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
    println("result12_1: " + result12_1.collect.mkString(" "))
    println("result13: " + result13.mkString("   "))
    println("result14: " + result14.mkString("   "))
    println("result15: " + result15.collect.mkString(" "))
    println("result15_1: " + result15_1.collect.mkString(" "))
    println("result15_2: " + result15_2.mkString(" "))
    println("result15_3: " + result15_3.mkString(" "))


    val deptEmployees = List(
      ("cs", ("jack", 1000.0)),
      ("cs", ("bron", 1200.0)),
      ("phy", ("sam", 2200.0)),
      ("phy", ("ronaldo", 500.0))
    )
    val employeeRDD = sc.makeRDD(deptEmployees)

    val maxByDept = employeeRDD.foldByKey(("dummy", 0.0))((acc, element) => if (acc._2 > element._2) acc else element)
    println("maximum salaries in each dept" + maxByDept.collect().toList)

    val pairs = sc.makeRDD(List(("holden", "likes coffee"), ("panda", "likes long strings and coffee")))
    val result16 = pairs.filter { case (key, value) => value.length < 20 }
    println("result16: " + result16.collect.mkString(" "))

    val rdd01 = sc.parallelize(List(("panda", 0), ("pink", 3), ("pirate", 3), ("panda", 1), ("pink", 4)))
    val result17 = rdd01.mapValues(x => (x, 1)).reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
    println("result17: " + result17.collect().mkString(" "))

    val result18 = rdd01.map(x => (x._1, (x._2, 1)))
    val result18_1 = rdd01.map { case (k, v) => (k, (v, 1)) }
    val result19 = result18.foldByKey((0, 0))((x, y) => (x._1 + y._1, x._2 + y._2))
    println("result18: " + result18.collect.mkString(" "))
    println("result18_1: " + result18_1.collect().mkString(" "))
    println("result19: " + result19.collect.mkString(" "))
    val result20 = rdd01.combineByKey(
      (v) => (v, 1),
      (acc: (Int, Int), v) => (acc._1 + v, acc._2 + 1),
      (acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2))
    println("result20: " + result20.collect.mkString(" "))
    val result21 = result20.map { case (key, value) => (key, value._1 / value._2.toFloat) }
    println("result21: " + result21.collect.mkString(" "))

    val pairs01 = sc.parallelize(List((1, 1), (2, 2), (3, 3)))
    val result22 = pairs01.partitioner.getOrElse(0)
    println("result22: " + result22)
    val pairs02 = pairs.partitionBy(new HashPartitioner(100))
    val result23 = pairs02.partitioner.get
    println("result23: " + result23)
  }
}
