package com.lineng.controller;

import com.lineng.model.Cat;
import com.lineng.model.Demo;
import com.lineng.service.CatService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by cailineng on 2017/12/2.
 */
@RestController
public class FirstController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    CatService catService;

    @ApiOperation(value="普通hello测试", notes="我要返回haha")
    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String hello(){
      return "haha";
    }

    @RequestMapping(value="/getDemo",method = RequestMethod.GET)
    public Demo getDemo(){
        Demo d = new Demo();
        d.setName("cailineng63");
        d.setCreateTime(new Date());
        return d;
    }

    @RequestMapping(value="/getDemo2",method = RequestMethod.GET)
    public Demo getDemo2(){
        Demo d = new Demo();
        d.setName("cailineng613");
        d.setCreateTime(new Date());
        return d;
    }

    @RequestMapping(value="/saveCat",method = RequestMethod.GET)
    public Cat saveCat(String name){
        Cat cat  = new Cat();
        cat.setCatAge("18");
        cat.setCatName(name);
        catService.save(cat);
        return cat;
    }

    @ApiOperation(value="根据猫的名字查询猫列表", notes="得到catNameList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "catName", value = "猫的名字", required = true,paramType = "query",  dataType = "String"),
    })
   // @RequestMapping(value="/getNameList/{catName}",method = RequestMethod.GET)
   // public List<Cat> getNameList(@PathVariable String catName){
    @RequestMapping(value="/getNameList",method = RequestMethod.GET)
    public List<Cat> getNameList(@RequestParam(value="catName") String catName){
        return catService.findByCatName2(catName);
    }

    @RequestMapping(value="/getCat",method = RequestMethod.GET)
    public Cat getCat(){
        return catService.getCat("chenshuwen");
    }

    @RequestMapping(value="/setKey",method = RequestMethod.GET)
    public Map setKey(String key){
        logger.info("setKey go go go");
        redisTemplate.opsForValue().set("cai","cailineng");
        List<Cat> list = catService.findByCatName2("18");
        redisTemplate.opsForValue().set("catList",list);
      return new HashMap();
    }
}
