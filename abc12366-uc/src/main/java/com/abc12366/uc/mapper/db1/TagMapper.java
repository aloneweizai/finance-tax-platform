package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.Tag;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-15
 * Time: 11:02
 */
public interface TagMapper {

    int insert(Tag tag);

    int update(Tag tag);

    int delete(String id);

    int enableOrDisable(Tag tag);
}
