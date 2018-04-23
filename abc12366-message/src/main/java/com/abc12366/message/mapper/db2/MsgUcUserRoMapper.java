package com.abc12366.message.mapper.db2;

import com.abc12366.message.model.PhoneExist;

/**
 * msg_uc_user视图查询Mapper类
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-23
 * Time: 16:15
 */
public interface MsgUcUserRoMapper {

    PhoneExist selectPhoneExist(String phone);
}
