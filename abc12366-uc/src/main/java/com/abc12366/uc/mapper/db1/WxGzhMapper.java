package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.weixin.bo.gzh.GzhInfo;

public interface WxGzhMapper {

    void insert(GzhInfo gzhInfo);

    int update(GzhInfo gzhInfo);

    void delete(String id);

	void updateUserToken(GzhInfo gzhInfo);

	void updatejsapiTicket(GzhInfo gzhInfo);

}
