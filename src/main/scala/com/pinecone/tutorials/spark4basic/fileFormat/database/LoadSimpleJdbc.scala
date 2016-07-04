package com.pinecone.tutorials.spark4basic.fileFormat.database

import java.sql.{DriverManager, ResultSet}

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ivan on 2016/6/28.
  */
object LoadSimpleJdbc {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("LoadSimpleJdbc"))
    val data = new JdbcRDD(sc,
      createConnection, "SELECT * FROM panda WHERE ? <= id AND ID <= ?",
      lowerBound = 1, upperBound = 3, numPartitions = 2, mapRow = extractValues)
    println(data.collect().toList)
  }

  def createConnection() = {
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    DriverManager.getConnection("jdbc:mysql://localhost/test?user=holden");
  }

  def extractValues(r: ResultSet) = {
    (r.getInt(1), r.getString(2))
  }
}
