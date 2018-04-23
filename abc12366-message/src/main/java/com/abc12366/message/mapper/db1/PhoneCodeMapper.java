package com.abc12366.message.mapper.db1;

import com.abc12366.message.model.PhoneCode;

import org.apache.ibatis.annotations.Param;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-20
 * Time: 17:16
 */
public interface PhoneCodeMapper {
    int insert(PhoneCode phoneCode);

    int delete(@Param("phone") String phone);

    /**
     * 根据手机号、验证码、过期时间查询未过期的验证码
     */
    PhoneCode selectOne(PhoneCode data);
}
