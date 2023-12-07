package com.jingdian.subject.domain.convert;

import com.jingdian.subject.domain.entity.SubjectAnswerBO;
import com.jingdian.subject.infra.basic.entity.SubjectBrief;
import com.jingdian.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 冯正阳
 */
@Mapper
public interface SubjectJudgeConverter {

    SubjectJudgeConverter INSTANCE = Mappers.getMapper(SubjectJudgeConverter.class);
    List<SubjectAnswerBO> convertBoToEntity(List<SubjectJudge> subjectJudges);

}
