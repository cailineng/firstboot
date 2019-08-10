package com.lineng.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by cailineng on 2017/12/2.
 */
@Data
@Table(name="cat")
public class Cat implements Serializable{
    @Id
    private Integer id;

    private String catName;

    private String catAge;
}
