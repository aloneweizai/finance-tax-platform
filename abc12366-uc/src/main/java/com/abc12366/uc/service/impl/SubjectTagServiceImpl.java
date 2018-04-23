package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.SubjectTagMapper;
import com.abc12366.uc.mapper.db2.SubjectTagRoMapper;
import com.abc12366.uc.mapper.db2.TagRoMapper;
import com.abc12366.uc.model.SubjectTag;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.service.SubjectTagService;
import com.abc12366.uc.web.SubjectTagController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-15
 * Time: 15:08
 */
@Service
public class SubjectTagServiceImpl implements SubjectTagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectTagController.class);

    @Autowired
    private SubjectTagMapper subjectTagMapper;

    @Autowired
    private SubjectTagRoMapper subjectTagRoMapper;

    @Autowired
    private TagRoMapper tagRoMapper;

    @Override
    public SubjectTagBO insert(String subject, String id, String tagId, HttpServletRequest request) {
        LOGGER.info("{}:{}:{}", subject, id, tagId);
        String userId = (String) request.getAttribute(Constant.ADMIN_ID);
        if (StringUtils.isEmpty(userId)) {
            throw new ServiceException(4130);
        }
        if (isExist(tagId, id)) {
            LOGGER.warn("{}:{}", tagId, id);
            throw new ServiceException(4612);
        }
        if (ExistEx(tagId, id) != null) {
            LOGGER.warn("{}:{}", tagId, id);
            throw new ServiceException(9999,"同类标签不可重复");
        }
        //判断被打标签对象和标签类型是否匹配
        if (!isObjectTagMatch(subject, tagId)) {
            throw new ServiceException(4627);
        }
        SubjectTag subjectTag = new SubjectTag();
        subjectTag.setId(Utils.uuid());
        subjectTag.setUserId(userId);
        subjectTag.setSubjectId(id);
        subjectTag.setTagId(tagId);
        subjectTag.setType(subject);
        subjectTag.setCreateTime(new Date());
        int result = subjectTagMapper.insert(subjectTag);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：" + subjectTag);
            throw new ServiceException(4101);
        }
        SubjectTagBO subjectTagBO = new SubjectTagBO();
        BeanUtils.copyProperties(subjectTag, subjectTagBO);
        return subjectTagBO;
    }

    @Transactional("db1TxManager")
    @Override
    public List<SubjectTagBO> batchInsert(String subject, String id, TagList tagList, HttpServletRequest request) {
        LOGGER.info("{}:{}:{}", subject, id, tagList);
        List<SubjectTagBO> subjectTagBOList = new ArrayList<>();
        SubjectTag subjectTag = new SubjectTag();
        subjectTag.setUserId((String) request.getAttribute(Constant.USER_ID));
        subjectTag.setSubjectId(id);
        subjectTag.setType(subject);
        subjectTag.setCreateTime(new Date());
        for (int i = 0; i < tagList.getTagIdList().size(); i++) {
            TagId tagId = tagList.getTagIdList().get(i);
            subjectTag.setId(Utils.uuid());
            subjectTag.setTagId(tagId.getTagId());
            int result = subjectTagMapper.insert(subjectTag);
            if (result != 1) {
                LOGGER.warn("新增失败，参数：" + subjectTag);
                throw new ServiceException(4101);
            }
            SubjectTagBO subjectTagBO = new SubjectTagBO();
            BeanUtils.copyProperties(subjectTag, subjectTagBO);
            subjectTagBOList.add(subjectTagBO);
        }
        return subjectTagBOList;
    }

    @Override
    public List<SubjectTagBO> selectList(String subject, String id) {
        LOGGER.info("{}:{}", subject, id);
        Map<String, String> map = new HashMap<>();
        if (subject != null && "".equals(subject)) {
            subject = null;
        }
        if (id != null && "".equals(id)) {
            id = null;
        }
        map.put("subject", subject);
        map.put("id", id);
        return subjectTagRoMapper.selectList(map);
    }

    @Override
    public int delete(String subject, String id, String tagId) {
        LOGGER.info("{}:{}:{}", subject, id, tagId);
        Map<String, String> map = new HashMap<>();
        if (subject != null && "".equals(subject)) {
            subject = null;
        }
        if (id != null && "".equals(id)) {
            id = null;
        }
        if (tagId != null && "".equals(tagId)) {
            tagId = null;
        }
        map.put("subject", subject);
        map.put("id", id);
        map.put("tagId", tagId);
        int result = subjectTagMapper.delete(map);
        if (result < 1) {
            LOGGER.warn("删除失败，参数：" + id);
            throw new ServiceException(4103);
        }
        return result;
    }

    @Override
    public int deleteByTagId(String id) {
        LOGGER.info("{}:{}:{}", id);
        return subjectTagMapper.deleteByTagId(id);
    }

    @Transactional("db1TxManager")
    @Override
    public List<SubjectTagBO> batchUserInsert(String subject, String tagId, String subjectIds, HttpServletRequest
            request) {
        LOGGER.info("{}:{}:{}:{}", subject, tagId, subjectIds, request);
        List<SubjectTagBO> subjectTagBOList = new ArrayList<>();
        SubjectTag subjectTag = new SubjectTag();
        subjectTag.setUserId((String) request.getAttribute(Constant.USER_ID));
        subjectTag.setTagId(tagId);
        //subjectTag.setSubjectId(id);
        //subjectTag.setType(subject);
        subjectTag.setCreateTime(new Date());
        List<String> subjectIdList = stringToList(",", subjectIds);
        for (int i = 0; i < subjectIdList.size(); i++) {
            subjectTag.setId(Utils.uuid());
            subjectTag.setSubjectId(subjectIdList.get(i));
            subjectTag.setType(subject);
            int result = subjectTagMapper.insert(subjectTag);
            if (result != 1) {
                LOGGER.warn("新增失败，参数：" + subjectTag);
                throw new ServiceException(4101);
            }
            SubjectTagBO subjectTagBO = new SubjectTagBO();
            BeanUtils.copyProperties(subjectTag, subjectTagBO);
            subjectTagBOList.add(subjectTagBO);
        }
        return subjectTagBOList;
    }

    @Transactional("db1TxManager")
    @Override
    public List<SubjectTagBO> batchInsert2(String subject, BatchTagInsertBO batchTagInsertBO, HttpServletRequest
            request) {
        LOGGER.info("{}:{}:{}", subject, batchTagInsertBO, request);
        List<String> tagIdList = stringToList(",", batchTagInsertBO.getTagIds());
        List<String> subjectIdList = stringToList(",", batchTagInsertBO.getSubjectIds());
        for (int i = 0; i < tagIdList.size(); i++) {
            for (int j = 0; j < subjectIdList.size(); j++) {
                //判断是否标签标记已存在,不存在才允许打标签
                String tagId = tagIdList.get(i);
                SubjectTagBO obj = ExistEx(tagId, subjectIdList.get(j));
                //存在同类标签
                if (obj != null) {
                    String id = obj.getId();
                    if (id == null || id.isEmpty()) {
                        LOGGER.warn("batchInsert2 参数：" + id);
                        throw new ServiceException(4101);
                    }
                    obj.setTagId(tagId);
                    int returnI = subjectTagMapper.update(obj );

                } else{
                        insert(subject, subjectIdList.get(j), tagIdList.get(i), request);

                }
            }
        }
        return selectListByTagIdsAndSubjectIds(batchTagInsertBO.getTagIds(), batchTagInsertBO.getSubjectIds());
    }

    private List<SubjectTagBO> selectListByTagIdsAndSubjectIds(String tagIds, String subjectIds) {
        LOGGER.info("{}:{}", tagIds, subjectIds);
        Map<String, String> map = new HashMap<>();
        map.put("tagIds", tagIds);
        map.put("subjectIds", subjectIds);
        return subjectTagRoMapper.selectListByTagIdsAndSubjectIds(map);
    }

    /**
     *
     * 查找是否有同类标签，没有返回null
     */
    private SubjectTagBO ExistEx(String tagId, String subjectId) {
        Map<String, String> map = new HashMap<>();
        //map.put("tagId", tagId);
        map.put("subjectId", subjectId);
        List<SubjectTagBO> objs = subjectTagRoMapper.getSubjectIdsCategorys(map);


        TagBO tagBO= tagRoMapper.selectOne(tagId);
        if(tagBO == null)return null;
        //循环判断此标签的类别  是否重复
        for (SubjectTagBO obj:objs) {
            if(tagBO.getCategory().equals(obj.getCategory())){
                return obj;

            }
        }
        return null;

    }

    public boolean isExist(String tagId, String subjectId) {
        if (StringUtils.isEmpty(tagId) || StringUtils.isEmpty(subjectId)) {
            throw new ServiceException(4613);
        }
        Map<String, String> map = new HashMap<>();
        map.put("tagId", tagId);
        map.put("subjectId", subjectId);
        List<SubjectTagBO> selectExistList = subjectTagRoMapper.selectExist(map);
        if (selectExistList == null || selectExistList.size() == 0) {
            return false;
        }
        return true;
    }

    public List stringToList(String sliptor, String string) {
        return Arrays.asList(string.split(sliptor));
    }

    public boolean isObjectTagMatch(String subject, String tagId) {
        TagBO tagBO = tagRoMapper.selectOne(tagId);
        if (tagBO == null) {
            throw new ServiceException(4628);
        }
        if (subject.trim().toUpperCase().equals(tagBO.getType().toUpperCase())) {
            return true;
        }
        return false;
    }
}
