package com.kingsoft.spark.metrics.beans.yarn;

/**
 * @Description
 * @Author: MengYao
 * @Created by: 2021-01-19 10:23:51
 */
public class Timeout {

    private String type;
    private String expiryTime;
    private int remainingTimeInSeconds;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setRemainingTimeInSeconds(int remainingTimeInSeconds) {
        this.remainingTimeInSeconds = remainingTimeInSeconds;
    }

    public int getRemainingTimeInSeconds() {
        return remainingTimeInSeconds;
    }

}