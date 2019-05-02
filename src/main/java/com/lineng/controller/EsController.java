package com.lineng.controller;

import com.lineng.esmodel.Resource;
import com.lineng.service.ResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/es")
public class EsController {
    @javax.annotation.Resource
    private ResourceService resourceService;
    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        Resource resource = new Resource();
        resource.setId(1L);
        resource.setCreateTime(new Date());
        resource.setName("lineng");
        resource.setUrl("lineng.com");

        Resource resource2 = new Resource();
        resource2.setId(2L);
        resource2.setCreateTime(new Date());
        resource2.setName("lineng");
        resource2.setUrl("lineng.com");
        List<Resource> resourceList = new ArrayList();
        resourceList.add(resource);
        resourceList.add(resource2);
        resourceService.saveResourceList(resourceList);
        return "testThe";
    }



}
