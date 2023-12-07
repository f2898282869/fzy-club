package com.jingdian.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目答案
 *
 * @author makejava
 * @since 2023-11-28 21:23:09
 */
@Data
public class SubjectAnswerDTO implements Serializable {
    private static final long serialVersionUID = 748126860936918467L;
    /**
     * 答案选项标识
     */
    private Integer optionType;
    /**
     * 答案名称
     */
    private String optionContent;
    /**
     * 标识位
     */
    private Integer isCorrect;

}

