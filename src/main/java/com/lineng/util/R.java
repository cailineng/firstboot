package com.lineng.util;

import java.util.HashMap;
import java.util.Map;

/**
 * restful 接口返回标准 Map 类
 *
 * @author 杰明Jamin
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;


    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error(1, "error");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}