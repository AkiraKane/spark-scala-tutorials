name := "spark-scala-tutorials"

version := "1.0"

scalaVersion := "2.10.5"

libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.6.1"
libraryDependencies += "org.apache.spark" % "spark-sql_2.10" % "1.6.1"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.10" % "1.6.1"
libraryDependencies += "org.apache.spark" % "spark-mllib_2.10" % "1.6.1"
libraryDependencies += "log4j" % "log4j" % "1.2.17"
libraryDependencies += "net.sf.opencsv" % "opencsv" % "2.3"
libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "org.apache.spark" % "spark-streaming-twitter_2.10" % "1.6.1" exclude("org.apache.spark", "spark-streaming_2.10")
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.1" exclude("org.apache.spark", "spark-streaming_2.10")
libraryDependencies += "org.apache.spark" % "spark-streaming-flume_2.10" % "1.6.1" exclude("org.apache.spark", "spark-streaming_2.10")
libraryDependencies += "org.apache.spark" % "spark-hive_2.10" % "1.6.1"












