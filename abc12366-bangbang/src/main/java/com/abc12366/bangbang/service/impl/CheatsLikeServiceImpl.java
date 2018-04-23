package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CheatsCommentMapper;
import com.abc12366.bangbang.mapper.db1.CheatsLikeMapper;
import com.abc12366.bangbang.mapper.db1.CheatsMapper;
import com.abc12366.bangbang.mapper.db2.CheatsCommentRoMapper;
import com.abc12366.bangbang.mapper.db2.CheatsLikeRoMapper;
import com.abc12366.bangbang.mapper.db2.CheatsRoMapper;
import com.abc12366.bangbang.model.question.Cheats;
import com.abc12366.bangbang.model.question.CheatsComment;
import com.abc12366.bangbang.model.question.CheatsLike;
import com.abc12366.bangbang.model.question.bo.CheatsBo;
import com.abc12366.bangbang.model.question.bo.CheatsCommentBo;
import com.abc12366.bangbang.service.CheatsLikeService;
import com.abc12366.bangbang.util.BangBangDtLogUtil;
import com.abc12366.bangbang.util.MapUtil;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by xieyanmao on 2017/9/15.
 */
@Service
public class CheatsLikeServiceImpl implements CheatsLikeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheatsLikeServiceImpl.class);

    @Autowired
    private CheatsLikeMapper likeMapper;

    @Autowired
    private CheatsMapper cheatsMapper;

    @Autowired
    private CheatsRoMapper cheatsRoMapper;

    @Autowired
    private CheatsCommentMapper commentMapper;

    @Autowired
    private CheatsCommentRoMapper commentRoMapper;

    @Autowired
    private CheatsLikeRoMapper likeRoMapper;

    @Autowired
    private BangBangDtLogUtil bangBangDtLogUtil;

    @Override
    public String insert(String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String userId = Utils.getUserId(request);
        String toUserId = "";
        Cheats cheats = cheatsRoMapper.selectByPrimaryKey(id);
        String cheatsId = "";
        int likeTarget = 1;//点赞来源1为秘籍，2为评论
        if(cheats != null){
            cheatsId = cheats.getId();
            toUserId = cheats.getUserId();
        }
        if("".equals(cheatsId)){
            CheatsCommentBo comment = commentRoMapper.selectByPrimaryKey(id);
            if(comment != null){
                likeTarget = 2;
                cheatsId = comment.getCheatsId();
                toUserId = comment.getUserId();
            }
        }

        String classifyCode = cheatsRoMapper.selectclassifyCode(cheatsId);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", userId);
        dataMap.put("classifyCode", classifyCode);
        String factionId = cheatsRoMapper.selectfactionId(dataMap);
        if(factionId == null){
            factionId = "";
        }

        CheatsLike like = new CheatsLike();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        like.setUserId(userId);
        like.setToUserId(toUserId);
        like.setLikeId(uuid);
        like.setLikeType(1);
        like.setLikeTime(new Date());
        like.setCheatsId(cheatsId);
        like.setLikeTarget(likeTarget);
        like.setId(id);
        like.setFactionId(factionId);

        Map map = MapUtil.kv("id", id, "userId", userId);
        int cnt =  likeRoMapper.selectExist(map);

        if(cnt >0){
            throw new ServiceException(6115);
        }

        int likeCnt = likeRoMapper.selectLikeCnt(id)+1;

        if(likeTarget == 1){
            Cheats cheats1 = new Cheats();
            cheats1.setLikeNum(likeCnt);
            cheats1.setId(id);
            cheatsMapper.updateByPrimaryKeySelective(cheats1);

            //帮邦日志记录表
            //日志类型,问题或者秘籍ID,回复ID,来源ID,用户ID,被关注用户ID
            bangBangDtLogUtil.insertLog(9,2, like.getCheatsId(), "", like.getLikeId(),"", like.getUserId(), "");

        }else{
            CheatsComment comment1 = new CheatsComment();
            comment1.setLikeNum(likeCnt);
            comment1.setId(id);
            commentMapper.updateByPrimaryKeySelective(comment1);
        }


        int result = likeMapper.insert(like);

        return likeCnt+"";
    }

    @Override
    public String inserttrample(String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String userId = Utils.getUserId(request);
        String toUserId = "";
        Cheats cheats = cheatsRoMapper.selectByPrimaryKey(id);
        String cheatsId = "";
        int likeTarget = 1;//点赞来源1为秘籍，2为评论
        if(cheats != null){
            cheatsId = cheats.getId();
            toUserId = cheats.getUserId();
        }
        if("".equals(cheatsId)){
            CheatsCommentBo comment = commentRoMapper.selectByPrimaryKey(id);
            if(comment != null){
                likeTarget = 2;
                cheatsId = comment.getCheatsId();
                toUserId = comment.getUserId();
            }
        }

        CheatsLike like = new CheatsLike();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        like.setUserId(userId);
        like.setToUserId(toUserId);
        like.setLikeId(uuid);
        like.setLikeType(2);
        like.setLikeTime(new Date());
        like.setLikeTarget(likeTarget);
        like.setCheatsId(cheatsId);
        like.setId(id);

        Map map = MapUtil.kv("id", id, "userId", userId);
        int cnt =  likeRoMapper.selectExist(map);

        if(cnt >0){
            throw new ServiceException(6115);
        }

        int trampleNum = likeRoMapper.selectTrampleCnt(id)+1;

        if(likeTarget == 1){
            Cheats cheats1 = new Cheats();
            cheats1.setTrampleNum(trampleNum);
            cheats1.setId(id);
            cheatsMapper.updateByPrimaryKeySelective(cheats1);
        }else{
            CheatsComment comment1 = new CheatsComment();
            comment1.setTrampleNum(trampleNum);
            comment1.setId(id);
            commentMapper.updateByPrimaryKeySelective(comment1);
        }

        int result = likeMapper.insert(like);



        return trampleNum+"";
    }

    @Override
    public String delete(String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String userId = Utils.getUserId(request);
        Map map = MapUtil.kv("id", id, "userId", userId);
//        likeMapper.delete(map);
//        int likeCnt = likeRoMapper.selectLikeCnt(id);
//        CheatsAnswer answer = new CheatsAnswer();
//        answer.setLikeNum(likeCnt);
//        answer.setId(id);
//        answerMapper.updateByPrimaryKeySelective(answer);

        return "";
    }

    @Override
    public List<CheatsBo> selectList(String userId) {
        LOGGER.info("{}", userId);
        return likeRoMapper.selectList(userId);
    }

    @Override
    public String selectExist(String id, HttpServletRequest request) {
        LOGGER.info("{}", id);
        String userId = Utils.getUserId(request);
        Map map = MapUtil.kv("id", id, "userId", userId);
        String cnt = likeRoMapper.selectExist(map)+"";
        return cnt;
    }

}
