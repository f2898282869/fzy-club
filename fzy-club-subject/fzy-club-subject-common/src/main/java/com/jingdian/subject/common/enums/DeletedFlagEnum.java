package com.jingdian.subject.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 冯正阳
 * 删除状态枚举类
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DeletedFlagEnum {

    DELETED(1,"已删除"),
    UN_DELETED(0,"未删除");

    public int code;
    public String desc;
}
