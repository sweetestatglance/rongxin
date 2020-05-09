//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import com.alibaba.druid.util.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class PageUtil {
    protected static PagingBean pagingBean;

    public PageUtil() {
    }

    public static PagingBean page(String s_start, String s_limit, int count, int defaultPageSize) {
        int start = 1;
        int limit = defaultPageSize;
        if (!StringUtils.isEmpty(s_start)) {
            start = NumberUtils.toInt(s_start);
        }

        if (!StringUtils.isEmpty(s_limit)) {
            limit = NumberUtils.toInt(s_limit);
        }

        start = (start - 1) * limit;
        pagingBean = new PagingBean(start, limit);
        pagingBean.setTotalItems(count);
        int pageNo = start / limit;
        pagingBean.setPageNo(pageNo + 1);
        pagingBean.setStart(start);
        pagingBean.setLimit(limit);
        int pageNum = 0;

        if (count % limit == 0) {
            pageNum = count / limit;
        } else {
            pageNum = count / limit + 1;
        }

        pagingBean.setPageNum(pageNum);
        if (pageNo + 1 == 1) {
            pagingBean.setHasPrePage(false);
        } else {
            pagingBean.setHasPrePage(true);
        }

        if (pageNo + 1 == pageNum) {
            pagingBean.setHasNextPage(false);
        } else if (pageNum > 0) {
            pagingBean.setHasNextPage(true);
        } else {
            pagingBean.setHasNextPage(false);
        }

        return pagingBean;
    }
}
