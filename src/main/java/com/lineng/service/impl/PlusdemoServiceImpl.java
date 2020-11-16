package com.lineng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lineng.mapper.PlusdemoMapper;
import com.lineng.model.Plusdemo;
import com.lineng.service.PlusdemoService;
import org.springframework.stereotype.Service;

@Service
public class PlusdemoServiceImpl  extends ServiceImpl<PlusdemoMapper, Plusdemo> implements PlusdemoService {
}
