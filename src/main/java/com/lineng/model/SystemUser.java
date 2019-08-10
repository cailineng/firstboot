package com.lineng.model;

import javax.persistence.Table;

/**
 * Created by cailineng on 2017/12/30.
 */
@Table(name="systemuser")
public class SystemUser{
    private Long id;
    private String userName;
    private String psw;

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
