package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.bo.WikiAccesslogBO;
import com.abc12366.bangbang.model.bo.WikiBO;

import java.util.List;

/**
 * Created by MY on 2017-05-15.
 */
public interface WikiService {

    /**
     * 查询百科主题列表信息
     *
     * @param wikiBO
     * @return
     */
    List<WikiBO> selectList(WikiBO wikiBO);

    /**
     * 查询百科主题信息
     *
     * @param id
     * @return
     */
    WikiBO selectOne(String id);


    /**
     * 修改购物车
     *
     * @param wikiBO
     * @return
     */
    WikiBO update(WikiBO wikiBO);


    /**
     * 新增百科主题
     *
     * @param wikiBO
     * @return
     */
    WikiBO addWiki(WikiBO wikiBO);


    /**
     * 删除百科主题
     *
     * @param wikiBO
     */
    void deleteWiki(WikiBO wikiBO);

    /**
     * 新增百科主题日志
     */

    WikiAccesslogBO addWikiLog(WikiAccesslogBO accesslogBO);
}
