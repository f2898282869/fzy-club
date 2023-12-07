package com.jingdian.subject.common.entity;

import lombok.Data;
import org.mapstruct.Mapper;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author 冯正阳
 * 分页返回实体
 */
@Data
public class PageResult<T> implements Serializable {

//    当前页
    private Integer pageNo = 1;
//   每页显示的条数
    private Integer pageSize = 20;
//   总条数
    private Integer total = 0;

//    总页数
    private Integer totalPages = 0;

    private List<T> result = Collections.emptyList();

    private Integer start = 1;

    private Integer end = 0;

    public void setRecords(List<T> result){
        this.result = result;
        if(result != null && result.size() > 0 && this.total != 0){
            setTotal(result.size());
        }
    }

    public void setTotal(Integer total){
        this.total = total;
        if(this.pageSize > 0){
//            总页数，先除，除不尽+1
            this.totalPages = (total / this.pageSize) + (total % this.pageSize == 0 ? 0: 1);
        }else {
            this.totalPages = 0;
        }
//
        this.start = (this.pageSize > 0 ? (this.pageNo - 1) * this.pageSize : 0) + 1;
        this.end = (this.start - 1 + this.pageSize * (this.pageNo > 0 ? 1 : 0));
    }

    public void setPageSize(Integer pageSize){
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo){
        this.pageNo = pageNo;
    }
}
