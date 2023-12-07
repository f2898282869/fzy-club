package com.jingdian.subject.domain.service;

import com.jingdian.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * @author 冯正阳
 */

public interface SubjectLabelDomainService {

    Boolean add(SubjectLabelBO subjectLabelBO);

    Boolean update(SubjectLabelBO subjectLabelBO);

    Boolean delete(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
