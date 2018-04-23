package com.abc12366.cms.service;


import com.abc12366.cms.model.bo.ChannelBo;
import com.abc12366.cms.model.bo.ChannelExtBo;
import com.abc12366.cms.model.bo.ChannelSaveBo;
import com.abc12366.cms.model.bo.ModelItemBo;

import java.util.List;
import java.util.Map;

public interface ChannelService {

    List<ChannelBo> selectList();

    List<ChannelBo> selectLists(Map<String, Object> map);

    List<ChannelBo> selectListByparentId(String parentId);

    List<ChannelExtBo> selectListBytplChannel(String tplChannel);

    List<ModelItemBo> selectModeList(Map<String, Object> map);

    ChannelSaveBo save(ChannelSaveBo channelSaveBo);

    ChannelSaveBo selectChannel(String channelId);

    ChannelSaveBo update(ChannelSaveBo channelSaveBo);

    ChannelBo updateChannelByparentId(ChannelBo channelBo);

    String delete(String channelId);
}
