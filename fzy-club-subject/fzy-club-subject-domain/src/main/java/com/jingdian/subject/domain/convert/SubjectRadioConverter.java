package com.jingdian.subject.domain.convert;

import com.jingdian.subject.domain.entity.SubjectAnswerBO;
import com.jingdian.subject.domain.entity.SubjectInfoBO;
import com.jingdian.subject.domain.handler.subject.RadioTypeHandler;
import com.jingdian.subject.infra.basic.entity.SubjectInfo;
import com.jingdian.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 冯正阳
 */
@Mapper
public interface SubjectRadioConverter {

    SubjectRadioConverter INSTANCE = Mappers.getMapper(SubjectRadioConverter.class);
    SubjectRadio convertBoToEntity(SubjectAnswerBO subjectAnswerBO);
    List<SubjectAnswerBO> convertToEntity(List<SubjectRadio> subjectRadioList);

}
