package com.kingsoft.spark.metrics.beans.yarn;

import java.util.List;

/**
 * @Description
 * @Author: MengYao
 * @Created by: 2021-01-19 10:23:51
 */
public class Timeouts {

    private List<Timeout> timeout;

    public void setTimeout(List<Timeout> timeout) {
        this.timeout = timeout;
    }

    public List<Timeout> getTimeout() {
        return timeout;
    }

}