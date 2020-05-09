//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.sysManage.model.SysEnterprise;
import com.fourfaith.sysManage.model.SysOrganization;
import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserRole;
import com.fourfaith.sysManage.service.SysEnterpriseService;
import com.fourfaith.sysManage.service.SysOrganizationService;
import com.fourfaith.sysManage.service.SysRoleService;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.sysManage.service.SysUserService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.BeanUtils;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.Constant;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/sysUser"})
public class SysUserController {
    protected static final String indexJsp = "/page/sysmanage/user/userIndex";
    protected static final String addJsp = "page/sysmanage/user/add";
    protected static final String editJsp = "page/sysmanage/user/edit";
    protected static final String listJsp = "page/sysmanage/user/list";
    protected static final String moveJsp = "page/sysmanage/user/moveUser";
    protected static final String changePwdJsp = "page/sysmanage/user/changePwd";
    protected static final String roleInfoJsp = "page/sysmanage/user/roleInfo";
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysEnterpriseService sysEnterpriseService;
    @Autowired
    private SysOrganizationService sysOrganizationService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    public String logContent = "";

    public SysUserController() {
    }

    @RequestMapping({"index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/user/userIndex");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        mav.addObject("enterId", login_user.getEnterpriseid());
        return mav;
    }

    @RequestMapping(
            value = {"/list"},
            method = {RequestMethod.GET}
    )
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("page/sysmanage/user/list");
        Map<String, Object> params = new HashMap();
        String query_username = request.getParameter("query_username");
        String pid = request.getParameter("pid");
        String enterId = request.getParameter("enterId");
        String level = request.getParameter("currentLevel");

        try {
            if (!StringUtils.isNullOrEmpty(query_username)) {
                query_username = URLDecoder.decode(query_username, "UTF-8");
                mav.addObject("query_username", query_username);
            } else {
                mav.addObject("query_username", (Object)null);
            }
        } catch (UnsupportedEncodingException var16) {
            var16.printStackTrace();
        }

        int count = 0;
        if (!StringUtils.isNullOrEmpty(query_username)) {
            params.put("username_fuzzy", query_username);
        }

        SysEnterprise enterprise = this.sysEnterpriseService.findById(enterId);
        String code = enterprise.getEnterprisecode();
        String noUserCode = code + "_admin";
        params.put("enabledstate_notEqual", -1);
        params.put("usercode_notEqual", noUserCode);
        String pageNo = request.getParameter("pageNo");
        PagingBean pagingBean = null;
        List<SysUser> userList = new ArrayList();
        if (org.apache.commons.lang.StringUtils.isNotEmpty(level)) {
            if (Integer.parseInt(level) == 0) {
                params.put("enterpriseid", pid);
                count = this.sysUserService.getCount(params);
                pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
                params.put("pageStart", pagingBean.getStart());
                params.put("pageEnd", pagingBean.getLimit());
                userList = this.sysUserService.getList(params);
            } else {
                mav.addObject("byorganizationid", pid);
                List<String> organizationIdList = this.sysOrganizationService.getAllChildIdList(pid);
                params.put("orgIdList", organizationIdList);
                count = this.sysUserService.getCount(params);
                pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
                params.put("pageStart", pagingBean.getStart());
                params.put("pageEnd", pagingBean.getLimit());
                userList = this.sysUserService.getList(params);
            }
        }

