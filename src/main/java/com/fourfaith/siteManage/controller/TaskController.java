//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.siteManage.model.DevicePhoto;
import com.fourfaith.siteManage.model.DeviceTask;
import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.model.StStsmtaskB;
import com.fourfaith.siteManage.model.TaskDeviceJson;
import com.fourfaith.siteManage.model.TaskDistance;
import com.fourfaith.siteManage.model.TaskJson2001;
import com.fourfaith.siteManage.model.TaskJson3001;
import com.fourfaith.siteManage.model.TaskQueryJson;
import com.fourfaith.siteManage.model.Upgrade;
import com.fourfaith.siteManage.service.DevicePhotoService;
import com.fourfaith.siteManage.service.DeviceTaskService;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.siteManage.service.StStsmtaskBService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.Constant;
import com.fourfaith.utils.FactorName;
import com.fourfaith.utils.PropertiesUtils;
import com.fourfaith.utils.httpUtils;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/task"})
public class TaskController {
    protected static final String photoJsp = "/page/stbprpb/task/photo";
    protected static final String versionJsp = "/page/stbprpb/task/config/version";
    protected static final String statusJsp = "/page/stbprpb/task/config/status";
    protected static final String distanceJsp = "/page/stbprpb/task/config/distance";
    protected static final String upgradeJsp = "/page/stbprpb/task/config/upgrade";
    protected static final String alarmJsp = "/page/stbprpb/task/config/alarm";
    @Autowired
    private StStbprpBService stStbprpBService;
    @Autowired
    private StStsmtaskBService stStsmtaskBService;
    @Autowired
    private DeviceTaskService deviceTaskService;
    @Autowired
    private DevicePhotoService devicePhotoService;
    @Autowired
    private StFactorNameService stFactorNameService;
    public String logContent = "";

    public TaskController() {
    }

