package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.QuestionFactionMemberMapper;
import com.abc12366.bangbang.mapper.db2.QuestionFactionMemberRoMapper;
import com.abc12366.bangbang.mapper.db2.QuestionFactionRoMapper;
import com.abc12366.bangbang.model.question.QuestionFaction;
import com.abc12366.bangbang.model.question.QuestionFactionMember;
import com.abc12366.bangbang.model.question.bo.QuestionFactionMemberBo;
import com.abc12366.bangbang.service.QueFactionMemberService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.model.bo.UCUserBO;
import com.abc12366.gateway.util.Utils;
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
public class QueFactionMemberServiceImpl implements QueFactionMemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueFactionMemberServiceImpl.class);

    @Autowired
    private QuestionFactionMemberMapper memberMapper;

    @Autowired
    private QuestionFactionMemberRoMapper memberRoMapper;

    @Autowired
    private QuestionFactionRoMapper factionRoMapper;

    @Override
    public List<QuestionFactionMemberBo> selectList(Map<String,Object> map) {
        List<QuestionFactionMemberBo> factionMemberBoList;
        try {
            //查询邦派成员列表
            factionMemberBoList = memberRoMapper.selectList(map);
        } catch (Exception e) {
            LOGGER.error("查询邦派成员列表信息异常：{}", e);
            throw new ServiceException(6130);
        }
        return factionMemberBoList;
    }

    @Override
    public List<QuestionFactionMemberBo> selectListTj(Map<String, Object> map) {
        List<QuestionFactionMemberBo> factionMemberBoList;
        try {
            //查询邦派成员列表(我管理的邦派)统计
            factionMemberBoList = memberRoMapper.selectListTj(map);

            for(QuestionFactionMemberBo factionMemberBo : factionMemberBoList){
                int honor = 2*factionMemberBo.getAnswerNum() + 1*factionMemberBo.getDiscussNum() + 7*factionMemberBo.getAdoptNum();
                factionMemberBo.setHonor(honor+"");
            }

        } catch (Exception e) {
            LOGGER.error("查询邦派成员列表信息异常：{}", e);
            throw new ServiceException(6130);
        }
        return factionMemberBoList;
    }

    @Transactional("db1TxManager")
    @Override
    public QuestionFactionMemberBo save(QuestionFactionMemberBo factionMemberBo) {
        JSONObject jsonStu = JSONObject.fromObject(factionMemberBo);
        LOGGER.info("新增邦派成员信息:{}", jsonStu.toString());
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userId", factionMemberBo.getUserId());
        dataMap.put("factionId", factionMemberBo.getFactionId());
        int cnt = memberRoMapper.selectMemberCnt(dataMap);
        if(cnt > 0){
            //已申请加入邦派，请勿重复申请
            throw new ServiceException(6135);
        }
        QuestionFaction faction = factionRoMapper.selectByPrimaryKey(factionMemberBo.getFactionId());

        int cnt1 = memberRoMapper.selectClassifyCnt(dataMap);
        if(cnt1 > 0){
            //该用户创建或加入的帮派所选话题分类不允许重叠
            throw new ServiceException(6125);
        }

        int peopleLimit = faction.getPeopleLimit();
        //已通过的人数
        int passcnt = memberRoMapper.selectPassMemberCnt(factionMemberBo.getFactionId());
        if(passcnt >= peopleLimit){
            //邦派人数已达上限
            throw new ServiceException(6136);
        }
        UCUserBO userBo = Utils.getUserInfo();
        String userLevel = "";
        if(userBo != null){
            userLevel = userBo.getLevel();
        }
        int minGrad = faction.getMinGrade();
        if(userLevel != null && userLevel.length() > 2){
            String userLevel1 = userLevel.substring(2);
            int userLevel2 = Integer.parseInt(userLevel1);
            if(minGrad != 0 && userLevel2 < minGrad){
                //用户等级小于入帮最低等级,不能申请入帮
                throw new ServiceException(6138);
            }
        }else{
            throw new ServiceException(6132);
        }
        // 是否自动入帮，1为是，0为否
        int auto = faction.getAuto();
        int status = 1;
        if(auto == 1){
            status = 2;
            factionMemberBo.setMemberGrade("B1");
            factionMemberBo.setDuty("B1");
        }
        try {
            factionMemberBo.setCreateTime(new Date());
            factionMemberBo.setLastUpdate(new Date());
            //保存邦派成员信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            QuestionFactionMember factionMember = new QuestionFactionMember();
            factionMemberBo.setMemberId(uuid);
            factionMemberBo.setStatus(status);
            BeanUtils.copyProperties(factionMemberBo, factionMember);
            memberMapper.insert(factionMember);
        } catch (Exception e) {
            LOGGER.error("新增邦派成员信息异常：{}", e);
            throw new ServiceException(6132);
        }

        return factionMemberBo;
    }

    @Override
    public QuestionFactionMemberBo selectFactionMember(String memberId) {
        QuestionFactionMemberBo factionMemberBo = new QuestionFactionMemberBo();
        try {
            LOGGER.info("查询单个邦派成员信息:{}", memberId);
            //查询单个邦派成员信息
            QuestionFactionMember factionMember = memberRoMapper.selectByPrimaryKey(memberId);
            BeanUtils.copyProperties(factionMember, factionMemberBo);
        } catch (Exception e) {
            LOGGER.error("查询单个邦派成员信息异常：{}", e);
            throw new ServiceException(6131);
        }
        return factionMemberBo;
    }


    @Transactional("db1TxManager")
    @Override
    public QuestionFactionMemberBo update(QuestionFactionMemberBo factionMemberBo) {
        //更新邦派成员信息
        QuestionFactionMember factionMember = new QuestionFactionMember();
        QuestionFaction faction = factionRoMapper.selectByPrimaryKey(factionMemberBo.getFactionId());
        int peopleLimit = faction.getPeopleLimit();
        if(factionMemberBo.getStatus() == 2){
            //已通过的人数
            int cnt = memberRoMapper.selectPassMemberCnt(factionMemberBo.getFactionId());
            if(cnt >= peopleLimit){
                //邦派人数已达上限
                throw new ServiceException(6136);
            }
            factionMemberBo.setDuty("B1");
            factionMemberBo.setMemberGrade("B1");
        }
        try {
            JSONObject jsonStu = JSONObject.fromObject(factionMemberBo);
            LOGGER.info("更新邦派成员信息:{}", jsonStu.toString());
            factionMemberBo.setLastUpdate(new Date());
            BeanUtils.copyProperties(factionMemberBo, factionMember);

            memberMapper.updateByPrimaryKeySelective(factionMember);
        } catch (Exception e) {
            LOGGER.error("更新邦派成员信息异常：{}", e);
            throw new ServiceException(6133);
        }
        return factionMemberBo;
    }

    @Override
    public String updateStatus(String memberId,String status) {
        //更新邦派成员信息
        try {

        } catch (Exception e) {
            LOGGER.error("更新邦派成员信息异常：{}", e);
            throw new ServiceException(6133);
        }
        return "";
    }

    @Override
    public String updateDuty(String memberId,String duty) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("memberId", memberId);//
        dataMap.put("duty", duty);//
        if("1".equals(duty)){
            int dutyCnt = memberRoMapper.selectMemberDutyCnt(dataMap);
            if(dutyCnt > 0){
                //该帮的副帮主已达上限
                throw new ServiceException(6191);
            }
        }
        //设置职位
        try {

            memberMapper.updateDuty(dataMap);
        } catch (Exception e) {
            LOGGER.error("设置职位异常：{}", e);
            throw new ServiceException(6133);
        }
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String memberId) {
        try {
            LOGGER.info("删除邦派成员信息:{}", memberId);
            memberMapper.deleteByPrimaryKey(memberId);
        } catch (Exception e) {
            LOGGER.error("删除邦派成员异常：{}", e);
            throw new ServiceException(6134);
        }
        return "";
    }

}
