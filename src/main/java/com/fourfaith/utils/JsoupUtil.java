//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.io.IOException;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
    static String url = "http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html";

    public JsoupUtil() {
    }

    private static void bolgBody() throws IOException {
        String html = "<html><head><title> 开源中国社区 </title></head><body><p> 这里是 jsoup 项目的相关文章 </p></body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.body());
        Document doc2 = Jsoup.connect(url).get();
        String title = doc2.body().toString();
        System.out.println(title);
    }

    public static void article() {
        try {
            Document doc = Jsoup.connect("http://news.xiamenwater.com/SmallClass.asp?BName=%C6%F3%CE%F1%B9%AB%BF%AA&BType=0&SName=%CD%A3%CB%AE%CD%A8%D6%AA&displayTree=1&model=3").get();
            Elements ListDiv31 = doc.getElementsByAttributeValue("style", "TABLE-LAYOUT: fixed");
            Elements ListDiv3 = ListDiv31.select("tr").select("table");
            Iterator var4 = ListDiv3.iterator();

            while(var4.hasNext()) {
                Element lineInfo = (Element)var4.next();
                Elements links = lineInfo.getElementsByTag("tr");
                Iterator var7 = links.iterator();

                while(var7.hasNext()) {
                    Element element = (Element)var7.next();
                    String linkHref = element.getElementsByTag("a").attr("href");
                    String linkText = element.getElementsByTag("a").text().trim();
                    String time = element.getElementsByTag("span").text().trim();
                    System.out.println(linkHref);
                    System.out.println(linkText);
                    System.out.println(time);
                }
            }
        } catch (IOException var11) {
            var11.printStackTrace();
        }

    }

    public static void blog() {
        try {
            Document doc = Jsoup.connect("http://www.four-faith.com/html/yaocezhongduan/53.html").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class", "property");
            Iterator var3 = ListDiv.iterator();

            while(var3.hasNext()) {
                Element element = (Element)var3.next();
                System.out.println(element.html());
            }
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        article();
    }
}
