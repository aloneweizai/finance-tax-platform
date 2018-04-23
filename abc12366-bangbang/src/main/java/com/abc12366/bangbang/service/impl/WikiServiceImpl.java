package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.WikiAccesslogMapper;
import com.abc12366.bangbang.mapper.db1.WikiMapper;
import com.abc12366.bangbang.mapper.db2.WikiRoMapper;
import com.abc12366.bangbang.model.Wiki;
import com.abc12366.bangbang.model.WikiAccesslog;
import com.abc12366.bangbang.model.bo.WikiAccesslogBO;
import com.abc12366.bangbang.model.bo.WikiBO;
import com.abc12366.bangbang.service.WikiService;
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
 * @author lizhongwei
 * @create 2017-06-19
 * @since 1.0.0
 */
@Service
public class WikiServiceImpl implements WikiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikiServiceImpl.class);

    @Autowired
    private WikiMapper wikiMapper;

    @Autowired
    private WikiRoMapper wikiRoMapper;

    @Autowired
    private WikiAccesslogMapper wikiAccesslogMapper;

    @Override
    public List<WikiBO> selectList(WikiBO wikiBO) {
        return wikiRoMapper.selectList(wikiBO);
    }

    @Override
    public WikiBO selectOne(String id) {
        return wikiRoMapper.selectOne(id);
    }

    @Override
    public WikiBO update(WikiBO wikiBO) {
        Wiki wiki = new Wiki();
        BeanUtils.copyProperties(wikiBO, wiki);
        wiki.setLastUpdate(new Date());
        int upd = wikiMapper.update(wiki);
        if (upd != 1) {
            LOGGER.info("维基百科数据，修改失败", wikiBO);
            throw new ServiceException(4502);
        }
        WikiBO bo = new WikiBO();
        BeanUtils.copyProperties(wiki, bo);
        return bo;
    }

    @Override
    public WikiBO addWiki(WikiBO wikiBO) {
        Wiki wiki = new Wiki();
        BeanUtils.copyProperties(wikiBO, wiki);
        wiki.setId(Utils.uuid());
        Date date = new Date();
        wiki.setLastUpdate(date);
        wiki.setCreateTime(date);
        int insert = wikiMapper.insert(wiki);
        if (insert != 1) {
            LOGGER.info("维基百科数据，新增失败", wikiBO);
            throw new ServiceException(4501);
        }
        WikiBO bo = new WikiBO();
        BeanUtils.copyProperties(wiki, bo);
        return bo;
    }

    @Override
    public void deleteWiki(WikiBO wikiBO) {
        int del = wikiMapper.deleteByPrimaryKey(wikiBO.getId());
        if (del != 1) {
            LOGGER.info("维基百科数据，删除失败", wikiBO);
            throw new ServiceException(4503);
        }
    }

    @Override
    public WikiAccesslogBO addWikiLog(WikiAccesslogBO accesslogBO) {
        WikiAccesslog accesslog = new WikiAccesslog();
        BeanUtils.copyProperties(accesslogBO, accesslog);
        accesslog.setId(Utils.uuid());
        Date date = new Date();
        accesslog.setCreateTime(date);
        int insert = wikiAccesslogMapper.insert(accesslog);
        if (insert != 1) {
            LOGGER.info("维基百科日志数据，新增失败", accesslogBO);
            throw new ServiceException(4504);
        }
        WikiAccesslogBO bo = new WikiAccesslogBO();
        BeanUtils.copyProperties(accesslog, bo);
        return bo;
    }
}
