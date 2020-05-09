//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.service.StAddvcdDService;
import com.fourfaith.sysManage.model.SysEnterprise;
import com.fourfaith.sysManage.model.SysEnterpriseMenu;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.sysManage.model.SysRoleArea;
import com.fourfaith.sysManage.model.SysRolePermission;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserRole;
import com.fourfaith.sysManage.service.SysEnterpriseMenuService;
import com.fourfaith.sysManage.service.SysEnterpriseService;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import com.fourfaith.sysManage.service.SysRolePermissionService;
import com.fourfaith.sysManage.service.SysRoleService;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.BeanUtils;
import com.fourfaith.utils.CommonUtil;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/sysRole"})
public class SysRoleController {
    protected static final String indexJsp = "/page/sysmanage/role/roleIndex";
    protected static final String addJsp = "/page/sysmanage/role/add";
    protected static final String editJsp = "/page/sysmanage/role/edit";
    protected static final String permissionJsp = "/page/sysmanage/role/permissionInfo";
    public String logContent = "";
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysEnterpriseService sysEnterpriseService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysEnterpriseMenuService sysEnterpriseMenuService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @Autowired
    private StAddvcdDService stAddvcdDService;
    @Autowired
    private SysRoleAreaService sysRoleAreaService;

    public SysRoleController() {
    }

    @RequestMapping({"index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/role/roleIndex");
        Map<String, Object> params = new HashMap();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        String query_rolename = request.getParameter("query_rolename");

        try {
            if (!StringUtils.isNullOrEmpty(query_rolename)) {
                query_rolename = URLDecoder.decode(query_rolename, "UTF-8");
                mav.addObject("query_rolename", query_rolename);
            } else {
                mav.addObject("query_rolename", (Object)null);
            }
        } catch (UnsupportedEncodingException var13) {
            var13.printStackTrace();
        }

        SysEnterprise enterprise = this.sysEnterpriseService.findById(login_user.getEnterpriseid());
        String code = enterprise.getEnterprisecode();
        String noRoleCode = code + "_admin";
        params.put("rolecode_notEqual", noRoleCode);
        params.put("enterpriseid", login_user.getEnterpriseid());
        params.put("rolename_fuzzy", query_rolename);
        params.put("enabledstate_notEqual", -1);
        String pageNo = request.getParameter("pageNo");
        int count = this.sysRoleService.getCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("pageStart", pagingBean.getStart());
        params.put("pageEnd", pagingBean.getLimit());
        List<SysRole> roleList = this.sysRoleService.getList(params);
        mav.addObject("pagingBean", pagingBean);
        mav.addObject("roleList", roleList);
        mav.addObject("enterpriseid", login_user.getEnterpriseid());
        mav.addObject("enterprise", enterprise);
        return mav;
    }

