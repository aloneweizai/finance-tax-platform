package com.abc12366.cms.model.event;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by stuy on 2017/9/14.
 */
public class EventApplyBbBo implements Serializable {
    private String applyId;
    private String eventId;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    private String source;
    private Date applytime;
    private String status;
    private String userid;



    private List<EventApplyCentenBo> eventApplyCentenBo;

    public List<EventApplyCentenBo> getEventApplyCentenBo() {
        return eventApplyCentenBo;
    }

    public void setEventApplyCentenBo(List<EventApplyCentenBo> eventApplyCentenBo) {
        this.eventApplyCentenBo = eventApplyCentenBo;
    }
}
