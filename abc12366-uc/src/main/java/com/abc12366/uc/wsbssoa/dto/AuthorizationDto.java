package com.abc12366.uc.wsbssoa.dto;

/**
 * 菜单权限
 * Created by zhouzhi on 2015-12-18.
 */
public class AuthorizationDto implements java.io.Serializable {
    private String yyfwDm;  //应用服务代码
    private String yyfwMc;  //应用服务权限
    private String url;     //请求地址
    private String dlbz;    //登录标志

    public String getYyfwDm() {
        return yyfwDm;
    }

    public void setYyfwDm(String yyfwDm) {
        this.yyfwDm = yyfwDm;
    }

    public String getYyfwMc() {
        return yyfwMc;
    }

    public void setYyfwMc(String yyfwMc) {
        this.yyfwMc = yyfwMc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDlbz() {
        return dlbz;
    }

    public void setDlbz(String dlbz) {
        this.dlbz = dlbz;
    }
}
