package com.lineng.service.impl;

import com.lineng.mapper.SystemUserMapper;
import com.lineng.service.SystemUserService;
import com.lineng.vo.SystemUserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cailineng on 2017/12/31.
 */
@Service("systemUserService")
public class SystemUserServiceImpl implements SystemUserService{
    @Resource
    private SystemUserMapper systemUserMapper;
    @Override
    public SystemUserVo getSystemUserByUserInfo(String userName, String psw) {
        Map map = new HashMap();
        map.put("userName",userName);
        map.put("psw",psw);
        return systemUserMapper.getSystemUserByUserInfo(map);
    }
}
