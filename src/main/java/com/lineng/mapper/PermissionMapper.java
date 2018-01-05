package com.lineng.mapper;

import com.lineng.model.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by cailineng on 2018/1/3.
 */
@Mapper
public interface PermissionMapper {
    public List<Permission> findAll();
    public List<Permission> findByAdminUserId(int userId);
}
