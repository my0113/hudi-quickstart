package com.kingsoft.spark.plugins;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

import java.util.Arrays;


public class WordCountApp {

    private static final String APP_NAME = WordCountApp.class.getSimpleName();


    public static void main(String[] args) throws Exception {
        SparkConf conf = new SparkConf(false)
                .setAppName(APP_NAME);
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("hadoop.home.dir", "D:/softs/developer/Apache/hadoop-3.1.0");
            conf.setMaster("local[2]");
            if (args.length==0) {
                args = new String[]{"D:/softs/developer/Apache/hadoop-3.1.0/README.txt"};
            }
        }

        JavaSparkContext jsc = new JavaSparkContext(conf);
        jsc.setLogLevel("DEBUG");
        jsc.sc().addSparkListener(new MyListener());

        jsc.textFile(args[0])
                .persist(StorageLevel.NONE())
                .flatMap(line -> Arrays.asList(line.split(",")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1L))
                .filter(cs -> StringUtils.isNotEmpty(cs._1))
                .reduceByKey(Long::sum)
                .foreachPartition(iter-> iter.forEachRemaining(t2->System.out.println(t2._1.trim()+" = "+t2._2.toString().trim())));

        jsc.stop();
    }

}
