package com.jingdian.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签表(SubjectLabel)实体类
 *
 * @author makejava
 * @since 2023-11-27 15:32:05
 */
@Data

public class SubjectLabelDTO implements Serializable {
    private static final long serialVersionUID = -11238350261591541L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;
    /**
     * 分类Id
     */
    private Long categoryId;

}

