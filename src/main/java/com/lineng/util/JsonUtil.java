package com.lineng.util;


import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    public static void writeObjectToJson(Object obj) throws Exception{
        System.out.println(mapToJson(obj));
    }


    public static String mapToJson(Object obj){
        String json=null;
        ObjectMapper mapper=null;
        try{
            mapper = new ObjectMapper();
            json=mapper.writeValueAsString(obj);//把map或者是list转换成
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            mapper=null;
            return json;
        }
    }
}

