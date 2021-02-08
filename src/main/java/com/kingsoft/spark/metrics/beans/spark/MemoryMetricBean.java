package com.kingsoft.spark.metrics.beans.spark;

/**
 * @ClassName MemoryMetricBean
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-19 16:21:34
 * @Version V1.0
 */
public class MemoryMetricBean {

    private int usedOnHeapStorageMemory;
    private int usedOffHeapStorageMemory;
    private long totalOnHeapStorageMemory;
    private int totalOffHeapStorageMemory;

    public void setUsedOnHeapStorageMemory(int usedOnHeapStorageMemory) {
        this.usedOnHeapStorageMemory = usedOnHeapStorageMemory;
    }

    public int getUsedOnHeapStorageMemory() {
        return usedOnHeapStorageMemory;
    }

    public void setUsedOffHeapStorageMemory(int usedOffHeapStorageMemory) {
        this.usedOffHeapStorageMemory = usedOffHeapStorageMemory;
    }

    public int getUsedOffHeapStorageMemory() {
        return usedOffHeapStorageMemory;
    }

    public void setTotalOnHeapStorageMemory(long totalOnHeapStorageMemory) {
        this.totalOnHeapStorageMemory = totalOnHeapStorageMemory;
    }

    public long getTotalOnHeapStorageMemory() {
        return totalOnHeapStorageMemory;
    }

    public void setTotalOffHeapStorageMemory(int totalOffHeapStorageMemory) {
        this.totalOffHeapStorageMemory = totalOffHeapStorageMemory;
    }

    public int getTotalOffHeapStorageMemory() {
        return totalOffHeapStorageMemory;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"usedOnHeapStorageMemory\":")
                .append(usedOnHeapStorageMemory);
        sb.append(",\"usedOffHeapStorageMemory\":")
                .append(usedOffHeapStorageMemory);
        sb.append(",\"totalOnHeapStorageMemory\":")
                .append(totalOnHeapStorageMemory);
        sb.append(",\"totalOffHeapStorageMemory\":")
                .append(totalOffHeapStorageMemory);
        sb.append('}');
        return sb.toString();
    }
}