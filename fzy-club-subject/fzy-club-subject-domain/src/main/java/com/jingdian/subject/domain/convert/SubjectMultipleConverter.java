package com.jingdian.subject.domain.convert;

import com.jingdian.subject.domain.entity.SubjectAnswerBO;
import com.jingdian.subject.infra.basic.entity.SubjectJudge;
import com.jingdian.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 冯正阳
 */
@Mapper
public interface SubjectMultipleConverter {

    SubjectMultipleConverter INSTANCE = Mappers.getMapper(SubjectMultipleConverter.class);
    SubjectMultiple convertBoToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertToAnswerBO(List<SubjectMultiple> subjectMultipleList);
}
