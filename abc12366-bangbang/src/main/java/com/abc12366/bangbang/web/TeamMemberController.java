package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.bo.TeamMemberBO;
import com.abc12366.bangbang.model.bo.TeamMemberInsertBO;
import com.abc12366.bangbang.model.bo.TeamMemberUpdateBO;
import com.abc12366.bangbang.service.TeamMemberService;
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
@RequestMapping(path = "/teammember", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class TeamMemberController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamMemberController.class);

    @Autowired
    private TeamMemberService teamMemberService;

    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{}:{}", page, size);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<TeamMemberBO> teamMemberBOList = teamMemberService.selectList();
        LOGGER.info("{}", teamMemberBOList);
        return (teamMemberBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) teamMemberBOList, "total", ((Page) teamMemberBOList)
                        .getTotal()));
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody TeamMemberInsertBO teamMemberInsertBO) {
        LOGGER.info("{}", teamMemberInsertBO);
        TeamMemberBO teamMemberBO = teamMemberService.insert(teamMemberInsertBO);
        LOGGER.info("{}", teamMemberBO);
        return ResponseEntity.ok(Utils.kv("data", teamMemberBO));
    }

    @DeleteMapping(path = "/{teamId}/{userId}")
    public ResponseEntity delete(@PathVariable String teamId, @PathVariable String userId) {
        LOGGER.info("{}:{}", teamId, userId);
        teamMemberService.delete(teamId, userId);
        return ResponseEntity.ok(Utils.kv());
    }

    @PutMapping(path = "/{teamId}/{userId}")
    public ResponseEntity update(@Valid @RequestBody TeamMemberUpdateBO teamMemberUpdateBO, @PathVariable String
            teamId, @PathVariable String userId) {
        LOGGER.info("{}:{}", teamMemberUpdateBO, teamId, userId);
        TeamMemberBO teamMemberBO = teamMemberService.update(teamMemberUpdateBO, teamId, userId);
        LOGGER.info("{}", teamMemberBO);
        return ResponseEntity.ok(Utils.kv("data", teamMemberBO));
    }

    @GetMapping(path = "/{teamId}/{userId}")
    public ResponseEntity selectOne(@PathVariable String teamId, @PathVariable String userId) {
        LOGGER.info("{}:{}", teamId, userId);
        TeamMemberBO teamMemberBO = teamMemberService.selectOne(teamId, userId);
        LOGGER.info("{}", teamMemberBO);
        return ResponseEntity.ok(Utils.kv("data", teamMemberBO));
    }
}
