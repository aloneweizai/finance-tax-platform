package com.abc12366.uc.service;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-19
 */


import com.abc12366.uc.model.bo.LotteryTemplateBO;

import java.util.List;
import java.util.Map;

public interface LotteryTemplateService {
    List<LotteryTemplateBO> selectList(Map map);
    LotteryTemplateBO selectOne(String id);
    LotteryTemplateBO insert(LotteryTemplateBO lotteryTemplateBO);
    LotteryTemplateBO update(LotteryTemplateBO lotteryTemplateBO, String id);
    boolean delete(String id);

}
