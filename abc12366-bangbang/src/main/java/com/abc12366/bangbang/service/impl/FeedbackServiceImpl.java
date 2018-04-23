package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.FeedbackMapper;
import com.abc12366.bangbang.mapper.db2.QuestionAcceptedRoMapper;
import com.abc12366.bangbang.model.Feedback;
import com.abc12366.bangbang.model.bo.FeedbackParamBO;
import com.abc12366.bangbang.model.bo.UCUserBO;
import com.abc12366.bangbang.service.FeedbackService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/7 13:36
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private QuestionAcceptedRoMapper questionAcceptedRoMapper;

    @Override
    public Feedback add(Feedback feedBack, HttpServletRequest request) {
        try {
            feedBack.setId(Utils.uuid());
            if(StringUtils.isEmpty(feedBack.getContactNumber())){
                /* 如果没有填写手机号，  查询用户的手机号 */
                UCUserBO user = questionAcceptedRoMapper.selectUCUser(Utils.getUserId());
                feedBack.setContactNumber(user.getPhone());
            }

            feedbackMapper.insert(feedBack);
            return feedBack;
        }catch (Exception e){
            LOGGER.error("FeedbackServiceImpl.add()", e);
            throw new ServiceException(4501);
        }
    }

    @Override
    public List<Feedback> selectList(FeedbackParamBO param) {
        return feedbackMapper.selectList(param);
    }

    @Override
    public Long selectCntUnanswered() {
        return feedbackMapper.selectCntUnanswered();
    }

    @Override
    public void delete(List<String> ids) {
        try {
            feedbackMapper.deleteByPrimaryKeys(ids);
        }catch (Exception e){
            LOGGER.error("FeedbackServiceImpl.delete()", e);
            throw new ServiceException(4519);
        }
    }

    @Override
    public void delete(String id) {
        try {
            feedbackMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            LOGGER.error("FeedbackServiceImpl.delete()", e);
            throw new ServiceException(4519);
        }
    }

}
