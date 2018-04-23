package com.abc12366.uc.service.gift.impl;


import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.model.bo.UCUserBO;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.*;
import com.abc12366.uc.mapper.db2.*;
import com.abc12366.uc.model.Dict;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.gift.*;
import com.abc12366.uc.model.gift.bo.*;
import com.abc12366.uc.service.MessageSendUtil;
import com.abc12366.uc.service.gift.GiftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 礼品服务
 *
 * @author lizhongwei
 * @date  2017-12-18 3:09 PM
 * @since 1.0.0
 */
@Service
public class GiftServiceImpl implements GiftService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiftServiceImpl.class);

    @Autowired
    private GiftMapper giftMapper;

    @Autowired
    private GiftRoMapper giftRoMapper;

    @Autowired
    private UamountLogMapper uamountLogMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GiftApplyMapper giftApplyMapper;

    @Autowired
    private GiftApplyRoMapper giftApplyRoMapper;

    @Autowired
    private UgiftApplyMapper ugiftApplyMapper;

    @Autowired
    private UgiftApplyRoMapper ugiftApplyRoMapper;

    @Autowired
    private UgiftLogMapper ugiftLogMapper;

    @Autowired
    private UgiftLogRoMapper ugiftLogRoMapper;

    @Autowired
    private DictRoMapper dictRoMapper;

    @Autowired
    private UamountLogRoMapper uamountLogRoMapper;

    @Autowired
    private MessageSendUtil messageSendUtil;

    @Override
    public List<Gift> selectList(Gift gift) {
        return giftRoMapper.selectList(gift);
    }

    @Override
    public GiftBO selectOne(Gift gift) {
        return giftRoMapper.selectOne(gift);
    }

    @Override
    public Gift insert(Gift gift) {
        gift.setId(Utils.uuid());
        Date date = new Date();
        gift.setCreateTime(date);
        gift.setLastUpdate(date);
        int insert = giftMapper.insert(gift);
        if (insert != 1) {
            LOGGER.info("新增礼物异常：{}", gift);
            throw new ServiceException(4101);
        }
        return gift;
    }

    @Override
    public GiftBO update(Gift gift) {
        Date date = new Date();
        gift.setLastUpdate(date);
        int update = giftMapper.update(gift);
        if (update != 1) {
            LOGGER.info("修改礼物异常：{}", gift);
            throw new ServiceException(4102);
        }
        GiftBO giftBO = new GiftBO();
        BeanUtils.copyProperties(gift, giftBO);
        return giftBO;
    }

    @Override
    public int delete(String id) {
        //查询礼物是否有被兑换
        List<GiftApply> list = giftApplyRoMapper.selectByGiftId(id);
        if (list != null && list.size() > 0) {
            LOGGER.info("礼物有被兑换，不能再进行删除：{}", id);
            throw new ServiceException(7010);
        }
        int delete = giftMapper.delete(id);
        if (delete != 1) {
            LOGGER.info("删除礼物异常：{}", id);
            throw new ServiceException(4103);
        }
        return delete;
    }

    @Override
    public Gift selectById(String id) {
        return giftRoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Gift selectGiftByGiftId(Map<String, Object> map) {
        return giftRoMapper.selectGiftByGiftId(map);
    }

    @Transactional(value = "db1TxManager", rollbackFor = {ServiceException.class, SQLException.class})
    @Override
    public void buyGift(Map<String, Object> map) {
        LOGGER.info("礼包申请：{}",map);
        //查询礼物信息
        String userId = (String) map.get("userId");
        UgiftApplyBO ugiftApply = (UgiftApplyBO)map.get("ugiftApply");
        List<GiftApplyBO> giftApplyList = ugiftApply.getGiftApplyBOList();
        for(GiftApplyBO giftApply : giftApplyList){
            Gift gift = giftRoMapper.selectByPrimaryKey(giftApply.getGiftId());
            if(gift == null){
                LOGGER.info("查询礼物异常：{}");
                throw new ServiceException(4104);
            }
            if(gift.getStatus() != null && !"2".equals(gift.getStatus())){
                LOGGER.info("会员礼包已经下架：{}");
                throw new ServiceException(7149);
            }
            int giftNum = giftApply.getGiftNum();
            if(giftNum <= 0){
                LOGGER.info("申请数量错误：{}", gift);
                throw new ServiceException(7148,"申请数量错误");
            }
            int stock = gift.getStock();
            int finalStock = stock - giftNum;
            //判断库存
            if(stock == 0 || finalStock < 0){
                LOGGER.info("礼物库存不足，请联系管理员：{}", gift);
                throw new ServiceException(7002);
            }

            //减去礼物库存
            gift.setStock(finalStock);
            int gUpdate = giftMapper.update(gift);
            if(gUpdate != 1){
                LOGGER.info("修改会员礼包异常：{}", gUpdate);
                throw new ServiceException(4102);
            }
            //状态：0-已拒绝，1-待处理，2-已审批，3-已发货，4-已完成
            Date date = new Date();
            //更新用户信息和礼包交易信息
            updateUserAmount(userId, gift.getSellingPrice()*giftNum,date,0);
            String applyId = getNo();
            UgiftApply apply = new UgiftApply();
            BeanUtils.copyProperties(ugiftApply,apply);
            apply.setApplyId(applyId);
            apply.setUserId(userId);
            apply.setCreateTime(date);
            apply.setLastUpdate(date);
            apply.setStatus("1");

            //新增会员礼包申请表
            int aInsert = ugiftApplyMapper.insert(apply);
            if(aInsert != 1){
                LOGGER.info("新增会员礼包申请异常：{}", aInsert);
                throw new ServiceException(4101);
            }
            //新增礼包申请表与礼包关联表
            GiftApply tempApply = new GiftApply();
            tempApply.setId(Utils.uuid());
            tempApply.setApplyId(applyId);
            tempApply.setGiftId(giftApply.getGiftId());
            tempApply.setGiftAmount(gift.getSellingPrice());
            tempApply.setGiftName(gift.getName());
            tempApply.setGiftNum(giftNum);
            LOGGER.info("新增礼包申请表与礼包关联表：{}", tempApply.toString());
            int inst = giftApplyMapper.insert(tempApply);
            if(inst != 1){
                LOGGER.info("新增礼包申请表与礼包关联表异常：{}", inst);
                throw new ServiceException(4101);
            }
            UCUserBO ucUserBO = Utils.getUserInfo();
            //加入礼包申请日志
            insertUgiftLog(applyId,ucUserBO.getId(),ucUserBO.getNickname(),ugiftApply.getRemark(),"1");
        }
    }

    @Override
    public void checkGiftBuy(GiftCheckBO giftCheckBO, HttpServletRequest request) {
        LOGGER.info("礼物申请审核:{}",giftCheckBO);
        com.abc12366.gateway.model.User user = Utils.getAdminInfo();
        //查找礼物申请信息
        UgiftApplyBO ugiftApplyBO = ugiftApplyRoMapper.selectByApplyId(giftCheckBO.getApplyId());
        if (ugiftApplyBO == null) {
            LOGGER.info("查询礼物申请异常");
            throw new ServiceException(7008);
        }
        if (ugiftApplyBO.getStatus() != null && !"1".equals(ugiftApplyBO.getStatus())) {
            LOGGER.info("只有待处理的礼物申请才能进行审核：{}", ugiftApplyBO);
            throw new ServiceException(7009);
        }
        UgiftApply ugiftApply = new UgiftApply();
        ugiftApply.setApplyId(giftCheckBO.getApplyId());
        ugiftApply.setLastUpdate(new Date());
        int status = giftCheckBO.getStatus();
        ugiftApply.setRemark(giftCheckBO.getRemark());
        String content;
        //审核状态：0：不通过，1：通过
        //礼包申请状态：0-已拒绝，1-待处理，2-已审批，3-已发货，4-已完成
        if (status == 0) {
            //修改礼包申请信息
            ugiftApply.setStatus("0");
            int ugUpdate = ugiftApplyMapper.update(ugiftApply);
            if (ugUpdate != 1) {
                LOGGER.info("修改礼物申请异常：{}", ugUpdate);
                throw new ServiceException(7006);
            }

            //加入礼包申请日志
            insertUgiftLog(giftCheckBO.getApplyId(), user.getId(), user.getNickname(), giftCheckBO.getRemark(), "0");

            //退还库存
            //查找礼包申请表与礼包关联信息，礼物信息
            List<GiftApplyBO> giftApplyBOList = ugiftApplyBO.getGiftApplyBOList();
            for(GiftApplyBO giftApplyBO:giftApplyBOList){
                Gift gift = giftRoMapper.selectByPrimaryKey(giftApplyBO.getGiftId());
                if(gift == null){
                    LOGGER.info("礼物信息查询异常：{}");
                    throw new ServiceException(7004);
                }
                gift.setStock(gift.getStock()+giftApplyBO.getGiftNum());
                int gUpdate = giftMapper.update(gift);
                if(gUpdate != 1){
                    LOGGER.info("礼物信息修改异常：{}", gUpdate);
                    throw new ServiceException(7005);
                }
                //退还用户礼品金额
                updateUserAmount(ugiftApplyBO.getUserId(), giftApplyBO.getGiftAmount(),new Date(),1);
            }
            content = "很抱歉！您的会员礼包申请未通过，礼包订单号："+ugiftApplyBO.getApplyId()+"，具体原因请至会员礼包申请详情里查询；";
            messageSendUtil.sendPhoneMessage(request,content,ugiftApplyBO.getPhone());
        }else if(status == 1){
            //修改礼包申请信息
            ugiftApply.setStatus("2");
            int ugUpdate = ugiftApplyMapper.update(ugiftApply);
            if (ugUpdate != 1) {
                LOGGER.info("修改礼物申请异常：{}", ugUpdate);
                throw new ServiceException(7006);
            }
            //加入礼包申请日志
            insertUgiftLog(giftCheckBO.getApplyId(), user.getId(), user.getNickname(), giftCheckBO.getRemark(), "2");

            content = "恭喜您！您的会员礼包申请已通过，礼包订单号：" + ugiftApplyBO.getApplyId() + "，具体原因请至会员礼包申请详情里查询；";
            messageSendUtil.sendPhoneMessage(request, content, ugiftApplyBO.getPhone());
        }

    }

    @Override
    public void sendGift(GiftSendBO giftSendBO) {
        //查找礼物申请信息
        UgiftApplyBO ugiftApplyBO = ugiftApplyRoMapper.selectByApplyId(giftSendBO.getApplyId());
        if (ugiftApplyBO == null) {
            LOGGER.info("查询礼物申请异常");
            throw new ServiceException(7008);
        }
        //礼包申请状态：0-已拒绝，1-待处理，2-已审批，3-已发货，4-已完成
        if (ugiftApplyBO.getStatus() != null && !"2".equals(ugiftApplyBO.getStatus())) {
            LOGGER.info("只有已审批的礼物申请才能进行发货：{}", ugiftApplyBO);
            throw new ServiceException(7011);
        }
        UgiftApply ugiftApply = new UgiftApply();
        ugiftApply.setApplyId(giftSendBO.getApplyId());
        ugiftApply.setExpressNo(giftSendBO.getExpressNo());
        ugiftApply.setExpressComp(giftSendBO.getExpressComp());
        ugiftApply.setLastUpdate(new Date());
        ugiftApply.setStatus("3");
        int update = ugiftApplyMapper.update(ugiftApply);
        if (update != 1) {
            LOGGER.info("礼物申请发货异常：{}", update);
            throw new ServiceException(7007);
        }

        // 加入礼包申请日志
        com.abc12366.gateway.model.User admin = Utils.getAdminInfo();
        insertUgiftLog(ugiftApplyBO.getApplyId(), admin.getId(), admin.getNickname(), giftSendBO.getRemark(), "3");
    }

    @Override
    public void receiveApply(String applyId) {
        UgiftApplyBO data = ugiftApplyRoMapper.selectByApplyId(applyId);
        if (!"3".equals(data.getStatus())) {
            LOGGER.warn("只有已审批的礼物申请才能进行发货:{}", applyId);
            throw new ServiceException(7012);
        }
        // 更新申请单状态
        UgiftApply updateBo = new UgiftApply();
        updateBo.setApplyId(data.getApplyId());
        updateBo.setStatus("4");
        updateBo.setLastUpdate(new Date());
        ugiftApplyMapper.update(updateBo);

        // 插入生申请单日志
        UCUserBO user = Utils.getUserInfo();
        insertUgiftLog(applyId, user.getId(), user.getNickname(), "用户完成收货", "4");
    }

    @Override
    public List<UgiftApplyBO> selectUgiftApplyList(Map<String, Object> map) {
        return ugiftApplyRoMapper.selectUgiftApplyList(map);
    }

    @Override
    public List<UamountLog> selectUamountLogList(Map<String, Object> map) {
        return uamountLogRoMapper.selectUamountLogList(map);
    }

    @Override
    public List<UgiftLog> selectApplyLogList(String applyId) {
        return ugiftLogRoMapper.selectListByApplyId(applyId);
    }

    @Override
    public UgiftApplyBO selectUgiftApplyBO(Map<String, Object> map) {
        return ugiftApplyRoMapper.selectUgiftApplyBO(map);
    }

    @Override
    public void automaticReceipt() {
        Date date = DateUtils.getAddDate(Constant.ORDER_RECEIPT_DAYS);
        //查询15天之前未确认的订单
        List<Gift> giftList = giftRoMapper.selectReceiptGiftByDate(date);
        for (Gift gift : giftList) {
            gift.setStatus("4");
            giftMapper.update(gift);
            insertUgiftLog(gift.getId(), "", "管理员", "系统自动确认收货", "4");
        }
    }

    @Override
    public List<Gift> selectAdminList(Gift gift) {
        return giftRoMapper.selectAdminList(gift);
    }

    @Override
    public Integer getGiftStatusCount(String status) {
        return ugiftApplyRoMapper.getGiftStatusCount(status);
    }

    /**
     * 获取礼物订单号
     *
     * @return 礼物订单号
     */
    private String getNo() {
        return DateUtils.getGiftIdString() + ugiftApplyMapper.getNo("lp_seq");
    }

    /**
     * 新增礼包申请日志
     *
     * @param applyId   申请单ID
     * @param adminId   管理员ID
     * @param adminName 管理员名称
     * @param remark    备注
     * @param status    状态
     */
    private void insertUgiftLog(String applyId, String adminId, String adminName, String remark, String status) {
        UgiftLog ugiftLog = new UgiftLog();
        ugiftLog.setId(Utils.uuid());
        ugiftLog.setApplyId(applyId);
        ugiftLog.setAction(selectFieldValue("giftStatus", status));
        ugiftLog.setCreateTime(new Date());
        ugiftLog.setAdminId(adminId);
        ugiftLog.setAdminName(adminName);
        ugiftLog.setRemark(remark);
        int insert = ugiftLogMapper.insert(ugiftLog);
        if (insert != 1) {
            LOGGER.info("新增礼包申请日志异常：{}", insert);
            throw new ServiceException(4101);
        }
    }

    /**
     * 查询字典信息
     *
     * @param fieldValue 字典ID
     * @param value      字典值
     * @return 字典名称
     */
    private String selectFieldValue(String fieldValue, String value) {
        Dict dict = new Dict();
        dict.setDictId(fieldValue);
        dict.setFieldValue(value);
        dict = dictRoMapper.selectOne(dict);
        return dict != null ? dict.getFieldKey() : "";
    }

    /**
     * 修改用户礼包金额
     *
     * @param userId       用户ID
     * @param sellingPrice 金额
     * @param date         时间
     * @param type         类型，0：减去，1：加上
     */
    private void updateUserAmount(String userId, double sellingPrice, Date date, int type) {
        User user = userMapper.selectOne(userId);
        double amount = 0;
        double userAmount = 0;
        if(user.getAmount() != null){
            userAmount = user.getAmount();
        }
        //礼物金额扣除
        UamountLog uamountLog = new UamountLog();
        uamountLog.setId(Utils.uuid());
        uamountLog.setCreateTime(date);
        uamountLog.setUserId(userId);
        uamountLog.setBusinessId(MessageConstant.HYLB_CODE);

        if (type == 0) {
            amount = userAmount - sellingPrice;
            if (amount < 0) {
                LOGGER.info("礼包金额不足：{}", amount);
                throw new ServiceException(7001);
            }
            uamountLog.setUsable(amount);
            uamountLog.setRemark("兑换礼品，使用金额");
            uamountLog.setOutgo(sellingPrice);
        } else if (type == 1) {
            amount = userAmount + sellingPrice;
            uamountLog.setUsable(amount);
            uamountLog.setRemark("礼品申请不通过，退还金额");
            uamountLog.setIncome(sellingPrice);
        }


        //加入会员礼包交易日志
        int uaInsert = uamountLogMapper.insert(uamountLog);
        if (uaInsert != 1) {
            LOGGER.info("新增会员礼包交易日志异常：{}", uaInsert);
            throw new ServiceException(4101);
        }
        //计算消费后的礼包金额

        user.setAmount(amount);
        int uInsert = userMapper.update(user);
        if (uInsert != 1) {
            LOGGER.info("修改会员礼包金额异常：{}", uInsert);
            throw new ServiceException(4102);
        }
    }

}
