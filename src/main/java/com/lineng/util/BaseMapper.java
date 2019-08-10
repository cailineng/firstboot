package com.lineng.util;


import com.lineng.model.Cat;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**

 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
