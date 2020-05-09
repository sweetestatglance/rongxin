//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service;

import com.fourfaith.siteManage.model.StRvfcchB;
import java.util.List;
import java.util.Map;

public interface StRvfcchBService {
    Integer add(StRvfcchB var1);

    List<StRvfcchB> getList(Map<String, Object> var1);

    Integer delete(String var1);

    StRvfcchB findById(String var1);

    Integer update(StRvfcchB var1);

    StRvfcchB findByStcd(String var1);
}
