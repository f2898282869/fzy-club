package com.jingdian.subject.domain.handler.subject;

import com.jingdian.subject.common.enums.DeletedFlagEnum;
import com.jingdian.subject.common.enums.SubjectInfoEnum;
import com.jingdian.subject.domain.convert.SubjectBriefConverter;
import com.jingdian.subject.domain.entity.SubjectInfoBO;
import com.jingdian.subject.domain.entity.SubjectOptionBO;
import com.jingdian.subject.infra.basic.entity.SubjectBrief;
import com.jingdian.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 冯正阳
 * 判断
 */
@Component
public class BRIEFTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoEnum getHandlerType() {
        return SubjectInfoEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
//        判断题目的插入
        SubjectBrief subjectBrief = SubjectBriefConverter.INSTANCE.convertBoToEntity(subjectInfoBO);
        subjectBrief.setSubjectId(Math.toIntExact(subjectInfoBO.getId()));
        subjectBrief.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        SubjectBrief result = subjectBriefService.queryByCondition((long) subjectId);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;
    }
}