        mav.addObject("level", level);
        mav.addObject("enterpriseid", enterId);
        mav.addObject("pid", pid);
        mav.addObject("pagingBean", pagingBean);
        mav.addObject("userList", userList);
        return mav;
    }

    @RequestMapping({"/addPage"})
    public ModelAndView addPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("page/sysmanage/user/add");
        String byenterpriseid = request.getParameter("byenterpriseid");
        String byorganizationid = request.getParameter("byorganizationid");
        SysEnterprise enterprise = this.sysEnterpriseService.findById(byenterpriseid);
        mav.addObject("enterprise", enterprise);
        SysOrganization organization = this.sysOrganizationService.findById(byorganizationid);
        mav.addObject("organization", organization);
        return mav;
    }

    @RequestMapping(
            value = {"/logAddUser"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String logAddUser(HttpServletRequest request, @ModelAttribute("user") SysUser user) throws Exception {
        HashMap<String, Object> hm = new HashMap();
        RequestContext requestContext = new RequestContext(request);

        try {
            user.setId(CommonUtil.getRandomUUID());
            String salt = StringUtils.getRandomStr(8, false);
            user.setUserpwdsalt(salt);
            user.setUserpwd(StringUtils.encryptMd5(user.getUserpwd() + salt));
            user.setCreatetime(new Date());
            user.setUpdatetime(new Date());
            if (user.getEnabledstate() == null) {
                user.setEnabledstate(0);
            }

            Integer result = this.sysUserService.add(user);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            this.logContent = requestContext.getMessage("userAddLog", new Object[]{"[" + user.getUsername() + "]"});
            hm.put("success", true);
            hm.put("msg", msg);
        } catch (Exception var8) {
            var8.printStackTrace();
            hm.put("success", false);
            hm.put("msg", requestContext.getMessage("optFailExcept") + "：" + var8.getMessage());
        }

        return JSONObject.toJSONString(hm);
    }

    @RequestMapping({"/editPage"})
    public ModelAndView editPage(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("page/sysmanage/user/edit");
        String id = request.getParameter("id");
        SysUser user = this.sysUserService.findById(id);
        mav.addObject("model", user);
        SysEnterprise enterprise = this.sysEnterpriseService.findById(user.getEnterpriseid());
        mav.addObject("enterprise", enterprise);
        SysOrganization organization = this.sysOrganizationService.findById(user.getOrganizationid());
        mav.addObject("organization", organization);
        return mav;
    }

    @RequestMapping(
            value = {"/logEditUser"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String logEditUser(HttpServletRequest request, @ModelAttribute("user") SysUser user) throws Exception {
        RequestContext requestContext = new RequestContext(request);
        HashMap hm = new HashMap();

        try {
            SysUser rel = this.sysUserService.findById(user.getId());
            user.setUpdatetime(new Date());
            if (user.getEnabledstate() == null) {
                user.setEnabledstate(0);
            }

            BeanUtils.copyPropertiesExcludeNull(user, rel);
            Integer result = this.sysUserService.update(rel);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            this.logContent = requestContext.getMessage("userEditLog", new Object[]{"[" + user.getUsername() + "]"});
            hm.put("success", true);
            hm.put("msg", msg);
        } catch (Exception var8) {
            var8.printStackTrace();
            hm.put("success", false);
            hm.put("msg", requestContext.getMessage("optFailExcept") + "：" + var8.getMessage());
        }

        return JSONObject.toJSONString(hm);
    }

    @RequestMapping({"/logDelUser"})
    @ResponseBody
    public String logDelUser(HttpServletRequest request) throws Exception {
        String items = request.getParameter("items");
        AjaxJson ajaxJson = this.sysUserService.deleteUser(items, request);
        this.logContent = ajaxJson.getObj().toString();
        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/movePage"})
    public ModelAndView movePage(HttpServletRequest request, String dids, String enterId) {
        ModelAndView mav = new ModelAndView("page/sysmanage/user/moveUser");
        mav.addObject("dids", dids);
        mav.addObject("enterId", enterId);
        return mav;
    }

    @RequestMapping({"/moveUserfo"})
    @ResponseBody
    public String moveUserfo(HttpServletRequest request, String enterId) {
        RequestContext requestContext = new RequestContext(request);
        SysEnterprise enterPrise = this.sysEnterpriseService.findById(enterId);
        JSONObject enterObject = new JSONObject();
        enterObject.put("id", enterId);
        enterObject.put("name", enterPrise.getEnterprisename() + "【" + requestContext.getMessage("topArea") + "】");
        enterObject.put("pId", 0);
        enterObject.put("open", true);
        Map<String, Object> map = new HashMap();
        map.put("enterpriseid", enterId);
        map.put("organizationflag", Constant.organFlag);
        List<SysOrganization> list = this.sysOrganizationService.getList(map);
        JSONArray jsonarray = new JSONArray();
        jsonarray.add(enterObject);
        Iterator var10 = list.iterator();

        while(var10.hasNext()) {
            SysOrganization organ = (SysOrganization)var10.next();
            JSONObject jsonObject = new JSONObject();
            if (organ.getPid().equals("0")) {
                jsonObject.put("id", organ.getId());
                jsonObject.put("pId", enterId);
                jsonObject.put("name", organ.getOrganname());
                jsonObject.put("open", true);
                jsonarray.add(jsonObject);
            } else {
                jsonObject.put("id", organ.getId());
                jsonObject.put("pId", organ.getPid());
                jsonObject.put("name", organ.getOrganname());
                jsonarray.add(jsonObject);
            }
        }

        return JSONObject.toJSONString(jsonarray);
    }

    @RequestMapping({"/logSaveMoveUser"})
    @ResponseBody
    public String logSaveMoveUser(HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajaxJson = new AjaxJson();
        String ids = request.getParameter("ids");
        String organId = request.getParameter("organId");
        SysOrganization organ = this.sysOrganizationService.findById(organId);
        this.logContent = "";

        try {
            if (ids != null) {
                String[] idArray = ids.split(",");

                for(int i = 0; i < idArray.length; ++i) {
                    SysUser user = this.sysUserService.findById(idArray[i]);
                    user.setOrganizationid(organId);
                    this.sysUserService.moveUser(user);
                    this.logContent = this.logContent + requestContext.getMessage("moveUserLog", new Object[]{"[" + user.getUsername() + "]", "[" + organ.getOrganname() + "]"}) + ",";
                }

                ajaxJson.setMsg(requestContext.getMessage("operateSuccess"));
                ajaxJson.setSuccess(true);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            ajaxJson.setMsg(requestContext.getMessage("optFailExcept") + "：" + var10.getMessage());
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/logInitPwdUser"})
    @ResponseBody
    public String logInitPwdUser(HttpServletRequest request) throws Exception {
        String items = request.getParameter("items");
        RequestContext requestContext = new RequestContext(request);
        HashMap<String, Object> hm = new HashMap();
        this.logContent = "";
        if (items != null) {
            String[] itemArray = items.split(",");
            String[] var9 = itemArray;
            int var8 = itemArray.length;

            for(int var7 = 0; var7 < var8; ++var7) {
                String item = var9[var7];
                SysUser user = this.sysUserService.findById(item);
                user.setUserpwd(StringUtils.encryptMd5("123456" + user.getUserpwdsalt()));
                this.sysUserService.update(user);
                this.logContent = this.logContent + requestContext.getMessage("userInitLog", new Object[]{"[" + user.getUsername() + "]"}) + ",";
            }
        }

        hm.put("success", true);
        hm.put("msg", requestContext.getMessage("operateSuccess"));
        return JSONObject.toJSONString(hm);
    }

    @RequestMapping({"/changePwd"})
    public ModelAndView changePwd(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("page/sysmanage/user/changePwd");
        String userId = request.getParameter("userId");
        mav.addObject("userId", userId);
        return mav;
    }

    @RequestMapping(
            value = {"/changePwdSave"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String changePwdSave(HttpServletRequest request) throws Exception {
        RequestContext requestContext = new RequestContext(request);
        String userId = request.getParameter("userId");
        String userPwd = request.getParameter("userPwd");
        HashMap hm = new HashMap();

        try {
            SysUser user = this.sysUserService.findById(userId);
            user.setUserpwd(StringUtils.encryptMd5(userPwd + user.getUserpwdsalt()));
            user.setUpdatetime(new Date());
            this.sysUserService.update(user);
            hm.put("success", true);
            hm.put("msg", requestContext.getMessage("operateSuccess"));
        } catch (Exception var7) {
            var7.printStackTrace();
            hm.put("success", false);
            hm.put("msg", requestContext.getMessage("optFailExcept") + "：" + var7.getMessage());
        }

        return JSONObject.toJSONString(hm);
    }

    @RequestMapping({"/checkUserPwd"})
    @ResponseBody
    public String checkUserPwd(String userId, String userPwd) {
        AjaxJson ajaxJson = new AjaxJson();
        SysUser user = this.sysUserService.findById(userId);
        if (user != null) {
            String userpwd = StringUtils.encryptMd5(userPwd + user.getUserpwdsalt());
            if (user.getUserpwd().equals(userpwd)) {
                ajaxJson.setSuccess(false);
            } else {
                ajaxJson.setSuccess(true);
            }
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/isHasChildOrg"})
    @ResponseBody
    public String isHasChildOrg(HttpServletRequest request) throws Exception {
        String orgId = request.getParameter("orgId");
        HashMap<String, Object> hm = new HashMap();
        if (!StringUtils.isNullOrEmpty(orgId)) {
            hm.put("result", true);
            HashMap<String, Object> orgParams = new HashMap();
            orgParams.put("enabledstate", 1);
            orgParams.put("pid", orgId);
            List<SysOrganization> orgList = this.sysOrganizationService.getList(orgParams);
            if (orgList != null && orgList.size() > 0) {
                hm.put("result", false);
            }
        }

        return JSONObject.toJSONString(hm);
    }

    @RequestMapping({"/checkUserCodeExist"})
    @ResponseBody
    public String checkUserCodeExist(String usercode, String id, String byenterpriseid) {
        AjaxJson ajaxJson = new AjaxJson();
        Map<String, Object> params = new HashMap();
        params.put("usercode", usercode);
        params.put("enabledstate_notEqual", -1);
        List<SysUser> userList = this.sysUserService.getList(params);
        if (userList != null && userList.size() > 0) {
            SysUser user = (SysUser)userList.get(0);
            if (user.getId().equals(id)) {
                ajaxJson.setSuccess(false);
            } else {
                ajaxJson.setSuccess(true);
            }
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/checkUserNameExist"})
    @ResponseBody
    public String checkUserNameExist(String username, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        Map<String, Object> params = new HashMap();
        params.put("username", username);
        params.put("enabledstate_notEqual", -1);
        List<SysUser> userList = this.sysUserService.getList(params);
        if (userList != null && userList.size() > 0) {
            SysUser user = (SysUser)userList.get(0);
            if (user.getId().equals(id)) {
                ajaxJson.setSuccess(false);
            } else {
                ajaxJson.setSuccess(true);
            }
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/setRoleInfo"})
    public ModelAndView setRoleInfo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("page/sysmanage/user/roleInfo");
        mav.addObject("userId", request.getParameter("userId"));
        mav.addObject("userName", request.getParameter("userName"));
        mav.addObject("enterId", request.getParameter("enterId"));
        return mav;
    }

    @RequestMapping({"/getEnterpriseRole"})
    @ResponseBody
    public String getEnterpriseRole(HttpServletRequest request) throws Exception {
        new HashMap();
        Map<String, Object> params = new HashMap();
        List list = new ArrayList();
        String enterId = request.getParameter("enterId");
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        SysEnterprise enterModel = this.sysEnterpriseService.findById(enterId);
        params.put("enterpriseid", enterId);
        params.put("roleflag", 1);
        HashMap<String, Object> enterHm = new HashMap();
        enterHm.put("name", enterModel.getEnterprisename());
        enterHm.put("id", enterModel.getId());
        enterHm.put("pId", 0);
        enterHm.put("nocheck", true);
        list.add(enterHm);
        List<SysRole> allRoleList = this.sysRoleService.getList(params);
        List<String> roleIdList = this.sysUserRoleService.getRoleIdByUserId(userId);
        HashMap roleHm;
        if (allRoleList != null && allRoleList.size() > 0) {
            for(Iterator var13 = allRoleList.iterator(); var13.hasNext(); list.add(roleHm)) {
                SysRole sysRole = (SysRole)var13.next();
                roleHm = new HashMap();
                roleHm.put("name", sysRole.getRolename());
                roleHm.put("id", sysRole.getId());
                roleHm.put("pId", enterModel.getId());
                if (roleIdList.contains(sysRole.getId())) {
                    roleHm.put("checked", true);
                }
            }
        }

        return JSONObject.toJSONString(list);
    }

    @RequestMapping({"/logSaveUserRole"})
    @ResponseBody
    public String logSaveUserRole(HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        Map<String, Object> hm = new HashMap();
        String userId = request.getParameter("userId");
        String roleIds = request.getParameter("roleIds");
        String[] roleArray = roleIds.split(",");
        Map<String, Object> params = new HashMap();
        params.put("userid", userId);
        List<SysUserRole> oldSysUserRole = this.sysUserRoleService.getList(params);
        Iterator var10 = oldSysUserRole.iterator();

        while(var10.hasNext()) {
            SysUserRole sysUserRole = (SysUserRole)var10.next();
            this.sysUserRoleService.delete(sysUserRole.getId());
        }

        SysUser sysUser = null;
        String userName = "";
        if (org.apache.commons.lang.StringUtils.isNotEmpty(userId)) {
            sysUser = this.sysUserService.findById(userId);
        }

        if (sysUser != null) {
            userName = sysUser.getUsername();
        }

        for(int i = 0; i < roleArray.length; ++i) {
            if (!StringUtils.isNullOrEmpty(roleArray[i])) {
                this.logContent = "";
                SysUserRole model = new SysUserRole();
                model.setId(CommonUtil.getRandomUUID());
                model.setRoleid(roleArray[i]);
                model.setUserid(userId);
                this.sysUserRoleService.add(model);
                this.logContent = this.logContent + requestContext.getMessage("userCofigLog", new Object[]{"[" + userName + "]"}) + ",";
            }
        }

        hm.put("success", true);
        hm.put("msg", requestContext.getMessage("operateSuccess"));
        hm.put("userId", userId);
        return JSONObject.toJSONString(hm);
    }
}
