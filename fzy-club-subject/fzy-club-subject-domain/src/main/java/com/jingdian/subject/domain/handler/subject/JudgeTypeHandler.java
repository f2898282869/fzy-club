package com.jingdian.subject.domain.handler.subject;

import com.jingdian.subject.common.enums.DeletedFlagEnum;
import com.jingdian.subject.common.enums.SubjectInfoEnum;
import com.jingdian.subject.domain.convert.SubjectJudgeConverter;
import com.jingdian.subject.domain.entity.SubjectAnswerBO;
import com.jingdian.subject.domain.entity.SubjectInfoBO;
import com.jingdian.subject.domain.entity.SubjectOptionBO;
import com.jingdian.subject.infra.basic.entity.SubjectJudge;
import com.jingdian.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 冯正阳
 * 判断
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectJudgeService subjectJudgeService;
    @Override
    public SubjectInfoEnum getHandlerType() {
        return SubjectInfoEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudge.setIsDeleted(DeletedFlagEnum.UN_DELETED.getCode());
        subjectJudgeService.batchInsert(subjectJudge);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId((long) subjectId);
        List<SubjectJudge> result = subjectJudgeService.queryByCondition(subjectJudge);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectJudgeConverter.INSTANCE.convertBoToEntity(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);

        return subjectOptionBO;
    }
}
