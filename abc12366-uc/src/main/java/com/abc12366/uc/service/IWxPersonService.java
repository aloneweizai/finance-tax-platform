package com.abc12366.uc.service;

import com.abc12366.uc.model.weixin.bo.person.WxPerson;

import java.util.List;

public interface IWxPersonService {
    boolean startUsersynchro();

    List<WxPerson> selectList(WxPerson person, int page, int size);

    WxPerson selectOne(String openid);

    WxPerson synchroOne(String openid);

	WxPerson getOneFromWx(String openid);
}
