package com.aliapesha.testtask.spark

import com.aliapesha.testtask.core.entity.HouseStat
import com.datastax.spark.connector._
import org.apache.spark.sql.SparkSession

object Main {
  //http://archive.ics.uci.edu/ml/machine-learning-databases/housing/housing.data
  def main(args: Array[String]) {
    val inputFile = args(0)

    val spark = SparkSession
      .builder
      .appName("Spark app")
      .master("local[*]")
      .config("spark.cassandra.connection.host", "192.168.99.100")
      .config("spark.cassandra.auth.username", "cassandra")
      .config("spark.cassandra.auth.password", "password123")
      .getOrCreate()

    val sc = spark.sparkContext

    val html = scala.io.Source.fromURL(inputFile).mkString
    val list = html.split("\n").filter(_ != "")
    val rdd = sc.parallelize(list)
      .map(line => line.split(" ").filter(_.nonEmpty))
      .map(HouseStat.fromArray)

    rdd.saveToCassandra("testtask", "housestats", SomeColumns(
      "id",
      "crime",
      "zn",
      "indus",
      "chas",
      "nox",
      "averagerooms",
      "ageproportion",
      "weighteddistance",
      "rad",
      "tax",
      "ptratio",
      "bindex",
      "lstat",
      "medianvalue"
    ))
  }

}


