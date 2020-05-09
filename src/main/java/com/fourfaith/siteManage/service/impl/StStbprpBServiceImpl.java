//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.reportManage.model.StDeviceFactor;
import com.fourfaith.reportManage.service.StDeviceFactorService;
import com.fourfaith.siteManage.dao.StStbprpBMapper;
import com.fourfaith.siteManage.model.StRsvrfcchB;
import com.fourfaith.siteManage.model.StRvfcchB;
import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.model.StStsmtaskB;
import com.fourfaith.siteManage.service.StRsvrfcchBService;
import com.fourfaith.siteManage.service.StRvfcchBService;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.siteManage.service.StStsmtaskBService;
import com.fourfaith.sysManage.model.SysEnterprise;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysEnterpriseService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.BeanUtils;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.ImportBean;
import com.fourfaith.utils.ValidateRegular;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.RequestContext;

@Service("stStbprpBService")
public class StStbprpBServiceImpl implements StStbprpBService {
    protected Logger logger = Logger.getLogger(StStbprpBServiceImpl.class);
    @Autowired
    private StStbprpBMapper stStbprpBMapper;
    @Autowired
    private StStsmtaskBService stStsmtaskBService;
    @Autowired
    private StRvfcchBService stRvfcchBService;
    @Autowired
    private StRsvrfcchBService stRsvrfcchBService;
    @Autowired
    private StDeviceFactorService stDeviceFactorService;
    @Autowired
    private SysEnterpriseService sysEnterpriseService;

    public StStbprpBServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.stStbprpBMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(StStbprpB record) {
        int result = this.stStbprpBMapper.insert(record);
        return result;
    }

    public int insertSelective(StStbprpB record) {
        int result = this.stStbprpBMapper.insertSelective(record);
        return result;
    }

