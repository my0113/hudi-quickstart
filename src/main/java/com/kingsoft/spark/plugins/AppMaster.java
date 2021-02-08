package com.kingsoft.spark.plugins;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.net.NetUtils;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.ContainerStatus;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.api.records.NodeReport;
import org.apache.hadoop.yarn.client.api.async.AMRMClientAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AppMaster {

    private static Logger LOG = LoggerFactory.getLogger(AppClient.class);

    private AMRMClientAsync amRmClient ;
    private Configuration hadoopConf ;
    private int appMasterRpcPort = -1 ;
    private String appMasterTrackingUrl ;

    /**
     * 使用脚本运行appMaster之后主要运行main方法里面的内容
     *
     * @param args 执行参数
     */
    public static void main(String[] args) {
        try{
            AppMaster master = new AppMaster();
            master.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void run(){
        try{
            // 提交任务需要注册本身到ResourceManager上
            AMRMClientAsync.CallbackHandler allocListener = new RMCallBackHandler();
            amRmClient = AMRMClientAsync.createAMRMClientAsync(1000,allocListener);
            hadoopConf = new Configuration();
            amRmClient.init(hadoopConf);
            amRmClient.start();
            String hostName = NetUtils.getHostname();
            amRmClient.registerApplicationMaster(hostName,appMasterRpcPort,appMasterTrackingUrl);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
			System.out.println("hello yarn");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try{
            amRmClient.unregisterApplicationMaster(FinalApplicationStatus.SUCCEEDED,"任务运行成功!!!",appMasterTrackingUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class RMCallBackHandler implements AMRMClientAsync.CallbackHandler {

        /**
         * 当container完成之后调用的函数
         * @param statuses container状态
         */
        @Override
        public void onContainersCompleted(List<ContainerStatus> statuses) {

        }

        /**
         * 当container分配完成之后
         * @param containers 分配好的container
         */
        @Override
        public void onContainersAllocated(List<Container> containers) {

        }

        /**
         * 关闭请求回调
         */
        @Override
        public void onShutdownRequest() {

        }

        /**
         * 节点更新
         * @param updatedNodes 更新的节点
         */
        @Override
        public void onNodesUpdated(List<NodeReport> updatedNodes) {

        }

        /**
         * 获取进程
         * @return
         */
        @Override
        public float getProgress() {
            return 0;
        }

        /**
         * 发生错误之后
         * @param e 异常信息
         */
        @Override
        public void onError(Throwable e) {

        }
    }
}