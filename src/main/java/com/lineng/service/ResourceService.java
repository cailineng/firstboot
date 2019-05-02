package com.lineng.service;

import com.lineng.esmodel.Resource;

import java.util.List;

public interface ResourceService{
  //  PageInfo<Resource> resources(int page, int count);

    Long resourcesCount();
    Resource findResourceById(Long id);


    void saveResourceList(List<Resource> resourceList);

}