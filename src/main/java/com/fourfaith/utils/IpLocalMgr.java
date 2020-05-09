//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import com.fourfaith.utils.ip.FileUtil;
import com.fourfaith.utils.ip.IPSeeker;
import java.io.IOException;

public class IpLocalMgr {
    private static IpLocalMgr m_instance = new IpLocalMgr();
    private IPSeeker m_iPSeeker;

    public static IpLocalMgr getInstance() {
        return m_instance;
    }

    private IpLocalMgr() {
    }

    public void init() throws IOException {
        try {
            m_instance.m_iPSeeker = new IPSeeker(FileUtil.classpath("/qqwry.dat"));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public IPSeeker getM_iPSeeker() {
        return this.m_iPSeeker;
    }
}
