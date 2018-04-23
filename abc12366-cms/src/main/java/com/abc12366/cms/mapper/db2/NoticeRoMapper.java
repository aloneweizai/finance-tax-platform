package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.bo.NoticeBO;
import com.abc12366.cms.model.bo.NoticeForqtBO;

import java.util.List;

/**
 * @author yuanluo <ljun51@outlook.com>
 * @create 2017-07-26 4:18 PM
 * @since 1.0.0
 */
public interface NoticeRoMapper {

    List<NoticeBO> selectList(NoticeBO notice);

    List<NoticeForqtBO> selectListForqt(NoticeForqtBO notice);

    NoticeBO selectOne(String id);
}
