package com.lineng.controller;

import com.lineng.model.Cat;
import com.lineng.model.Demo;
import com.lineng.service.CatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by cailineng on 2017/12/2.
 */
@RestController
public class FirstController {
    @Resource
    CatService catService;
    @RequestMapping("/hello")
    public String hello(){
      return "haha";
    }

    @RequestMapping("/getDemo")
    public Demo getDemo(){
        Demo d = new Demo();
        d.setName("cailineng63");
        d.setCreateTime(new Date());
        return d;
    }

    @RequestMapping("/getDemo2")
    public Demo getDemo2(){
        Demo d = new Demo();
        d.setName("cailineng613");
        d.setCreateTime(new Date());
        return d;
    }

    @RequestMapping("/saveCat")
    public Cat saveCat(String name){
        Cat cat  = new Cat();
        cat.setCatAge("18");
        cat.setCatName(name);
        catService.save(cat);
        return cat;
    }

    @RequestMapping("/getNameList")
    public List<Cat> getNameList(){
        return catService.findByCatName2("18");
    }

    @RequestMapping("/getCat")
    public Cat getCat(){
        return catService.getCat("chenshuwen");
    }
}
