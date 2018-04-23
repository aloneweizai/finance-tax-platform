package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.ViewMapper;
import com.abc12366.bangbang.mapper.db2.ViewRoMapper;
import com.abc12366.bangbang.model.View;
import com.abc12366.bangbang.model.bo.ViewBO;
import com.abc12366.bangbang.service.ViewService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-12
 * Time: 9:34
 */
@Service
public class ViewServiceImpl implements ViewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewServiceImpl.class);

    @Autowired
    private ViewMapper viewMapper;

    @Autowired
    private ViewRoMapper viewRoMapper;

    @Override
    public ViewBO insert(String askId, HttpServletRequest request) {
        LOGGER.info("{}:{}", askId, request);
        String userId = Utils.getUserId(request);
        Date date = new Date();
        View view = new View();
        view.setId(Utils.uuid());
        view.setAskId(askId);
        view.setUserId(userId);
        view.setStatus(true);
        view.setCreateTime(date);
        view.setLastUpdate(date);
        int result = viewMapper.insert(view);
        if (result < 1) {
            throw new ServiceException(4710);
        }
        ViewBO viewBO = new ViewBO();
        BeanUtils.copyProperties(view, viewBO);
        return viewBO;
    }

    @Override
    public void delete(String askId, HttpServletRequest request) {
        LOGGER.info("{}:{}", askId, request);
        String userId = Utils.getUserId(request);
        View view = new View();
        view.setAskId(askId);
        view.setUserId(userId);
        view.setStatus(false);
        view.setLastUpdate(new Date());
        int result = viewMapper.update(view);
        if (result < 1) {
            throw new ServiceException(4711);
        }
    }

    @Override
    public List<ViewBO> selectList(String userId) {
        LOGGER.info("{}", userId);
        return viewRoMapper.selectList(userId);
    }

    @Override
    public int selectCount(String askId) {
        LOGGER.info("{}", askId);
        return Integer.parseInt(viewRoMapper.selectCount(askId));
    }
}
