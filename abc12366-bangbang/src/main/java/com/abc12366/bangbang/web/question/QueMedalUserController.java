package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.QuestionMedalUserBo;
import com.abc12366.bangbang.service.QueMedalUserService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 邦派个人勋章管理
 *
 * @author xieyanmao
 * @create 2017-10-24
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/queMedalUser", headers = Constant.VERSION_HEAD + "=1")
public class QueMedalUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueMedalUserController.class);

    @Autowired
    private QueMedalUserService medalUserService;

    /**
     * 邦派个人勋章列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "userId", required = false) String userId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);//
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QuestionMedalUserBo> dataList = medalUserService.selectListByUserId(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

    }


}
