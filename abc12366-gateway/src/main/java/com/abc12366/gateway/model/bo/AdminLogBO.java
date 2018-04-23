package com.abc12366.gateway.model.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 操作员日志
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-27 2:08 PM
 * @since 1.0.0
 */
public class AdminLogBO {

    @NotEmpty
    @Length(max = 255)
    private String businessUri;
    // 业务名称
    @NotEmpty
    @Length(max = 50)
    private String businessName;
    // 业务数据
    @Length(max = 3000)
    private String businessData;
    // 操作类型：POST（新增）、PUT（修改）、GET（查询）、DELETE（删除）
    @NotEmpty
    @Length(max = 10)
    private String method;
    // 备注
    @Length(max = 50)
    private String remark;

    @NotEmpty
    @Length(max = 64)
    private String adminId;

    public String getBusinessUri() {
        return businessUri;
    }

    public void setBusinessUri(String businessUri) {
        this.businessUri = businessUri;
    }

    public String getBusinessData() {
        return businessData;
    }

    public void setBusinessData(String businessData) {
        this.businessData = businessData;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "AdminLogBO{" +
                "businessUri='" + businessUri + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessData='" + businessData + '\'' +
                ", method='" + method + '\'' +
                ", remark='" + remark + '\'' +
                ", adminId='" + adminId + '\'' +
                '}';
    }
}
