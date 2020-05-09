//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils.ip;

import java.io.IOException;

public class IPSeekerTest {
    public IPSeekerTest() {
    }

    public static void main(String[] args) {
        IPSeeker seeker = null;

        try {
            seeker = new IPSeeker(FileUtil.classpath("/qqwry.dat"));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        IPLocation location = seeker.getLocation((byte)120, (byte)42, (byte)46, (byte)98);
        System.out.println("城市:" + location.getCountry());
    }
}
