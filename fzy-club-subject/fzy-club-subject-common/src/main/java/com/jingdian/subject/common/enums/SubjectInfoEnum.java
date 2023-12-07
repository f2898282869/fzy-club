 package com.jingdian.subject.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 冯正阳
 * 题目类型枚举
 */
@Getter
@AllArgsConstructor
public enum SubjectInfoEnum {

    RADIO(1,"单选"),
    MULTIPLE(2,"多选"),
    JUDGE(3,"判断"),
    BRIEF(4,"简答");

    public int code;
    private String desc;

    public static SubjectInfoEnum getByCode(int codeVal){
        for (SubjectInfoEnum resultCodeEnum: SubjectInfoEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }
}
