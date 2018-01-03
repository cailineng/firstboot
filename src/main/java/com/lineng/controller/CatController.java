package com.lineng.controller;

import com.lineng.model.Cat;
import com.lineng.model.Demo;
import com.lineng.model.SystemUser;
import com.lineng.service.CatService;
import com.lineng.service.SystemUserService;
import com.lineng.vo.SystemUserVo;
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
public class CatController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    CatService catService;
    @Resource
    private SystemUserService systemUserService;

    @ApiOperation(value="JSON的demo", notes="我要返回")
    @RequestMapping(value="/getDemo",method = RequestMethod.GET)
    public Demo getDemo(){
        Demo d = new Demo();
        d.setName("cailineng63");
        d.setCreateTime(new Date());
        return d;
    }

    @ApiOperation(value="根据猫的名字查询猫列表", notes="得到catNameList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "catName", value = "猫的名字", required = true,paramType = "query",  dataType = "String"),
    })
    @RequestMapping(value="/getNameList",method = RequestMethod.GET)
    public List<Cat> getNameList(@RequestParam(value="catName") String catName){
        return catService.findByCatName2(catName);
    }


    @ApiOperation(value="REDIS以及日志的测试", notes="我要返回")
    @RequestMapping(value="/setKey",method = RequestMethod.GET)
    public Map setKey(String key){
        logger.info("setKey go go go");
        redisTemplate.opsForValue().set("cai","cailineng");
        List<Cat> list = catService.findByCatName2("18");
        redisTemplate.opsForValue().set("catList",list);
      return new HashMap();
    }

    @RequestMapping(value="/getSystemUserByUserInfo",method = RequestMethod.GET)
    public Map getSystemUserByUserInfo(String userName, String psw){
        SystemUserVo systemUserVo = systemUserService.getSystemUserByUserInfo(userName,psw);
        Map map = new HashMap();
        map.put("data",systemUserVo);
        return map;
    }
}
