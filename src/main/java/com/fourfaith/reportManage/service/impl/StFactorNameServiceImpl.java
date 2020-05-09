//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.service.impl;

import com.fourfaith.reportManage.dao.StFactorNameMapper;
import com.fourfaith.reportManage.model.StEnterFactor;
import com.fourfaith.reportManage.model.StFactorName;
import com.fourfaith.reportManage.service.StEnterFactorService;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.utils.FactorName;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContext;

@Service("stFactorNameService")
public class StFactorNameServiceImpl implements StFactorNameService {
    protected Logger logger = Logger.getLogger(StEnterFactorServiceImpl.class);
    @Autowired
    private StFactorNameMapper stFactorNameMapper;
    @Autowired
    private StEnterFactorService stEnterFactorService;

    public StFactorNameServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        return this.stFactorNameMapper.deleteByPrimaryKey(id);
    }

    public int insert(StFactorName record) {
        return this.stFactorNameMapper.insert(record);
    }

    public int insertSelective(StFactorName record) {
        return this.stFactorNameMapper.insertSelective(record);
    }

    public StFactorName selectByPrimaryKey(String id) {
        return this.stFactorNameMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(StFactorName record) {
        return this.stFactorNameMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(StFactorName record) {
        return this.stFactorNameMapper.updateByPrimaryKey(record);
    }

    public StFactorName getByEnterId(String enterId) {
        return this.stFactorNameMapper.getByEnterId(enterId);
    }

    public List<StFactorName> getList(Map<String, Object> params) {
        return this.stFactorNameMapper.getList(params);
    }

    public Map<String, FactorName> getFactorMap(String enterId, List<String> filterFactor, HttpServletRequest request) {
        Map<String, FactorName> map = new LinkedHashMap();
        StEnterFactor enterFactor = this.stEnterFactorService.getByEnterId(enterId);
        RequestContext requestContext = new RequestContext(request);
        StFactorName factorName = this.getByEnterId(enterId);
        FactorName[] var11;
        int var10 = (var11 = FactorName.values()).length;

        for(int var9 = 0; var9 < var10; ++var9) {
            FactorName e = var11[var9];
            if (!filterFactor.contains(e.getFlag()) && enterFactor != null) {
                try {
                    Field fields = null;
                    fields = StEnterFactor.class.getDeclaredField(e.getFlag());
                    fields.setAccessible(true);
                    int isView = (Integer)fields.get(enterFactor);
                    if (factorName != null) {
                        if (requestContext.getMessage("userLogin").equals("User login")) {
                            e.setName(e.getEnName());
                        } else {
                            e.setName(e.getName());
                        }

                        try {
                            Field field = StFactorName.class.getDeclaredField(e.getFlag().replace("td", "factorviewname"));
                            field.setAccessible(true);
                            String viewName = (String)field.get(factorName);
                            if (StringUtils.isNotBlank(viewName)) {
                                e.setName(viewName);
                            }

                            if (isView == 1) {
                                map.put(e.getFlag(), e);
                            }
                        } catch (Exception var16) {
                            if (isView == 1) {
                                map.put(e.getFlag(), e);
                            }
                        }
                    } else if (isView == 1) {
                        map.put(e.getFlag(), e);
                    }
                } catch (Exception var17) {
                    var17.printStackTrace();
                }
            }
        }

        return map;
    }

    public Map<String, FactorName> getAllFactorMap(String enterId, List<String> filterFactor) {
        Map<String, FactorName> map = new LinkedHashMap();
        StFactorName factorName = this.getByEnterId(enterId);
        FactorName[] var8;
        int var7 = (var8 = FactorName.values()).length;

        for(int var6 = 0; var6 < var7; ++var6) {
            FactorName e = var8[var6];
            if (!filterFactor.contains(e.getFlag()) && factorName != null) {
                try {
                    Field field = StFactorName.class.getDeclaredField(e.getFlag().replace("td", "factorviewname"));
                    field.setAccessible(true);
                    String viewName = (String)field.get(factorName);
                    if (StringUtils.isNotBlank(viewName)) {
                        e.setName(viewName);
                    } else {
                        e.setName(e.getName());
                    }

                    map.put(e.getFlag(), e);
                } catch (Exception var11) {
                    map.put(e.getFlag(), e);
                }
            }
        }

        return map;
    }
}
