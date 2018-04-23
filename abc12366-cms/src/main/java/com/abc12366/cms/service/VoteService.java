package com.abc12366.cms.service;

import com.abc12366.cms.model.Vote;
import com.abc12366.cms.model.VoteHistory;
import com.abc12366.cms.model.VoteResult;
import com.abc12366.cms.model.bo.SubItemBo;
import com.abc12366.cms.model.bo.VoteStatAreaBO;
import com.abc12366.cms.model.bo.VoteStatBrowserBO;
import com.abc12366.cms.model.bo.VotetjListBo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 投票功能服务类
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-07 4:19 PM
 * @since 1.0.0
 */
public interface VoteService {
    List<Vote> selectList(Vote vote, int page, int size);

    Vote insert(Vote vote);

    Vote selectOne(String id);

    Vote update(Vote vote);

    void delete(String id);

    List<VoteResult> vote(String voteId, List<VoteResult> resultList, HttpServletRequest request);

    VoteResult vote(VoteResult result, HttpServletRequest request);

    VoteHistory insertHistory(String voteId, HttpServletRequest request);

    List<VoteStatBrowserBO> statBrowser(String voteId);

    List<VoteStatAreaBO> statIpArea(String voteId);

    Map<String, Integer> statViews(String voteId);

    VotetjListBo selecttj(Map<String, Object> map);

    String updateItemStatus(SubItemBo subItemBo);

    List<VoteResult> selectResultList(VoteResult voteResult, int page, int size);

    void deleteLog(String id);

}
