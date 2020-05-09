//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.service.StAddvcdDService;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.sysManage.model.SysRoleArea;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/stAddvcdD"})
public class StAddvcdDController {
    protected static final String addJsp = "/page/sysmanage/addvcdD/add";
    protected static final String editJsp = "/page/sysmanage/addvcdD/edit";
    @Autowired
    private StAddvcdDService stAddvcdDService;
    @Autowired
    private SysRoleAreaService sysRoleAreaService;
    @Autowired
    private StStbprpBService stStbprpBService;

    public StAddvcdDController() {
    }

    @RequestMapping({"addPage"})
    public ModelAndView addPage(HttpServletRequest request) {
        String pId = request.getParameter("pId");
        ModelAndView mav = new ModelAndView("/page/sysmanage/addvcdD/add");
        mav.addObject("pId", pId);
        return mav;
    }

    @RequestMapping({"editPage"})
    public ModelAndView editPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/addvcdD/edit");
        String id = request.getParameter("id");
        StAddvcdD staddvcdD = this.stAddvcdDService.findById(id);
        mav.addObject("stAddvcdD", staddvcdD);
        return mav;
    }

    @RequestMapping({"/addAddvcdD"})
    @ResponseBody
    public String addAddvcdD(HttpServletRequest request, StAddvcdD addvcdD) {
        AjaxJson ajax = new AjaxJson();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        addvcdD.setId(CommonUtil.getRandomUUID());
        addvcdD.setEnterpriseid(login_user.getEnterpriseid());
        RequestContext requestContext = new RequestContext(request);
        String msg = "";

        try {
            this.stAddvcdDService.add(addvcdD);
            Map<String, Object> params = new HashMap();
            params.put("addvcd", addvcdD.getFaddvcd());
            List<StStbprpB> stbprpBList = this.stStbprpBService.getList(params);
            if (stbprpBList != null && stbprpBList.size() > 0) {
                Iterator var10 = stbprpBList.iterator();

                while(var10.hasNext()) {
                    StStbprpB bp = (StStbprpB)var10.next();
                    StStbprpB st = this.stStbprpBService.findById(bp.getStcd());
                    st.setAddvcd(addvcdD.getId());

                    try {
                        Integer result = this.stStbprpBService.update(st);
                        if (result > 0) {
                            msg = requestContext.getMessage("operateSuccess");
                        } else {
                            msg = requestContext.getMessage("operateFailed");
                        }
                    } catch (Exception var13) {
                        var13.printStackTrace();
                        ajax.setMsg(requestContext.getMessage("operateFailedMsg") + var13.getMessage());
                        ajax.setSuccess(false);
                    }
                }
            }

            ajax.setMsg(msg);
            ajax.setSuccess(true);
            ajax.setObj(addvcdD);
        } catch (Exception var14) {
            var14.printStackTrace();
            ajax.setMsg(requestContext.getMessage("operateFailedMsg") + var14.getMessage());
            ajax.setSuccess(false);
        }

        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"/editAddvcdD"})
    @ResponseBody
    public String logEditGroup(HttpServletRequest request, StAddvcdD addvcdD) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajax = new AjaxJson();
        addvcdD.setModitime(new Date());

        try {
            Integer result = this.stAddvcdDService.update(addvcdD);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            ajax.setMsg(msg);
            ajax.setSuccess(true);
            ajax.setObj(addvcdD);
        } catch (Exception var7) {
            var7.printStackTrace();
            ajax.setMsg(requestContext.getMessage("operateFailedMsg") + var7.getMessage());
            ajax.setSuccess(false);
        }

        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"/delAddvcdD"})
    @ResponseBody
    public String delAddvcdD(HttpServletRequest request, String id) {
        AjaxJson ajax = new AjaxJson();
        RequestContext requestContext = new RequestContext(request);
        StAddvcdD addvcdD = this.stAddvcdDService.findById(id);
        Map<String, Object> params = new HashMap();
        params.put("enterpriseid", addvcdD.getEnterpriseid());
        params.put("addvcd", addvcdD.getId());
        List<StStbprpB> stbprpBList = this.stStbprpBService.getList(params);
        if (stbprpBList != null && stbprpBList.size() > 0) {
            ajax.setMsg(requestContext.getMessage("areaIncludeDevice"));
            ajax.setSuccess(false);
        } else {
            try {
                this.stAddvcdDService.delete(id);
                this.sysRoleAreaService.deleteAddvcdD(id);
                ajax.setMsg(requestContext.getMessage("deleteArea"));
                ajax.setSuccess(true);
            } catch (Exception var9) {
                var9.printStackTrace();
                ajax.setMsg(requestContext.getMessage("operateFailedMsg") + var9.getMessage());
                ajax.setSuccess(false);
            }
        }

        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"/checkAddvnmExist"})
    @ResponseBody
    public String checkAddvnmExist(HttpServletRequest request, String addvnm, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> params = new HashMap();
        params.put("enterpriseid", login_user.getEnterpriseid());
        params.put("addvnm", addvnm);
        StAddvcdD stAddvcdD = this.stAddvcdDService.getAddvnm(params);
        if (stAddvcdD != null && !stAddvcdD.getId().equals(id)) {
            ajaxJson.setSuccess(true);
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"addvcdDInfo"})
    @ResponseBody
    public String addvcdDInfo(HttpServletRequest request, boolean isSearchDevice, boolean showOnLineDevice, Integer sttp, String query_stcdName) throws UnsupportedEncodingException {
        SysUser userInfo = (SysUser)CommonUtil.getLoginUserInfo(request);
        String roleId = userInfo.getSysrole().getId();
        JSONArray jsonarray = new JSONArray();
        new ArrayList();
        List addvcdD;
        if (userInfo.getEnterManager()) {
            HashMap<String, Object> params = new HashMap();
            params.put("enterpriseid", userInfo.getEnterpriseid());
            addvcdD = this.stAddvcdDService.getList(params);
        } else {
            List<SysRoleArea> areaList = this.sysRoleAreaService.getListByRoleId(roleId);
            List<String> idList = new ArrayList();
            if (areaList != null && areaList.size() > 0) {
                Iterator var13 = areaList.iterator();

                while(var13.hasNext()) {
                    SysRoleArea roleArea = (SysRoleArea)var13.next();
                    idList.add(roleArea.getAddvcdid());
                }
            } else {
                idList.add("");
            }

            addvcdD = this.stAddvcdDService.getListByIdList(idList);
        }

        JSONObject jsonObject;
        if (addvcdD != null && addvcdD.size() > 0) {
            label73:
            for(Iterator var20 = addvcdD.iterator(); var20.hasNext(); jsonarray.add(jsonObject)) {
                StAddvcdD staddvcdD = (StAddvcdD)var20.next();
                jsonObject = new JSONObject();
                if (staddvcdD.getFaddvcd().equals("0")) {
                    jsonObject.put("id", staddvcdD.getId());
                    jsonObject.put("pId", 0);
                    jsonObject.put("name", staddvcdD.getAddvnm());
                    jsonObject.put("addnum", staddvcdD.getAddnum());
                    jsonObject.put("open", true);
                    jsonObject.put("iconSkin", "rootIcon");
                } else {
                    jsonObject.put("id", staddvcdD.getId());
                    jsonObject.put("pId", staddvcdD.getFaddvcd());
                    jsonObject.put("name", staddvcdD.getAddvnm());
                    jsonObject.put("addnum", staddvcdD.getAddnum());
                    jsonObject.put("iconSkin", "flodIcon");
                }

                jsonObject.put("isDevice", false);
                if (isSearchDevice) {
                    Map<String, Object> params = new HashMap();
                    params.put("addvcd", staddvcdD.getId());
                    if (showOnLineDevice) {
                        params.put("dsfl", 1);
                    }

                    List<StStbprpB> stbprpList = this.stStbprpBService.getList(params);
                    if (StringUtils.isNotBlank(query_stcdName)) {
                        query_stcdName = URLDecoder.decode(query_stcdName, "UTF-8");
                    }

                    if (stbprpList != null && stbprpList.size() > 0) {
                        Iterator var16 = stbprpList.iterator();

                        while(true) {
                            StStbprpB stbprpB;
                            do {
                                do {
                                    if (!var16.hasNext()) {
                                        continue label73;
                                    }

                                    stbprpB = (StStbprpB)var16.next();
                                } while(sttp != null && !stbprpB.getSttp().equals(sttp));
                            } while(!StringUtils.isBlank(query_stcdName) && !stbprpB.getStcd().contains(query_stcdName) && !stbprpB.getStnm().contains(query_stcdName));

                            JSONObject deviceHm = new JSONObject();
                            deviceHm.put("id", stbprpB.getStcd());
                            deviceHm.put("name", stbprpB.getStnm());
                            deviceHm.put("pId", staddvcdD.getId());
                            deviceHm.put("isDevice", true);
                            deviceHm.put("lng", stbprpB.getLgtd());
                            deviceHm.put("lat", stbprpB.getLttd());
                            deviceHm.put("iconSkin", "deviceIcon");
                            jsonarray.add(deviceHm);
                        }
                    }
                }
            }
        }

        return JSONObject.toJSONString(jsonarray);
    }

    public static void main(String[] args) {
        String mobiles = "13159251165";
        Pattern p = Pattern.compile("^1[3|4|5|7|8]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
    }
}
