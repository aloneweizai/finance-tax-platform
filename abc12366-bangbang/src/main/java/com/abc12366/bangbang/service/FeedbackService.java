package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.Feedback;
import com.abc12366.bangbang.model.bo.FeedbackParamBO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/7 14:28
 */
public interface FeedbackService {

    Feedback add(Feedback feedBack, HttpServletRequest request);

    List<Feedback> selectList(FeedbackParamBO param);

    Long selectCntUnanswered();

    void delete(List<String> ids);

    void delete(String id);
}
