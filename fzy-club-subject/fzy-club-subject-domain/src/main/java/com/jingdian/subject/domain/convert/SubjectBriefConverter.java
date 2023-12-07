package com.jingdian.subject.domain.convert;

import com.jingdian.subject.domain.entity.SubjectAnswerBO;
import com.jingdian.subject.domain.entity.SubjectBriefBO;
import com.jingdian.subject.domain.entity.SubjectInfoBO;
import com.jingdian.subject.infra.basic.entity.SubjectBrief;
import com.jingdian.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 冯正阳
 */
@Mapper
public interface SubjectBriefConverter {

    SubjectBriefConverter INSTANCE = Mappers.getMapper(SubjectBriefConverter.class);
    SubjectBrief convertBoToEntity(SubjectInfoBO subjectInfoBO);

}
