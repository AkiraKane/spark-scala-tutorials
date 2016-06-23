package com.pinecone.tutorials.scala4basic.collection.list

/**
  * Created by ivan on 2016/6/23.
  */
object Opt04 {
  def main(args: Array[String]) {
    val nums = List(1, 2, 3)
    val square = (x: Int) => x * x
    val result01 = nums.map(num => num * num) //List(1,4,9)
    val result02 = nums.map(math.pow(_, 2)) //List(1,4,9)
    val result03 = nums.map(square) //List(1,4,9)
    println("result01: " + result01.mkString(" "))
    println("result02: " + result02.mkString(" "))
    println("result03: " + result03.mkString(" "))

    val text01 = List("Homeway,25,Male", "XSDYM,23,Female")
    val result04 = text01.map(_.split(",")(1))
    val result05 = text01.map(line => {
      val fields = line.split(",")
      val user = fields(0)
      val age = fields(1).toInt
      (user, age)
    })
    println("result04: "+ result04.mkString(" "))
    println("result05: "+ result05.mkString(" "))

    val text02 = List("A,B,C","D,E,F")
    val result06 = text02.map(_.split(",").toList) // List(List("A","B","C"),List("D","E","F"))
    val result07 = result06.flatten          // List("A","B","C","D","E","F")
    val result08 = text02.flatMap(_.split(",").toList) // List("A","B","C","D","E","F")
    println("result06: "+ result06.mkString(" "))
    println("result07: "+ result07.mkString(" "))
    println("result08: "+ result08.mkString(" "))

  }
}

