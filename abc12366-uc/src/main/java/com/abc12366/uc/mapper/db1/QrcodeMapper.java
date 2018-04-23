package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.weixin.bo.qrcode.Qrcode;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-29 11:06 AM
 * @since 1.0.0
 */
public interface QrcodeMapper {
    void insert(Qrcode qrcode);

    void update(Qrcode qrcode);

    void delete(String id);
}
