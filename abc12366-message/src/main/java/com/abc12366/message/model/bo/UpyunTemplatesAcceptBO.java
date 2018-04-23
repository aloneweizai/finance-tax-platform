package com.abc12366.message.model.bo;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-08
 * Time: 16:52
 */
public class UpyunTemplatesAcceptBO {
    private List<UpyunTemplateAcceptBaseBO> templates;

    public List<UpyunTemplateAcceptBaseBO> getTemplates() {
        return templates;
    }

    public void setTemplates(List<UpyunTemplateAcceptBaseBO> templates) {
        this.templates = templates;
    }

    @Override
    public String toString() {
        return "UpyunTemplatesAcceptBO{" +
                "templates=" + templates +
                '}';
    }
}
