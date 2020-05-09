//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils.ip;

public class IP4Util {
    public static byte[] toBytes(int address) {
        return new byte[]{(byte)(address >>> 24 & 255), (byte)(address >>> 16 & 255), (byte)(address >>> 8 & 255), (byte)(address & 255)};
    }

    private IP4Util() {
    }
}
