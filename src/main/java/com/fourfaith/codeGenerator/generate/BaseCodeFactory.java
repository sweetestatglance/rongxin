//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.codeGenerator.generate;

import com.fourfaith.codeGenerator.core.CodeResourceUtil;
import freemarker.template.Configuration;
import java.io.IOException;
import java.util.Locale;

public class BaseCodeFactory {
    public BaseCodeFactory() {
    }

    public Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(this.getClass(), CodeResourceUtil.FREEMARKER_CLASSPATH);
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }
}
