package tutorials.spark4.operations

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkContext, SparkConf}

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
    val rdd = sc.parallelize(List((1, 2), (3, 4), (3, 6)))
    val other = sc.parallelize(List((3, 9)))
    rdd.persist()
    other.persist()
    println("----------------------------------")
    rdd.reduceByKey((x, y) => x + y).foreach(println)
    println("----------------------------------")
    rdd.groupByKey().foreach(println)
    println("----------------------------------")
    rdd.mapValues(x => x+1).foreach(println)
    println("----------------------------------")
    rdd.flatMapValues(x => (x to 5)).foreach(println)
    println("----------------------------------")
    rdd.keys.foreach(println)
    println("----------------------------------")
    rdd.values.foreach(println)
    println("----------------------------------")
    rdd.sortByKey().foreach(println)
    println("----------------------------------")
    rdd.subtractByKey(other).foreach(println)
    println("----------------------------------")
    rdd.join(other).foreach(println)
    println("----------------------------------")
    rdd.rightOuterJoin(other).foreach(println)
    println("----------------------------------")
    rdd.leftOuterJoin(other).foreach(println)
    println("----------------------------------")
    rdd.cogroup(other).(println)
    println("----------------------------------")
    println("----------------------------------")
  }
}
