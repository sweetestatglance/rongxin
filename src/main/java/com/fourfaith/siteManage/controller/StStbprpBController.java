//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.reportManage.model.StDeviceFactor;
import com.fourfaith.reportManage.service.StDeviceFactorService;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.model.StModel;
import com.fourfaith.siteManage.model.StRsvrfcchB;
import com.fourfaith.siteManage.model.StRvfcchB;
import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.model.StStsmtaskB;
import com.fourfaith.siteManage.service.StAddvcdDService;
import com.fourfaith.siteManage.service.StModelService;
import com.fourfaith.siteManage.service.StRsvrfcchBService;
import com.fourfaith.siteManage.service.StRvfcchBService;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.siteManage.service.StStsmtaskBService;
import com.fourfaith.sysManage.model.SysEnterprise;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysEnterpriseService;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonMethod;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.FactorName;
import com.fourfaith.utils.ImportBean;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.PropertiesUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/stStbprpB"})
public class StStbprpBController {
    protected static final String indexJsp = "/page/stbprpb/index";
    protected static final String listJsp = "/page/stbprpb/list";
    protected static final String addJsp = "/page/stbprpb/add";
    protected static final String editJsp = "/page/stbprpb/edit";
    protected static final String moveJsp = "/page/stbprpb/move";
    protected static final String mapDialogJsp = "/page/stbprpb/mapDialog";
    protected static final String modelJsp = "/page/stbprpb/model";
    protected static final String configInfoJsp = "/page/stbprpb/task/config";
    protected static final String importJsp = "/page/stbprpb/importPage";
    @Autowired
    private StStbprpBService stStbprpBService;
    @Autowired
    private StAddvcdDService stAddvcdDService;
    @Autowired
    private SysEnterpriseService sysEnterpriseService;
    @Autowired
    private SysRoleAreaService sysRoleAreaService;
    @Autowired
    private StStsmtaskBService stStsmtaskBService;
    @Autowired
    private StRvfcchBService stRvfcchBService;
    @Autowired
    private StRsvrfcchBService stRsvrfcchBService;
    @Autowired
    private StModelService stModelService;
    @Autowired
    private StDeviceFactorService stDeviceFactorService;
    @Autowired
    private StFactorNameService stFactorNameService;
    public String logContent = "";

    public StStbprpBController() {
    }

    @RequestMapping({"index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/index");
        return mav;
    }

    @RequestMapping({"list"})
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/list");
        String addvcdId = request.getParameter("id");
        String stcd = request.getParameter("stcd");

        try {
            if (StringUtils.isNotBlank(stcd)) {
                stcd = URLDecoder.decode(stcd, "UTF-8");
                mav.addObject("stcd", stcd);
            } else {
                stcd = null;
                mav.addObject("stcd", (Object)null);
            }
        } catch (UnsupportedEncodingException var19) {
            var19.printStackTrace();
        }

        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> map = new HashMap();
        map.put("roleid", login_user.getSysrole().getId());
        List<String> addvcdDIdList = this.sysRoleAreaService.getAddvcdDIdList(map);
        List<String> aIdList = new ArrayList();
        List<StAddvcdD> staddvcdD = null;
        staddvcdD = CommonMethod.getChildList(this.stAddvcdDService, addvcdId);
        StAddvcdD addvcdD;
        String aId;
        if (staddvcdD != null && staddvcdD.size() > 0) {
            Iterator var11 = staddvcdD.iterator();

            while(var11.hasNext()) {
                addvcdD = (StAddvcdD)var11.next();
                aId = addvcdD.getId();
                if (login_user.getEnterManager()) {
                    aIdList.add(aId);
                } else if (addvcdDIdList.contains(aId)) {
                    aIdList.add(aId);
                }
            }
        }

