package com.kingsoft.spark.plugins

import org.apache.commons.lang3.{StringUtils, SystemUtils}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

/**
 *
 * @ClassName WordCountApp
 * @Description
 * @Created by: MengYao
 * @Date: 2021-02-18 18:00:22
 * @Version V1.0
 */
object WordCountApp_ {

  private val APP_NAME: String = WordCountApp_.getClass.getSimpleName

  def main(args: Array[String]): Unit = {
    var _args = args.toBuffer.asInstanceOf[ArrayBuffer[String]]
    val conf = new SparkConf(false)
      .setAppName(APP_NAME)
    if (SystemUtils.IS_OS_WINDOWS) {
      System.setProperty("hadoop.home.dir", "D:/softs/developer/Apache/hadoop-3.1.0")
      conf.setMaster("local[2]")
      if (_args.length == 0) _args += "D:/softs/developer/Apache/hadoop-3.1.0/README.txt"
    }

    val sc = new SparkContext(conf)
    sc.setLogLevel("DEBUG")
    sc.addSparkListener(new MyListener())

    sc.textFile(_args(0))
      .persist(StorageLevel.NONE)
      .flatMap(_.split(","))
      .map(word => (word, 1L))
      .filter(cs => StringUtils.isNotEmpty(cs._1))
      .reduceByKey(_+_)
      .foreachPartition(iter => iter.foreach(t2 => println(t2._1.trim() + " = " + t2._2.toString().trim())))

    sc.stop()
  }


}
