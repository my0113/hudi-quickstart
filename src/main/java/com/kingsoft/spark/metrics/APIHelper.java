package com.kingsoft.spark.metrics;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName API
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-19 15:20:21
 * @Version V1.0
 */
public enum APIHelper {

    spark_apps("/applications",new HashMap<String, String>() {{
        put("status", "[completed|running]");
        put("minDate", "[date]");//2015-02-10 or 2015-02-03T16:42:40.000GMT
        put("maxDate", "[date]");
        put("minEndDate", "[date]");
        put("maxEndDate", "[date]");
        put("limit", "100");
    }}),
    spark_app_allexecutors("/applications/[app-id]/allexecutors", null),
    spark_app_executors("/applications/[app-id]/executors", new HashMap<String, String>() {{
        put("", "[app-id]");
    }}),
    spark_app_environment("/applications/[app-id]/environment", null),
    spark_version("/version",null),
    ;

    /** MapReduce/Spark/Flink/Hive/Tez **/
    private String path;
    private Map<String, String> params;

    APIHelper(String path, Map<String, String> params) {
        this.path = path;
        this.params = params;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
