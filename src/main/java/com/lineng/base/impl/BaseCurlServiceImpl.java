package com.lineng.base.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lineng.base.BaseMapper;
import com.lineng.base.BaseService;
import com.lineng.page.PageContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * 基本的增删改查操作
 * @author LH
 *
 * @param <D>
 * @param <T>
 */
public class BaseCurlServiceImpl<D extends BaseMapper<T>, T> implements BaseService<T> {

	@Autowired
	protected D dao;
	
	@Override
	public List<T> select(T t) {
		return dao.select(t);
	}

	@Override
	public List<T> selectAll() {
		return dao.selectAll();
	}

	@Override
	public int insert(T t) {
		return dao.insert(t);
	}

	@Override
	public int insertSelective(T t) {
		return dao.insertSelective(t);
	}

	@Override
	public T selectByPrimaryKey(Object id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(T t) {
		return dao.updateByPrimaryKey(t);
	}

	@Override
	public int deleteByPrimaryKey(Serializable id) {
		return dao.deleteByPrimaryKey(id);
	}


	@SuppressWarnings("unchecked")
	public D getBaseMapper() {
		return this.dao;
	}

	@Override
	public Page<T> pageAll() {
		PageHelper.startPage(PageContext.getPageNumber(), PageContext.getPageSize());
		return (Page<T>) dao.selectAll();
	}

	@Override
	public Page<T> pageSelect(T t) {
		PageHelper.startPage(PageContext.getPageNumber(), PageContext.getPageSize());
		PageHelper.orderBy(PageContext.getOrderBy());
		return (Page<T>) dao.select(t);
	}

	@Override
	public int updateByPrimaryKeySelective(T t) {
		return dao.updateByPrimaryKeySelective(t);
	}
}
