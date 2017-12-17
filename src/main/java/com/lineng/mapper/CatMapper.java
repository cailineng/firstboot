package com.lineng.mapper;


import java.util.List;
import java.util.Map;

import com.lineng.model.Cat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 菜单管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:09
 */
@Mapper
public interface CatMapper {
	@Select("select * from Cat where catName = #{catName}")
	public List<Cat> getCats(String catName);
}
