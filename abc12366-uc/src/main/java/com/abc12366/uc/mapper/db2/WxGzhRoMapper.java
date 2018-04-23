package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.weixin.bo.gzh.GzhInfo;

import java.util.List;

public interface WxGzhRoMapper {

    List<GzhInfo> selectList(GzhInfo gzhInfo);

    GzhInfo selectOne(String id);

	String selectUserToken(String appid);

}
