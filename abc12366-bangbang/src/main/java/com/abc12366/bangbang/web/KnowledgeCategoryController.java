package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.KnowledgeCategory;
import com.abc12366.bangbang.model.bo.SortBO;
import com.abc12366.bangbang.service.KnowledgeCategoryService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author liuqi
 * @Date 2017/8/2 20:58
 * 知识库 分类 接口
 */

@RestController
@RequestMapping(path = "/knowledgeCategory", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class KnowledgeCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeBaseController.class);

    @Autowired
    private KnowledgeCategoryService knowledgeCategoryService;

    /* 查询所有分类 */
    @GetMapping("/listAll")
    public ResponseEntity listAll() {
        List<KnowledgeCategory> all = knowledgeCategoryService.listAll();
        return ResponseEntity.ok(Utils.kv("dataList", all));
    }

    /* 新增分类 */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody KnowledgeCategory record) {
        KnowledgeCategory rs = knowledgeCategoryService.add(record);
        return ResponseEntity.ok(Utils.kv("data", rs));
    }

    /* 修改分类 */
    @PutMapping(path = "/modify")
    public ResponseEntity modify(@RequestBody KnowledgeCategory record) {
        KnowledgeCategory rs = knowledgeCategoryService.modify(record);
        return ResponseEntity.ok(Utils.kv("data", rs));
    }

    /* 修改分类的名称 */
    @PutMapping(path = "/modifyName/{id}")
    public ResponseEntity modifyName(@PathVariable String id, @RequestBody Map<String,String> map) {
        String name = map.get("name");
        knowledgeCategoryService.modifyNameById(id, name);
        return ResponseEntity.ok(Utils.kv());
    }

    /* 修改分类排序 */
    @PutMapping(path = "/modifySort")
    public ResponseEntity modifySort(@RequestBody List<SortBO> list) {
        knowledgeCategoryService.modifySort(list);
        return ResponseEntity.ok(Utils.kv());
    }

    /* 删除分类*/
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        knowledgeCategoryService.deleteById(id);
        return ResponseEntity.ok(Utils.kv());
    }


}
