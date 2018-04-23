package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.TagStatisticsBO;

import java.util.List;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-16
 * Time: 10:23
 */
public interface TagStatisticsService {
    List<TagStatisticsBO> selectStatistics(String tagName);
}
