package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.bo.AdPageBO;

import java.util.List;

/**
 * @author yuanluo <ljun51@outlook.com>
 * @create 2017-07-26 4:18 PM
 * @since 1.0.0
 */
public interface AdPageRoMapper {

    List<AdPageBO> selectList(AdPageBO adPageBO);

    List<AdPageBO> selectListForqt(AdPageBO adPageBO);

    AdPageBO selectOne(String id);
}
