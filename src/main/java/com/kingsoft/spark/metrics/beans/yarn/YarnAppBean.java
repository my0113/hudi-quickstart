package com.kingsoft.spark.metrics.beans.yarn;

import java.util.Date;

/**
 * @Description
 * @Author: MengYao
 * @Created by: 2021-01-19 10:23:51
 */
public class YarnAppBean {

    private String id;
    private String user;
    private String name;
    private String queue;
    private String state;
    private String finalStatus;
    private int progress;
    private String trackingUI;
    private String trackingUrl;
    private String diagnostics;
    private long clusterId;
    private String applicationType;
    private String applicationTags;
    private int priority;
    private long startedTime;
    private long finishedTime;
    private int elapsedTime;
    private String amContainerLogs;
    private String amHostHttpAddress;
    private String amRPCAddress;
    private String masterNodeId;
    private int allocatedMB;
    private int allocatedVCores;
    private int reservedMB;
    private int reservedVCores;
    private int runningContainers;
    private int memorySeconds;
    private int vcoreSeconds;
    private int queueUsagePercentage;
    private int clusterUsagePercentage;
    private ResourceSeconds resourceSecondsMap;
    private int preemptedResourceMB;
    private int preemptedResourceVCores;
    private int numNonAMContainerPreempted;
    private int numAMContainerPreempted;
    private int preemptedMemorySeconds;
    private int preemptedVcoreSeconds;
    private PreemptedResourceSeconds preemptedResourceSecondsMap;
    private String logAggregationStatus;
    private boolean unmanagedApplication;
    private String amNodeLabelExpression;
    private Timeouts timeouts;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getQueue() {
        return queue;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }

    public void setTrackingUI(String trackingUI) {
        this.trackingUI = trackingUI;
    }

    public String getTrackingUI() {
        return trackingUI;
    }

