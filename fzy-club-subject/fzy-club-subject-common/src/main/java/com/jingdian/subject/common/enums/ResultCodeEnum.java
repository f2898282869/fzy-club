package com.jingdian.subject.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 冯正阳
 */
@AllArgsConstructor
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(500,"失败");

    private int code;
    private String desc;

    public static ResultCodeEnum getByCode(int codeVal){
        for (ResultCodeEnum resultCodeEnum: ResultCodeEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }
}
