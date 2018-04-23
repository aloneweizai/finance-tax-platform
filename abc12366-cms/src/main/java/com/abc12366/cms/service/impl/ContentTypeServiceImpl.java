package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.ContentTypeMapper;
import com.abc12366.cms.mapper.db2.ContentTypeRoMapper;
import com.abc12366.cms.model.ContentType;
import com.abc12366.cms.service.ContentTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容类型管理模块
 *
 * @author xieyanmao
 * @create 2017-5-3
 * @since 1.0.0
 */
@Service
public class ContentTypeServiceImpl implements ContentTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentTypeServiceImpl.class);

    @Autowired
    private ContentTypeMapper contentTypeMapper;

    @Autowired
    private ContentTypeRoMapper contentTypeRoMapper;


    @Override
    public List<ContentType> selectList() {
        //查询内容类型列表
        List<ContentType> contentTypeList = contentTypeRoMapper.selectList();
        return contentTypeList;
    }

    @Override
    public String save(ContentType contentType) {
        //新增内容类型
        contentTypeMapper.insert(contentType);

        return "22222";
    }

    @Override
    public ContentType selectContentType(String typeId) {
        //查询内容类型
        ContentType contentType = contentTypeRoMapper.selectByPrimaryKey(typeId);
        return contentType;
    }

    @Override
    public String update(ContentType contentType) {
        //更新内容类型
        contentTypeMapper.updateByPrimaryKeySelective(contentType);
        return "333333";
    }

    @Override
    public String delete(String typeId) {
        //删除内容类型
        contentTypeMapper.deleteByPrimaryKey(typeId);
        return null;
    }
}
