package com.lineng.mapper;


import com.lineng.model.Cat;
import com.lineng.util.BaseMapper;

import java.util.List;

/**
 * 菜单管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:09
 */
public interface CatMapper extends BaseMapper<Cat> {
	public List<Cat> getCats(String catName);

}
