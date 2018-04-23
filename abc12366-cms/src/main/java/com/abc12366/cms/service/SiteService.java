package com.abc12366.cms.service;

import com.abc12366.cms.model.bo.SiteBo;
import com.abc12366.cms.model.bo.SiteListBo;

import java.util.List;

public interface SiteService {

    List<SiteListBo> selectList();

    SiteBo save(SiteBo siteBo);

    SiteBo update(SiteBo siteBo);

    SiteBo selectOneById(String id);

}
