package com.lineng.model;

import java.io.Serializable;

/**
 * Created by cailineng on 2017/12/2.
 */
public class Cat implements Serializable{
    private Integer id;

    private String catName;

    private String catAge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatAge() {
        return catAge;
    }

    public void setCatAge(String catAge) {
        this.catAge = catAge;
    }
}
