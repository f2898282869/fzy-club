package com.jingdian.subject.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 冯正阳
 */
@Getter
@AllArgsConstructor
public enum CategoryTypeEnum {

    PRIMARY(1,"岗位大类"),
    SECOND(2,"二级分类");
    public int code;
    private String desc;
}
