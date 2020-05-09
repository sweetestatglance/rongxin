//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service;

import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.model.StStsmtaskB;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.ImportBean;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.RequestContext;

public interface StStbprpBService {
    int deleteByPrimaryKey(String var1);

    int insert(StStbprpB var1);

    int insertSelective(StStbprpB var1);

    Integer add(StStbprpB var1);

    Integer update(StStbprpB var1);

    Integer delete(String var1);

    StStbprpB selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StStbprpB var1);

    int updateByPrimaryKey(StStbprpB var1);

    Integer getCount(Map<String, Object> var1);

    List<StStbprpB> getList(Map<String, Object> var1);

    StStbprpB findById(String var1);

    AjaxJson delStbprpB(RequestContext var1, String var2);

    AjaxJson saveStbprpB(RequestContext var1, StStbprpB var2, StStsmtaskB var3);

    AjaxJson editStbprpB(RequestContext var1, StStbprpB var2, StStsmtaskB var3);

    List<StStbprpB> getListByType(Map<String, Object> var1);

    StStbprpB getByStcd(String var1);

    StStbprpB getByTel(String var1);

    ImportBean importStbprpB(HttpServletRequest var1, String var2, String var3);

    int getByAddvcdDIdCount(Map<String, Object> var1);

    List<StStbprpB> getStbprpBCollect(Map<String, Object> var1);

    List<StStbprpB> findMaxPj(Map<String, Object> var1);
}
