
package com.fourfaith.utils;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class AjaxJson {
    private boolean success = true;
    private String msg = "操作成功";
    private Object obj = null;
    private Integer count = 0;
    private Map<String, Object> attributes;

    public AjaxJson() {
    }

    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return this.obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setOptFailed(String msg) {
        this.msg = "操作失败";
        if (StringUtils.isNotBlank(msg)) {
            this.msg = msg;
        }

        this.success = false;
    }

    public String getJsonStr() {
        JSONObject obj = new JSONObject();
        obj.put("success", this.isSuccess());
        obj.put("msg", this.getMsg());
        obj.put("obj", this.obj);
        obj.put("count", this.count);
        obj.put("attributes", this.attributes);
        return obj.toJSONString();
    }
}
