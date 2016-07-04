package com.pinecone.tutorials.spark4advance.accumulate

import java.io.StringReader

import au.com.bytecode.opencsv.CSVReader
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by ivan on 2016/6/29.
  */
object LoadCsvWithCountersExample {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  System.setProperty("hadoop.home.dir", "C:\\winutils")

  def main(args: Array[String]) {
    val inputFile = "files/example_partailly_invalid.csv"
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("Load CSV With Counters Example"))
    val invalidLineCounter = sc.accumulator(0)
    val invalidNumericLineCounter = sc.accumulator(0)
    val inFile = sc.textFile(inputFile)
    val splitLines = inFile.flatMap(line => {
      try {
        val reader = new CSVReader(new StringReader(line))
        Some(reader.readNext())
      } catch {
        case _ => {
          invalidLineCounter += 1
          None
        }
      }
    }
    )
    val numericData = splitLines.flatMap(line => {
      try {
        Some(line.map(_.toDouble))
      } catch {
        case _ => {
          invalidNumericLineCounter += 1
          None
        }
      }
    }
    )
    println(numericData.collect().mkString(" "))
//    val summedData = numericData.map(row => row)
//    println(summedData.collect().mkString(","))
//    println("Errors: " + invalidLineCounter + "," + invalidNumericLineCounter)
//    println(summedData.stats())
  }
}
