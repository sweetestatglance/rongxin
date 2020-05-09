//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import org.springframework.stereotype.Service;

@Service
public class TaskJob {
    public TaskJob() {
    }

    public void job() {
        System.out.println("读取通知通告任务进行中。。。");
        NoticeUtil util = new NoticeUtil();
        util.getWithout();
        util.getWaterQuality();
    }
}
