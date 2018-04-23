package com.abc12366.uc.model.weixin.bo.qrcode;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-29 11:53 AM
 * @since 1.0.0
 */
public class TicketResp {

    private String ticket;
    private Integer expire_seconds;
    private String url;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(Integer expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "TicketResp{" +
                "ticket='" + ticket + '\'' +
                ", expire_seconds=" + expire_seconds +
                ", url='" + url + '\'' +
                '}';
    }
}
