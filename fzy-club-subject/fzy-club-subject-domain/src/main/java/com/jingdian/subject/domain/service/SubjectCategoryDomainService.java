package com.jingdian.subject.domain.service;

import com.jingdian.subject.domain.entity.SubjectCategoryBO;
import com.jingdian.subject.infra.basic.entity.SubjectCategory;

import java.util.List;

/**
 * @author 冯正阳
 */
public interface SubjectCategoryDomainService {

    void add(SubjectCategoryBO subjectCategory);

    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    Boolean update(SubjectCategoryBO subjectCategoryBO);

    Boolean delete(SubjectCategoryBO subjectCategoryBO);
}
