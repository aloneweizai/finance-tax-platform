package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CurriculumLabelMapper;
import com.abc12366.bangbang.mapper.db1.KnowledgeTagMapper;
import com.abc12366.bangbang.mapper.db1.KnowledgeTagRelMapper;
import com.abc12366.bangbang.mapper.db1.QuestionClassifyTagMapper;
import com.abc12366.bangbang.model.KnowledgeTag;
import com.abc12366.bangbang.service.KnowledgeTagService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/8/4 16:08
 */
@Service
public class KnowledgeTagServiceImpl implements KnowledgeTagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeTagServiceImpl.class);

    @Autowired
    private KnowledgeTagMapper knowledgeTagMapper;

    @Autowired
    private KnowledgeTagRelMapper knowledgeTagRelMapper;

    @Autowired
    private CurriculumLabelMapper curriculumLabelMapper;

    @Autowired
    private QuestionClassifyTagMapper questionClassifyTagMapper;

    @Override
    public List<String> selectHotTag(Integer num) {
        return knowledgeTagMapper.selectHotTag(num);
    }

    @Override
    public List<KnowledgeTag> selectList(String keywords, Boolean status, String tagType) {
        return knowledgeTagMapper.selectList(keywords, status, tagType);
    }

    @Override
    public List<KnowledgeTag> selectRelatedTags(String knowledgeId) {
        return knowledgeTagMapper.selectRelatedTags(knowledgeId);
    }

    @Override
    public KnowledgeTag selectByPk(String id) {
        return knowledgeTagMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KnowledgeTag> addBatch(List<KnowledgeTag> knowledgeTags) {
        for (KnowledgeTag tag: knowledgeTags){
            KnowledgeTag tag1 = knowledgeTagMapper.selectByName(tag.getName());
            if(tag1 != null && tag1.getStatus()){
                throw new ServiceException(4520);
            }
            if(tag1 != null && !tag1.getStatus()){
                throw new ServiceException(4521);
            }
            tag.setId(Utils.uuid());
            tag.setCreateUser(Utils.getAdminId());
            tag.setUpdateUser(Utils.getAdminId());
        }
        knowledgeTagMapper.insertBatch(knowledgeTags);
        return knowledgeTags;
    }

    @Transactional("db1TxManager")
    @Override
    public List<KnowledgeTag> addBatchByOtherChannel(List<KnowledgeTag> knowledgeTags) {
        List<KnowledgeTag> updateList = new ArrayList<>();
        List<KnowledgeTag> insertList = new ArrayList<>();

        for (KnowledgeTag tag: knowledgeTags){
            KnowledgeTag tag1 = knowledgeTagMapper.selectByName(tag.getName());
            if(tag1 != null && tag1.getTagType().indexOf(tag.getTagType()) > -1 && tag1.getStatus()){
                throw new ServiceException(4520);
            }
            if(tag1 != null && tag1.getTagType().indexOf(tag.getTagType()) > -1 && !tag1.getStatus()){
//                throw new ServiceException(4521);
                tag1.setStatus(Boolean.TRUE);
            }
            tag.setUpdateUser(Utils.getAdminId());
            if(tag1 == null){
                tag.setId(Utils.uuid());
                tag.setCreateUser(Utils.getAdminId());
                insertList.add(tag);
            }else{
                tag.setId(tag1.getId());
                updateList.add(tag);
            }
        }
        if(!insertList.isEmpty()){
            knowledgeTagMapper.insertBatch(insertList);
        }
        if(!updateList.isEmpty()){
            knowledgeTagMapper.batchUpdateTypeByName(updateList);
        }
        return knowledgeTags;
    }

    @Override
    public KnowledgeTag add(KnowledgeTag knowledgeTag) {
        KnowledgeTag tag = knowledgeTagMapper.selectByName(knowledgeTag.getName());
        if(tag != null && tag.getTagType().indexOf(knowledgeTag.getTagType()) > -1 && tag.getStatus()){
            throw new ServiceException(4520);
        }
        if(tag != null && tag.getTagType().indexOf(knowledgeTag.getTagType()) > -1 && !tag.getStatus()){
            throw new ServiceException(4521);
        }
        if(tag != null){
            StringBuilder tagTypes1 = new StringBuilder(knowledgeTag.getTagType());
            String tagTypes = tag.getTagType();
            String[] arr = tagTypes.split(";");
            for (String tagType: arr){
                if(tagTypes1.indexOf(tagType) == -1){
                    tagTypes1.append(";"+tagType);
                }
            }
            tag.setTagType(tagTypes1.toString());
            tag.setUpdateUser(Utils.getAdminId());
            knowledgeTagMapper.updateByPrimaryKeySelective(tag);
            return tag;
        }
        try {
            knowledgeTag.setId(Utils.uuid());
            knowledgeTag.setCreateUser(Utils.getAdminId());
            knowledgeTag.setUpdateUser(Utils.getAdminId());
            knowledgeTagMapper.insert(knowledgeTag);
            return knowledgeTag;
        }catch (Exception e){
            LOGGER.error("KnowledgeTagServiceImpl.add()", e);
            throw new ServiceException(4514);
        }
    }

    @Override
    public KnowledgeTag modify(KnowledgeTag knowledgeTag) {
        KnowledgeTag tag = knowledgeTagMapper.selectByName(knowledgeTag.getName());

        try {
            knowledgeTag.setUpdateUser(Utils.getAdminId());
            knowledgeTag.setUpdateTime(new Date());
            knowledgeTagMapper.updateByPrimaryKeySelective(knowledgeTag);
            return knowledgeTag;
        }catch (Exception e){
            LOGGER.error("KnowledgeTagServiceImpl.modify()", e);
            throw new ServiceException(4516);
        }
    }

    @Override
    public void modifyStatus(String id, Boolean status) {
        try {
            KnowledgeTag knowledgeTag = new KnowledgeTag();
            knowledgeTag.setId(id);
            knowledgeTag.setStatus(status);
            knowledgeTag.setUpdateUser(Utils.getAdminId());
            knowledgeTag.setUpdateTime(new Date());
            knowledgeTagMapper.updateByPrimaryKeySelective(knowledgeTag);
        }catch (Exception e){
            LOGGER.error("KnowledgeTagServiceImpl.modifyStatus()", e);
            throw new ServiceException(4517);
        }
    }

    @Transactional("db1TxManager")
    @Override
    public void delete(String id) {
        try {
            curriculumLabelMapper.deleteByLabelId(id);//删除课程关联标签
            knowledgeTagRelMapper.deleteByTagId(id);//删除知识库关联标签
            questionClassifyTagMapper.deleteByTagId(id);//删除帮帮关联标签

            knowledgeTagMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            LOGGER.error("KnowledgeTagServiceImpl.delete()", e);
            throw new ServiceException(4515);
        }
    }

    @Transactional("db1TxManager")
    @Override
    public void delete(List<String> ids) {
        try {
            curriculumLabelMapper.deleteByLabelIds(ids);
            knowledgeTagRelMapper.deleteByTagIds(ids);
            questionClassifyTagMapper.deleteByTagIds(ids);

            knowledgeTagMapper.deleteByPrimaryKeys(ids);
        }catch (Exception e){
            LOGGER.error("KnowledgeTagServiceImpl.delete(List<String> ids)", e);
            throw new ServiceException(4515);
        }
    }
}
