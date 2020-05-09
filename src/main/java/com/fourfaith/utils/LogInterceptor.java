//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import com.fourfaith.sysManage.model.SysLog;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysLogService;
import java.lang.reflect.Field;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor {
    @Autowired
    private SysLogService logService;

    public LogInterceptor() {
    }

    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
        System.out.print("===================+afterCompletion");
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
        Field field = null;
        field = arg2.getClass().getDeclaredField("logContent");
        Object logContent = field.get(arg2);
        SysUser userInfo = (SysUser)CommonUtil.getLoginUserInfo(request);
        if (userInfo != null && logContent != null && !userInfo.getIssupermanager() && logContent != null && StringUtils.isNotEmpty(logContent.toString())) {
            String[] attr = logContent.toString().split(",");
            if (attr != null && attr.length > 0) {
                for(int i = 0; i < attr.length; ++i) {
                    SysLog log = new SysLog();
                    log.setId(CommonUtil.getRandomUUID());
                    log.setEnterpriseid(userInfo.getEnterpriseid());
                    SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
                    log.setLogintime(login_user.getLoginTime());
                    log.setLoginarea(login_user.getLoginArea());
                    log.setLoginip(login_user.getLoginIp());
                    log.setLogtime(new Date());
                    log.setLogcontent(userInfo.getUsername() + "&nbsp;&nbsp;" + attr[i]);
                    log.setUserid(userInfo.getId());
                    this.logService.add(log);
                }
            }
        }

    }

    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        return true;
    }
}
