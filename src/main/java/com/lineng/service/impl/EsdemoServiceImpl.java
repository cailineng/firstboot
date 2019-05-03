package com.lineng.service.impl;

import com.lineng.esdao.GiftRepository;
import com.lineng.esmodel.Gift;
import com.lineng.service.EsdemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class EsdemoServiceImpl implements EsdemoService {
    @Autowired
    GiftRepository giftRepository;

    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void createIndex() {
        elasticsearchTemplate.createIndex(Gift.class);
    }

    @Override
    public void saveGiftList(List<Gift> giftList) {
        giftRepository.saveAll(giftList);
    }

    @Override
    public void deleteById(Long id) {
        giftRepository.deleteById(id);
    }

    @Override
    public Gift findById(Long id) {
        return giftRepository.findById(id).get();
    }

    @Override
    public List<Gift> findByGiftName(String giftName) {
        return giftRepository.findByGiftName(giftName);
    }

    @Override
    public List<Gift> findByGiftNamePage(String giftName, Pageable pageable) {
        return giftRepository.findByGiftName(giftName,pageable);
    }

}
