package com.jingdian.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jingdian.subject.application.convert.SubjectCategoryDTOConverter;
import com.jingdian.subject.application.dto.SubjectCategoryDTO;
import com.jingdian.subject.common.entity.Result;
import com.jingdian.subject.domain.convert.SubjectCategoryConverter;
import com.jingdian.subject.domain.entity.SubjectCategoryBO;
import com.jingdian.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 冯正阳
 * 刷题模块
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCateGoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){

        SubjectCategoryBO subjectCategoryBO = null;
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCateGoryController.add.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()),"分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"分类父级id不能为空");
            subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertBoToCategory(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok();
        } catch (Exception e) {
            log.error("SubjectCateGoryController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){

        List<SubjectCategoryBO> subjectCategoryBOList = null;
        try {
            SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();
            subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoTOCategoryBO(subjectCategoryDTO);
            subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOS = SubjectCategoryDTOConverter.INSTANCE.convertBoTPCategoryDTO(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOS);
        } catch (Exception e) {
            log.error("SubjectCateGoryController.queryPrimaryCategory.error:{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }

    @GetMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){

        List<SubjectCategoryBO> subjectCategoryBOList = null;
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCateGoryController.queryCategoryByPrimary:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"分类id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoTOCategoryBO(subjectCategoryDTO);

            subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            return Result.ok(subjectCategoryBOList);
        } catch (Exception e) {
            log.error("SubjectCateGoryController.queryPrimaryCategory.error:{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }

    /**
     * 更新分类
     */
    @GetMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCateGoryController.update.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"分类id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoTOCategoryBO(subjectCategoryDTO);

            Boolean result = subjectCategoryDomainService.update(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            return Result.fail("更新分类失败");
        }
    }

    @GetMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCateGoryController.delete.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"分类id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoTOCategoryBO(subjectCategoryDTO);

            Boolean result = subjectCategoryDomainService.delete(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            return Result.fail("删除失败");
        }
    }
}