    public void setTrackingUrl(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public String getTrackingUrl() {
        return trackingUrl;
    }

    public void setDiagnostics(String diagnostics) {
        this.diagnostics = diagnostics;
    }

    public String getDiagnostics() {
        return diagnostics;
    }

    public void setClusterId(long clusterId) {
        this.clusterId = clusterId;
    }

    public long getClusterId() {
        return clusterId;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationTags(String applicationTags) {
        this.applicationTags = applicationTags;
    }

    public String getApplicationTags() {
        return applicationTags;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setStartedTime(long startedTime) {
        this.startedTime = startedTime;
    }

    public long getStartedTime() {
        return startedTime;
    }

    public void setFinishedTime(long finishedTime) {
        this.finishedTime = finishedTime;
    }

    public long getFinishedTime() {
        return finishedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setAmContainerLogs(String amContainerLogs) {
        this.amContainerLogs = amContainerLogs;
    }

    public String getAmContainerLogs() {
        return amContainerLogs;
    }

    public void setAmHostHttpAddress(String amHostHttpAddress) {
        this.amHostHttpAddress = amHostHttpAddress;
    }

    public String getAmHostHttpAddress() {
        return amHostHttpAddress;
    }

    public void setAmRPCAddress(String amRPCAddress) {
        this.amRPCAddress = amRPCAddress;
    }

    public String getAmRPCAddress() {
        return amRPCAddress;
    }

    public void setMasterNodeId(String masterNodeId) {
        this.masterNodeId = masterNodeId;
    }

    public String getMasterNodeId() {
        return masterNodeId;
    }

    public void setAllocatedMB(int allocatedMB) {
        this.allocatedMB = allocatedMB;
    }

    public int getAllocatedMB() {
        return allocatedMB;
    }

    public void setAllocatedVCores(int allocatedVCores) {
        this.allocatedVCores = allocatedVCores;
    }

    public int getAllocatedVCores() {
        return allocatedVCores;
    }

    public void setReservedMB(int reservedMB) {
        this.reservedMB = reservedMB;
    }

    public int getReservedMB() {
        return reservedMB;
    }

    public void setReservedVCores(int reservedVCores) {
        this.reservedVCores = reservedVCores;
    }

    public int getReservedVCores() {
        return reservedVCores;
    }

    public void setRunningContainers(int runningContainers) {
        this.runningContainers = runningContainers;
    }

    public int getRunningContainers() {
        return runningContainers;
    }

    public void setMemorySeconds(int memorySeconds) {
        this.memorySeconds = memorySeconds;
    }

    public int getMemorySeconds() {
        return memorySeconds;
    }

    public void setVcoreSeconds(int vcoreSeconds) {
        this.vcoreSeconds = vcoreSeconds;
    }

    public int getVcoreSeconds() {
        return vcoreSeconds;
    }

    public void setQueueUsagePercentage(int queueUsagePercentage) {
        this.queueUsagePercentage = queueUsagePercentage;
    }

    public int getQueueUsagePercentage() {
        return queueUsagePercentage;
    }

    public void setClusterUsagePercentage(int clusterUsagePercentage) {
        this.clusterUsagePercentage = clusterUsagePercentage;
    }

    public int getClusterUsagePercentage() {
        return clusterUsagePercentage;
    }

    public void setResourceSecondsMap(ResourceSeconds resourceSecondsMap) {
        this.resourceSecondsMap = resourceSecondsMap;
    }

    public ResourceSeconds getResourceSecondsMap() {
        return resourceSecondsMap;
    }

    public void setPreemptedResourceMB(int preemptedResourceMB) {
        this.preemptedResourceMB = preemptedResourceMB;
    }

    public int getPreemptedResourceMB() {
        return preemptedResourceMB;
    }

    public void setPreemptedResourceVCores(int preemptedResourceVCores) {
        this.preemptedResourceVCores = preemptedResourceVCores;
    }

    public int getPreemptedResourceVCores() {
        return preemptedResourceVCores;
    }

    public void setNumNonAMContainerPreempted(int numNonAMContainerPreempted) {
        this.numNonAMContainerPreempted = numNonAMContainerPreempted;
    }

    public int getNumNonAMContainerPreempted() {
        return numNonAMContainerPreempted;
    }

    public void setNumAMContainerPreempted(int numAMContainerPreempted) {
        this.numAMContainerPreempted = numAMContainerPreempted;
    }

    public int getNumAMContainerPreempted() {
        return numAMContainerPreempted;
    }

    public void setPreemptedMemorySeconds(int preemptedMemorySeconds) {
        this.preemptedMemorySeconds = preemptedMemorySeconds;
    }

    public int getPreemptedMemorySeconds() {
        return preemptedMemorySeconds;
    }

    public void setPreemptedVcoreSeconds(int preemptedVcoreSeconds) {
        this.preemptedVcoreSeconds = preemptedVcoreSeconds;
    }

    public int getPreemptedVcoreSeconds() {
        return preemptedVcoreSeconds;
    }

    public void setPreemptedResourceSecondsMap(PreemptedResourceSeconds preemptedResourceSecondsMap) {
        this.preemptedResourceSecondsMap = preemptedResourceSecondsMap;
    }

    public PreemptedResourceSeconds getPreemptedResourceSecondsMap() {
        return preemptedResourceSecondsMap;
    }

    public void setLogAggregationStatus(String logAggregationStatus) {
        this.logAggregationStatus = logAggregationStatus;
    }

    public String getLogAggregationStatus() {
        return logAggregationStatus;
    }

    public void setUnmanagedApplication(boolean unmanagedApplication) {
        this.unmanagedApplication = unmanagedApplication;
    }

    public boolean getUnmanagedApplication() {
        return unmanagedApplication;
    }

    public void setAmNodeLabelExpression(String amNodeLabelExpression) {
        this.amNodeLabelExpression = amNodeLabelExpression;
    }

    public String getAmNodeLabelExpression() {
        return amNodeLabelExpression;
    }

    public void setTimeouts(Timeouts timeouts) {
        this.timeouts = timeouts;
    }

    public Timeouts getTimeouts() {
        return timeouts;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"user\":\"")
                .append(user).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"queue\":\"")
                .append(queue).append('\"');
        sb.append(",\"state\":\"")
                .append(state).append('\"');
        sb.append(",\"finalStatus\":\"")
                .append(finalStatus).append('\"');
        sb.append(",\"progress\":")
                .append(progress);
        sb.append(",\"trackingUI\":\"")
                .append(trackingUI).append('\"');
        sb.append(",\"trackingUrl\":\"")
                .append(trackingUrl).append('\"');
        sb.append(",\"diagnostics\":\"")
                .append(diagnostics).append('\"');
        sb.append(",\"clusterId\":")
                .append(clusterId);
        sb.append(",\"applicationType\":\"")
                .append(applicationType).append('\"');
        sb.append(",\"applicationTags\":\"")
                .append(applicationTags).append('\"');
        sb.append(",\"priority\":")
                .append(priority);
        sb.append(",\"startedTime\":")
                .append(startedTime);
        sb.append(",\"finishedTime\":")
                .append(finishedTime);
        sb.append(",\"elapsedTime\":")
                .append(elapsedTime);
        sb.append(",\"amContainerLogs\":\"")
                .append(amContainerLogs).append('\"');
        sb.append(",\"amHostHttpAddress\":\"")
                .append(amHostHttpAddress).append('\"');
        sb.append(",\"amRPCAddress\":\"")
                .append(amRPCAddress).append('\"');
        sb.append(",\"masterNodeId\":\"")
                .append(masterNodeId).append('\"');
        sb.append(",\"allocatedMB\":")
                .append(allocatedMB);
        sb.append(",\"allocatedVCores\":")
                .append(allocatedVCores);
        sb.append(",\"reservedMB\":")
                .append(reservedMB);
        sb.append(",\"reservedVCores\":")
                .append(reservedVCores);
        sb.append(",\"runningContainers\":")
                .append(runningContainers);
        sb.append(",\"memorySeconds\":")
                .append(memorySeconds);
        sb.append(",\"vcoreSeconds\":")
                .append(vcoreSeconds);
        sb.append(",\"queueUsagePercentage\":")
                .append(queueUsagePercentage);
        sb.append(",\"clusterUsagePercentage\":")
                .append(clusterUsagePercentage);
        sb.append(",\"resourceSecondsMap\":")
                .append(resourceSecondsMap);
        sb.append(",\"preemptedResourceMB\":")
                .append(preemptedResourceMB);
        sb.append(",\"preemptedResourceVCores\":")
                .append(preemptedResourceVCores);
        sb.append(",\"numNonAMContainerPreempted\":")
                .append(numNonAMContainerPreempted);
        sb.append(",\"numAMContainerPreempted\":")
                .append(numAMContainerPreempted);
        sb.append(",\"preemptedMemorySeconds\":")
                .append(preemptedMemorySeconds);
        sb.append(",\"preemptedVcoreSeconds\":")
                .append(preemptedVcoreSeconds);
        sb.append(",\"preemptedResourceSecondsMap\":")
                .append(preemptedResourceSecondsMap);
        sb.append(",\"logAggregationStatus\":\"")
                .append(logAggregationStatus).append('\"');
        sb.append(",\"unmanagedApplication\":")
                .append(unmanagedApplication);
        sb.append(",\"amNodeLabelExpression\":\"")
                .append(amNodeLabelExpression).append('\"');
        sb.append(",\"timeouts\":")
                .append(timeouts);
        sb.append('}');
        return sb.toString();
    }
}