package com.abc12366.bangbang.web;

import com.abc12366.bangbang.model.FollowLecturer;
import com.abc12366.bangbang.model.bo.FollowLecturerBO;
import com.abc12366.bangbang.service.FollowLecturerService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户关注讲师表控制类
 *
 * @author lizhongwei
 * @date 2017-03-12
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/followlecturer", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class FollowLecturerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FollowLecturerController.class);

    @Autowired
    private FollowLecturerService followLecturerService;

    /**
     * 查询列表详解
     */
    @GetMapping()
    public ResponseEntity selectList(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "lecturerId", required = false) String lecturerId,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("lecturerId", lecturerId);
        map.put("status", status);
        LOGGER.info("{}:{}:{}", map, page, size);

        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<FollowLecturerBO> dataList = followLecturerService.selectList(map);
        PageInfo<FollowLecturerBO> pageInfo = new PageInfo<>(dataList);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 查询讲师是否关注
     */
    @GetMapping(path = "/{lecturerId}")
    public ResponseEntity selectIsFollow(@PathVariable String lecturerId, HttpServletRequest request) {
        LOGGER.info("{}", lecturerId);
        FollowLecturer bo = new FollowLecturer();
        bo.setUserId(Utils.getUserId(request));
        bo.setLecturerId(lecturerId);
        return ResponseEntity.ok(Utils.kv("data",  followLecturerService.selectIsFollow(bo)));
    }

    /**
     * 关注和取消关注
     */
    @PutMapping(path = "/{lecturerId}")
    public ResponseEntity followOrUnfollow(@PathVariable String lecturerId, HttpServletRequest request) {
        LOGGER.info("{}", lecturerId);
        FollowLecturer bo = new FollowLecturer();
        bo.setUserId(Utils.getUserId(request));
        bo.setLecturerId(lecturerId);
        return ResponseEntity.ok(Utils.kv("data",  followLecturerService.followOrUnfollow(bo)));
    }
}
