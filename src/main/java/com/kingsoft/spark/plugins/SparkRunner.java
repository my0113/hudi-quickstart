package com.kingsoft.spark.plugins;

import java.io.IOException;
import java.util.Map;

import com.kingsoft.Configured;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.SystemUtils;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;

/**
 * 使用客户端提交Spark的yarn-cluster应用程序
 * @ClassName SparkRunner
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-19 15:49:47
 * @Version V1.0
 */
public class SparkRunner {

	// 初始化环境变量
	private static final Map<String, String> ENV = Configured.env();
	static {
		if(SystemUtils.IS_OS_WINDOWS) {
			// 提权至特定用户
			ENV.put(Configured.USER_NAME, Configured.getUserName());
		}
	}

	public static void main(String[] args) {
		// 构建应用启动程序
		SparkLauncher appLauncher = new SparkLauncher(ENV)
				.setAppResource("file://D:/softs/developer/Apache/spark-2.4.6-bin-hadoop2.7/examples/jars/spark-examples_2.11-2.4.6.jar")
				.setMainClass("org.apache.spark.examples.SparkPi")
				.setMaster("yarn")
				.setDeployMode("cluster")
				.setAppName("SparkPi")
				.setConf("spark.driver.memory", "512m")
				.setConf("spark.executor.memory", "1g")
				.setConf("spark.executor.cores", "1")
				.setConf("spark.yarn.queue", "default")
				.setConf("spark.executor.instances", "1")
				.setConf("spark.default.parallelism", "10")
				.setConf("spark.driver.allowMultipleContexts","false")
				.setVerbose(true);

		// 如果有参数
		if (args.length > 0) {
			appLauncher.addAppArgs(args);
		}
		// 提交应用
		appSubmit(appLauncher, RunType.launch);
	}

	/**
	 * 使用SparkLauncher提交应用程序
	 * 		launch方式：
	 * 				通过SparkLanuncher.lanunch()方法获取一个进程process，再调用进程的process.waitFor()方法等待线程返回结果。
	 * 			这种方式需要自行维护应用运行期间的输出信息，通过process进程的getInputStream、getErrorStream和getOutputStream
	 * 			来获取即获取的输出信息，与使用spark-submit.sh脚本提交应用程序所打印的结果一样。
	 * 		startApp方式：
	 * 				此启动器启动的应用程序作为子进程运行。 当SparkLauncher没有配置重定向输出时，子级的stdout和stderr才会合并并
	 * 			写入记录器（请参阅java.util.logging）。
	 * 			
	 * @param appLauncher	构建SparkLauncher实例
	 * @param runType		采用何种方式运行：launch和startApp两种
	 */
	private static void appSubmit(SparkLauncher appLauncher, RunType runType) {
		try {
			// 运行一个子进程，该子进程将启动配置的Spark应用程序
			if (runType == RunType.launch) {
				final Process process = appLauncher.launch();
		        new Thread(() -> {
		        	System.out.println("==== stdin:");
		        	try {
						LineIterator iterator = IOUtils.lineIterator(process.getInputStream(), Configured.getDefaultCharset());
						iterator.forEachRemaining(line->{
							System.out.println(line);
						});
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }).start();
		        new Thread(() -> {
		        	System.out.println("==== stderr:");
		        	try {
						LineIterator iterator = IOUtils.lineIterator(process.getErrorStream(), Configured.getDefaultCharset());
						iterator.forEachRemaining(line->{
							System.out.println(line);
						});
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }).start();
		        int exitCode = process.waitFor();
		        System.out.println("==== Spark job finished, exit code: " + exitCode + " ====");
			}
			// 此启动器启动的应用程序作为子进程运行。 仅当未在此SparkLauncher上另外配置重定向时，子级的stdout和stderr才会合并并写入记录器（请参阅java.util.logging） 
			if (runType == RunType.startApp) {
				SparkAppHandle appHandle = appLauncher.startApplication(new SparkAppHandle.Listener() {
					@Override
					public void stateChanged(SparkAppHandle handle) {
						System.out.println("==== Spark job state was changed to："+handle.getState()+" ====");
					}
					@Override
					public void infoChanged(SparkAppHandle handle) {
						System.out.println("==== Spark job info was changed. ====");
					}
				});
				while (appHandle.getState() != SparkAppHandle.State.FINISHED) {
				}
			}
		} catch (IOException|InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * SparkApplication Running Type
	 * Created by mengyao
	 * 2019年11月15日
	 */
	enum RunType {
		launch,
		startApp
	}

}









