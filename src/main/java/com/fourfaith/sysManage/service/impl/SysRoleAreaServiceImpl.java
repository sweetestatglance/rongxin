//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysRoleAreaMapper;
import com.fourfaith.sysManage.model.SysRoleArea;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysRoleAreaService")
public class SysRoleAreaServiceImpl implements SysRoleAreaService {
    protected Logger logger = Logger.getLogger(SysRoleAreaServiceImpl.class);
    @Autowired
    private SysRoleAreaMapper sysRoleAreaMapper;

    public SysRoleAreaServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysRoleAreaMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysRoleArea record) {
        int result = this.sysRoleAreaMapper.insert(record);
        return result;
    }

    public int insertSelective(SysRoleArea record) {
        int result = this.sysRoleAreaMapper.insertSelective(record);
        return result;
    }

    public SysRoleArea selectByPrimaryKey(String id) {
        SysRoleArea entity = this.sysRoleAreaMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysRoleArea record) {
        int result = this.sysRoleAreaMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysRoleArea record) {
        int result = this.sysRoleAreaMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysRoleAreaMapper.getCount(params);
        return result;
    }

    public List<SysRoleArea> getList(Map<String, Object> params) {
        return this.sysRoleAreaMapper.getList(params);
    }

    public SysRoleArea findById(String Id) {
        return this.sysRoleAreaMapper.findById(Id);
    }

    public List<SysRoleArea> getListByRoleId(String roleId) {
        Map<String, Object> params = new HashMap();
        params.put("roleid", roleId);
        return this.sysRoleAreaMapper.getList(params);
    }

    public String deleteRoleId(String roleid) {
        String msg = null;

        try {
            int result = this.sysRoleAreaMapper.deleteRoleId(roleid);
            if (result > 0) {
                msg = "删除成功";
            } else {
                msg = "删除失败";
            }

            this.logger.info(msg);
        } catch (Exception var4) {
            var4.printStackTrace();
            msg = "删除失败";
            this.logger.error(msg + var4.getMessage());
        }

        return msg;
    }

    public SysRoleArea findByAddvcdIdAndRoleId(String roleId, String addvcdId) {
        SysRoleArea sysRoleArea = new SysRoleArea();
        Map<String, Object> params = new HashMap();
        params.put("addvcd", addvcdId);
        params.put("roleid", roleId);
        List<SysRoleArea> list = this.getList(params);
        if (list != null && list.size() > 0) {
            sysRoleArea = (SysRoleArea)list.get(0);
        }

        return sysRoleArea;
    }

    public String add(SysRoleArea model) {
        String msg = null;

        try {
            int result = this.sysRoleAreaMapper.insertSelective(model);
            if (result > 0) {
                msg = "添加成功";
            } else {
                msg = "添加失败";
            }

            this.logger.info(msg);
        } catch (Exception var4) {
            var4.printStackTrace();
            msg = "添加失败";
            this.logger.error(msg + var4.getMessage());
        }

        return msg;
    }

    public String deleteAddvcdD(String addvcd) {
        String msg = null;

        try {
            int result = this.sysRoleAreaMapper.deleteAddvcdD(addvcd);
            if (result > 0) {
                msg = "删除成功";
            } else {
                msg = "删除失败";
            }

            this.logger.info(msg);
        } catch (Exception var4) {
            var4.printStackTrace();
            msg = "删除失败";
            this.logger.error(msg + var4.getMessage());
        }

        return msg;
    }

    public List<String> getAddvcdDIdList(Map<String, Object> params) {
        List<String> addvcdDIdList = new ArrayList();
        List<SysRoleArea> roleAreaList = this.getList(params);
        if (roleAreaList != null && roleAreaList.size() > 0) {
            Iterator var5 = roleAreaList.iterator();

            while(var5.hasNext()) {
                SysRoleArea sysRoleArea = (SysRoleArea)var5.next();
                addvcdDIdList.add(sysRoleArea.getAddvcdid());
            }
        }

        return addvcdDIdList;
    }
}
