package com.abc12366.cms.service;


import com.abc12366.cms.model.questionnaire.bo.PrizeSetBO;

/**
 * 发奖管理接口类
 *
 * @author lizhongwei
 * @create 2017-6-17
 * @since 1.0.0
 */
public interface PrizeSetService {

    PrizeSetBO selectOne(String id);

    PrizeSetBO insert(PrizeSetBO prizeSetBO);

    PrizeSetBO update(PrizeSetBO prizeSetBO);

    void delete(PrizeSetBO prizeSetBO);
}
