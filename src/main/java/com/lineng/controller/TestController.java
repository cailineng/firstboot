package com.lineng.controller;

import com.alibaba.fastjson.JSONObject;
import com.lineng.model.ChannelStatisticsVO;
import com.lineng.util.HttpUtil;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.AlgorithmParameters;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
public class TestController {

    @RequestMapping("/testExcel")
    @ResponseBody
    public String testExcel(ModelMap map) {
        try {
            /*String json = HttpUtil.doGet("http://api.map.baidu.com/location/ip?ip=117.136.31.224&ak=r8y37j2Tib7WPqkvS7temMhQ&coor=bd09ll");
            JSONObject jsonObj = JSONObject.parseObject(json);
            String city = ((JSONObject) ((JSONObject) jsonObj.get("content")).get("address_detail")).get("city").toString();
            System.out.println(city);*/

            File file = new File("D:\\data3.xlsx");
            System.out.println("测试");
            FileInputStream fis = new FileInputStream(file);
            System.out.println("ceshi2");
            String str = "";
            if (file != null) {
                // 打开HSSFWorkbook
                Workbook wb = null;
                wb = new XSSFWorkbook(fis);
                System.out.println("ceshi3");
                XSSFSheet st = (XSSFSheet) wb.getSheetAt(0);

                ExecutorService executor = Executors.newFixedThreadPool(80);

                List<Future> futureList = new ArrayList<Future>();
                for (int rowIndex = 0; rowIndex < 10904; rowIndex++) {
                    XSSFRow row = st.getRow(rowIndex);
                    if(rowIndex!=0) {
                        TaskCallable taskCallable = new TaskCallable(row);
                        Future future = executor.submit(taskCallable);
                        futureList.add(future);
                        System.out.println(rowIndex);
                    }
                    if(rowIndex%80==0){
                        Thread.sleep(1000);
                    }
                }
                for(int k=0;k<futureList.size();k++){
                    futureList.get(k).get();
                }
                //创建文件流
                OutputStream stream = new FileOutputStream(file);
                //写入数据
                wb.write(stream);
                //关闭文件流
                stream.close();
                System.out.println("end");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return "testThe";
    }


    @RequestMapping("/testSmall")
    @ResponseBody
    public Map testSmall(String code) throws Exception {
        Map map = new HashMap();
        System.out.println(code);
        String json = HttpUtil.doGet("https://api.weixin.qq.com/sns/jscode2session?appid=wxd9e12be339b704b0&secret=4ce196f0b66cd9464d8777124da4dcdf&js_code="+code+"&grant_type=authorization_code");
        System.out.println(json);
        JSONObject jsonObj = JSONObject.parseObject(json);
        map.put("session_key",jsonObj.get("session_key").toString());
        return map;
    }


    /**
     * 解密用户敏感数据获取用户信息
     * @param sessionKey 数据进行加密签名的密钥
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv 加密算法的初始向量
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public JSONObject getUserInfo(String encryptedData, String sessionKey, String iv){
       /// 被加密的数据
        byte[] dataByte = Base64.getDecoder().decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.getDecoder().decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.getDecoder().decode(iv);
       try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                System.out.println(result);
                return JSONObject.parseObject(result);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/testvo")
    @ResponseBody
    public String testvo(ChannelStatisticsVO  channelStatisticsVO) {
        System.out.println("haha");
       return channelStatisticsVO.toString();
    }
}
