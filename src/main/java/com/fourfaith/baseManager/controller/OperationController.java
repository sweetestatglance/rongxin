

package com.fourfaith.baseManager.controller;

import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.service.StAddvcdDService;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/operation"})
public class OperationController {
    protected static final String indexJsp = "/page/operation/index";
    protected static final String listJsp = "/page/operation/list";
    @Autowired
    private SysRoleAreaService sysRoleAreaService;
    @Autowired
    private StAddvcdDService stAddvcdDService;
    @Autowired
    private StStbprpBService stStbprpBService;

    public OperationController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/operation/index");
        return mav;
    }

    @RequestMapping(
            value = {"/list"},
            method = {RequestMethod.GET}
    )
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/operation/list");
        String addvcdId = request.getParameter("id");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> map = new HashMap();
        map.put("roleid", login_user.getSysrole().getId());
        List<String> addvcdDIdList = this.sysRoleAreaService.getAddvcdDIdList(map);
        List<String> aIdList = new ArrayList();
        List<StAddvcdD> staddvcdD = null;
        staddvcdD = getChildList(this.stAddvcdDService, addvcdId);
        StAddvcdD addvcdD;
        String s_start;
        if (staddvcdD != null && staddvcdD.size() > 0) {
            Iterator var10 = staddvcdD.iterator();

            while(var10.hasNext()) {
                addvcdD = (StAddvcdD)var10.next();
                s_start = addvcdD.getId();
                if (addvcdDIdList.contains(s_start)) {
                    aIdList.add(s_start);
                }
            }
        }

        addvcdD = null;
        Integer count = 0;
        s_start = request.getParameter("pageNo");
        Map<String, Object> params = new HashMap();
        List<StStbprpB> stbprpBList = null;
        PagingBean pagingBean;
        if (addvcdDIdList != null && addvcdDIdList.size() > 0) {
            if (aIdList != null && aIdList.size() > 0) {
                params.put("addvcdDId", aIdList);
            } else {
                params.put("addvcdDId", (Object)null);
            }

            count = this.stStbprpBService.getCount(params);
            pagingBean = PageUtil.page(s_start, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
            params.put("pageStart", pagingBean.getStart());
            params.put("pageEnd", pagingBean.getLimit());
            stbprpBList = this.stStbprpBService.getList(params);
        } else {
            pagingBean = PageUtil.page(s_start, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        }

        mav.addObject("pagingBean", pagingBean);
        mav.addObject("stbprpBList", stbprpBList);
        return mav;
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
