package com.demo.study.dependency

import org.apache.spark.sql.SparkSession

object OneToOneDependency {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("test")
      .master("local[1]")
      .config("spark.testing.memory", "536870912")
      .enableHiveSupport().getOrCreate()
    val num = Array(100, 90, 70)
    val rdd = spark.sparkContext.parallelize(num)
    val mapRdd = rdd.map(_*2)
    mapRdd.collect().foreach(println)
  }

}
