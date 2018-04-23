package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.VipLevel;

/**
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-19 10:18 PM
 * @since 2.0.0
 */
public interface VipLevelMapper {
    int insert(VipLevel vipLevel);

    int update(VipLevel vipLevel);

    int delete(String id);

    int enableOrDisable(VipLevel vipLevel);
}
