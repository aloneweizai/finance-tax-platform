package com.abc12366.uc.model;

import java.io.Serializable;


/**
 *
 *
 *
 **/
@SuppressWarnings("serial")
public class Province implements Serializable {

    /**
     * 省ID
     **/
    private String provinceId;

    /**
     * 省名称
     **/
    private String province;

    public String getProvinceId() {
        return this.provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
