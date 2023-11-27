package com.jingdian.subject.common.entity;

import com.jingdian.subject.common.enums.ResultCodeEnum;
import lombok.Data;

/**
 * @author 冯正阳
 * 数据返回值-->BaseResponse
 */
@Data
public class Result<T> {

    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    public static Result ok(){
        Result<Object> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        return result;
    }

    public static <T> Result ok(T data){
        Result<Object> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDesc());
        result.setData(data);
        return result;
    }


    public static Result fail(){
        Result<Object> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        return result;
    }

    public static <T> Result fail(T data){
        Result<Object> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getDesc());
        result.setData(data);
        return result;
    }
}
