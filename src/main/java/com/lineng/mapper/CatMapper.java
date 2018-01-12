package com.lineng.mapper;


import com.lineng.model.Cat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:09
 */
@Mapper
public interface CatMapper {
	public List<Cat> getCats(String catName);

	int insert(Cat record);
}
