package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CheatsCommentMapper;
import com.abc12366.bangbang.mapper.db1.CheatsMapper;
import com.abc12366.bangbang.mapper.db1.QuestionSysBlockMapper;
import com.abc12366.bangbang.mapper.db2.*;
import com.abc12366.bangbang.model.question.Cheats;
import com.abc12366.bangbang.model.question.CheatsComment;
import com.abc12366.bangbang.model.question.QuestionSysBlock;
import com.abc12366.bangbang.model.question.bo.CheatsCommentBo;
import com.abc12366.bangbang.service.CheatsCommentService;
import com.abc12366.bangbang.util.BangBangDtLogUtil;
import com.abc12366.gateway.exception.ServiceException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by xieyanmao on 2017/9/14.
 */
@Service
public class CheatsCommentServiceImpl implements CheatsCommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheatsCommentServiceImpl.class);

    @Autowired
    private CheatsCommentMapper commentMapper;

    @Autowired
    private CheatsCommentRoMapper commentRoMapper;

    @Autowired
    private QuestionSysBlockMapper questionSysBlockMapper;

    @Autowired
    private CheatsMapper cheatsMapper;

    @Autowired
    private CheatsRoMapper cheatsRoMapper;

    @Autowired
    private SensitiveWordsRoMapper sensitiveWordsRoMapper;

    @Autowired
    private QuestionDisableIpRoMapper questionDisableIpRoMapper;

    @Autowired
    private QuestionDisableUserRoMapper questionDisableUserRoMapper;

    @Autowired
    private BangBangDtLogUtil bangBangDtLogUtil;

    @Override
    public List<CheatsCommentBo> selectList(Map<String,Object> map) {
        List<CheatsCommentBo> commentBoList;
        try {
            //查询秘籍评论列表
            commentBoList = commentRoMapper.selectList(map);
        } catch (Exception e) {
            LOGGER.error("查询秘籍评论列表信息异常：{}", e);
            throw new ServiceException(6170);
        }
        return commentBoList;
    }

    @Override
    public List<CheatsCommentBo> selectMyCommentList(Map<String,Object> map) {
        List<CheatsCommentBo> commentBoList;
        try {
            //查询我的评论列表
            commentBoList = commentRoMapper.selectMyCommentList(map);
        } catch (Exception e) {
            LOGGER.error("查询我的评论列表信息异常：{}", e);
            throw new ServiceException(6170);
        }
        return commentBoList;
    }

    @Override
    public CheatsCommentBo save(CheatsCommentBo commentBo) {

        int ipcnt = questionDisableIpRoMapper.selectIpCnt(commentBo.getIp());

        if(ipcnt > 0){
            //该IP已被禁言
            throw new ServiceException(6372);
        }

        int usercnt = questionDisableUserRoMapper.selectUserCnt(commentBo.getUserId());

        if(usercnt > 0){
            //该用户已被禁言
            throw new ServiceException(6373);
        }

        try {
            JSONObject jsonStu = JSONObject.fromObject(commentBo);
            LOGGER.info("新增秘籍评论信息:{}", jsonStu.toString());

            String classifyCode = cheatsRoMapper.selectclassifyCode(commentBo.getCheatsId());

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userId", commentBo.getUserId());
            dataMap.put("classifyCode", classifyCode);
            String factionId = cheatsRoMapper.selectfactionId(dataMap);
            if(factionId == null){
                factionId = "";
            }
            commentBo.setFactionId(factionId);
            commentBo.setCreateTime(new Date());
            commentBo.setLastUpdate(new Date());
            commentBo.setLikeNum(0);
            commentBo.setTrampleNum(0);
            commentBo.setReportNum(0);
            commentBo.setStatus("0");

            //保存问题评论信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            CheatsComment comment = new CheatsComment();
            commentBo.setId(uuid);


            //敏感词校验
            String commentTxt = commentBo.getCommentTxt();
            List<String> wordList = sensitiveWordsRoMapper.selectListWords();
            if(commentTxt != null && !"".equals(commentTxt)){
                for(String word : wordList){
                    boolean bl = commentTxt.contains(word);
                    if(bl){
                        commentBo.setStatus("1");

                        //question：提问，answer：回答，comment：评论 cheats：秘籍，cheats_comment:秘籍下的评论
                        QuestionSysBlock sysBlock = new QuestionSysBlock();
                        sysBlock.setId(UUID.randomUUID().toString().replace("-", ""));
                        sysBlock.setUserId(commentBo.getUserId());
                        sysBlock.setClassifyCode(classifyCode);
                        sysBlock.setStatus("1");
                        sysBlock.setSourceId(commentBo.getId());
                        sysBlock.setSourceType("cheats_comment");
                        questionSysBlockMapper.insert(sysBlock);

                        break;
                    }
                }
            }

            int commentNum = commentRoMapper.selectCommentCnt(commentBo.getCheatsId());
            Cheats cheats = new Cheats();
            cheats.setId(commentBo.getCheatsId());
            cheats.setCommentNum(commentNum+1);
            cheatsMapper.updateByPrimaryKeySelective(cheats);

            commentBo.setCommentNum(commentNum);

            comment.setClassifyCode(classifyCode);

            BeanUtils.copyProperties(commentBo, comment);

            commentMapper.insert(comment);

            //帮邦日志记录表
            //日志类型,问题或者秘籍ID,回复ID,来源ID,用户ID,被关注用户ID
            bangBangDtLogUtil.insertLog(7,2, comment.getCheatsId(), "", comment.getId(),comment.getCommentTxt(),comment.getUserId(), "");




        } catch (Exception e) {
            LOGGER.error("新增秘籍评论信息异常：{}", e);
            throw new ServiceException(6172);
        }

        return commentBo;
    }

    @Override
    public CheatsCommentBo selectComment(String id) {
        CheatsCommentBo commentBo = new CheatsCommentBo();
        try {
            LOGGER.info("查询单个秘籍评论信息:{}", id);
            //查询单个秘籍评论信息
            commentBo = commentRoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("查询单个秘籍评论信息异常：{}", e);
            throw new ServiceException(6171);
        }
        return commentBo;
    }

    @Transactional("db1TxManager")
    @Override
    public CheatsCommentBo update(CheatsCommentBo commentBo) {

        int ipcnt = questionDisableIpRoMapper.selectIpCnt(commentBo.getIp());

        if(ipcnt > 0){
            //该IP已被禁言
            throw new ServiceException(6372);
        }

        int usercnt = questionDisableUserRoMapper.selectUserCnt(commentBo.getUserId());

        if(usercnt > 0){
            //该用户已被禁言
            throw new ServiceException(6373);
        }

        String classifyCode = cheatsRoMapper.selectclassifyCode(commentBo.getCheatsId());

        //更新秘籍评论信息
        CheatsComment comment = new CheatsComment();
        try {
            JSONObject jsonStu = JSONObject.fromObject(commentBo);
            LOGGER.info("更新秘籍评论信息:{}", jsonStu.toString());
            commentBo.setLastUpdate(new Date());
            commentBo.setStatus("0");

            //敏感词校验
            String commentTxt = commentBo.getCommentTxt();
            List<String> wordList = sensitiveWordsRoMapper.selectListWords();
            if(commentTxt != null && !"".equals(commentTxt)){
                for(String word : wordList){
                    boolean bl = commentTxt.contains(word);
                    if(bl){
                        commentBo.setStatus("1");

                        //question：提问，answer：回答，comment：评论 cheats：秘籍，cheats_comment:秘籍下的评论
                        QuestionSysBlock sysBlock = new QuestionSysBlock();
                        sysBlock.setId(UUID.randomUUID().toString().replace("-", ""));
                        sysBlock.setUserId(commentBo.getUserId());
                        sysBlock.setClassifyCode(classifyCode);
                        sysBlock.setStatus("1");
                        sysBlock.setSourceId(commentBo.getId());
                        sysBlock.setSourceType("cheats_comment");
                        questionSysBlockMapper.insert(sysBlock);

                        break;
                    }
                }
            }

            BeanUtils.copyProperties(commentBo, comment);
            commentMapper.updateByPrimaryKeySelective(comment);
        } catch (Exception e) {
            LOGGER.error("更新秘籍评论信息异常：{}", e);
            throw new ServiceException(6173);
        }
        return commentBo;
    }

    @Override
    public String updateStatus(String id,String status) {
        //更新秘籍评论信息
        try {

        } catch (Exception e) {
            LOGGER.error("更新秘籍评论信息异常：{}", e);
            throw new ServiceException(6173);
        }
        return "";
    }

    @Override
    public String delete(String id) {
        int num = 0;
        try {
            LOGGER.info("删除秘籍评论信息:{}", id);

            CheatsCommentBo commentBo = commentRoMapper.selectByPrimaryKey(id);

            int commentNum = commentRoMapper.selectCommentCnt(commentBo.getCheatsId());
            Cheats cheats = new Cheats();
            cheats.setId(commentBo.getCheatsId());
            cheats.setCommentNum(commentNum-1);
            cheatsMapper.updateByPrimaryKeySelective(cheats);
            num = commentNum-1;

            commentMapper.deleteByPrimaryKey(id);



        } catch (Exception e) {
            LOGGER.error("删除秘籍评论异常：{}", e);
            throw new ServiceException(6174);
        }
        return num+"";
    }

}
