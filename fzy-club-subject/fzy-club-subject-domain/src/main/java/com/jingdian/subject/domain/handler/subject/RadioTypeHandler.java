package com.jingdian.subject.domain.handler.subject;

import com.jingdian.subject.common.enums.DeletedFlagEnum;
import com.jingdian.subject.common.enums.SubjectInfoEnum;
import com.jingdian.subject.domain.convert.SubjectRadioConverter;
import com.jingdian.subject.domain.entity.SubjectAnswerBO;
import com.jingdian.subject.domain.entity.SubjectInfoBO;
import com.jingdian.subject.domain.entity.SubjectOptionBO;
import com.jingdian.subject.infra.basic.entity.SubjectRadio;
import com.jingdian.subject.infra.basic.service.SubjectRadioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 冯正阳
 * 单选题目的策略类
 */
@Component
@Slf4j
public class RadioTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoEnum getHandlerType() {
        return SubjectInfoEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
//        单选题目的插入
        List<SubjectRadio> subjectRadioList = new LinkedList<>();
        log.info("单选题目的插入");
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectRadio subjectRadio = SubjectRadioConverter.INSTANCE.convertBoToEntity(option);
            subjectRadio.setSubjectId(Long.valueOf(subjectInfoBO.getId()));
            subjectRadio.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
            subjectRadio.setOptionType(option.getOptionType());
            subjectRadio.setIsCorrect(option.getIsCorrect());
            subjectRadioList.add(subjectRadio);
        });
        subjectRadioService.batchInsert(subjectRadioList);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId((long) subjectId);
        List<SubjectRadio> result = this.subjectRadioService.queryByCondition(subjectRadio);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectRadioConverter.INSTANCE.convertToEntity(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }

}
