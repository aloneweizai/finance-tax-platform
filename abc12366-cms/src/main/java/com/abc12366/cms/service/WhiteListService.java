package com.abc12366.cms.service;


import com.abc12366.cms.model.questionnaire.WhiteList;
import com.abc12366.cms.model.questionnaire.bo.WhiteListBO;

import java.util.List;

/**
 * 白名单管理接口类
 *
 * @author lizhongwei
 * @create 2017-6-14
 * @since 1.0.0
 */
public interface WhiteListService {

    List<WhiteList> selectList(WhiteList whiteList);

    WhiteListBO insert(WhiteListBO whiteListBO);

    void delete(WhiteListBO whiteListBO);
}
