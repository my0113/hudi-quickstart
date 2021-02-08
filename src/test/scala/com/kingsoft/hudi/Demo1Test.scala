package com.kingsoft.hudi

import com.kingsoft.Configured
import org.apache.hudi.DataSourceReadOptions._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.junit.{Before, Test}
import Demo1._
import DemoUtils._

import scala.collection.JavaConverters._

object Demo1Test {

  // 类似Hive中的DB
  val basePath = "file:/D:/tmp"
  // 类似Hive中的Table
  val tableName = "tbl_temperature"
  // 数据所在的路径
  val path = s"$basePath/$tableName"
  val conf: SparkConf = new SparkConf()
    .set("spark.master", s"local[${Runtime.getRuntime.availableProcessors()/2}]")
    .set("spark.app.name", Demo1Test.getClass.getName)
    .setAll(Configured.sparkConf().asScala)
  var spark: SparkSession = _

  @Before
  def init(): Unit = {
    System.setProperty("hadoop.home.dir", "D:\\softs\\developer\\Apache\\hadoop-3.1.0")
    spark = SparkSession.builder().config(conf).getOrCreate()
    // 关闭日志
    spark.sparkContext.setLogLevel("OFF")
  }

  @Test
  def insertTest(): Unit = {
    insert(spark.createDataFrame(insertData.toBuffer.asJava, classOf[TemperatureBean]), tableName, "deviceId", "deviceType", "cdt", path)
    query(spark.read.format(SOURCE).options(buildQuery(QUERY_TYPE_SNAPSHOT_OPT_VAL)).load(s"$path/*/*").withColumn("queryType", lit("查询方式为：快照（默认）")).orderBy(col("deviceId").asc))
  }

  @Test
  def updateTest(): Unit = {
    update(spark.createDataFrame(updateData.toBuffer.asJava, classOf[TemperatureBean]), tableName, "deviceId", "deviceType", "cdt", path)
    val commitTime:String = spark.read.format(SOURCE).load(s"$path/*/*").dropDuplicates("_hoodie_commit_time").select(col("_hoodie_commit_time").as("commitTime")).orderBy(col("commitTime")).first().getAs(0)
    query(spark.read.format(SOURCE).options(buildQuery(QUERY_TYPE_INCREMENTAL_OPT_VAL,Option((commitTime.toLong-2).toString))).load(s"$path/*/*").withColumn("queryType", lit("查询方式为：增量查询")).orderBy(col("deviceId").asc))
  }

  @Test
  def deleteTest(): Unit = {
    delete(spark.createDataFrame(deleteData.toBuffer.asJava, classOf[TemperatureBean]), tableName, "deviceId", "deviceType", "cdt", path)
    query(spark.read.format(SOURCE).options(buildQuery(QUERY_TYPE_OPT_KEY)).load(s"$path/*/*").withColumn("queryType", lit("查询方式为：读时优化")).orderBy(col("deviceId").asc))
  }

}
