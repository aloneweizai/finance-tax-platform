package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.*;
import com.abc12366.bangbang.mapper.db2.QuestionTipOffRoMapper;
import com.abc12366.bangbang.model.BaseObject;
import com.abc12366.bangbang.model.Message;
import com.abc12366.bangbang.model.MessageSendBo;
import com.abc12366.bangbang.model.question.QuestionTipOff;
import com.abc12366.bangbang.model.question.bo.AllocationPointAwardBO;
import com.abc12366.bangbang.model.question.bo.QuestionTipOffBo;
import com.abc12366.bangbang.model.question.bo.QuestionTipOffParamBo;
import com.abc12366.bangbang.model.question.bo.QuestionTipOffStatus;
import com.abc12366.bangbang.service.MessageSendUtil;
import com.abc12366.bangbang.service.QuestionTipOffService;
import com.abc12366.bangbang.util.MapUtil;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author liuQi
 * @Date 2017/9/19 14:17
 */
@Service
public class QuestionTipOffServiceImpl implements QuestionTipOffService{

    @Autowired
    private QuestionTipOffMapper questionTipOffMapper;

    @Autowired
    private QuestionTipOffRoMapper questionTipOffRoMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;

    @Autowired
    private QuestionCommentMapper questionCommentMapper;

    @Autowired
    private CheatsMapper cheatsMapper;

    @Autowired
    private CheatsCommentMapper cheatsCommentMapper;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private MessageSendUtil messageSendUtil;

    @Override
    public List<QuestionTipOffBo> selectList(QuestionTipOffParamBo paramBo) {
        return questionTipOffRoMapper.selectList(paramBo);
    }

    @Override
    public QuestionTipOffBo selectOne(String id) {
        return questionTipOffRoMapper.selectOne(id);
    }


    @Transactional("db1TxManager")
    @Override
    public void changeStatus(QuestionTipOff questionTipOff) {
        String id = questionTipOff.getId();
        String status = questionTipOff.getStatus();

        questionTipOff.setUpdateAdmin(Utils.getAdminId());
        questionTipOffMapper.updateByPrimaryKeySelective(questionTipOff);
        /*如果举报内容 审核通过 加被举报数, 修改状态*/
        if(QuestionTipOffStatus.approved.name().equals(status)){
            QuestionTipOff record = questionTipOffRoMapper.selectByPrimaryKey(id);
            String sourceId = record.getSourceId();
            if("question".equals(record.getSourceType())){
                questionMapper.updateReportNum(sourceId);
            }else if("answer".equals(record.getSourceType())){
                questionAnswerMapper.updateReportNum(sourceId);
            }else if("comment".equals(record.getSourceType())){
                questionCommentMapper.updateReportNum(sourceId);
            }else if("cheats".equals(record.getSourceType())){
                cheatsMapper.updateReportNum(sourceId);
            }else if("cheats_comment".equals(record.getSourceType())){
                cheatsCommentMapper.updateReportNum(sourceId);
            }
        }
    }

    @Transactional("db1TxManager")
    @Override
    public void changeStatusByAdmin(QuestionTipOff questionTipOff, HttpServletRequest request) {
        changeStatus(questionTipOff);

        QuestionTipOff record = questionTipOffRoMapper.selectByPrimaryKey(questionTipOff.getId());
        /*Message message = new Message();
        message.setType("2");
        message.setUserId(record.getCreateUser());
        message.setBusinessId(record.getId());*/
        String content = "";
        if(QuestionTipOffStatus.approved.name().equals(questionTipOff.getStatus())){
            /*送积分奖励*/
            Integer rewardsPoints = record.getRewardsPoints();
            if(rewardsPoints != null && rewardsPoints > 0){
                HashMap map = new HashMap<>();
                List<AllocationPointAwardBO> list = new ArrayList<>();
                AllocationPointAwardBO awardBO = new AllocationPointAwardBO();
                awardBO.setPoint(rewardsPoints);
                awardBO.setUserId(record.getCreateUser());
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
                content = new StringBuilder("您").append(DateUtils.dateToStr(record.getCreateTime())).append("举报的内容已被屏蔽！感谢您的参与，赠送").append(rewardsPoints).append("积分").toString();
            }
            content = new StringBuilder("您").append(DateUtils.dateToStr(record.getCreateTime())).append("举报的内容已被屏蔽！感谢您的参与").toString();
        }
        if(QuestionTipOffStatus.refuse.name().equals(questionTipOff.getStatus())){
        	content = "很抱歉！您"+ DateUtils.dateToStr(record.getCreateTime())+"举报的内容已被拒绝，拒绝原因为："+questionTipOff.getRefuseReason();
        }
        //messageSendUtil.sendMessage(message, request);

        
        // 2018-03-08
		MessageSendBo messageSendBo = new MessageSendBo();
		messageSendBo.setType(MessageConstant.USER_MESSAGE);
		messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_BANGBANG);
		messageSendBo.setBusinessId(record.getId());
		messageSendBo.setWebMsg(content);

		List<String> userIds = new ArrayList<String>();
		userIds.add(record.getCreateUser());
		messageSendBo.setUserIds(userIds);

		messageSendUtil.sendMsgBySubscriptions(messageSendBo, request);

    }

    @Override
    public QuestionTipOffBo save(QuestionTipOffBo questionTipOffBo) {

        JSONObject jsonStu = JSONObject.fromObject(questionTipOffBo);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        QuestionTipOff tipOff = new QuestionTipOff();
        questionTipOffBo.setCreateTime(new Date());
        questionTipOffBo.setId(uuid);
        questionTipOffBo.setStatus("auditing");

        Map map = MapUtil.kv("sourceId", questionTipOffBo.getSourceId(), "createUser", questionTipOffBo.getCreateUser());
        int cnt =  questionTipOffRoMapper.selectExist(map);
        if(cnt >0){
            throw new ServiceException(6370);
        }

        try {
            BeanUtils.copyProperties(questionTipOffBo, tipOff);
            questionTipOffMapper.insert(tipOff);

        } catch (Exception e) {
            throw new ServiceException(6371);
        }

        return questionTipOffBo;
    }

    @Override
    public QuestionTipOffBo savepb(QuestionTipOffBo questionTipOffBo) {

        JSONObject jsonStu = JSONObject.fromObject(questionTipOffBo);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        QuestionTipOff tipOff = new QuestionTipOff();
        questionTipOffBo.setCreateTime(new Date());
        tipOff.setUpdateTime(new Date());
        questionTipOffBo.setUpdateAdmin(Utils.getAdminId());
        questionTipOffBo.setId(uuid);
        questionTipOffBo.setStatus("approved");//拉黑

        Map map = MapUtil.kv("sourceId", questionTipOffBo.getSourceId(), "createUser", questionTipOffBo.getCreateUser());
        int cnt =  questionTipOffRoMapper.selectExist(map);
        if(cnt >0){
            throw new ServiceException(6374);
        }

        try {
            BeanUtils.copyProperties(questionTipOffBo, tipOff);
            questionTipOffMapper.insert(tipOff);
            changeStatus(tipOff);

        } catch (Exception e) {
            throw new ServiceException(6375);
        }

        return questionTipOffBo;
    }

    @Override
    public Long selectCntByStatus(String status) {
        return questionTipOffRoMapper.selectCntByStatus(status);
    }
}
