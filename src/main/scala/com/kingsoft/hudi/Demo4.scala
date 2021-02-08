package com.kingsoft.hudi

import com.kingsoft.Configured
import org.apache.hudi.DataSourceWriteOptions._
import org.apache.hudi.common.model.EmptyHoodieRecordPayload
import org.apache.hudi.config.HoodieIndexConfig._
import org.apache.hudi.config.HoodieWriteConfig._
import org.apache.hudi.hive.MultiPartKeysValueExtractor
import org.apache.hudi.index.HoodieIndex
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.SaveMode._

import scala.collection.JavaConverters._
import scala.collection.mutable._

/**
 * Spark on Hudi(MOR表) to Hive
 * @ClassName Demo3
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-23 16:13:23
 * @Version V1.0
 */
class Demo4(db:String,
            table:String,
            partition:String,
            url:String,
            user:String="hive",
            password:String="hive") {

  private val hiveCfg = hiveConfig(db,table,partition, url, user, password)
  private val SOURCE = "hudi"

  /**
   * 新增数据
   * @param df                  数据集
   * @param tableName           Hudi表
   * @param primaryKey          主键列名
   * @param partitionField      分区列名
   * @param changeDateField     变更时间列名
   * @param path                数据的存储路径
   */
  def insert(df: DataFrame, tableName: String, primaryKey: String, partitionField: String, changeDateField: String, path: String): Unit = {
    df.write.format(SOURCE)
      .options(Map(
          // 要操作的表
          TABLE_NAME->tableName,
          // 操作的表类型（默认COW）
          TABLE_TYPE_OPT_KEY -> MOR_TABLE_TYPE_OPT_VAL,
          // 执行insert操作
          OPERATION_OPT_KEY->INSERT_OPERATION_OPT_VAL,
          // 设置主键列
          RECORDKEY_FIELD_OPT_KEY->primaryKey,
          // 设置分区列,类似Hive的表分区概念
          PARTITIONPATH_FIELD_OPT_KEY->partitionField,
          // 设置数据更新时间列,该字段数值大的数据会覆盖小的，必须是数值类型
          PRECOMBINE_FIELD_OPT_KEY->changeDateField,
          // 要使用的索引类型，可用的选项是[SIMPLE|BLOOM|HBASE|INMEMORY]，默认为布隆过滤器
          INDEX_TYPE_PROP-> HoodieIndex.IndexType.GLOBAL_BLOOM.name,
          // 执行insert操作的shuffle并行度
          INSERT_PARALLELISM->"2"
        ) ++= hiveCfg
      )
      // 如果数据存在会覆盖
      .mode(Overwrite)
      .save(path)
  }

  /**
   * 修改数据
   * @param df                  数据集
   * @param tableName           Hudi表
   * @param primaryKey          主键列名
   * @param partitionField      分区列名
   * @param changeDateField     变更时间列名
   * @param path                数据的存储路径
   */
  def update(df: DataFrame, tableName: String, primaryKey: String, partitionField: String, changeDateField: String, path: String): Unit = {
    df.write.format(SOURCE)
      .options(Map(
          // 要操作的表
          TABLE_NAME->tableName,
          // 操作的表类型（默认COW）
          TABLE_TYPE_OPT_KEY -> MOR_TABLE_TYPE_OPT_VAL,
          // 执行upsert操作
          OPERATION_OPT_KEY->UPSERT_OPERATION_OPT_VAL,
          // 设置主键列
          RECORDKEY_FIELD_OPT_KEY->primaryKey,
          // 设置分区列,类似Hive的表分区概念
          PARTITIONPATH_FIELD_OPT_KEY->partitionField,
          // 设置数据更新时间列,该字段数值大的数据会覆盖小的
          PRECOMBINE_FIELD_OPT_KEY->changeDateField,
          // 要使用的索引类型，可用的选项是[SIMPLE|BLOOM|HBASE|INMEMORY]，默认为布隆过滤器
          INDEX_TYPE_PROP-> HoodieIndex.IndexType.GLOBAL_BLOOM.name,
          // 执行upsert操作的shuffle并行度
          UPSERT_PARALLELISM->"2"
        ) ++= hiveCfg
      )
      // 如果数据存在会覆盖
      .mode(Append)
      .save(path)
  }

  /**
   * 删除数据
   * @param df                  数据集
   * @param tableName           Hudi表
   * @param primaryKey          主键列名
   * @param partitionField      分区列名
   * @param changeDateField     变更时间列名
   * @param path                数据的存储路径
   */
  def delete(df: DataFrame, tableName: String, primaryKey: String, partitionField: String, changeDateField: String, path: String): Unit = {
    df.write.format(SOURCE)
      .options(Map(
          // 要操作的表
          TABLE_NAME->tableName,
          // 操作的表类型（默认COW）
          TABLE_TYPE_OPT_KEY->MOR_TABLE_TYPE_OPT_VAL,
          // 执行delete操作
          OPERATION_OPT_KEY->DELETE_OPERATION_OPT_VAL,
          // 设置主键列
          RECORDKEY_FIELD_OPT_KEY->primaryKey,
          // 设置分区列,类似Hive的表分区概念
          PARTITIONPATH_FIELD_OPT_KEY->partitionField,
          // 设置数据更新时间列,该字段数值大的数据会覆盖小的
          PRECOMBINE_FIELD_OPT_KEY->changeDateField,
          // 要使用的索引类型，可用的选项是[SIMPLE|BLOOM|HBASE|INMEMORY]，默认为布隆过滤器
          INDEX_TYPE_PROP-> HoodieIndex.IndexType.BLOOM.name,
          // 执行delete操作的shuffle并行度
          DELETE_PARALLELISM->"2",
          // 删除策略有软删除（保留主键且其余字段为null）和硬删除（从数据集中彻底删除）两种，此处为硬删除
          PAYLOAD_CLASS_OPT_KEY->classOf[EmptyHoodieRecordPayload].getName
        ) ++= hiveCfg
      )
      // 如果数据存在会覆盖
      .mode(Append)
      .save(path)
  }

  private def hiveConfig(db:String, table:String, partition:String,url:String,user:String="hive", password:String="hive"): Map[String, String] = {
    scala.collection.mutable.Map(
      // 设置jdbc 连接同步
      HIVE_URL_OPT_KEY -> url,
      // 设置访问Hive的用户名
      HIVE_USER_OPT_KEY -> user,
      // 设置访问Hive的密码
      HIVE_PASS_OPT_KEY -> password,
      // 设置Hive数据库名称
      HIVE_DATABASE_OPT_KEY -> db,
      // 设置Hive表名称
      HIVE_TABLE_OPT_KEY->table,
      // 设置要同步的分区列名
      HIVE_PARTITION_FIELDS_OPT_KEY->partition,
      // 设置数据集注册并同步到hive
      HIVE_SYNC_ENABLED_OPT_KEY -> "true",
      // 设置当分区变更时，当前数据的分区目录是否变更
      BLOOM_INDEX_UPDATE_PARTITION_PATH -> "true",
      //
      HIVE_PARTITION_EXTRACTOR_CLASS_OPT_KEY -> classOf[MultiPartKeysValueExtractor].getName
    )
  }

}