    @RequestMapping({"/logTaskRestart"})
    @ResponseBody
    public String logTaskRestart(HttpServletRequest request, String stcd) throws ConnectException, HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        RequestContext requestContext = new RequestContext(request);
        StStbprpB stbprpB = this.stStbprpBService.findById(stcd);
        if (stbprpB != null) {
            if (stbprpB.getDsfl() != 1) {
                ajaxJson.setMsg(requestContext.getMessage("deviceNotOnline"));
                ajaxJson.setSuccess(false);
            } else {
                ajaxJson = this.sbTaskCommon(request, stcd, "null", Constant.rtuRestart, requestContext.getMessage("restart"));
                if (!ajaxJson.isSuccess()) {
                    this.logContent = null;
                }
            }
        } else {
            ajaxJson.setMsg(requestContext.getMessage("noDeviceInfo"));
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    public AjaxJson sbTaskCommon(HttpServletRequest request, String stcd, String content, String code, String iden) throws ConnectException, HttpException, IOException, NullPointerException {
        this.logContent = null;
        AjaxJson ajaxJson = new AjaxJson();
        RequestContext requestContext = new RequestContext(request);
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(11, 12);
        HttpSession session = request.getSession();
        Object token = session.getAttribute("Token");
        if (token == null) {
            TokenController.getToken(request);
            token = session.getAttribute("Token");
        }

        Map<String, Object> map = new HashMap();
        List<TaskDeviceJson> taskDeviceJson = new ArrayList();
        StStbprpB stbprpB = this.stStbprpBService.findById(stcd);
        if (stbprpB != null) {
            Date time = new Date();
            DeviceTask deviceTask = new DeviceTask();
            deviceTask.setId(CommonUtil.getRandomUUID());
            deviceTask.setStcd(stbprpB.getStcd());
            Map<String, Object> param = new HashMap();
            param.put("stcd", stbprpB.getStcd());
            param.put("taskcode", code);
            param.put("taskstatus", 0);
            List<DeviceTask> taskInfo = this.deviceTaskService.getList(param);
            if (taskInfo != null && taskInfo.size() > 0) {
                Iterator var19 = taskInfo.iterator();

                while(var19.hasNext()) {
                    DeviceTask ts = (DeviceTask)var19.next();
                    ts.setTaskstatus(3);
                    this.deviceTaskService.updateByStatus(ts);
                }
            }

            deviceTask.setTaskcode(code);
            deviceTask.setTaskcreatetime(time);
            deviceTask.setTaskcontent(content);
            deviceTask.setTaskstatus(0);
            this.deviceTaskService.add(deviceTask);
            TaskDeviceJson deviceJson = new TaskDeviceJson();
            deviceJson.setId(deviceTask.getId());
            deviceJson.setT_devicecode(deviceTask.getStcd());
            deviceJson.setT_funcode(deviceTask.getTaskcode());
            deviceJson.setT_time(time.getTime());
            deviceJson.setT_timeout(nowTime.getTimeInMillis());
            deviceJson.setT_send(deviceTask.getTaskcontent().toString());
            taskDeviceJson.add(deviceJson);
            this.logContent = this.logContent + iden + stbprpB.getStnm() + ",";
            map.put("tasks", taskDeviceJson);
            TaskJson3001 json = TokenController.getThreeTaskBean();
            json.setToken(token.toString());
            json.setPayload(map);
            ajaxJson = this.getCenterParam3001(json, request, session);
        } else {
            ajaxJson.setMsg(requestContext.getMessage("noDeviceInfo"));
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    @RequestMapping({"/isCamera"})
    @ResponseBody
    public String isCamera(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        RequestContext requestContext = new RequestContext(request);
        String stcd = request.getParameter("stcd");
        StStbprpB stbprpB = this.stStbprpBService.findById(stcd);
        if (stbprpB != null) {
            int isCamera = stbprpB.getIscamera();
            if (isCamera == 1) {
                ajaxJson.setMsg(requestContext.getMessage("hasCamera"));
                ajaxJson.setSuccess(true);
            } else {
                ajaxJson.setMsg(requestContext.getMessage("doesCamera"));
                ajaxJson.setSuccess(false);
            }
        } else {
            ajaxJson.setMsg(requestContext.getMessage("noDeviceInfo"));
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/sbPhoto"})
    public ModelAndView sbPhoto(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/task/photo");
        String stcd = request.getParameter("stcd");
        mav.addObject("stcd", stcd);
        return mav;
    }

    @RequestMapping({"/doCaptureTask"})
    @ResponseBody
    public String doCaptureTask(HttpServletRequest request) throws ConnectException, HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        RequestContext requestContext = new RequestContext(request);
        String stcd = request.getParameter("stcd");
        String photo = request.getParameter("photo");
        String[] idArray = photo.split(",");
        StStbprpB stbprpB = this.stStbprpBService.findById(stcd);
        SimpleDateFormat sp = new SimpleDateFormat("yyyyMMddHHmmss");
        String content = "";
        String sendNo = stbprpB.getStcd();
        if (stbprpB.getCameratype() == 2) {
            sendNo = stbprpB.getDvrcode();
        }

        if (stbprpB.getDsfl() != 1) {
            ajaxJson.setMsg(requestContext.getMessage("deviceNotOnline"));
            ajaxJson.setSuccess(false);
        } else {
            HttpSession session = request.getSession();
            Object token = session.getAttribute("Token");
            if (token == null) {
                TokenController.getToken(request);
                token = session.getAttribute("Token");
            }

            for(int i = 0; i < idArray.length; ++i) {
                Date date = new Date();
                String strCurrent = sp.format(date);
                String dateStr = strCurrent.substring(2, strCurrent.length());
                String photoName = sendNo + "-" + idArray[i] + "-01" + "-" + dateStr + ".jpg";
                content = content + idArray[i] + "_" + photoName + "\r";
            }

            System.out.println("=============================================" + content);
            content = content + "_" + stbprpB.getCameratype();
            TaskQueryJson deviceJson = new TaskQueryJson();
            deviceJson.setDeviceCode(stbprpB.getStcd());
            deviceJson.setFunCode(Constant.photo);
            deviceJson.setRequest(content);
            TaskJson2001 json = TokenController.getFourTaskBean();
            json.setToken(token.toString());
            json.setPayload(deviceJson);
            ajaxJson = this.getCenterParam4001(json, request, session);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    public AjaxJson getCenterParam4001(TaskJson2001 json, HttpServletRequest request, HttpSession session) throws ConnectException, HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        Gson gson = new Gson();
        RequestContext requestContext = new RequestContext(request);
        String urls = gson.toJson(json);
        String strURL = null;

        try {
            strURL = URLEncoder.encode(urls, "utf-8").replace("*", "*").replace("~", "~").replace("+", " ");
        } catch (UnsupportedEncodingException var13) {
            var13.printStackTrace();
        }

        String newUr = PropertiesUtils.getPara("web_apiaddress") + strURL;
        String result = httpUtils.get(newUr);
        if (result == "") {
            ajaxJson.setMsg(requestContext.getMessage("centerReturnNull"));
            ajaxJson.setSuccess(false);
        } else {
            TaskJson3001 tj = (TaskJson3001)gson.fromJson(result, TaskJson3001.class);
            if (tj.getRspcode() == 0) {
                String re = tj.getPayload().get("Result").toString();
                if (re.equals("1.0")) {
                    ajaxJson.setMsg(requestContext.getMessage("missionSuccess"));
                    ajaxJson.setSuccess(true);
                } else {
                    ajaxJson.setMsg(requestContext.getMessage("taskFailure"));
                    ajaxJson.setSuccess(false);
                }
            } else if (tj.getRspcode() == 2) {
                TokenController.getToken(request);
                Object token = session.getAttribute("Token");
                if (token == null) {
                    ajaxJson.setMsg("Connection refused: Please contact administrator!");
                    ajaxJson.setSuccess(false);
                } else {
                    this.getCenterParam4001(json, request, session);
                }
            } else {
                TokenController.getToken(request);
                ajaxJson.setMsg(requestContext.getMessage("tokenInvalid"));
                ajaxJson.setSuccess(false);
            }
        }

        return ajaxJson;
    }

    @RequestMapping({"/readVersion"})
    @ResponseBody
    public String version(HttpServletRequest request) throws HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        String stcd = request.getParameter("stcd");
        RequestContext requestContext = new RequestContext(request);
        StStbprpB stbprpB = this.stStbprpBService.findById(stcd);
        if (stbprpB != null) {
            if (stbprpB.getDsfl() == 1) {
                HttpSession session = request.getSession();
                Object token = session.getAttribute("Token");
                if (token == null) {
                    TokenController.getToken(request);
                    token = session.getAttribute("Token");
                }

                TaskQueryJson deviceJson = new TaskQueryJson();
                deviceJson.setDeviceCode(stbprpB.getStcd());
                deviceJson.setFunCode(Constant.readVersonStatus);
                deviceJson.setRequest("");
                TaskJson2001 json = TokenController.getTwoTaskBeans();
                json.setToken(token.toString());
                json.setPayload(deviceJson);
                ajaxJson = this.getCenterQuery(json, request, session, (String)null, (String)null);
            } else {
                ajaxJson.setMsg(requestContext.getMessage("cannotQueryVersion"));
                ajaxJson.setSuccess(false);
            }
        } else {
            ajaxJson.setMsg(requestContext.getMessage("noDeviceInfo"));
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/readStatus"})
    @ResponseBody
    public String readStatus(HttpServletRequest request) throws ConnectException, HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        String stcd = request.getParameter("stcd");
        RequestContext requestContext = new RequestContext(request);
        String requestContent = "";
        StStbprpB stbprpB = this.stStbprpBService.findById(stcd);
        StStsmtaskB smtask = this.stStsmtaskBService.findById(stcd);
        if (stbprpB != null) {
            if (smtask == null) {
                ajaxJson.setMsg(requestContext.getMessage("notQueryElementStatus"));
                ajaxJson.setSuccess(false);
                return JSONObject.toJSONString(ajaxJson);
            }

            int packageType = stbprpB != null && stbprpB.getPackettype() != null ? stbprpB.getPackettype() : 1;
            List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
            Map<String, FactorName> factorNameMap = this.stFactorNameService.getAllFactorMap(login_user.getEnterpriseid(), filterFactor);
            FactorName[] var15;
            int var14 = (var15 = FactorName.values()).length;

            for(int var13 = 0; var13 < var14; ++var13) {
                FactorName factor = var15[var13];
                boolean var16 = false;

                try {
                    Field field = StStsmtaskB.class.getDeclaredField(factor.getFlag());
                    field.setAccessible(true);
                    int f = (Integer)field.get(smtask);
                    if (f == 1) {
                        requestContent = requestContent + (packageType == 1 ? ((FactorName)factorNameMap.get(factor.getFlag())).getHexCode() : ((FactorName)factorNameMap.get(factor.getFlag())).getAsciiCode()) + "\r";
                    }
                } catch (Exception var18) {
                    var18.printStackTrace();
                }
            }

            if (stbprpB.getDsfl() == 1) {
                HttpSession session = request.getSession();
                Object token = session.getAttribute("Token");
                if (token == null) {
                    TokenController.getToken(request);
                    token = session.getAttribute("Token");
                }

                TaskQueryJson deviceJson = new TaskQueryJson();
                deviceJson.setDeviceCode(stbprpB.getStcd());
                deviceJson.setFunCode(Constant.readFactory);
                deviceJson.setRequest(requestContent);
                TaskJson2001 json = TokenController.getTwoTaskBeans();
                json.setToken(token.toString());
                json.setPayload(deviceJson);
                ajaxJson = this.getCenterQuery(json, request, session, (String)null, requestContent);
            } else {
                ajaxJson.setMsg(requestContext.getMessage("deviceNotOnline"));
                ajaxJson.setSuccess(false);
            }
        } else {
            ajaxJson.setMsg(requestContext.getMessage("noDeviceInfo"));
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/statusPage"})
    public ModelAndView statusPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/task/config/status");
        RequestContext requestContext = new RequestContext(request);
        String stcd = request.getParameter("stcd");
        StStbprpB stbprpB = this.stStbprpBService.findById(stcd);
        int packageType = stbprpB != null && stbprpB.getPackettype() != null ? stbprpB.getPackettype() : 1;
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> factoryMap = new LinkedHashMap();
        String factoryStr = request.getParameter("factoryStr");
        String requestContent = request.getParameter("requestContent");
        String[] factoryArray = factoryStr.split("\\r");
        String[] requestArray = requestContent.split("\\r");
        StringBuffer strBf = new StringBuffer();
        List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getAllFactorMap(login_user.getEnterpriseid(), filterFactor);
        int j;
        int var20;
        String[] strSplits;
        if (StringUtils.isNotEmpty(factoryStr)) {
            String[] split;
            for(int i = 0; i < requestArray.length; ++i) {
                for(j = 0; j < factoryArray.length; ++j) {
                    split = factoryArray[j].split("_");
                    if (requestArray[i].equals(split[0])) {
                        if (split[1].contains("A")) {
                            strBf.append(split[0] + "_" + requestContext.getMessage("invalidValue") + "\r");
                        } else {
                            strBf.append(split[0] + "_" + split[1] + "\r");
                        }
                        break;
                    }
                }

                if (!strBf.toString().contains(requestArray[i] + "_")) {
                    strBf.append(requestArray[i] + "_" + requestContext.getMessage("noAccess") + "\r");
                }
            }

            System.out.println(strBf.toString());
            strSplits = strBf.toString().split("\r");

            for(j = 0; j < strSplits.length; ++j) {
                split = strSplits[j].split("_");
                FactorName[] var22;
                int var21 = (var22 = FactorName.values()).length;

                for(var20 = 0; var20 < var21; ++var20) {
                    FactorName r = var22[var20];
                    String code = packageType == 1 ? r.getHexCode() : r.getAsciiCode();
                    if (split[0].equals(code)) {
                        if (split[1].equals(requestContext.getMessage("noAccess"))) {
                            if (requestContext.getMessage("userLogin").equals("User login")) {
                                factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), split[1]);
                            } else {
                                factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), split[1]);
                            }
                            break;
                        }

                        if (requestContext.getMessage("userLogin").equals("User login")) {
                            if (!code.equals("FF2F") && !code.equals("DI0")) {
                                factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), split[1] + " " + (requestContext.getMessage("invalidValue").equalsIgnoreCase(split[1]) ? "" : r.getUnit()));
                                break;
                            }

                            if (split[1].equals("0")) {
                                factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), "雨 " + (requestContext.getMessage("invalidValue").equalsIgnoreCase(split[1]) ? "" : r.getUnit()));
                            } else if (split[1].equals("1")) {
                                factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), "雪 " + (requestContext.getMessage("invalidValue").equalsIgnoreCase(split[1]) ? "" : r.getUnit()));
                            } else {
                                factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), "--" + (requestContext.getMessage("invalidValue").equalsIgnoreCase(split[1]) ? "" : r.getUnit()));
                            }
                            break;
                        }

                        if (!code.equals("FF2F") && !code.equals("DI0")) {
                            factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), split[1] + " " + (requestContext.getMessage("invalidValue").equalsIgnoreCase(split[1]) ? "" : r.getUnit()));
                            break;
                        }

                        if (split[1].equals("0")) {
                            factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), "雨 " + (requestContext.getMessage("invalidValue").equalsIgnoreCase(split[1]) ? "" : r.getUnit()));
                        } else if (split[1].equals("1")) {
                            factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), "雪 " + (requestContext.getMessage("invalidValue").equalsIgnoreCase(split[1]) ? "" : r.getUnit()));
                        } else {
                            factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), "--" + (requestContext.getMessage("invalidValue").equalsIgnoreCase(split[1]) ? "" : r.getUnit()));
                        }
                        break;
                    }
                }
            }
        } else {
            strSplits = requestContent.split("\\r");
            if (strSplits.length > 0) {
                for(j = 0; j < strSplits.length; ++j) {
                    FactorName[] var27;
                    var20 = (var27 = FactorName.values()).length;

                    for(int var26 = 0; var26 < var20; ++var26) {
                        FactorName r = var27[var26];
                        String code = packageType == 1 ? r.getHexCode() : r.getAsciiCode();
                        if (strSplits[j].equals(code)) {
                            if (requestContext.getMessage("userLogin").equals("User login")) {
                                factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), requestContext.getMessage("noAccess"));
                            } else {
                                factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), requestContext.getMessage("noAccess"));
                            }
                            break;
                        }
                    }
                }
            }
        }

        mav.addObject("factoryMap", factoryMap);
        return mav;
    }

    @RequestMapping({"findDeviceDistance"})
    @ResponseBody
    public String findDeviceDistance(HttpServletRequest request, String stcd) throws HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        RequestContext requestContext = new RequestContext(request);
        String requestContent = "01\r03\r0F\r0C\r04\r06\r08\r0A";
        StStbprpB stbprpB = this.stStbprpBService.findById(stcd);
        new TaskQueryJson();
        new HashMap();
        new Date();
        if (stbprpB != null) {
            if (stbprpB.getDsfl() == 1) {
                HttpSession session = request.getSession();
                Object token = session.getAttribute("Token");
                if (token == null) {
                    TokenController.getToken(request);
                    token = session.getAttribute("Token");
                }

                TaskQueryJson deviceJson = new TaskQueryJson();
                deviceJson.setDeviceCode(stbprpB.getStcd());
                deviceJson.setFunCode(Constant.readDistanceConfig);
                deviceJson.setRequest(requestContent);
                TaskJson2001 json = TokenController.getTwoTaskBeans();
                json.setToken(token.toString());
                json.setPayload(deviceJson);
                ajaxJson = this.getCenterQuery(json, request, session, (String)null, (String)null);
            } else {
                ajaxJson.setMsg(requestContext.getMessage("cannotRemoteConfig"));
                ajaxJson.setSuccess(false);
            }
        } else {
            ajaxJson.setMsg(requestContext.getMessage("noDeviceInfo"));
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/taskDistancePage"})
    public ModelAndView taskDistancePage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/task/config/distance");
        String ids = request.getParameter("ids");
        String disStr = request.getParameter("disStr");
        mav.addObject("ids", ids);
        TaskDistance distance = new TaskDistance();
        if (StringUtils.isNotEmpty(disStr)) {
            String[] strSplit = disStr.split("\\r");

            for(int i = 0; i < strSplit.length; ++i) {
                String[] split = strSplit[i].split("_");
                String var9;
                String[] sp;
                switch((var9 = split[0]).hashCode()) {
                    case 1537:
                        if (var9.equals("01")) {
                            if (split.length > 1) {
                                distance.setCenterAddress(split[1]);
                            } else {
                                distance.setCenterAddress("");
                            }
                        }
                        break;
                    case 1539:
                        if (var9.equals("03")) {
                            if (split.length > 1) {
                                distance.setPwd(split[1]);
                            } else {
                                distance.setPwd("");
                            }
                        }
                        break;
                    case 1540:
                        if (var9.equals("04")) {
                            if (split.length > 1) {
                                sp = split[1].split("\\^");
                                if (sp.length > 0) {
                                    distance.setMain1Type(sp[0]);
                                    if (sp.length > 1) {
                                        distance.setMain1Address(sp[1]);
                                    } else {
                                        distance.setMain1Address("");
                                    }
                                }
                            } else {
                                distance.setMain1Address(split[1]);
                                distance.setMain1Type(split[1]);
                            }
                        }
                        break;
                    case 1542:
                        if (var9.equals("06")) {
                            if (split.length > 1) {
                                sp = split[1].split("\\^");
                                if (sp.length > 0) {
                                    distance.setMain2Type(sp[0]);
                                    if (sp.length > 1) {
                                        distance.setMain2Address(sp[1]);
                                    } else {
                                        distance.setMain2Address("");
                                    }
                                }
                            } else {
                                distance.setMain2Address(split[1]);
                                distance.setMain2Type(split[1]);
                            }
                        }
                        break;
                    case 1544:
                        if (var9.equals("08")) {
                            if (split.length > 1) {
                                sp = split[1].split("\\^");
                                if (sp.length > 0) {
                                    distance.setMain3Type(sp[0]);
                                    if (sp.length > 1) {
                                        distance.setMain3Address(sp[1]);
                                    } else {
                                        distance.setMain3Address("");
                                    }
                                }
                            } else {
                                distance.setMain3Address(split[1]);
                                distance.setMain3Type(split[1]);
                            }
                        }
                        break;
                    case 1553:
                        if (var9.equals("0A")) {
                            if (split.length > 1) {
                                sp = split[1].split("\\^");
                                if (sp.length > 0) {
                                    distance.setMain4Type(sp[0]);
                                    if (sp.length > 1) {
                                        distance.setMain4Address(sp[1]);
                                    } else {
                                        distance.setMain4Address("");
                                    }
                                }
                            } else {
                                distance.setMain4Address(split[1]);
                                distance.setMain4Type(split[1]);
                            }
                        }
                        break;
                    case 1555:
                        if (var9.equals("0C")) {
                            if (split.length > 1) {
                                distance.setWorkModel(split[1]);
                            } else {
                                distance.setWorkModel("");
                            }
                        }
                        break;
                    case 1558:
                        if (var9.equals("0F")) {
                            if (split.length > 1) {
                                sp = split[1].split("\\^");
                                if (sp.length > 1) {
                                    distance.setNumber(sp[1]);
                                } else {
                                    distance.setNumber("");
                                }
                            } else {
                                distance.setNumber("");
                            }
                        }
                }
            }
        }

        mav.addObject("TaskDistance", distance);
        return mav;
    }

    @RequestMapping({"/logDiDeviceSetting"})
    @ResponseBody
    public String logDiDeviceSetting(HttpServletRequest request, TaskDistance distance) throws ConnectException, HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        RequestContext requestContext = new RequestContext(request);
        this.logContent = "";
        String content = "";
        if (StringUtils.isNotEmpty(distance.getCenterAddress())) {
            content = Constant.centerAddress + "_" + distance.getCenterAddress() + "\r" + content;
        }

        if (StringUtils.isNotEmpty(distance.getPwd())) {
            content = Constant.pwd + "_" + distance.getPwd() + "\r" + content;
        }

        if (StringUtils.isNotEmpty(distance.getNumber())) {
            content = Constant.number + "_" + "1^" + distance.getNumber() + "\r" + content;
        }

        if (distance.getWorkModel() != null && !distance.getWorkModel().equals("0")) {
            content = Constant.workModel + "_" + distance.getWorkModel() + "\r" + content;
        }

        if (distance.getMain1Type() != null && distance.getMain1Type().equals("0")) {
            content = Constant.main1TypeAddress + "_" + distance.getMain1Type() + "^" + "null" + "\r" + content;
        } else if (distance.getMain1Type() != null && !distance.getMain1Type().equals("-1")) {
            content = Constant.main1TypeAddress + "_" + distance.getMain1Type() + "^" + distance.getMain1Address() + "\r" + content;
        }

        if (distance.getMain2Type() != null && distance.getMain2Type().equals("0")) {
            content = Constant.main2TypeAddress + "_" + distance.getMain2Type() + "^" + "null" + "\r" + content;
        } else if (distance.getMain2Type() != null && !distance.getMain2Type().equals("-1")) {
            content = Constant.main2TypeAddress + "_" + distance.getMain2Type() + "^" + distance.getMain2Address() + "\r" + content;
        }

        if (distance.getMain3Type() != null && distance.getMain3Type().equals("0")) {
            content = Constant.main3TypeAddress + "_" + distance.getMain3Type() + "^" + "null" + "\r" + content;
        } else if (distance.getMain3Type() != null && !distance.getMain3Type().equals("-1")) {
            content = Constant.main3TypeAddress + "_" + distance.getMain3Type() + "^" + distance.getMain3Address() + "\r" + content;
        }

        if (distance.getMain4Type() != null && distance.getMain4Type().equals("0")) {
            content = Constant.main4TypeAddress + "_" + distance.getMain4Type() + "^" + "null" + "\r" + content;
        } else if (distance.getMain4Type() != null && !distance.getMain4Type().equals("-1")) {
            content = Constant.main4TypeAddress + "_" + distance.getMain4Type() + "^" + distance.getMain4Address() + "\r" + content;
        }

        if (StringUtils.isEmpty(content)) {
            ajaxJson.setMsg(requestContext.getMessage("noConfigured"));
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson = this.logDiDeviceCommand(request, distance.getGroupId(), content);
            if (!ajaxJson.isSuccess()) {
                this.logContent = null;
            }
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/logDiDeviceCommand"})
    @ResponseBody
    public AjaxJson logDiDeviceCommand(HttpServletRequest request, String ids, String content) throws ConnectException, HttpException, IOException {
        this.logContent = null;
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajaxJson = new AjaxJson();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(11, 12);
        HttpSession session = request.getSession();
        Object token = session.getAttribute("Token");
        if (token == null) {
            TokenController.getToken(request);
            token = session.getAttribute("Token");
        }

        Map<String, Object> map = new HashMap();
        List<TaskDeviceJson> taskDeviceJson = new ArrayList();
        boolean isExecute = true;
        String[] idArray = ids.split(",");
        int i;
        if (idArray.length == 1) {
            for(i = 0; i < idArray.length; ++i) {
                StStbprpB stbprpB = this.stStbprpBService.findById(idArray[i]);
                if (stbprpB.getDsfl() != 1) {
                    isExecute = false;
                    break;
                }
            }
        }

        if (ids != null && isExecute) {
            this.logContent = "";

            for(i = 0; i < idArray.length; ++i) {
                Date time = new Date();
                DeviceTask task = new DeviceTask();
                task.setId(CommonUtil.getRandomUUID());
                StStbprpB stbprpB = this.stStbprpBService.findById(idArray[i]);
                task.setStcd(stbprpB.getStcd());
                task.setTaskcode(Constant.updateConfig);
                task.setTaskcreatetime(time);
                task.setTaskcontent(content.replaceAll("\r", "\\\\r"));
                task.setTaskstatus(0);
                this.deviceTaskService.add(task);
                TaskDeviceJson deviceJson = new TaskDeviceJson();
                deviceJson.setId(task.getId());
                deviceJson.setT_devicecode(task.getStcd());
                deviceJson.setT_funcode(task.getTaskcode());
                deviceJson.setT_time(time.getTime());
                deviceJson.setT_timeout(nowTime.getTimeInMillis());
                deviceJson.setT_send(content);
                taskDeviceJson.add(deviceJson);
                this.logContent = this.logContent + requestContext.getMessage("remoteConfiguration") + stbprpB.getStnm() + ",";
            }

            map.put("tasks", taskDeviceJson);
            TaskJson3001 json = TokenController.getThreeTaskBean();
            json.setToken(token.toString());
            json.setPayload(map);
            ajaxJson = this.getCenterParam3001(json, request, session);
        } else if (ids == null) {
            ajaxJson.setMsg(requestContext.getMessage("noDeviceSelected"));
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setMsg(requestContext.getMessage("noLineEquipment"));
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    @RequestMapping({"/taskUpgradePage"})
    public ModelAndView taskUpgradePage(HttpServletRequest request) {
        String ids = request.getParameter("ids");
        ModelAndView mav = new ModelAndView("/page/stbprpb/task/config/upgrade");
        mav.addObject("ids", ids);
        return mav;
    }

    @RequestMapping({"logUpgradeToTask"})
    @ResponseBody
    public String logUpgradeToTask(HttpServletRequest request, Upgrade upgrade) throws HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        this.logContent = null;
        String content = "";
        RequestContext requestContext = new RequestContext(request);
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(upgrade.getAddress())) {
            content = Constant.ip + "_" + upgrade.getAddress() + "\r" + content;
        }

        if (StringUtils.isNotEmpty(upgrade.getPort())) {
            content = Constant.port + "_" + upgrade.getPort() + "\r" + content;
        }

        if (StringUtils.isNotEmpty(upgrade.getFile())) {
            content = Constant.var + "_" + upgrade.getFile() + "\r" + content;
        }

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(11, 12);
        HttpSession session = request.getSession();
        Object token = session.getAttribute("Token");
        if (token == null) {
            TokenController.getToken(request);
            token = session.getAttribute("Token");
        }

        Map<String, Object> map = new HashMap();
        List<TaskDeviceJson> taskDeviceJson = new ArrayList();
        boolean isExecute = true;
        String[] idArray = ids.split(",");
        int i;
        if (idArray.length == 1) {
            for(i = 0; i < idArray.length; ++i) {
                StStbprpB stbprpB = this.stStbprpBService.findById(idArray[i]);
                if (stbprpB.getDsfl() != 1) {
                    isExecute = false;
                    break;
                }
            }
        }

        if (ids != null && isExecute) {
            this.logContent = "";

            for(i = 0; i < idArray.length; ++i) {
                Date time = new Date();
                DeviceTask task = new DeviceTask();
                task.setId(CommonUtil.getRandomUUID());
                StStbprpB stbprpB = this.stStbprpBService.findById(idArray[i]);
                task.setStcd(stbprpB.getStcd());
                Map<String, Object> param = new HashMap();
                param.put("stcd", stbprpB.getStcd());
                param.put("taskcode", Constant.rtuUpgrade);
                param.put("taskstatus", 0);
                List<DeviceTask> taskInfo = this.deviceTaskService.getList(param);
                if (taskInfo != null && taskInfo.size() > 0) {
                    Iterator var21 = taskInfo.iterator();

                    while(var21.hasNext()) {
                        DeviceTask ts = (DeviceTask)var21.next();
                        ts.setTaskstatus(3);
                        this.deviceTaskService.updateByStatus(ts);
                    }
                }

                task.setTaskcode(Constant.rtuUpgrade);
                task.setTaskcreatetime(time);
                task.setTaskcontent(content.replaceAll("\r", "\\\\r"));
                task.setTaskstatus(0);
                this.deviceTaskService.add(task);
                TaskDeviceJson deviceJson = new TaskDeviceJson();
                deviceJson.setId(task.getId());
                deviceJson.setT_devicecode(task.getStcd());
                deviceJson.setT_funcode(task.getTaskcode());
                deviceJson.setT_time(time.getTime());
                deviceJson.setT_timeout(nowTime.getTimeInMillis());
                deviceJson.setT_send(content);
                taskDeviceJson.add(deviceJson);
                this.logContent = this.logContent + requestContext.getMessage("remoteUpgrade") + stbprpB.getStnm() + ",";
            }

            map.put("tasks", taskDeviceJson);
            TaskJson3001 json = TokenController.getThreeTaskBean();
            json.setToken(token.toString());
            json.setPayload(map);
            ajaxJson = this.getCenterParam3001(json, request, session);
        } else if (ids == null) {
            ajaxJson.setMsg(requestContext.getMessage("noDeviceSelected"));
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setMsg(requestContext.getMessage("noLineEquipment1"));
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    public AjaxJson getCenterQuery(TaskJson2001 json, HttpServletRequest request, HttpSession session, String str, String requestContent) throws ConnectException, HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        Gson gson = new Gson();
        RequestContext requestContext = new RequestContext(request);
        System.out.println("url请求的参数:" + gson.toJson(json));
        String urls = gson.toJson(json);
        String strURL = null;

        try {
            strURL = URLEncoder.encode(urls, "utf-8").replace("*", "*").replace("~", "~").replace("+", " ");
        } catch (UnsupportedEncodingException var19) {
            var19.printStackTrace();
        }

        String newUr = PropertiesUtils.getPara("web_apiaddress") + strURL;
        String result = httpUtils.get(newUr);
        if (result == "") {
            ajaxJson.setMsg(requestContext.getMessage("centerReturnNull"));
            ajaxJson.setSuccess(false);
        } else {
            TaskJson2001 tj = (TaskJson2001)gson.fromJson(result, TaskJson2001.class);
            if (tj.getRspcode() == 0) {
                if (str == null) {
                    net.sf.json.JSONObject attr = net.sf.json.JSONObject.fromObject(result);
                    Object payload = attr.get("payload");
                    JSONArray array = JSONArray.fromObject(payload);
                    net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(array.get(0));
                    String results = obj.getString("Result");
                    if (results.equals("offLine")) {
                        ajaxJson.setMsg(requestContext.getMessage("refreshNotLine"));
                        ajaxJson.setSuccess(false);
                    } else if (results.equals("timeOut")) {
                        ajaxJson.setMsg(requestContext.getMessage("networkInstability"));
                        ajaxJson.setSuccess(false);
                    } else {
                        ajaxJson.setMsg(results);
                        ajaxJson.setObj(requestContent);
                        ajaxJson.setSuccess(true);
                    }
                } else {
                    System.out.println("-----------------------str not null");
                    ajaxJson.setMsg(str);
                    ajaxJson.setSuccess(true);
                }
            } else if (tj.getRspcode() == 2) {
                TokenController.getToken(request);
                String token = (String)session.getAttribute("Token");
                if (token == null) {
                    ajaxJson.setMsg("Connection refused: Please contact administrator!");
                    ajaxJson.setSuccess(false);
                } else {
                    json.setToken(token);
                    ajaxJson = this.getCenterQuery(json, request, session, str, requestContent);
                }
            } else {
                ajaxJson.setSuccess(false);
                TokenController.getToken(request);
                ajaxJson.setMsg(requestContext.getMessage("tokenInvalid"));
            }
        }

        return ajaxJson;
    }

    public AjaxJson getCenterParam3001(TaskJson3001 json, HttpServletRequest request, HttpSession session) throws ConnectException, HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        Gson gson = new Gson();
        RequestContext requestContext = new RequestContext(request);
        String urls = gson.toJson(json);
        String strURL = null;

        try {
            strURL = URLEncoder.encode(urls, "utf-8").replace("*", "*").replace("~", "~").replace("+", " ");
        } catch (UnsupportedEncodingException var13) {
            var13.printStackTrace();
        }

        String newUr = PropertiesUtils.getPara("web_apiaddress") + strURL;
        String result = httpUtils.get(newUr);
        if (result == "") {
            ajaxJson.setMsg(requestContext.getMessage("centerReturnNull"));
            ajaxJson.setSuccess(false);
        } else {
            TaskJson3001 tj = (TaskJson3001)gson.fromJson(result, TaskJson3001.class);
            if (tj.getRspcode() == 0) {
                String re = tj.getPayload().get("Result").toString();
                if (re.equals("1.0")) {
                    ajaxJson.setMsg(requestContext.getMessage("lookTaskList"));
                    ajaxJson.setSuccess(true);
                } else {
                    ajaxJson.setMsg(requestContext.getMessage("taskFailure"));
                    ajaxJson.setSuccess(false);
                }
            } else if (tj.getRspcode() == 2) {
                TokenController.getToken(request);
                Object token = session.getAttribute("Token");
                if (token == null) {
                    ajaxJson.setMsg("Connection refused: Please contact administrator!");
                    ajaxJson.setSuccess(false);
                } else {
                    this.getCenterParam3001(json, request, session);
                }
            } else {
                ajaxJson.setMsg(requestContext.getMessage("tokenInvalid"));
                ajaxJson.setSuccess(false);
                TokenController.getToken(request);
            }
        }

        return ajaxJson;
    }

    @RequestMapping({"/findAlarmSetting"})
    @ResponseBody
    public String findAlarmSetting(HttpServletRequest request, String id) throws HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        StStbprpB stbprpB = this.stStbprpBService.findById(id);
        RequestContext requestContext = new RequestContext(request);
        int packageType = stbprpB != null && stbprpB.getPackettype() != null ? stbprpB.getPackettype() : 1;
        String requestContent = null;
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), filterFactor, request);
        FactorName[] var14;
        int var13 = (var14 = FactorName.values()).length;

        for(int var12 = 0; var12 < var13; ++var12) {
            FactorName factor = var14[var12];
            boolean var15 = false;

            try {
                requestContent = requestContent + (packageType == 1 ? ((FactorName)factorNameMap.get(factor.getFlag())).getHexCode() : ((FactorName)factorNameMap.get(factor.getFlag())).getAsciiCode()) + "\r";
            } catch (Exception var18) {
                var18.printStackTrace();
            }
        }

        new TaskQueryJson();
        new HashMap();
        new Date();
        if (stbprpB != null) {
            if (stbprpB.getDsfl() == 1) {
                HttpSession session = request.getSession();
                Object token = session.getAttribute("Token");
                if (token == null) {
                    TokenController.getToken(request);
                    token = session.getAttribute("Token");
                }

                TaskQueryJson deviceJson = new TaskQueryJson();
                if (stbprpB != null) {
                    deviceJson.setDeviceCode(stbprpB.getStcd());
                } else {
                    deviceJson.setDeviceCode("null");
                }

                deviceJson.setFunCode(Constant.readAlarmSetting);
                deviceJson.setRequest(requestContent);
                TaskJson2001 json = TokenController.getTwoTaskBeans();
                json.setToken(token.toString());
                json.setPayload(deviceJson);
                ajaxJson = this.getCenterQuery(json, request, session, (String)null, (String)null);
            } else {
                ajaxJson.setMsg(requestContext.getMessage("noAlarmSetting"));
                ajaxJson.setSuccess(false);
            }
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/alarmPage"})
    public ModelAndView alarmPage(HttpServletRequest request, String disStr, String stcdId) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/task/config/alarm");
        new RequestContext(request);
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<Object, Object> factoryMap = new LinkedHashMap();
        Map<Object, Object> factoryIdMap = new LinkedHashMap();
        StStbprpB stbprpB = this.stStbprpBService.findById(stcdId);
        if (stbprpB != null && stbprpB.getPackettype() != null) {
            stbprpB.getPackettype();
        } else {
            boolean var10000 = true;
        }

        int packageType = 1;
        List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getAllFactorMap(login_user.getEnterpriseid(), filterFactor);
        if (StringUtils.isNotEmpty(disStr)) {
            String[] strSplit = disStr.split("\\r");

            for(int i = 0; i < strSplit.length; ++i) {
                String[] split = strSplit[i].split("_");
                FactorName[] var19;
                int var18 = (var19 = FactorName.values()).length;

                for(int var17 = 0; var17 < var18; ++var17) {
                    FactorName r = var19[var17];
                    String code = packageType>0 ? r.getHexCode() : r.getAsciiCode();
                    if (split[0].equals(code)) {
                        if (split.length > 1) {
                            if (StringUtils.isNotEmpty(split[1])) {
                                factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), split[1]);
                                factoryIdMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), code);
                            } else {
                                factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), "终端未设置");
                                factoryIdMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), code);
                            }
                        } else {
                            factoryMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), "终端未设置");
                            factoryIdMap.put(((FactorName)factorNameMap.get(r.getFlag())).getName(), code);
                        }
                        break;
                    }
                }
            }
        }

        mav.addObject("factoryMap", factoryMap);
        mav.addObject("factoryIdMap", factoryIdMap);
        mav.addObject("stcdId", stcdId);
        return mav;
    }

    @RequestMapping({"/logAlarmSetting"})
    @ResponseBody
    public String logAlarmSetting(HttpServletRequest request) throws ConnectException, HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        String stcdId = request.getParameter("stcdId");
        String rain20 = request.getParameter("rain20");
        String water39 = request.getParameter("water39");
        RequestContext requestContext = new RequestContext(request);
        StStbprpB stbprpB = this.stStbprpBService.findById(stcdId);
        if (stbprpB != null && stbprpB.getPackettype() != null) {
            stbprpB.getPackettype();
        } else {
            boolean var10000 = true;
        }

        int packageType = 1;
        String content = "";
        if (StringUtils.isEmpty(content)) {
            ajaxJson.setMsg(requestContext.getMessage("noConfigured"));
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson = this.logAlarmCommand(request, stcdId, content);
            if (!ajaxJson.isSuccess()) {
                this.logContent = null;
            }
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/logAlarmCommand"})
    @ResponseBody
    public AjaxJson logAlarmCommand(HttpServletRequest request, String stcdId, String content) throws ConnectException, HttpException, IOException {
        this.logContent = null;
        AjaxJson ajaxJson = new AjaxJson();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(11, 12);
        RequestContext requestContext = new RequestContext(request);
        HttpSession session = request.getSession();
        Object token = session.getAttribute("Token");
        if (token == null) {
            TokenController.getToken(request);
            token = session.getAttribute("Token");
        }

        Map<String, Object> map = new HashMap();
        List<TaskDeviceJson> taskDeviceJson = new ArrayList();
        StStbprpB stbprpB = this.stStbprpBService.findById(stcdId);
        boolean isExecute = true;
        if (stcdId != null && stbprpB.getDsfl() != 1) {
            isExecute = false;
        }

        if (stcdId != null && isExecute) {
            this.logContent = "";
            Date time = new Date();
            DeviceTask task = new DeviceTask();
            task.setId(CommonUtil.getRandomUUID());
            if (stbprpB != null) {
                task.setStcd(stbprpB.getStcd());
            } else {
                task.setStcd("null");
            }

            task.setTaskcode(Constant.updateAlarmSetting);
            task.setTaskcreatetime(time);
            task.setTaskcontent(content.replaceAll("\r", "\\\\r"));
            task.setTaskstatus(0);
            this.deviceTaskService.add(task);
            TaskDeviceJson deviceJson = new TaskDeviceJson();
            deviceJson.setId(task.getId());
            deviceJson.setT_devicecode(task.getStcd());
            deviceJson.setT_funcode(task.getTaskcode());
            deviceJson.setT_time(time.getTime());
            deviceJson.setT_timeout(nowTime.getTimeInMillis());
            deviceJson.setT_send(content);
            taskDeviceJson.add(deviceJson);
            this.logContent = this.logContent + "报警设定" + stbprpB.getStnm() + ",";
            map.put("tasks", taskDeviceJson);
            TaskJson3001 json = TokenController.getThreeTaskBean();
            json.setToken(token.toString());
            json.setPayload(map);
            ajaxJson = this.getCenterParam3001(json, request, session);
        } else if (stcdId == null) {
            ajaxJson.setMsg(requestContext.getMessage("noDeviceSelected"));
            ajaxJson.setSuccess(false);
        } else {
            ajaxJson.setMsg(requestContext.getMessage("noLineEquipment"));
            ajaxJson.setSuccess(false);
        }

        return ajaxJson;
    }

    @RequestMapping({"/photoDropped"})
    @ResponseBody
    public String photoDropped(HttpServletRequest request, String id, String photoId, String stcd) throws HttpException, IOException {
        new AjaxJson();
        HttpSession session = request.getSession();
        Object token = session.getAttribute("Token");
        if (token == null) {
            TokenController.getToken(request);
            token = session.getAttribute("Token");
        }

        TaskQueryJson deviceJson = new TaskQueryJson();
        deviceJson.setDeviceCode(stcd);
        deviceJson.setFunCode(Constant.dropped);
        deviceJson.setRequest(photoId);
        TaskJson2001 json = TokenController.getFourTaskBean();
        json.setToken(token.toString());
        json.setPayload(deviceJson);
        AjaxJson ajaxJson = this.getCenterDropped(json, request, session, id);
        return JSONObject.toJSONString(ajaxJson);
    }

    public AjaxJson getCenterDropped(TaskJson2001 json, HttpServletRequest request, HttpSession session, String id) throws ConnectException, HttpException, IOException {
        AjaxJson ajaxJson = new AjaxJson();
        Gson gson = new Gson();
        RequestContext requestContext = new RequestContext(request);
        String urls = gson.toJson(json);
        String strURL = null;

        try {
            strURL = URLEncoder.encode(urls, "utf-8").replace("*", "*").replace("~", "~").replace("+", " ");
        } catch (UnsupportedEncodingException var15) {
            var15.printStackTrace();
        }

        String newUr = PropertiesUtils.getPara("web_apiaddress") + strURL;
        String result = httpUtils.get(newUr);
        if (result == "") {
            ajaxJson.setMsg(requestContext.getMessage("centerReturnNull"));
            ajaxJson.setSuccess(false);
        } else {
            TaskJson3001 tj = (TaskJson3001)gson.fromJson(result, TaskJson3001.class);
            if (tj.getRspcode() == 0) {
                String re = tj.getPayload().get("Result").toString();
                if (re.equals("1.0")) {
                    DevicePhoto photo = this.devicePhotoService.findById(id);
                    photo.setPhotostatus(5);
                    this.devicePhotoService.update(photo);
                    ajaxJson.setMsg(requestContext.getMessage("missionSuccess"));
                    ajaxJson.setSuccess(true);
                } else {
                    ajaxJson.setMsg(requestContext.getMessage("taskFailure"));
                    ajaxJson.setSuccess(false);
                }
            } else if (tj.getRspcode() == 2) {
                TokenController.getToken(request);
                Object token = session.getAttribute("Token");
                if (token == null) {
                    ajaxJson.setMsg("Connection refused: Please contact administrator!");
                    ajaxJson.setSuccess(false);
                } else {
                    this.getCenterDropped(json, request, session, id);
                }
            } else {
                TokenController.getToken(request);
                ajaxJson.setMsg(requestContext.getMessage("tokenInvalid"));
                ajaxJson.setSuccess(false);
            }
        }

        return ajaxJson;
    }

    @ExceptionHandler
    @ResponseBody
    public String exception(HttpServletRequest request, Exception ex) {
        request.setAttribute("ex", ex);
        ex.printStackTrace();
        if (ex instanceof ConnectTimeoutException) {
            return com.alibaba.fastjson.JSONArray.toJSONString("HttpError");
        } else if (ex instanceof ConnectException) {
            return com.alibaba.fastjson.JSONArray.toJSONString("HttpError");
        } else if (ex instanceof NullPointerException) {
            return com.alibaba.fastjson.JSONArray.toJSONString("NullError");
        } else if (ex instanceof SecurityException) {
            return com.alibaba.fastjson.JSONArray.toJSONString("SecurityError");
        } else if (ex instanceof SocketException) {
            return com.alibaba.fastjson.JSONArray.toJSONString("SocketError");
        } else {
            return ex instanceof SocketTimeoutException ? com.alibaba.fastjson.JSONArray.toJSONString("SocketTimeOutError") : com.alibaba.fastjson.JSONArray.toJSONString("UnknownError");
        }
    }
}
