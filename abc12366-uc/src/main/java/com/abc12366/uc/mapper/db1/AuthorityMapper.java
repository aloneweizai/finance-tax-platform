package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.Authority;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-03-28 9:21 AM
 * @since 1.0.0
 */
public interface AuthorityMapper {

    int insert(Authority authority);

    List<String> selectByUserId(String id);
}
