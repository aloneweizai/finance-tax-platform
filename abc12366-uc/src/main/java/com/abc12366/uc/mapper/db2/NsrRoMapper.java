package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.Nsr;
import com.abc12366.uc.model.bo.NsrSelectBO;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-22 10:15 AM
 * @since 1.0.0
 */
public interface NsrRoMapper {
    Nsr selectOne(String id);

    List<Nsr> selectList(NsrSelectBO nsrSelectBO);

}

