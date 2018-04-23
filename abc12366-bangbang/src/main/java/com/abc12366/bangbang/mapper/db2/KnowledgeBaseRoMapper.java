package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.KnowledgeBase;
import com.abc12366.bangbang.model.bo.KnowledgeBaseHotParamBO;
import com.abc12366.bangbang.model.bo.KnowledgeBaseListBO;
import com.abc12366.bangbang.model.bo.KnowledgeBaseParamBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/12/18 11:06
 */
public interface KnowledgeBaseRoMapper {

    /**
     * 查询（根据主键ID查询）
     **/
    KnowledgeBase selectByPrimaryKey(@Param("id") String id);


    /*
    *
    * 知识详情页 感兴趣的知识查询
    *
    **/
    List<KnowledgeBase> interestedList(@Param("id") String id, @Param("num") int num);

    /*
     *
     * 知识详情页 感兴趣的知识查询
     *
     **/
    List<KnowledgeBase> relatedList(@Param("id") String id, @Param("num") int num);

    /*
    *
    * 帮助中心首页热点推荐查询
    *
    **/
    List<KnowledgeBase> hotList(KnowledgeBaseHotParamBO param);

    /*
     *
     * 财税网首页最新问题查询
     *
     **/
    List<KnowledgeBase> nearestList(KnowledgeBaseHotParamBO param);

    /*
    *
    * 财税网首页热点推荐查询
    *
    **/
    List<KnowledgeBase> hotUnClassifyList(KnowledgeBaseHotParamBO param);

    /*
    *
    * UC列表查询
    *
    **/
    List<KnowledgeBase> selectUCList(KnowledgeBaseParamBO param);

    /*
    *
    * 根据标签查询热点知识、问题
    *
    **/
    List<KnowledgeBase> selectUCListBytag(KnowledgeBaseParamBO param);

    /*
    *
    * 列表查询
    *
    **/
    List<KnowledgeBaseListBO> selectList(KnowledgeBaseParamBO param);

    /* 根据知识库分类id  查询知识库个数 */
    int selectCntByCategoryId(String categoryId);

    List<KnowledgeBase> wxhotUnClassifyMap(KnowledgeBaseHotParamBO param);

    /**
     * 采集来源列表
     * @return
     */
    List<String> selectSourceList();

    /**
     * 根据标题查询个数
     * @param subject
     * @return
     */
    int selectCntBySubject(@Param("subject")String subject, @Param("id")String id);

    /**
     * 根据标题查询匹配标题的知识库
     * @param subject
     * @return
     */
    List<KnowledgeBase> selectBySubject(@Param("subject")String subject);

}
