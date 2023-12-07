package com.jingdian.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jingdian.subject.common.entity.PageResult;
import com.jingdian.subject.common.enums.DeletedFlagEnum;
import com.jingdian.subject.domain.convert.SubjectInfoConverter;
import com.jingdian.subject.domain.entity.SubjectInfoBO;
import com.jingdian.subject.domain.entity.SubjectOptionBO;
import com.jingdian.subject.domain.handler.subject.SubjectTypeHandler;
import com.jingdian.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.jingdian.subject.domain.service.SubjectInfoDomainService;
import com.jingdian.subject.infra.basic.entity.SubjectInfo;
import com.jingdian.subject.infra.basic.entity.SubjectLabel;
import com.jingdian.subject.infra.basic.entity.SubjectMapping;
import com.jingdian.subject.infra.basic.service.SubjectInfoService;
import com.jingdian.subject.infra.basic.service.SubjectLabelService;
import com.jingdian.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 冯正阳
 */
@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Resource
    private SubjectLabelService subjectLabelService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}",JSON.toJSONString(subjectInfoBO));
        }
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToINfo(subjectInfoBO);
        subjectInfo.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
        //        subjectInfo是题目信息表，不仅要插题目信息，还要插入对应的类型表里
        subjectInfoService.insert(subjectInfo);
//        根据传入的code值，判断类型，返回一个大类，根据大类来调用他对应的插入方法
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        log.info("SubjectInfoDomainServiceImpl.add.subjectInfoBO{}",subjectInfoBO);
        handler.add(subjectInfoBO);
        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> subjectMappings = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setLabelId(Long.valueOf(labelId));
                subjectMapping.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
                subjectMappings.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(subjectMappings);
    }

    /**
     * 分页
     * @param subjectInfoBO
     * @return
     */
    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToINfo(subjectInfoBO);
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId()
                , subjectInfoBO.getLabelId());
        if (count == 0) {
            return pageResult;
        }
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId()
                , subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConverter.INSTANCE.convertListInfoBO(subjectInfoList);
        subjectInfoBOS.forEach(info->{
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setSubjectId(info.getId());
            List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
            List<Long> labelIds = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
            List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIds);
            List<String> labelNames = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
            info.setLabelName(labelNames);
        });
        pageResult.setRecords(subjectInfoBOS);
        pageResult.setTotal(count);
        return pageResult;

    }

    @Override
    public SubjectInfoBO getSubjectInfo(SubjectInfoBO subjectInfoBO) {
//        通过id来获取题目信息
        SubjectInfo subjectInfo = this.subjectInfoService.queryById(subjectInfoBO.getId());
//        获取题目类型
        Integer subjectType = subjectInfo.getSubjectType();
//        根据题目类型来查找答案信息
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectType);
        SubjectOptionBO optionBO = handler.query(subjectInfo.getId().intValue());
//        把查找的答案信息封装在BO中
        SubjectInfoBO bo = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBO(optionBO,subjectInfo);
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
        List<Long> labelIdList = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNameList = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        bo.setLabelName(labelNameList);
        return bo;
    }
}
