package com.abc12366.bangbang.web.statis;

import com.abc12366.bangbang.model.curriculum.CurriculumBrowserWatch;
import com.abc12366.bangbang.service.CurriculumBrowserWatchService;
import com.abc12366.bangbang.service.FeedbackService;
import com.abc12366.bangbang.service.QuestionSysBlockService;
import com.abc12366.bangbang.service.QuestionTipOffService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2018/1/31 10:25
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class TodoListController {

    @Autowired
    private QuestionSysBlockService questionSysBlockService;

    @Autowired
    private QuestionTipOffService questionTipOffService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private CurriculumBrowserWatchService curriculumBrowserWatchService;


    @GetMapping("/todoList")
    public ResponseEntity selectList() {
        Map<String, Integer> map = new HashMap<>();

        Long sysBlockCnt = questionSysBlockService.selectCntByStatus("1");
        Long tipOffCnt = questionTipOffService.selectCntByStatus("auditing");
        Long feedbackUnansweredCnt = feedbackService.selectCntUnanswered();

        CurriculumBrowserWatch todayNum = curriculumBrowserWatchService.selectTodayNum();
        Integer todayBrowserCnt = 0;
        Integer todayWatchCnt = 0;
        if(todayNum != null){
            todayBrowserCnt = todayNum.getBrowseNum();
            todayWatchCnt = todayNum.getWatchNum();
        }

        CurriculumBrowserWatch currentMonthNum = curriculumBrowserWatchService.selectCurrentMonthNum();
        Integer currentMonthBrowserCnt = 0;
        Integer currentMonthWatchCnt = 0;
        if(currentMonthNum != null){
            currentMonthBrowserCnt = currentMonthNum.getBrowseNum();
            currentMonthWatchCnt = currentMonthNum.getWatchNum();
        }

        map.put("num1", sysBlockCnt.intValue());
        map.put("num2", tipOffCnt.intValue());
        map.put("num3", feedbackUnansweredCnt.intValue());
        map.put("num4", todayBrowserCnt);
        map.put("num5", todayWatchCnt);
        map.put("num6", currentMonthBrowserCnt);
        map.put("num7", currentMonthWatchCnt);
        return ResponseEntity.ok(Utils.kv("dataList", map));
    }



}
