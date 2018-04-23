package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.TeamMemberMapper;
import com.abc12366.bangbang.mapper.db2.TeamMemberRoMapper;
import com.abc12366.bangbang.model.TeamMember;
import com.abc12366.bangbang.model.bo.TeamMemberBO;
import com.abc12366.bangbang.model.bo.TeamMemberInsertBO;
import com.abc12366.bangbang.model.bo.TeamMemberUpdateBO;
import com.abc12366.bangbang.service.TeamMemberService;
import com.abc12366.gateway.exception.ServiceException;
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
 * Time: 14:26
 */
@Service
public class TeamMemberServiceImpl implements TeamMemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamMemberServiceImpl.class);

    @Autowired
    private TeamMemberRoMapper teamMemberRoMapper;

    @Autowired
    private TeamMemberMapper teamMemberMapper;


    @Override
    public List<TeamMemberBO> selectList() {
        return teamMemberRoMapper.selectList();
    }

    @Override
    public TeamMemberBO insert(TeamMemberInsertBO teamMemberInsertBO) {
        LOGGER.info("{}", teamMemberInsertBO);
        TeamMember teamMember = new TeamMember();
        BeanUtils.copyProperties(teamMemberInsertBO, teamMember);
        Date date = new Date();
        teamMember.setCreateTime(date);
        teamMember.setLastUpdate(date);
        int result = teamMemberMapper.insert(teamMember);
        if (result < 1) {
            throw new ServiceException(4101);
        }
        TeamMemberBO teamMemberBO = new TeamMemberBO();
        BeanUtils.copyProperties(teamMember, teamMemberBO);
        return teamMemberBO;
    }

    @Override
    public void delete(String teamId, String userId) {
        LOGGER.info("{}:{}", teamId, userId);
        TeamMember teamMember = new TeamMember();
        teamMember.setTeamId(teamId);
        teamMember.setUserId(userId);
        int result = teamMemberMapper.delete(teamMember);
        if (result < 1) {
            throw new ServiceException(4103);
        }
    }

    @Override
    public TeamMemberBO update(TeamMemberUpdateBO teamMemberUpdateBO, String teamId, String userId) {
        LOGGER.info("{}", teamMemberUpdateBO);
        TeamMember teamMember = new TeamMember();
        BeanUtils.copyProperties(teamMemberUpdateBO, teamMember);
        Date date = new Date();
        teamMember.setTeamId(teamId);
        teamMember.setUserId(userId);
        teamMember.setCreateTime(date);
        teamMember.setLastUpdate(date);
        int result = teamMemberMapper.update(teamMember);
        if (result < 1) {
            throw new ServiceException(4101);
        }
        TeamMemberBO teamMemberBO = new TeamMemberBO();
        BeanUtils.copyProperties(teamMember, teamMemberBO);
        return teamMemberBO;
    }

    @Override
    public TeamMemberBO selectOne(String teamId, String userId) {
        LOGGER.info("{}:{}", teamId, userId);
        TeamMember teamMember = new TeamMember();
        teamMember.setTeamId(teamId);
        teamMember.setUserId(userId);
        return teamMemberRoMapper.selectOne(teamMember);
    }
}
