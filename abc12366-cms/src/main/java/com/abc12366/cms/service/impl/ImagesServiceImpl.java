package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.ImagesMapper;
import com.abc12366.cms.mapper.db2.ImagesRoMapper;
import com.abc12366.cms.model.questionnaire.Images;
import com.abc12366.cms.service.ImagesService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lizhongwei
 * @create 2017-07-01 4:21 PM
 * @since 1.0.0
 */
@Service
public class ImagesServiceImpl implements ImagesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImagesServiceImpl.class);
    @Autowired
    private ImagesRoMapper imagesRoMapper;

    @Autowired
    private ImagesMapper imagesMapper;


    @Override
    public List<Images> selectList(Images images) {
        return imagesRoMapper.selectList(images);
    }

    @Override
    public Images selectOne(String id) {
        return imagesRoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Images insert(Images images) {
        images.setId(Utils.uuid());
        int insert = imagesMapper.insert(images);
        if (insert != 1) {
            if (insert != 1) {
                LOGGER.info("{新增背景图片失败}", images);
                throw new ServiceException(4420);
            }
        }
        return images;
    }

    @Override
    public Images update(Images images, String id) {
        images.setId(id);
        int update = imagesMapper.update(images);
        if (update != 1) {
            if (update != 1) {
                LOGGER.info("{新增背景图片失败}", images);
                throw new ServiceException(4421);
            }
        }
        return imagesRoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(String id) {
        int del = imagesMapper.deleteByPrimaryKey(id);
        if (del != 1) {
            if (del != 1) {
                LOGGER.info("{删除背景图片失败}", id);
                throw new ServiceException(4421);
            }
        }
    }
}
