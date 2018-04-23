package com.abc12366.cms.service;

import com.abc12366.cms.model.bo.NoticeBO;
import com.abc12366.cms.model.bo.NoticeForqtBO;

import java.util.List;

/**
 * 通知公告管理服务类
 *
 * @author yuanluo <ljun51@outlook.com>
 * @create 2017-07-26 4:19 PM
 * @since 1.0.0
 */
public interface NoticeService {
    List<NoticeBO> selectList(NoticeBO notice, int page, int size);

    List<NoticeForqtBO> selectListForqt(NoticeForqtBO notice, int page, int size);

    NoticeBO insert(NoticeBO notice);

    NoticeBO selectOne(String id);

    NoticeBO selectOneForqt(String id);

    NoticeBO update(NoticeBO notice);

    void delete(String id);
}
