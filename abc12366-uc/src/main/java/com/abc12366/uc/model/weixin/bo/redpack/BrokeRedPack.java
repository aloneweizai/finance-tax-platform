package com.abc12366.uc.model.weixin.bo.redpack;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-22 5:29 PM
 * @since 1.0.0
 */
public class BrokeRedPack {

    private String openid;
    private String amount;
    private String rcv_time;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRcv_time() {
        return rcv_time;
    }

    public void setRcv_time(String rcv_time) {
        this.rcv_time = rcv_time;
    }

    @Override
    public String toString() {
        return "BrokeRedPack{" +
                "openid='" + openid + '\'' +
                ", amount='" + amount + '\'' +
                ", rcv_time='" + rcv_time + '\'' +
                '}';
    }
}
