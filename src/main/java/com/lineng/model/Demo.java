package com.lineng.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by cailineng on 2017/12/2.
 */
public class Demo implements Serializable{
    private Integer id;
    private String name;

    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date createTime;//创建时间.


    /*
     * 我们不想返回remarks?
     * serialize:是否需要序列化属性.
     */
    @JSONField(serialize=false)
    private String remarks;//备注信息.

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
