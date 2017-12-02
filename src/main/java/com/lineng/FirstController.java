package com.lineng;

import com.lineng.com.lineng.model.Demo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by cailineng on 2017/12/2.
 */
@RestController
public class FirstController {
    @RequestMapping("/hello")
    public String hello(){
      return "haha";
    }

    @RequestMapping("/getDemo")
    public Demo getDemo(){
        Demo d = new Demo();
        d.setName("cailineng");
        d.setCreateTime(new Date());
        return d;
    }
}
