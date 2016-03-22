package solutions

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

//define the schema using a case class
case class Auction(auctionid: String, bid: Float, bidtime: Float, bidder: String, bidderrate: Integer, openbid: Float, price: Float, item: String, daystolive: Integer)

object AuctionsDFApp {
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("SparkDFebay").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    import sqlContext.implicits._
    val usrhome = System.getenv("HOME")
    val aucFile = usrhome.concat("/Documents/Lab3/DEV360Data/auctiondata.csv")
    // load the data into an RDD
    val ebayText = sc.textFile(aucFile)

    // create an RDD of Auction objects 
    val ebay = ebayText.map(_.split(",")).map(p => Auction(p(0), p(1).toFloat, p(2).toFloat, p(3), p(4).toInt, p(5).toFloat, p(6).toFloat, p(7), p(8).toInt))

    // change ebay RDD of Auction objects to a DataFrame
    val auction = ebay.toDF()
    // How many auctions were held ? 
    val count = auction.select("auctionid").distinct.count
    System.out.println(count)

  }
}
