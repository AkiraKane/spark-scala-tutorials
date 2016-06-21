package com.pinecone.tutorials.scala4basic.collection.list

/**
  * Created by ivan on 2016/5/26.
  */
object Opt02 {
  def main(args: Array[String]) {

    val list1 = new Array[String](0) // Array()

    val list2 = new Array[String](3) // Array(null, null, null)

    val list3: Array[String] = new Array(3) // // Array(null, null, null)

    val list4 = Array("a", "b", "c", "d") // 相当于Array.apply("a","b","c","d")
    //定义一个类型为Any的Array
    val aa01 = Array[Any](1, 2)
    val aa02: Array[Any] = Array(1, 2)
    val aa03: Array[_] = Array(1, 2)
    val aa04 = Array(1, 3, 5, 7, 9, 11)
    val aa05 = Array[Int](1 to 11 by 2: _*)
    val aa06 = (1 to 11 by 2).toArray
    val aa07 = (1 to 11 by 2).toList
    println("aa05: " + aa05.mkString(" "))
    println("aa06: " + aa06.mkString(" "))
    println("aa07: " + aa07.mkString(" "))
  }
}
