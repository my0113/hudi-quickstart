package com.kingsoft.spark.metrics.beans.spark;

/**
 * @ClassName ExecutorBean
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-19 15:49:47
 * @Version V1.0
 */
public class ExecutorBean {

    private String id;
    private String hostPort;
    private boolean isActive;
    private int rddBlocks;
    private int memoryUsed;
    private int diskUsed;
    private int totalCores;
    private int maxTasks;
    private int activeTasks;
    private int failedTasks;
    private int completedTasks;
    private int totalTasks;
    private int totalDuration;
    private int totalGCTime;
    private int totalInputBytes;
    private int totalShuffleRead;
    private int totalShuffleWrite;
    private boolean isBlacklisted;
    private long maxMemory;
    private String addTime;
    private ExecutorLogBean executorLogs;
    private MemoryMetricBean memoryMetrics;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setRddBlocks(int rddBlocks) {
        this.rddBlocks = rddBlocks;
    }

    public int getRddBlocks() {
        return rddBlocks;
    }

    public void setMemoryUsed(int memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public int getMemoryUsed() {
        return memoryUsed;
    }

    public void setDiskUsed(int diskUsed) {
        this.diskUsed = diskUsed;
    }

    public int getDiskUsed() {
        return diskUsed;
    }

    public void setTotalCores(int totalCores) {
        this.totalCores = totalCores;
    }

    public int getTotalCores() {
        return totalCores;
    }

    public void setMaxTasks(int maxTasks) {
        this.maxTasks = maxTasks;
    }

    public int getMaxTasks() {
        return maxTasks;
    }

    public void setActiveTasks(int activeTasks) {
        this.activeTasks = activeTasks;
    }

    public int getActiveTasks() {
        return activeTasks;
    }

    public void setFailedTasks(int failedTasks) {
        this.failedTasks = failedTasks;
    }

    public int getFailedTasks() {
        return failedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalGCTime(int totalGCTime) {
        this.totalGCTime = totalGCTime;
    }

    public int getTotalGCTime() {
        return totalGCTime;
    }

    public void setTotalInputBytes(int totalInputBytes) {
        this.totalInputBytes = totalInputBytes;
    }

    public int getTotalInputBytes() {
        return totalInputBytes;
    }

    public void setTotalShuffleRead(int totalShuffleRead) {
        this.totalShuffleRead = totalShuffleRead;
    }

    public int getTotalShuffleRead() {
        return totalShuffleRead;
    }

    public void setTotalShuffleWrite(int totalShuffleWrite) {
        this.totalShuffleWrite = totalShuffleWrite;
    }

    public int getTotalShuffleWrite() {
        return totalShuffleWrite;
    }

    public void setIsBlacklisted(boolean isBlacklisted) {
        this.isBlacklisted = isBlacklisted;
    }

    public boolean getIsBlacklisted() {
        return isBlacklisted;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setExecutorLogs(ExecutorLogBean executorLogs) {
        this.executorLogs = executorLogs;
    }

    public ExecutorLogBean getExecutorLogs() {
        return executorLogs;
    }

    public void setMemoryMetrics(MemoryMetricBean memoryMetrics) {
        this.memoryMetrics = memoryMetrics;
    }

    public MemoryMetricBean getMemoryMetrics() {
        return memoryMetrics;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"hostPort\":\"")
                .append(hostPort).append('\"');
        sb.append(",\"isActive\":")
                .append(isActive);
        sb.append(",\"rddBlocks\":")
                .append(rddBlocks);
        sb.append(",\"memoryUsed\":")
                .append(memoryUsed);
        sb.append(",\"diskUsed\":")
                .append(diskUsed);
        sb.append(",\"totalCores\":")
                .append(totalCores);
        sb.append(",\"maxTasks\":")
                .append(maxTasks);
        sb.append(",\"activeTasks\":")
                .append(activeTasks);
        sb.append(",\"failedTasks\":")
                .append(failedTasks);
        sb.append(",\"completedTasks\":")
                .append(completedTasks);
        sb.append(",\"totalTasks\":")
                .append(totalTasks);
        sb.append(",\"totalDuration\":")
                .append(totalDuration);
        sb.append(",\"totalGCTime\":")
                .append(totalGCTime);
        sb.append(",\"totalInputBytes\":")
                .append(totalInputBytes);
        sb.append(",\"totalShuffleRead\":")
                .append(totalShuffleRead);
        sb.append(",\"totalShuffleWrite\":")
                .append(totalShuffleWrite);
        sb.append(",\"isBlacklisted\":")
                .append(isBlacklisted);
        sb.append(",\"maxMemory\":")
                .append(maxMemory);
        sb.append(",\"addTime\":\"")
                .append(addTime).append('\"');
        sb.append(",\"executorLogs\":")
                .append(executorLogs);
        sb.append(",\"memoryMetrics\":")
                .append(memoryMetrics);
        sb.append('}');
        return sb.toString();
    }
}