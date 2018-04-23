package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.weixin.bo.qrcode.Qrcode;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-29 11:06 AM
 * @since 1.0.0
 */
public interface QrcodeRoMapper {

    List<Qrcode> selectList(Qrcode qrcode);

    Qrcode selectOne(String id);
}
