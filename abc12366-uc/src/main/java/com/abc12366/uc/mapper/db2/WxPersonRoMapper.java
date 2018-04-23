package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.weixin.bo.person.WxPerson;

import java.util.List;

public interface WxPersonRoMapper {

    long countPersonNum(WxPerson wxPerson);

    List<WxPerson> selectList(WxPerson person);

    WxPerson selectOne(String openid);

}
