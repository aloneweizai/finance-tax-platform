package com.abc12366.uc.model.weixin.bo.redpack;

import java.util.Date;
import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-22 5:11 PM
 * @since 1.0.0
 */
public class GetRedPackResp {

    private String return_code;

    private String return_msg;

    private String result_code;

    private String err_code;

    private String err_code_des;

    // 商户订单号 商户使用查询API填写的商户单号的原路返回
    private String mch_billno;

    // 商户号 微信支付分配的商户号
    private String mch_id;

    // 红包单号 使用API发放现金红包时返回的红包单号
    private String detail_id;

    // 红包状态 SENDING:发放中 SENT:已发放待领取 FAILED：发放失败 RECEIVED:已领取 RFUND_ING:退款中 REFUND:已退款
    private String status;

    // 发放类型
    // API:通过API接口发放
    // UPLOAD:通过上传文件方式发放
    // ACTIVITY:通过活动方式发放
    private String send_type;

    // 红包类型 GROUP:裂变红包 NORMAL:普通红包
    private String hb_type;

    // 红包个数
    private Integer total_num;

    // 红包金额
    private Integer total_amount;

    // 失败原因
    private String reason;

    // 红包发送时间	send_time	是	2015-04-21 20:00:00	String(32)
    private Date send_time;

    // 红包退款时间	refund_time	否	2015-04-21 23:03:00	String(32)	红包的退款时间（如果其未领取的退款）
    private Date refund_time;

    // 红包退款金额	refund_amount	否	8000	Int	红包退款金额
    private Integer refund_amount;

    // 祝福语	wishing	否	新年快乐	String(128)	祝福语
    private String wishing;

    // 活动描述	remark	否	新年红包	String(256)	活动描述，低版本微信可见
    private String remark;

    // 活动名称	act_name	否	新年红包	String(32)	发红包的活动名称
    private String act_name;

    // 裂变红包领取列表	hblist	否	内容如下表	 	裂变红包的领取列表
    private List<BrokeRedPack> hblist;

    // 领取红包的Openid	openid	是	ohO4GtzOAAYMp2yapORH3dQB3W18	String(32)	领取红包的openid
    private String openid;

    // 金额	amount	是	100	int	领取金额
    private Integer amount;

    // 接收时间	rcv_time	是	2015-04-21 20:00:00	String(32)	领取红包的时间
    private Date rcv_time;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getMch_billno() {
        return mch_billno;
    }

    public void setMch_billno(String mch_billno) {
        this.mch_billno = mch_billno;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }

    public String getHb_type() {
        return hb_type;
    }

    public void setHb_type(String hb_type) {
        this.hb_type = hb_type;
    }

    public Integer getTotal_num() {
        return total_num;
    }

    public void setTotal_num(Integer total_num) {
        this.total_num = total_num;
    }

    public Integer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Integer total_amount) {
        this.total_amount = total_amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public Date getRefund_time() {
        return refund_time;
    }

    public void setRefund_time(Date refund_time) {
        this.refund_time = refund_time;
    }

    public Integer getRefund_amount() {
        return refund_amount;
    }

    public void setRefund_amount(Integer refund_amount) {
        this.refund_amount = refund_amount;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public List<BrokeRedPack> getHblist() {
        return hblist;
    }

    public void setHblist(List<BrokeRedPack> hblist) {
        this.hblist = hblist;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getRcv_time() {
        return rcv_time;
    }

    public void setRcv_time(Date rcv_time) {
        this.rcv_time = rcv_time;
    }

    @Override
    public String toString() {
        return "GetRedPackResp{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code='" + err_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", mch_billno='" + mch_billno + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", detail_id='" + detail_id + '\'' +
                ", status='" + status + '\'' +
                ", send_type='" + send_type + '\'' +
                ", hb_type='" + hb_type + '\'' +
                ", total_num=" + total_num +
                ", total_amount=" + total_amount +
                ", reason='" + reason + '\'' +
                ", send_time=" + send_time +
                ", refund_time=" + refund_time +
                ", refund_amount=" + refund_amount +
                ", wishing='" + wishing + '\'' +
                ", remark='" + remark + '\'' +
                ", act_name='" + act_name + '\'' +
                ", hblist=" + hblist +
                ", openid='" + openid + '\'' +
                ", amount=" + amount +
                ", rcv_time=" + rcv_time +
                '}';
    }
}
