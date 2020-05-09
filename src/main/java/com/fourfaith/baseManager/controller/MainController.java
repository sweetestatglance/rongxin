package com.fourfaith.baseManager.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.sysManage.model.SysEnterprise;
import com.fourfaith.sysManage.model.SysLog;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.sysManage.model.SysRolePermission;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserRole;
import com.fourfaith.sysManage.service.SysEnterpriseService;
import com.fourfaith.sysManage.service.SysLogService;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysRolePermissionService;
import com.fourfaith.sysManage.service.SysRoleService;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.sysManage.service.SysUserService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.Constant;
import com.fourfaith.utils.LocalMAC;
import com.fourfaith.utils.PropertiesUtils;
import com.fourfaith.utils.SignProvider;
import com.fourfaith.utils.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
public class MainController {
    protected static final String indexJsp = "/index";
    protected static final String loginJsp = "/login";
    protected static final String errorJsp = "/errorAuthority";
    protected static final String versionJsp = "versionInfo";
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysEnterpriseService sysEnterpriseService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @Autowired
    private SysLogService sysLogService;

    public MainController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = null;
        String expiry = PropertiesUtils.getPara("License.expiry");
        String macAddress = "";

        String plainText;
        try {
            plainText = LocalMAC.getOSName();
            if (!plainText.equals("unix") && !plainText.equals("linux")) {
                macAddress = LocalMAC.getLocalMac();
            } else {
                macAddress = LocalMAC.getUnixMACAddress();
            }
        } catch (Exception var18) {
            var18.printStackTrace();
        }

        plainText = macAddress + "," + expiry;
        String pubKey = PropertiesUtils.getPara("PubKey");
        String signature = PropertiesUtils.getPara("Signature");
        SignProvider.verify(pubKey.getBytes(), plainText, signature.getBytes());
        boolean result = true;
        if (result) {
            mav = new ModelAndView("/index");
            SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
            if (login_user == null) {
                mav.setViewName("/login");
            } else {
                mav.setViewName("/index");
                mav.addObject("login_user", login_user);
                List<SysMenu> menuList = new ArrayList();
                if (login_user.getIssupermanager()) {
                    SysMenu menu = this.sysMenuService.getByMenuCode(Constant.xtglMenuCode);
                    ((List)menuList).add(menu);
                } else if (login_user.getSysrole() != null && login_user.getSysrole().getEnabledstate() == 1) {
                    String roleId = login_user.getSysrole().getId();
                    menuList = this.sysMenuService.getListByRoleId(roleId);
                    boolean hasAlarm = false;
                    boolean hasNotice = false;
                    Map<String, Object> params = new HashMap();
                    params.put("roleid", roleId);
                    params.put("enabledstate", 1);
                    List<SysMenu> allMenuList = this.sysMenuService.getListByParams(params);
                    if (allMenuList != null) {
                        Iterator var17 = allMenuList.iterator();

                        while(var17.hasNext()) {
                            SysMenu sysMenu = (SysMenu)var17.next();
                            if (sysMenu.getMenucode().equals("deviceAlarm")) {
                                hasAlarm = true;
                            }

                            if (sysMenu.getMenucode().equals("normalNotice")) {
                                hasNotice = true;
                            }
                        }
                    }

                    mav.addObject("hasNotice", hasNotice);
                    mav.addObject("hasAlarm", hasAlarm);
                }

                mav.addObject("menuList", menuList);
            }
        } else {
            mav = new ModelAndView("/errorAuthority");
        }

