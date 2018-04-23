package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.SiteIssueMapper;
import com.abc12366.cms.mapper.db2.SiteIssueRoMapper;
import com.abc12366.cms.model.SiteIssue;
import com.abc12366.cms.model.bo.SiteIssueBo;
import com.abc12366.cms.service.SiteIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author xieyanmao
 * @create 2017-05-18 3:10 PM
 * @since 1.0.0
 */
@Service
public class SiteIssueServiceImpl implements SiteIssueService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteIssueServiceImpl.class);

    @Autowired
    private SiteIssueMapper siteIssueMapper;

    @Autowired
    private SiteIssueRoMapper siteIssueRoMapper;

    @Override
    public List<SiteIssueBo> selectList(Map<String, Object> map) {
        List<SiteIssueBo> siteIssueListBo = siteIssueRoMapper.selectList(map);
        LOGGER.info("{}", siteIssueListBo);
        return siteIssueListBo;
    }

    @Override
    public SiteIssueBo selectOneById(String issueId) {
        SiteIssue siteIssue = siteIssueRoMapper.selectByPrimaryKey(issueId);
        SiteIssueBo siteIssueBo = new SiteIssueBo();
        try {
            try {
                BeanUtils.copyProperties(siteIssue, siteIssueBo);
            } catch (Exception e) {
                LOGGER.error("类转换异常：{}", e);
                throw new RuntimeException("类型转换异常：{}", e);
            }
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        LOGGER.info("{}", siteIssueBo);
        return siteIssueBo;
    }

    @Override
    public SiteIssueBo save(SiteIssueBo siteIssueBo) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        siteIssueBo.setIssueId(uuid);
        siteIssueBo.setIssueDate(new Date());
        SiteIssue siteIssue = new SiteIssue();
        try {
            BeanUtils.copyProperties(siteIssueBo, siteIssue);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        int count = siteIssueMapper.insert(siteIssue);
        LOGGER.info("{}", count);
        return siteIssueBo;
    }

    @Override
    public SiteIssueBo update(SiteIssueBo siteIssueBo) {
        siteIssueBo.setUpdateDate(new Date());
        SiteIssue siteIssue = new SiteIssue();
        try {
            BeanUtils.copyProperties(siteIssueBo, siteIssue);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        int count = siteIssueMapper.updateByPrimaryKeySelective(siteIssue);
        LOGGER.info("{}", count);
        return siteIssueBo;
    }

    @Override
    public String deleteList(String[] issueIds) {
        int r = siteIssueMapper.deleteList(issueIds);
        LOGGER.info("{}", r);
        return "";
    }


}
