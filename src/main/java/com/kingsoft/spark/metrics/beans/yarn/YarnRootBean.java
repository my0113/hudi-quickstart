package com.kingsoft.spark.metrics.beans.yarn;

/**
 * @ClassName YarnRootBean
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-19 16:38:09
 * @Version V1.0
 */
public class YarnRootBean {

    private YarnAppBean app;

    public YarnAppBean getApp() {
        return app;
    }

    public void setApp(YarnAppBean app) {
        this.app = app;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"app\":")
                .append(app);
        sb.append('}');
        return sb.toString();
    }
}