        addvcdD = null;
        int count = 0;
        aId = request.getParameter("pageNo");
        Map<String, Object> params = new HashMap();
        params.put("stcd", stcd);
        List<StStbprpB> stbprpBList = null;
        PagingBean pagingBean;
        if ((addvcdDIdList == null || addvcdDIdList.size() <= 0) && !login_user.getEnterManager()) {
            pagingBean = PageUtil.page(aId, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        } else {
            if (aIdList != null && aIdList.size() > 0) {
                params.put("addvcdDId", aIdList);
            } else {
                params.put("addvcdDId", (Object)null);
            }

            count = this.stStbprpBService.getCount(params);
            pagingBean = PageUtil.page(aId, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
            params.put("pageStart", pagingBean.getStart());
            params.put("pageEnd", pagingBean.getLimit());
            stbprpBList = this.stStbprpBService.getList(params);
            Iterator var16 = stbprpBList.iterator();

            while(var16.hasNext()) {
                StStbprpB stStbprpB = (StStbprpB)var16.next();
                StAddvcdD stAddvcdD = this.stAddvcdDService.findById(stStbprpB.getFaddvcd());
                if (stAddvcdD != null) {
                    if ("0".equals(stAddvcdD.getFaddvcd())) {
                        stStbprpB.setAddvnm2(stStbprpB.getAddvnm1());
                    } else {
                        stStbprpB.setAddvnm2(stAddvcdD.getAddvnm());
                    }
                }
            }
        }

        String localName = this.getparentName(addvcdId);
        mav.addObject("localName", localName);
        mav.addObject("pagingBean", pagingBean);
        mav.addObject("stbprpBList", stbprpBList);
        Map<String, Object> param = new HashMap();
        param.put("enterpriseid", login_user.getEnterpriseid());
        int sbcount = this.stStbprpBService.getCount(param);
        param.put("dsfl", 1);
        int onlineCount = this.stStbprpBService.getCount(param);
        mav.addObject("sbcount", sbcount);
        mav.addObject("onlineCount", onlineCount);
        return mav;
    }

    public String getparentName(String addvcdId) {
        String localName = "";
        StAddvcdD addvcdD = this.stAddvcdDService.findById(addvcdId);
        if (addvcdD != null) {
            localName = this.getparentName(addvcdD.getFaddvcd());
            if (StringUtils.isNotBlank(localName)) {
                localName = localName + "-" + addvcdD.getAddvnm();
            } else {
                localName = addvcdD.getAddvnm();
            }
        }

        return localName;
    }

    @RequestMapping({"stbprpBCountResult"})
    @ResponseBody
    public String deviceCountResult(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        SysUser userInfo = (SysUser)CommonUtil.getLoginUserInfo(request);
        String enterId = userInfo.getEnterpriseid();
        Map<String, Object> params = new HashMap();
        params.put("enterpriseid", enterId);
        int count = 0;
        List<StStbprpB> stbprpBList = this.stStbprpBService.getList(params);
        if (stbprpBList != null && stbprpBList.size() > 0) {
            count = stbprpBList.size();
        }

        SysEnterprise enter = this.sysEnterpriseService.findById(enterId);
        int devicenum = enter.getEnterprisedevicenum();
        if (devicenum == 0) {
            ajaxJson.setSuccess(true);
        } else if (count >= devicenum) {
            ajaxJson.setMsg("" + devicenum);
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setSuccess(true);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"addPage"})
    public ModelAndView addPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/add");
        String addvcdId = request.getParameter("addvcdId");
        mav.addObject("addvcdId", addvcdId);
        SysUser userInfo = (SysUser)CommonUtil.getLoginUserInfo(request);
        mav.addObject("enterpriseid", userInfo.getEnterpriseid());
        Map<String, Object> params = new HashMap();
        params.put("enterpriseid", userInfo.getEnterpriseid());
        List<Map<String, FactorName>> factorList = new ArrayList();
        List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(userInfo.getEnterpriseid(), filterFactor, request);
        if (factorNameMap.size() > 0) {
            factorList.add(factorNameMap);
        }

        mav.addObject("factorList", factorList);
        return mav;
    }

