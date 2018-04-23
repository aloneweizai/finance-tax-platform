package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.AdpageLogBO;
import com.abc12366.cms.service.AdpageLogService;
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

@RestController
@RequestMapping(path = "/adpagelog", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class AdpageLogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdpageLogController.class);
//
    @Autowired
    private AdpageLogService adpageLogService;
            
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "adpageid", required = false) String adpageid,
                                     @RequestParam(required = false, defaultValue = Constant.pageNum) int page,
                                     @RequestParam(required = false, defaultValue = Constant.pageSize) int size) {
        Map<String, Object> map = new HashMap<>();
        if(adpageid != null && !adpageid.isEmpty()) {
            map.put("adPageId", adpageid);
        }
      PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
      List<AdpageLogBO> list = adpageLogService.selectList(map);
      LOGGER.info("{}", list);
      return (list == null) ?
              ResponseEntity.ok(Utils.kv()) :
              ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal
                      ()));
    	}

    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        AdpageLogBO adpageLogBO = adpageLogService.selectOne(id);
        LOGGER.info("{}", adpageLogBO);
        return ResponseEntity.ok(Utils.kv("data", adpageLogBO));
    }

  @PostMapping
  public ResponseEntity insert(@Valid @RequestBody AdpageLogBO adpageLogInsertBO) {
      LOGGER.info("{}", adpageLogInsertBO);
      AdpageLogBO adpageLogBOReturn = adpageLogService.insert(adpageLogInsertBO);
      LOGGER.info("{}", adpageLogBOReturn);
      return ResponseEntity.ok(Utils.kv("data", adpageLogBOReturn));
  }
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody AdpageLogBO adpageLogUpdateBO,
                                 @PathVariable String id) {
        LOGGER.info("{}", adpageLogUpdateBO);
        AdpageLogBO adpageLogBOReturn = adpageLogService.update(adpageLogUpdateBO, id);
        LOGGER.info("{}", adpageLogBOReturn);
        return ResponseEntity.ok(Utils.kv("data", adpageLogBOReturn));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("{}", id);
        adpageLogService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }
}
