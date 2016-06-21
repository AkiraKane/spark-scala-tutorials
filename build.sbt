name := "spark-scala-tutorials"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq("org.apache.spark" % "spark-core_2.11" % "1.6.1",
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scala-lang" % "scala-library" % scalaVersion.value,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.apache.commons" % "commons-lang3" % "3.3.2",
  "org.slf4j" % "slf4j-api" % "1.7.10",
  "org.apache.spark" % "spark-sql_2.11" % "1.6.1",
  "org.apache.spark" % "spark-streaming_2.11" % "1.6.1",
  "org.apache.spark" % "spark-mllib_2.11" % "1.6.1",
  "log4j" % "log4j" % "1.2.17",
  "net.sf.opencsv" % "opencsv" % "2.3",
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.apache.spark" % "spark-streaming-twitter_2.11" % "1.6.1" exclude("org.apache.spark", "spark-streaming_2.11"),
  "org.apache.spark" % "spark-streaming-kafka_2.11" % "1.6.1" exclude("org.apache.spark", "spark-streaming_2.11"),
  "org.apache.spark" % "spark-streaming-flume_2.11" % "1.6.1" exclude("org.apache.spark", "spark-streaming_2.11"),
  "org.apache.spark" % "spark-hive_2.11" % "1.6.1",
  "com.typesafe.play" % "play-json_2.11" % "2.4.6",
  "com.esotericsoftware" % "kryo" % "3.0.3").map(
  _.excludeAll(ExclusionRule(organization = "org.mortbay.jetty")))


ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

dependencyOverrides ++= Set(
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.4"
)

sbtavrohugger.SbtAvrohugger.avroSettings
(scalaSource in avroConfig) := new java.io.File("src/main/scala")










