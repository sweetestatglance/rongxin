//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.util.HashMap;
import java.util.Map;

public enum RtuCodeEnum {
    UpdateConfig("远程配置", "40"),
    RtuUpgrade("远程升级", "E1"),
    RtuRestart("远程重启", "E2");

    private String text;
    private String value;

    private RtuCodeEnum(String text, String value) {
        this.value = value;
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public String getValue() {
        return this.value;
    }

    public static Map<String, String> getMapSource() {
        Map<String, String> source = new HashMap();
        RtuCodeEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            RtuCodeEnum r = var4[var2];
            source.put(r.getValue(), r.getText());
        }

        return source;
    }
}
