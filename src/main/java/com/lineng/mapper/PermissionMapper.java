package com.lineng.mapper;

import com.lineng.model.Permission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by cailineng on 2018/1/3.
 */
public interface PermissionMapper extends Mapper<Permission> {
    public List<Permission> findAll();
    public List<Permission> findByAdminUserId(int userId);
}
