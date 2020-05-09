//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import com.fourfaith.reportManage.model.StAllDetailsFactor;
import com.fourfaith.reportManage.service.StAllDetailsFactorService;
import com.fourfaith.siteManage.service.DevicePhotoService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public Test() {
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml", "spring-mybatis.xml"});
        DevicePhotoService devicePhotoService = (DevicePhotoService)context.getBean("devicePhotoService");
        StAllDetailsFactorService stAllDetailsFactorService = (StAllDetailsFactorService)context.getBean("stAllDetailsFactorService");

        for(int i = 0; i < 8500; ++i) {
            double add = 6006012.0D;
            StAllDetailsFactor model = new StAllDetailsFactor();
            model.setId(CommonUtil.getRandomUUID());
            model.setStcd("1000000001");
            model.setTm(new Date());
            model.setAddvcd("d6ed9e2c78304579a0a09b7d1b98b96d");
            add += Math.random() * 10000.0D;
            model.setVoltage(new BigDecimal((new DecimalFormat("########")).format(add)));
            model.setPh(new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 100.0D)));

            try {
                stAllDetailsFactorService.insert(model);
            } catch (Exception var10) {
                var10.printStackTrace();
            }
        }

        new Date();
    }
}
