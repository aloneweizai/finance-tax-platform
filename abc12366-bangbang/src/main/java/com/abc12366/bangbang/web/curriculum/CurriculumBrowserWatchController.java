package com.abc12366.bangbang.web.curriculum;

import com.abc12366.bangbang.model.curriculum.CurriculumBrowserWatch;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumListBo;
import com.abc12366.bangbang.service.CurriculumBrowserWatchService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
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
 * @Author liuQi
 * @Date 2018/1/30 16:58
 */
@RestController
@RequestMapping(path = "/curriculumBrowserWatch", headers = Constant.VERSION_HEAD + "=1")
public class CurriculumBrowserWatchController {

    @Autowired
    private CurriculumBrowserWatchService curriculumBrowserWatchService;


    /**
     * 查询今天数量
     */
    @GetMapping(path = "/selectTodayNum")
    public ResponseEntity selectTodayNum() {
        CurriculumBrowserWatch data = curriculumBrowserWatchService.selectTodayNum();
        return ResponseEntity.ok(Utils.kv("data", data));
    }


    /**
     * 查询本月数量
     */
    @GetMapping(path = "/selectCurrentMonthNum")
    public ResponseEntity selectCurrentMonthNum() {
        CurriculumBrowserWatch data = curriculumBrowserWatchService.selectCurrentMonthNum();
        return ResponseEntity.ok(Utils.kv("data", data));
    }


    /**
     * 列表查询
     */
    @GetMapping(path = "/selectList")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "date", required = false) String date) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("date", date);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CurriculumListBo> dataList = null;
        dataList = curriculumBrowserWatchService.selectList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 列表查询
     */
    @GetMapping(path = "/selectMonthList")
    public ResponseEntity selectMonthList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "month", required = false) String month) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("month", month);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CurriculumListBo> dataList = curriculumBrowserWatchService.selectMonthList(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

}