        return mav;
    }

    @RequestMapping(
            value = {"/userLogin"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String userLogin(HttpServletRequest request, HttpServletResponse response) {
        RequestContext requestContext = new RequestContext(request);
        HashMap<String, Object> hm = new HashMap();
        hm.put("success", false);
        hm.put("msg", requestContext.getMessage("loginWrong"));
        Date date = new Date();
        String mUserName = request.getParameter("userName");
        String mPwd = request.getParameter("passWord");
        String flagRemember = request.getParameter("flagRemember");
        if ("1".equals(flagRemember)) {
            String loginInfo = mUserName + "," + mPwd + "," + flagRemember;
            Cookie userCookie = new Cookie("loginInfo", loginInfo);
            userCookie.setMaxAge(2592000);
            userCookie.setPath("/");
            response.addCookie(userCookie);
        } else {
            Cookie newCookie = new Cookie("loginInfo", (String)null);
            newCookie.setMaxAge(0);
            newCookie.setPath("/");
            response.addCookie(newCookie);
        }

        boolean isSimplePwd = false;
        if (mPwd.equals("123456")) {
            isSimplePwd = true;
        }

        hm.put("isSimplePwd", isSimplePwd);
        if (!StringUtils.isNullOrEmpty(mUserName) && !StringUtils.isNullOrEmpty(mPwd)) {
            Map<String, Object> params = new HashMap();
            boolean flag = false;
            SysUser resultUser = new SysUser();
            SysUser sysUser;
            String area;
            if (this.checkAdmin(mUserName, mPwd)) {
                flag = true;
                hm.put("success", true);
                sysUser = new SysUser();
                sysUser.setUsername(mUserName);
                sysUser.setIssupermanager(true);
                resultUser = sysUser;
            } else {
                params.put("username", mUserName);
                sysUser = this.sysUserService.findByUserName(mUserName);
                if (sysUser != null) {
                    area = sysUser.getUserpwdsalt();
                    if (StringUtils.encryptMd5(mPwd + area).equals(sysUser.getUserpwd())) {
                        SysEnterprise enterprise = this.sysEnterpriseService.findById(sysUser.getEnterpriseid());
                        if (sysUser.getEnabledstate() == 0) {
                            hm.put("success", false);
                            hm.put("msg", requestContext.getMessage("loginDisabled"));
                        } else if (sysUser.getEnabledstate() == -1) {
                            hm.put("success", false);
                            hm.put("msg", requestContext.getMessage("loginDelete"));
                        } else if (enterprise.getEnabledstate() == 0) {
                            hm.put("success", false);
                            hm.put("msg", requestContext.getMessage("enterpriseDisabled"));
                        } else if (enterprise.getEnterprisetype() == 2 && enterprise.getEnterpriseexpiredtime() != null && enterprise.getEnterpriseexpiredtime().before(date)) {
                            hm.put("success", false);
                            hm.put("msg", requestContext.getMessage("loginExpired"));
                        } else {
                            HashMap<String, Object> roleParams = new HashMap();
                            roleParams.put("userid", sysUser.getId());
                            List<SysUserRole> userRoleList = this.sysUserRoleService.getList(roleParams);
                            if (userRoleList != null && userRoleList.size() > 0) {
                                SysRole role = this.sysRoleService.findById(((SysUserRole)userRoleList.get(0)).getRoleid());
                                if (role.getEnabledstate() == 0) {
                                    hm.put("success", false);
                                    hm.put("msg", requestContext.getMessage("loginRoleExpired"));
                                } else if (role.getEnabledstate() == -1) {
                                    hm.put("success", false);
                                    hm.put("msg", requestContext.getMessage("loginRoleDelete"));
                                } else {
                                    HashMap<String, Object> rpParams = new HashMap();
                                    rpParams.put("roleid", role.getId());
                                    List<SysRolePermission> rpList = this.sysRolePermissionService.getList(rpParams);
                                    if (rpList != null && rpList.size() > 0) {
                                        flag = true;
                                        hm.put("success", true);
                                    } else {
                                        hm.put("success", false);
                                        hm.put("msg", requestContext.getMessage("loginRoleNoAuthority"));
                                    }
                                }

                                sysUser.setSysrole(role);
                            } else {
                                hm.put("success", false);
                                hm.put("msg", requestContext.getMessage("loginRoleNoConfig"));
                            }

                            sysUser.setIssupermanager(false);
                            sysUser.setSysenterprise(enterprise);
                            resultUser = sysUser;
                        }
                    }
                }
            }

            if (flag) {
                String ip = CommonUtil.getIpAddr(request);
                area = "";
                resultUser.setLoginIp(ip);
                resultUser.setLoginArea(area);
                resultUser.setLoginTime(new Date());
                CommonUtil.setLoginUserInfo(request, resultUser);
                if (!this.checkAdmin(mUserName, mPwd)) {
                    SysLog log = new SysLog();
                    log.setId(CommonUtil.getRandomUUID());
                    log.setUserid(resultUser.getId());
                    log.setEnterpriseid(resultUser.getEnterpriseid());
                    log.setLoginip(ip);
                    log.setLogcontent(requestContext.getMessage("signIn") + requestContext.getMessage("headerTitle"));
                    log.setLogintime(new Date());
                    log.setLogtime(new Date());
                    log.setLoginarea(area);
                    this.sysLogService.add(log);
                }
            }
        }

        return JSONObject.toJSONString(hm);
    }

    public boolean checkAdmin(String userName, String pwd) {
        boolean flag = false;
        String root_username = PropertiesUtils.getPara("root_username");
        String root_pwd = PropertiesUtils.getPara("root_pwd");
        String rootsalt = "1236547890000";
        String newPwd = StringUtils.encryptMd5(pwd);
        newPwd = StringUtils.encryptMd5(newPwd + rootsalt);
        if (root_username.equals(userName) && root_pwd.equals(newPwd)) {
            flag = true;
        }

        return flag;
    }

    @RequestMapping({"/logout"})
    @ResponseBody
    public String logout(HttpServletRequest request) {
        Map<String, Object> hm = new HashMap();
        CommonUtil.setLoginUserInfo(request, (SysUser)null);
        request.getSession().invalidate();
        hm.put("success", true);
        return JSONObject.toJSONString(hm);
    }

    @RequestMapping(
            value = {"/versionInfo"},
            method = {RequestMethod.POST}
    )
    public ModelAndView versionInfo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("versionInfo");
        return mav;
    }
}
