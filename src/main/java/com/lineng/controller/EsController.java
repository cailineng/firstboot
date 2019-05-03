package com.lineng.controller;

import com.lineng.esmodel.Gift;
import com.lineng.service.EsdemoService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/es")
public class EsController {
   
    @Resource
    private EsdemoService esdemoService;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/createIndex")
    @ResponseBody
    public String createIndex() {
        esdemoService.createIndex();
        return "createIndex";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        List<Gift> giftList = new ArrayList();
        for(long i=0;i<10L;i++){
            Gift gift = new Gift();
            gift.setId(i);
            gift.setCreateTime(new Date());
            gift.setChangeCount(1);
            gift.setGiftDetail("礼物详情"+i);
            gift.setGiftName("礼物名称"+i);
            gift.setPicUrl("陈奕迅图片Url"+i);
            gift.setType("实体"+i);
            gift.setUpdateTime(new Date());
            giftList.add(gift);
        }

        for(long i=10;i<200L;i++){
            Gift gift2 = new Gift();
            gift2.setId(i);
            gift2.setCreateTime(new Date());
            gift2.setChangeCount(0);
            gift2.setGiftDetail("第二批礼物"+i);
            gift2.setGiftName("第二批礼物哈哈哈"+i);
            gift2.setPicUrl("周杰伦图片Url2"+i);
            gift2.setType("虚拟"+i);
            gift2.setUpdateTime(new Date());
            giftList.add(gift2);
        }
        esdemoService.saveGiftList(giftList);
        return "save";
    }


    @RequestMapping("/saveWithoutId")
    @ResponseBody
    public String saveWithoutId() {
        Gift gift = new Gift();
        gift.setCreateTime(new Date());
        gift.setChangeCount(0);
        gift.setGiftDetail("礼物详情222");
        gift.setGiftName("礼物名称222");
        gift.setPicUrl("陈奕迅图片Url222");
        gift.setType("卡券");
        gift.setUpdateTime(new Date());


        List<Gift> giftList = new ArrayList();
        giftList.add(gift);

        esdemoService.saveGiftList(giftList);
        return "saveWithoutId";
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById() {
        Long id = 1L;
        esdemoService.deleteById(id);
        return "deleteById";
    }


    @RequestMapping("/findById")
    @ResponseBody
    public String findById() {
        Long id = 1L;
        Gift gift = esdemoService.findById(id);
        return gift.toString();
    }

    @RequestMapping("/findByGiftName")
    @ResponseBody
    public List<Gift> findByGiftName() {
        String giftName ="第二";
        List<Gift> gift = esdemoService.findByGiftName(giftName);
        return gift;
    }

    @RequestMapping("/findByGiftNamePageable")
    @ResponseBody
    public List<Gift> findByGiftNamePageable() {
        PageRequest pageRequest = PageRequest.of(0,15);
        String giftName ="第二";
        List<Gift> gift = esdemoService.findByGiftNamePage(giftName,pageRequest);
        return gift;
    }

    @RequestMapping("/tessQueryBuilder")
    @ResponseBody
    public List<Gift> tessQueryBuilder() {
        //https://blog.csdn.net/mj86534210/article/details/79910909    一些增删查用法
      /*  QueryBuilder queryBuilder = new
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                                      .withQuery().withPageable().build();*/

        return null;
    }



}
