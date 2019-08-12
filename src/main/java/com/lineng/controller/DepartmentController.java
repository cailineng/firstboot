package com.lineng.controller;

import com.github.pagehelper.Page;
import com.lineng.annotation.Pagination;
import com.lineng.bo.InfoResult;
import com.lineng.bo.PageResult;
import com.lineng.bo.Result;
import com.lineng.model.Department;
import com.lineng.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 部门管理
 */
@Controller
@RequestMapping("/manage/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    /**
     *
     * @Title: getPage
     * @Description: 获取列表
     * @param
     * @return Result    返回类型
     * @author cailineng
     * @throws
     */
    @GetMapping("getPage")
    @Pagination
    @ResponseBody
    public Result getPage() {
        Page<Department> page = departmentService.getPage();
        PageResult<Department> result = new PageResult<>();
        result.setInfo(page);
        return result;
    }

    /**
     *
     * @Title: saveOrUpdate
     * @Description: 新增或修改
     * @param  department
     * @return Result    返回类型
     * @author cailineng
     * @throws
     */
    @GetMapping({
            "save", "update"
    })
    @ResponseBody
    public Result saveOrUpdate(Department department) {
        Result rs = new Result();
        try {
            if (department.getId() == null) {
                Department dep = new Department();
                dep.setCreated(new Date());
                dep.setCreator(1);
                Byte b = 0;
                dep.setDeleted(1);
                departmentService.insert(dep);
            } else {
              /*  DynamicInformation old = dynamicInformationService.selectByPrimaryKey(dynamicInformation.getId());
                old.setNameAndHeadType(dynamicInformation.getNameAndHeadType());
                old.setName(dynamicInformation.getName());
                old.setHeadUrl(dynamicInformation.getHeadUrl());
                old.setDynamicContent(dynamicInformation.getDynamicContent());
                old.setDynamicPics(dynamicInformation.getDynamicPics());
                old.setPublicTimeType(dynamicInformation.getPublicTimeType());
                if(dynamicInformation.getPublicTimeType() == DynamicInformationConst.publicTimeType.AT_ONCE_PUBLIC){
                    old.setPublicTime(null);
                }else {
                    old.setPublicTime(dynamicInformation.getPublicTime());
                }
                old.setShowStatus(dynamicInformation.getShowStatus());
                old.setIsTop(dynamicInformation.getIsTop());
                old.setType(dynamicInformation.getType());
                old.setObjectId(dynamicInformation.getObjectId());
                dynamicInformationService.saveOrUpdate(old);*/
            }

        } catch (Exception e) {
            rs.setExceptionStatus();
            rs.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return rs;
    }

    /**
     * 读取编辑数据
     * @param id
     * @return
     */
    @GetMapping("getById")
    @ResponseBody
    public Result getById(Integer id) {
        InfoResult<Department> rs = new InfoResult();
        Department dynamicInformation = departmentService.selectByPrimaryKey(id);
        rs.setInfo(dynamicInformation);
        return rs;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("deleteById")
    @ResponseBody
    public Result deleteById(Integer id) {
        Result rs = new Result();
        try {
            //departmentService.deleteById(id);
        } catch (Exception e) {
            rs.setExceptionStatus();
            rs.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return rs;
    }

}
