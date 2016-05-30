package tutorials.scala4basic.collection.map

/**
  * Created by ivan on 2016/5/26.
  */
object BufferOpt {
  def main(args: Array[String]) {
    val buf = scala.collection.mutable.ArrayBuffer.empty[Int]
    val result01 = buf += 1
    println("result01: " + result01.mkString(" "))
    val result02 = buf +=10
    println("result02: " + result02.mkString(" "))

  }
}
