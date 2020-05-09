//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

public class httpUtils {
    protected static Logger logger = Logger.getLogger(httpUtils.class);

    public httpUtils() {
    }

    public static String post(String url, NameValuePair... data) {
        String result = "";
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter("http.protocol.content-charset", "utf-8");

        try {
            try {
                postMethod.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
                postMethod.setRequestBody(data);
                int statusCode = 0;

                try {
                    statusCode = httpClient.executeMethod(postMethod);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }

                if (statusCode != 200) {
                    logger.error("请求返回状态：" + statusCode);
                    return result;
                }

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream(), "utf-8"));

                    for(String msg = null; (msg = reader.readLine()) != null; result = result + msg) {
                    }

                    String var9 = result;
                    return var9;
                } catch (IOException var15) {
                    var15.printStackTrace();
                }
            } catch (Exception var16) {
                logger.error(var16.getMessage(), var16);
            }

            return result;
        } finally {
            postMethod.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(0L);
        }
    }

    public static String post(String url, List<NameValuePair> list) {
        String result = "";
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter("http.protocol.content-charset", "utf-8");

        try {
            try {
                postMethod.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
                NameValuePair[] data = new NameValuePair[list.size()];
                postMethod.setRequestBody((NameValuePair[])list.toArray(data));
                int statusCode = 0;

                try {
                    statusCode = httpClient.executeMethod(postMethod);
                } catch (IOException var15) {
                    var15.printStackTrace();
                }

                if (statusCode != 200) {
                    logger.error("请求返回状态：" + statusCode);
                    return result;
                }

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream(), "utf-8"));

                    for(String msg = null; (msg = reader.readLine()) != null; result = result + msg) {
                    }

                    String var10 = result;
                    return var10;
                } catch (IOException var16) {
                    var16.printStackTrace();
                }
            } catch (Exception var17) {
                logger.error(var17.getMessage(), var17);
            }

            return result;
        } finally {
            postMethod.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(0L);
        }
    }

    public static String get(String url) {
        url = url.replaceAll(" ", "%20");
        String result = "";
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        GetMethod postMethod = new GetMethod(url);
        postMethod.getParams().setParameter("http.protocol.content-charset", "utf-8");
        postMethod.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

        try {
            try {
                int statusCode = 0;

                try {
                    statusCode = httpClient.executeMethod(postMethod);
                } catch (ConnectException var13) {
                }

                if (statusCode != 200) {
                    logger.error("请求返回状态：" + statusCode);
                    return result;
                }

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream(), "utf-8"));

                    for(String msg = null; (msg = reader.readLine()) != null; result = result + msg) {
                    }

                    String var8 = result;
                    return var8;
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
            } catch (Exception var15) {
                logger.error(var15.getMessage(), var15);
            }

            return result;
        } finally {
            postMethod.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(0L);
        }
    }
}
