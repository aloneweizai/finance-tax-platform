package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.*;
import com.abc12366.cms.mapper.db2.*;
import com.abc12366.cms.model.*;
import com.abc12366.cms.model.bo.*;
import com.abc12366.cms.service.ContentService;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 内容管理模块
 *
 * @author xieyanmao
 * @create 2017-04-27
 * @since 1.0.0
 */
@Service
public class ContentServiceImpl implements ContentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private ContentRoMapper contentRoMapper;

    @Autowired
    private ModelItemRoMapper modelItemRoMapper;

    @Autowired
    private ContentExtMapper contentExtMapper;

    @Autowired
    private ContentExtRoMapper contentExtRoMapper;

    @Autowired
    private ContentTxtMapper contentTxtMapper;

    @Autowired
    private ContentTxtRoMapper contentTxtRoMapper;

    @Autowired
    private ContentAttrMapper contentAttrMapper;

    @Autowired
    private ContentAttrRoMapper contentAttrRoMapper;

    @Autowired
    private ContentPictureMapper contentPictureMapper;

    @Autowired
    private ContentPictureRoMapper contentPictureRoMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private FileRoMapper fileRoMapper;

    @Autowired
    private ContentTopicMapper topicMapper;

    @Autowired
    private ContentTopicRoMapper topicRoMapper;

    @Autowired
    private TopicRoMapper tpRoMapper;

    @Autowired
    private ContentGroupViewMapper groupMapper;

    @Autowired
    private ContentGroupViewRoMapper groupRoMapper;

    @Autowired
    private ContentCountMapper contentCountMapper;

    @Autowired
    private ContenttagidMapper tagMapper;

    @Autowired
    private ContenttagidRoMapper tagRoMapper;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public List<ContentListBo> selectList(Map<String, Object> map) {
        //查询内容列表
        List<ContentListBo> contents = contentRoMapper.selectList(map);
        LOGGER.info("{}", contents);
        return contents;
    }

    @Override
    public List<ContentViewListBo> selectListByviews(Map<String, Object> map) {
        //查询内容列表按访问量
        List<ContentViewListBo> contents = contentRoMapper.selectListByviews(map);
        LOGGER.info("{}", contents);
        return contents;
    }

    @Override
    public List<ContentsListBo> selectListByContentType(Map<String, Object> map) {
        //查询内容列表按标签
        List<ContentsListBo> contents = contentRoMapper.selectListByContentType(map);
        LOGGER.info("{}", contents);
        return contents;
    }

    @Override
    public List<ContenttagidBo> selectContentType(Map<String, Object> map) {
        //查询标签
        List<ContenttagidBo> contents = contentRoMapper.selectContentType(map);
        LOGGER.info("{}", contents);
        return contents;
    }

    @Override
    public List<ContentsListBo> selectListByChannelId(Map<String, Object> map) {
        //查询内容列表
        List<ContentsListBo> contents = contentRoMapper.selectListByChannelId(map);
        LOGGER.info("{}", contents);
        return contents;
    }

    @Override
    public int selectCntByChannelId(Map<String, Object> map) {
        int cnt = contentRoMapper.selectCntByChannelId(map);
        return cnt;
    }

    @Override
    public List<ContentsListBo> selectListcszxw(Map<String, Object> map) {
        //查询内容列表
        List<ContentsListBo> contents = contentRoMapper.selectListcszxw(map);
        LOGGER.info("{}", contents);
        return contents;
    }

    @Override
    public List<ContentsListBo> selectListBytopicId(Map<String, Object> map) {
        //查询内容列表
        List<ContentsListBo> contents = contentRoMapper.selectListBytopicId(map);
        LOGGER.info("{}", contents);
        return contents;
    }

    @Override
    public List<ContentsListBo> selectListByTplContent(Map<String, Object> map) {
        //查询内容列表
        List<ContentsListBo> contents = contentRoMapper.selectListByTplContent(map);
        LOGGER.info("{}", contents);
        return contents;
    }

    @Override
    public List<ContentsListBo> selectListByTagName(Map<String, Object> map) {
        //根据标签名称查询内容列表
        List<ContentsListBo> contents = contentRoMapper.selectListByTagName(map);
        LOGGER.info("{}", contents);
        return contents;
    }

    @Override
    public List<ModelItemBo> selectModeList(Map<String, Object> map) {
        //查询模型项
        List<ModelItemBo> modelItemBos = modelItemRoMapper.selectList(map);
        LOGGER.info("{}", modelItemBos);
        return modelItemBos;
    }

    @Transactional("db1TxManager")
    @Override
    public ContentSaveBo save(ContentSaveBo contentSaveBo) {
        JSONObject jsonStu = JSONObject.fromObject(contentSaveBo);
        LOGGER.info("新增文章信息:{}", jsonStu.toString());
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //内容
        ContentBo contentBo = contentSaveBo.getContent();
//        contentBo.setStatus(2);//审核通过
        contentBo.setContentId(uuid);
        contentBo.setViewsDay(0);
        Content content = new Content();
        try {
            BeanUtils.copyProperties(contentBo, content);
            contentMapper.insert(content);
        } catch (Exception e) {
            LOGGER.error("新增文章信息异常：{}", e);
            throw new ServiceException(4250);
        }
        //内容扩展项
        ContentExtBo contentExtBo = contentSaveBo.getContentExt();
        contentExtBo.setContentId(uuid);
        if(contentExtBo !=null && contentExtBo.getReleaseDate()==null) {
            contentExtBo.setReleaseDate(new Date());
        }
        ContentExt contentExt = new ContentExt();
        try {
            BeanUtils.copyProperties(contentExtBo, contentExt);
            contentExt.setNeedRegenerate(0);
            contentExtMapper.insert(contentExt);
        } catch (Exception e) {
            LOGGER.error("新增文章信息异常：{}", e);
            throw new ServiceException(4250);
        }
        //内容文本
        ContentTxtBo contentTxtBo = contentSaveBo.getContentTxt();
        contentTxtBo.setContentId(uuid);
        ContentTxt contentTxt = new ContentTxt();
        try {
            if (contentTxtBo != null) {
                BeanUtils.copyProperties(contentTxtBo, contentTxt);
                contentTxtMapper.insert(contentTxt);
            }
        } catch (Exception e) {
            LOGGER.error("新增文章信息异常：{}", e);
            throw new ServiceException(4250);
        }
        //内容扩展属性
        List<ContentAttrBo> contentAttrList = contentSaveBo.getContentAttrList();
        //内容图片
        List<ContentPictureBo> contentPictureList = contentSaveBo.getContentPictureList();
        //内容附件
        List<FileBo> fileList = contentSaveBo.getFileList();

        if (contentAttrList != null) {
            for (ContentAttrBo contentAttrBo : contentAttrList) {
                contentAttrBo.setContentId(uuid);
                ContentAttr contentAttr = new ContentAttr();
                try {
                    BeanUtils.copyProperties(contentAttrBo, contentAttr);
                    contentAttrMapper.insert(contentAttr);
                } catch (Exception e) {
                    LOGGER.error("新增文章信息异常：{}", e);
                    throw new ServiceException(4250);
                }

            }
        }
        if (contentPictureList != null) {
            for (ContentPictureBo contentPictureBo : contentPictureList) {
                contentPictureBo.setContentId(uuid);
                ContentPicture contentPicture = new ContentPicture();
                try {
                    BeanUtils.copyProperties(contentPictureBo, contentPicture);
                    contentPictureMapper.insert(contentPicture);
                } catch (Exception e) {
                    LOGGER.error("新增文章信息异常：{}", e);
                    throw new ServiceException(4250);
                }

            }
        }
        if (fileList != null) {
            for (FileBo fileBo : fileList) {
                fileBo.setContentId(uuid);
                File file = new File();
                try {
                    BeanUtils.copyProperties(fileBo, file);
                    fileMapper.insert(file);
                } catch (Exception e) {
                    LOGGER.error("新增文章信息异常：{}", e);
                    throw new ServiceException(4250);
                }

            }
        }

        //用户组
        List<ContentGroupViewBo> groupList = contentSaveBo.getGroupList();
        if (groupList != null) {
            for (ContentGroupViewBo groupBo : groupList) {
                ContentGroupView group = new ContentGroupView();
                groupBo.setContentId(uuid);
                try {
                    BeanUtils.copyProperties(groupBo, group);
                    groupMapper.insert(group);
                } catch (Exception e) {
                    LOGGER.error("新增文章信息异常：{}", e);
                    throw new ServiceException(4250);
                }

            }
        }

        //专题组
        List<ContentTopicBo> topicList = contentSaveBo.getTopicList();
        if (topicList != null) {
            for (ContentTopicBo topicBo : topicList) {
                ContentTopic topic = new ContentTopic();
                topicBo.setContentId(uuid);
                try {
                    BeanUtils.copyProperties(topicBo, topic);
                    topicMapper.insert(topic);
                } catch (Exception e) {
                    LOGGER.error("新增文章信息异常：{}", e);
                    throw new ServiceException(4250);
                }

            }
        }

        //标签组
        List<ContenttagidBo> tagList = contentSaveBo.getTagList();
        if (tagList != null) {
            for (ContenttagidBo tagBo : tagList) {
                Contenttagid tag = new Contenttagid();
                tagBo.setContentId(uuid);
                try {
                    BeanUtils.copyProperties(tagBo, tag);
                    tagMapper.insert(tag);
                } catch (Exception e) {
                    LOGGER.error("新增文章信息异常：{}", e);
                    throw new ServiceException(4250);
                }

            }
        }

        //统计表
        ContentCount cons = new ContentCount();
        cons.setContentId(uuid);
        try {
            contentCountMapper.insert(cons);
        } catch (Exception e) {
            LOGGER.error("新增文章信息异常：{}", e);
            throw new ServiceException(4250);
        }

        return contentSaveBo;
    }

    @Override
    public ContentSaveBo selectContent(String contentId) {
        LOGGER.info("查询单个文章信息:{}", contentId);
        ContentSaveBo contentSaveBo = new ContentSaveBo();
        //内容
        Content content = contentRoMapper.selectByContentId(contentId);
        if (content == null) {
            //未查询到文章信息，请检查文章是否已被删除
            throw new ServiceException(4251);
        }

        //内容扩展项
        ContentExt contentExt = contentExtRoMapper.selectByContentId(contentId);
        //内容文本
        ContentTxt contentTxt = contentTxtRoMapper.selectByContentId(contentId);
        //内容扩展属性
        List<ContentAttr> contentAttrList = contentAttrRoMapper.selectContentAttrList(contentId);
        //内容图片
        List<ContentPicture> contentPictureList = contentPictureRoMapper.selectContentPictureList(contentId);
        //内容附件
        List<File> fileList = fileRoMapper.selectFileList(contentId);

        ContentBo contentBo = new ContentBo();
        try {
            BeanUtils.copyProperties(content, contentBo);
        } catch (Exception e) {
            LOGGER.error("查询单个文章信息异常：{}", e);
            throw new ServiceException(4252);
        }
        contentSaveBo.setContent(contentBo);

        ContentExtBo contentExtBo = new ContentExtBo();
        try {
            BeanUtils.copyProperties(contentExt, contentExtBo);
        } catch (Exception e) {
            LOGGER.error("查询单个文章信息异常：{}", e);
            throw new ServiceException(4252);
        }
        contentSaveBo.setContentExt(contentExtBo);

        ContentTxtBo contentTxtBo = new ContentTxtBo();
        try {
            if (contentTxt != null) {
                BeanUtils.copyProperties(contentTxt, contentTxtBo);
            }
        } catch (Exception e) {
            LOGGER.error("查询单个文章信息异常：{}", e);
            throw new ServiceException(4252);
        }
        contentSaveBo.setContentTxt(contentTxtBo);

        List<ContentAttrBo> contentAttrBoList = new ArrayList<ContentAttrBo>();
        for (ContentAttr contentAttr : contentAttrList) {
            ContentAttrBo contentAttrBo = new ContentAttrBo();
            try {
                BeanUtils.copyProperties(contentAttr, contentAttrBo);
                contentAttrBoList.add(contentAttrBo);
            } catch (Exception e) {
                LOGGER.error("查询单个文章信息异常：{}", e);
                throw new ServiceException(4252);
            }
        }
        contentSaveBo.setContentAttrList(contentAttrBoList);

        List<ContentPictureBo> contentPictureBoList = new ArrayList<ContentPictureBo>();
        for (ContentPicture contentPicture : contentPictureList) {
            ContentPictureBo contentPictureBo = new ContentPictureBo();
            try {
                BeanUtils.copyProperties(contentPicture, contentPictureBo);
                contentPictureBoList.add(contentPictureBo);
            } catch (Exception e) {
                LOGGER.error("查询单个文章信息异常：{}", e);
                throw new ServiceException(4252);
            }
        }
        contentSaveBo.setContentPictureList(contentPictureBoList);

        List<FileBo> fileBoList = new ArrayList<FileBo>();
        for (File file : fileList) {
            FileBo fileBo = new FileBo();
            try {
                BeanUtils.copyProperties(file, fileBo);
                fileBoList.add(fileBo);
            } catch (Exception e) {
                LOGGER.error("查询单个文章信息异常：{}", e);
                throw new ServiceException(4252);
            }
        }
        contentSaveBo.setFileList(fileBoList);

        //获取用户组
        List<ContentGroupView> groupList = groupRoMapper.selectList(contentId);
        List<ContentGroupViewBo> groupBoList = new ArrayList<ContentGroupViewBo>();
        if (groupList != null) {
            for (ContentGroupView group : groupList) {
                ContentGroupViewBo groupBo = new ContentGroupViewBo();
                try {
                    BeanUtils.copyProperties(group, groupBo);
                    groupBoList.add(groupBo);
                } catch (Exception e) {
                    LOGGER.error("查询单个文章信息异常：{}", e);
                    throw new ServiceException(4252);
                }
            }
        }
        contentSaveBo.setGroupList(groupBoList);

        //获取专题组
        List<ContentTopic> topicList = topicRoMapper.selectByPrimaryKey(contentId);
        List<ContentTopicBo> topicBoList = new ArrayList<ContentTopicBo>();
        if (topicList != null) {
            for (ContentTopic topic : topicList) {
                ContentTopicBo topicBo = new ContentTopicBo();
                try {
                    BeanUtils.copyProperties(topic, topicBo);
                    topicBoList.add(topicBo);
                } catch (Exception e) {
                    LOGGER.error("查询单个文章信息异常：{}", e);
                    throw new ServiceException(4252);
                }
            }
        }
        contentSaveBo.setTopicList(topicBoList);

        //获取标签组
        List<Contenttagid> tagList = tagRoMapper.selectList(contentId);
        List<ContenttagidBo> tagBoList = new ArrayList<ContenttagidBo>();
        if (tagList != null) {
            for (Contenttagid tag : tagList) {
                ContenttagidBo tagBo = new ContenttagidBo();
                try {
                    BeanUtils.copyProperties(tag, tagBo);
                    tagBoList.add(tagBo);
                } catch (Exception e) {
                    LOGGER.error("查询单个文章信息异常：{}", e);
                    throw new ServiceException(4252);
                }
            }
        }
        contentSaveBo.setTagList(tagBoList);

        LOGGER.info("{}", contentSaveBo);
        return contentSaveBo;
    }

    @Transactional("db1TxManager")
    @Override
    public ContentSaveBo update(ContentSaveBo contentSaveBo) {
        JSONObject jsonStu = JSONObject.fromObject(contentSaveBo);
        LOGGER.info("更新文章信息:{}", jsonStu.toString());
        //内容
        ContentBo contentBo = contentSaveBo.getContent();
        Content content = new Content();
        try {
            BeanUtils.copyProperties(contentBo, content);
            contentMapper.updateByPrimaryKeySelective(content);
        } catch (Exception e) {
            LOGGER.error("更新文章信息异常：{}", e);
            throw new ServiceException(4253);
        }
        //内容扩展项
        ContentExtBo contentExtBo = contentSaveBo.getContentExt();
        ContentExt contentExt = new ContentExt();
        try {
            BeanUtils.copyProperties(contentExtBo, contentExt);
            contentExtMapper.updateByPrimaryKeySelective(contentExt);
        } catch (Exception e) {
            LOGGER.error("更新文章信息异常：{}", e);
            throw new ServiceException(4253);
        }
        //内容文本
        ContentTxtBo contentTxtBo = contentSaveBo.getContentTxt();
        ContentTxt contentTxt = new ContentTxt();
        try {
            if (contentTxtBo != null) {
                BeanUtils.copyProperties(contentTxtBo, contentTxt);
                contentTxtMapper.updateByPrimaryKeySelective(contentTxt);
            }
        } catch (Exception e) {
            LOGGER.error("更新文章信息异常：{}", e);
            throw new ServiceException(4253);
        }
        //内容扩展属性
        List<ContentAttrBo> contentAttrList = contentSaveBo.getContentAttrList();
        //内容图片
        List<ContentPictureBo> contentPictureList = contentSaveBo.getContentPictureList();
        //内容附件
        List<FileBo> fileList = contentSaveBo.getFileList();

        LOGGER.info("准备插入内容扩展属性");
        if (contentAttrList != null) {
            for (ContentAttrBo contentAttrBo : contentAttrList) {
                ContentAttr contentAttr = new ContentAttr();
                try {

                    BeanUtils.copyProperties(contentAttrBo, contentAttr);
                    List<ContentAttr> contentAttrListFromDb = contentAttrMapper.selectContentAttr(contentAttr);
                    if(contentAttrListFromDb==null || contentAttrListFromDb.size()==0) {
                        contentAttrMapper.insert(contentAttr);
                    }else if(contentAttrListFromDb!=null && contentAttrListFromDb.size()==1) {
                        contentAttrMapper.updateByPrimaryKey(contentAttr);
                    }else{
                        LOGGER.error("内容扩展属性有重复:"+JSONObject.fromObject(contentAttr).toString());
                        throw new ServiceException(4253);
                    }
                } catch (Exception e) {
                    LOGGER.error("更新文章信息异常：{}", e);
                    throw new ServiceException(4253);
                }
            }
        }

        int priority = 0;//排序
        //根据内容ID删除内容图片，然后再新增
        contentPictureMapper.deleteByContentId(content.getContentId());
        if (contentPictureList != null) {
            for (ContentPictureBo contentPictureBo : contentPictureList) {
                contentPictureBo.setPriority(priority);
                ContentPicture contentPicture = new ContentPicture();
                try {
                    BeanUtils.copyProperties(contentPictureBo, contentPicture);
                    contentPictureMapper.insert(contentPicture);
                    priority++;
                } catch (Exception e) {
                    LOGGER.error("更新文章信息异常：{}", e);
                    throw new ServiceException(4253);
                }
            }
        }

        //根据内容ID删除附件信息，然后再新增
        fileMapper.deleteByContentId(content.getContentId());
        if (fileList != null) {
            for (FileBo fileBo : fileList) {
                File file = new File();
                try {
                    BeanUtils.copyProperties(fileBo, file);
                    fileMapper.insert(file);
                } catch (Exception e) {
                    LOGGER.error("更新文章信息异常：{}", e);
                    throw new ServiceException(4253);
                }
            }
        }


        //用户组
        groupMapper.deleteByPrimaryKey(content.getContentId());
        List<ContentGroupViewBo> groupList = contentSaveBo.getGroupList();
        if (groupList != null) {
            for (ContentGroupViewBo groupBo : groupList) {
                ContentGroupView group = new ContentGroupView();
                try {
                    BeanUtils.copyProperties(groupBo, group);
                    groupMapper.insert(group);
                } catch (Exception e) {
                    LOGGER.error("更新文章信息异常：{}", e);
                    throw new ServiceException(4253);
                }
            }
        }

        //专题组
        topicMapper.deleteByContentId(content.getContentId());
        List<ContentTopicBo> topicList = contentSaveBo.getTopicList();
        if (topicList != null) {
            for (ContentTopicBo topicBo : topicList) {
                ContentTopic topic = new ContentTopic();
                try {
                    BeanUtils.copyProperties(topicBo, topic);
                    topicMapper.insert(topic);
                } catch (Exception e) {
                    LOGGER.error("更新文章信息异常：{}", e);
                    throw new ServiceException(4253);
                }
            }
        }

        //标签组
        tagMapper.deleteByPrimaryKey(content.getContentId());
        List<ContenttagidBo> tagList = contentSaveBo.getTagList();
        if (tagList != null) {
            for (ContenttagidBo tagBo : tagList) {
                Contenttagid tag = new Contenttagid();
                try {
                    BeanUtils.copyProperties(tagBo, tag);
                    tagMapper.insert(tag);
                } catch (Exception e) {
                    LOGGER.error("更新文章信息异常：{}", e);
                    throw new ServiceException(4253);
                }
            }
        }
        return contentSaveBo;
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String contentId) {
        try {
            LOGGER.info("删除文章信息:{}", contentId);
            //删除内容扩展信息
            contentExtMapper.deleteByPrimaryKey(contentId);
            //删除内容文本信息
            contentTxtMapper.deleteByPrimaryKey(contentId);
            //删除内容扩展属性信息
            contentAttrMapper.deleteByPrimaryKey(contentId);
            //删除内容图片信息
            contentPictureMapper.deleteByPrimaryKey(contentId);
            //删除内容附件信息
            fileMapper.updateByContentId(contentId);
            //用户组
            groupMapper.deleteByPrimaryKey(contentId);
            //专题组
            topicMapper.deleteByContentId(contentId);
            //标签组
            tagMapper.deleteByPrimaryKey(contentId);
            //删除内容信息
            contentMapper.deleteByPrimaryKey(contentId);
        } catch (Exception e) {
            LOGGER.error("删除文章信息异常：{}", e);
            throw new ServiceException(4254);
        }
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String deleteList(String[] contentIds) {
        for (int i = 0; i < contentIds.length; i++) {
            this.delete(contentIds[i]);
        }
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String updateStatusList(String[] contentIds) {
        try {
            contentMapper.updateStatusList(contentIds);
            contentExtMapper.updatRegenerateList0(contentIds);
//            contentExtMapper.updateReleaseDate(contentIds);
        } catch (Exception e) {
            LOGGER.error("撤销文章信息异常：{}", e);
            throw new ServiceException(4258);
        }
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String updatRegenerateList(String[] contentIds) {
        contentExtMapper.updatRegenerateList(contentIds);
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public ContentUpdateListBo updateList(ContentUpdateListBo contentUpdateListBo) {
        List<ContentBo> contentBoList = contentUpdateListBo.getContentBoList();
        for (ContentBo contentBo : contentBoList) {
            Content content = new Content();
            try {
                BeanUtils.copyProperties(contentBo, content);
                contentMapper.updateByPrimaryKeySelective(content);
            } catch (Exception e) {
                LOGGER.error("类转换异常：{}", e);
                throw new RuntimeException("类型转换异常：{}", e);
            }
        }
        return contentUpdateListBo;
    }

    @Transactional("db1TxManager")
    @Override
    public ContentTopicListBo updatetopicList(ContentTopicListBo topicListBo) {
        if (topicListBo != null) {
            for (ContentTopicBo topicBo : topicListBo.getTopicBoList()) {
                ContentTopic topic = new ContentTopic();
                try {
                    BeanUtils.copyProperties(topicBo, topic);
                } catch (Exception e) {
                    LOGGER.error("类转换异常：{}", e);
                    throw new RuntimeException("类型转换异常：{}", e);
                }
                Topic tp = tpRoMapper.selectByPrimaryKey(topic.getTopicId());
                Content ct = contentRoMapper.selectByContentId(topic.getContentId());
                if (tp != null && ct != null && tp.getSiteId() != null) {
                    if (!tp.getSiteId().equals(ct.getSiteId())) {
                        throw new ServiceException(4256);
                    }
                } else {
                    throw new ServiceException(4257);
                }
                topicMapper.deleteByPrimaryKey(topic);
                topicMapper.insert(topic);
            }
        }
        return topicListBo;
    }


    @Override
    public List<ContentudBo> selectContentudList(Map<String, Object> map) {
        List<ContentudBo> list = new ArrayList<ContentudBo>();
        //下一篇
        ContentudBo contentudBo1 = contentRoMapper.selectByReleaseDateAsc(map);
        list.add(contentudBo1);
        //上一篇
        ContentudBo contentudBo2 = contentRoMapper.selectByReleaseDateDesc(map);
        list.add(contentudBo2);
        return list;
    }


    @Override
    public String updateViewsDay(String contentId) {
        contentMapper.updateViewsDay(contentId);
        return "";
    }

    @Override
    public String updateViewsDayjf(String contentId, HttpServletRequest request) {
        contentMapper.updateViewsDay(contentId);
        String userId = Utils.getUserId();
        String url = SpringCtxHolder.getProperty("abc12366.uc.url") + "/todo/task/do/award/{userId}/{taskCode}";
        String responseStr;
        String sysTaskId = TaskConstant.SYS_TASK_BROSE_NEWS_CODE;
        responseStr = restTemplateUtil.send(url, HttpMethod.POST, request,userId,sysTaskId);
        return "";
    }

    @Override
    public List<ContentListBo> selectListSearch(Map<String, Object> map) {
        //查询内容列表
        List<ContentListBo> contents = contentRoMapper.selectListSearch(map);
        LOGGER.info("{}", contents);
        return contents;
    }

    @Transactional("db1TxManager")
    @Override
    public String updateStatus2(String contentId) {
        contentExtMapper.updateReleaseDate2(contentId);
        contentMapper.updateStatus2(contentId);
        return "";
    }


}
