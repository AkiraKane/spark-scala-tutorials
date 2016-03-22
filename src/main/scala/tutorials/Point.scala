package tutorials


/**
  * Created by leeivan on 3/22/16.
  */
class Point(xc: Int, yc: Int) extends Equal {
  var x: Int = xc
  var y: Int = yc

  def isEqual(obj: Any) =
    obj.isInstanceOf[Point] &&
      obj.asInstanceOf[Point].x == x
}

object Test {
  def main(args: Array[String]) {
    val p1 = new Point(2, 3)
    val p2 = new Point(2, 4)
    val p3 = new Point(3, 3)

    println(p1.isNotEqual(p2))
    println(p1.isNotEqual(p3))
    println(p1.isNotEqual(2))
  }
}

/**
  * This trait consists of two methods isEqual and isNotEqual. Here, we have not given any implementation for isEqual where
  * as another method has its implementation. Child classes extending a trait can give implementation for the un-implemented
  * methods. So a trait is very similar to what we have abstract classes in Java. Below is a complete example to show the
  * concept of traits:
  */
trait Equal {
  def isEqual(x: Any): Boolean

  def isNotEqual(x: Any): Boolean = !isEqual(x)
}