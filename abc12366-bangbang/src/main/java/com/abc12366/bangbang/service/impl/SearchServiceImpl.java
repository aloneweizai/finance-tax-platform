package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db2.SearchMapper;
import com.abc12366.bangbang.model.question.CheatsSearchBo;
import com.abc12366.bangbang.model.question.QuestionSearchBo;
import com.abc12366.bangbang.service.SearchService;
import com.abc12366.gateway.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by stuy on 2017/11/9.
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);
    @Autowired
    private SearchMapper searchMapper;


    @Override
    public List<QuestionSearchBo> queryQuestionSearch(String text) {
        LOGGER.info("参数值："+text);
        List<QuestionSearchBo> list = null;
        try {
            list = searchMapper.queryQuestionSearch(text+"*");
            if(list!=null&&list.size()>0){
                for(QuestionSearchBo questionSearchBo : list){
                    Map<String, Object> maps = searchMapper.queryAnswer(questionSearchBo.getQuestionid());
                    if(maps!=null&&maps.size()>0){
                        if(maps.get("shortAnswer")!=null){
                            questionSearchBo.setShortAnswer(maps.get("shortAnswer").toString());
                        }
                        questionSearchBo.setAnswerId(maps.get("id").toString());
                        if(maps.get("likenum")!=null&&!"".equals(maps.get("likenum").toString())){
                            questionSearchBo.setLikeNum(Integer.parseInt(maps.get("likenum").toString()));
                        }
                        if(maps.get("treadNum")!=null&&!"".equals(maps.get("treadNum"))){
                            int treadNum = Integer.parseInt(maps.get("treadNum").toString());
                            questionSearchBo.setTreadNum(treadNum);
                        }
                        if(maps.get("commentNum")!=null&&!"".equals(maps.get("commentNum").toString())){
                            int commentNum = Integer.parseInt(maps.get("commentNum").toString());
                            questionSearchBo.setCommentNum(commentNum);
                        }
                    }
                    Map<String, String> map = searchMapper.queryUser(questionSearchBo.getUserid());
                    if(map!=null&&map.size()>0){
                        questionSearchBo.setNickname(map.get("nickname"));
                        questionSearchBo.setUserPicturePath(map.get("userPicturePath"));
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.info("查询异常:"+e.getMessage());
            throw new ServiceException(5000);
        }
        return list;
    }


    public List<CheatsSearchBo> queryCheatsSearch(String text){
        LOGGER.info("参数值："+text);
        List<CheatsSearchBo> listcheats = null;
        try {
            listcheats = searchMapper.queryCheats(text + "*");
            if(listcheats!=null&&listcheats.size()>0){
                for(CheatsSearchBo cheatsSearchBo: listcheats){
                    Map<String, String> map = searchMapper.queryUser(cheatsSearchBo.getUserId());
                    if(map!=null&&map.size()>0){
                        cheatsSearchBo.setNickname(map.get("nickname"));
                        cheatsSearchBo.setUserPicturePath(map.get("userPicturePath"));
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.info("查询异常:"+e.getMessage());
            throw new ServiceException(5000);
        }
        return listcheats;
    }


}
