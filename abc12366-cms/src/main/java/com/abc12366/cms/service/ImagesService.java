package com.abc12366.cms.service;


import com.abc12366.cms.model.questionnaire.Images;

import java.util.List;

/**
 * 题目管理接口类
 *
 * @author lizhongwei
 * @create 2017-7-1
 * @since 1.0.0
 */
public interface ImagesService {

    List<Images> selectList(Images images);


    Images selectOne(String id);

    Images insert(Images images);

    Images update(Images images, String id);

    void delete(String id);
}
