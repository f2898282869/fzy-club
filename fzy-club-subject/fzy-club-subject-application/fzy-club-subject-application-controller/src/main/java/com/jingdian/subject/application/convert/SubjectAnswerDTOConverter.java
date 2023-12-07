package com.jingdian.subject.application.convert;

import com.jingdian.subject.application.dto.SubjectAnswerDTO;
import com.jingdian.subject.application.dto.SubjectInfoDTO;
import com.jingdian.subject.domain.entity.SubjectAnswerBO;
import com.jingdian.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * @author 冯正阳
 */
@Mapper
public interface SubjectAnswerDTOConverter {
    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);


    SubjectAnswerDTO convertDTOTOBO(SubjectAnswerDTO subjectAnswerDTO);

    List<SubjectAnswerBO> convertListDTOToBo(List<SubjectAnswerDTO> subjectAnswerDTOList);
}
