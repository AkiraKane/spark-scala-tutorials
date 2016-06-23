package com.pinecone.tutorials.scala4basic.collection.list

/**
  * Created by leeivan on 4/8/16.
  */
object Opt05 {
  def main(args: Array[String]) {

    val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
    val numbers01 = Nil
    println("---------------------------------")
    println("Head of fruit : " + fruit.head)
    println("Tail of fruit : " + fruit.tail)
    println("Check if fruit is empty : " + fruit.isEmpty)
    println("Check if numbers is empty : " + numbers01.isEmpty)
    val product = (x: Int, y: Int) => {
      val result = x + y
      println(s"added $x by $y to yield $result")
      result
    }
    val numbers02 = List(5, 4, 8, 6, 2)
    val result05 = numbers02.reduceLeft(_ + _)
    val result06 = numbers02.reduceLeft((x, y) => x + y)
    val result07 = numbers02.reduceLeft(_ * _)
    val result08 = numbers02.reduceLeft(_ min _)
    val result09 = numbers02.reduceLeft(_ max _)
    val result12 = numbers02.fold(0) { (x, y) => x + y }
    val result10 = numbers02.foldLeft(20)(_ + _)
    val result11 = numbers02.scanLeft(0)(_ + _)
    val result13 = numbers02.scanRight(0)(_ + _)
    println("---------------------------------")
    println("result05: " + result05.toString)
    println("result06: " + result06.toString)
    println("result07: " + result07.toString)
    println("result08: " + result08.toString)
    println("result09: " + result09.toString)
    println("result10: " + result10.toString)
    println("result11: " + result11.toString)
    println("result13: " + result13.toString)
    println("result12: " + result12.toString)
    println("fold: ")
    numbers02.fold(0)(product)
    println("left: ")
    numbers02.scanLeft(0)(product)
    println("right: ")
    numbers02.scanRight(0)(product)

    val sorted = numbers02.sorted
    val users = List(("HomeWay", 25), ("XSDYM", 23)).sorted
    val sortedByAge = users.sortBy { case (user, age) => age } //List(("XSDYM",23),("HomeWay",25))
    val sortedWith = users.sortWith { case (user1, user2) => user1._2 > user2._2 } //List(("XSDYM",23),("HomeWay",25))
    println("sorted: " + sorted.mkString(" "))
    println("users: " + users.mkString(" "))
    println("sortedByAge: " + sortedByAge.mkString(" "))
    println("sortedWith: " + sortedWith.mkString(" "))

    val odd = numbers02.filter(_ % 2 != 0) // List(1,3)
    val even = numbers02.filterNot(_ % 2 != 0) // List(2,4)
    println("odd: " + odd.mkString(" "))
    println("even: " + even.mkString(" "))

    val plusCnt1 = numbers02.count(_ > 4)
    val plusCnt2 = numbers02.filter(_ > 4).length

    val numbers03 = List(1,2,3)
    val numbers04 = List(2,3,4)
    val diff1 = numbers03 diff numbers04   // List(1)
    val diff2 = numbers04.diff(numbers03)   // List(4)
    val union1 = numbers03 union numbers04  // List(1,2,3,2,3,4)
    val union2 = numbers04 ++ numbers03        // List(2,3,4,1,2,3)
    val intersection = numbers03 intersect numbers04  //List(2,3)

    val data = List(("HomeWay","Male"),("XSDYM","Femail"),("Mr.Wang","Male"))
    val group1 = data.groupBy(_._2) // = Map("Male" -> List(("HomeWay","Male"),("Mr.Wang","Male")),"Female" -> List(("XSDYM","Femail")))
    val group2 = data.groupBy{case (name,sex) => sex} // = Map("Male" -> List(("HomeWay","Male"),("Mr.Wang","Male")),"Female" -> List(("XSDYM","Femail")))
    val fixSizeGroup = data.grouped(2).toList // = Map("Male" -> List(("HomeWay","Male"),("XSDYM","Femail")),"Female" -> List(("Mr.Wang","Male")))

    class Foo(val name: String, val age: Int, val sex: Symbol)
    object Foo {
      def apply(name: String, age: Int, sex: Symbol) = new Foo(name, age, sex)
    }
    val fooList = Foo("Hugh Jass", 25, 'male) ::
      Foo("Biggus Dickus", 43, 'male) ::
      Foo("Incontinentia Buttocks", 37, 'female) ::
      Nil
    val result02 = fooList.foldLeft(List[String]()) { (z, f) =>
      val title = f.sex match {
        case 'male => "Mr."
        case 'female => "Ms."
      }
      z :+ s"$title ${f.name}, ${f.age}"
    }

    println("---------------------------------")
    println("result02: " + result02.mkString(" "))

    val parArray = (1 to 10000).toArray.par
    val result04 = parArray.fold(0)(_ + _)
    println("result04: " + result04.toString)


    val lastNames = List("Smith", "Jones", "Frankenstein", "Bach", "Jackson", "Rodin").par
    val result03 = lastNames.map(_.toUpperCase)
    println("---------------------------------")
    println("result03: " + result03.mkString(" "))


  }
}
