package com.kingsoft.spark.metrics.beans.spark;

/**
 * @ClassName ExecutorLogBean
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-19 16:21:34
 * @Version V1.0
 */
public class ExecutorLogBean {

    private String stdout;
    private String stderr;

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"stdout\":\"")
                .append(stdout).append('\"');
        sb.append(",\"stderr\":\"")
                .append(stderr).append('\"');
        sb.append('}');
        return sb.toString();
    }
}