package com.kingsoft.spark.plugins;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerStageCompleted;
import scala.Tuple2;

import java.util.Arrays;


public class WordCountApp {

    private static final String APP_NAME = WordCountApp.class.getSimpleName();


    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName(APP_NAME);

        JavaSparkContext jsc = new JavaSparkContext(conf);
        jsc.sc().addSparkListener(new SparkListener() {

        });

        jsc.textFile(args[0])
                .flatMap(line -> Arrays.asList(line.split(",")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1L))
                .reduceByKey(Long::sum)
                .foreach(System.out::println);

        jsc.stop();
    }

}
