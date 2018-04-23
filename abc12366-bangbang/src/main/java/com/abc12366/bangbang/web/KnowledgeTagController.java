package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.KnowledgeTag;
import com.abc12366.bangbang.service.KnowledgeTagService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/4 15:57
 */

@RestController
@RequestMapping(path = "/KnowledgeTag", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class KnowledgeTagController {

    @Autowired
    private KnowledgeTagService knowledgeTagService;

    /*
    *  查询关联问题最多的标签
    */
    @GetMapping(path = "/listHot")
    public ResponseEntity selectHotTagName(@RequestParam(value = "num", defaultValue = "5") int num) {

        List<String> list = knowledgeTagService.selectHotTag(num);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", list));
    }

    /*
    *  知识库标签 分页查询
    *  支持 关键字查询
    */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "keywords", required = false) String keywords,
                                     @RequestParam(value = "status", required = false) Boolean status,
                                     @RequestParam(value = "tagType", required = false) String tagType) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);

        List<KnowledgeTag> list = knowledgeTagService.selectList(keywords, status, tagType);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    /*
    *  知识库标签
    *  根据知识库id 查询相关联的标签
    */
    @GetMapping(path = "/list/{knowledgeId}")
    public ResponseEntity selectRelatedTags(@PathVariable String knowledgeId) {

        List<KnowledgeTag> list = knowledgeTagService.selectRelatedTags(knowledgeId);
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", list));
    }

    /*
    *  知识库标签
    *  根据知识库id 查询
    */
    @GetMapping(path = "/{id}")
    public ResponseEntity view(@PathVariable String id) {
        KnowledgeTag tag = knowledgeTagService.selectByPk(id);
        return ResponseEntity.ok(Utils.kv("data", tag));
    }


    /**
     * 添加知识库标签 接口
     */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody KnowledgeTag knowledgeTag) {
        knowledgeTagService.add(knowledgeTag);
        return ResponseEntity.ok(Utils.kv("data", knowledgeTag));
    }

    /**
     * 添加知识库标签 接口
     */
    @PostMapping(path = "/batch/add")
    public ResponseEntity addBatch(@RequestBody List<KnowledgeTag> knowledgeTags) {
        List<KnowledgeTag> list = knowledgeTagService.addBatch(knowledgeTags);
        return ResponseEntity.ok(Utils.kv("dataList",list));
    }

    /**
     * 通过知识库，课程页面 添加知识库标签 接口
     */
    @PostMapping(path = "/addByOtherChannel")
    public ResponseEntity add(@RequestBody List<KnowledgeTag> knowledgeTags) {
        List<KnowledgeTag> list = knowledgeTagService.addBatchByOtherChannel(knowledgeTags);
        return ResponseEntity.ok(Utils.kv("dataList",list));
    }


    /*
    * 修改知识库标签 接口
    */
    @PutMapping(path = "/modify")
    public ResponseEntity modify(@RequestBody KnowledgeTag knowledgeTag) {
        knowledgeTagService.modify(knowledgeTag);
        return ResponseEntity.ok(Utils.kv("data", knowledgeTag));
    }

    /*
    * 删除知识库标签 接口
    */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        knowledgeTagService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /*
    * 批量删除知识库标签 接口
    */
    @DeleteMapping(path = "/batch/delete")
    public ResponseEntity batchDelete(@RequestBody List<String> ids) {
        knowledgeTagService.delete(ids);
        return ResponseEntity.ok(Utils.kv());
    }

    /*
    *  停用启用 接口
    */
    @PutMapping(path = "/modifyStatus/{id}/{status}")
    public ResponseEntity modifyStatus(@PathVariable String id, @PathVariable Boolean status) {
        knowledgeTagService.modifyStatus(id, status);
        return ResponseEntity.ok(Utils.kv());
    }

}
