package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.QuestionHeadmanMapper;
import com.abc12366.bangbang.mapper.db2.QuestionHeadmanRoMapper;
import com.abc12366.bangbang.model.Message;
import com.abc12366.bangbang.model.MessageSendBo;
import com.abc12366.bangbang.model.question.QuestionHeadman;
import com.abc12366.bangbang.model.question.bo.QuestionHeadmanBo;
import com.abc12366.bangbang.service.MessageSendUtil;
import com.abc12366.bangbang.service.QuestionHeadmanService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.model.bo.UCUserBO;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/22 18:05
 */
@Service
public class QuestionHeadmanServiceImpl implements QuestionHeadmanService {

	@Autowired
	private QuestionHeadmanMapper questionHeadmanMapper;

	@Autowired
	private QuestionHeadmanRoMapper questionHeadmanRoMapper;

	@Override
	public List<QuestionHeadman> selectList() {
		return questionHeadmanRoMapper.selectList();
	}

	@Override
	public QuestionHeadmanBo selectByPrimaryKey(String id) {
		return questionHeadmanRoMapper.selectByPrimaryKey(id);
	}

	@Autowired
	private MessageSendUtil messageSendUtil;

	@Transactional("db1TxManager")
	@Override
	public void add(QuestionHeadmanBo headmanBo) {
		QuestionHeadman headman = new QuestionHeadman();
		BeanUtils.copyProperties(headmanBo, headman);
		headman.setId(Utils.uuid());
		headman.setUserId(Utils.getUserId());
		headman.setStatus("apply");
		headman.setCreateTime(new Date());
		int cnt = questionHeadmanRoMapper.selectExist(headman.getUserId());
		if (cnt > 0) {
			// 已申请过掌门人，请勿重复申请
			throw new ServiceException(6192);
		}

		// UCUserBO userBo = Utils.getUserInfo();
		// String userLevel = "";
		// if(userBo != null){
		// userLevel = userBo.getLevel();
		// }
		// if(userLevel != null && userLevel.length() > 2){
		// String userLevel1 = userLevel.substring(2);
		// int userLevel2 = Integer.parseInt(userLevel1);
		// if(userLevel2 < 20){
		// //用户等级大于等于20级才能申请掌门人
		// throw new ServiceException(6129);
		// }
		// }else{
		// throw new ServiceException(6129);
		// }

		questionHeadmanMapper.insert(headman);
	}

	@Transactional("db1TxManager")
	@Override
	public void modify(QuestionHeadmanBo headmanBo) {
		QuestionHeadman headman = new QuestionHeadman();
		BeanUtils.copyProperties(headmanBo, headman);
		questionHeadmanMapper.updateByPrimaryKeySelective(headman);
	}

	@Transactional("db1TxManager")
	@Override
	public void delete(String id) {
		questionHeadmanMapper.deleteByPrimaryKey(id);
	}

	/* 审核，并发送站内信息 */
	@Transactional("db1TxManager")
	@Override
	public void changeStatus(QuestionHeadman record, HttpServletRequest request) {
		questionHeadmanMapper.updateByPrimaryKeySelective(record);
		QuestionHeadmanBo headmanBo = questionHeadmanRoMapper.selectByPrimaryKey(record.getId());
		/*
		 * Message message = new Message();
		 * message.setUserId(headmanBo.getUserId());
		 */
		String content = "";
		if ("success".equals(record.getStatus())) {
			content = "恭喜您！您的掌门人审核已通过！";
		}
		if ("refuse".equals(record.getStatus())) {
			content = "很抱歉！您的掌门人审核未通过！拒绝理由为：" + record.getRemark();
		}
		/*
		 * message.setType("2"); message.setBusinessId(headmanBo.getId());
		 * messageSendUtil.sendMessage(message, request);
		 */

		// 2018-03-08
		MessageSendBo messageSendBo = new MessageSendBo();
		messageSendBo.setType(MessageConstant.USER_MESSAGE);
		messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_BANGBANG);
		messageSendBo.setBusinessId(headmanBo.getId());
		messageSendBo.setWebMsg(content);

		List<String> userIds = new ArrayList<String>();
		userIds.add(headmanBo.getUserId());
		messageSendBo.setUserIds(userIds);

		messageSendUtil.sendMsgBySubscriptions(messageSendBo, request);
	}
}
