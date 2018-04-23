package com.abc12366.uc.wsbssoa.response;

import com.abc12366.uc.wsbssoa.dto.AuthorizationDto;

import java.util.List;

/**
 * 登录成功返回对象
 * Created by zhouzhi on 2015-12-05.
 */
public class HngsNsrLoginResponse extends BaseResponse implements java.io.Serializable {
    private String nsrmc;   //纳税人名称
    private String userName;    //办税人名称
    private String djxh;
    private List<AuthorizationDto> menuList;    //菜单权限

    //登录失败时返回的一些字段
    private String nsrsbh;
    private String roleId;
    private int errorCount;     //连续登录失败次数

    private int logCount;   //登录成功次数
    private String csmmbz;  //是否修改了初始密码，Y为未修改过，N为已修改过

    private String gxruuid; //干系人uuid
    private String zgswjDm; //主管税务局代码

    private String zgswskfjDm;     //根据该值判断是否做了管户分配，   该值为空的，说明纳税人未作管户分配
    private String zgswjmc;//税务机关名称

    public HngsNsrLoginResponse() {
        super();
    }

    public HngsNsrLoginResponse(String code, String msg) {
        super(code, msg);
    }

    public String getZgswjmc() {
        return zgswjmc;
    }

    public void setZgswjmc(String zgswjmc) {
        this.zgswjmc = zgswjmc;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDjxh() {
        return djxh;
    }

    public void setDjxh(String djxh) {
        this.djxh = djxh;
    }


    public List<AuthorizationDto> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<AuthorizationDto> menuList) {
        this.menuList = menuList;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getLogCount() {
        return logCount;
    }

    public void setLogCount(int logCount) {
        this.logCount = logCount;
    }

    public String getCsmmbz() {
        return csmmbz;
    }

    public void setCsmmbz(String csmmbz) {
        this.csmmbz = csmmbz;
    }

    public String getGxruuid() {
        return gxruuid;
    }

    public void setGxruuid(String gxruuid) {
        this.gxruuid = gxruuid;
    }

    public String getZgswjDm() {
        return zgswjDm;
    }

    public void setZgswjDm(String zgswjDm) {
        this.zgswjDm = zgswjDm;
    }

    public String getZgswskfjDm() {
        return zgswskfjDm;
    }

    public void setZgswskfjDm(String zgswskfjDm) {
        this.zgswskfjDm = zgswskfjDm;
    }
}
