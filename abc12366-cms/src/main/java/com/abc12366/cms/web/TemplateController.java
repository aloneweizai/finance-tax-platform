package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.TemplateBo;
import com.abc12366.cms.service.TemplateService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板模块
 *
 * @author xieyanmao
 * @create 2017-06-27
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/template", headers = Constant.VERSION_HEAD + "=1")
public class TemplateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    private TemplateService templateService;

    /**
     * 查询模板列表信息
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "templateProperty", required = false) String
                                                 templateProperty,
                                     @RequestParam(value = "templateType", required = false) String templateType,
                                     @RequestParam(value = "siteId", required = false) String siteId,
                                     @RequestParam(value = "isFolder", required = false) String isFolder,
                                     @RequestParam(value = "parentPath", required = false) String parentPath,
                                     @RequestParam(value = "state", required = false) String state,
                                     @RequestParam(value = "templateName", required = false)String templateName) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("templateProperty", templateProperty);//模板属性
        dataMap.put("templateType", templateType);//模板类型
        dataMap.put("siteId", siteId);//站点ID
        dataMap.put("isFolder", isFolder);//是否文件夹

        dataMap.put("parentPath", parentPath);//父节点路径
        dataMap.put("state", state);//启停标志位
        dataMap.put("templateName",templateName);
        List<TemplateBo> dataList = templateService.selectList(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * 新增模板信息
     */
    @PostMapping
    public ResponseEntity save(@RequestBody TemplateBo templateBo) {
        LOGGER.info("{}", templateBo);
        //新增模板信息
        templateBo = templateService.save(templateBo);
        LOGGER.info("{}", templateBo);
        return ResponseEntity.ok(Utils.kv("data", templateBo));
    }

    /**
     * 查询单个模板信息
     */
    @GetMapping(path = "/{templateId}")
    public ResponseEntity selectOne(@PathVariable String templateId) {
        LOGGER.info("{}", templateId);
        //查询模板信息
        TemplateBo templateBo = templateService.selectTemplate(templateId);
        LOGGER.info("{}", templateBo);
        return ResponseEntity.ok(Utils.kv("data", templateBo));
    }

    /**
     * 更新模板信息
     */
    @PutMapping(path = "/{templateId}")
    public ResponseEntity update(@PathVariable String templateId,
                                 @Valid @RequestBody TemplateBo templateBo) {

        LOGGER.info("{}", templateBo);
        //更新模板信息
        templateBo = templateService.update(templateBo);
        LOGGER.info("{}", templateBo);
        return ResponseEntity.ok(Utils.kv("data", templateBo));
    }

    /**
     * 删除模板信息
     */
    @DeleteMapping(path = "/{templateId}")
    public ResponseEntity delete(@PathVariable String templateId) {
        LOGGER.info("{}", templateId);
        //删除模板信息
        String rtn = templateService.delete(templateId);
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

}
