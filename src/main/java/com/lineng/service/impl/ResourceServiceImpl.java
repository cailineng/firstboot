package com.lineng.service.impl;

import com.lineng.esdao.ResourceRepository;
import com.lineng.esmodel.Resource;
import com.lineng.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    ResourceRepository resourceRepository;

  /*  @Override
    public  PageInfo<Resource> resources(int page, int count) {
        PageHelper.startPage(page,count);
        List<Resource> resources = resourceMapper.selectByExample(new ResourceExample());
        PageInfo<Resource> pageInfo =  PageInfo.of(resources);
        return pageInfo;
    }*/

    public Long resourcesCount() {
        //return resourceMapper.countByExample(new ResourceExample());
        return null;
    }

    public Resource findResourceById(Long id) {
        return null;
    }

    public void saveResourceList(List<Resource> resourceList) {
        resourceRepository.saveAll(resourceList);
    }
}
