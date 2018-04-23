package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.QuestionnaireParamMapper;
import com.abc12366.cms.mapper.db2.QuestionnaireParamRoMapper;
import com.abc12366.cms.service.QuestionnaireParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lizhongwei
 * @create 2017-06-16 4:21 PM
 * @since 1.0.0
 */
@Service
public class QuestionnaireParamServiceImpl implements QuestionnaireParamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionnaireParamServiceImpl.class);
    @Autowired
    private QuestionnaireParamRoMapper questionnaireRoMapper;

    @Autowired
    private QuestionnaireParamMapper questionnaireMapper;

}
