package com.lineng.controller;

import com.lineng.model.Cat;
import com.lineng.model.Demo;
import com.lineng.service.CatService;
import com.lineng.service.SystemUserService;
import com.lineng.service.impl.CatServiceImpl;
import com.lineng.vo.CatVo;
import com.lineng.vo.SystemUserVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cailineng on 2017/12/2.
 */
@RestController
@Validated
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
    public List<Cat> getNameList(@RequestParam(value="catName") String catName) throws Exception {
        return catService.findByCatName2(catName);
    }


    @ApiOperation(value="REDIS以及日志的测试", notes="我要返回")
    @RequestMapping(value="/setKey",method = RequestMethod.GET)
    public Map setKey(String key){
        logger.info("setKey go go go");
        System.out.println("测试乱码");
       // redisTemplate.opsForValue().set("cai","cailineng");
        try {
            List<Cat> list = catService.findByCatName2("18");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // redisTemplate.opsForValue().set("catList",list);
       return new HashMap();
    }

    @RequestMapping(value="/getSystemUserByUserInfo",method = RequestMethod.GET)
    public Map getSystemUserByUserInfo(String userName, String psw){
        System.out.println("进来了测试乱码哈哈哈");
        SystemUserVo systemUserVo = systemUserService.getSystemUserByUserInfo(userName,psw);
        Map map = new HashMap();
        map.put("data",systemUserVo);
        return map;
    }


    @RequestMapping(value="/validBean",method = RequestMethod.GET)
    public Map validBean(@Valid CatVo catDemo,BindingResult result){
        Map map = new HashMap();
        map.put("data","");
        return map;
    }

    /**如果只有少数对象，直接把参数写到Controller层，然后在Controller层进行验证就可以了。*/
    @RequestMapping(value="/validParams",method = RequestMethod.GET)
    public void validParams(@Range(min = 1, max = 9, message = "年级只能从1-9")
                      @RequestParam(name = "grade", required = true)
                      int grade,
                      @Min(value = 1, message = "班级最小只能1")
                      @Max(value = 99, message = "班级最大只能99")
                      @RequestParam(name = "classroom", required = true)
                      int classroom) {
        System.out.println(grade + "," + classroom);
    }


    /**如果只有少数对象，直接把参数写到Controller层，然后在Controller层进行验证就可以了。*/
    @RequestMapping(value="/saveCat")
    @ResponseBody
    public Cat saveCat(){
        Cat cat = new Cat();
        cat.setCatAge("102");
        cat.setCatName("danshencat");
        Cat catReturn = catService.saveCat(cat);
        return catReturn;
    }


    @RequestMapping(value="/selectById")
    @ResponseBody
    public Cat selectById(Integer id){
        Cat catReturn = catService.selectById(id);
        return catReturn;
    }

}
