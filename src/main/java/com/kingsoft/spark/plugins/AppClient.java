package com.kingsoft.spark.plugins;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.yarn.api.ApplicationConstants;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationResponse;
import org.apache.hadoop.yarn.api.records.*;
import org.apache.hadoop.yarn.api.records.impl.pb.ResourcePBImpl;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.util.ConverterUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.io.IOException;
import java.util.*;

public class AppClient {

    private static Logger LOG = LoggerFactory.getLogger(AppClient.class);
    private static final String DEFAULT_CONFIG_NAME = "spark-defaults.conf";
    private static String appMasterClass = AppMaster.class.getName();
    private static final String appName = "";
    private Configuration hadoopConf;
    private SparkConf sparkConf;

    public AppClient() {
        // 初始化SparkConf
        sparkConf = new SparkConf()
                .loadFromSystemProperties(true)
                .setAll(Utils.getPropertiesFromFile(System.getenv("SPARK_HOME")+"/conf/"+DEFAULT_CONFIG_NAME));
        // 初始化Hadoop Configuration
        hadoopConf = new Configuration();
        for (Tuple2<String, String> tuple2 : sparkConf.getAll()) {
            hadoopConf.set(tuple2._1, tuple2._2);
        }
    }

    public static void main(String[] args) {
        AppClient client = new AppClient();
        try {
            client.run();
        } catch (Exception e) {
            LOG.error("client run exception , please check log file.", e);
        }
    }

