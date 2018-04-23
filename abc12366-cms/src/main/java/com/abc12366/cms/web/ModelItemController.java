package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.IdsBo;
import com.abc12366.cms.model.bo.ModelItemBo;
import com.abc12366.cms.model.bo.ModelItemListBo;
import com.abc12366.cms.service.ModelItemService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
 * 模型项管理模块
 *
 * @author xieyanmao
 * @create 2017-05-08
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/modelItem", headers = Constant.VERSION_HEAD + "=1")
public class ModelItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelItemController.class);

    @Autowired
    private ModelItemService modelItemService;

    /**
     * 模型项列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "modelId", required = false) String modelId,
                                     @RequestParam(value = "isChannel", required = false) String isChannel) {
        //查询模型项
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("modelId", modelId);//模型ID
        dataMap.put("isChannel", isChannel);//是否为栏目
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<ModelItemBo> dataList = modelItemService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 新增模型项
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody ModelItemBo modelItemBo) {
        LOGGER.info("{}", modelItemBo);
        //新增模型项信息
        modelItemBo = modelItemService.save(modelItemBo);
        return ResponseEntity.ok(Utils.kv("data", modelItemBo));
    }

    /**
     * 查询单个模型项
     */
    @GetMapping(path = "/{modelItemId}")
    public ResponseEntity selectOne(@PathVariable String modelItemId) {
        //查询模型项信息
        ModelItemBo modelBo = modelItemService.selectModel(modelItemId);
        return ResponseEntity.ok(Utils.kv("data", modelBo));
    }

    /**
     * 更新模型项
     */
    @PutMapping(path = "/{modelItemId}")
    public ResponseEntity update(@PathVariable String modelItemId,
                                 @Valid @RequestBody ModelItemBo modelItemBo) {
        //更新模型项信息
        modelItemBo = modelItemService.update(modelItemBo);
        return ResponseEntity.ok(Utils.kv("data", modelItemBo));
    }

    /**
     * 批量新增模型项
     */
    @PostMapping(path = "/saveList")
    public ResponseEntity saveList(@RequestBody ModelItemListBo modelItemListBo) {
        //批量新增模型项信息
        modelItemListBo = modelItemService.saveList(modelItemListBo);
        return ResponseEntity.ok(Utils.kv("data", modelItemListBo));
    }

    /**
     * 批量更新模型项
     */
    @PutMapping(path = "/updateList")
    public ResponseEntity updateList(@RequestBody ModelItemListBo modelItemListBo) {
        //批量更新模型项信息
        modelItemListBo = modelItemService.updateList(modelItemListBo);
        return ResponseEntity.ok(Utils.kv("data", modelItemListBo));
    }

    /**
     * 删除模型项
     */
    @DeleteMapping(path = "/{modelItemId}")
    public ResponseEntity delete(@PathVariable String modelItemId) {
        //删除模型项信息
        String rtn = modelItemService.delete(modelItemId);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 批量删除模型项
     */
    @PostMapping(path = "/deleteList")
    public ResponseEntity deleteList(@RequestBody IdsBo idsBo) {
        //批量删除模型项信息
        String rtn = modelItemService.deleteList(idsBo.getIds());
        return ResponseEntity.ok(Utils.kv("data", idsBo));
    }


}
