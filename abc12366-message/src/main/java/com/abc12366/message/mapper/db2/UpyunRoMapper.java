package com.abc12366.message.mapper.db2;

import com.abc12366.message.model.UpyunTemplate;

import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-08
 * Time: 16:01
 */
public interface UpyunRoMapper {
    List<UpyunTemplate> selectList(Map<String, String> map);
}
