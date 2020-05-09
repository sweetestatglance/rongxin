//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysNoticeMapper;
import com.fourfaith.sysManage.model.SysNotice;
import com.fourfaith.sysManage.service.SysNoticeService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysNoticeService")
public class SysNoticeServiceImpl implements SysNoticeService {
    protected Logger logger = Logger.getLogger(SysNoticeServiceImpl.class);
    @Autowired
    private SysNoticeMapper sysNoticeMapper;

    public SysNoticeServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        return this.sysNoticeMapper.deleteByPrimaryKey(id);
    }

    public int insert(SysNotice record) {
        return this.sysNoticeMapper.insert(record);
    }

    public int insertSelective(SysNotice record) {
        return this.sysNoticeMapper.insertSelective(record);
    }

    public SysNotice selectByPrimaryKey(String id) {
        return this.sysNoticeMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysNotice record) {
        return this.sysNoticeMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysNotice record) {
        return this.sysNoticeMapper.updateByPrimaryKey(record);
    }

    public List<SysNotice> getList(Map<String, Object> params) {
        return this.sysNoticeMapper.getList(params);
    }

    public int deleteNotice(Integer type) {
        return this.sysNoticeMapper.deleteNotice(type);
    }
}
