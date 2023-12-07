package com.jingdian.subject.application.convert;

import com.jingdian.subject.application.dto.SubjectLabelDTO;
import com.jingdian.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * @author 冯正阳
 */
@Mapper
public interface SubjectLabelDTOConverter {
    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDTOTOBO(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertBOTODTO(List<SubjectLabelBO> subjectLabelBO);

}
