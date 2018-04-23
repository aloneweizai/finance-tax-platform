package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.QuestionDisableIpMapper;
import com.abc12366.bangbang.mapper.db2.QuestionDisableIpRoMapper;
import com.abc12366.bangbang.model.question.QuestionDisableIp;
import com.abc12366.bangbang.model.question.bo.QuestionDisableIpBo;
import com.abc12366.bangbang.service.QuestionDisableIpService;
import com.abc12366.gateway.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/18 15:49
 */
@Service
public class QuestionDisableIpServiceImpl implements QuestionDisableIpService {


    @Autowired
    private QuestionDisableIpMapper questionDisableIpMapper;

    @Autowired
    private QuestionDisableIpRoMapper questionDisableIpRoMapper;


    @Override
    public List<QuestionDisableIpBo> selectList(Map map) {
        return questionDisableIpRoMapper.selectList(map);
    }

    @Transactional("db1TxManager")
    @Override
    public void disable(QuestionDisableIp record) {
        questionDisableIpMapper.deleteByIP(record.getIp());
        record.setId(Utils.uuid());
        record.setUpdateAdmin(Utils.getAdminId());
        questionDisableIpMapper.insert(record);
    }

    @Override
    public void enable(String ip) {
        questionDisableIpMapper.deleteByIP(ip);
    }

}