object Demo4 {

  private val APP_NAME = classOf[Demo3].getClass.getSimpleName
  val insertData = Array[TemperatureBean](
    new TemperatureBean("4301",1,28.6,"2019-12-07 12:35:33"),
    new TemperatureBean("4312",0,31.4,"2019-12-07 12:25:03"),
    new TemperatureBean("4302",1,30.1,"2019-12-07 12:32:17"),
    new TemperatureBean("4305",3,31.5,"2019-12-07 12:33:11"),
    new TemperatureBean("4310",2,29.9,"2019-12-07 12:34:42")
  )
  val updateData = Array[TemperatureBean](
    new TemperatureBean("4310",2,30.4,"2019-12-07 12:35:42")// 设备ID为4310的传感器发生修改，温度值由29.9->30.4,时间由12:34:42->12:35:42
  )
  val deleteData = Array[TemperatureBean](
    new TemperatureBean("4310",2,30.4,"2019-12-07 12:35:42")// 设备ID为4310的传感器要被删除，必须与最新的数据保持一致（如果字段值不同时无法删除）
  )

  def main(args: Array[String]): Unit = {
    // 类似Hive中的DB（basePath的schema决定了最终要操作的文件系统，如果是file:则为LocalFS，如果是hdfs:则为HDFS）
    val basePath = "hdfs://node01:9820/apps/demo/hudi/hudi-hive-mor/"
    // Hive中的Table
    val tableName = "tbl_hudi_temperature_mor1"
    // 数据所在的路径
    val path = s"$basePath/$tableName"

    // 创建SparkConf
    val conf = new SparkConf()
      .set("spark.app.name", APP_NAME)
      .setAll(Configured.sparkConf().asScala)
    // 创建SparkSession
    val spark = SparkSession.builder()
      .config(conf)
      .getOrCreate()
    // 关闭日志
    spark.sparkContext.setLogLevel("DEBUG")
    // 导入隐式转换
    import spark.implicits._
    // 创建Demo4实例
    val demo = new Demo4("test_db", tableName,"deviceType","jdbc:hive2://node01:10000","root","123456")
    // 插入数据
//    demo.insert(spark.createDataFrame(insertData.toBuffer.asJava, classOf[TemperatureBean]),tableName, "deviceId", "deviceType", "cdt", path)
    // 修改数据
//    demo.update(spark.createDataFrame(updateData.toBuffer.asJava, classOf[TemperatureBean]),tableName, "deviceId", "deviceType", "cdt", path)
    // 删除数据
    demo.delete(spark.createDataFrame(deleteData.toBuffer.asJava, classOf[TemperatureBean]),tableName, "deviceId", "deviceType", "cdt", path)

    spark.stop()
  }

}
