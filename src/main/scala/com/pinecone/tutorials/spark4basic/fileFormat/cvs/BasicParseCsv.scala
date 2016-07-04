/**
  * Illustrates a simple map partition to parse CSV data in Scala
  */
package com.pinecone.tutorials.spark4basic.fileFormat.cvs

import java.io.{StringReader, StringWriter}

import au.com.bytecode.opencsv.{CSVReader, CSVWriter}
import org.apache.log4j.{Level, Logger}
import org.apache.spark._

import scala.collection.JavaConversions._

object BasicParseCsv {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  System.setProperty("hadoop.home.dir", "C:\\winutils")

  def main(args: Array[String]) {
    val inputFile = "files/favourite_animals.csv"
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("BasicParseCsv"))
    val input = sc.textFile(inputFile)
    val result = input.map { line =>
      val reader = new CSVReader(new StringReader(line))
      reader.readNext()
    }
    val people = result.map(x => Person(x(0), x(1)))
    val pandaLovers = people.filter(person => person.favouriteAnimal == "panda")
    val result01 = pandaLovers.map(person => (person.name, person.favouriteAnimal))
    println("result01: " + result01.collect().mkString(" "))
    println("pandaLovers: " + pandaLovers.collect().mkString(" "))
    println("input: " + input.collect().mkString(" "))
    val outputPath = "file:///D:/cvsFile-" + System.currentTimeMillis()
    val result02 = result01.map(person => List(person._1, person._2).toArray)
    println("result02: " + result02.collect().mkString(" "))
    result02.mapPartitions { people =>
      val stringWriter = new StringWriter()
      val csvWriter = new CSVWriter(stringWriter)
      csvWriter.writeAll(people.toList)
      Iterator(stringWriter.toString)
    }.saveAsTextFile(outputPath)
  }

  case class Person(name: String, favouriteAnimal: String)

}
