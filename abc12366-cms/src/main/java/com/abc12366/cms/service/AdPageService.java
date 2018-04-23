package com.abc12366.cms.service;

import com.abc12366.cms.model.bo.AdPageBO;

import java.util.List;

/**
 * 广告图片管理服务类
 *
 * @author yuanluo <ljun51@outlook.com>
 * @create 2017-07-26 4:19 PM
 * @since 1.0.0
 */
public interface AdPageService {
    List<AdPageBO> selectList(AdPageBO adPage, int page, int size);

    List<AdPageBO> selectListForqt(AdPageBO adPage, int page, int size);

    AdPageBO insert(AdPageBO adPage);

    AdPageBO selectOne(String id);

    AdPageBO selectOneForqt(String id);

    AdPageBO update(AdPageBO adPage);

    void delete(String id);
}
