package com.jingdian.subject.domain.convert;

import com.jingdian.subject.domain.entity.SubjectInfoBO;
import com.jingdian.subject.domain.entity.SubjectOptionBO;
import com.jingdian.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 冯正阳
 */
@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);
    SubjectInfo convertBoToINfo(SubjectInfoBO subjectInfoBO);
    SubjectInfoBO convertOptionToBO(SubjectOptionBO subjectOptionBO);
    SubjectInfoBO convertOptionAndInfoToBO(SubjectOptionBO subjectOptionBO,SubjectInfo subjectInfo);

    List<SubjectInfoBO> convertListInfoBO(List<SubjectInfo> subjectInfoList);

}
