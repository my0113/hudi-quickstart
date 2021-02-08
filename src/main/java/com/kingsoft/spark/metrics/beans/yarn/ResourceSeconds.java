package com.kingsoft.spark.metrics.beans.yarn;

import java.util.HashMap;

/**
 * @Description
 * @Author: MengYao
 * @Created by: 2021-01-19 10:23:51
 */
public class ResourceSeconds {

    private HashMap<String, String> entry;

    public void setEntry(HashMap<String, String> entry) {
        this.entry = entry;
    }

    public HashMap<String, String> getEntry() {
        return entry;
    }

}