    // 开始执行任务
    public void run() throws IOException, YarnException {
        // 创建用于和ResourceManager交互的YarnClient
        YarnClient yarnClient = YarnClient.createYarnClient();
        yarnClient.init(hadoopConf);
        // 启动yarnClient
        yarnClient.start();
        // 使用yarnClient创建应用
        YarnClientApplication app = yarnClient.createApplication();
        // 实例化appSubmissionCtx（它持有RM启动AM的所需的全部信息）
        ApplicationSubmissionContext appSubmissionCtx = app.getApplicationSubmissionContext();
        // RM向客户返回ApplicationId
        GetNewApplicationResponse newApplicationResponse = app.getNewApplicationResponse();
        // 设置application对象运行上下文
        appSubmissionCtx.setApplicationId(newApplicationResponse.getApplicationId());
        // 设置作业名称
        appSubmissionCtx.setApplicationName(appName);
        // 设置作业使用的队列
        appSubmissionCtx.setQueue("default");
        // 设置任务优先级，数字越大则优先级越高，默认为-1
        appSubmissionCtx.setPriority(Priority.newInstance(1));
        // 设置作业类型
        appSubmissionCtx.setApplicationType("SPARK");
        // 设置本作业的AM所需的资源信息
        appSubmissionCtx.setResource(new ResourcePBImpl() {{setMemory(1);setVirtualCores(1);}});
        // 设置运行完成后清除token
        appSubmissionCtx.setCancelTokensWhenComplete(true);
        // 设置作业最大失败重试的次数
        appSubmissionCtx.setMaxAppAttempts(4);
        // 设置作业在重试期间是否还能访问此Container
        appSubmissionCtx.setKeepContainersAcrossApplicationAttempts(true);
        // 设置作业资源请求信息
        appSubmissionCtx.setAMContainerResourceRequest(null);
        // 设置作业的标签
        appSubmissionCtx.setApplicationTags(new HashSet<String>() {{add("");}});

        // TODO 添加本地资源
        Map<String, LocalResource> localResources = new HashMap<>(1 << 4);
        FileSystem fs = FileSystem.get(hadoopConf);
        String appMasterJarPath = "yarn-application-demo-1.0-SNAPSHOT.jar";
        String appMasterJar = "D:\\Users\\Bigdata\\learning\\source\\yarn-application-demo\\target\\yarn-application-demo-1.0-SNAPSHOT.jar" ;
        ApplicationId appId = appSubmissionCtx.getApplicationId();
        addToLocalResources(fs,appMasterJar, appMasterJarPath, appId.toString(), localResources,null);

        // TODO 添加运行环境
        Map<String, String> env = new HashMap<>(1 << 4);
        // 任务的运行依赖jar包的准备
        StringBuilder classPathEnv = new StringBuilder(ApplicationConstants.Environment.CLASSPATH.$$())
                .append(ApplicationConstants.CLASS_PATH_SEPARATOR).append("./*");
        for (String c : hadoopConf.getStrings(
                YarnConfiguration.YARN_APPLICATION_CLASSPATH,
                YarnConfiguration.DEFAULT_YARN_CROSS_PLATFORM_APPLICATION_CLASSPATH)) {
            classPathEnv.append(ApplicationConstants.CLASS_PATH_SEPARATOR);
            classPathEnv.append(c.trim());
        }
        classPathEnv.append(ApplicationConstants.CLASS_PATH_SEPARATOR).append(
                "./log4j.properties");

        // add the runtime classpath needed for tests to work
        if (hadoopConf.getBoolean(YarnConfiguration.IS_MINI_YARN_CLUSTER, false)) {
            classPathEnv.append(':');
            classPathEnv.append(System.getProperty("java.class.path"));
        }
        env.put("CLASSPATH", classPathEnv.toString());

        // TODO 添加命令列表
        List<String> commands = new ArrayList<>(1 << 4);

        // 1. 需要将path下面的jar包上传至hdfs，然后其他节点从hdfs上下载下来
        commands.add(ApplicationConstants.Environment.JAVA_HOME.$$() + "/bin/java"+ " -Xmx200m -Xms200m -Xmn20m " + appMasterClass);

        ContainerLaunchContext amContainer = ContainerLaunchContext.newInstance(
                localResources, env, commands, null, null, null);
        // 准备amContainer的运行环境
        appSubmissionCtx.setAMContainerSpec(amContainer);

        // 设置UnmmanageAM，默认值是false，am默认是启动在节点上的container，如果设置成true，再配合其他设置可将这个am启动在指定的环境下方便调试
        //  appSubmissionCtx.setUnmanagedAM(false);
        // 任务完成时令牌是否销毁，默认值是true
        appSubmissionCtx.setCancelTokensWhenComplete(true);
        // 任务失败后最大重试次数，
        //  appSubmissionCtx.setMaxAppAttempts();

        // 对资源进行设置，正常是从用户输入的参数中解析出来设置进入
        int memory = 1024;
        int vCores = 2;
        appSubmissionCtx.setResource(Resource.newInstance(memory, vCores));
        // 设置任务类型
        appSubmissionCtx.setApplicationType("my-yarn-application");
        // 默认是false
        appSubmissionCtx.setKeepContainersAcrossApplicationAttempts(false);

        // 为应用程序设置标签
        Set<String> tags = new HashSet<>(1 << 2);
        tags.add("tag1");
        tags.add("tag2");
        appSubmissionCtx.setApplicationTags(tags);

        // 设置节点标签
        //  appSubmissionCtx.setNodeLabelExpression();

        // 设置applicationMaster的container运行资源请求
//        String hostName = "127.0.0.1";
//        int numContainers = 1;
//        ResourceRequest amRequest = ResourceRequest.newInstance(Priority.newInstance(10), hostName, Resource.newInstance(memory, vCores), numContainers);
//        appSubmissionCtx.setAMContainerResourceRequest(amRequest);

        // 应用失败重试时间间隔
        appSubmissionCtx.setAttemptFailuresValidityInterval(30 * 1000L);
        // 日志聚合上下文
        // appSubmissionCtx.setLogAggregationContext();

        // TODO 检查提交申请的资源上限，避免程序资源过载造成系统宕机

        // 最后提交开始正式运行设置好的任务
        yarnClient.submitApplication(appSubmissionCtx);
    }

    private void addToLocalResources(FileSystem fs, String fileSrcPath,
                                     String fileDstPath, String appId, Map<String, LocalResource> localResources,
                                     String resources) throws IOException {
        String suffix =
                appName + "/" + appId + "/" + fileDstPath;
        Path dst =
                new Path(fs.getHomeDirectory(), suffix);
        if (fileSrcPath == null) {
            FSDataOutputStream ostream = null;
            try {
                ostream = FileSystem
                        .create(fs, dst, new FsPermission((short) 0710));
                ostream.writeUTF(resources);
            } finally {
                IOUtils.closeQuietly(ostream);
            }
        } else {
            fs.copyFromLocalFile(new Path(fileSrcPath), dst);
        }
        FileStatus scFileStatus = fs.getFileStatus(dst);
        LocalResource scRsrc =
                LocalResource.newInstance(
                        ConverterUtils.getYarnUrlFromURI(dst.toUri()),
                        LocalResourceType.FILE, LocalResourceVisibility.APPLICATION,
                        scFileStatus.getLen(), scFileStatus.getModificationTime());
        localResources.put(fileDstPath, scRsrc);
    }
}