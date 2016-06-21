package com.pinecone.tutorials.scala4basic

/**
  * Created by leeivan on 3/22/16.
  */
object PatternMatching {
  def main(args: Array[String]) {
    case class Person(name: String, age: Int)
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
    println("injection and extractor")
    object IPAddress {

      def unapply(ip: String): Option[(String, String, String, String)] = {
        val tokens = ip split "\\."
        if (tokens.length == 4 && isValid(tokens)) Some(tokens(0), tokens(1), tokens(2), tokens(3)) else None
      }

      private def isValid(tokens: Array[String]): Boolean = {
        tokens forall { elem =>
          try {
            val intValue = elem.toInt
            intValue >= 0 && intValue <= 255
          } catch {
            case _ : Throwable=> false
          }
        }
      }
    }
    val IP = "127.0.0.1"
    val nonIP = "128.-112.ABC."
    IP match {
      case IPAddress(_, _, _, a) => println(a)
      case _ => println("Invalid ip address")
    }
    nonIP match {
      case IPAddress(_, _, _, a) => println(a)
      case _ => println("Invalid ip address")
    }
    println("------------------------------------------")
    val ips = "192.168.0.1,192.168.0.2,192.168.0.3,192.168.0.4"
    object IPAddresses {
      def unapplySeq(ips: String): Option[Seq[String]] = {
        Some(ips split ",")
      }
    }
    ips match {
      case IPAddresses(IPAddresses(a, _, _, _), IPAddresses(b, _, _, _), _*) => println(a + " " + b)
      case _ => println("Invalid IP addresses")
    }

    object PositiveInt {
      def unapply(n: Int): PositiveIntOpt =
        new PositiveIntOpt(n)
    }
  }
}

