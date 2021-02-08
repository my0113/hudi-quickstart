package com.kingsoft.spark.metrics.beans.spark;

import java.util.List;

/**
 * @ClassName AppBean
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-19 15:49:47
 * @Version V1.0
 */
public class SparkAppBean {

    /** application_1609988336857_0021 **/
    private String id;
    /** SparkPi **/
    private String name;
    /**  **/
    private List<AttemptBean> attempts;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAttempts(List<AttemptBean> attempts) {
        this.attempts = attempts;
    }

    public List<AttemptBean> getAttempts() {
        return attempts;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"attempts\":")
                .append(attempts);
        sb.append('}');
        return sb.toString();
    }
}
