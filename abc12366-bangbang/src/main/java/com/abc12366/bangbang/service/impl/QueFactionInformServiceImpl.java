package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.QuestionFactionInformMapper;
import com.abc12366.bangbang.mapper.db2.QuestionFactionInformRoMapper;
import com.abc12366.bangbang.model.question.QuestionFactionInform;
import com.abc12366.bangbang.model.question.bo.QuestionFactionInformBo;
import com.abc12366.bangbang.service.QueFactionInformService;
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
public class QueFactionInformServiceImpl implements QueFactionInformService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueFactionInformServiceImpl.class);

    @Autowired
    private QuestionFactionInformMapper informMapper;

    @Autowired
    private QuestionFactionInformRoMapper informRoMapper;

    @Override
    public List<QuestionFactionInformBo> selectList(Map<String,Object> map) {
        List<QuestionFactionInformBo> factionMemberBoList;
        try {
            //查询邦派通知列表
            factionMemberBoList = informRoMapper.selectList(map);
        } catch (Exception e) {
            LOGGER.error("查询邦派通知列表信息异常：{}", e);
            throw new ServiceException(6150);
        }
        return factionMemberBoList;
    }

    @Transactional("db1TxManager")
    @Override
    public QuestionFactionInformBo save(QuestionFactionInformBo factionInformBo) {
        JSONObject jsonStu = JSONObject.fromObject(factionInformBo);
        LOGGER.info("新增邦派通知信息:{}", jsonStu.toString());
        Map<String, Object> dataMap = new HashMap<>();
        try {
            factionInformBo.setCreateTime(new Date());
            factionInformBo.setUpdateTime(new Date());
            //保存邦派通知信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            QuestionFactionInform factionInform = new QuestionFactionInform();
            factionInformBo.setId(uuid);
            BeanUtils.copyProperties(factionInformBo, factionInform);
            informMapper.insert(factionInform);
        } catch (Exception e) {
            LOGGER.error("新增邦派通知信息异常：{}", e);
            throw new ServiceException(6152);
        }

        return factionInformBo;
    }

    @Override
    public QuestionFactionInformBo selectFactionInform(String id) {
        QuestionFactionInformBo factionInformBo = new QuestionFactionInformBo();
        try {
            LOGGER.info("查询单个邦派通知信息:{}", id);
            //查询单个邦派通知信息
            QuestionFactionInform factionInform = informRoMapper.selectByPrimaryKey(id);
            BeanUtils.copyProperties(factionInform, factionInformBo);
        } catch (Exception e) {
            LOGGER.error("查询单个邦派通知信息异常：{}", e);
            throw new ServiceException(6151);
        }
        return factionInformBo;
    }


    @Transactional("db1TxManager")
    @Override
    public QuestionFactionInformBo update(QuestionFactionInformBo factionInformBo) {
        //更新邦派通知信息
        QuestionFactionInform factionInform = new QuestionFactionInform();
        try {
            JSONObject jsonStu = JSONObject.fromObject(factionInformBo);
            LOGGER.info("更新邦派通知信息:{}", jsonStu.toString());
            factionInformBo.setUpdateTime(new Date());
            BeanUtils.copyProperties(factionInformBo, factionInform);
            informMapper.updateByPrimaryKeySelective(factionInform);
        } catch (Exception e) {
            LOGGER.error("更新邦派通知信息异常：{}", e);
            throw new ServiceException(6153);
        }
        return factionInformBo;
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String id) {
        try {
            LOGGER.info("删除邦派通知信息:{}", id);
            informMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("删除邦派通知异常：{}", e);
            throw new ServiceException(6154);
        }
        return "";
    }

}
