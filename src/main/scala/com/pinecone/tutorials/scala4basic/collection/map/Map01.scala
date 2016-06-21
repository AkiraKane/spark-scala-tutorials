package com.pinecone.tutorials.scala4basic.collection.map

/**
  * Created by leeivan on 4/8/16.
  */
object Map01 {
  def main(args: Array[String]) {
    val colors = Map("red" -> "#FF0000",
      "azure" -> "#F0FFFF",
      "peru" -> "#CD853F")

    val nums: Map[Int, Int] = Map()

    println("-----------------------------------------")
    println("Keys in colors : " + colors.keys)
    println("Values in colors : " + colors.values)
    println("Check if colors is empty : " + colors.isEmpty)
    println("Check if nums is empty : " + nums.isEmpty)
    println("---------Concatenating Maps---------------")
    val colors1 = Map("red" -> "#FF0000",
      "azure" -> "#F0FFFF",
      "peru" -> "#CD853F")
    val colors2 = Map("blue" -> "#0033FF",
      "yellow" -> "#FFFF00",
      "red" -> "#FF0000")

    // use two or more Maps with ++ as operator
    var color12 = colors1 ++ colors2
    println("colors1 ++ colors2 : " + color12)

    // use two maps with ++ as method
    color12 = colors1.++(colors2)
    println("colors1.++(colors2)) : " + color12)
    println("---------Print Keys and Values from a Map----------------")
    colors.keys.foreach { i =>
      print("Key = " + i)
      println(" Value = " + colors(i))
    }
    println("---------Check for a Key in Map--------------------------")
    if( colors.contains( "red" )){
      println("Red key exists with value :"  + colors("red"))
    }else{
      println("Red key does not exist")
    }
    if( colors.contains( "maroon" )){
      println("Maroon key exists with value :"  + colors("maroon"))
    }else{
      println("Maroon key does not exist")
    }
  }
}
