//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class LocalMAC {
    public LocalMAC() {
    }

    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static String getUnixMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;

        try {
            process = Runtime.getRuntime().exec("ifconfig eth0");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            boolean var4 = true;

            while((line = bufferedReader.readLine()) != null) {
                int index = line.toLowerCase().indexOf("hwaddr");
                if (index >= 0) {
                    mac = line.substring(index + "hwaddr".length() + 1).trim();
                    break;
                }
            }
        } catch (IOException var13) {
            var13.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException var12) {
                var12.printStackTrace();
            }

            bufferedReader = null;
            process = null;
        }

        System.out.println("unix mac地址==========================================：" + mac);
        return mac;
    }

    public static String getLocalMac() throws SocketException {
        InetAddress ia = null;

        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException var6) {
            var6.printStackTrace();
        }

        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");

        for(int i = 0; i < mac.length; ++i) {
            if (i != 0) {
                sb.append("-");
            }

            int temp = mac[i] & 255;
            String str = Integer.toHexString(temp);
            System.out.println("每8位:" + str);
            if (str.length() == 1) {
                sb.append("0" + str);
            } else {
                sb.append(str);
            }
        }

        System.out.println("window 本机MAC地址:" + sb.toString().toUpperCase());
        return sb.toString().toUpperCase();
    }

    public static void main(String[] argc) throws Exception {
        String os = getOSName();
        System.out.println(os);
        String mac;
        if (os.equals("unix")) {
            mac = getUnixMACAddress();
        } else {
            mac = getLocalMac();
            System.out.println(mac);
        }

    }
}
