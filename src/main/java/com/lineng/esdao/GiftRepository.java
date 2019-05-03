package com.lineng.esdao;

import com.lineng.esmodel.Gift;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface GiftRepository extends ElasticsearchRepository<Gift,Long> {

    List<Gift> findByGiftName(String giftName);

    List<Gift> findByGiftName(String giftName, Pageable pageable);
}