    public StStbprpB selectByPrimaryKey(String id) {
        StStbprpB entity = this.stStbprpBMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(StStbprpB record) {
        int result = this.stStbprpBMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(StStbprpB record) {
        int result = this.stStbprpBMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.stStbprpBMapper.getCount(params);
        return result;
    }

    public List<StStbprpB> getList(Map<String, Object> params) {
        return this.stStbprpBMapper.getList(params);
    }

    public StStbprpB findById(String Id) {
        return this.stStbprpBMapper.selectByPrimaryKey(Id);
    }

    public Integer add(StStbprpB model) {
        return this.stStbprpBMapper.insertSelective(model);
    }

    public Integer update(StStbprpB model) {
        return this.stStbprpBMapper.updateByPrimaryKeySelective(model);
    }

    public Integer delete(String id) {
        return this.stStbprpBMapper.deleteByPrimaryKey(id);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public AjaxJson saveStbprpB(RequestContext requestContext, StStbprpB stbprpB, StStsmtaskB stsmtaskB) {
        AjaxJson ajaxJson = new AjaxJson();
        String logContent = "";
        Integer flag = this.add(stbprpB);
        if (flag <= 0) {
            throw new RuntimeException(requestContext.getMessage("addDeviceFailed"));
        } else {
            stsmtaskB.setStcd(stbprpB.getStcd());
            stsmtaskB.setModitime(new Date());
            Integer smTaskResult = this.stStsmtaskBService.add(stsmtaskB);
            if (smTaskResult <= 0) {
                throw new RuntimeException(requestContext.getMessage("addDevice") + "---" + requestContext.getMessage("addTaskError"));
            } else {
                Integer result;
                if (stbprpB.getSttp() != 0 && stbprpB.getSttp() != 3) {
                    if (stbprpB.getSttp() == 1) {
                        StRsvrfcchB rsvrfcchB = new StRsvrfcchB();
                        rsvrfcchB.setStcd(stbprpB.getStcd());
                        rsvrfcchB.setNormz(stbprpB.getNormz());
                        rsvrfcchB.setDsflz(stbprpB.getDsflz());
                        rsvrfcchB.setDdz(stbprpB.getDdz());
                        rsvrfcchB.setDamel(stbprpB.getDamel());
                        rsvrfcchB.setModitime(new Date());
                        result = this.stRsvrfcchBService.add(rsvrfcchB);
                        if (result <= 0) {
                            throw new RuntimeException(requestContext.getMessage("addDevice") + "---" + requestContext.getMessage("addLakeError"));
                        }
                    }
                } else {
                    StRvfcchB rvfchhB = new StRvfcchB();
                    rvfchhB.setStcd(stbprpB.getStcd());
                    rvfchhB.setWrz(stbprpB.getWrz());
                    rvfchhB.setGrz(stbprpB.getGrz());
                    rvfchhB.setModitime(new Date());
                    result = this.stRvfcchBService.add(rvfchhB);
                    if (result <= 0) {
                        throw new RuntimeException(requestContext.getMessage("addDevice") + "---" + requestContext.getMessage("addRiverError"));
                    }
                }

                logContent = stbprpB.getStcd();
                ajaxJson.setMsg(logContent);
                ajaxJson.setSuccess(true);
                ajaxJson.setObj(flag);
                return ajaxJson;
            }
        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public AjaxJson editStbprpB(RequestContext requestContext, StStbprpB stbprpB, StStsmtaskB stsmtaskB) {
        AjaxJson ajaxJson = new AjaxJson();
        String logContent = "";
        StStbprpB stBprpB = this.findById(stbprpB.getStcd());
        stBprpB.setModitime(new Date());

        try {
            BeanUtils.copyPropertiesExcludeNull(stbprpB, stBprpB);
        } catch (IllegalAccessException var14) {
            var14.printStackTrace();
        } catch (InvocationTargetException var15) {
            var15.printStackTrace();
        }

        if (stBprpB.getIscamera() == 0) {
            stBprpB.setCameratype(0);
        }

        if (stBprpB.getCameratype() == 0 || stBprpB.getCameratype() == 1) {
            stBprpB.setDvraddr("");
            stBprpB.setDvrcode("");
        }

        Integer msg = this.update(stBprpB);
        logContent = stBprpB.getStcd();
        StStsmtaskB smtaskB = this.stStsmtaskBService.findById(stbprpB.getStcd());
        Integer updateResult;
        if (smtaskB != null) {
            try {
                BeanUtils.copyPropertiesExcludeNull(stsmtaskB, smtaskB);
            } catch (IllegalAccessException var12) {
                var12.printStackTrace();
            } catch (InvocationTargetException var13) {
                var13.printStackTrace();
            }

            smtaskB.setStcd(stbprpB.getStcd());
            smtaskB.setModitime(new Date());
            updateResult = this.stStsmtaskBService.update(smtaskB);
            if (updateResult <= 0) {
                throw new RuntimeException(requestContext.getMessage("updateFailed") + "---" + requestContext.getMessage("updateTaskError"));
            }
        } else {
            stsmtaskB.setStcd(stbprpB.getStcd());
            stsmtaskB.setModitime(new Date());
            updateResult = this.stStsmtaskBService.add(stsmtaskB);
            if (updateResult <= 0) {
                throw new RuntimeException(requestContext.getMessage("updateFailed") + "---" + requestContext.getMessage("addTaskError"));
            }
        }

        Integer result;
        if (stBprpB.getSttp() != 0 && stbprpB.getSttp() != 3) {
            if (stBprpB.getSttp() == 1) {
                StRsvrfcchB rsvrfcchB = this.stRsvrfcchBService.findById(stBprpB.getStcd());
                if (rsvrfcchB != null) {
                    rsvrfcchB.setStcd(stbprpB.getStcd());
                    rsvrfcchB.setNormz(stbprpB.getNormz());
                    rsvrfcchB.setDsflz(stbprpB.getDsflz());
                    rsvrfcchB.setDdz(stbprpB.getDdz());
                    rsvrfcchB.setDamel(stbprpB.getDamel());
                    rsvrfcchB.setModitime(new Date());
                    result = this.stRsvrfcchBService.update(rsvrfcchB);
                    if (result <= 0) {
                        throw new RuntimeException(requestContext.getMessage("updateEquipment") + "---" + requestContext.getMessage("updateLakeError"));
                    }
                } else {
                    StRsvrfcchB strsvrfcchB = new StRsvrfcchB();
                    strsvrfcchB.setStcd(stBprpB.getStcd());
                    strsvrfcchB.setNormz(stbprpB.getNormz());
                    strsvrfcchB.setDsflz(stbprpB.getDsflz());
                    strsvrfcchB.setDdz(stbprpB.getDdz());
                    strsvrfcchB.setDamel(stbprpB.getDamel());
                    strsvrfcchB.setModitime(new Date());
                    result = this.stRsvrfcchBService.add(strsvrfcchB);
                    if (result <= 0) {
                        throw new RuntimeException(requestContext.getMessage("updateEquipment") + "---" + requestContext.getMessage("addLakeError"));
                    }
                }
            }
        } else {
            StRvfcchB rvfcchB = this.stRvfcchBService.findById(stBprpB.getStcd());
            if (rvfcchB != null) {
                rvfcchB.setWrz(stBprpB.getWrz());
                rvfcchB.setGrz(stBprpB.getGrz());
                rvfcchB.setModitime(new Date());
                result = this.stRvfcchBService.update(rvfcchB);
                if (result <= 0) {
                    throw new RuntimeException(requestContext.getMessage("updateEquipment") + "---" + requestContext.getMessage("updateRiverError"));
                }
            } else {
                StRvfcchB strvfcchB = new StRvfcchB();
                strvfcchB.setStcd(stbprpB.getStcd());
                strvfcchB.setWrz(stbprpB.getWrz());
                strvfcchB.setGrz(stbprpB.getGrz());
                strvfcchB.setModitime(new Date());
                result = this.stRvfcchBService.add(strvfcchB);
                if (result <= 0) {
                    throw new RuntimeException(requestContext.getMessage("updateEquipment") + "---" + requestContext.getMessage("addRiverError"));
                }
            }
        }

        ajaxJson.setSuccess(true);
        ajaxJson.setMsg(logContent);
        ajaxJson.setObj(msg);
        return ajaxJson;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public AjaxJson delStbprpB(RequestContext requestContext, String ids) {
        AjaxJson ajaxJson = new AjaxJson();
        String logContent = "";
        if (ids != null) {
            String[] idArray = ids.split(",");

            for(int i = 0; i < idArray.length; ++i) {
                StStbprpB stbprpB = this.findById(idArray[i]);
                logContent = logContent + stbprpB.getStcd() + ",";
                Integer delResult = this.stStsmtaskBService.delete(idArray[i]);
                if (delResult <= 0) {
                    throw new RuntimeException(requestContext.getMessage("deleteDevice") + "---" + requestContext.getMessage("deleteTaskError"));
                }

                Integer result;
                if (stbprpB.getSttp() != 0 && stbprpB.getSttp() != 3) {
                    if (stbprpB.getSttp() == 1) {
                        StRsvrfcchB stRsvrfcchB = this.stRsvrfcchBService.findByStcd(idArray[i]);
                        if (stRsvrfcchB != null) {
                            result = this.stRsvrfcchBService.delete(idArray[i]);
                            if (result <= 0) {
                                throw new RuntimeException(requestContext.getMessage("deleteDevice") + "---" + requestContext.getMessage("deleteReserVoirTable"));
                            }
                        }
                    }
                } else {
                    StRvfcchB stRvfcchB = this.stRvfcchBService.findByStcd(idArray[i]);
                    if (stRvfcchB != null) {
                        result = this.stRvfcchBService.delete(idArray[i]);
                        if (result <= 0) {
                            throw new RuntimeException(requestContext.getMessage("deleteDevice") + "---" + requestContext.getMessage("deleteRiverError"));
                        }
                    }
                }

                StDeviceFactor deviceFactors = this.stDeviceFactorService.findByStcd(idArray[i]);
                if (deviceFactors != null) {
                    String factorResult = this.stDeviceFactorService.deleteStcd(idArray[i]);
                    if (factorResult.equals(requestContext.getMessage("deleteFailed"))) {
                        throw new RuntimeException(requestContext.getMessage("deleteDevice") + "---" + requestContext.getMessage("deleteFactorError"));
                    }
                }

                this.delete(idArray[i]);
            }

            ajaxJson.setSuccess(true);
            ajaxJson.setObj(logContent);
        }

        return ajaxJson;
    }

    public List<StStbprpB> getListByType(Map<String, Object> params) {
        return this.stStbprpBMapper.getListByType(params);
    }

    public StStbprpB getByStcd(String stcd) {
        return this.stStbprpBMapper.getByStcd(stcd);
    }

    public StStbprpB getByTel(String tel) {
        return this.stStbprpBMapper.getByTel(tel);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public ImportBean importStbprpB(HttpServletRequest request, String fileName, String addvcdId) {
        ImportBean imports = new ImportBean();
        List list = new ArrayList();
        String logContent = "";
        String msg = "";
        Workbook wb = null;
        Cell cell = null;

        try {
            wb = Workbook.getWorkbook(new File(fileName));
        } catch (BiffException var29) {
            var29.printStackTrace();
        } catch (IOException var30) {
            var30.printStackTrace();
        }

        if (wb == null) {
            return null;
        } else {
            Sheet sheet = wb.getSheet(0);

            String[] str;
            for(int i = 0; i < sheet.getRows(); ++i) {
                str = new String[sheet.getColumns()];
                int count = 0;

                for(int j = 0; j < sheet.getColumns(); ++j) {
                    cell = sheet.getCell(j, i);
                    if (cell != null) {
                        str[j] = cell.getContents();
                        if (StringUtils.isEmpty(str[j])) {
                            ++count;
                        }
                    }
                }

                if (count < 6) {
                    list.add(str);
                }
            }

            RequestContext requestContext = new RequestContext(request);
            str = null;
            SysUser userInfo = (SysUser)CommonUtil.getLoginUserInfo(request);
            String enterId = userInfo.getEnterpriseid();
            SysEnterprise enter = this.sysEnterpriseService.findById(enterId);
            int devicenum = enter.getEnterprisedevicenum();
            if (list != null && list.size() > 0) {
                if (sheet.getColumns() != 6) {
                    throw new IllegalArgumentException("100010,1");
                } else if (list.size() == 1) {
                    throw new IllegalArgumentException("100020,1");
                } else {
                    for(int i = 1; i < list.size(); ++i) {
                        Object[] obj = (Object[])list.get(i);
                        if (obj != null && obj.length > 0) {
                            if (obj[0] != null && obj[0] != "") {
                                if (!ValidateRegular.isNumber(obj[0].toString())) {
                                    msg = requestContext.getMessage("must10Digits");
                                }

                                if (obj[1] == null || obj[1] == "") {
                                    msg = requestContext.getMessage("cannotStaNameEmpty");
                                }

                                if (obj[2] != null && obj[2] != "") {
                                    String sttp = obj[2].toString();
                                    if (!sttp.equals(requestContext.getMessage("sttp2")) && !sttp.equals(requestContext.getMessage("sttp0")) && !sttp.equals(requestContext.getMessage("sttp1")) && !sttp.equals(requestContext.getMessage("sttp3")) && !sttp.equals(requestContext.getMessage("sttp4"))) {
                                        msg = requestContext.getMessage("staClassNotStand");
                                    }
                                } else {
                                    msg = requestContext.getMessage("cannotStaClassEmpty");
                                }

                                if (msg != "") {
                                    throw new IllegalArgumentException("100030,1");
                                }

                                Map<String, Object> params = new HashMap();
                                params.put("enterpriseid", enterId);
                                int count = 0;
                                List<StStbprpB> stbprpBList = this.getList(params);
                                if (stbprpBList != null && stbprpBList.size() > 0) {
                                    count = stbprpBList.size();
                                }

                                if (devicenum != 0 && count >= devicenum) {
                                    throw new IllegalArgumentException("100090," + devicenum);
                                }

                                StStbprpB bprpB = this.getByStcd(obj[0].toString());
                                if (bprpB != null) {
                                    throw new IllegalArgumentException("100050," + i);
                                }

                                StStbprpB stbprpB = new StStbprpB();
                                stbprpB.setStcd(obj[0].toString());
                                stbprpB.setStnm(obj[1].toString());
                                if (obj[2] != null) {
                                    String sttpName = obj[2].toString();
                                    if (sttpName.equals(requestContext.getMessage("sttp2"))) {
                                        stbprpB.setSttp(2);
                                    } else if (sttpName.equals(requestContext.getMessage("sttp0"))) {
                                        stbprpB.setSttp(0);
                                    } else if (sttpName.equals(requestContext.getMessage("sttp1"))) {
                                        stbprpB.setSttp(1);
                                    } else if (sttpName.equals(requestContext.getMessage("sttp3"))) {
                                        stbprpB.setSttp(3);
                                    } else if (sttpName.equals(requestContext.getMessage("sttp4"))) {
                                        stbprpB.setSttp(4);
                                    }
                                }

                                stbprpB.setStlc(obj[3].toString());
                                stbprpB.setAddvcd(addvcdId);
                                stbprpB.setEnterpriseid(enterId);
                                stbprpB.setModitime(new Date());
                                stbprpB.setPwd(40960);
                                stbprpB.setIscamera(0);
                                stbprpB.setDsfl(0);
                                if (StringUtils.isNotEmpty(obj[4].toString())) {
                                    stbprpB.setLgtd(BigDecimal.valueOf(Double.parseDouble(obj[4].toString())));
                                }

                                if (StringUtils.isNotEmpty(obj[5].toString())) {
                                    stbprpB.setLttd(BigDecimal.valueOf(Double.parseDouble(obj[5].toString())));
                                }

                                StStsmtaskB smtaskB = new StStsmtaskB();
                                smtaskB.setStcd(obj[0].toString());
                                smtaskB.setModitime(new Date());
                                Integer stbprpbMsg = this.add(stbprpB);
                                if (stbprpbMsg <= 0) {
                                    throw new IllegalArgumentException("110000,1");
                                }

                                Integer stsmtaskBMsg = this.stStsmtaskBService.add(smtaskB);
                                if (stsmtaskBMsg <= 0) {
                                    throw new IllegalArgumentException("110000,1");
                                }

                                Integer result;
                                if (stbprpB.getSttp() != 0 && stbprpB.getSttp() != 3) {
                                    if (stbprpB.getSttp() == 1) {
                                        StRsvrfcchB rsvrfcchB = new StRsvrfcchB();
                                        rsvrfcchB.setStcd(stbprpB.getStcd());
                                        rsvrfcchB.setNormz(stbprpB.getNormz());
                                        rsvrfcchB.setDsflz(stbprpB.getDsflz());
                                        rsvrfcchB.setDdz(stbprpB.getDdz());
                                        rsvrfcchB.setDamel(stbprpB.getDamel());
                                        rsvrfcchB.setModitime(new Date());
                                        result = this.stRsvrfcchBService.add(rsvrfcchB);
                                        if (result <= 0) {
                                            throw new IllegalArgumentException("110000,1");
                                        }
                                    }
                                } else {
                                    StRvfcchB rvfchhB = new StRvfcchB();
                                    rvfchhB.setStcd(stbprpB.getStcd());
                                    rvfchhB.setWrz(stbprpB.getWrz());
                                    rvfchhB.setGrz(stbprpB.getGrz());
                                    rvfchhB.setModitime(new Date());
                                    result = this.stRvfcchBService.add(rvfchhB);
                                    if (result <= 0) {
                                        throw new IllegalArgumentException("110000,1");
                                    }
                                }

                                Object[] arg = new Object[]{stbprpB.getStcd()};
                                logContent = logContent + requestContext.getMessage("importStationData", arg) + ",";
                            } else {
                                msg = requestContext.getMessage("cannotStaCodeEmpty");
                            }
                        }
                    }

                    wb.close();
                    msg = "100040";
                    imports.setLogContent(logContent);
                    imports.setMsg(msg);
                    return imports;
                }
            } else {
                throw new IllegalArgumentException("100020,1");
            }
        }
    }

    public int getByAddvcdDIdCount(Map<String, Object> params) {
        return this.stStbprpBMapper.getByAddvcdDIdCount(params);
    }

    public List<StStbprpB> getStbprpBCollect(Map<String, Object> params) {
        return this.stStbprpBMapper.getStbprpBCollect(params);
    }

    public List<StStbprpB> findMaxPj(Map<String, Object> map) {
        return this.stStbprpBMapper.findMaxPj(map);
    }
}
