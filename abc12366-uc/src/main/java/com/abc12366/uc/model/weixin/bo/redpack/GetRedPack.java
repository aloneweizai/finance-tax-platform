package com.abc12366.uc.model.weixin.bo.redpack;

/**
 * 查询红包信息
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-22 5:01 PM
 * @since 1.0.0
 */
public class GetRedPack {

    // 随机字符串
    private String nonce_str;
    // 签名
    private String sign;
    // 商户订单号 28bit
    private String mch_billno;
    // 商户号
    private String mch_id;
    // 公众账号appid
    private String appid;
    // MCHT
    private String bill_type;

    public GetRedPack() {
    }

    private GetRedPack(Builder builder) {
        setNonce_str(builder.nonce_str);
        setSign(builder.sign);
        setMch_billno(builder.mch_billno);
        setMch_id(builder.mch_id);
        setAppid(builder.appid);
        setBill_type(builder.bill_type);
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    @Override
    public String toString() {
        return "GetRedPack{" +
                "nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", mch_billno='" + mch_billno + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", appid='" + appid + '\'' +
                ", bill_type='" + bill_type + '\'' +
                '}';
    }

    public static final class Builder {
        private String nonce_str;
        private String sign;
        private String mch_billno;
        private String mch_id;
        private String appid;
        private String bill_type;

        public Builder() {
        }

        public Builder nonce_str(String val) {
            nonce_str = val;
            return this;
        }

        public Builder sign(String val) {
            sign = val;
            return this;
        }

        public Builder mch_billno(String val) {
            mch_billno = val;
            return this;
        }

        public Builder mch_id(String val) {
            mch_id = val;
            return this;
        }

        public Builder appid(String val) {
            appid = val;
            return this;
        }

        public Builder bill_type(String val) {
            bill_type = val;
            return this;
        }

        public GetRedPack build() {
            return new GetRedPack(this);
        }
    }
}
