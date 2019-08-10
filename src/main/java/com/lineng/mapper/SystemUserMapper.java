package com.lineng.mapper;


import com.lineng.model.SystemUser;
import com.lineng.vo.SystemUserVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * 菜单管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:09
 */
public interface SystemUserMapper extends Mapper<SystemUser> {
	public SystemUserVo getSystemUserByUserInfo(Map map);
}
