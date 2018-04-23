package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.AnswerLogMapper;
import com.abc12366.cms.mapper.db1.AnswerMapper;
import com.abc12366.cms.mapper.db2.AnswerLogRoMapper;
import com.abc12366.cms.mapper.db2.AnswerRoMapper;
import com.abc12366.cms.model.bo.AnswerLogRolltjBo;
import com.abc12366.cms.model.bo.AnswerLogtjListBo;
import com.abc12366.cms.model.questionnaire.Answer;
import com.abc12366.cms.model.questionnaire.AnswerLog;
import com.abc12366.cms.model.questionnaire.bo.AnswerLogBO;
import com.abc12366.cms.model.questionnaire.bo.AnswertjBO;
import com.abc12366.cms.service.AnswerLogService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lizhongwei
 * @create 2017-06-16 4:21 PM
 * @since 1.0.0
 */
@Service
public class AnswerLogServiceImpl implements AnswerLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnswerLogServiceImpl.class);
    @Autowired
    private AnswerLogRoMapper answerLogRoMapper;

    @Autowired
    private AnswerLogMapper answerLogMapper;

    @Autowired
    private AnswerRoMapper answerRoMapper;

    @Autowired
    private AnswerMapper answerMapper;

    /*
 * 毫秒转化时分秒毫秒
 */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond + "毫秒");
        }
        return sb.toString();
    }

    @Override
    public List<AnswerLogBO> selectList(AnswerLogBO answerLogBO) {

        return answerLogRoMapper.selectList(answerLogBO);
    }

    @Override
    public AnswerLogBO selectOne(String id) {
        return answerLogRoMapper.selectOne(id);
    }

    @Transactional("db1TxManager")
    @Override
    public AnswerLogBO insert(AnswerLogBO answerLogBO) {
        AnswerLog answerLog = new AnswerLog();
        BeanUtils.copyProperties(answerLogBO, answerLog);
        String answerLogId = Utils.uuid();
        answerLog.setId(answerLogId);
        Date date = new Date();
        answerLog.setStartTime(date);
        answerLog.setEndTime(date);
        int insert = answerLogMapper.insert(answerLog);
        if (insert != 1) {
            LOGGER.info("{新增答题记录失败}", answerLog);
            throw new ServiceException(4407);
        }
        AnswerLogBO bo = new AnswerLogBO();
        BeanUtils.copyProperties(answerLog, bo);
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public AnswerLogBO update(AnswerLogBO answerLogBO) {
        AnswerLog answerLog = new AnswerLog();
        BeanUtils.copyProperties(answerLogBO, answerLog);
        answerLog.setEndTime(new Date());
        int update = answerLogMapper.update(answerLog);
        if (update != 1) {
            LOGGER.info("{修改答题记录失败}", answerLog);
            throw new ServiceException(4408);
        }
        Answer answer = answerLogBO.getAnswer();
        answer.setAnswerLogId(answerLogBO.getId());
        //查询是否存在该题回答
        Answer temp = answerRoMapper.selectByLogId(answer);
        if (temp != null) {
            int upd = answerMapper.update(answer);
            if (upd != 1) {
                LOGGER.info("{修改答题失败}", answerLog);
                throw new ServiceException(4411);
            }
        } else {
            int ins = answerMapper.insert(answer);
            if (ins != 1) {
                LOGGER.info("{新增答题失败}", answerLog);
                throw new ServiceException(4410);
            }
        }
        AnswerLogBO bo = new AnswerLogBO();
        BeanUtils.copyProperties(answerLog, bo);
        bo.setAnswer(answer);
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public void delete(AnswerLogBO answerLogBO) {
        if (answerLogBO != null && answerLogBO.getId() != null) {
            String ids[] = answerLogBO.getId().split(",");
            for (String id : ids) {
                answerMapper.deleteByPrimaryKey(id);
                int del = answerLogMapper.deleteByPrimaryKey(id);
                if (del != 1) {
                    LOGGER.info("{删除答题记录失败}", id);
                    throw new ServiceException(4409);
                }
            }
        } else {
            LOGGER.info("{答题记录ID不能为空}", answerLogBO);
            throw new ServiceException(4424);
        }

    }

    @Override
    public AnswerLogBO batch(AnswerLogBO answerLogBO) {
        AnswerLog answerLog = new AnswerLog();
        String answerLogId = Utils.uuid();
        answerLogBO.setId(answerLogId);
        BeanUtils.copyProperties(answerLogBO, answerLog);
//        answerLog.setEndTime(new Date());
//        int update = answerLogMapper.update(answerLog);
//        if(update != 1){
//            LOGGER.info("{修改答题记录失败}", answerLog);
//            throw new ServiceException(4408);
//        }
        int insert = answerLogMapper.insert(answerLog);
        if (insert != 1) {
            LOGGER.info("{新增答题记录失败}", answerLog);
            throw new ServiceException(4408);
        }
        List<Answer> answerList = answerLogBO.getAnswerList();
        for (Answer answer : answerList) {
            answer.setAnswerLogId(answerLogBO.getId());
            //查询是否存在该题回答
//            Answer temp = answerRoMapper.selectByLogId(answer);
//            if(temp != null){
//                int upd = answerMapper.update(answer);
//                if(upd != 1){
//                    LOGGER.info("{修改答题失败}", answerLog);
//                    throw new ServiceException(4411);
//                }
//            }else{
            int ins = answerMapper.insert(answer);
            if (ins != 1) {
                LOGGER.info("{新增答题失败}", answerLog);
                throw new ServiceException(4410);
            }
//            }
        }

        AnswerLogBO bo = new AnswerLogBO();
        BeanUtils.copyProperties(answerLog, bo);
        bo.setAnswerList(answerList);
        return bo;
    }

    @Override
    public AnswerLogBO answerAvg(AnswerLogBO answerLogBO) {
        AnswerLog answerLog = new AnswerLog();
        BeanUtils.copyProperties(answerLogBO, answerLog);
        AnswerLogBO bo = answerLogRoMapper.selectAvgTime(answerLog);
        if (bo != null && bo.getAvgTimeLong() != null) {
            String avg = formatTime(bo.getAvgTimeLong() * 1000);
            bo.setAvgTime(avg);
        } else {
            LOGGER.info("{该问卷没有答题记录}", answerLog);
            throw new ServiceException(4425);
        }
        return bo;
    }

    @Override
    public AnswerLogtjListBo selecttj(Map<String, Object> map) {
        AnswerLogtjListBo answerLogtjListBo = new AnswerLogtjListBo();
        //浏览统计总数
        Integer llcnt = answerLogRoMapper.selectlltjs(map);
        answerLogtjListBo.setLlcnt(llcnt);
        //浏览统计总数按时间
        Integer llcnts = answerLogRoMapper.selectlltjsbysj(map);
        answerLogtjListBo.setLlcnts(llcnts);
        //浏览统计浏览统计
        List<AnswerLogRolltjBo> list = answerLogRoMapper.selectlltj(map);
        answerLogtjListBo.setList(list);
        //pc浏览统计浏览统计
        map.put("accessTerminal", "PC");
        List<AnswerLogRolltjBo> pclist = answerLogRoMapper.selectlltj(map);
        answerLogtjListBo.setPclist(pclist);
        //mobileWeb浏览统计
        map.put("accessTerminal", "MobileWeb");
        List<AnswerLogRolltjBo> mobileWeblist = answerLogRoMapper.selectlltj(map);
        answerLogtjListBo.setMobileWeblist(mobileWeblist);
        return answerLogtjListBo;
//        return answerLogRoMapper.selectAnswerLogRolltjBo(map);
    }

    @Override
    public int selectdtcnt(Map<String, Object> map) {
        //用户回答次数
        int cnt = answerLogRoMapper.selectldtcnt(map);

        return cnt;
    }

    @Override
    public List<AnswertjBO> selectListBysubjectsId(String subjectsId) {

        return answerRoMapper.selectListBysubjectsId(subjectsId);
    }

}
