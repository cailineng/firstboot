package com.lineng.base;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {

	List<T> select(T t);

	List<T> selectAll();

	int insert(T t);

	int insertSelective(T t);

	T selectByPrimaryKey(Object id);

	int updateByPrimaryKey(T t);

	int updateByPrimaryKeySelective(T t);

	int deleteByPrimaryKey(Serializable id);

	Page<T> pageAll();

	Page<T> pageSelect(T t);
	
}