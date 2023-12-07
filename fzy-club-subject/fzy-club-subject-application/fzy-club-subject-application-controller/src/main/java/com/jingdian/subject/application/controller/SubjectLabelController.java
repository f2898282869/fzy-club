package com.jingdian.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jingdian.subject.application.convert.SubjectLabelDTOConverter;
import com.jingdian.subject.application.dto.SubjectLabelDTO;
import com.jingdian.subject.common.entity.Result;
import com.jingdian.subject.domain.entity.SubjectLabelBO;
import com.jingdian.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 冯正阳
 * 标签controller
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    /**
     * 新增标签
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.add.dto{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getLabelName(),"标签名称不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOTOBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.add.dto{}",e.getMessage(),e);
            return Result.fail("新增标签失败");
        }
    }

    /**
     * 更新标签
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.update.dto{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOTOBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.update.dto{}",e.getMessage(),e);
            return Result.fail("更新标签失败");
        }
    }

    /**
     * 删除标签
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.delete.dto{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOTOBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.delete(subjectLabelBO);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.delete.dto{}",e.getMessage(),e);
            return Result.fail("删除标签失败");
        }
    }

    /**
     * 查询分类下标签
     */
    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectLabelController.queryLabelByCategoryId.dto{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"分类id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDTOTOBO(subjectLabelDTO);
            List<SubjectLabelBO> resultList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> result = SubjectLabelDTOConverter.INSTANCE.convertBOTODTO(resultList);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.queryLabelByCategoryId.dto{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }
}
