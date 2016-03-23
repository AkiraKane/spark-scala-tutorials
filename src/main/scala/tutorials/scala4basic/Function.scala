package tutorials.scala4basic

import java.util.Date

/**
  * Created by leeivan on 3/17/16.
  */

object Function {
  def main(args: Array[String]) {
    delayed(time());

    println("--------------------------------")
    callByName(something());
    println("--------------------------------")
    callByValue(something());
    println("--------------------------------")
    printInt(b = 5, a = 7);
    println("--------------------------------")
    printStrings("Hello", "Scala", "Python");
    println("--------------------------------")
    for (i <- 1 to 10)
      println("Factorial of " + i + ": = " + factorial(i))
    println("--------------------------------")
    println("Returned Value : " + addIntDefault());
    println("--------------------------------")
    println(apply(layout, 10))
    println("--------------------------------")
    println(factorial(0))
    println(factorial(1))
    println(factorial(2))
    println(factorial(3))
    println("--------------------------------")
    val date = new Date
    val logWithDateBound = log(date, _: String)
    logWithDateBound("message1")
    Thread.sleep(1000)
    logWithDateBound("message2")
    Thread.sleep(1000)
    logWithDateBound("message3")
    println("--------------------------------")
    val str1: String = "Hello, "
    val str2: String = "Scala!"
    println("str1 + str2 = " + strcat(str1)(str2))
  }

  /**
    * basic feature of function
    */
  def addInt(a: Int, b: Int): Int = {
    var sum: Int = 0
    sum = a + b

    return sum
  }

  def printMe(): Unit = {
    println("Hello, Scala!")
  }

  /**
    * call-by-name and call-by-value
    * Typically, parameters to functions are by-value parameters; that is, the value of the parameter is determined
    * before it is passed to the function. But what if we need to write a function that accepts as a parameter an expression
    * that we don't want evaluated until it's called within our function? For this circumstance, Scala offers call-by-name parameters.
    */

  def time() = {
    println("Getting time in nano seconds")
    System.nanoTime
  }

  def delayed(t: => Long) = {
    println("In delayed method")
    println("Param: " + t)
    t
  }

  def something() = {
    println("calling something")
    1 // return value
  }

  def callByValue(x: Int) = {
    println("x1=" + x)
    println("x2=" + x)
  }

  def callByName(x: => Int) = {
    println("x1=" + x)
    println("x2=" + x)
  }

  /**
    * Scala Functions with Named Arguments
    * In a normal function call, the arguments in the call are matched one by one in the order of the
    * parameters of the called function. Named arguments allow you to pass arguments to a function in
    * a different order. The syntax is simply that each argument is preceded by a parameter name and
    * an equals sign.
    */
  def printInt(a: Int, b: Int) = {
    println("Value of a : " + a);
    println("Value of b : " + b);
  }


  /**
    * Scala Function with Variable Arguments
    * Scala allows you to indicate that the last parameter to a function may be repeated. This allows
    * clients to pass variable length argument lists to the function.
    */
  def printStrings(args: String*) = {
    var i: Int = 0;
    for (arg <- args) {
      println("Arg value[" + i + "] = " + arg);
      i = i + 1;
    }
  }

  /**
    * Scala Recursion Functions
    *
    */
  def factorial(n: BigInt): BigInt = {
    if (n <= 1)
      1
    else
      n * factorial(n - 1)
  }

  /**
    * Scala Default Parameter Values for a Function
    *
    * @param a
    * @param b
    * @return
    */
  def addIntDefault(a: Int = 5, b: Int = 7): Int = {
    var sum: Int = 0
    sum = a + b

    return sum
  }

  /**
    * Scala Higher-Order Functions
    * Scala allows the definition of higher-order functions. These are functions that take other functions as parameters,
    * or whose result is a function. For example in the following code, apply() function takes another function f and a
    * value v and applies function f to v:
    *
    * @param f
    * @param v
    * @return
    */
  def apply(f: Int => String, v: Int) = f(v)

  def layout[A](x: A) = "[" + x.toString() + "]"

  /**
    * Scala Nested Functions
    * Scala allows you to define functions inside a function and functions defined inside other functions are called local
    * functions. Here is an implementation of a factorial calculator, where we use a conventional technique of calling a
    * second, nested method to do the work:
    *
    * @param i
    * @return
    */
  def factorial(i: Int): Int = {
    def fact(i: Int, accumulator: Int): Int = {
      if (i <= 1)
        accumulator
      else
        fact(i - 1, i * accumulator)
    }
    fact(i, 1)
  }

  /**
    * Scala Anonymous Functions
    * Scala provides a relatively lightweight syntax for defining anonymous functions. Anonymous functions in source code
    * are called function literals and at run time, function literals are instantiated into objects called function values.
    * Scala supports first-class functions, which means you can express functions in function literal syntax, i.e., (x: Int) => x + 1,
    * and that functions can be represented by objects, which are called function values. The following expression creates a
    * successor function for integers:
    */
  var inc = (x: Int) => x + 1
  var x = inc(7) - 1
  var mul = (x: Int, y: Int) => x * y
  println(mul(3, 4))
  var userDir = () => {
    System.getProperty("user.dir")
  }
  println(userDir)

  /**
    * Scala Partially Applied Functions
    * We want to invoke the method multiple times, with the same value for date but different values for message. We
    * can eliminate the noise of passing the date to each call by partially applying that argument to the log( ) method.
    * To do so, we first bind a value to the date parameter and leave the second parameter unbound by putting an underscore
    * at its place. The result is a partially applied function that we've stored in a variable. We can now invoke this new
    * method with only the unbound argument message as follows:
    *
    * @param date
    * @param message
    */
  def log(date: Date, message: String) = {
    println(date + "----" + message)
  }

  /**
    * Scala Currying Functions
    *
    * @param s1
    * @return
    */
  def strcat(s1: String)(s2: String) = {
    s1 + s2
  }
}