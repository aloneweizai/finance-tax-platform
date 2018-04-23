package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.bo.NoticeBO;
import org.apache.ibatis.annotations.Param;

/**
 * ֪ͨ�������
 *
 * @author yuanluo <ljun51@outlook.com>
 * @create 2017-07-26 4:21 PM
 * @since 1.0.0
 */

public interface NoticeMapper {


    /**
     * ��������ɾ��֪ͨ����
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * ����֪ͨ����
     **/
    int insert(NoticeBO notice);


    /**
     * ����֪ͨ����
     **/
    int update(NoticeBO notice);

    int updatecount(NoticeBO notice);

}