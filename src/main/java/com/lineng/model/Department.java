package com.lineng.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_department")
@Data
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 部门名
     */
    @Column(name = "department_name")
    private String departmentName;

    /**
     * t_project表id(项目组表)
     */
    @Column(name = "project_id")
    private Integer projectId;

    private Integer creator;

    private Date created;

    /**
     * 是否被删除 1.被删除 0.未被删除
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}