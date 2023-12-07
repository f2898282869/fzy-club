package com.jingdian.subject.domain.entity;

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
public class SubjectOptionBO  implements Serializable {
    private static final long serialVersionUID = 748126860936918467L;

    /**
     * 题目答案
     */
    private String subjectAnswer;
    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> OptionList;

}

