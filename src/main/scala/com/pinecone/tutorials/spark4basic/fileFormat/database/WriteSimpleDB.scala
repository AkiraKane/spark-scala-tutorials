package com.pinecone.tutorials.spark4basic.fileFormat.database

import java.sql.{PreparedStatement, ResultSet}

import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.mapred.lib.db.{DBConfiguration, DBOutputFormat, DBWritable}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ivan on 2016/6/28.
  */
object WriteSimpleDB {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("WriteSimpleJdbc"))
    val data = sc.parallelize(List(("cat1", 1)))
    // foreach partition method
    data.foreachPartition { records =>
      records.foreach(record => println("fake db write"))
    }
    // DBOutputFormat approach
    val records = data.map(e => (catRecord(e._1, e._2), null))
    val tableName = "table"
    val fields = Array("name", "age")
    val jobConf = new JobConf()
    DBConfiguration.configureDB(jobConf, "com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test?user=holden")
    DBOutputFormat.setOutput(jobConf, tableName, fields: _*)
    records.saveAsHadoopDataset(jobConf)
  }

  case class catRecord(name: String, age: Int) extends DBWritable {
    override def write(s: PreparedStatement) {
      s.setString(1, name)
      s.setInt(2, age)
    }

    override def readFields(r: ResultSet) = {
      // blank since only used for writing
    }
  }

}
