package com.lineng.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by cailineng on 2017/12/2.
 */
public class CatVo implements Serializable{
    @NotNull(message = "id 不能为空")
    @Max(value = 100, message = "id大于100岁")
    private Integer id;
    @NotNull(message = "catName不能为空")
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

    @Override
    public String toString() {
        return "CatVo{" +
                "id=" + id +
                ", catName='" + catName + '\'' +
                ", catAge='" + catAge + '\'' +
                '}';
    }
}
