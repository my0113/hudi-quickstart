package com.kingsoft.spark.metrics;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingsoft.spark.metrics.beans.spark.ExecutorBean;
import com.kingsoft.spark.metrics.beans.spark.SparkAppBean;
import com.kingsoft.spark.metrics.beans.yarn.YarnAppBean;
import com.kingsoft.spark.metrics.beans.yarn.YarnRootBean;
import com.kingsoft.utils.HttpTool;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @ClassName ApplicationMetric
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-18 21:17:43
 * @Version V1.0
 */
public class ApplicationMetric {

    private static final String YARN_CTR_PATH = "/ws/v1/cluster/apps";
    private static final String SPARK_BASE_PATH = "/api/v1";
    private static final String SEPARATOR = "/";
    private Builder builder;


    public static void main(String[] args) throws Exception {

        ApplicationMetric applicationMetric = ApplicationMetric.builder()
                .withYws("http://10.69.73.228:8088")
                .withShs("http://10.69.73.228:18081")
                .build();
        System.out.println(applicationMetric.getSparkAppMetric("application_1609988336857_0021"));
        System.out.println(applicationMetric.getSparkAppExecutorMetric("application_1609988336857_0021"));
        System.out.println(applicationMetric.getYarnAppMetric("application_1609988336857_0021"));
    }

    private ApplicationMetric(Builder builder) {
        this.builder = builder;
    }

    /**
     * 从Spark History Server中获取作业运行摘要信息
     * @param appId
     */
    public SparkAppBean getSparkAppMetric(String appId) throws IOException {
        String url = builder.getShs()
                .concat(SPARK_BASE_PATH)
                .concat(APIHelper.spark_apps.getPath())
                .concat(SEPARATOR);
        String result = HttpTool.get(url.concat(appId));
        if (StringUtils.isNotEmpty(result)) {
            return new ObjectMapper().readValue(result, SparkAppBean.class);
        }
        return null;
    }

    public List<ExecutorBean> getSparkAppExecutorMetric(String appId) throws IOException {
        APIHelper api = APIHelper.spark_app_executors;
        String url = builder.getShs()
                .concat(SPARK_BASE_PATH)
                .concat(api.getPath());
        String result = HttpTool.get(url.replace(api.getParams().values().iterator().next(), appId));
        if (StringUtils.isNotEmpty(result)) {
            return new ObjectMapper().readValue(result, new TypeReference<List<ExecutorBean>>() {});
        }
        return null;
    }

    /**
     * 从YARN的REST服务中获取作业运行信息
     * @param jobId
     */
    public YarnAppBean getYarnAppMetric(String jobId) throws IOException {
        String url = builder.getYws()
                .concat(YARN_CTR_PATH)
                .concat(SEPARATOR);
        String result = HttpTool.get(url.concat(jobId));
        if (StringUtils.isNotEmpty(result)) {
            YarnRootBean yarnRootBean = new ObjectMapper().readValue(result, YarnRootBean.class);
            if (Objects.nonNull(yarnRootBean)) {
                return yarnRootBean.getApp();
            }
        }
        return null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        /** SparkHistoryServer **/
        private String shs;
        /** JobHistoryServer **/
        private String jhs;
        /** YARN ReourceManager WebUI **/
        private String yws;
        public String getShs() {
            return shs;
        }
        public Builder withShs(String shs) {
            this.shs = shs;
            return this;
        }
        public String getJhs() {
            return jhs;
        }
        public Builder withJhs(String jhs) {
            this.jhs = jhs;
            return this;
        }
        public String getYws() {
            return yws;
        }
        public Builder withYws(String yws) {
            this.yws = yws;
            return this;
        }
        public ApplicationMetric build() {
            return new ApplicationMetric(this);
        }
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"shs\":\"")
                    .append(shs).append('\"');
            sb.append(",\"jhs\":\"")
                    .append(jhs).append('\"');
            sb.append(",\"yws\":\"")
                    .append(yws).append('\"');
            sb.append('}');
            return sb.toString();
        }
    }

}
