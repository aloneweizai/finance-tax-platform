package com.abc12366.uc.model;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-22
 * Time: 10:18
 */
public class PrivilegeItem {
    private String levelId;
    //专享会员勋章：1：是，0：否
    private boolean zxhyxz;
    //会员积分加成
    private float hyjfjc;
    //会员经验值加成
    private float hyjyzjc;
    //用户升级奖励积分数
    private int yhsjjl;
    //用户财税经验证明印制,单位：次/年
    private int csjyzmyz_times;
    //用户财税经验证明印制,是否包邮
    private boolean csjyzmyz_baoyou;
    //用户帐号合并特权\r\n,单位：个/年
    private int yhzhhbtq;
    //在线课程培训\r\n:vip1,vip2,vip3,vip4
    private String zxkcpx;
    //线下课程培训，单位：次
    private int xxkcpx;
    //线下会员活动，单位：次/年
    private int xxhyhd;
    //线上会员日：享有/不享有
    private boolean xshyr;
    //个人帐号绑定企业户数,-1代表无限制
    private int grzhbdqys;
    //业务提醒-站内,是/否
    private boolean ywtx_zn;
    //业务提醒-微信,是/否
    private boolean ywtx_wx;
    //业务提醒-短信,是/否
    private boolean ywtx_dx;
    //发布求职信息，次/年
    private int fbqzxx;
    //生日礼包，vip2，vip3，vip4
    private String srlb;
    //用户积分转让-次数
    private int yhjfzr_cs;
    //用户积分转让-上限
    private int yhjfzr_sx;
    //商品/发票免邮券,单位：张
    private int spfpmyq;
    //专属客服经理,享有/不享有
    private boolean zskfjl;
    //财税期刊,享有/不享有
    private boolean csqk;

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public boolean isZxhyxz() {
        return zxhyxz;
    }

    public void setZxhyxz(boolean zxhyxz) {
        this.zxhyxz = zxhyxz;
    }

    public float getHyjfjc() {
        return hyjfjc;
    }

    public void setHyjfjc(float hyjfjc) {
        this.hyjfjc = hyjfjc;
    }

    public float getHyjyzjc() {
        return hyjyzjc;
    }

    public void setHyjyzjc(float hyjyzjc) {
        this.hyjyzjc = hyjyzjc;
    }

    public int getYhsjjl() {
        return yhsjjl;
    }

    public void setYhsjjl(int yhsjjl) {
        this.yhsjjl = yhsjjl;
    }

    public int getCsjyzmyz_times() {
        return csjyzmyz_times;
    }

    public void setCsjyzmyz_times(int csjyzmyz_times) {
        this.csjyzmyz_times = csjyzmyz_times;
    }

    public boolean isCsjyzmyz_baoyou() {
        return csjyzmyz_baoyou;
    }

    public void setCsjyzmyz_baoyou(boolean csjyzmyz_baoyou) {
        this.csjyzmyz_baoyou = csjyzmyz_baoyou;
    }

    public int getYhzhhbtq() {
        return yhzhhbtq;
    }

    public void setYhzhhbtq(int yhzhhbtq) {
        this.yhzhhbtq = yhzhhbtq;
    }

    public String getZxkcpx() {
        return zxkcpx;
    }

    public void setZxkcpx(String zxkcpx) {
        this.zxkcpx = zxkcpx;
    }

    public int getXxkcpx() {
        return xxkcpx;
    }

    public void setXxkcpx(int xxkcpx) {
        this.xxkcpx = xxkcpx;
    }

    public int getXxhyhd() {
        return xxhyhd;
    }

    public void setXxhyhd(int xxhyhd) {
        this.xxhyhd = xxhyhd;
    }

    public boolean isXshyr() {
        return xshyr;
    }

    public void setXshyr(boolean xshyr) {
        this.xshyr = xshyr;
    }

    public int getGrzhbdqys() {
        return grzhbdqys;
    }

    public void setGrzhbdqys(int grzhbdqys) {
        this.grzhbdqys = grzhbdqys;
    }

    public boolean isYwtx_zn() {
        return ywtx_zn;
    }

    public void setYwtx_zn(boolean ywtx_zn) {
        this.ywtx_zn = ywtx_zn;
    }

    public boolean isYwtx_wx() {
        return ywtx_wx;
    }

    public void setYwtx_wx(boolean ywtx_wx) {
        this.ywtx_wx = ywtx_wx;
    }

    public boolean isYwtx_dx() {
        return ywtx_dx;
    }

    public void setYwtx_dx(boolean ywtx_dx) {
        this.ywtx_dx = ywtx_dx;
    }

    public int getFbqzxx() {
        return fbqzxx;
    }

    public void setFbqzxx(int fbqzxx) {
        this.fbqzxx = fbqzxx;
    }

    public String getSrlb() {
        return srlb;
    }

    public void setSrlb(String srlb) {
        this.srlb = srlb;
    }

    public int getYhjfzr_cs() {
        return yhjfzr_cs;
    }

    public void setYhjfzr_cs(int yhjfzr_cs) {
        this.yhjfzr_cs = yhjfzr_cs;
    }

    public int getYhjfzr_sx() {
        return yhjfzr_sx;
    }

    public void setYhjfzr_sx(int yhjfzr_sx) {
        this.yhjfzr_sx = yhjfzr_sx;
    }

    public int getSpfpmyq() {
        return spfpmyq;
    }

    public void setSpfpmyq(int spfpmyq) {
        this.spfpmyq = spfpmyq;
    }

    public boolean isZskfjl() {
        return zskfjl;
    }

    public void setZskfjl(boolean zskfjl) {
        this.zskfjl = zskfjl;
    }

    public boolean isCsqk() {
        return csqk;
    }

    public void setCsqk(boolean csqk) {
        this.csqk = csqk;
    }
}
