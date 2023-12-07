package com.jingdian.subject.application.convert;

import com.jingdian.subject.application.dto.SubjectInfoDTO;
import com.jingdian.subject.application.dto.SubjectLabelDTO;
import com.jingdian.subject.domain.entity.SubjectInfoBO;
import com.jingdian.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * @author 冯正阳
 */
@Mapper
public interface SubjectInfoDTOConverter {
    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);


    SubjectInfoBO convertDTOTOBO(SubjectInfoDTO subjectInfoDTO);


    SubjectInfoDTO convertBOTODTO(SubjectInfoBO boSubjectInfo);
}
