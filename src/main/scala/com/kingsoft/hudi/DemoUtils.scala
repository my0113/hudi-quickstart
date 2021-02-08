package com.kingsoft.hudi

import org.apache.spark.sql.{DataFrame, SparkSession, functions}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}

import java.sql.Date
import java.sql.Timestamp

object DemoUtils {

  def schemaExtractor(caz: Class[_]): StructType = {
    val fields = caz.getDeclaredFields.map(field => {
      StructField(field.getName, field.getType.getSimpleName.toLowerCase match {
        case "byte" => DataTypes.ByteType
        case "short" => DataTypes.ShortType
        case "int" => DataTypes.IntegerType
        case "double" => DataTypes.DoubleType
        case "float" => DataTypes.FloatType
        case "long" => DataTypes.LongType
        case "boolean" => DataTypes.BooleanType
        // java.sql.Date
        case "date" => DataTypes.DateType
        // java.sql.Timestamp
        case "timestamp" => DataTypes.TimestampType
        case _ => DataTypes.StringType
      }, true)
    })
    StructType(fields)
  }

  /**
   * 新增列和列值到DataFrame
   * @param df
   * @param colName
   * @param colValue
   * @return
   */
  def addColumn(df: DataFrame, colName: String, colValue: Any): DataFrame = df.withColumn(colName, functions.lit(colValue))

  /**
   * 测试输出
   * @param df
   * @param numRows
   */
  def query(df: DataFrame, numRows: Int = Int.MaxValue): Unit = {
    df.show(numRows, false)
  }

  case class A(a:Byte,b:Short,c:Int,d:Double,e:Float,f:Long,g:Boolean,h:Date,i:Timestamp,j:String) {
    def getA = a
    def getB = b
    def getC = c
    def getD = d
    def getE = e
    def getF = f
    def getG = g
    def getH = h
    def getI = i
    def getJ = j
  }


  def main(args: Array[String]): Unit = {
    val data = A(1.byteValue, 2.shortValue, 3.intValue, 4.doubleValue, 5.floatValue, 6.longValue, true, new Date(System.currentTimeMillis), new Timestamp(System.currentTimeMillis), "hello")
    println(schemaExtractor(classOf[A]).mkString("\n"))
    val spark = SparkSession.builder().appName("A").master("local[*]").getOrCreate()
    import scala.collection.JavaConverters._
    spark.createDataFrame(Array[A](data).toBuffer.asJava, classOf[A]).show(false)
    spark.close
  }

}