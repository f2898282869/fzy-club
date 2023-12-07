package com.jingdian.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jingdian.subject.common.enums.DeletedFlagEnum;
import com.jingdian.subject.domain.convert.SubjectLabelConverter;
import com.jingdian.subject.domain.entity.SubjectLabelBO;
import com.jingdian.subject.domain.service.SubjectLabelDomainService;
import com.jingdian.subject.infra.basic.entity.SubjectLabel;
import com.jingdian.subject.infra.basic.entity.SubjectMapping;
import com.jingdian.subject.infra.basic.service.SubjectLabelService;
import com.jingdian.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 冯正阳
 */
@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectMappingService subjectMappingService;
    /**
     * 新增标签
     * @param subjectLabelBO
     */
    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.add.dto{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
        int insert = subjectLabelService.insert(subjectLabel);
        return insert > 0;
    }

    /**
     *
     * @param subjectLabelBO
     * @return
     */
    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.update.dto{}",subjectLabelBO);
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        int update = subjectLabelService.update(subjectLabel);
        return update > 0;
    }

    /**
     * 删除标签
     * @param subjectLabelBO
     * @return
     */
    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.delete.dto{}",subjectLabelBO);
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(DeletedFlagEnum.DELETED.getCode());
        int update = subjectLabelService.update(subjectLabel);
        return update > 0;
    }

    /**
     * 查询分类下标签
     * @param subjectLabelBO
     * @return
     */
    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> list = subjectMappingService.queryLabelId(subjectMapping);
        if(CollectionUtils.isEmpty(list)){
            log.info("List<SubjectMapping> 为空");
            return Collections.emptyList();
        }
        List<Long> labelIdList = list.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList1 = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> boList = new LinkedList<>();
        labelList1.forEach(label->{
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setId(label.getId());
            bo.setLabelName(label.getLabelName());
            bo.setCategoryId(categoryId);
            bo.setSortNum(label.getSortNum());
            boList.add(bo);
        });
        return boList;
    }
}
