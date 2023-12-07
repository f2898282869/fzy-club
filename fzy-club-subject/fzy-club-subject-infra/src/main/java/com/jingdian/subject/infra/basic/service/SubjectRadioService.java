package com.jingdian.subject.infra.basic.service;

import com.jingdian.subject.infra.basic.entity.SubjectRadio;

import java.util.List;


/**
 * 单选题信息表(SubjectRadio)表服务接口
 *
 * @author makejava
 * @since 2023-11-28 21:24:33
 */
public interface SubjectRadioService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectRadio queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    SubjectRadio insert(SubjectRadio subjectRadio);

    /**
     * 修改数据
     *
     * @param subjectRadio 实例对象
     * @return 实例对象
     */
    SubjectRadio update(SubjectRadio subjectRadio);

    /**
     * 批量添加数据
     * @param subjectRadioList
     */
    void batchInsert(List<SubjectRadio> subjectRadioList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<SubjectRadio> queryByCondition(SubjectRadio subjectRadio);
}
