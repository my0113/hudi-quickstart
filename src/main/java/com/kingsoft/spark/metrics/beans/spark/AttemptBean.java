package com.kingsoft.spark.metrics.beans.spark;

/**
 * @ClassName AttemptBean
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-19 15:49:47
 * @Version V1.0
 */
public class AttemptBean {

    private String attemptId;
    private String startTime;
    private String endTime;
    private String lastUpdated;
    private long duration;
    private String sparkUser;
    private boolean completed;
    private String appSparkVersion;
    private long endTimeEpoch;
    private long startTimeEpoch;
    private long lastUpdatedEpoch;

    public String getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public void setSparkUser(String sparkUser) {
        this.sparkUser = sparkUser;
    }

    public String getSparkUser() {
        return sparkUser;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setAppSparkVersion(String appSparkVersion) {
        this.appSparkVersion = appSparkVersion;
    }

    public String getAppSparkVersion() {
        return appSparkVersion;
    }

    public void setEndTimeEpoch(long endTimeEpoch) {
        this.endTimeEpoch = endTimeEpoch;
    }

    public long getEndTimeEpoch() {
        return endTimeEpoch;
    }

    public void setStartTimeEpoch(long startTimeEpoch) {
        this.startTimeEpoch = startTimeEpoch;
    }

    public long getStartTimeEpoch() {
        return startTimeEpoch;
    }

    public void setLastUpdatedEpoch(long lastUpdatedEpoch) {
        this.lastUpdatedEpoch = lastUpdatedEpoch;
    }

    public long getLastUpdatedEpoch() {
        return lastUpdatedEpoch;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"attemptId\":\"")
                .append(attemptId).append('\"');
        sb.append(",\"startTime\":\"")
                .append(startTime).append('\"');
        sb.append(",\"endTime\":\"")
                .append(endTime).append('\"');
        sb.append(",\"lastUpdated\":\"")
                .append(lastUpdated).append('\"');
        sb.append(",\"duration\":")
                .append(duration);
        sb.append(",\"sparkUser\":\"")
                .append(sparkUser).append('\"');
        sb.append(",\"completed\":")
                .append(completed);
        sb.append(",\"appSparkVersion\":\"")
                .append(appSparkVersion).append('\"');
        sb.append(",\"endTimeEpoch\":")
                .append(endTimeEpoch);
        sb.append(",\"startTimeEpoch\":")
                .append(startTimeEpoch);
        sb.append(",\"lastUpdatedEpoch\":")
                .append(lastUpdatedEpoch);
        sb.append('}');
        return sb.toString();
    }
}