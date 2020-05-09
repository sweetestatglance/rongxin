//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.dao;

import com.fourfaith.siteManage.model.StAddvcdD;
import java.util.List;
import java.util.Map;

public interface StAddvcdDMapper {
    int deleteByPrimaryKey(String var1);

    int insert(StAddvcdD var1);

    int insertSelective(StAddvcdD var1);

    StAddvcdD selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StAddvcdD var1);

    int updateByPrimaryKey(StAddvcdD var1);

    Integer getCount(Map<String, Object> var1);

    List<StAddvcdD> getList(Map<String, Object> var1);

    StAddvcdD getAddvnm(Map<String, Object> var1);

    List<StAddvcdD> getChildAddvcdD(String var1);

    int isParent(String var1);

    StAddvcdD findByFaddvcd(String var1);
}
