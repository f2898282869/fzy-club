package com.jingdian.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jingdian.subject.common.enums.CategoryTypeEnum;
import com.jingdian.subject.common.enums.DeletedFlagEnum;
import com.jingdian.subject.domain.convert.SubjectCategoryConverter;
import com.jingdian.subject.domain.entity.SubjectCategoryBO;
import com.jingdian.subject.domain.service.SubjectCategoryDomainService;
import com.jingdian.subject.infra.basic.entity.SubjectCategory;
import com.jingdian.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

/**
 * @author 冯正阳
 */
@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    /**
     * 新增分类
     * @param subjectCategorybo
     */
    @Override
    public void add(SubjectCategoryBO subjectCategorybo) {
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectCategorybo));
        }
//        相当于把BO对象转变为subjectCategory对象
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategorybo);

        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategories = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertToCategoryBO(subjectCategories);
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.queryPrimaryCategory.boList:{}", JSON.toJSONString(subjectCategoryBOList));
        }
        return subjectCategoryBOList;
    }

    /**
     * 更新分类
     * @param subjectCategoryBO
     * @return
     */
    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    /**
     * 删除分类
     * @param subjectCategoryBO
     * @return
     */
    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(DeletedFlagEnum.DELETED.getCode());
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }
}
