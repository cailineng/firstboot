package com.lineng.service.impl;
import com.github.pagehelper.Page;
import com.lineng.base.impl.BaseCurlServiceImpl;
import com.lineng.mapper.DepartmentMapper;
import com.lineng.model.Department;
import com.lineng.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends BaseCurlServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Override
    public Page<Department> getPage() {
        return dao.getPage();
    }
}
