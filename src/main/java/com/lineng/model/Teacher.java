package com.lineng.model;

import java.io.Serializable;

public class Teacher implements Serializable{
    private String id;
    private Integer age;
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", userName='" + userName + '\'' +
                '}';
    }
}
