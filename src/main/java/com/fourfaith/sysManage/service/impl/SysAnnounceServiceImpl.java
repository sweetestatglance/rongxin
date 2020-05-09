//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysAnnounceMapper;
import com.fourfaith.sysManage.model.SysAnnounce;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserAnnounce;
import com.fourfaith.sysManage.service.SysAnnounceService;
import com.fourfaith.sysManage.service.SysUserAnnounceService;
import com.fourfaith.sysManage.service.SysUserService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.RequestContext;

@Service("sysAnnounceService")
public class SysAnnounceServiceImpl implements SysAnnounceService {
    protected Logger logger = Logger.getLogger(SysAnnounceServiceImpl.class);
    @Autowired
    private SysAnnounceMapper sysAnnounceMapper;
    @Autowired
    private SysUserAnnounceService sysUserAnnounceService;
    @Autowired
    private SysUserService sysUserService;

    public SysAnnounceServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysAnnounceMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysAnnounce record) {
        int result = this.sysAnnounceMapper.insert(record);
        return result;
    }

    public int insertSelective(SysAnnounce record) {
        int result = this.sysAnnounceMapper.insertSelective(record);
        return result;
    }

    public SysAnnounce selectByPrimaryKey(String id) {
        SysAnnounce entity = this.sysAnnounceMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysAnnounce record) {
        int result = this.sysAnnounceMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysAnnounce record) {
        int result = this.sysAnnounceMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysAnnounceMapper.getCount(params);
        return result;
    }

    public List<SysAnnounce> getList(Map<String, Object> params) {
        return this.sysAnnounceMapper.getList(params);
    }

    public SysAnnounce findById(String Id) {
        return this.sysAnnounceMapper.selectByPrimaryKey(Id);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public AjaxJson saveAnnounce(SysAnnounce announce, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        RequestContext requestContext = new RequestContext(request);
        String logContent = "";
        Integer result = this.add(announce);
        String msg = null;
        if (result <= 0) {
            msg = requestContext.getMessage("operateFailed");
            throw new RuntimeException(requestContext.getMessage("operateFailed"));
        } else {
            msg = requestContext.getMessage("operateSuccess");
            HashMap params = new HashMap();
            params.put("enabledstate", 1);
            List userList = this.sysUserService.getList(params);
            if (userList != null && userList.size() > 0) {
                Iterator var11 = userList.iterator();

                while(var11.hasNext()) {
                    SysUser user = (SysUser)var11.next();
                    SysUserAnnounce userAnnounce = new SysUserAnnounce();
                    userAnnounce.setId(CommonUtil.getRandomUUID());
                    userAnnounce.setNoticeid(announce.getId());
                    userAnnounce.setModitime(new Date());
                    userAnnounce.setIsread(0);
                    userAnnounce.setUserid(user.getId());
                    if (this.sysUserAnnounceService.add(userAnnounce) <= 0) {
                        throw new RuntimeException(requestContext.getMessage("operateFailed"));
                    }
                }
            }

            logContent = requestContext.getMessage("noticeAddLog", new Object[]{"[" + announce.getTitle() + "]"});
            ajaxJson.setMsg(msg);
            ajaxJson.setSuccess(true);
            ajaxJson.setObj(logContent);
            return ajaxJson;
        }
    }

    public Integer add(SysAnnounce model) {
        return this.sysAnnounceMapper.insertSelective(model);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public AjaxJson deleteAnnounce(String items, HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajaxJson = new AjaxJson();
        String logContent = "";

        try {
            if (items != null) {
                String[] itemArray = items.split(",");
                String[] var10 = itemArray;
                int var9 = itemArray.length;

                for(int var8 = 0; var8 < var9; ++var8) {
                    String item = var10[var8];
                    SysAnnounce announce = this.findById(item);
                    if (this.sysUserAnnounceService.deleteByNoticeId(item) <= 0) {
                        throw new RuntimeException(requestContext.getMessage("operateFailed"));
                    }

                    if (this.delete(item) <= 0) {
                        throw new RuntimeException(requestContext.getMessage("operateFailed"));
                    }

                    logContent = logContent + requestContext.getMessage("noticeDeleteLog", new Object[]{"[" + announce.getTitle() + "]"}) + ",";
                }

                ajaxJson.setMsg(requestContext.getMessage("operateSuccess"));
                ajaxJson.setSuccess(true);
                ajaxJson.setObj(logContent);
            }
        } catch (Exception var13) {
            var13.printStackTrace();
            ajaxJson.setMsg(requestContext.getMessage("optFailExcept") + "：" + var13.getMessage());
            ajaxJson.setSuccess(false);
            ajaxJson.setObj(logContent);
        }

        return ajaxJson;
    }

    public Integer delete(String id) {
        return this.sysAnnounceMapper.deleteByPrimaryKey(id);
    }

    public List<SysAnnounce> getListByRead(Map<String, Object> param) {
        return this.sysAnnounceMapper.getListByRead(param);
    }

    public int getCountByRead(Map<String, Object> params) {
        return this.sysAnnounceMapper.getCountByRead(params);
    }
}
