package com.abc12366.cms.model.bo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


/**
 * CMS内容表
 * add by xieyanmao on 2017-4-27
 **/
@SuppressWarnings("serial")
public class ChannelSaveBo implements Serializable {
    @Valid
    @NotNull
    private ChannelBo channel;//栏目
    @Valid
    @NotNull
    private ChannelExtBo channelExt;//栏目扩展
    private List<ChannelAttrBo> channelAttrList;//栏目扩展属性
    private List<ChnlGroupViewBo> groupList;//用户组

    /**
     * 0 新增 1修改 2审核 3退回 4移动 5生成静态页 6删除到回收站 7归档 8出档 9推送共享
     **/
    private Integer operateType;

    public ChannelBo getChannel() {
        return channel;
    }

    public void setChannel(ChannelBo channel) {
        this.channel = channel;
    }

    public ChannelExtBo getChannelExt() {
        return channelExt;
    }

    public void setChannelExt(ChannelExtBo channelExt) {
        this.channelExt = channelExt;
    }

    public List<ChannelAttrBo> getChannelAttrList() {
        return channelAttrList;
    }

    public void setChannelAttrList(List<ChannelAttrBo> channelAttrList) {
        this.channelAttrList = channelAttrList;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public List<ChnlGroupViewBo> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<ChnlGroupViewBo> groupList) {
        this.groupList = groupList;
    }
}
