package com.abc12366.uc.model.gift.bo;

import java.io.Serializable;


/**
 * 礼包申请表与礼包关联表
 **/
@SuppressWarnings("serial")
public class GiftApplyBO implements Serializable {

    /**
     * PK
     **/
    private String id;

    /**
     * 申请ID
     **/
    private String applyId;

    /**
     * 礼物ID
     **/
    private String giftId;

    /**
     * 礼物名称
     **/
    private String giftName;

    /**
     * 礼物金额
     **/
    private double giftAmount;

    /**
     * 礼物数量
     **/
    private int giftNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public double getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(double giftAmount) {
        this.giftAmount = giftAmount;
    }

    public int getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(int giftNum) {
        this.giftNum = giftNum;
    }
}
