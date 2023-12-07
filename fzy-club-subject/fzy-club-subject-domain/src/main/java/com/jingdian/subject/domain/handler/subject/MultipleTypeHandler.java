package com.jingdian.subject.domain.handler.subject;

import com.jingdian.subject.common.enums.DeletedFlagEnum;
import com.jingdian.subject.common.enums.SubjectInfoEnum;
import com.jingdian.subject.domain.convert.SubjectMultipleConverter;
import com.jingdian.subject.domain.convert.SubjectRadioConverter;
import com.jingdian.subject.domain.entity.SubjectAnswerBO;
import com.jingdian.subject.domain.entity.SubjectInfoBO;
import com.jingdian.subject.domain.entity.SubjectOptionBO;
import com.jingdian.subject.infra.basic.entity.SubjectMultiple;
import com.jingdian.subject.infra.basic.entity.SubjectRadio;
import com.jingdian.subject.infra.basic.service.SubjectMultipleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 冯正阳
 * 多选
 */
@Component
@Slf4j
public class MultipleTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoEnum getHandlerType() {
        return SubjectInfoEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //      多选题目的插入
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        log.info("多选题目的插入");
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = SubjectMultipleConverter.INSTANCE.convertBoToEntity(option);
            subjectMultiple.setSubjectId(Long.valueOf(subjectInfoBO.getId()));
            subjectMultiple.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
            subjectMultiple.setOptionType(Long.valueOf(option.getOptionType()));
            subjectMultiple.setOptionContent(option.getOptionContent());
            subjectMultiple.setIsCorrect(option.getIsCorrect());
            subjectMultipleList.add(subjectMultiple);
        });
        subjectMultipleService.batchInsert(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId((long) subjectId);
        List<SubjectMultiple> result = subjectMultipleService.queryByCondition(subjectMultiple);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectMultipleConverter.INSTANCE.convertToAnswerBO(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
