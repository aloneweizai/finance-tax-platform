package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author liuguiyao
 * @create 2017-05-08 10:08 AM
 * @since 1.0.0
 */
public class NsrSelectBO {
    @NotEmpty(message = "userId不能为空！")
    private String userId;
    private String djxh;//登记序号
    private String nsrsbh;//纳税人识别号
    private String shxydm;//社会信用代码

    public NsrSelectBO() {
    }

    public NsrSelectBO(String userId, String djxh, String nsrsbh, String shxydm) {
        this.userId = userId;
        this.djxh = djxh;
        this.nsrsbh = nsrsbh;
        this.shxydm = shxydm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDjxh() {
        return djxh;
    }

    public void setDjxh(String djxh) {
        this.djxh = djxh;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getShxydm() {
        return shxydm;
    }

    public void setShxydm(String shxydm) {
        this.shxydm = shxydm;
    }

    @Override
    public String toString() {
        return "NsrSelectBO{" +
                "userId='" + userId + '\'' +
                ", djxh='" + djxh + '\'' +
                ", nsrsbh='" + nsrsbh + '\'' +
                ", shxydm='" + shxydm + '\'' +
                '}';
    }
}
