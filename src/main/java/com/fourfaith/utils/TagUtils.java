//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.lang3.StringUtils;

public class TagUtils extends TagSupport {
    private static final long serialVersionUID = -7644952050507273750L;
    private String str;
    private int maxDigits;

    public TagUtils() {
    }

    public void setValue(String str) {
        this.str = str;
    }

    public void setMaxDigits(int maxDigits) {
        this.maxDigits = maxDigits;
    }

    public int doStartTag() {
        JspWriter out = this.pageContext.getOut();

        try {
            if (StringUtils.isBlank(this.str)) {
                out.print("--");
            } else {
                BigDecimal value = new BigDecimal(this.str);
                double result = value.setScale(this.maxDigits, 4).doubleValue();
                if (this.maxDigits == 0) {
                    out.print((int)result);
                } else {
                    out.print(result);
                }
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return 1;
    }

    public int doAfterBody() throws JspException {
        return 0;
    }

    public int doEndTag() throws JspException {
        return 6;
    }
}
