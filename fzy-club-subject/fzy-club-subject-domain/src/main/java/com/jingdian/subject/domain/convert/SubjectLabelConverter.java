package com.jingdian.subject.domain.convert;

import com.jingdian.subject.domain.entity.SubjectCategoryBO;
import com.jingdian.subject.domain.entity.SubjectLabelBO;
import com.jingdian.subject.infra.basic.entity.SubjectCategory;
import com.jingdian.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 冯正阳
 */
@Mapper
public interface SubjectLabelConverter {

    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);
    SubjectLabel convertBoToLabel(SubjectLabelBO subjectLabelBO);

}
