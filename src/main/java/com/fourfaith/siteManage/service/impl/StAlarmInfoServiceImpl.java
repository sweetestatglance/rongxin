//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.siteManage.dao.StAlarmInfoMapper;
import com.fourfaith.siteManage.model.StAlarmInfo;
import com.fourfaith.siteManage.service.StAlarmInfoService;
import com.fourfaith.utils.AjaxJson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.RequestContext;

@Service("stAlarmInfoService")
public class StAlarmInfoServiceImpl implements StAlarmInfoService {
    protected Logger logger = Logger.getLogger(StAddvcdDServiceImpl.class);
    @Autowired
    private StAlarmInfoMapper stAlarmInfoMapper;

    public StAlarmInfoServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        return this.stAlarmInfoMapper.deleteByPrimaryKey(id);
    }

    public int insert(StAlarmInfo record) {
        return this.stAlarmInfoMapper.insert(record);
    }

    public int insertSelective(StAlarmInfo record) {
        return this.stAlarmInfoMapper.insertSelective(record);
    }

    public StAlarmInfo selectByPrimaryKey(String id) {
        return this.stAlarmInfoMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(StAlarmInfo record) {
        return this.stAlarmInfoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(StAlarmInfo record) {
        return this.stAlarmInfoMapper.updateByPrimaryKey(record);
    }

    public Integer getCount(Map<String, Object> params) {
        return this.stAlarmInfoMapper.getCount(params);
    }

    public List<StAlarmInfo> getAlarmList(Map<String, Object> params) {
        return this.stAlarmInfoMapper.getAlarmList(params);
    }

    public Integer getTodayCount(Map<String, Object> params) {
        return this.stAlarmInfoMapper.getTodayCount(params);
    }

    public List<StAlarmInfo> getSevenALarmList(Map<String, Object> params) {
        return this.stAlarmInfoMapper.getSevenALarmList(params);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public AjaxJson editSettled(String ids, HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(false);
        ajaxJson.setMsg(requestContext.getMessage("operateFailed"));
        String[] idArray = ids.split(",");
        if (idArray.length > 0) {
            for(int j = 0; j < idArray.length; ++j) {
                Map<String, Object> param = new HashMap();
                param.put("hasSolved", 1);
                param.put("id", idArray[j]);
                int result = this.updateHasSolved(param);
                if (result <= 0) {
                    throw new RuntimeException(requestContext.getMessage("operateSuccess"));
                }
            }

            ajaxJson.setSuccess(true);
            ajaxJson.setMsg(requestContext.getMessage("operateSuccess"));
        }

        return ajaxJson;
    }

    public int updateHasSolved(Map<String, Object> params) {
        return this.stAlarmInfoMapper.updateHasSolved(params);
    }
}
