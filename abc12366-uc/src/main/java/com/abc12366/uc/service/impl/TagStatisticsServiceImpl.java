package com.abc12366.uc.service.impl;

import com.abc12366.uc.mapper.db2.TagStatisticsRoMapper;
import com.abc12366.uc.model.bo.TagStatisticsBO;
import com.abc12366.uc.service.TagStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-16
 * Time: 10:23
 */
@Service
public class TagStatisticsServiceImpl implements TagStatisticsService {

    @Autowired
    private TagStatisticsRoMapper tagStatisticsRoMapper;

    @Override
    public List<TagStatisticsBO> selectStatistics(String tagName) {
        return tagStatisticsRoMapper.selectStatistics();
    }
}
