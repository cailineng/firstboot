package com.lineng.vo;

import com.lineng.model.SystemRole;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cailineng on 2017/12/31.
 */
public class SystemUserVo implements Serializable{
    private Long id;
    private String userName;
    private String psw;


    private List<SystemRole> systemRoleList;

    public List<SystemRole> getSystemRoleList() {
        return systemRoleList;
    }

    public void setSystemRoleList(List<SystemRole> systemRoleList) {
        this.systemRoleList = systemRoleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
