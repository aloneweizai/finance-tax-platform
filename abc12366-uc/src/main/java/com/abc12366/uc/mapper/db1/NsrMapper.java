package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.Nsr;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-22 10:15 AM
 * @since 1.0.0
 */
public interface NsrMapper {
    int insert(Nsr nsr);

    int delete(String id);

    int update(Nsr nsr);
}
