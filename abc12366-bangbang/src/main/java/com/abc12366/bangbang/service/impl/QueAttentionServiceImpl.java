package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.QuestionAttentionMapper;
import com.abc12366.bangbang.mapper.db2.QuestionAttentionRoMapper;
import com.abc12366.bangbang.model.question.QuestionAttention;
import com.abc12366.bangbang.model.question.bo.QuestionAttentionBo;
import com.abc12366.bangbang.service.QueAttentionService;
import com.abc12366.bangbang.util.BangBangDtLogUtil;
import com.abc12366.bangbang.util.MapUtil;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/9/15.
 */
@Service
public class QueAttentionServiceImpl implements QueAttentionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueAttentionServiceImpl.class);

    @Autowired
    private QuestionAttentionMapper attentionMapper;

    @Autowired
    private QuestionAttentionRoMapper attentionRoMapper;

    @Autowired
    private BangBangDtLogUtil bangBangDtLogUtil;

    @Override
    public String insert(String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String userId = Utils.getUserId(request);


        QuestionAttention attention = new QuestionAttention();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        attention.setUserId(userId);
        attention.setAttentionId(uuid);
        attention.setAttentionUserId(id);
        attention.setAttentionTime(new Date());
        attention.setIsRead(0);

        Map map = MapUtil.kv("attentionUserId", id, "userId", userId);
        int cnt =  attentionRoMapper.selectExist(map);

        if(cnt >0){
            throw new ServiceException(6117);
        }



        int result = attentionMapper.insert(attention);

        //帮邦日志记录表
        //日志类型,问题或者秘籍ID,回复ID,来源ID,用户ID,被关注用户ID
//        bangBangDtLogUtil.insertLog(10, "", "", "", attention.getUserId(), attention.getAttentionUserId());


        int attentionCnt = attentionRoMapper.selectAttentionCnt(id);

        return attentionCnt+"";
    }

    @Override
    public String delete(String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String userId = Utils.getUserId(request);
        Map map = MapUtil.kv("attentionUserId", id, "userId", userId);
        attentionMapper.delete(map);
        int attentionCnt = attentionRoMapper.selectAttentionCnt(id);

        return attentionCnt+"";
    }

    @Override
    public List<QuestionAttentionBo> selectAttentionUserList(String userId) {
        //查询我关注的用户
        LOGGER.info("{}", userId);
        return attentionRoMapper.selectAttentionUserList(userId);
    }

    @Override
    public List<QuestionAttentionBo> selectUserList(String attentionUserId) {
        //查询关注我的用户
        LOGGER.info("{}", attentionUserId);
        return attentionRoMapper.selectUserList(attentionUserId);
    }

    @Override
    public String selectExist(String id, HttpServletRequest request) {
        LOGGER.info("{}", id);
        String userId = Utils.getUserId(request);
        Map map = MapUtil.kv("attentionUserId", id, "userId", userId);
        String cnt = attentionRoMapper.selectExist(map)+"";
        return cnt;
    }

    @Override
    public String updateIsRead(String id) {
        //更新为已读
        try {
            attentionMapper.updateIsRead(id);
        } catch (Exception e) {
            LOGGER.error("更新粉丝为已读异常：{}", e);
            throw new ServiceException(6119);
        }
        return "";
    }

}
