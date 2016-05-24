package tutorials.scala4basic.collection.list

/**
  * Created by leeivan on 4/8/16.
  */
object Test {
  def main(args: Array[String]) {

    val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
    val nums = Nil
    println("---------------------------------")
    println("Head of fruit : " + fruit.head)
    println("Tail of fruit : " + fruit.tail)
    println("Check if fruit is empty : " + fruit.isEmpty)
    println("Check if nums is empty : " + nums.isEmpty)
    val product = (x: Int, y: Int) => {
      val result = x + y
      println(s"added $x by $y to yield $result")
      result
    }
    val numbers = List(5, 4, 8, 6, 2)
    val result05 = numbers.reduceLeft(_ + _)
    val result06 = numbers.reduceLeft((x, y) => x + y)
    val result07 = numbers.reduceLeft(_ * _)
    val result08 = numbers.reduceLeft(_ min _)
    val result09 = numbers.reduceLeft(_ max _)
    val result12 = numbers.fold(0) { (x, y) => x + y }
    val result10 = numbers.foldLeft(20)(_ + _)
    val result11 = numbers.scanLeft(0)(_ + _)
    val result13 = numbers.scanRight(0)(_ + _)
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
    println("left: ")
    numbers.scanLeft(0)(product)
    println("right: ")
    numbers.scanRight(0)(product)

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
