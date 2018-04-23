package com.abc12366.bangbang.util;

import com.abc12366.bangbang.mapper.db1.QuestionLogMapper;
import com.abc12366.bangbang.model.question.QuestionLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * User: xieyanmao
 * Date: 2017-11-08
 * Time: 14:49
 */
@Component
public class BangBangDtLogUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BangBangDtLogUtil.class);
    @Autowired
    private QuestionLogMapper questionLogMapper;
    //日志类型,问题或者秘籍ID,回复ID,来源ID,用户ID,被关注用户ID
    public String insertLog(int qlogType,int bbType,String qcId,String answerId,String sourceId,String commentTxt,String userId,String attentionUserId){
        //帮邦日志记录表
        QuestionLog log = new QuestionLog();
        log.setId(UUID.randomUUID().toString().replace("-", ""));//主键ID
        //日志类型:1、提出了问题,2、回答了问题,3、评论了回答,4、收藏了问题,5、点赞了回答
        //6、发表秘籍,7、评论了秘籍,8、收藏秘籍,9、点赞了秘籍,10、关注了用户
        log.setQlogType(qlogType);//日志类型
        log.setBbType(bbType);//类型：1、问答，2、秘籍
        log.setQcId(qcId);//问题或者秘籍ID
        log.setAnswerId(answerId);//回复ID
        log.setSourceId(sourceId);//来源ID
        log.setCommentTxt(commentTxt);
        log.setUserId(userId);//用户ID
        log.setAttentionUserId(attentionUserId);//被关注用户ID
        log.setCreateTime(new Date());//记录时间
        questionLogMapper.insert(log);
        return "";
    }
}
