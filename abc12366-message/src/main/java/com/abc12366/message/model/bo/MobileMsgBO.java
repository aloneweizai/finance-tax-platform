package com.abc12366.message.model.bo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 16:10
 */
public class MobileMsgBO {

    @NotNull(message = "手机号码phone不能为空")
    @Pattern(regexp = "^\\d{11}$" ,message = "手机号码phone只能为11位")
    @Size(min = 11, max = 11)
    private String phone;

    @NotNull(message = "短信内容模板ID不能为空")
    private String templateId;

    @NotNull(message = "短信内容不能为空")
    private List<SendMsgVarBO> vars;

    public MobileMsgBO() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<SendMsgVarBO> getVars() {
        return vars;
    }

    public void setVars(List<SendMsgVarBO> vars) {
        this.vars = vars;
    }

    @Override
    public String toString() {
        return "MobileMsgBO{" +
                "phone='" + phone + '\'' +
                ", templateId='" + templateId + '\'' +
                ", vars=" + vars +
                '}';
    }
}
