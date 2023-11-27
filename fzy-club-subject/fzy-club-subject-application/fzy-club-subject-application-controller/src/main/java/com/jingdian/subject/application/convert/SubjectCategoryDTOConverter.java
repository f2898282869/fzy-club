package com.jingdian.subject.application.convert;

import com.jingdian.subject.application.dto.SubjectCategoryDTO;
import com.jingdian.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 冯正阳
 */
@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertBoToCategory(SubjectCategoryDTO subjectCategoryDTO);
    List<SubjectCategoryDTO> convertBoTPCategoryDTO(List<SubjectCategoryBO> subjectCategoryBO);

    SubjectCategoryBO convertDtoTOCategoryBO(SubjectCategoryDTO subjectCategoryDTO);
}
