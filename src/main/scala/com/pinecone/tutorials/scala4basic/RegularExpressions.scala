package com.pinecone.tutorials.scala4basic
import scala.util.matching.Regex
/**
  * Created by ivan on 2016/6/23.
  */
object RegularExpressions {
  def main(args: Array[String]) {
    val pattern = "Scala".r
    val str = "Scala is Scalable and cool"
    println("--------------------------------")
    println(pattern findFirstIn str)
    /**
      * We create a String and call the r( ) method on it. Scala implicitly converts the String to a RichString and invokes
      * that method to get an instance of Regex. To find a first match of the regular expression, simply call the findFirstIn()
      * method. If instead of finding only the first occurrence we would like to find all occurrences of the matching word,
      * we can use the findAllIn( ) method and in case there are multiple Scala words available in the target string,
      * this will return a collection of all matching words.You can make use of the mkString( ) method to concatenate the
      * resulting list and you can use a pipe (|) to search small and capital case of Scala and you can use Regex constructor
      * instead or r() method to create a pattern as follows:
      */
    val pattern1 = new Regex("(S|s)cala")
    println("--------------------------------")
    println((pattern1 findAllIn str).mkString(","))

    /**
      * If you would like to replace matching text, we can use replaceFirstIn( ) to replace the first match or replaceAllIn( )
      * to replace all occurrences as follows:
      */
    println("--------------------------------")
    println(pattern replaceFirstIn(str, "Java"))
  }

}