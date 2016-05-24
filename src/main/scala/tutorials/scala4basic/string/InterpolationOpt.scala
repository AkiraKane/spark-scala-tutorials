package tutorials.scala4basic.string

/**
  * Created by ivan on 2016/5/24.
  */
object InterpolationOpt {
  def main(args: Array[String]) {
    val result01 = "James"
    println(s"Hello, $result01") // Hello, James
    val height = 1.9d
    val result02 = "James"
    println(f"$result02%s is $height%2.4f meters tall") // James is 1.90 meters tall
    val result03 = s"a\nb"
    print(result03)
  }
}
