package com.lineng.service;

import com.lineng.mapper.CatMapper;
import com.lineng.model.Cat;
import com.lineng.model.SystemUser;
import com.lineng.vo.SystemUserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



public interface SystemUserService {
	public SystemUserVo getSystemUserByUserInfo(String userName, String psw);
}
