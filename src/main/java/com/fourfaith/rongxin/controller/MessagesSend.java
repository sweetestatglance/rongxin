package com.fourfaith.rongxin.controller;

import com.fourfaith.rongxin.httpclientpool.HttpConnectionManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URI;

public class MessagesSend {

    protected static Logger logger = LoggerFactory.getLogger(MessagesSend.class);
    private static String apiurl = "";
    private static String account = "";
    private static String pwd = "";
    private static String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)";
    private static String content = "test";

    public static void messageSend(String apikey, String textToSynthesize, String outputFormat, String locale, String genderName, String voiceName, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CloseableHttpClient client = null;
        try {
            client = HttpConnectionManager.getHttpClient(5000);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        HttpPost post = new HttpPost(buildRequestURI());
        post.addHeader("Content-type", "application/ssml+xml");
        post.addHeader("X-Microsoft-OutputFormat",outputFormat);
        post.addHeader("Authorization","Bearer ");
        post.addHeader("X-Search-AppId", "07D3234E49CE426DAA29772419F436CA");
        post.addHeader("X-Search-ClientID", "1ECFAE91408841A480F00935DC390960");
        post.addHeader("User-Agent", USER_AGENT);
        post.addHeader("Accept","*/*");
        /*RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
        post.setConfig(config);*/
        String body = null;
        post.setEntity(new StringEntity(body,"utf-8"));
        CloseableHttpResponse resp = null;
        try {
            // 执行请求
            resp = client.execute(post);
            // 判断返回状态是否为200
            long s2 = System.currentTimeMillis();
            if (resp.getStatusLine().getStatusCode() == 200) {
                //内容写入文件
                HttpEntity entity = resp.getEntity();
                if (entity.getContent() != null) {
                    ServletOutputStream out = response.getOutputStream();
                    entity.writeTo(out);
                    out.flush();
                    out.close();
                }
            }
            long s3 = System.currentTimeMillis();
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
        }finally {
            if (resp.getEntity() != null){
                resp.getEntity().getContent().close();
            }
            if (resp != null) {
                resp.close();
            }
        }
    }


    private static URI buildRequestURI() throws MalformedURLException {
        return URI.create(apiurl);
    }


}
