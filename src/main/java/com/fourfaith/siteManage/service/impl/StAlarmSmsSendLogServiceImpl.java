//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.siteManage.dao.StAlarmSmsSendLogMapper;
import com.fourfaith.siteManage.model.StAlarmSmsSendLog;
import com.fourfaith.siteManage.service.StAlarmSmsSendLogService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stAlarmSmsSendLogService")
public class StAlarmSmsSendLogServiceImpl implements StAlarmSmsSendLogService {
    protected Logger logger = Logger.getLogger(StStbprpBServiceImpl.class);
    @Autowired
    private StAlarmSmsSendLogMapper stAlarmSmsSendLogMapper;

    public StAlarmSmsSendLogServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        return this.stAlarmSmsSendLogMapper.deleteByPrimaryKey(id);
    }

    public int insert(StAlarmSmsSendLog record) {
        return this.stAlarmSmsSendLogMapper.insert(record);
    }

    public int insertSelective(StAlarmSmsSendLog record) {
        return this.stAlarmSmsSendLogMapper.insertSelective(record);
    }

    public StAlarmSmsSendLog selectByPrimaryKey(String id) {
        return this.stAlarmSmsSendLogMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(StAlarmSmsSendLog record) {
        return this.stAlarmSmsSendLogMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(StAlarmSmsSendLog record) {
        return this.stAlarmSmsSendLogMapper.updateByPrimaryKey(record);
    }

    public List<StAlarmSmsSendLog> getList(Map<String, Object> params) {
        return this.stAlarmSmsSendLogMapper.getList(params);
    }

    public int getCount(Map<String, Object> params) {
        return this.stAlarmSmsSendLogMapper.getCount(params);
    }

}
