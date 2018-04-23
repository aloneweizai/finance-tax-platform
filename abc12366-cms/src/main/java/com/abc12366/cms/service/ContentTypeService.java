package com.abc12366.cms.service;

import com.abc12366.cms.model.ContentType;

import java.util.List;

/**
 * 内容类型管理模块
 *
 * @author xieyanmao
 * @create 2017-5-3
 * @since 1.0.0
 */
public interface ContentTypeService {
    List<ContentType> selectList();

    String save(ContentType contentType);

    ContentType selectContentType(String typeId);

    String update(ContentType contentType);

    String delete(String typeId);
}
