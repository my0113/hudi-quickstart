package com.kingsoft.utils;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @ClassName HttpTool
 * @Description
 * @Created by: MengYao
 * @Date: 2021-01-18 21:17:43
 * @Version V1.0
 */
public class HttpTool {

    /** 默认字符集 **/
    private static final String CHARSET = "UTF-8";
    /** 成功状态码 **/
    private static final int SUCCESS_CODE = 200;

    private static HttpClient client;
    private static Header[] headers;

    static {
        headers = new Header[] {
            /** 设置请求头的内容类型为JSON **/
            new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8"),
            /** 设置请求头的连接参数为始终保持连接 **/
            //new BasicHeader(HttpHeaders.CONNECTION, "Keep-Alive"),
            /** 设置请求头缓存策略为不使用缓存 **/
            new BasicHeader(HttpHeaders.CACHE_CONTROL, "no-cache"),
            /** 设置请求头的UA标识为Chrome **/
            new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36")
        };
        client = new DefaultHttpClient();
    }

    /**
     * Get请求
     * @param url       指定URL
     * @return
     */
    public static String get(String url) {
        return get(url, null);
    }

    /**
     * Get请求
     * @param url       指定URL
     * @param params    入参
     * @return
     */
    public static String get(String url, Map<String, String> params) {
        HttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (Objects.nonNull(params)) {
                params.forEach((param,value) -> uriBuilder.addParameter(param, value));
            }
            response = client.execute(new HttpGet(uriBuilder.build()) {{
                setHeaders(headers);
            }});
            if (SUCCESS_CODE == response.getStatusLine().getStatusCode()) {
                return EntityUtils.toString(response.getEntity(), CHARSET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
        return null;
    }

    /**
     * Post请求
     * @param url       指定URL
     * @return
     */
    public static String post(String url) {
        return post(url, null);
    }

    /**
     * Post请求
     * @param url       指定URL
     * @param params    入参
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        HttpResponse response = null;
        try {
            final HttpPost post = new HttpPost(url) {{
                setHeaders(headers);
            }};
            if (Objects.nonNull(params)) {
                post.setEntity(new UrlEncodedFormEntity(params.entrySet().stream()
                        .map(e -> new BasicNameValuePair(e.getKey(), e.getValue()))
                        .collect(Collectors.toList()),
                        CHARSET));
            }
            response = client.execute(post);
            if (SUCCESS_CODE == response.getStatusLine().getStatusCode()) {
                return EntityUtils.toString(response.getEntity(), CHARSET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(HttpTool.get("http://10.69.73.228:8088/ws/v1/cluster/apps/application_1609988336857_0015"));
        System.out.println("########");
        System.out.println(HttpTool.post("http://10.69.73.228:8088/ws/v1/cluster/apps/application_1609988336857_0015"));
        System.out.println("".compareTo("") > 0 ? 1 : 0);
    }

}
