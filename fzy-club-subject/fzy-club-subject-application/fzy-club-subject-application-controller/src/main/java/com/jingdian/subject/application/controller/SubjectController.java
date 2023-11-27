package com.jingdian.subject.application.controller;

import com.jingdian.subject.infra.basic.entity.SubjectCategory;
import com.jingdian.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 冯正阳
 */
@RestController
public class SubjectController {

    @Resource
    private SubjectCategoryService subjectCategoryService;


    @GetMapping("/test")
    public String test(){
        SubjectCategory subjectCategory = subjectCategoryService.queryById(1L);

        return subjectCategory.getCategoryName();
    }
}
