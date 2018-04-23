package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.KnowledgeVoteLog;
import com.abc12366.bangbang.model.bo.KnowledgeBaseParamBO;
import com.abc12366.bangbang.model.bo.KnowledgeVoteLogBO;
import com.abc12366.bangbang.service.KnowledgeVoteService;
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
 * @Date 2017/8/11 09:34
 */
@RestController
@RequestMapping(path = "/knowledgeBase/vote", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class KnowledgeVoteController {

    @Autowired
    private KnowledgeVoteService knowledgeVoteService;

    /*
    * 知识库投票列表  接口
    */
    @GetMapping(path = "/list")
    public ResponseEntity selectVoteList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                         @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                         @RequestParam(value = "categoryCode", required = false) String categoryCode,
                                         @RequestParam(value = "keywords", required = false) String keywords,
                                         @RequestParam(value = "knowledgeType", required = false) String knowledgeType) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);


        KnowledgeBaseParamBO param = new KnowledgeBaseParamBO(categoryCode, knowledgeType, keywords, null, null,"");
        List<KnowledgeVoteLogBO> list = knowledgeVoteService.selectVoteList(param);

        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }

    /**
     * 为知识库投票
     */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody KnowledgeVoteLog knowledgeVoteLog) {
    	KnowledgeVoteLog log =knowledgeVoteService.selectByUserVotedKnowledge(knowledgeVoteLog.getUserId(), knowledgeVoteLog.getKnowledgeId());
    	if(log!=null){
    		return ResponseEntity.ok(Utils.bodyStatus(9999, "您已经投票过，不能重复投票!"));
    	}else{
    		knowledgeVoteService.addVote(knowledgeVoteLog);
            return ResponseEntity.ok(Utils.kv());
    	}
    }


    /*
     * 删除知识库投票 接口
     */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        knowledgeVoteService.deleteVoteLog(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /*
     * 批量删除知识库投票 接口
     */
    @DeleteMapping(path = "/delete")
    public ResponseEntity deleteList(@RequestBody List<String> ids) {
        knowledgeVoteService.deleteVoteLogs(ids);
        return ResponseEntity.ok(Utils.kv());
    }

    /*
    * 查询该知识库投过票的投票数据
    */
    @GetMapping(path = "/one/{userId}/{knowledgeId}")
    public ResponseEntity selectVoteList(@PathVariable String userId, @PathVariable String knowledgeId) {

        KnowledgeVoteLog log = knowledgeVoteService.selectByUserVotedKnowledge(userId, knowledgeId);

        return ResponseEntity.ok(Utils.kv("data", log));
    }
}
