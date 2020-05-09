
package com.fourfaith.utils;

import javax.servlet.ServletContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private static ServletContext servletContext;

    public ApplicationUtil() {
    }

    public static void init(ServletContext _servletContext) {
        servletContext = _servletContext;
    }

    public static ServletContext getServletContext() throws Exception {
        return servletContext;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
        } else {
            return applicationContext;
        }
    }

    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }
}
