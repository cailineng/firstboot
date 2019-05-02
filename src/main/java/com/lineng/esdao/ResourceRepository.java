package com.lineng.esdao;

import com.lineng.esmodel.Resource;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ResourceRepository extends ElasticsearchRepository<Resource,Long> {

}