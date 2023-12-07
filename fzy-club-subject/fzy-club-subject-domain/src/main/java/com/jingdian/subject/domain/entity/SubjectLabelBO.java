package com.jingdian.subject.domain.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签表(SubjectLabel)实体类
 *
 * @author makejava
 * @since 2023-11-27 15:32:05
 */
@Data
public class SubjectLabelBO implements Serializable {
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

    private Long CategoryId;
}

