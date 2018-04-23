package com.abc12366.cms.service;

import com.abc12366.cms.model.bo.SiteIssueBo;

import java.util.List;
import java.util.Map;

public interface SiteIssueService {

    List<SiteIssueBo> selectList(Map<String, Object> map);

    SiteIssueBo save(SiteIssueBo siteIssueBo);

    SiteIssueBo update(SiteIssueBo siteIssueBo);

    String deleteList(String[] issueIds);

    SiteIssueBo selectOneById(String issueId);
}
