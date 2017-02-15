import sbt._

object Dependencies {

  lazy val akkaDeps = {
    val akkaVersion = "2.3.14"
    Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
      "com.typesafe.akka" %% "akka-http" % "10.0.1",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.1",
      "com.typesafe.akka" %% "akka-http-testkit" % "10.0.1"
    )
  }

  lazy val cassandraDeps = {
    lazy val driverCore = "3.0.0"
    lazy val cassandraVersion = "2.2.5"
    val phantomV = "2.0.2"
    Seq(
      "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.1" exclude("org.xerial.snappy", "snappy-java"),
      "com.datastax.cassandra" % "cassandra-driver-core" % driverCore,
      "org.apache.cassandra" % "cassandra-all" % cassandraVersion,
      "commons-pool" % "commons-pool" % "1.6",
      "com.outworkers"      %%  "phantom-dsl"                 % phantomV,
      "com.outworkers"      %%  "phantom-streams"             % phantomV,
      "com.websudos"        %%  "util-testing"                % "0.13.0"    % "test, provided"

    )
  }

  lazy val typesafeDeps = Seq(
    "com.typesafe" % "config" % "1.3.0"
  )
  lazy val logDeps = {
    val slf4jVersion = "1.7.10"
    Seq(
      "ch.qos.logback" % "logback-core" % "1.1.2",
      "ch.qos.logback" % "logback-classic" % "1.1.2" % "runtime",
      "org.slf4j" % "slf4j-api" % slf4jVersion,
      "org.slf4j" % "jul-to-slf4j" % slf4jVersion,
      "org.slf4j" % "jcl-over-slf4j" % slf4jVersion,
      "uk.org.lidalia" % "sysout-over-slf4j" % "1.0.2"
    )
  }
  lazy val sparkDeps = Seq(
    "org.apache.spark" %% "spark-core" % "2.1.0" % "provided",
    "org.apache.spark" %% "spark-sql" % "2.1.0",
    "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.0-M3"
  )
  lazy val testDeps = Seq(
    "org.scalatest" %% "scalatest" % "2.2.4"
  )
  val scalaVersion = "2.11.4"

}