    @RequestMapping({"/addPage"})
    public ModelAndView addPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/role/add");
        String enterpriseid = request.getParameter("enterpriseid");
        SysEnterprise enterprise = this.sysEnterpriseService.findById(enterpriseid);
        mav.addObject("enterprise", enterprise);
        return mav;
    }

    @RequestMapping(
            value = {"/logAddRole"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String logAddRole(HttpServletRequest request, SysRole role) throws Exception {
        RequestContext requestContext = new RequestContext(request);
        HashMap<String, Object> hm = new HashMap();
        this.logContent = "";

        try {
            role.setId(CommonUtil.getRandomUUID());
            role.setCreatetime(new Date());
            role.setUpdatetime(new Date());
            if (role.getEnabledstate() == null) {
                role.setEnabledstate(0);
            }

            Integer result = this.sysRoleService.add(role);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            this.logContent = requestContext.getMessage("roleAddLog", new Object[]{"[" + role.getRolename() + "]"});
            hm.put("success", true);
            hm.put("msg", msg);
        } catch (Exception var7) {
            var7.printStackTrace();
            hm.put("success", false);
            hm.put("msg", requestContext.getMessage("optFailExcept") + "：" + var7.getMessage());
        }

        return JSONObject.toJSONString(hm);
    }

    @RequestMapping({"/editPage"})
    public ModelAndView edit(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("/page/sysmanage/role/edit");
        String id = request.getParameter("id");
        SysRole role = this.sysRoleService.findById(id);
        String enterpriseid = role.getEnterpriseid();
        SysEnterprise enterprise = this.sysEnterpriseService.findById(enterpriseid);
        mav.addObject("enterprise", enterprise);
        mav.addObject("model", role);
        return mav;
    }

    @RequestMapping(
            value = {"/logEditRole"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String logEditRole(HttpServletRequest request, SysRole role) throws Exception {
        HashMap<String, Object> hm = new HashMap();
        RequestContext requestContext = new RequestContext(request);
        this.logContent = "";

        try {
            SysRole rel = this.sysRoleService.findById(role.getId());
            role.setUpdatetime(new Date());
            if (role.getEnabledstate() == null) {
                role.setEnabledstate(0);
            }

            BeanUtils.copyPropertiesExcludeNull(role, rel);
            Integer result = this.sysRoleService.update(rel);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            this.logContent = requestContext.getMessage("roleEditLog", new Object[]{"[" + role.getRolename() + "]"});
            hm.put("success", true);
            hm.put("msg", msg);
        } catch (Exception var8) {
            var8.printStackTrace();
            hm.put("success", false);
            hm.put("msg", requestContext.getMessage("optFailExcept") + "：" + var8.getMessage());
        }

        return JSONObject.toJSONString(hm);
    }

    @RequestMapping({"/setPermissionInfo"})
    public ModelAndView setPermissionInfo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/role/permissionInfo");
        mav.addObject("roleId", request.getParameter("roleId"));
        mav.addObject("enterId", request.getParameter("enterId"));
        return mav;
    }

    @RequestMapping({"/checkUserRoleBy"})
    @ResponseBody
    public String checkUserRoleBy(HttpServletRequest request) throws Exception {
        Map<String, Object> hm = new HashMap();
        String items = request.getParameter("items");
        hm.put("exist", false);
        if (items != null) {
            String[] itemArray = items.split(",");
            String[] var8 = itemArray;
            int var7 = itemArray.length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String item = var8[var6];
                Map<String, Object> params = new HashMap();
                params.put("roleid", item);
                List<SysUserRole> userRole = this.sysUserRoleService.getList(params);
                if (userRole != null && userRole.size() > 0) {
                    hm.put("exist", true);
                    break;
                }
            }
        }

        return JSONObject.toJSONString(hm);
    }

    @RequestMapping({"/logDelRole"})
    @ResponseBody
    public String logDelRole(HttpServletRequest request) throws Exception {
        String items = request.getParameter("items");
        this.logContent = "";
        AjaxJson ajaxJson = this.sysRoleService.deleteRole(items, request);
        this.logContent = ajaxJson.getObj().toString();
        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/checkRoleCodeExist"})
    @ResponseBody
    public String checkRoleCodeExist(String rolecode, String id, String enterpriseid) {
        AjaxJson ajaxJson = new AjaxJson();
        Map<String, Object> params = new HashMap();
        params.put("enterpriseid", enterpriseid);
        params.put("rolecode", rolecode);
        params.put("roleflag_notEqual", -1);
        List<SysRole> roleList = this.sysRoleService.getList(params);
        if (roleList != null && roleList.size() > 0) {
            SysRole role = (SysRole)roleList.get(0);
            if (role.getId().equals(id)) {
                ajaxJson.setSuccess(false);
            } else {
                ajaxJson.setSuccess(true);
            }
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/getEnterMenuTree"})
    @ResponseBody
    public String getEnterMenuTree(HttpServletRequest request) throws Exception {
        Map<String, Object> enterMenuParams = new HashMap();
        new HashMap();
        List list = new ArrayList();
        String enterId = request.getParameter("enterId");
        String roleId = request.getParameter("roleId");
        enterMenuParams.put("enterpriseid", enterId);
        List<SysEnterpriseMenu> enterMenuList = this.sysEnterpriseMenuService.getList(enterMenuParams);
        if (enterMenuList != null && enterMenuList.size() > 0) {
            for(int i = 0; i < enterMenuList.size(); ++i) {
                SysEnterpriseMenu sysEnterPriseMenu = (SysEnterpriseMenu)enterMenuList.get(i);
                SysMenu menu = this.sysMenuService.findById(sysEnterPriseMenu.getMenuid());
                HashMap<String, Object> menuHm = new HashMap();
                if (menu != null && menu.getEnabledstate() == 1) {
                    menuHm.put("name", menu.getMenuname());
                    menuHm.put("id", menu.getId());
                    menuHm.put("pId", menu.getPid());
                    menuHm.put("open", true);
                    SysRolePermission sysRolePermission = this.sysRolePermissionService.findByMenuIdAndRoleId(menu.getId(), roleId);
                    if (sysRolePermission != null && sysRolePermission.getId() != null) {
                        menuHm.put("checked", true);
                    }

                    list.add(menuHm);
                }
            }
        }

        return JSONObject.toJSONString(list);
    }

    @RequestMapping({"/getAddvcdTree"})
    @ResponseBody
    public String getAddvcdTree(HttpServletRequest request) throws Exception {
        List list = new ArrayList();
        Map<String, Object> addvcdParams = new HashMap();
        String enterId = request.getParameter("enterId");
        String roleId = request.getParameter("roleId");
        SysEnterprise enter = this.sysEnterpriseService.findById(enterId);
        Map<String, Object> enterHm = new HashMap();
        enterHm.put("name", enter.getEnterprisename());
        enterHm.put("id", enter.getId());
        enterHm.put("pId", 0);
        enterHm.put("nocheck", true);
        enterHm.put("open", true);
        list.add(enterHm);
        addvcdParams.put("enterpriseid", enterId);
        List<StAddvcdD> addvcdList = this.stAddvcdDService.getList(addvcdParams);
        if (addvcdList != null && addvcdList.size() > 0) {
            for(int i = 0; i < addvcdList.size(); ++i) {
                HashMap<String, Object> addvcdHm = new HashMap();
                StAddvcdD stAddvcdD = (StAddvcdD)addvcdList.get(i);
                if (stAddvcdD != null) {
                    addvcdHm.put("name", stAddvcdD.getAddvnm());
                    addvcdHm.put("id", stAddvcdD.getId());
                    if (stAddvcdD.getFaddvcd().equals("0")) {
                        addvcdHm.put("pId", enter.getId());
                    } else {
                        addvcdHm.put("pId", stAddvcdD.getFaddvcd());
                    }

                    addvcdHm.put("open", true);
                    SysRoleArea sysRoleArea = this.sysRoleAreaService.findByAddvcdIdAndRoleId(roleId, stAddvcdD.getId());
                    if (sysRoleArea != null && sysRoleArea.getAddvcdid() != null) {
                        addvcdHm.put("checked", true);
                    }

                    list.add(addvcdHm);
                }
            }
        }

        return JSONObject.toJSONString(list);
    }

    @RequestMapping({"/logSaveRoleMenuAreaPermission"})
    @ResponseBody
    public String logSaveRoleMenuAreaPermission(HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        Map<String, Object> hm = new HashMap();
        String roleId = request.getParameter("roleId");
        String menuIds = request.getParameter("menuIds");
        String areaIds = request.getParameter("areaIds");
        this.logContent = "";
        new HashMap();
        new HashMap();

        try {
            this.sysRoleAreaService.deleteRoleId(roleId);
            this.sysRolePermissionService.deleteByRoleMenu(roleId);
            Map<String, List<String>> menuMap = (Map)JSONObject.parse(menuIds);
            Map<String, List<String>> areaMap = (Map)JSONObject.parse(areaIds);
            Iterator var10 = menuMap.keySet().iterator();

            while(true) {
                List areaList;
                int i;
                do {
                    do {
                        String key;
                        if (!var10.hasNext()) {
                            var10 = areaMap.keySet().iterator();

                            while(true) {
                                do {
                                    do {
                                        if (!var10.hasNext()) {
                                            SysRole role = this.sysRoleService.findById(roleId);
                                            this.logContent = requestContext.getMessage("roleCofigLog", new Object[]{"[" + role.getRolename() + "]"});
                                            hm.put("success", true);
                                            hm.put("msg", requestContext.getMessage("operateSuccess"));
                                            return JSONObject.toJSONString(hm);
                                        }

                                        key = (String)var10.next();
                                        areaList = (List)areaMap.get(key);
                                    } while(areaList == null);
                                } while(areaList.size() <= 0);

                                for(i = 0; i < areaList.size(); ++i) {
                                    SysRoleArea sysRoleArea = new SysRoleArea();
                                    sysRoleArea.setId(CommonUtil.getRandomUUID());
                                    sysRoleArea.setRoleid(roleId);
                                    sysRoleArea.setAddvcdid((String)areaList.get(i));
                                    this.sysRoleAreaService.add(sysRoleArea);
                                }
                            }
                        }

                        key = (String)var10.next();
                        areaList = (List)menuMap.get(key);
                    } while(areaList == null);
                } while(areaList.size() <= 0);

                for(i = 0; i < areaList.size(); ++i) {
                    SysRolePermission sysRolePermission = new SysRolePermission();
                    sysRolePermission.setId(CommonUtil.getRandomUUID());
                    sysRolePermission.setRoleid(roleId);
                    sysRolePermission.setMenuid((String)areaList.get(i));
                    this.sysRolePermissionService.add(sysRolePermission);
                }
            }
        } catch (Exception var14) {
            var14.printStackTrace();
            hm.put("success", false);
            hm.put("msg", requestContext.getMessage("operateFailed"));
            return JSONObject.toJSONString(hm);
        }
    }
}
