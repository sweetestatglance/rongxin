//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.dao;

import com.fourfaith.siteManage.model.StModel;
import java.util.List;
import java.util.Map;

public interface StModelMapper {
    int deleteByPrimaryKey(String var1);

    int insert(StModel var1);

    int insertSelective(StModel var1);

    StModel selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StModel var1);

    int updateByPrimaryKey(StModel var1);

    Integer getCount(Map<String, Object> var1);

    List<StModel> getList(Map<String, Object> var1);

    StModel getNm(Map<String, Object> var1);
}
