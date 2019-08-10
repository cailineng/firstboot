package com.lineng.service.impl;

import com.lineng.mapper.CatMapper;
import com.lineng.model.Cat;
import com.lineng.service.CatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CatServiceImpl implements CatService {
	@Resource
	private CatMapper catMapper;
/*	@Resource
	private CatDao catDao;*/

	/**
	 * save,update ,delete 方法需要绑定事务.
	 * 
	 * 使用@Transactional进行事务的绑定.
	 *
	 * @param catName
	 * @return
	 * @throws Exception
	 */
	/*
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

	//spring jdbc
	public Cat getCat(String catName){
        return catDao.selectByCatName(catName);
    }
*/
	@Transactional
	public List<Cat> findByCatName2(String catName) throws Exception {
		Cat testCat = new Cat();
		testCat.setCatAge("105");
		testCat.setCatName("testCatName");
		catMapper.insert(testCat);
	/*	if(1==1) {
			throw new RuntimeException();
		}*/
		catMapper.insert(testCat);
	 	return catMapper.getCats(catName);
		/*return cat2Repository.findByCatAge(catName);*/
	}

	@Override
	public Cat saveCat(Cat cat) {
		catMapper.insertUseGeneratedKeys(cat);
		return cat;
	}

	@Override
	public Cat selectById(Integer id) {
		return catMapper.selectByPrimaryKey(id);
	}


}
