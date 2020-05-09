//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.sysManage.model.SysEnterprise;
import com.fourfaith.sysManage.model.SysEnterpriseMenu;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.sysManage.service.SysEnterpriseMenuService;
import com.fourfaith.sysManage.service.SysEnterpriseService;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysRoleService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.Constant;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/sysEnterprise"})
public class SysEnterpriseController {
    @Autowired
    private SysEnterpriseService sysEnterpriseService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysEnterpriseMenuService sysEnterpriseMenuService;
    protected static final String indexJsp = "/page/sysmanage/enterprise/enterpriseIndex";
    protected static final String addOrEditJsp = "/page/sysmanage/enterprise/addOrEdit";
    protected static final String seeMenuJsp = "/page/sysmanage/enterprise/seeMenuInfo";

    public SysEnterpriseController() {
    }

    @RequestMapping({"/index"})
    public ModelAndView index(HttpServletRequest request, String enterName) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/enterprise/enterpriseIndex");
        HashMap params = new HashMap();

        try {
            if (StringUtils.isNotEmpty(enterName)) {
                enterName = URLDecoder.decode(enterName, "UTF-8");
                mav.addObject("enterprisename", enterName);
            } else {
                mav.addObject("enterprisename", (Object)null);
            }
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        }

        params.put("enterprisename_fuzzy", enterName);
        params.put("enabledstate_notEqual", Constant.delStatus);
        String pageNo = request.getParameter("pageNo");
        int count = this.sysEnterpriseService.getCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("pageStart", pagingBean.getStart());
        params.put("pageEnd", pagingBean.getLimit());
        List<SysEnterprise> list = this.sysEnterpriseService.getList(params);
        mav.addObject("enterpriseList", list);
        mav.addObject("pagingBean", pagingBean);
        return mav;
    }

    @RequestMapping({"/addEditPage"})
    public ModelAndView addEditPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/enterprise/addOrEdit");
        String id = request.getParameter("id");
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 30);
        String expiredTime = DateUtils.DateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isNotBlank(id)) {
            SysEnterprise enterprise = this.sysEnterpriseService.findById(id);
            if (enterprise.getEnterprisetype() == 0) {
                expiredTime = DateUtils.DateToString(enterprise.getEnterpriseexpiredtime(), "yyyy-MM-dd HH:mm:ss");
            }

            mav.addObject("sysEnterprise", enterprise);
        }

        mav.addObject("expiredTime", expiredTime);
        return mav;
    }

    @RequestMapping(
            value = {"addOrEditEnterPrise"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String addOrEditEnterPrise(HttpServletRequest request, SysEnterprise enterPrise, String expiredtime) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajax = new AjaxJson();
        if (enterPrise.getEnterprisetype() == 1) {
            expiredtime = "";
        }

        if (StringUtils.isNotEmpty(expiredtime)) {
            Date date = DateUtils.StringToDate(expiredtime, "yyyy-MM-dd HH:mm:ss");
            enterPrise.setEnterpriseexpiredtime(date);
        }

        String id = enterPrise.getId();
        String msg;
        int result;
        if (StringUtils.isNotBlank(id)) {
            enterPrise.setUpdatetime(new Date());
            if (StringUtils.isNotEmpty(expiredtime)) {
                Date date = DateUtils.StringToDate(expiredtime, "yyyy-MM-dd HH:mm:ss");
                enterPrise.setEnterpriseexpiredtime(date);
            } else {
                enterPrise.setEnterpriseexpiredtime((Date)null);
            }

            if (enterPrise.getEnabledstate() == null) {
                enterPrise.setEnabledstate(0);
            }

            try {
                result = this.sysEnterpriseService.update(enterPrise);
                msg = null;
                if (result > 0) {
                    msg = requestContext.getMessage("operateSuccess");
                } else {
                    msg = requestContext.getMessage("operateFailed");
                }

                ajax.setMsg(msg);
                ajax.setSuccess(true);
            } catch (Exception var11) {
                var11.printStackTrace();
                ajax.setMsg(requestContext.getMessage("optFailExcept") + "：" + var11.getMessage());
                ajax.setSuccess(false);
            }
        } else {
            enterPrise.setCreatetime(new Date());
            enterPrise.setUpdatetime(new Date());
            enterPrise.setId(CommonUtil.getRandomUUID());
            if (enterPrise.getEnabledstate() == null) {
                enterPrise.setEnabledstate(0);
            }

            try {
                this.sysEnterpriseService.addCreateDefaultPermission(enterPrise, request);
                result = this.sysEnterpriseService.add(enterPrise);
                msg = null;
                if (result > 0) {
                    msg = requestContext.getMessage("operateSuccess");
                } else {
                    msg = requestContext.getMessage("operateFailed");
                }

                StringBuffer sb = new StringBuffer(msg);
                if (result > 0) {
                    sb.append("</br>");
                    sb.append(requestContext.getMessage("generateUser") + ":" + enterPrise.getEnterprisecode() + "_admin");
                    sb.append("</br>");
                    sb.append(requestContext.getMessage("generatePwd") + ":123456");
                }

                ajax.setMsg(sb.toString());
                ajax.setSuccess(true);
            } catch (Exception var10) {
                var10.printStackTrace();
                ajax.setMsg(requestContext.getMessage("optFailExcept") + "：" + var10.getMessage());
                ajax.setSuccess(false);
            }
        }

        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"/checkCodeExist"})
    @ResponseBody
    public String checkCodeExist(String code, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        SysEnterprise EnterCode = this.sysEnterpriseService.getByEnterPriseCode(code);
        if (EnterCode != null && !EnterCode.getId().equals(id)) {
            ajaxJson.setSuccess(true);
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/setSeeMenuInfo"})
    public ModelAndView setSeeMenuInfo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/enterprise/seeMenuInfo");
        String enterId = request.getParameter("enterId");
        mav.addObject("enterId", enterId);
        return mav;
    }

    @RequestMapping({"/getMenuTree"})
    @ResponseBody
    public String getMenuTree(HttpServletRequest request) throws Exception {
        List list = new ArrayList();
        Map<String, Object> menuParams = new HashMap();
        menuParams.put("enabledstate", 1);
        List<SysMenu> menuList = this.sysMenuService.getList(menuParams);
        List<String> filterMenu = this.sysMenuService.getFilterMenus();
        String enterId = request.getParameter("enterId");
        Map<String, Object> enterMenuParams = new HashMap();
        enterMenuParams.put("enterpriseid", enterId);
        List<SysEnterpriseMenu> enterMenuList = this.sysEnterpriseMenuService.getList(enterMenuParams);
        List<String> enterMenuIdList = new ArrayList();
        int j;
        if (enterMenuList != null && enterMenuList.size() > 0) {
            for(j = 0; j < enterMenuList.size(); ++j) {
                enterMenuIdList.add(((SysEnterpriseMenu)enterMenuList.get(j)).getMenuid());
            }
        }

        if (menuList != null && menuList.size() > 0) {
            for(j = 0; j < menuList.size(); ++j) {
                SysMenu menu = (SysMenu)menuList.get(j);
                if (!filterMenu.contains(menu.getMenucode())) {
                    HashMap<String, Object> menuHm = new HashMap();
                    menuHm.put("name", menu.getMenuname());
                    menuHm.put("id", menu.getId());
                    menuHm.put("pId", menu.getPid());
                    menuHm.put("open", true);
                    if (enterMenuIdList != null && enterMenuIdList.contains(menu.getId())) {
                        menuHm.put("checked", true);
                    }

                    list.add(menuHm);
                }
            }
        }

        return JSONObject.toJSONString(list);
    }

    @RequestMapping({"/saveSeeMenu"})
    @ResponseBody
    public String saveSeeMenu(HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajax = new AjaxJson();
        String menuArray = request.getParameter("menuArray");
        String enterId = request.getParameter("enterId");
        String roleId = null;

        try {
            Map<String, Object> params = new HashMap();
            SysEnterprise enterPrise = this.sysEnterpriseService.findById(enterId);
            if (enterPrise != null) {
                params.put("rolecode", enterPrise.getEnterprisecode() + "_admin");
                params.put("enterpriseid", enterId);
                SysRole role = this.sysRoleService.getByRoleId(params);
                if (role != null) {
                    roleId = role.getId();
                }
            }

            this.sysEnterpriseMenuService.saveEnterMenu(menuArray, enterId, roleId);
            String msg = requestContext.getMessage("operateSuccess");
            ajax.setMsg(msg);
            ajax.setSuccess(true);
        } catch (Exception var10) {
            var10.printStackTrace();
            ajax.setMsg(requestContext.getMessage("optFailExcept") + "：" + var10.getMessage());
            ajax.setSuccess(false);
        }

        return JSONObject.toJSONString(ajax);
    }
}
