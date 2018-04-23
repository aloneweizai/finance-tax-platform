package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.NoticeMapper;
import com.abc12366.cms.mapper.db2.NoticeRoMapper;
import com.abc12366.cms.model.bo.NoticeBO;
import com.abc12366.cms.model.bo.NoticeForqtBO;
import com.abc12366.cms.service.NoticeService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 通知公告管理实现类
 *
 * @author yuanluo <ljun51@outlook.com>
 * @create 2017-07-26 4:21 PM
 * @since 1.0.0
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);
    // 通知公告
    @Autowired
    private NoticeRoMapper noticeRoMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<NoticeBO> selectList(NoticeBO notice, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<NoticeBO> noticeList = noticeRoMapper.selectList(notice);
        return noticeList;
    }

    @Override
    public List<NoticeForqtBO> selectListForqt(NoticeForqtBO notice, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<NoticeForqtBO> noticeList = noticeRoMapper.selectListForqt(notice);
        return noticeList;
    }

    @Transactional("db1TxManager")
    @Override
    public NoticeBO insert(NoticeBO notice) {
        Timestamp now = new Timestamp(new Date().getTime());
        notice.setId(Utils.uuid());
        notice.setCreateTime(now);
        notice.setLastUpdate(now);
        notice.setCount(0);
        String status = notice.getStatus();
        if (status == null) {
            //默认草稿状态
            notice.setStatus("0");
        }
        if("1".equals(status)){
            notice.setReleaseTime(now);
        }

        noticeMapper.insert(notice);
        return notice;
    }

    @Override
    public NoticeBO selectOneForqt(String id) {
        NoticeBO notice = noticeRoMapper.selectOne(id);
        NoticeBO n = new NoticeBO();
        if (notice != null) {
            int num = notice.getCount();
            num += 1;
            n.setCount(num);
            n.setId(notice.getId());
            updateCount(n);
            return notice;
        } else {
            LOGGER.error("查询单个通知公告异常：{}", id);
            throw new ServiceException(4012);
        }
    }

    @Override
    public NoticeBO selectOne(String id) {
        NoticeBO notice = new NoticeBO();
        try {
            LOGGER.info("查询单个通知公告信息:{}", id);
            notice = noticeRoMapper.selectOne(id);
        } catch (Exception e) {
            LOGGER.error("查询单个通知公告异常：{}", e);
            throw new ServiceException(4234);
        }
        return notice;
    }

    public void updateCount(NoticeBO notice) {
        noticeMapper.updatecount(notice);
    }

    @Transactional("db1TxManager")
    @Override
    public NoticeBO update(NoticeBO notice) {
        Timestamp now = new Timestamp(new Date().getTime());
        String status = notice.getStatus();
        if("1".equals(status)){
            notice.setReleaseTime(now);
        }
        notice.setLastUpdate(now);
        int update = noticeMapper.update(notice);
        if (update != 1) {
            if (update != 1) {
                LOGGER.info("{修改通知公告失败}", update);
                throw new ServiceException(4421);
            }
        }
        return noticeRoMapper.selectOne(notice.getId());
    }

    @Transactional("db1TxManager")
    @Override
    public void delete(String id) {
        NoticeBO v = selectOne(id);
        if (v != null) {
            // 删除投票信息
            noticeMapper.deleteByPrimaryKey(id);
        } else {
            LOGGER.info("{删除通知公告失败}", id);
            throw new ServiceException(4012);
        }
    }

}
