package com.lineng.service;

import com.lineng.base.BaseService;
import com.github.pagehelper.Page;
import com.lineng.model.Department;

public interface DepartmentService extends BaseService<Department> {
    /**
     * 得到部门列表
     * @return
     */
    Page<Department> getPage();
}
