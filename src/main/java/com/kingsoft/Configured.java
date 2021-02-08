package com.kingsoft;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @ClassName Configured
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-11 10:10:53
 * @Version V1.0
 */
public class Configured {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("config", Locale.CHINESE);
    private static Map<String, String> config = new HashMap<>();
    public static final String USER_NAME = "user.name";
    public static final String JAVA_HOME = "JAVA_HOME";
    public static final String HADOOP_HOME = "HADOOP_HOME";
    public static final String HADOOP_CONF_DIR = "HADOOP_CONF_DIR";
    public static final String HADOOP_HOME_DIR = "hadoop.home.dir";
    public static final String SPARK_HOME = "SPARK_HOME";
    public static final String DEFAULT_CHARSET = "default.charset";


    public static Map<String, String> get() {
        return bundle.keySet().stream()
                .collect(Collectors.toMap(key->key, key->bundle.getString(key), (e1,e2)->e2, HashMap::new));
    }

    public static Map<String, String> env() {
        return bundle.keySet().stream()
                .filter(k -> k.matches("(JAVA_HOME|HADOOP_CONF_DIR|SPARK_HOME|SPARK_PRINT_LAUNCH_COMMAND)"))
                .collect(Collectors.toMap(key->key, key->bundle.getString(key), (e1,e2)->e2, HashMap::new));
    }

    public static Map<String, String> sparkConf() {
        return bundle.keySet().stream()
                .filter(key->Objects.nonNull(key)&&key.startsWith("spark"))
                .collect(Collectors.toMap(key->key, key->bundle.getString(key), (e1,e2)->e2, HashMap::new));
    }

    public static Map<String, String> appConf() {
        return bundle.keySet().stream()
                .filter(key->Objects.nonNull(key)&&key.startsWith("app"))
                .collect(Collectors.toMap(key->key, key->bundle.getString(key), (e1,e2)->e2, HashMap::new));
    }

    public static String getDefaultCharset() {
        return bundle.getString(DEFAULT_CHARSET);
    }

    public static String getUserName() {
        return bundle.getString(USER_NAME);
    }
    public static String getJavaHome() {
        return bundle.getString(JAVA_HOME);
    }
    public static String getHadoopHome() {
        return bundle.getString(HADOOP_HOME);
    }
    public static String getHadoopConfDir() {
        return bundle.getString(HADOOP_CONF_DIR);
    }
    public static String getSparkHome() {
        return bundle.getString(SPARK_HOME);
    }


}
