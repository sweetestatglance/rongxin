//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.controller;

import com.fourfaith.siteManage.model.DevicePhoto;
import com.fourfaith.siteManage.service.DevicePhotoService;
import com.fourfaith.sysManage.model.SysEnterprise;
import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserRole;
import com.fourfaith.sysManage.service.SysEnterpriseService;
import com.fourfaith.sysManage.service.SysRoleService;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.PropertiesUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/devicePhoto"})
public class DevicePhotoController {
    @Autowired
    private DevicePhotoService devicePhotoService;
    protected static final String videoIEJsp = "/page/sysmanage/video/videoIE";
    protected static final String listJsp = "/page/stbprpb/task/config/photolist";
    @Autowired
    private SysEnterpriseService sysEnterpriseService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    public DevicePhotoController() {
    }

    @RequestMapping({"/list"})
    public ModelAndView photoList(HttpServletRequest request, String deviceNo, String beginTime, String endTime, String query_photoType) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/task/config/photolist");
        Map<String, Object> params = new HashMap();
        String pageNo = request.getParameter("pageNo");
        if (beginTime == null) {
            beginTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
        }

        if (endTime == null) {
            endTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59");
        }

        params.put("stcd", deviceNo);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        params.put("photoType", query_photoType);
        int count = this.devicePhotoService.getCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "5", count, 5);
        params.put("start", pagingBean.getStart());
        params.put("limit", pagingBean.getLimit());
        List<DevicePhoto> photoList = this.devicePhotoService.getList(params);
        mav.addObject("photoList", photoList);
        mav.addObject("pagingBean", pagingBean);
        mav.addObject("deviceNo", deviceNo);
        mav.addObject("stcd", deviceNo);
        mav.addObject("beginTime", beginTime);
        mav.addObject("endTime", endTime);
        mav.addObject("query_photoType", query_photoType);
        return mav;
    }

    @RequestMapping({"/videoIEPage"})
    public ModelAndView videoIEPage(HttpServletRequest request) {
        String deviceNo = request.getParameter("name");
        String dvrAdd = request.getParameter("dvrAdd");
        String dvrCode = request.getParameter("dvrCode");
        String videochannel = request.getParameter("videochannel");
        String dvrIp = "";
        if (StringUtils.isNotEmpty(dvrAdd)) {
            String[] array = dvrAdd.split(":");
            if (array.length > 0) {
                dvrAdd = array[0];
                if (array.length > 1) {
                    dvrIp = array[1];
                }
            }
        }

        ModelAndView mav = new ModelAndView("/page/sysmanage/video/videoIE");
        mav.addObject("dvrAdd", dvrAdd);
        mav.addObject("dvrIp", dvrIp);
        mav.addObject("dvrCode", dvrCode);
        mav.addObject("stcd", deviceNo);
        mav.addObject("videochannel", videochannel);
        int videochannelNum = 0;
        String binaryVideochannel = "";
        if (videochannel != null) {
            int arrNum = videochannel.split(",").length;
            if (arrNum % 2 == 1 && arrNum != 1) {
                videochannelNum = arrNum + 1;
            } else {
                videochannelNum = arrNum;
            }

            for(int i = 1; i < 9; ++i) {
                if (videochannel.contains(String.valueOf(i))) {
                    binaryVideochannel = "1" + binaryVideochannel;
                } else {
                    binaryVideochannel = "0" + binaryVideochannel;
                }
            }
        }

        mav.addObject("videochannelNum", videochannelNum);
        mav.addObject("binaryVideochannel", Integer.parseInt(binaryVideochannel, 2));
        String videoUrl = PropertiesUtils.getPara("videoDlUrl");
        mav.addObject("videoUrl", videoUrl);
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        SysEnterprise enterprise = this.sysEnterpriseService.findById(login_user.getEnterpriseid());
        String code = enterprise.getEnterprisecode();
        String adminRoleCode = code + "_admin";
        HashMap<String, Object> roleParams = new HashMap();
        roleParams.put("userid", login_user.getId());
        List<SysUserRole> userRoleList = this.sysUserRoleService.getList(roleParams);
        int isAdmin = 0;
        if (userRoleList != null && userRoleList.size() > 0) {
            SysRole role = this.sysRoleService.findById(((SysUserRole)userRoleList.get(0)).getRoleid());
            if (role != null && role.getRolecode().equals(adminRoleCode)) {
                isAdmin = 1;
            } else {
                isAdmin = 0;
            }
        }

        mav.addObject("isAdmin", Integer.valueOf(isAdmin));
        return mav;
    }
}
