//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils.ip;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    public static Path classpath(String name) {
        try {
            URL url = FileUtil.class.getResource(name);
            return url != null ? Paths.get(url.toURI()) : null;
        } catch (URISyntaxException var2) {
            return null;
        }
    }

    private FileUtil() {
    }

    public static void main(String[] args) {
        Path classpath = classpath("/qqwry.dat");
        System.out.println(classpath);
    }
}
