/**
  * Illustrates a simple map partition to parse CSV data in Scala
  */
package com.pinecone.tutorials.spark4basic.fileFormat.cvs

import java.io.StringReader

import au.com.bytecode.opencsv.CSVReader
import org.apache.spark._

import scala.collection.JavaConversions._

object BasicParseWholeFileCsv {
  def main(args: Array[String]) {
    val inputFile = args(1)
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("BasicParseWholeFileCsv"))
    val input = sc.wholeTextFiles(inputFile)
    val result = input.flatMap { case (_, txt) =>
      val reader = new CSVReader(new StringReader(txt));
      reader.readAll()
    }
    println(result.collect().map(_.toList).mkString(","))
  }
}
