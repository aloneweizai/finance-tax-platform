package com.abc12366.cms.model.event;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by stuy on 2017/9/14.
 */
public class EventRecordBbBo implements Serializable {
    private String recordId;
    private String eventId;
    private String source;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
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

    public Date getBrowsetime() {
        return browsetime;
    }

    public void setBrowsetime(Date browsetime) {
        this.browsetime = browsetime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private Date browsetime;
    private String ip;
}
