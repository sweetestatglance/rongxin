//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.model.SysNotice;
import com.fourfaith.sysManage.service.SysNoticeService;
import com.fourfaith.utils.CommonUtil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NoticeUtil {
    ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml", "spring-mybatis.xml"});
    BeanFactory factory;
    SysNoticeService sysNoticeService;
    SimpleDateFormat sp;

    public NoticeUtil() {
        this.factory = this.context;
        this.sysNoticeService = (SysNoticeService)this.factory.getBean("sysNoticeService");
        this.sp = new SimpleDateFormat("yyyy/MM/dd");
    }

    public void getWithout() {
        boolean result = false;

        try {
            Document doc = Jsoup.connect("http://news.xiamenwater.com/SmallClass.asp?BName=%C6%F3%CE%F1%B9%AB%BF%AA&BType=0&SName=%CD%A3%CB%AE%CD%A8%D6%AA&displayTree=1&model=3").get();
            Elements ListDiv = doc.getElementsByAttributeValue("style", "TABLE-LAYOUT: fixed");
            Elements ListDivTable = ListDiv.select("tr").select("table");
            this.sysNoticeService.deleteNotice(0);
            Iterator var6 = ListDivTable.iterator();

            while(var6.hasNext()) {
                Element lineInfo = (Element)var6.next();
                Elements links = lineInfo.getElementsByTag("tr");
                Iterator var9 = links.iterator();

                while(var9.hasNext()) {
                    Element element = (Element)var9.next();
                    String linkHref = element.getElementsByTag("a").attr("href");
                    String linkText = element.getElementsByTag("a").text().trim();
                    String time = element.getElementsByTag("span").text().trim();
                    SysNotice notice = new SysNotice();
                    notice.setId(CommonUtil.getRandomUUID());
                    notice.setHref(linkHref);
                    notice.setTitle(linkText);
                    notice.setTime(time);
                    notice.setType(0);
                    notice.setTm(new Date());
                    Date orderTm = null;
                    if (StringUtils.isNotBlank(time)) {
                        time = com.fourfaith.utils.StringUtils.trimFirstAndLastChar(time, '[', ']');

                        try {
                            orderTm = this.sp.parse(time);
                        } catch (ParseException var16) {
                            var16.printStackTrace();
                        }
                    }

                    notice.setOrdertm(orderTm);
                    this.sysNoticeService.insert(notice);
                }
            }

            result = true;
        } catch (IOException var17) {
            var17.printStackTrace();
            System.out.println("停水通知查询结果=" + result);
            if (!result) {
                this.getWithout();
            }
        }

    }

    public void getWaterQuality() {
        boolean result = false;

        try {
            Document doc = Jsoup.connect("http://news.xiamenwater.com/SmallClass.asp?BName=%C6%F3%CE%F1%B9%AB%BF%AA&BType=0&SName=%CB%AE%D6%CA%D0%C5%CF%A2&displayTree=1&model=3").get();
            Elements ListDiv = doc.getElementsByAttributeValue("style", "TABLE-LAYOUT: fixed");
            Elements ListDivTable = ListDiv.select("tr").select("table");
            this.sysNoticeService.deleteNotice(1);
            Iterator var6 = ListDivTable.iterator();

            while(var6.hasNext()) {
                Element lineInfo = (Element)var6.next();
                Elements links = lineInfo.getElementsByTag("tr");
                Iterator var9 = links.iterator();

                while(var9.hasNext()) {
                    Element element = (Element)var9.next();
                    String linkHref = element.getElementsByTag("a").attr("href");
                    String linkText = element.getElementsByTag("a").text().trim();
                    String time = element.getElementsByTag("span").text().trim();
                    SysNotice notice = new SysNotice();
                    notice.setId(CommonUtil.getRandomUUID());
                    notice.setHref(linkHref);
                    notice.setTitle(linkText);
                    notice.setTime(time);
                    notice.setType(1);
                    notice.setTm(new Date());
                    Date orderTm = null;
                    if (StringUtils.isNotBlank(time)) {
                        time = com.fourfaith.utils.StringUtils.trimFirstAndLastChar(time, '[', ']');

                        try {
                            orderTm = this.sp.parse(time);
                        } catch (ParseException var16) {
                            var16.printStackTrace();
                        }
                    }

                    notice.setOrdertm(orderTm);
                    this.sysNoticeService.insert(notice);
                }
            }

            result = true;
        } catch (IOException var17) {
            var17.printStackTrace();
            System.out.println("水质通告查询结果=" + result);
            if (!result) {
                this.getWaterQuality();
            }
        }

    }

    public static void main(String[] args) {
        String str = "[2016/6/27]";
        str = com.fourfaith.utils.StringUtils.trimFirstAndLastChar(str, '[', ']');
        System.out.println("=====================" + str);
        SimpleDateFormat sp = new SimpleDateFormat("yyyy/MM/dd");

        try {
            System.out.println(sp.parse(str));
            System.out.println(new Date());
        } catch (ParseException var4) {
            var4.printStackTrace();
        }

    }
}
