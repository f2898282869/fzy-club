package com.jingdian.subject.domain.entity;

import com.jingdian.subject.common.entity.PageInfo;
import com.jingdian.subject.infra.basic.entity.SubjectInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)实体类
 *
 * @author makejava
 * @since 2023-11-28 21:23:09
 */
@Data
public class SubjectInfoBO extends PageInfo implements Serializable {
    private static final long serialVersionUID = 748126860936918467L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 分类Id
     */
    private List<Integer> CategoryIds;
    /**
     * 标签id
     */
    private List<Integer> LabelIds;
    /**
     * 标签name
     */
    private List<String> labelName;
    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> OptionList;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 标签id
     */
    private Integer labelId;

}

