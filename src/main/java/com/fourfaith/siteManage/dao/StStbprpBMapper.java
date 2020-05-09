//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.dao;

import com.fourfaith.siteManage.model.StStbprpB;
import java.util.List;
import java.util.Map;

public interface StStbprpBMapper {
    int deleteByPrimaryKey(String var1);

    int insert(StStbprpB var1);

    int insertSelective(StStbprpB var1);

    StStbprpB selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StStbprpB var1);

    int updateByPrimaryKey(StStbprpB var1);

    Integer getCount(Map<String, Object> var1);

    List<StStbprpB> getList(Map<String, Object> var1);

    List<StStbprpB> getListByType(Map<String, Object> var1);

    StStbprpB getByStcd(String var1);

    StStbprpB getByTel(String var1);

    int getByAddvcdDIdCount(Map<String, Object> var1);

    List<StStbprpB> getStbprpBCollect(Map<String, Object> var1);

    List<StStbprpB> findMaxPj(Map<String, Object> var1);
}
