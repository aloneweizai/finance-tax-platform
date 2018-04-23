package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.bo.AdPageBO;
import org.apache.ibatis.annotations.Param;

/**
 * ���ͼƬ����
 *
 * @author yuanluo <ljun51@outlook.com>
 * @create 2017-07-26 4:21 PM
 * @since 1.0.0
 */

public interface AdPageMapper {


    /**
     * ��������ɾ�����ͼƬ
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * �������ͼƬ
     **/
    int insert(AdPageBO adPage);


    /**
     * ���¹��ͼƬ
     **/
    int update(AdPageBO adPage);

}