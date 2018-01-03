package com.lineng.mapper;


import com.lineng.model.Cat;
import com.lineng.model.SystemUser;
import com.lineng.vo.SystemUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:09
 */
@Mapper
public interface SystemUserMapper {
	public SystemUserVo getSystemUserByUserInfo(Map map);
}
