package com.kingsoft.hudi;

import org.apache.hudi.QuickstartUtils;
import org.apache.hudi.common.model.HoodieRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringJoiner;

/**
 * @ClassName Test
 * @Description
 * @Created by MengYao
 * @Date 2021/1/24 21:38
 * @Version V1.0
 */
public class TemperatureBean {

    // 设备ID
    private String deviceId;
    // 设备类型
    private int deviceType;
    // 温度
    private double temperature;
    // 采集时间
    private long cdt;

    public TemperatureBean(String deviceId, int deviceType, double temperature, String cdt) {
        try {
            this.deviceId = deviceId;
            this.deviceType = deviceType;
            this.temperature = temperature;
            this.cdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cdt).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public long getCdt() {
        return cdt;
    }

    public void setCdt(long cdt) {
        this.cdt = cdt;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add("" + deviceId)
                .add("" + deviceType)
                .add("" + temperature)
                .add("" + cdt)
                .toString();
    }
}
