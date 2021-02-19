
## 1.1 配置oozie-site.xml
```shell
vim $OOZIE_HOME/conf/oozie-site.xml
```
**新增如下内容（改完要重启oozie的tomcat）**
```xml
<!-- 配置Oozie的时区为东八区 -->
<property>
    <name>oozie.processing.timezone</name>
    <value>GMT+0800</value>
</property>
<!-- 关闭频率检查 -->
<property>
    <name>oozie.service.coord.check.maximum.frequency</name>
    <value>false</value>
</property>
```
## 1.2 修改oozie-console.js
```shell
vim $OOZIE_HOME/oozie-server/webapps/oozie/oozie-console.js
```
**修改为如下**
```javascript
function getTimeZone() {
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    return Ext.state.Manager.get("TimezoneId","GMT+0800");
}
```
## 1.3 创建demo1案例
### 1.3.1 简介
    workflow.xml（不可改名，必须位于HDFS中）是执行具体任务的流程定义
    coordinator.xml（不可改名，必须位于HDFS中）是负责定时提交一个或多个workflow.xml
    job.properties（位于本地，workflow和coordinator所需的配置参数）
### 1.3.2 编写workflow.xml
    src/main/java/com/kingsoft/oozie/demo1/workflow.xml
### 1.3.3 编写coordinator.xml
    src/main/java/com/kingsoft/oozie/demo1/coordinator.xml
### 1.3.4 编写job.properties
    src/main/java/com/kingsoft/oozie/demo1/job.properties
### 1.3.5 执行定时任务
```shell
bin/oozie job --config job.properties -run -oozie http://node01:11000/oozie
```
### 1.3.6 查看定时任务执行情况
**在浏览器中访问** 
```shell
http://node01:11000/oozie
```