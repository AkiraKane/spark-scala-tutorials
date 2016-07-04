package com.pinecone.tutorials.spark4basic

import org.apache.log4j.{Level, Logger}
import org.apache.spark._
import org.eclipse.jetty.client.ContentExchange
import org.eclipse.jetty.client.HttpClient

/**
  * Created by leeivan on 7/4/16.
  */
object BasicMapPartitions {
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  System.setProperty("hadoop.home.dir", "C:\\winutils")
  def main(args: Array[String]) {
    val master = args.length match {
      case x: Int if x > 0 => args(0)
      case _ => "local"
    }
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("BasicMapPartitions"))
    val input = sc.parallelize(List("KK6JKQ", "Ve3UoW", "kk6jlk", "W6BB"))
    val result = input.mapPartitions {
      signs =>
        val client = new HttpClient()
        client.start()
        signs.map { sign =>
          val exchange = new ContentExchange(true);
          exchange.setURL(s"http://qrzcq.com/call/${sign}")
          client.send(exchange)
          exchange
        }.map { exchange =>
          exchange.waitForDone();
          exchange.getResponseContent()
        }
    }
    println(result.collect().mkString(","))
  }
}
