package com.lineng.mapper;

import com.github.pagehelper.Page;
import com.lineng.base.BaseMapper;
import com.lineng.model.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department>, tk.mybatis.mapper.common.Mapper<Department>, tk.mybatis.mapper.common.MySqlMapper<Department> {
    Page<Department> getPage();
}