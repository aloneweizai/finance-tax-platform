package com.abc12366.uc.service.impl;

import com.abc12366.uc.mapper.db2.NsrBindQueryRoMapper;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.service.NsrBindQueryService;
import com.abc12366.uc.service.UserService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-16
 * Time: 11:45
 */
@Service
public class NsrBindQueryServiceImpl implements NsrBindQueryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NsrBindQueryServiceImpl.class);

    @Autowired
    private NsrBindQueryRoMapper nsrBindQueryRoMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<NsrBindQueryBO> selectList(Map<String, Object> map, int page, int size) {
        List<NsrBindQueryBO> dataList = new ArrayList<>();

        // 直接查询主表
        if (StringUtils.isEmpty(map.get("username")) &&
                (!StringUtils.isEmpty(map.get("nsrsbh")) || !StringUtils.isEmpty(map.get("status")))) {
            NsrBindQueryParamBO bo = new NsrBindQueryParamBO();
            if (!StringUtils.isEmpty(map.get("nsrsbh"))) {
                bo.setNsrsbh(String.valueOf(map.get("nsrsbh")));
            }
            if (!StringUtils.isEmpty(map.get("status"))) {
                bo.setStatus((Boolean) map.get("status"));
            }
            dataList = selectListForType(map, page, size, dataList, bo);
        }
        // username不为空，先查询userId
        if (!StringUtils.isEmpty(map.get("username"))) {
            UserBO user = userService.selectByUsernameOrPhone(String.valueOf(map.get("username")));
            if (user != null) {
                NsrBindQueryParamBO bo = new NsrBindQueryParamBO();
                bo.setUserId(user.getId());
                if (!StringUtils.isEmpty(map.get("nsrsbh"))) {
                    bo.setNsrsbh(String.valueOf(map.get("nsrsbh")));
                }
                if (!StringUtils.isEmpty(map.get("status"))) {
                    bo.setStatus((Boolean) map.get("status"));
                }
                dataList = selectListForType(map, page, size, dataList, bo);
            }
        }
        // 默认查询
        if (StringUtils.isEmpty(map.get("nsrsbh")) && StringUtils.isEmpty(map.get("status"))
                && StringUtils.isEmpty(map.get("username"))) {
            NsrBindQueryParamBO bo = new NsrBindQueryParamBO();
            dataList = selectListForType(map, page, size, dataList, bo);
        }

        for (NsrBindQueryBO nbq : dataList) {
            User user = userService.selectUserById(new User(nbq.getUserId()));
            if (user != null) {
                nbq.setUsername(user.getUsername());
                nbq.setNickname(user.getNickname());
            }
        }
        return dataList;
    }

    private List<NsrBindQueryBO> selectListForType(Map<String, Object> map,
                                                   int page,
                                                   int size,
                                                   List<NsrBindQueryBO> dataList,
                                                   NsrBindQueryParamBO bo) {

        if (!StringUtils.isEmpty(map.get("type"))) {
            PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
            if ("hngs".equalsIgnoreCase(String.valueOf(map.get("type")))) {
                dataList = nsrBindQueryRoMapper.selectHngsList(bo);
            } else if ("hnds".equalsIgnoreCase(String.valueOf(map.get("type")))) {
                dataList = nsrBindQueryRoMapper.selectHndsList(bo);
            } else {
                dataList = nsrBindQueryRoMapper.selectDzsbList(bo);
            }
        }
        return dataList;
    }

    @Override
    public UserDzsbBO selectDzsb(String id) {
        LOGGER.info("{}", id);
        return nsrBindQueryRoMapper.selectDzsb(id);
    }

    @Override
    public UserHndsBO selectHnds(String id) {
        LOGGER.info("{}", id);
        return nsrBindQueryRoMapper.selectHnds(id);
    }

    @Override
    public UserHngsBO selectHngs(String id) {
        LOGGER.info("{}", id);
        return nsrBindQueryRoMapper.selectHngs(id);
    }
}
