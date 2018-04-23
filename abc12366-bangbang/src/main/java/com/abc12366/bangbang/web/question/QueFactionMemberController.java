package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.QuestionFactionMemberBo;
import com.abc12366.bangbang.service.QueFactionMemberService;
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
 * 邦派成员管理模块
 *
 * @author xieyanmao
 * @create 2017-09-14
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/queFacMember", headers = Constant.VERSION_HEAD + "=1")
public class QueFactionMemberController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueFactionMemberController.class);

    @Autowired
    private QueFactionMemberService queFactionMemberService;

    /**
     * 邦派成员列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "userId",required = false)String userId,
                                     @RequestParam(value = "factionId", required = false) String factionId,
                                     @RequestParam(value = "status", required = false) String status) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("factionId", factionId);//
        dataMap.put("status", status);//
        dataMap.put("userId", userId);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionFactionMemberBo> dataList = queFactionMemberService.selectList(dataMap);
        //计算帮派成员荣誉值
        if(dataList !=null && dataList.size()>0){
            for(QuestionFactionMemberBo memberBo : dataList){
                int honor = 2*memberBo.getAnswerNum() + 1*memberBo.getDiscussNum() + 7*memberBo.getAdoptNum();
                memberBo.setHonor(honor+"");
            }
        }
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 邦派成员列表查询
     */
    @GetMapping(path = "/selectListTj")
    public ResponseEntity selectListTj(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "factionId", required = false) String factionId,
                                       @RequestParam(value = "userId", required = false) String userId) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("factionId", factionId);//
        if(userId ==null || "".equals(userId)){
            userId = "11";
        }
        dataMap.put("userId", userId);//
        List<QuestionFactionMemberBo> dataList = queFactionMemberService.selectListTj(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }

    /**
     * 邦派成员新增
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody QuestionFactionMemberBo FactionMemberBo) {
        //新增邦派成员信息
        FactionMemberBo = queFactionMemberService.save(FactionMemberBo);
        return ResponseEntity.ok(Utils.kv("data", FactionMemberBo));
    }

    /**
     * 查询单个邦派成员信息
     */
    @GetMapping(path = "/{memberId}")
    public ResponseEntity selectOne(@PathVariable String memberId) {
        //查询单个邦派成员信息
        QuestionFactionMemberBo FactionMemberBo = queFactionMemberService.selectFactionMember(memberId);
        return ResponseEntity.ok(Utils.kv("data", FactionMemberBo));
    }

    /**
     * 更新邦派成员信息
     */
    @PutMapping(path = "/{memberId}")
    public ResponseEntity update(@PathVariable String memberId,
                                 @Valid @RequestBody QuestionFactionMemberBo FactionMemberBo) {
        //更新邦派成员信息
        FactionMemberBo = queFactionMemberService.update(FactionMemberBo);
        return ResponseEntity.ok(Utils.kv("data", FactionMemberBo));
    }

    /**
     * 更新邦派成员状态
     *
     * @param status
     * @param memberId
     * @return
     */
    @PutMapping(path = "/updateStatus/{memberId}")
    public ResponseEntity updateStatus(@Valid @RequestBody String status, @PathVariable("memberId") String memberId) {
        queFactionMemberService.updateStatus(memberId, status);
        return ResponseEntity.ok(Utils.kv("data", memberId));
    }

    /**
     * 设置职位
     *
     * @param duty
     * @param memberId
     * @return
     */
    @PutMapping(path = "/updateDuty/{memberId}")
    public ResponseEntity updateDuty(@Valid @RequestBody String duty, @PathVariable("memberId") String memberId) {
        queFactionMemberService.updateDuty(memberId, duty);
        return ResponseEntity.ok(Utils.kv("data", memberId));
    }

    /**
     * 删除邦派成员信息
     */
    @DeleteMapping(path = "/{memberId}")
    public ResponseEntity delete(@PathVariable String memberId) {
        //删除邦派成员信息
        String rtn = queFactionMemberService.delete(memberId);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }


}
