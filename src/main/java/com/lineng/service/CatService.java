package com.lineng.service;

import com.lineng.model.Cat;

import java.util.List;


public interface CatService {
	public List<Cat> findByCatName2(String catName) throws Exception;
}
