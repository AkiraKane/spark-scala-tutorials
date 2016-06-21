package com.pinecone.tutorials.scala4basic.collection.list

/**
  * Created by ivan on 2016/5/26.
  */
object Opt01 {
  def main(args: Array[String]) {

    val left = List(1, 2, 3)
    val right = List(4, 5, 6)

    //以下操作等价
    val result01 = left ++ right // List(1,2,3,4,5,6)
    val result02 = left ++: right // List(1,2,3,4,5,6)
    val result03 = right.++:(left) // List(1,2,3,4,5,6)
    val result04 = right.:::(left) // List(1,2,3,4,5,6)

    //以下操作等价
    val result05 = 0 +: left //List(0,1,2,3)
    val result06 = left.+:(0) //List(0,1,2,3)

    //以下操作等价
    val result07 = left :+ 4 //List(1,2,3,4)
    val result08 = left.:+(4) //List(1,2,3,4)

    //以下操作等价
    val result09 = 0 :: left //List(0,1,2,3)
    val result10 = left.::(0) //List(0,1,2,3)
    println("result01: " + result01.mkString(" "))
    println("result02: " + result02.mkString(" "))
    println("result03: " + result03.mkString(" "))
    println("result04: " + result04.mkString(" "))
    println("result05: " + result05.mkString(" "))
    println("result06: " + result06.mkString(" "))
    println("result07: " + result07.mkString(" "))
    println("result08: " + result08.mkString(" "))
    println("result09: " + result09.mkString(" "))
    println("result10: " + result10.mkString(" "))
    val ab = collection.mutable.ArrayBuffer[Int]()
    //Array对应的可变ArrayBuffer：
    ab +=(1, 3, 5, 7)
    ab ++= List(9, 11) // ArrayBuffer(1, 3, 5, 7, 9, 11)
    ab.toArray // Array (1, 3, 5, 7, 9, 11)
    ab.clear // ArrayBuffer()

    val list: List[Int] = List(1, 3, 4, 5, 6) // 或者 List(1 to 6:_*)
    val list1 = List("a", "b", "c", "d") // 或者 List('a' to 'd':_*) map (_.toString)
    //元素合并进List用::
    val list2 = "a" :: "b" :: "c" :: Nil // Nil是必须的
    val list3 = "begin" :: list2
    val list4 = list2 ++ "end" ++ Nil
    val list5 = list2 ::: "end" :: Nil // 相当于 list2 ::: List("end")
    println("list: " + list)
    println("list1: " + list1)
    println("list2: " + list2)
    println("list3: " + list3)
    println("list4: " + list4)
    println("list5: " + list5)
    //List对应的可变ListBuffer ：
    val lbuffer01 = scala.collection.mutable.ListBuffer(1, 2, 3)
    lbuffer01.append(4) // ListBuffer(1, 2, 3, 4)
    val lbuffer02 = collection.mutable.ListBuffer[Int]()
    lbuffer02 +=(1, 3, 5, 7)
    lbuffer02 ++= List(9, 11) // ListBuffer(1, 3, 5, 7, 9, 11)
    lbuffer02.toList // List(1, 3, 5, 7, 9, 11)
    lbuffer02.clear // ListBuffer()
    val head :: body = List(4, "a", "b", "c", "d")
    println("head: " + head)
    println("body: " + body)
    val a :: b :: c = List(1, 2, 3)
    println("a: "+ a)
    println("b: "+ b)
    println("c: "+ c)
    //定义固定长度的List：
    List.fill(10)(2) // List(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
    Array.fill(10)(2) // Array(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
    //    又如：
    List.fill(10)(scala.util.Random.nextPrintableChar)
    // List(?, =, ^, L, p, <, \, 4, 0, !)
    List.fill(10)(scala.util.Random.nextInt(101))
    // List(80, 45, 26, 75, 24, 72, 96, 88, 86, 15)
  }
}
