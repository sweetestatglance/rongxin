//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.imgManage.service;

import com.fourfaith.imgManage.model.StImgMonit;
import java.util.List;
import java.util.Map;

public interface StImgMonitService {
    int deleteByPrimaryKey(String var1);

    int insert(StImgMonit var1);

    int insertSelective(StImgMonit var1);

    StImgMonit selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StImgMonit var1);

    int updateByPrimaryKey(StImgMonit var1);

    List<StImgMonit> getImgList(Map<String, Object> var1);

    Integer getImgCount(Map<String, Object> var1);

    StImgMonit getStcd(String var1);
}
