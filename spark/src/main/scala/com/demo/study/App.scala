package com.demo.study

import org.apache.spark.sql.SparkSession

/**
 * Hello world!
 *
 */
trait LocalSparkSession {
 @transient val spark = SparkSession.builder()
}
