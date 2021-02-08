package com.kingsoft.spark.plugins;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationResponse;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationSubmissionContext;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkHadoopUtil;
import org.apache.spark.internal.config.ConfigBuilder;
import org.apache.spark.util.Utils;
import scala.collection.JavaConverters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 在spark-default.properties中新增配置
 *      # 预先启动的am数量，默认0
 *      spark.yarn.prelauncher.applicationmaster.size=10
 *      # 预先启动的executor数量，默认0
 *      spark.yarn.prelauncher.executor.size=10
 *
 * @ClassName SparkRunner
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-19 15:49:47
 * @Version V1.0
 */
public class AmPoolService {

    private static final String PRE_LAUNCHER_APPLICATION_MASTER_SIZE = "spark.yarn.prelauncher.applicationmaster.size";
    private static final String PRE_LAUNCHER_EXECUTOR_SIZE = "spark.yarn.prelauncher.executor.size";

    private SparkConf sparkConf;
    private Configuration conf;
    private YarnConfiguration yarnConf;
    private YarnClient yarnClient;


    public static void main(String[] args) {
//        AmPoolService amPoolService = new AmPoolService();
        SparkConf sparkConf = new SparkConf().loadFromSystemProperties(true);
        System.out.println(Arrays.stream(sparkConf.getAll()).collect(Collectors.toList()));

    }

    private void init() {
        sparkConf = new SparkConf().loadFromSystemProperties(true);
        yarnClient = YarnClient.createYarnClient();
//        yarnClient.init(new YarnConfiguration(sparkConf));
        yarnClient.start();

    }

    private void createApp() throws Exception {
        YarnClientApplication app = yarnClient.createApplication();
        GetNewApplicationResponse appResponse = app.getNewApplicationResponse();
        ApplicationSubmissionContext appContext = app.getApplicationSubmissionContext();
        ApplicationId appId = appContext.getApplicationId();

        appContext.setKeepContainersAcrossApplicationAttempts(true);
        appContext.setApplicationName("");
    }

    private void allocationResource() throws Exception {
        Map<String, LocalResource> localResources = new HashMap<String, LocalResource>();
        FileSystem fs = FileSystem.get(conf);



    }

}