    @RequestMapping({"logAddStbprpB"})
    @ResponseBody
    public String logAddStbprpB(HttpServletRequest request, StStbprpB stbprpB, StStsmtaskB stsmtaskB) {
        stbprpB.setModitime(new Date());
        RequestContext requestContext = new RequestContext(request);
        Object[] arg = null;
        this.logContent = "";
        if (stbprpB.getIscamera() == 0) {
            stbprpB.setCameratype(0);
            stbprpB.setVideochannel((String)null);
        }

        if (stbprpB.getCameratype() == null || stbprpB.getCameratype() == 1) {
            stbprpB.setDvraddr("");
            stbprpB.setDvrcode("");
        }

        stbprpB.setDsfl(0);
        AjaxJson ajaxJson = new AjaxJson();

        try {
            ajaxJson = this.stStbprpBService.saveStbprpB(requestContext, stbprpB, stsmtaskB);
            arg = new Object[]{ajaxJson.getMsg()};
            if (Integer.parseInt(ajaxJson.getObj().toString()) > 0) {
                ajaxJson.setMsg(requestContext.getMessage("operateSuccess"));
            } else {
                ajaxJson.setMsg(requestContext.getMessage("operateFailed"));
            }
        } catch (Exception var8) {
            var8.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setObj(requestContext.getMessage("operatDeviceException") + var8.getMessage());
            ajaxJson.setMsg(requestContext.getMessage("operatDeviceFailed"));
        }

        this.logContent = requestContext.getMessage("addStationData", arg);
        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"editPage"})
    public ModelAndView editPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/edit");
        String id = request.getParameter("id");
        StStbprpB stBprpB = this.stStbprpBService.findById(id);
        StAddvcdD addvcdD = this.stAddvcdDService.findById(stBprpB.getAddvcd());
        mav.addObject("addvcdName", addvcdD.getAddvnm());
        StStsmtaskB stsmtaskB = this.stStsmtaskBService.findById(stBprpB.getStcd());
        if (stBprpB.getSttp() != 0 && stBprpB.getSttp() != 3) {
            if (stBprpB.getSttp() == 1) {
                StRsvrfcchB rsvrfcchB = this.stRsvrfcchBService.findById(stBprpB.getStcd());
                if (rsvrfcchB != null) {
                    stBprpB.setNormz(rsvrfcchB.getNormz());
                    stBprpB.setDsflz(rsvrfcchB.getDsflz());
                    stBprpB.setDdz(rsvrfcchB.getDdz());
                    stBprpB.setDamel(rsvrfcchB.getDamel());
                }
            }
        } else {
            StRvfcchB rvfcchB = this.stRvfcchBService.findById(stBprpB.getStcd());
            if (rvfcchB != null) {
                stBprpB.setWrz(rvfcchB.getWrz());
                stBprpB.setGrz(rvfcchB.getGrz());
            }
        }

        String modelName = "";
        StModel model = this.stModelService.findById(stBprpB.getModel());
        if (model != null) {
            modelName = model.getName();
        }

        mav.addObject("stBprpB", stBprpB);
        mav.addObject("modelName", modelName);
        SysUser userInfo = (SysUser)CommonUtil.getLoginUserInfo(request);
        String enterId = userInfo.getEnterpriseid();
        mav.addObject("enterpriseid", enterId);
        Map<String, Object> factorMap = new HashMap();
        if (stsmtaskB != null) {
            factorMap.put("voltage", stsmtaskB.getVoltage());
            factorMap.put("signalinten", stsmtaskB.getSignalinten());
            factorMap.put("pn05", stsmtaskB.getPn05());
            factorMap.put("z", stsmtaskB.getZ());
            factorMap.put("zb", stsmtaskB.getZb());
            factorMap.put("zu", stsmtaskB.getZu());
            factorMap.put("vj", stsmtaskB.getVj());
            factorMap.put("va", stsmtaskB.getVa());
            factorMap.put("q", stsmtaskB.getQ());
            factorMap.put("qa", stsmtaskB.getQa());
            factorMap.put("ai", stsmtaskB.getAi());
            factorMap.put("c", stsmtaskB.getC());
            factorMap.put("mst", stsmtaskB.getMst());
            factorMap.put("fl", stsmtaskB.getFl());
            factorMap.put("uc", stsmtaskB.getUc());
            factorMap.put("us", stsmtaskB.getUs());
            factorMap.put("ue", stsmtaskB.getUe());
            factorMap.put("ej", stsmtaskB.getEj());
            factorMap.put("ed", stsmtaskB.getEd());
            factorMap.put("gtp", stsmtaskB.getGtp());
            factorMap.put("m10", stsmtaskB.getM10());
            factorMap.put("m20", stsmtaskB.getM20());
            factorMap.put("m30", stsmtaskB.getM30());
            factorMap.put("m40", stsmtaskB.getM40());
            factorMap.put("m50", stsmtaskB.getM50());
            factorMap.put("m60", stsmtaskB.getM60());
            factorMap.put("m80", stsmtaskB.getM80());
            factorMap.put("m100", stsmtaskB.getM100());
            factorMap.put("ph", stsmtaskB.getPh());
            factorMap.put("doxy", stsmtaskB.getDoxy());
            factorMap.put("cond", stsmtaskB.getCond());
            factorMap.put("turb", stsmtaskB.getTurb());
            factorMap.put("nh4n", stsmtaskB.getNh4n());
            factorMap.put("tp", stsmtaskB.getTp());
            factorMap.put("tn", stsmtaskB.getTn());
            factorMap.put("chla", stsmtaskB.getChla());
            factorMap.put("td11", stsmtaskB.getTd11());
            factorMap.put("td12", stsmtaskB.getTd12());
            factorMap.put("td13", stsmtaskB.getTd13());
            factorMap.put("td14", stsmtaskB.getTd14());
            factorMap.put("td15", stsmtaskB.getTd15());
            factorMap.put("td16", stsmtaskB.getTd16());
            factorMap.put("td17", stsmtaskB.getTd17());
            factorMap.put("td18", stsmtaskB.getTd18());
            factorMap.put("td19", stsmtaskB.getTd19());
            factorMap.put("td20", stsmtaskB.getTd20());
            factorMap.put("td21", stsmtaskB.getTd21());
            factorMap.put("td22", stsmtaskB.getTd22());
            factorMap.put("td23", stsmtaskB.getTd23());
            factorMap.put("td24", stsmtaskB.getTd24());
        }

        List<Map<String, FactorName>> factorList = new ArrayList();
        List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(enterId, filterFactor, request);
        factorList.add(factorNameMap);
        mav.addObject("factorList", factorList);
        mav.addObject("factorMap", factorMap);
        return mav;
    }

    @RequestMapping({"logEditStbprpB"})
    @ResponseBody
    public String logEditStbprpB(HttpServletRequest request, StStbprpB stbprpB, StStsmtaskB stsmtaskB) {
        AjaxJson ajaxJson = new AjaxJson();
        RequestContext requestContext = new RequestContext(request);
        Object[] arg = null;
        this.logContent = "";

        try {
            ajaxJson = this.stStbprpBService.editStbprpB(requestContext, stbprpB, stsmtaskB);
            arg = new Object[]{ajaxJson.getMsg()};
            if (Integer.parseInt(ajaxJson.getObj().toString()) > 0) {
                ajaxJson.setMsg(requestContext.getMessage("operateSuccess"));
            } else {
                ajaxJson.setMsg(requestContext.getMessage("operateFailed"));
            }
        } catch (Exception var8) {
            var8.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setObj(requestContext.getMessage("operatDeviceException") + var8.getMessage());
            ajaxJson.setMsg(requestContext.getMessage("operatDeviceFailed"));
        }

        this.logContent = requestContext.getMessage("editStationData", arg);
        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"logDelStbprpB"})
    @ResponseBody
    public String logDelStbprpB(HttpServletRequest request) {
        this.logContent = "";
        Object[] arg = null;
        RequestContext requestContext = new RequestContext(request);
        String ids = request.getParameter("ids");
        AjaxJson ajaxJson = new AjaxJson();

        try {
            ajaxJson = this.stStbprpBService.delStbprpB(requestContext, ids);
            arg = new Object[]{ajaxJson.getObj().toString()};
            ajaxJson.setMsg(requestContext.getMessage("operateSuccess"));
        } catch (Exception var7) {
            var7.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setObj(requestContext.getMessage("operatDeviceException") + var7.getMessage());
            ajaxJson.setMsg(requestContext.getMessage("operatDeviceFailed"));
        }

        this.logContent = requestContext.getMessage("deleteStationData", arg);
        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/movePage"})
    public ModelAndView movePage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/move");
        String ids = request.getParameter("dids");
        mav.addObject("dids", ids);
        return mav;
    }

    @RequestMapping({"/logMoveStbprpB"})
    @ResponseBody
    public String logMoveDevice(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        String ids = request.getParameter("ids");
        String addvcdId = request.getParameter("addvcdId");
        RequestContext requestContext = new RequestContext(request);
        this.logContent = "";

        try {
            if (ids != null) {
                String[] idArray = ids.split(",");

                for(int i = 0; i < idArray.length; ++i) {
                    StStbprpB stbprpB = this.stStbprpBService.findById(idArray[i]);
                    stbprpB.setAddvcd(addvcdId);
                    this.stStbprpBService.update(stbprpB);
                    Map<String, Object> params = new HashMap();
                    params.put("stcd", idArray[i]);
                    List<StDeviceFactor> deviceFactorList = this.stDeviceFactorService.getList(params);
                    if (deviceFactorList != null && deviceFactorList.size() > 0) {
                        Iterator var12 = deviceFactorList.iterator();

                        while(var12.hasNext()) {
                            StDeviceFactor sd = (StDeviceFactor)var12.next();
                            sd.setAddvcd(addvcdId);
                            this.stDeviceFactorService.updateByPrimaryKey(sd);
                        }
                    }

                    this.logContent = this.logContent + requestContext.getMessage("removeDeviceData") + ",";
                }

                ajaxJson.setMsg(requestContext.getMessage("mobileSuccess"));
                ajaxJson.setSuccess(true);
            }
        } catch (Exception var13) {
            var13.printStackTrace();
            ajaxJson.setMsg(requestContext.getMessage("operateFailedMsg") + var13.getMessage());
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/checkStcdExist"})
    @ResponseBody
    public String checkStcdExist(String stcd) {
        AjaxJson ajaxJson = new AjaxJson();
        StStbprpB stbprpB = this.stStbprpBService.getByStcd(stcd);
        if (stbprpB != null) {
            ajaxJson.setSuccess(true);
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/checkDeviceTelExist"})
    @ResponseBody
    public String checkDeviceTelExist(String deviceTel, String stcd) {
        AjaxJson ajaxJson = new AjaxJson();
        StStbprpB stbprpB = this.stStbprpBService.getByTel(deviceTel);
        if (stbprpB != null && !stbprpB.getStcd().equals(stcd)) {
            ajaxJson.setSuccess(true);
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"importStbprpBPage"})
    public ModelAndView importStbprpBPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/importPage");
        String addvcdId = request.getParameter("id");
        mav.addObject("addvcdId", addvcdId);
        return mav;
    }

    @RequestMapping({"/downLoadFile"})
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response) {
        Date date = new Date();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy");
        String year = sp.format(date);
        String basePath = PropertiesUtils.getPara("downFileDir");
        String savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
        String downName = "downMbName";
        RequestContext requestContext = new RequestContext(request);
        if (requestContext.getMessage("userLogin").equals("User login")) {
            downName = "dwonMbEnName";
        }

        String pathName = savePath + PropertiesUtils.getPara(downName);
        response.setContentType("multipart/form-data");
        String fname = year + PropertiesUtils.getPara(downName);
        String fileName = null;

        try {
            fileName = URLEncoder.encode(fname, "UTF-8");
            fileName = new String(fname.getBytes("GB2312"), "ISO_8859_1");
        } catch (UnsupportedEncodingException var18) {
            var18.printStackTrace();
        }

        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        File file = new File(pathName);

        try {
            FileInputStream inputStream = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            boolean var17 = true;

            int length;
            while((length = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }

            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException var19) {
            var19.printStackTrace();
        }

    }

    @RequestMapping({"/modelList"})
    public ModelAndView modelList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/model");
        String enterpriseid = request.getParameter("enterpriseid");
        mav.addObject("enterpriseid", enterpriseid);
        return mav;
    }

    @RequestMapping({"/mapDialog"})
    public ModelAndView mapDialog(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/mapDialog");
        String addvcdDId = request.getParameter("addvcdDId");
        if (StringUtils.isNotEmpty(addvcdDId)) {
            StAddvcdD addvcdD = this.stAddvcdDService.findById(addvcdDId);
            mav.addObject("addvcdName", addvcdD.getAddvnm());
            if (!addvcdD.getFaddvcd().equals("0")) {
                StAddvcdD parentAddvcdD = this.stAddvcdDService.findById(addvcdD.getFaddvcd());
                mav.addObject("parentAddvcdName", parentAddvcdD.getAddvnm());
            }
        }

        return mav;
    }

    @RequestMapping({"/configInfo"})
    public ModelAndView configInfo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/task/config");
        String stcd = request.getParameter("stcd");
        mav.addObject("stcd", stcd);
        return mav;
    }

    @RequestMapping({"/logStbprpBFileUpload"})
    @ResponseBody
    public String logStbprpBFileUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        this.logContent = null;
        AjaxJson ajax = new AjaxJson();
        String addvcdId = request.getParameter("addvcdId");
        String basePath = PropertiesUtils.getPara("upFileDir");
        long maxSize = 5242880L;
        String savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String sdate = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int)(random.nextDouble() * 90000.0D) + 10000;
        String str = sdate + rannum;
        String fName = "";
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            Iterator iter = multiRequest.getFileNames();

            while(iter.hasNext()) {
                MultipartFile file = multiRequest.getFile((String)iter.next());
                File saveDirFile = new File(savePath);
                if (!saveDirFile.exists()) {
                    saveDirFile.mkdirs();
                }

                if (file != null) {
                    long length = file.getSize();
                    String myFileName = file.getOriginalFilename();
                    String fileExt = myFileName.substring(myFileName.lastIndexOf(".") + 1).toLowerCase();
                    if (!fileExt.equals("xls")) {
                        ajax.setMsg("100080");
                        ajax.setSuccess(false);
                        return JSONObject.toJSONString(ajax);
                    }

                    if (maxSize < length) {
                        ajax.setMsg("100070");
                        ajax.setSuccess(false);
                        return JSONObject.toJSONString(ajax);
                    }

                    if (myFileName.trim() != "") {
                        String fileName = str + file.getOriginalFilename();
                        File localFile = new File(savePath, fileName);
                        file.transferTo(localFile);
                        fName = localFile.getPath();
                    }
                }
            }

            try {
                ImportBean imports = this.stStbprpBService.importStbprpB(request, fName, addvcdId);
                if (imports.getMsg().equals("100040")) {
                    this.logContent = imports.getLogContent();
                }

                ajax.setMsg(imports.getMsg());
            } catch (Exception var26) {
                String msg = var26.getMessage();
                String[] smsg = msg.split(",");
                ajax.setMsg(smsg[0]);
                ajax.setObj(smsg[1]);
            }
        }

        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"/stbprpBDownload"})
    public void stbprpBDownload(HttpServletRequest request, HttpServletResponse response) {
        String addvcdId = request.getParameter("addvcdId");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String sdate = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int)(random.nextDouble() * 90000.0D) + 10000;
        String str = sdate + rannum;
        SimpleDateFormat sp = new SimpleDateFormat("yyyy");
        String year = sp.format(date);
        str = str + year + PropertiesUtils.getPara("writeStbprpBName");
        String fileName = null;

        try {
            fileName = URLEncoder.encode(str, "UTF-8");
            fileName = new String(str.getBytes("GB2312"), "ISO_8859_1");
        } catch (UnsupportedEncodingException var19) {
            var19.printStackTrace();
        }

        String pathName = this.stbprpBWrite(request, addvcdId);
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        File file = new File(pathName);

        try {
            FileInputStream inputStream = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            boolean var18 = true;

            int length;
            while((length = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }

            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException var20) {
            var20.printStackTrace();
        }

    }

    public String stbprpBWrite(HttpServletRequest request, String addvcdId) {
        RequestContext requestContext = new RequestContext(request);
        SysUser loginUser = (SysUser)CommonUtil.getLoginUserInfo(request);
        String byenterpriseid = loginUser.getEnterpriseid();
        String basePath = PropertiesUtils.getPara("downFileDir");
        String savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String sdate = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int)(random.nextDouble() * 90000.0D) + 10000;
        String str = sdate + rannum;
        SimpleDateFormat sp = new SimpleDateFormat("yyyy");
        String year = sp.format(date);
        str = str + year + PropertiesUtils.getPara("writeStbprpBName");
        String fileName = savePath + str;
        WritableWorkbook wwb = null;

        try {
            wwb = Workbook.createWorkbook(new File(fileName));
        } catch (IOException var48) {
            var48.printStackTrace();
        }

        if (wwb != null) {
            WritableSheet ws = wwb.createSheet(requestContext.getMessage("deviceExport"), 0);

            Label addvcdD;
            try {
                Label title1 = new Label(0, 0, requestContext.getMessage("stationCode"));
                ws.addCell(title1);
                ws.setColumnView(0, 20);
                Label title2 = new Label(1, 0, requestContext.getMessage("stationName"));
                ws.addCell(title2);
                ws.setColumnView(1, 30);
                addvcdD = new Label(2, 0, requestContext.getMessage("siteType"));
                ws.addCell(addvcdD);
                ws.setColumnView(2, 15);
                Label title5 = new Label(3, 0, requestContext.getMessage("installationLocation"));
                ws.addCell(title5);
                ws.setColumnView(3, 55);
                Label title6 = new Label(4, 0, requestContext.getMessage("longitude"));
                ws.addCell(title6);
                ws.setColumnView(4, 20);
                Label title7 = new Label(5, 0, requestContext.getMessage("latitude"));
                ws.addCell(title7);
                ws.setColumnView(5, 20);
            } catch (RowsExceededException var46) {
                var46.printStackTrace();
            } catch (WriteException var47) {
                var47.printStackTrace();
            }

            new HashMap();
            SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
            addvcdD = null;
            List<StAddvcdD> addvcdDx = getChildList(this.stAddvcdDService, addvcdId);
            List<String> addvcdList = new ArrayList();
            Map<String, Object> map = new HashMap();
            map.put("roleid", login_user.getSysrole().getId());
            List<String> addvcdDIdList = this.sysRoleAreaService.getAddvcdDIdList(map);
            if (addvcdDx != null && addvcdDx.size() > 0) {
                Iterator var27 = addvcdDx.iterator();

                while(var27.hasNext()) {
                    StAddvcdD staddvcdD = (StAddvcdD)var27.next();
                    String gId = staddvcdD.getId();
                    if (login_user.getEnterManager()) {
                        addvcdList.add(gId);
                    } else if (addvcdDIdList.contains(gId)) {
                        addvcdList.add(gId);
                    }
                }

                int count = 0;
                List<StStbprpB> stbprpBList = null;
                Map<String, Object> params = new HashMap();
                if (addvcdDIdList != null && addvcdDIdList.size() > 0 || login_user.getEnterManager()) {
                    if (addvcdList != null && addvcdList.size() > 0) {
                        params.put("addvcdDId", addvcdList);
                    } else {
                        params.put("addvcdDId", (Object)null);
                    }

                    this.stStbprpBService.getByAddvcdDIdCount(params);
                    stbprpBList = this.stStbprpBService.getStbprpBCollect(params);
                }

                WritableFont bold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
                new WritableCellFormat(bold);
                if (stbprpBList != null && stbprpBList.size() > 0) {
                    for(int i = 0; i < stbprpBList.size(); ++i) {
                        for(int j = i; j <= i; ++j) {
                            String reportType = "";
                            StStsmtaskB smtask = this.stStsmtaskBService.findById(((StStbprpB)stbprpBList.get(i)).getStcd());
                            Label labelC0 = new Label(0, j + 1, ((StStbprpB)stbprpBList.get(i)).getStcd());
                            Label labelC1 = new Label(1, j + 1, ((StStbprpB)stbprpBList.get(i)).getStnm());
                            Label labelC2 = null;
                            switch(((StStbprpB)stbprpBList.get(i)).getSttp()) {
                                case 0:
                                    labelC2 = new Label(2, j + 1, requestContext.getMessage("sttp0"));
                                    break;
                                case 1:
                                    labelC2 = new Label(2, j + 1, requestContext.getMessage("sttp1"));
                                    break;
                                case 2:
                                    labelC2 = new Label(2, j + 1, requestContext.getMessage("sttp2"));
                                    break;
                                case 3:
                                    labelC2 = new Label(2, j + 1, requestContext.getMessage("sttp3"));
                                    break;
                                case 4:
                                    labelC2 = new Label(2, j + 1, requestContext.getMessage("sttp4"));
                                    break;
                                default:
                                    labelC2 = new Label(1, j + 1, "");
                            }

                            Label labelC3 = new Label(3, j + 1, ((StStbprpB)stbprpBList.get(i)).getStlc());
                            Label labelC4;
                            if (((StStbprpB)stbprpBList.get(i)).getLgtd() != null) {
                                labelC4 = new Label(4, j + 1, ((StStbprpB)stbprpBList.get(i)).getLgtd().toString());
                            } else {
                                labelC4 = new Label(4, j + 1, "");
                            }

                            Label labelC5;
                            if (((StStbprpB)stbprpBList.get(i)).getLttd() != null) {
                                labelC5 = new Label(5, j + 1, ((StStbprpB)stbprpBList.get(i)).getLttd().toString());
                            } else {
                                labelC5 = new Label(5, j + 1, "");
                            }

                            try {
                                ws.addCell(labelC0);
                                ws.addCell(labelC1);
                                ws.addCell(labelC2);
                                ws.addCell(labelC3);
                                ws.addCell(labelC4);
                                ws.addCell(labelC5);
                            } catch (RowsExceededException var44) {
                                var44.printStackTrace();
                            } catch (WriteException var45) {
                                var45.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        try {
            wwb.write();
            wwb.close();
        } catch (IOException var42) {
            var42.printStackTrace();
        } catch (WriteException var43) {
            var43.printStackTrace();
        }

        return fileName;
    }

    @RequestMapping({"/videoActiveX"})
    public void videoActiveX(HttpServletRequest request, HttpServletResponse response) {
        String basePath = PropertiesUtils.getPara("downFileDir");
        String savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
        String pathName = savePath + PropertiesUtils.getPara("videoActiveX");
        response.setContentType("multipart/form-data");
        String fname = PropertiesUtils.getPara("videoActiveX");
        String fileName = null;

        try {
            fileName = URLEncoder.encode(fname, "UTF-8");
            fileName = new String(fname.getBytes("GB2312"), "ISO_8859_1");
        } catch (UnsupportedEncodingException var13) {
            var13.printStackTrace();
        }

        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        File file = new File(pathName);

        try {
            FileInputStream inputStream = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            boolean var12 = true;

            int length;
            while((length = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }

            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException var14) {
            var14.printStackTrace();
        }

    }

    public static List<StAddvcdD> getChildList(StAddvcdDService stAddvcdDService, String addvcdId) {
        List<StAddvcdD> staddvcdDList = new ArrayList();
        List<StAddvcdD> list = stAddvcdDService.getChildAddvcdD(addvcdId);
        StAddvcdD addvcd;
        if (list != null && list.size() > 0) {
            staddvcdDList.addAll(list);
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                addvcd = (StAddvcdD)var5.next();
                int count = stAddvcdDService.isParent(addvcd.getId());
                if (count > 0) {
                    staddvcdDList.addAll(getChildList(stAddvcdDService, addvcd.getId()));
                }
            }
        } else {
            addvcd = stAddvcdDService.findById(addvcdId);
            staddvcdDList.add(addvcd);
        }

        return staddvcdDList;
    }
}
