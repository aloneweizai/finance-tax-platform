package com.abc12366.gateway.service;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.mapper.db1.BlacklistMapper;
import com.abc12366.gateway.mapper.db2.BlacklistRoMapper;
import com.abc12366.gateway.model.Blacklist;
import com.abc12366.gateway.model.bo.BlacklistBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-06 1:58 PM
 * @since 1.0.0
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlacklistServiceImpl.class);

    @Autowired
    private BlacklistRoMapper blacklistRoMapper;

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    public boolean isBlacklist(String addr) {
        Blacklist blackList = new Blacklist();
        blackList.setStatus(true);
        blackList.setNow(new Date());
        List<Blacklist> blacklists = blacklistRoMapper.isBlacklist(blackList);
        return blacklists.size() > 0;
    }

    @Override
    public List<Blacklist> selectList() {
        return blacklistRoMapper.selectList();
    }

    @Override
    public Blacklist selectOne(String id) {
        Blacklist blacklist = new Blacklist();
        blacklist.setId(id);
        return blacklistRoMapper.selectOne(blacklist);
    }

    @Override
    public Blacklist insert(BlacklistBO bo) {
        Blacklist blacklist = new Blacklist();
        BeanUtils.copyProperties(bo, blacklist);

        int insert = blacklistMapper.insert(blacklist);
        if (insert != 1) {
            LOGGER.warn("新增异常{}", blacklist);
            throw new ServiceException(4101);
        }
        return blacklist;
    }

    @Override
    public Blacklist update(BlacklistBO bo) {
        Blacklist blacklist = new Blacklist();
        BeanUtils.copyProperties(bo, blacklist);

        int update = blacklistMapper.update(blacklist);
        if (update != 1) {
            LOGGER.warn("修改异常{}", blacklist);
            throw new ServiceException(4102);
        }
        return blacklist;
    }

    @Override
    public void delete(String id) {
        int del = blacklistMapper.delete(id);
        if (del != 1) {
            LOGGER.warn("删除异常{}", del);
            throw new ServiceException(4103);
        }
    }

    @Override
    public Blacklist selectByIp(String ip) {
        return blacklistRoMapper.selectByIp(ip);
    }
}
