package com.lineng.repository;

import com.lineng.model.Cat;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cailineng on 2017/12/3.
 */

public interface CatRepository extends CrudRepository<Cat, Integer> {

}
