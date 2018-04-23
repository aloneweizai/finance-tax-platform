package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * CMS活动表
 **/
@SuppressWarnings("serial")
public class VotetjListBo implements Serializable {

    //pc浏览统计
    private List<VoteRolltjBo> pclist;

    //mobileWeb浏览统计
    private List<VoteRolltjBo> mobileWeblist;

    //投票统计按时间
    private List<VoteRolltjBo> tptj;

    //投票详细统计
    private List<VoteRotptjBo> tpxxtj;

    //投票统计总数
    private Integer tpcnt;

    //浏览统计总数按时间
    private Integer llcnt;

    //浏览统计总数
    private Integer llcnts;

    public List<VoteRolltjBo> getPclist() {
        return pclist;
    }

    public void setPclist(List<VoteRolltjBo> pclist) {
        this.pclist = pclist;
    }

    public List<VoteRolltjBo> getMobileWeblist() {
        return mobileWeblist;
    }

    public void setMobileWeblist(List<VoteRolltjBo> mobileWeblist) {
        this.mobileWeblist = mobileWeblist;
    }

    public List<VoteRolltjBo> getTptj() {
        return tptj;
    }

    public void setTptj(List<VoteRolltjBo> tptj) {
        this.tptj = tptj;
    }

    public List<VoteRotptjBo> getTpxxtj() {
        return tpxxtj;
    }

    public void setTpxxtj(List<VoteRotptjBo> tpxxtj) {
        this.tpxxtj = tpxxtj;
    }

    public Integer getTpcnt() {
        return tpcnt;
    }

    public void setTpcnt(Integer tpcnt) {
        this.tpcnt = tpcnt;
    }

    public Integer getLlcnt() {
        return llcnt;
    }

    public void setLlcnt(Integer llcnt) {
        this.llcnt = llcnt;
    }

    public Integer getLlcnts() {
        return llcnts;
    }

    public void setLlcnts(Integer llcnts) {
        this.llcnts = llcnts;
    }
}
