package com.lineng.controller;

import com.google.gson.JsonObject;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ElasticSearchController {
    private static String host="192.168.192.254"; // 服务器地址

    private static int port=9300; // 端口

    private TransportClient client=null;

    private static final String CLUSTERNAME = "my-application";

    private static Settings.Builder settings = Settings.builder().put("cluster.name",CLUSTERNAME);

    /**
     * 获取连接
     * @return
     */
    @SuppressWarnings({ "unchecked", "resource" })
    @Before
    public void getCient()throws Exception{
        client = new PreBuiltTransportClient(settings.build())
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ElasticSearchController.host), ElasticSearchController.port));
    }

    /**
     * 关闭连接
     */
    @After
    public void close(){
        if(client!=null){
            client.close();
        }
    }

    /**
     * 添加索引
     */
    @Test
    public void add()throws Exception{
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("user", "lineng");
        jsonObject.addProperty("postDate", "1989-11-11");
        jsonObject.addProperty("message", "trying out Elasticsearch");

        IndexResponse response =client.prepareIndex("qq", "tweet","1")
                .setSource(jsonObject.toString(),XContentType.JSON)
                .get();
        System.out.println("索引名称："+response.getIndex());
        System.out.println("类型："+response.getType());
        System.out.println("文档ID："+response.getId()); // 第一次使用是1
        System.out.println("当前实例状态："+response.status());
    }

    /**
     * 得到索引
     */
    @Test
    public void get()throws Exception{
        GetResponse getResponse = client.prepareGet("qq", "tweet","1").get();
        System.out.println(getResponse.getSourceAsString());
    }

    /**更新**/
    @Test
    public void testUpdate(){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("user", "蔡力能");
        jsonObject.addProperty("postDate", "1993-01-19");
        jsonObject.addProperty("message", "力能学习Elasticsearch");

        UpdateResponse response = client.prepareUpdate("qq", "tweet", "1").setDoc(jsonObject.toString(),XContentType.JSON).get();
        System.out.println("索引名称："+response.getIndex());
        System.out.println("类型："+response.getType());
        System.out.println("文档ID："+response.getId()); // 第一次使用是1
        System.out.println("当前实例状态："+response.status());
    }

    /**删除*/
    @Test
    public void testDelete(){
        DeleteResponse response=client.prepareDelete("qq", "tweet", "1").get();
        System.out.println("索引名称："+response.getIndex());
        System.out.println("类型："+response.getType());
        System.out.println("文档ID："+response.getId()); // 第一次使用是1
        System.out.println("当前实例状态："+response.status());
    }
}
