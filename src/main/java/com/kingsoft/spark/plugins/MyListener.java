package com.kingsoft.spark.plugins;

import org.apache.spark.scheduler.*;

/**
 * @ClassName MyListener
 * @Description
 * @Created by: MengYao
 * @Date: 2021-02-08 18:21:39
 * @Version V1.0
 */
public class MyListener extends SparkListener {

    public MyListener() {
        super();
    }

    @Override
    public void onStageCompleted(SparkListenerStageCompleted stageCompleted) {
        super.onStageCompleted(stageCompleted);
    }

    @Override
    public void onStageSubmitted(SparkListenerStageSubmitted stageSubmitted) {
        super.onStageSubmitted(stageSubmitted);
    }

    @Override
    public void onTaskStart(SparkListenerTaskStart taskStart) {
        super.onTaskStart(taskStart);
    }

    @Override
    public void onTaskGettingResult(SparkListenerTaskGettingResult taskGettingResult) {
        super.onTaskGettingResult(taskGettingResult);
    }

    @Override
    public void onTaskEnd(SparkListenerTaskEnd taskEnd) {
        super.onTaskEnd(taskEnd);
    }

    @Override
    public void onJobStart(SparkListenerJobStart jobStart) {
        super.onJobStart(jobStart);
    }

    @Override
    public void onJobEnd(SparkListenerJobEnd jobEnd) {
        super.onJobEnd(jobEnd);
    }

    @Override
    public void onEnvironmentUpdate(SparkListenerEnvironmentUpdate environmentUpdate) {
        super.onEnvironmentUpdate(environmentUpdate);
    }

    @Override
    public void onBlockManagerAdded(SparkListenerBlockManagerAdded blockManagerAdded) {
        super.onBlockManagerAdded(blockManagerAdded);
    }

    @Override
    public void onBlockManagerRemoved(SparkListenerBlockManagerRemoved blockManagerRemoved) {
        super.onBlockManagerRemoved(blockManagerRemoved);
    }

    @Override
    public void onUnpersistRDD(SparkListenerUnpersistRDD unpersistRDD) {
        super.onUnpersistRDD(unpersistRDD);
    }

    @Override
    public void onApplicationStart(SparkListenerApplicationStart applicationStart) {
        super.onApplicationStart(applicationStart);
    }

    @Override
    public void onApplicationEnd(SparkListenerApplicationEnd applicationEnd) {
        super.onApplicationEnd(applicationEnd);
    }

    @Override
    public void onExecutorMetricsUpdate(SparkListenerExecutorMetricsUpdate executorMetricsUpdate) {
        super.onExecutorMetricsUpdate(executorMetricsUpdate);
    }

    @Override
    public void onExecutorAdded(SparkListenerExecutorAdded executorAdded) {
        super.onExecutorAdded(executorAdded);
    }

    @Override
    public void onExecutorRemoved(SparkListenerExecutorRemoved executorRemoved) {
        super.onExecutorRemoved(executorRemoved);
    }

    @Override
    public void onExecutorBlacklisted(SparkListenerExecutorBlacklisted executorBlacklisted) {
        super.onExecutorBlacklisted(executorBlacklisted);
    }

    @Override
    public void onExecutorBlacklistedForStage(SparkListenerExecutorBlacklistedForStage executorBlacklistedForStage) {
        super.onExecutorBlacklistedForStage(executorBlacklistedForStage);
    }

    @Override
    public void onNodeBlacklistedForStage(SparkListenerNodeBlacklistedForStage nodeBlacklistedForStage) {
        super.onNodeBlacklistedForStage(nodeBlacklistedForStage);
    }

    @Override
    public void onExecutorUnblacklisted(SparkListenerExecutorUnblacklisted executorUnblacklisted) {
        super.onExecutorUnblacklisted(executorUnblacklisted);
    }

    @Override
    public void onNodeBlacklisted(SparkListenerNodeBlacklisted nodeBlacklisted) {
        super.onNodeBlacklisted(nodeBlacklisted);
    }

    @Override
    public void onNodeUnblacklisted(SparkListenerNodeUnblacklisted nodeUnblacklisted) {
        super.onNodeUnblacklisted(nodeUnblacklisted);
    }

    @Override
    public void onBlockUpdated(SparkListenerBlockUpdated blockUpdated) {
        super.onBlockUpdated(blockUpdated);
    }

    @Override
    public void onSpeculativeTaskSubmitted(SparkListenerSpeculativeTaskSubmitted speculativeTask) {
        super.onSpeculativeTaskSubmitted(speculativeTask);
    }

    @Override
    public void onOtherEvent(SparkListenerEvent event) {
        super.onOtherEvent(event);
    }

}
