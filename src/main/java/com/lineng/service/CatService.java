package com.lineng.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.lineng.model.Cat;
import com.lineng.repository.Cat2Repository;
import com.lineng.repository.CatRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CatService {
	@Resource
	private CatRepository catRepository;

	@Resource
	private Cat2Repository cat2Repository;
/*	@Resource
	private CatDao catDao;*/

	/**
	 * save,update ,delete 方法需要绑定事务.
	 * 
	 * 使用@Transactional进行事务的绑定.
	 * 
	 * @param cat
	 */
	
	//保存数据.
	@Transactional
	public void save(Cat cat){
		catRepository.save(cat);
	}
	
	//删除数据》
	@Transactional
	public void delete(int id){
		catRepository.delete(id);
	}
	
	//查询数据.
	public Iterable<Cat> getAll(){
		return catRepository.findAll();
	}

	public List<Cat> findByCatName2(String catName){
		return cat2Repository.findByCatAge(catName);
	}
}
