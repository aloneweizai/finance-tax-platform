package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.TeamBO;
import com.abc12366.bangbang.model.bo.TeamInsertBO;
import com.abc12366.bangbang.model.bo.TeamUpdateBO;
import com.abc12366.bangbang.service.TeamService;
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
import java.util.List;


/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-27
 * Time: 17:36
 */
@RestController
@RequestMapping(path = "/team", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class TeamController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}", page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<TeamBO> teamBOList = teamService.selectList();
        LOGGER.info("{}", teamBOList);
        return (teamBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) teamBOList, "total", ((Page) teamBOList).getTotal()));
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody TeamInsertBO teamInsertBO) {
        LOGGER.info("{}", teamInsertBO);
        TeamBO teamBO = teamService.insert(teamInsertBO);
        LOGGER.info("{}", teamBO);
        return ResponseEntity.ok(Utils.kv("data", teamBO));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("{}", id);
        teamService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody TeamUpdateBO teamUpdateBO, @PathVariable String id) {
        LOGGER.info("{}:{}", teamUpdateBO, id);
        TeamBO teamBO = teamService.update(teamUpdateBO, id);
        LOGGER.info("{}", teamBO);
        return ResponseEntity.ok(Utils.kv("data", teamBO));
    }

}
