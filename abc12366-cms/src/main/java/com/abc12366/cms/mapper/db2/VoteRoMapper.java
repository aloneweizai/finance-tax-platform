package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Vote;
import com.abc12366.cms.model.VoteResult;
import com.abc12366.cms.model.bo.VoteRolltjBo;
import com.abc12366.cms.model.bo.VoteRotptjBo;
import com.abc12366.cms.model.bo.VoteStatAreaBO;
import com.abc12366.cms.model.bo.VoteStatBrowserBO;

import java.util.List;
import java.util.Map;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-07 4:26 PM
 * @since 1.0.0
 */
public interface VoteRoMapper {
    List<Vote> selectList(Vote vote);

    Vote selectOne(String id);

    // 查询参与人数
    Integer selectResultCount(VoteResult result);

    // 查询浏览人数
    Integer selectHistoryCount(String voteId);

    // 统计浏览器访问情况
    List<VoteStatBrowserBO> statBrowser(String voteId);

    // 统计IP区域访问情况
    List<VoteStatAreaBO> statIpArea(String voteId);

    /**
     * 查询（浏览统计）
     **/
    List<VoteRolltjBo> selectlltj(Map<String, Object> map);

    /**
     * 查询（浏览统计总数按时间）
     **/
    Integer selectlltjsbysj(Map<String, Object> map);

    /**
     * 查询（浏览统计总数）
     **/
    Integer selectlltjs(Map<String, Object> map);

    /**
     * 查询（投票统计按时间）
     **/
    List<VoteRolltjBo> selecttptjbysj(Map<String, Object> map);

    /**
     * 查询（投票统计总数）
     **/
    Integer selecttptjs(Map<String, Object> map);

    /**
     * 查询（投票统计）
     **/
    List<VoteRotptjBo> selecttptj(Map<String, Object> map);

    /**
     * 查询投票记录
     */
    List<VoteResult> selectResultList(VoteResult voteResult);
}
