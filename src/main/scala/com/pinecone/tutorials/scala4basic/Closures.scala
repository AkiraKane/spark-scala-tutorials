package com.pinecone.tutorials.scala4basic

/**
  * Created by leeivan on 3/22/16.
  */
object Closures {
  def main(args: Array[String]) {
    println("muliplier(1) value = " + multiplier(1))
    println("muliplier(2) value = " + multiplier(2))
  }

  var factor = 3
  val multiplier = (i: Int) => i * factor
}
