package com.fourfaith.rongxin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.rongxin.model.MessageVo;
import com.fourfaith.rongxin.model.Openid;
import com.fourfaith.rongxin.service.MessagesService;
import com.fourfaith.rongxin.utils.AlexUtil;
import com.fourfaith.rongxin.wechat.AzureToken;
import com.fourfaith.rongxin.wechat.MyX509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.net.www.protocol.https.HttpsURLConnectionImpl;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

//@Component
public class AccessTokenTimer {


    public final static String AccessTokenUri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static final String  appid = "wx4f0039137c82d95e";

    public static final String appsecret = "adfe6140f1fff457b7336233e3010893";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private MessagesService messagesService;
    /**
     * 定时器：需要在spring配置中开启定时任务扫描
     * cron配置每9分钟执行一次，cron设置很怪只能0秒执行，不是任意时间计时
     */
    //@Scheduled(cron = "0/10 * * * * ?")
    public void timerfortoken() throws IOException {
        List<Openid> openids = new ArrayList<>();
        List<MessageVo> messageVo = new ArrayList<>();
        openids = messagesService.getopenids();
        messageVo = messagesService.getvo(AlexUtil.getTableName(new Date()));
        AzureToken token = getWxAccessToken();
        System.out.println("调用timerfortoken");
        if (messageVo != null && messageVo.size() > 0){
            for(MessageVo v : messageVo){
                Integer issended = messagesService.issended(v);
                if (issended == 0){
                    for (Openid x : openids){
                        try {
                            WxMessagesPush.pushmessage(v, token, x);
                            v.setUsername(x.getWxuser());
                            v.setPhone(x.getPhone());
                            v.setTm(new Date());
                            messagesService.addwxpushlog(v);
                        } catch (IOException e) {
                            logger.error("微信推送失败！");
                        }
                    }
                }
            }
        }
    }

    private static Logger logger = LoggerFactory.getLogger(AccessTokenTimer.class);

    public AzureToken getWxAccessToken(){
        AzureToken accesstoken;
        String key = "wxtoken";
        try {
            if (redisTemplate.hasKey(key)) {
                String value = redisTemplate.opsForValue().get(key);
                accesstoken = JSON.parseObject(value, AzureToken.class);
                return accesstoken;
            }
        } catch (Exception e) {
            logger.error("redis连接失败");
        }
        try {
            String requestUrl = AccessTokenUri.replace("APPID", appid).replace("APPSECRET", appsecret);
            //JSONObject jsonObject = new JSONObject();
            /*jsonObject.put("access_token","32_qs8HrBzwM6zLU14vBKXU3m7cwtkQpI8i-LY9dHBWRTwcp9iYMnB8fTTChTFvAfr8E22qN15p-hmOqup4UvpHgMhYseL5gfhEFb-3JD47eSJ4jziuNfhjHlvK4ANRfxOVX4jl3cYxnOyPNXugQYYbAGATBB");
            jsonObject.put("expires_in",7200);*/
            JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
            System.out.println("jsonObject: "+jsonObject);
            if (null != jsonObject) {
                accesstoken = new AzureToken();
                accesstoken.setToken(jsonObject.getString("access_token"));
                accesstoken.setAddTime(System.currentTimeMillis());
                //正常过期时间是7200秒，此处设置3600秒读取一次
                accesstoken.setExpiresIn(jsonObject.getIntValue("expires_in"));
                try {
                    //加入缓存
                    redisTemplate.opsForValue().set(key, JSON.toJSONString(accesstoken));
                    redisTemplate.expire(key, 3600, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return accesstoken;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnectionImpl httpUrlConn = (HttpsURLConnectionImpl) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
            // jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            System.out.println("Weixin server connection timed out.");
        } catch (Exception e) {
            System.out.println("https request error:{}" + e.getMessage());
        }
        return jsonObject;
    }

    private static URI buildRequestURI() throws MalformedURLException {
        return URI.create(AccessTokenUri.replace("APPID", appid).replace("APPSECRET", appsecret));
    }

    public static void main(String[] args) {
        AzureToken wxAccessToken = new AccessTokenTimer().getWxAccessToken();
        System.out.println(wxAccessToken);
    }
}
