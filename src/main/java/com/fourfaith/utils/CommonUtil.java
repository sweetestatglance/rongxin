//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import com.fourfaith.sysManage.model.SysUser;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

public class CommonUtil {
    public static String mSessionLoginUser = "LOGINUSERINFO";

    public CommonUtil() {
    }

    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Object getLoginUserInfo(HttpServletRequest request) {
        HttpSession mHs = request.getSession();
        return mHs.getAttribute(mSessionLoginUser);
    }

    public static void setLoginUserInfo(HttpServletRequest request, SysUser user) {
        HttpSession mHs = request.getSession();
        if (user != null) {
            mHs.setAttribute(mSessionLoginUser, user);
        } else {
            mHs.setAttribute(mSessionLoginUser, (Object)null);
        }

    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public static String getIpToArea(String ip) {
        String ip_taobao = PropertiesUtils.getPara("ip_taobao");
        String url = ip_taobao + ip;
        String reslut = httpUtils.get(url);
        JSONObject attr = JSONObject.fromObject(reslut);
        String code = attr.get("code").toString();
        String str = "查询失败";
        if (code.equals("0")) {
            Object payload = attr.get("data");
            JSONArray array = JSONArray.fromObject(payload);
            JSONObject obj = JSONObject.fromObject(array.get(0));
            String region = obj.getString("region");
            String city = obj.getString("city");
            if (StringUtils.isNotBlank(region)) {
                str = region + city;
            } else {
                str = "内网";
            }
        }

        return str;
    }

    public static void main(String[] args) {
        getIpToArea("120.42.46.98");
        getIpToArea("127.0.0.1 ");
    }
}
