//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.controller;

import com.fourfaith.siteManage.model.DeviceTask;
import com.fourfaith.siteManage.service.DeviceTaskService;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.RtuCodeEnum;
import com.fourfaith.utils.StringUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/deviceTask"})
public class DeviceTaskController {
    protected static final String listJsp = "/page/stbprpb/task/list";
    protected static final String taskDetailJsp = "/page/stbprpb/task/detail";
    @Autowired
    private DeviceTaskService deviceTaskService;

    public DeviceTaskController() {
    }

    @RequestMapping({"list"})
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/task/list");
        String query_rtucode = request.getParameter("query_rtucode");
        Map<String, Object> param = new HashMap();
        List<String> stcdList = new ArrayList();
        String dids = request.getParameter("dids");
        String flag = request.getParameter("flag");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if (dids != null) {
            String[] idArray = dids.split(",");

            for(int i = 0; i < idArray.length; ++i) {
                stcdList.add(idArray[i]);
            }

            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (beginTime == null) {
                beginTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
            }

            if (endTime == null) {
                endTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59");
            }

            param.put("beginTime", beginTime);
            param.put("endTime", endTime);
            if (!StringUtils.isNullOrEmpty(query_rtucode)) {
                param.put("taskcode", query_rtucode);
            }

            param.put("stcd", stcdList);
            String pageNo = request.getParameter("pageNo");
            int count = this.deviceTaskService.getTaskByStcdCount(param);
            PagingBean pagingBean = PageUtil.page(pageNo, "7", count, PagingBean.DEFAULT_PAGE_SIZE);
            param.put("pageStart", pagingBean.getStart());
            param.put("pageEnd", pagingBean.getLimit());
            List<DeviceTask> taskList = this.deviceTaskService.getTaskByStcd(param);
            mav.addObject("taskList", taskList);
            mav.addObject("rtuCodeMap", RtuCodeEnum.getMapSource());
            mav.addObject("query_rtucode", query_rtucode);
            mav.addObject("pagingBean", pagingBean);
            mav.addObject("beginTime", beginTime);
            mav.addObject("endTime", endTime);
            mav.addObject("dids", dids);
            mav.addObject("flag", flag);
        }

        return mav;
    }

    @RequestMapping({"/taskDetail"})
    public ModelAndView taskDetail(String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/stbprpb/task/detail");
        DeviceTask deviceTask = this.deviceTaskService.findById(id);
        mav.addObject("deviceTask", deviceTask);
        return mav;
    }
}
