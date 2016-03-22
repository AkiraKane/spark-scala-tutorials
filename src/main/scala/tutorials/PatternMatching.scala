package tutorials

/**
  * Created by leeivan on 3/22/16.
  */
object PatternMatching {
  def main(args: Array[String]) {
    val alice = new Person("Alice", 25)
    val bob = new Person("Bob", 32)
    val charlie = new Person("Charlie", 32)

    for (person <- List(alice, bob, charlie)) {
      person match {
        case Person("Alice", 25) => println("Hi Alice!")
        case Person("Bob", 32) => println("Hi Bob!")
        case Person(name, age) =>
          println("Age: " + age + " year, name: " + name + "?")
      }
    }
    println("----------------------------------")
    println(matchTest("two"))
    println(matchTest("test"))
    println(matchTest(1))
  }

  /**
    * Matching Using case Classes
    * The case classes are special classes that are used in pattern matching with case expressions. Syntactically, these
    * are standard classes with a special modifier: case. Following is a simple pattern matching example using case class:
    *
    * @param name
    * @param age
    */
  case class Person(name: String, age: Int)

  /**
    * A pattern match includes a sequence of alternatives, each starting with the keyword case. Each alternative includes
    * a pattern and one or more expressions, which will be evaluated if the pattern matches. An arrow symbol => separates
    * the pattern from the expressions. Here is a small example, which shows how to match against an integer value:
    *
    * @param x
    */
  def matchTest(x: Any): Any = x match {
    case 1 => "one"
    case "two" => 2
    case y: Int => "scala.Int"
    case _ => "many"
  }
}
