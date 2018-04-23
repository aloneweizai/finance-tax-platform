package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.TagMapper;
import com.abc12366.uc.mapper.db2.TagRoMapper;
import com.abc12366.uc.model.Tag;
import com.abc12366.uc.model.bo.TagBO;
import com.abc12366.uc.model.bo.TagInsertBO;
import com.abc12366.uc.model.bo.TagSelectParamBO;
import com.abc12366.uc.model.bo.TagUpdateBO;
import com.abc12366.uc.service.SubjectTagService;
import com.abc12366.uc.service.TagService;
import com.abc12366.uc.web.SubjectTagController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-15
 * Time: 15:23
 */
@Service
public class TagServiceImpl implements TagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectTagController.class);

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagRoMapper tagRoMapper;

    @Autowired
    private SubjectTagService subjectTagService;


    @Override
    public List<TagBO> selectList(TagSelectParamBO tagSelectParamBO) {
        LOGGER.info("{}", tagSelectParamBO);
        return tagRoMapper.selectList(tagSelectParamBO);
    }

    @Override
    public TagBO insert(TagInsertBO tagInsertBO) {
        LOGGER.info("{}", tagInsertBO);
        //标签名称唯一性校验
        List<TagBO> tagBOList = tagRoMapper.selectList(null);
        for (TagBO tagBO : tagBOList) {
            if (tagBO.getTagName().equals(tagInsertBO.getTagName())) {
                LOGGER.warn("新增失败，参数是：{}", tagInsertBO);
                throw new ServiceException(4604);
            }
        }
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagInsertBO, tag);
        Date date = new Date();
        tag.setId(Utils.uuid());
        tag.setCreateTime(date);
        tag.setLastUpdate(date);
        int result = tagMapper.insert(tag);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：" + tag);
            throw new ServiceException(4101);
        }
        TagBO tagBO = new TagBO();
        BeanUtils.copyProperties(tag, tagBO);
        return tagBO;
    }

    @Override
    public TagBO selectOne(String id) {
        LOGGER.info("{}", id);
        return tagRoMapper.selectOne(id);
    }

    @Override
    public TagBO update(TagUpdateBO tagUpdateBO, String id) {
        LOGGER.info("{}:{}", tagUpdateBO, id);
        //标签名称唯一性校验
        if (tagUpdateBO.getTagName() != null) {
            List<TagBO> tagBOList = tagRoMapper.selectList(null);
            //这条数据本身不做校验
            for (int i = 0; i < tagBOList.size(); i++) {
                if ((tagBOList.get(i)).getId().equals(id)) {
                    tagBOList.remove(i);
                }
            }
            for (TagBO tagBO : tagBOList) {
                if (tagBO.getTagName().equals(tagUpdateBO.getTagName())) {
                    LOGGER.warn("修改失败，参数是：{}", tagUpdateBO);
                    throw new ServiceException(4604);
                }
            }
        }

        Tag tag = new Tag();
        BeanUtils.copyProperties(tagUpdateBO, tag);
        Date date = new Date();
        tag.setId(id);
        tag.setLastUpdate(date);
        int result = tagMapper.update(tag);
        if (result != 1) {
            LOGGER.warn("修改失败，参数：" + tag);
            throw new ServiceException(4102);
        }
        TagBO tagBO = new TagBO();
        BeanUtils.copyProperties(tag, tagBO);
        return tagBO;
    }

    @Transactional("db1TxManager")
    @Override
    public int delete(String id) {
        LOGGER.info("{}", id);
        int result = tagMapper.delete(id);
        if (result < 1) {
            LOGGER.warn("删除失败，参数：" + id);
            throw new ServiceException(4103);
        }
        subjectTagService.deleteByTagId(id);
        return 1;
    }

    @Override
    public List<TagBO> selectListByUserId(String userId) {
        LOGGER.info("{}", userId);
        return tagRoMapper.selectListByUserId(userId);
    }

    @Override
    public void enableOrDisable(String id, String status) {
        LOGGER.info("{}:{}", id, status);
        if ((!status.equals("true")) && (!status.equals("false"))) {
            throw new ServiceException(4614);
        }
        boolean modifyStatus = status.equals("true");
        Tag tag = new Tag();
        tag.setId(id);
        tag.setStatus(modifyStatus);
        tag.setLastUpdate(new Date());
        int result = tagMapper.enableOrDisable(tag);
        if (result < 1) {
            if (modifyStatus) {
                throw new ServiceException(4625);
            }
            throw new ServiceException(4626);
        }
    }

    @Override
    public List<String> selectUserIdsByTagIds(Map map) {
        //解析多标签名称参数
        String tagId = "tagId";
        List tagIdList = new ArrayList<>();
        if (!StringUtils.isEmpty(map.get(tagId))) {
            tagIdList = analysisTagId((String) map.get(tagId), ",");
        }
        map.put(tagId, tagIdList);
        map.put("tagIdCount", (tagIdList == null) ? 0 : tagIdList.size());
        return tagRoMapper.selectUserIdsByTagIds(map);
    }

    /**
     * 逗号分隔的标签ID转为List
     *
     * @param tagId 带逗号分隔的标签ID
     * @param split 分隔符
     * @return ID列表
     */
    private List analysisTagId(String tagId, String split) {
        String[] tags = tagId.trim().split(split);
        List list = Arrays.asList(tags);
        //去除空的元素
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.isEmpty(list.get(i))) {
                list.remove(i);
            }
        }
        return list;
    }
}
