package com.jingdian.subject.domain.handler.subject;

import com.jingdian.subject.common.enums.SubjectInfoEnum;
import com.jingdian.subject.domain.entity.SubjectInfoBO;
import com.jingdian.subject.domain.entity.SubjectOptionBO;
import org.springframework.stereotype.Component;

/**
 * @author 冯正阳
 */
@Component
public interface SubjectTypeHandler {

    SubjectInfoEnum getHandlerType();

    /**
     * 实际的题目的插入
     * @param subjectInfoBO
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目详情
     */
    SubjectOptionBO query(int subjectId);
}
