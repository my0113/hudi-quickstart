package com.kingsoft.hudi;

import com.kingsoft.Configured;
import org.apache.hudi.client.HoodieWriteClient;
import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.OverwriteWithLatestAvroPayload;
import org.apache.hudi.config.HoodieIndexConfig;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.index.HoodieIndex;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.collection.JavaConverters;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * WriteClient模式是直接使用RDD级别api进行Hudi编程
 *      Application需要使用HoodieWriteConfig对象，并将其传递给HoodieWriteClient构造函数。 HoodieWriteConfig可以使用以下构建器模式构建。
 * @ClassName WriteClientMain
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-26 10:40:29
 * @Version V1.0
 */
public class WriteClientMain {

    private static final String APP_NAME = WriteClientMain.class.getSimpleName();
    private static final String MASTER = "local[2]";
    private static final String SOURCE = "hudi";

    public static void main(String[] args) {
        // 创建SparkConf
        SparkConf conf = new SparkConf()
                .setAll(JavaConverters.mapAsScalaMapConverter(Configured.sparkConf()).asScala());
        // 创建SparkContext
        JavaSparkContext jsc = new JavaSparkContext(MASTER, APP_NAME, conf);
        // 创建Hudi的WriteConfig
        HoodieWriteConfig hudiCfg = HoodieWriteConfig.newBuilder()
                .forTable("tableName")

                .withSchema("avroSchema")
                .withPath("basePath")
                .withProps(new HashMap())
                .withIndexConfig(HoodieIndexConfig.newBuilder().withIndexType(
                        HoodieIndex.IndexType.BLOOM
                        // HoodieIndex.IndexType.GLOBAL_BLOOM
                        // HoodieIndex.IndexType.INMEMORY
                        // HoodieIndex.IndexType.HBASE
                        // HoodieIndex.IndexType.SIMPLE
                        // HoodieIndex.IndexType.GLOBAL_SIMPLE
                ).build())
                .build();
        // 创建Hudi的WriteClient
        HoodieWriteClient hudiWriteCli = new HoodieWriteClient<OverwriteWithLatestAvroPayload>(jsc, hudiCfg);

        // 1、执行新增操作
        JavaRDD<HoodieRecord> insertData = jsc.parallelize(Arrays.asList());
        String insertInstantTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        JavaRDD<WriteStatus> insertStatus = hudiWriteCli.insert(insertData, insertInstantTime);
        // 【注意：为了便于理解，以下所有判断Hudi操作数据的状态不进行额外的方法封装】
        if (insertStatus.filter(ws->ws.hasErrors()).count()>0) {// 当提交后返回的状态中包含error时
            hudiWriteCli.rollback(insertInstantTime);// 从时间线（insertInstantTime）中回滚，插入失败
        } else {
            hudiWriteCli.commit(insertInstantTime, insertStatus);// 否则提交时间线（insertInstantTime）中的数据，到此，插入完成
        }

        // 2、也可以使用批量加载的方式新增数据
        String builkInsertInstantTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        JavaRDD<WriteStatus> bulkInsertStatus = hudiWriteCli.bulkInsert(insertData, builkInsertInstantTime);
        if (bulkInsertStatus.filter(ws->ws.hasErrors()).count()>0) {// 当提交后返回的状态中包含error时
            hudiWriteCli.rollback(builkInsertInstantTime);// 从时间线（builkInsertInstantTime）中回滚，批量插入失败
        } else {
            hudiWriteCli.commit(builkInsertInstantTime, bulkInsertStatus);// 否则提交时间线（builkInsertInstantTime）中的数据，到此，批量插入完成
        }

        // 3、执行修改or新增操作
        JavaRDD<HoodieRecord> updateData = jsc.parallelize(Arrays.asList());
        String updateInstantTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        JavaRDD<WriteStatus> updateStatus = hudiWriteCli.upsert(updateData, updateInstantTime);
        if (updateStatus.filter(ws->ws.hasErrors()).count()>0) {// 当提交后返回的状态中包含error时
            hudiWriteCli.rollback(updateInstantTime);// 从时间线（updateInstantTime）中回滚，修改失败
        } else {
            hudiWriteCli.commit(updateInstantTime, updateStatus);// 否则提交时间线（updateInstantTime）中的数据，到此，修改完成
        }

        // 4、执行删除操作
        JavaRDD<HoodieRecord> deleteData = jsc.parallelize(Arrays.asList());
        String deleteInstantTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        JavaRDD<WriteStatus> deleteStatus = hudiWriteCli.delete(deleteData, deleteInstantTime);
        if (deleteStatus.filter(ws->ws.hasErrors()).count()>0) {// 当提交后返回的状态中包含error时
            hudiWriteCli.rollback(deleteInstantTime);// 从时间线（deleteInstantTime）中回滚，删除失败
        } else {
            hudiWriteCli.commit(deleteInstantTime, deleteStatus);// 否则提交时间线（deleteInstantTime）中的数据，到此，删除完成
        }

        // 退出WriteClient
        hudiWriteCli.close();
        // 退出SparkContext
        jsc.stop();
    }


}
