package com.pinecone.tutorials.spark4basic.fileFormat

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext

/**
  * Created by ivan on 2016/6/28.
  */
object LoadHive {
  def main(args: Array[String]) {
    if (args.length < 2) {
      println("Usage: [sparkmaster] [tablename]")
      exit(1)
    }
    val master = args(0)
    val tableName = args(1)
    val sc = new SparkContext( new SparkConf().setMaster("local").setAppName("LoadHive"))
    val hiveCtx = new HiveContext(sc)
    val input = hiveCtx.sql("FROM src SELECT key, value")
    val data = input.map(_.getInt(0))
    println(data.collect().toList)
  }
}
