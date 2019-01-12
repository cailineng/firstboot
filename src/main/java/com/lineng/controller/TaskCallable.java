package com.lineng.controller;

import com.alibaba.fastjson.JSONObject;
import com.lineng.util.HttpUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.concurrent.Callable;

/**
 * Created by loongshawn on 2017/7/17.
 *
 * 并发任务
 */
public class TaskCallable implements Callable<Integer> {

    private XSSFRow row;

    public TaskCallable(XSSFRow row){
        this.row = row;
    }

    public Integer call()  {
        String ip =null;
        JSONObject jsonObj = null;
        try {
            if(row.getCell(5)==null){
            //    row.createCell(6).setCellValue("未知IP");
                return 0;
            }
            ip = row.getCell(5).toString();
            String json = null;
            json = HttpUtil.doGet("http://api.map.baidu.com/location/ip?ip="+ip+"&ak=r8y37j2Tib7WPqkvS7temMhQ&coor=bd09ll");
            jsonObj = JSONObject.parseObject(json);
            if(!jsonObj.get("status").toString().equals("1")) {
                String city = ((JSONObject) ((JSONObject) jsonObj.get("content")).get("address_detail")).get("city").toString();
                System.out.println(city);
                row.createCell(6).setCellValue(city);
                return 1;
            }else if(jsonObj.get("status").toString().equals("401")){
             //   row.createCell(6).setCellValue("请求过快");
                return 0;
            }else{
              //  row.createCell(6).setCellValue("未识别");
                return 0;
            }
        } catch (Exception e) {
            if(jsonObj!=null) {
                System.out.println("--------------------------------" + ip + "" + jsonObj.toString());
            }else{
                System.out.println("--------------------------------" + ip);
            }
            e.printStackTrace();
            if(row!=null) {
               // row.createCell(6).setCellValue("未知");
            }
            return 0;
        }
    }
}
