package com.pinecone.tutorials.scala4basic.collection.map

/**
  * Created by leeivan on 3/22/16.
  */
object Options {
  def main(args: Array[String]) {
    val capitals = Map("France" -> "Paris", "USA" -> "Washington DC", "China" -> "Beijing")
    val result01 = capitals.get("China")
    val result02 = capitals.get("India")

    println("result01: " + result01)
    println("result02: " + result02)
    val result03 = result01.get
    println("result03: " + result03)
    val result04 = result02 get;
    println("result04: " + result04)
    val result05 = result02 getOrElse("New Delhi");
    println("result05: " + result05)


    println("show(capitals.get( \"Chine\")) : " +
      show(capitals.get("China")))
    println("show(capitals.get( \"India\")) : " +
      show(capitals.get("India")))

    println("----------------------------------------")
    val a: Option[Int] = Some(5)
    val b: Option[Int] = None
    println("a.getOrElse(0): " + a.getOrElse(0))
    println("b.getOrElse(10): " + b.getOrElse(10))

    println("----------------------------------------")
    println("a.isEmpty: " + a.isEmpty)
    println("b.isEmpty: " + b.isEmpty)
  }

  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

}
