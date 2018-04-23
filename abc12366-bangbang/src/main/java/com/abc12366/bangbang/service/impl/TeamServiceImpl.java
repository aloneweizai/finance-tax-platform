package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.TeamMapper;
import com.abc12366.bangbang.mapper.db2.TeamRoMapper;
import com.abc12366.bangbang.model.Team;
import com.abc12366.bangbang.model.bo.TeamBO;
import com.abc12366.bangbang.model.bo.TeamInsertBO;
import com.abc12366.bangbang.model.bo.TeamUpdateBO;
import com.abc12366.bangbang.service.TeamService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-29
 * Time: 10:12
 */
@Service
public class TeamServiceImpl implements TeamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);

    @Autowired
    private TeamRoMapper teamRoMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<TeamBO> selectList() {
        return teamRoMapper.selectList();
    }

    @Override
    public TeamBO insert(TeamInsertBO teamInsertBO) {
        LOGGER.info("{}", teamInsertBO);
        Team team = new Team();
        BeanUtils.copyProperties(teamInsertBO, team);
        Date date = new Date();
        team.setId(Utils.uuid());
        team.setCreateTime(date);
        team.setLastUpdate(date);
        int result = teamMapper.insert(team);
        if (result < 1) {
            throw new ServiceException(4101);
        }
        TeamBO teamBO = new TeamBO();
        BeanUtils.copyProperties(team, teamBO);
        return teamBO;
    }

    @Override
    public void delete(String id) {
        LOGGER.info("{}", id);
        int result = teamMapper.delete(id);
        if (result != 1) {
            throw new ServiceException(4103);
        }
    }

    @Override
    public TeamBO update(TeamUpdateBO teamUpdateBO, String id) {
        LOGGER.info("{}", teamUpdateBO);
        Team team = new Team();
        BeanUtils.copyProperties(teamUpdateBO, team);
        team.setId(id);
        team.setLastUpdate(new Date());
        int result = teamMapper.update(team);
        if (result != 1) {
            throw new ServiceException(4102);
        }
        TeamBO teamBO = new TeamBO();
        BeanUtils.copyProperties(team, teamBO);
        return teamBO;
    }

    @Override
    public TeamBO selectOne(String id) {
        return teamRoMapper.selectOne(id);
    }

    @Override
    public List<TeamBO> selectListByUserId(String userId) {
        return teamRoMapper.selectListByUserId(userId);
    }
}
