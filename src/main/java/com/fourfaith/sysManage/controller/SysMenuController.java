//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.Constant;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/sysMenu"})
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;
    protected static final String indexJsp = "/page/sysmanage/menu/menuIndex";
    protected static final String childMenuJsp = "/page/sysmanage/menu/childMenuPage";
    protected static final String addJsp = "/page/sysmanage/menu/add";
    protected static final String editJsp = "/page/sysmanage/menu/edit";

    public SysMenuController() {
    }

    @RequestMapping({"/index"})
    public ModelAndView index(HttpServletRequest request, String menuName) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/menu/menuIndex");
        HashMap params = new HashMap();

        try {
            if (StringUtils.isNotEmpty(menuName)) {
                menuName = URLDecoder.decode(menuName, "UTF-8");
                mav.addObject("menuname", menuName);
            } else {
                mav.addObject("menuname", (Object)null);
            }
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        }

        params.put("pid", 0);
        params.put("menuname_fuzzy", menuName);
        params.put("enabledstate_notEqual", Constant.delStatus);
        String pageNo = request.getParameter("pageNo");
        int count = this.sysMenuService.getCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("pageStart", pagingBean.getStart());
        params.put("pageEnd", pagingBean.getLimit());
        List<SysMenu> list = this.sysMenuService.getList(params);
        mav.addObject("menuList", list);
        mav.addObject("pagingBean", pagingBean);
        return mav;
    }

    @RequestMapping({"/menuPage"})
    public ModelAndView menuPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/menu/childMenuPage");
        String id = request.getParameter("id");
        mav.addObject("menuId", id);
        return mav;
    }

    @RequestMapping({"/getChildMenuList"})
    @ResponseBody
    public String getByAppId(HttpServletRequest request, String menuId) {
        RequestContext requestContext = new RequestContext(request);
        SysMenu parentMenu = this.sysMenuService.findById(menuId);
        Map<String, Object> params = new HashMap();
        params.put("pid", menuId);
        params.put("enabledstate_notEqual", Constant.delStatus);
        List<SysMenu> list = this.sysMenuService.getList(params);
        JSONArray jsonarray = new JSONArray();
        JSONObject menuObject = new JSONObject();
        menuObject.put("id", menuId);
        menuObject.put("name", parentMenu.getMenuname() + "【" + requestContext.getMessage("parentMenu") + "】");
        menuObject.put("pId", 0);
        menuObject.put("open", true);
        jsonarray.add(menuObject);
        Iterator var10 = list.iterator();

        while(var10.hasNext()) {
            SysMenu menu = (SysMenu)var10.next();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", menu.getId());
            jsonObject.put("pId", menuId);
            jsonObject.put("name", menu.getMenuname());
            jsonObject.put("open", true);
            jsonarray.add(jsonObject);
        }

        return JSONObject.toJSONString(jsonarray);
    }

    @RequestMapping({"/addPage"})
    public ModelAndView addPage(HttpServletRequest request, String id, String level, String menuName) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/menu/add");
        mav.addObject("parentMenuId", id);
        mav.addObject("level", level + 2);

        try {
            if (StringUtils.isNotEmpty(menuName)) {
                menuName = URLDecoder.decode(menuName, "UTF-8");
                mav.addObject("menuName", menuName);
            } else {
                mav.addObject("menuName", (Object)null);
            }
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
        }

        Integer order = this.sysMenuService.getMaxMenuOrder();
        if (order == null) {
            order = 0;
        }

        mav.addObject("order", order + 1);
        return mav;
    }

    @RequestMapping({"/addMenu"})
    @ResponseBody
    public String addMenu(HttpServletRequest request, SysMenu menu) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajax = new AjaxJson();
        menu.setId(CommonUtil.getRandomUUID());
        menu.setMenucreatetime(new Date());
        menu.setMenuupdatetime(new Date());
        if (menu.getEnabledstate() == null) {
            menu.setEnabledstate(0);
        }

        try {
            Integer result = this.sysMenuService.add(menu);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            ajax.setMsg(msg);
            ajax.setSuccess(true);
        } catch (Exception var7) {
            var7.printStackTrace();
            ajax.setMsg(requestContext.getMessage("optFailExcept") + "：" + var7.getMessage());
            ajax.setSuccess(false);
        }

        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"/editPage"})
    public ModelAndView editPage(HttpServletRequest request, String id, String parentName) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/menu/edit");
        SysMenu menu = this.sysMenuService.findById(id);
        mav.addObject("sysMenu", menu);
        mav.addObject("parentName", parentName);
        return mav;
    }

    @RequestMapping({"editMenu"})
    @ResponseBody
    public String editApp(HttpServletRequest request, SysMenu menu) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajax = new AjaxJson();
        menu.setMenuupdatetime(new Date());
        if (menu.getEnabledstate() == null) {
            menu.setEnabledstate(0);
        }

        menu.setEnabledstate(1);

        try {
            Integer result = this.sysMenuService.update(menu);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            ajax.setMsg(msg);
            ajax.setSuccess(true);
        } catch (Exception var7) {
            var7.printStackTrace();
            ajax.setMsg(requestContext.getMessage("optFailExcept") + "：" + var7.getMessage());
            ajax.setSuccess(false);
        }

        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"delMenu"})
    @ResponseBody
    public String delMenu(HttpServletRequest request, String id) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajax = new AjaxJson();
        SysMenu menu = this.sysMenuService.findById(id);
        menu.setEnabledstate(-1);

        try {
            Integer result = this.sysMenuService.update(menu);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            ajax.setMsg(msg);
            ajax.setSuccess(true);
        } catch (Exception var8) {
            var8.printStackTrace();
            ajax.setMsg(requestContext.getMessage("optFailExcept") + "：" + var8.getMessage());
            ajax.setSuccess(false);
        }

        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"/checkMenuCodeExist"})
    @ResponseBody
    public String checkMenuCodeExist(String menuCode, String id) {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (StringUtils.isNotEmpty(menuCode)) {
                menuCode = URLDecoder.decode(menuCode, "UTF-8");
            } else {
                menuCode = null;
            }
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
        }

        SysMenu menu = this.sysMenuService.getByMenuCode(menuCode);
        if (menu != null && !menu.getId().equals(id)) {
            ajaxJson.setSuccess(true);
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }
}
