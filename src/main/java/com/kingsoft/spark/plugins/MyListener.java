package com.kingsoft.spark.plugins;

import org.apache.spark.scheduler.*;
import org.apache.spark.scheduler.cluster.ExecutorInfo;
import org.apache.spark.storage.BlockManagerId;
import scala.collection.Map;
//import scala.reflect.runtime.ReflectionUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;


/**
 * @ClassName MyListener
 * @Description
 * @Created by: MengYao
 * @Date: 2021-02-08 18:21:39
 * @Version V1.0
 */
public class MyListener extends SparkListener {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    public MyListener() {
        super();
    }

    @Override
    public void onStageCompleted(SparkListenerStageCompleted stageCompleted) {
        try {
            System.out.println("==== onStageCompleted ====");
            System.out.println(extract(stageCompleted.stageInfo()));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStageSubmitted(SparkListenerStageSubmitted stageSubmitted) {
        try {
            System.out.println("==== onStageSubmitted ====");
            System.out.println(extract(stageSubmitted.stageInfo()));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskStart(SparkListenerTaskStart taskStart) {
        try {
            System.out.println("==== onTaskStart ====");
            System.out.println(extract(taskStart.taskInfo()));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskGettingResult(SparkListenerTaskGettingResult taskGettingResult) {
        try {
            System.out.println("==== onTaskGettingResult ====");
            System.out.println(extract(taskGettingResult.taskInfo()));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskEnd(SparkListenerTaskEnd taskEnd) {
        try {
            System.out.println("==== onTaskEnd ====");
            System.out.println(extract(taskEnd.taskInfo()));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onJobStart(SparkListenerJobStart jobStart) {
        try {
            System.out.println("==== onJobStart ====");
            System.out.println(jobStart.stageInfos().mkString("## "));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onJobEnd(SparkListenerJobEnd jobEnd) {
        try {
            System.out.println("==== onJobEnd ====");
            int jobId = jobEnd.jobId();
            long time = jobEnd.time();
            JobResult jobResult = jobEnd.jobResult();
            System.out.println("==== JobId="+jobId+", Time="+sdf.format(new Date(time))+", JobResult="+ jobResult);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnvironmentUpdate(SparkListenerEnvironmentUpdate environmentUpdate) {
        try {
            System.out.println("==== onEnvironmentUpdate ====");
            System.out.println(extract(environmentUpdate.environmentDetails()));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBlockManagerAdded(SparkListenerBlockManagerAdded blockManagerAdded) {
        try {
            System.out.println("==== onBlockManagerAdded ====");
            long time = blockManagerAdded.time();
            BlockManagerId blockManagerId = blockManagerAdded.blockManagerId();
            long maxMem =blockManagerAdded.maxMem();
            Object maxOnHeapMem = blockManagerAdded.maxOnHeapMem().get();
            Object maxOffHeapMem =blockManagerAdded.maxOffHeapMem().get();
            System.out.println("==== Time="+sdf.format(new Date(time))+", MaxMem="+maxMem+", maxOnHeapMem="+maxOnHeapMem+", maxOffHeapMem="+maxOffHeapMem+", blockManagerId="+ blockManagerId);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBlockManagerRemoved(SparkListenerBlockManagerRemoved blockManagerRemoved) {
        try {
            System.out.println("==== onEnvironmentUpdate ====");
            long time = blockManagerRemoved.time();
            BlockManagerId blockManagerId = blockManagerRemoved.blockManagerId();
            System.out.println("==== Time="+sdf.format(new Date(time))+", BlockManagerId="+ blockManagerId);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUnpersistRDD(SparkListenerUnpersistRDD unpersistRDD) {
        try {
            System.out.println("==== onUnpersistRDD ====");
            System.out.println("==== unpersistRDD="+ extract(unpersistRDD));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApplicationStart(SparkListenerApplicationStart applicationStart) {
        try {
            System.out.println("==== onApplicationStart ====");
            String appName = applicationStart.appName();
            String appId = applicationStart.appId().get();
            long time = applicationStart.time();
            String sparkUser = applicationStart.sparkUser();
            String appAttemptId = applicationStart.appAttemptId().get();
            Map<String, String> driverLogs = applicationStart.driverLogs().get();
            System.out.println("==== AppId="+appId+", AppName="+appName+", Time="+sdf.format(new Date(time))+", SparkUser="+sparkUser+", AppAttemptId="+appAttemptId+", DriverLogs="+driverLogs);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApplicationEnd(SparkListenerApplicationEnd applicationEnd) {
        try {
            System.out.println("==== onApplicationEnd ====");
            System.out.println(sdf.format(new Date(applicationEnd.time())));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onExecutorMetricsUpdate(SparkListenerExecutorMetricsUpdate executorMetricsUpdate) {
        try {
            System.out.println("==== onExecutorMetricsUpdate ====");
            System.out.println(extract(executorMetricsUpdate));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onExecutorAdded(SparkListenerExecutorAdded executorAdded) {
        try {
            System.out.println("==== onExecutorAdded ====");
            String executorId = executorAdded.executorId();
            long time = executorAdded.time();
            ExecutorInfo executorInfo = executorAdded.executorInfo();
            System.out.println("==== ExecutorId="+executorId+", Time="+sdf.format(new Date(time))+", ExecutorInfo="+ executorInfo);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onExecutorRemoved(SparkListenerExecutorRemoved executorRemoved) {
        try {
            System.out.println("==== onExecutorRemoved ====");
            System.out.println(extract(executorRemoved));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onExecutorBlacklisted(SparkListenerExecutorBlacklisted executorBlacklisted) {
        try {
            System.out.println("==== onExecutorBlacklisted ====");
            System.out.println(extract(executorBlacklisted));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onExecutorBlacklistedForStage(SparkListenerExecutorBlacklistedForStage executorBlacklistedForStage) {
        try {
            System.out.println("==== onExecutorBlacklistedForStage ====");
            System.out.println(extract(executorBlacklistedForStage));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNodeBlacklistedForStage(SparkListenerNodeBlacklistedForStage nodeBlacklistedForStage) {
        try {
            System.out.println("==== onNodeBlacklistedForStage ====");
            System.out.println(extract(nodeBlacklistedForStage));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onExecutorUnblacklisted(SparkListenerExecutorUnblacklisted executorUnblacklisted) {
        try {
            System.out.println("==== onExecutorUnblacklisted ====");
            System.out.println(extract(executorUnblacklisted));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNodeBlacklisted(SparkListenerNodeBlacklisted nodeBlacklisted) {
        try {
            System.out.println("==== onNodeBlacklisted ====");
            System.out.println(extract(nodeBlacklisted));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNodeUnblacklisted(SparkListenerNodeUnblacklisted nodeUnblacklisted) {
        try {
            System.out.println("==== onNodeUnblacklisted ====");
            System.out.println(extract(nodeUnblacklisted));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBlockUpdated(SparkListenerBlockUpdated blockUpdated) {
        try {
            System.out.println("==== onBlockUpdated ====");
            System.out.println(extract(blockUpdated.blockUpdatedInfo()));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSpeculativeTaskSubmitted(SparkListenerSpeculativeTaskSubmitted speculativeTask) {
        try {
            System.out.println("==== onSpeculativeTaskSubmitted ====");
            System.out.println(extract(speculativeTask));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOtherEvent(SparkListenerEvent event) {
        try {
            System.out.println("==== onOtherEvent ====");
            System.out.println(extract(event));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extract(Object obj) {
        return extract(obj, 1);
    }

    private String extract(Object obj, int maxDeep) {
        if (Objects.nonNull(obj)) {
            StringBuilder builder = new StringBuilder();
            try {
                Field[] fields = obj.getClass().getDeclaredFields();
                for(Field field : fields) {
                    String fieldName = field.getName();
                    if (fieldName.equals("serialVersionUID")) continue;
                    field.setAccessible(true);
                    String typeName = field.getType().getSimpleName().toLowerCase();
                    Object fieldValue = field.get(obj);
                    if (typeName.matches("(byte|short|int|float|double|long|char|string|boolean)")) {
                        builder.append(fieldName +"="+ fieldValue +", ");
                    } else {
                        maxDeep--;
                        if (maxDeep>=0) {
                            builder.append(extract(fieldValue, maxDeep)+",");
                        }
                    }
                }
                int length = builder.length();
                if (length>0) {
                    builder.setLength(length -1);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return builder.toString();
        }
        return null;
    }

}
