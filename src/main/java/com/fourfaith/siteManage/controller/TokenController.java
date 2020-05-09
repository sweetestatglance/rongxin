//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.controller;

import com.fourfaith.siteManage.model.TaskJson2001;
import com.fourfaith.siteManage.model.TaskJson3001;
import com.fourfaith.siteManage.model.Token;
import com.fourfaith.siteManage.model.TokenJson;
import com.fourfaith.utils.PropertiesUtils;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class TokenController {
    public TokenController() {
    }

    public static void getToken(HttpServletRequest request) throws ConnectException {
        Gson gson = new Gson();
        String token = null;
        String authUrl = PropertiesUtils.getPara("web_apiaddress");
        Token tj = new Token();
        tj.setIdentity("13246515");
        TokenJson tokj = new TokenJson();
        tokj.setType(1001);
        tokj.setToken("null");
        tokj.setSecureflag(0);
        tokj.setPayload(tj);
        String url = gson.toJson(tokj);
        String strURL = null;

        try {
            strURL = URLEncoder.encode(url, "utf-8").replace("*", "*").replace("~", "~").replace("+", " ");
        } catch (UnsupportedEncodingException var19) {
            var19.printStackTrace();
        }

        authUrl = authUrl + strURL;
        String result = "";
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);
        GetMethod getMethod = new GetMethod(authUrl);
        getMethod.getParams().setParameter("http.protocol.content-charset", "utf-8");
        getMethod.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

        try {
            int statusCode = 0;
            statusCode = httpClient.executeMethod(getMethod);
            if (statusCode == 200) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream(), "utf-8"));

                    for(String msg = null; (msg = reader.readLine()) != null; result = result + msg) {
                    }
                } catch (IOException var20) {
                    var20.printStackTrace();
                }
            }

            TaskJson3001 tk = (TaskJson3001)gson.fromJson(result, TaskJson3001.class);
            if (tk != null) {
                token = tk.getPayload().get("token").toString();
                int rspCode = tk.getRspcode();
                if (rspCode != 0) {
                    throw new ConnectException();
                }
            }
        } catch (Exception var21) {
            throw new ConnectException();
        } finally {
            getMethod.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(0L);
        }

        HttpSession session = request.getSession();
        session.setAttribute("Token", token);
    }

    public static TaskJson2001 getTwoTaskBeans() {
        TaskJson2001 json = new TaskJson2001();
        json.setType(Integer.parseInt(PropertiesUtils.getPara("2001Operate")));
        json.setSecureflag(0);
        return json;
    }

    public static TaskJson3001 getThreeTaskBean() {
        TaskJson3001 json = new TaskJson3001();
        json.setType(Integer.parseInt(PropertiesUtils.getPara("3001Operate")));
        json.setSecureflag(0);
        return json;
    }

    public static TaskJson2001 getFourTaskBean() {
        TaskJson2001 json = new TaskJson2001();
        json.setType(Integer.parseInt(PropertiesUtils.getPara("4001Operate")));
        json.setSecureflag(0);
        return json;
    }
}
