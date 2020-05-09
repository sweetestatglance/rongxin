//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import com.fourfaith.reportManage.model.StAllDetailsFactor;
import com.fourfaith.reportManage.model.StFactorName;
import com.fourfaith.reportManage.service.StAllDetailsFactorService;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    public Test2() {
    }

    public static void main(String[] args) throws Exception {
        StFactorName st = new StFactorName();
        st.setFactorviewname11("12345");
        Field field = StFactorName.class.getDeclaredField("td11".replace("td", "factorviewname"));
        field.setAccessible(true);
        System.out.println(field.get(st));
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml", "spring-mybatis.xml"});
        StAllDetailsFactorService stAllDetailsFactorService = (StAllDetailsFactorService)context.getBean("stAllDetailsFactorService");
        Date beginTime = DateUtils.StringToDate("2017-04-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date endTime = DateUtils.add(beginTime, 5, 20);

        while(beginTime.before(endTime)) {
            StAllDetailsFactor model = new StAllDetailsFactor();
            model.setId(CommonUtil.getRandomUUID());
            model.setStcd("8888888888");
            model.setTm(beginTime);
            model.setAddvcd("eac7eabe7dd24d9b96f1780e2df1a5bb");
            model.setVoltage(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setSignalinten(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setPn05(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setZ(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setZb(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setZu(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setVj(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setVa(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setQ(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setQa(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setAi(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setC(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setMst(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setFl(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setUc(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setUs(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setUe(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setEj(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setEd(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setGtp(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setM10(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setM20(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setM30(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setM40(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setM50(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setM60(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setM80(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setM100(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setPh(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setDoxy(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setCond(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTurb(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setNh4n(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTp(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTn(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setChla(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd11(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd12(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd13(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd13(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd14(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd15(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd16(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd17(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd18(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd19(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd20(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd21(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd22(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd23(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            model.setTd24(Math.random() > 0.8D ? null : new BigDecimal((new DecimalFormat("####.00")).format(Math.random() * 80.0D)));
            beginTime = DateUtils.add(beginTime, 12, 5);

            try {
                stAllDetailsFactorService.insert(model);
            } catch (Exception var10) {
                var10.printStackTrace();
            }
        }

    }
}
