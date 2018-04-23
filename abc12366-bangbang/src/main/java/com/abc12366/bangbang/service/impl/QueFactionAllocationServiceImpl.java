package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.QuestionFactionAllocationMapper;
import com.abc12366.bangbang.mapper.db1.QuestionFactionMapper;
import com.abc12366.bangbang.mapper.db2.QuestionFactionAllocationRoMapper;
import com.abc12366.bangbang.mapper.db2.QuestionFactionRoMapper;
import com.abc12366.bangbang.model.BaseObject;
import com.abc12366.bangbang.model.question.QuestionFaction;
import com.abc12366.bangbang.model.question.QuestionFactionAllocation;
import com.abc12366.bangbang.model.question.bo.AllocationPointAwardBO;
import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationManageBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationsBo;
import com.abc12366.bangbang.service.QueFactionAllocationService;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by xieyanmao on 2017/9/14.
 */
@Service
public class QueFactionAllocationServiceImpl implements QueFactionAllocationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueFactionAllocationServiceImpl.class);

    @Autowired
    private QuestionFactionAllocationMapper allocationMapper;

    @Autowired
    private QuestionFactionAllocationRoMapper allocationRoMapper;

    @Autowired
    private QuestionFactionMapper questionFactionMapper;

    @Autowired
    private QuestionFactionRoMapper questionFactionRoMapper;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public List<QuestionFactionAllocationBo> selectList(Map<String,Object> map) {
        List<QuestionFactionAllocationBo> factionMemberBoList;
        try {
            //查询邦派奖励分配列表
            factionMemberBoList = allocationRoMapper.selectList(map);
        } catch (Exception e) {
            LOGGER.error("查询邦派奖励分配列表信息异常：{}", e);
            throw new ServiceException(6140);
        }
        return factionMemberBoList;
    }

    @Transactional("db1TxManager")
    @Override
    public QuestionFactionAllocationBo save(QuestionFactionAllocationBo factionAllocationBo) {
        JSONObject jsonStu = JSONObject.fromObject(factionAllocationBo);
        LOGGER.info("新增邦派奖励分配信息:{}", jsonStu.toString());
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("factionId", factionAllocationBo.getFactionId());//
        dataMap.put("userId", factionAllocationBo.getUserId());
        dataMap.put("state", "1");
        int cnt = allocationRoMapper.selectAllocationCnt(dataMap);
        if(cnt > 0){
            //已对该用户申请奖励分配,请勿重复申请
            throw new ServiceException(6145);
        }

        Map<String, Object> dataMap1 = new HashMap<>();
        dataMap1.put("factionId", factionAllocationBo.getFactionId());//
        dataMap1.put("state", "1");
        //已申请分配的积分
        int integral = allocationRoMapper.selectIntegral(dataMap1);

        Map<String, Object> dataMap2 = new HashMap<>();
        dataMap2.put("factionId", factionAllocationBo.getFactionId());//
        //邦派剩余奖励积分
        int awardPoint = allocationRoMapper.selectAwardPoint(dataMap2);

        if((integral + factionAllocationBo.getIntegral()) > awardPoint ){
            //待审批分配的积分大于邦派剩余奖励积分
            throw new ServiceException(6146);
        }

        try {
            factionAllocationBo.setCreateTime(new Date());
            factionAllocationBo.setUpdateTime(new Date());
            //保存邦派成员信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            QuestionFactionAllocation factionAllocation = new QuestionFactionAllocation();
            factionAllocationBo.setId(uuid);
            factionAllocationBo.setState(1);
            BeanUtils.copyProperties(factionAllocationBo, factionAllocation);
            allocationMapper.insert(factionAllocation);
        } catch (Exception e) {
            LOGGER.error("新增邦派奖励分配信息异常：{}", e);
            throw new ServiceException(6142);
        }

        return factionAllocationBo;
    }

    @Override
    public QuestionFactionAllocationBo selectFactionAllocation(String id) {
        QuestionFactionAllocationBo factionAllocationBo = new QuestionFactionAllocationBo();
        try {
            LOGGER.info("查询单个邦派奖励分配信息:{}", id);
            //查询单个邦派奖励分配信息
            QuestionFactionAllocation factionAllocation = allocationRoMapper.selectByPrimaryKey(id);
            BeanUtils.copyProperties(factionAllocation, factionAllocationBo);
        } catch (Exception e) {
            LOGGER.error("查询单个邦派奖励分配信息异常：{}", e);
            throw new ServiceException(6141);
        }
        return factionAllocationBo;
    }


    @Transactional("db1TxManager")
    @Override
    public QuestionFactionAllocationBo update(QuestionFactionAllocationBo factionAllocationBo) {
        //更新邦派奖励分配信息
        QuestionFactionAllocation factionAllocation = new QuestionFactionAllocation();
        try {
            JSONObject jsonStu = JSONObject.fromObject(factionAllocationBo);
            LOGGER.info("更新邦派奖励分配信息:{}", jsonStu.toString());
            factionAllocationBo.setUpdateTime(new Date());
            BeanUtils.copyProperties(factionAllocationBo, factionAllocation);
            allocationMapper.updateByPrimaryKeySelective(factionAllocation);
        } catch (Exception e) {
            LOGGER.error("更新邦派奖励分配信息异常：{}", e);
            throw new ServiceException(6143);
        }
        return factionAllocationBo;
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String id) {
        try {
            LOGGER.info("删除邦派奖励分配信息:{}", id);
            allocationMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("删除邦派奖励分配异常：{}", e);
            throw new ServiceException(6144);
        }
        return "";
    }

    @Override
    public List<QuestionFactionAllocationManageBo> selectManageList(Map<String, Object> map) {
        try {
            return allocationRoMapper.selectAllocationManageList(map);
        } catch (Exception e) {
            LOGGER.error("查询邦派成员奖励分配列表信息异常：{}", e);
            throw new ServiceException(6140);
        }
    }

    @Override
    public List<QuestionFactionAllocationsBo> selectAllocationList(Map<String, Object> map) {
        try {
            return allocationRoMapper.selectAllocationList(map);
        } catch (Exception e) {
            LOGGER.error("查询邦派成员奖励分配列表信息异常：{}", e);
            throw new ServiceException(6140);
        }
    }

    @Transactional("db1TxManager")
    @Override
    public void audit(List<QuestionFactionAllocationManageBo> records, HttpServletRequest request) {
        try{
            List<String> ids = new ArrayList<>();
            for (QuestionFactionAllocationManageBo bo: records){
                allocationMapper.audit(bo);
                //审核通过的ids
                if(bo.getStatus().intValue() == 2){
                    ids.add(bo.getId());
                }
            }
            //审核通过的成员加积分
            if(!ids.isEmpty()){
                List<AllocationPointAwardBO> list = allocationRoMapper.selectPointAwardListByIds(ids);
                Map<String, Object> map = new HashMap<>();
                map.put("pointAwardBOList", list);
                try {
                    String url = SpringCtxHolder.getProperty("abc12366.api.url") + "/uc/points/batch/award";
                    String responseStr = restTemplateUtil.exchange(url, HttpMethod.POST, map, request);
                    if (!StringUtils.isEmpty(responseStr)) {
                        BaseObject response = JSON.parseObject(responseStr, BaseObject.class);
                        if(!response.getCode().equals("2000")){
                            throw new ServiceException(6143);
                        }
                    }
                } catch (Exception e) {
                    throw new ServiceException(6143);
                }
                //成员积分加完后要扣除帮派的积分
                for (AllocationPointAwardBO bo: list){
                    QuestionFaction faction = questionFactionRoMapper.selectByPrimaryKey(bo.getFactionId());
                    if(faction.getAwardPoint() == null || faction.getAwardPoint().intValue() < bo.getPoint()){
                        throw new ServiceException(6146);
                    }else{
                        questionFactionMapper.decreaseAwardPoint(bo.getFactionId(), bo.getPoint());
                    }
                }
            }
        }catch (Exception e){
            if(e instanceof ServiceException){
                throw e;
            }else{
                LOGGER.error("邦派成员奖励分配信息异常：{}", e);
                throw new ServiceException(6143);
            }
        }
    }
}