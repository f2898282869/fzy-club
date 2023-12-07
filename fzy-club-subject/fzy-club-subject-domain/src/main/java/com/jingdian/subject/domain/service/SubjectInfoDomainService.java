package com.jingdian.subject.domain.service;

import com.jingdian.subject.common.entity.PageResult;
import com.jingdian.subject.domain.entity.SubjectInfoBO;
import com.jingdian.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * @author 冯正阳
 */

public interface SubjectInfoDomainService {

    void add(SubjectInfoBO subjectInfoBO);


    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO getSubjectInfo(SubjectInfoBO subjectInfoBO);
}
