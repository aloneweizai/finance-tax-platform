package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.ReturnVisitMapper;
import com.abc12366.bangbang.mapper.db2.QuestionAcceptedRoMapper;
import com.abc12366.bangbang.mapper.db2.ReturnVisitRoMapper;
import com.abc12366.bangbang.model.ReturnVisit;
import com.abc12366.bangbang.model.bo.ReturnVisitBO;
import com.abc12366.bangbang.model.bo.UCUserBO;
import com.abc12366.bangbang.service.ReturnVisitService;
import com.abc12366.gateway.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lizhongwei
 * @Date 2017/9/21 13:39
 */
@Service
public class ReturnVisitServiceImpl implements ReturnVisitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReturnVisitServiceImpl.class);

    @Autowired
    private ReturnVisitMapper returnVisitMapper;

    @Autowired
    private QuestionAcceptedRoMapper questionAcceptedRoMapper;


    @Autowired
    private ReturnVisitRoMapper returnVisitRoMapper;


    @Override
    public ReturnVisit add(ReturnVisit returnVisit) {
        try {
            returnVisit.setId(1);
            returnVisitMapper.insert(returnVisit);
            return returnVisit;
        }catch (Exception e){
            LOGGER.error("ReturnVisitServiceImpl.add()", e);
            throw new ServiceException(4501);
        }
    }

    @Override
    public List<ReturnVisit> selectList(ReturnVisitBO returnVisitBO) {
        UCUserBO ucUserBO = questionAcceptedRoMapper.selectUCUser(returnVisitBO.getUserId());
        if(ucUserBO != null && ucUserBO.getPhone() != null && !"".equals(ucUserBO.getPhone())){
            returnVisitBO.setPhone(ucUserBO.getPhone());
            return returnVisitRoMapper.selectList(returnVisitBO);
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(String id) {
        try {
            returnVisitMapper.delete(id);
        }catch (Exception e){
            LOGGER.error("ReturnVisitServiceImpl.delete()", e);
            throw new ServiceException(4519);
        }
    }

    @Override
    public List<ReturnVisitBO> selectStatisList(ReturnVisitBO param) {
        UCUserBO ucUserBO = questionAcceptedRoMapper.selectUCUser(param.getUserId());
        if(ucUserBO != null && ucUserBO.getPhone() != null && !"".equals(ucUserBO.getPhone())){
            param.setPhone(ucUserBO.getPhone());
            return returnVisitRoMapper.selectStatisList(param);
        }
        return new ArrayList<>();
    }

}
