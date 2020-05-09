//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.service;

import com.fourfaith.reportManage.model.StEnterFactor;

public interface StEnterFactorService {
    int deleteByPrimaryKey(String var1);

    int insert(StEnterFactor var1);

    int insertSelective(StEnterFactor var1);

    StEnterFactor selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StEnterFactor var1);

    int updateByPrimaryKey(StEnterFactor var1);

    StEnterFactor getByEnterId(String var1);
}
