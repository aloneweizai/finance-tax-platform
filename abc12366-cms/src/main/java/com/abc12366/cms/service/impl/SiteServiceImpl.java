package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.SiteMapper;
import com.abc12366.cms.mapper.db2.SiteRoMapper;
import com.abc12366.cms.model.Site;
import com.abc12366.cms.model.bo.SiteBo;
import com.abc12366.cms.model.bo.SiteListBo;
import com.abc12366.cms.service.SiteService;
import com.abc12366.gateway.exception.ServiceException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author xieyanmao
 * @create 2017-05-18 3:10 PM
 * @since 1.0.0
 */
@Service
public class SiteServiceImpl implements SiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteServiceImpl.class);

    @Autowired
    private SiteMapper siteMapper;

    @Autowired
    private SiteRoMapper siteRoMapper;

    @Override
    public List<SiteListBo> selectList() {
        List<SiteListBo> siteListBo;
        try {
            siteListBo = siteRoMapper.selectList();
            //JSONArray jsonArray = JSONArray.fromObject(siteListBo);
            //LOGGER.info("查询站点信息结果:{}", jsonArray.toString());
        } catch (Exception e) {
            LOGGER.error("查询站点信息异常：{}", e);
            throw new ServiceException(4210);
        }
        return siteListBo;
    }

    @Override
    public SiteBo selectOneById(String siteId) {
        SiteBo siteBo = new SiteBo();
        try {
            LOGGER.info("查询单个站点信息:{}", siteId);
            Site site = siteRoMapper.selectByPrimaryKey(siteId);
            BeanUtils.copyProperties(site, siteBo);
        } catch (Exception e) {
            LOGGER.error("查询单个站点信息异常：{}", e);
            throw new ServiceException(4211);
        }
        return siteBo;
    }

    @Override
    public SiteBo save(SiteBo siteBo) {
        SiteBo siteBo1 = new SiteBo();
        siteBo1.setSitePath(siteBo.getSitePath());
        int cnt = siteRoMapper.selectCnt(siteBo1);
        if (cnt > 0) {
            throw new ServiceException(4214);
        }
        try {
            JSONObject jsonStu = JSONObject.fromObject(siteBo);
            LOGGER.info("新增站点信息:{}", jsonStu.toString());
            Site site = new Site();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            siteBo.setSiteId(uuid);
            BeanUtils.copyProperties(siteBo, site);
            siteMapper.insert(site);
        } catch (Exception e) {
            LOGGER.error("新增站点信息异常：{}", e);
            throw new ServiceException(4212);
        }
        return siteBo;
    }

    @Override
    public SiteBo update(SiteBo siteBo) {
        SiteBo siteBo1 = new SiteBo();
        siteBo1.setSitePath(siteBo.getSitePath());
        siteBo1.setSiteId(siteBo.getSiteId());
        int cnt = siteRoMapper.selectCnt(siteBo1);
        if (cnt > 0) {
            throw new ServiceException(4214);
        }
        try {
            JSONObject jsonStu = JSONObject.fromObject(siteBo);
            LOGGER.info("更新站点信息:{}", jsonStu.toString());
            Site site = new Site();
            BeanUtils.copyProperties(siteBo, site);
            siteMapper.updateByPrimaryKeySelective(site);
        } catch (Exception e) {
            LOGGER.error("更新站点信息异常：{}", e);
            throw new ServiceException(4213);
        }
        return siteBo;
    }


}
