package tutorials

/**
  * Created by leeivan on 3/23/16.
  */

import org.apache.spark.SparkContext
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import tutorials.spark4.MoreTestableLoadCsvExample

class MoreTestableLoadCsvExampleSuite extends FunSuite with ShouldMatchers {
  test("summ data on input") {
    val sc = new SparkContext("local", "Load CSV Example")
    val counter = sc.accumulator(0)
    val input = sc.parallelize(List("1,2", "1,3"))
    val result = MoreTestableLoadCsvExample.handleInput(counter, input)
    result.collect() should equal(Array[Int](3, 4))
    sc.stop()
  }
  test("should parse a csv line with numbers") {
    MoreTestableLoadCsvExample.parseLine("1,2") should equal(Array[Double](1.0, 2.0))
    MoreTestableLoadCsvExample.parseLine("100,-1,1,2,2.5") should equal(Array[Double](100, -1, 1.0, 2.0, 2.5))
  }
  test("should error if there is a non-number") {
    evaluating {
      MoreTestableLoadCsvExample.parseLine("pandas")
    } should produce[NumberFormatException]
  }
}