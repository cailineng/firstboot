package com.lineng.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lineng.util.MD5;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) throws Exception {
        String url = "http://qiyukf.com/openapi/wechat/miniprogram/access_token?appKey=wxd9e12be339b704b0&time=%d&checksum=%s";
        Map<String,String> params = new HashMap<>();
        params.put("appid", "wxd9e12be339b704b0");
        params.put("secret", "4ce196f0b66cd9464d8777124da4dcdf");
        long time = System.currentTimeMillis() / 1000;
       String checksum = QiyuPushCheckSum.encode("4ce196f0b66cd9464d8777124da4dcdf",  MD5.string2MD5(params.toString()).toLowerCase(), String.valueOf(time));
        url = String.format(url, time, checksum);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(params));
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        System.out.println(result);
    }
}
