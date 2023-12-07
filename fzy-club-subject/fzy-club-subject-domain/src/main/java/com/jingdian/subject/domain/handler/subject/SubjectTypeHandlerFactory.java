package com.jingdian.subject.domain.handler.subject;

import com.jingdian.subject.common.enums.SubjectInfoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 冯正阳
 * 题目类型工厂
 */
@Component
@Slf4j
public class SubjectTypeHandlerFactory implements InitializingBean {

    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlerList;

    private Map<SubjectInfoEnum,SubjectTypeHandler> handlerMap = new HashMap<>();

    public SubjectTypeHandler getHandler(int subjectType){
        SubjectInfoEnum subjectInfoEnum = SubjectInfoEnum.getByCode(subjectType);
        log.info("SubjectTypeHandlerFactory中题目的类型为:" + subjectType);
        return handlerMap.get(subjectInfoEnum);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        for (SubjectTypeHandler subjectTypeHandler: subjectTypeHandlerList){
            handlerMap.put(subjectTypeHandler.getHandlerType(),subjectTypeHandler);
        }
    }
}
