package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * CMS活动表
 **/
@SuppressWarnings("serial")
public class AnswerLogtjListBo implements Serializable {

    //浏览统计
    private List<AnswerLogRolltjBo> list;

    //pc浏览统计
    private List<AnswerLogRolltjBo> pclist;

    //mobileWeb浏览统计
    private List<AnswerLogRolltjBo> mobileWeblist;

    //浏览统计总数按时间
    private Integer llcnt;

    //浏览统计总数
    private Integer llcnts;

    public List<AnswerLogRolltjBo> getList() {
        return list;
    }

    public void setList(List<AnswerLogRolltjBo> list) {
        this.list = list;
    }

    public List<AnswerLogRolltjBo> getPclist() {
        return pclist;
    }

    public void setPclist(List<AnswerLogRolltjBo> pclist) {
        this.pclist = pclist;
    }

    public List<AnswerLogRolltjBo> getMobileWeblist() {
        return mobileWeblist;
    }

    public void setMobileWeblist(List<AnswerLogRolltjBo> mobileWeblist) {
        this.mobileWeblist = mobileWeblist;
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
