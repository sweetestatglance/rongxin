//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.imgManage.controller;

import com.fourfaith.imgManage.model.StImgMonit;
import com.fourfaith.imgManage.service.StImgMonitService;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/stImage"})
public class ImageController {
    protected static final String indexJsp = "/page/image/index";
    protected static final String waterRainJsp = "/page/image/commonList";
    protected static final String waterListJsp = "/page/image/commonList";
    protected static final String rainListJsp = "/page/image/commonList";
    protected static final String szListJsp = "/page/image/commonList";
    @Autowired
    private StImgMonitService stImgMonitService;

    public ImageController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/image/index");
        return mav;
    }

    @RequestMapping(
            value = {"/list"},
            method = {RequestMethod.GET}
    )
    public ModelAndView list(HttpServletRequest request, boolean isDevice) throws UnsupportedEncodingException {
        ModelAndView mav = new ModelAndView("/page/image/commonList");
        String sttp = request.getParameter("sttp") == "" ? null : request.getParameter("sttp");
        String query_stcdName = request.getParameter("query_stcdName") == "" ? null : request.getParameter("query_stcdName");
        if (StringUtils.isNotBlank(query_stcdName)) {
            query_stcdName = URLDecoder.decode(query_stcdName, "UTF-8");
        }

        String nodeIds = request.getParameter("nodeIds");
        List<String> stcdList = null;
        if (StringUtils.isNotBlank(nodeIds)) {
            stcdList = new ArrayList(Arrays.asList(nodeIds.split(",")));
        } else {
            stcdList = new ArrayList(Arrays.asList(""));
        }

        Map<String, Object> params = new HashMap();
        if (isDevice) {
            params.put("stcdList", stcdList);
        } else if (stcdList.size() == 1) {
            params.put("addvcd", stcdList.get(0));
        } else {
            params.put("addvcdList", stcdList);
        }

        params.put("query_stcdName", query_stcdName);
        params.put("sttp", sttp);
        String pageNo = request.getParameter("pageNo");
        int count = this.stImgMonitService.getImgCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "15", count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("start", pagingBean.getStart());
        params.put("limit", pagingBean.getLimit());
        List<StImgMonit> imgList = this.stImgMonitService.getImgList(params);
        if (sttp != null) {
            if (!sttp.equals("0") && !sttp.equals("1")) {
                if (sttp.equals("2")) {
                    mav = new ModelAndView("/page/image/commonList");
                } else if (sttp.equals("4")) {
                    mav = new ModelAndView("/page/image/commonList");
                }
            } else {
                mav = new ModelAndView("/page/image/commonList");
            }
        }

        mav.addObject("imgList", imgList);
        mav.addObject("pagingBean", pagingBean);
        return mav;
    }
}
