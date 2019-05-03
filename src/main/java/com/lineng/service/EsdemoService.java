package com.lineng.service;

import com.lineng.esmodel.Gift;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EsdemoService {
    public void createIndex();

    void saveGiftList(List<Gift> giftList);

    void deleteById(Long id);

    Gift findById(Long id);


    List<Gift> findByGiftName(String giftName);

    List<Gift> findByGiftNamePage(String giftName,  Pageable pageable);
}
