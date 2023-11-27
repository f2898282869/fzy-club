package com.jingdian.subject.domain.convert;

import com.jingdian.subject.domain.entity.SubjectCategoryBO;
import com.jingdian.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 冯正阳
 */
@Mapper
public interface SubjectCategoryConverter {

    SubjectCategoryConverter INSTANCE = Mappers.getMapper(SubjectCategoryConverter.class);
    SubjectCategory convertBoToCategory(SubjectCategoryBO subjectCategoryBO);
    List<SubjectCategoryBO> convertToCategoryBO(List<SubjectCategory> subjectCategoryList);
}
