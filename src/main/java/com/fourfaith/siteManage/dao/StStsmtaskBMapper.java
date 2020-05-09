//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.dao;

import com.fourfaith.siteManage.model.StStsmtaskB;
import java.util.List;
import java.util.Map;

public interface StStsmtaskBMapper {
    int deleteByPrimaryKey(String var1);

    int insert(StStsmtaskB var1);

    int insertSelective(StStsmtaskB var1);

    StStsmtaskB selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StStsmtaskB var1);

    int updateByPrimaryKey(StStsmtaskB var1);

    Integer getCount(Map<String, Object> var1);

    List<StStsmtaskB> getList(Map<String, Object> var1);
}
