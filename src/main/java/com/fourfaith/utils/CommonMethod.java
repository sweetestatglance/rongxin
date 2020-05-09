//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.service.StAddvcdDService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommonMethod {
    public CommonMethod() {
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
