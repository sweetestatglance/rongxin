//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service;

import com.fourfaith.siteManage.model.StStsmtaskB;
import java.util.List;
import java.util.Map;

public interface StStsmtaskBService {
    int deleteByPrimaryKey(String var1);

    int insert(StStsmtaskB var1);

    int insertSelective(StStsmtaskB var1);

    StStsmtaskB selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StStsmtaskB var1);

    int updateByPrimaryKey(StStsmtaskB var1);

    Integer getCount(Map<String, Object> var1);

    List<StStsmtaskB> getList(Map<String, Object> var1);

    StStsmtaskB findById(String var1);

    Integer add(StStsmtaskB var1);

    Integer update(StStsmtaskB var1);

    Integer delete(String var1);

    List<String> getFactorFlagList(StStsmtaskB var1, String var2);
}
