package com.abc12366.uc.service.invoice.impl;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.*;
import com.abc12366.uc.mapper.db1.*;
import com.abc12366.uc.mapper.db2.*;
import com.abc12366.uc.model.Dict;
import com.abc12366.uc.model.Message;
import com.abc12366.uc.model.MessageSendBo;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.dzfp.DzfpGetReq;
import com.abc12366.uc.model.dzfp.Einvocie;
import com.abc12366.uc.model.dzfp.InvoiceXm;
import com.abc12366.uc.model.invoice.*;
import com.abc12366.uc.model.invoice.bo.*;
import com.abc12366.uc.model.order.ExpressComp;
import com.abc12366.uc.model.order.Order;
import com.abc12366.uc.model.order.OrderExchange;
import com.abc12366.uc.model.order.OrderInvoice;
import com.abc12366.uc.model.order.bo.OrderBO;
import com.abc12366.uc.model.order.bo.OrderProductBO;
import com.abc12366.uc.model.order.bo.UserAddressBO;
import com.abc12366.uc.model.weixin.bo.redpack.WxRedEnvelopBO;
import com.abc12366.uc.service.IActivityService;
import com.abc12366.uc.service.IMsgSendV2service;
import com.abc12366.uc.service.MessageSendUtil;
import com.abc12366.uc.service.invoice.InvoiceService;
import com.abc12366.uc.util.CharUtil;
import com.abc12366.uc.webservice.DzfpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;

/**
 * @create 2017-05-15 10:17 AM
 * @since 1.0.0
 */
@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Autowired
    private InvoiceRoMapper invoiceRoMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRoMapper orderRoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderInvoiceMapper orderInvoiceMapper;

    @Autowired
    private OrderInvoiceRoMapper orderInvoiceRoMapper;

    @Autowired
    private InvoiceDetailRoMapper invoiceDetailRoMapper;

    @Autowired
    private InvoiceDetailMapper invoiceDetailMapper;

    @Autowired
    private InvoiceBackMapper invoiceBackMapper;

    @Autowired
    private InvoiceBackRoMapper invoiceBackRoMapper;

    @Autowired
    private ExpressRoMapper expressRoMapper;

    @Autowired
    private InvoiceLogMapper invoiceLogMapper;

    @Autowired
    private OrderExchangeRoMapper orderExchangeRoMapper;

    @Autowired
    private ExpressCompRoMapper expressCompRoMapper;

    @Autowired
    private MessageSendUtil messageSendUtil;

    @Autowired
    private UserAddressRoMapper userAddressRoMapper;

    @Autowired
    private DictRoMapper dictRoMapper;

    @Autowired
    private IActivityService iActivityService;

    @Autowired
    private DzfpRoMapper dzfpRoMapper;
    
    @Autowired
	private IMsgSendV2service msgSendV2Service;

    @Override
    public List<InvoiceBO> selectList(InvoiceBO invoice) {
        return invoiceRoMapper.selectList(invoice);
    }

    /**
     * 查询单个订单
     */
    @Override
    public InvoiceBO selectOne(String id) {
        return invoiceRoMapper.selectById(id);
    }


    /**
     * 修改发票申请
     */
    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public InvoiceBO updateInvoice(InvoiceBO invoiceBO) {
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceBO, invoice);
        Date date = new Date();
        invoice.setLastUpdate(date);
        invoiceMapper.update(invoice);

        String id = invoice.getId();

        List<OrderInvoice> orderInvoiceList = orderInvoiceRoMapper.selectByInvoiceId(id);
        if (orderInvoiceList != null && (!orderInvoiceList.isEmpty())) {
            for (OrderInvoice orderInvoice : orderInvoiceList) {
                orderInvoiceMapper.delete(orderInvoice.getId());
            }
        }

        String[] orderNos = invoiceBO.getOrderNos();
        OrderInvoice orderInvoice = new OrderInvoice();
        for (String orderId : orderNos) {
            orderInvoice.setId(Utils.uuid());
            orderInvoice.setInvoiceId(id);
            orderInvoice.setOrderNo(orderId);
            orderInvoice.setLastUpdate(date);
            orderInvoiceMapper.insert(orderInvoice);
        }
        InvoiceBO temp = new InvoiceBO();
        BeanUtils.copyProperties(invoice, temp);
        return temp;
    }

    /**
     * 根据订单ID和用户ID删除发票订单
     */
    @Override
    public int deleteByIdAndUserId(InvoiceBO invoiceBO) {
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceBO, invoice);
        int del = invoiceMapper.deleteByIdAndUserId(invoice);
        if (del != 1) {
            throw new ServiceException(4103);
        }
        return del;
    }

    /**
     * 索要发票
     */
    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public InvoiceBO addInvoice(InvoiceBO invoiceBO) {

        String invoiceId = DateUtils.getInvoiceOrderString();
        invoiceBO.setId(invoiceId);
        Date date = new Date();
        invoiceBO.setCreateTime(date);
        invoiceBO.setLastUpdate(date);

        String[] orderNos = invoiceBO.getOrderNos();
        OrderInvoice orderInvoice = new OrderInvoice();
        Double amount = 0.0;
        for (String orderNo : orderNos) {
            orderInvoice.setId(Utils.uuid());
            orderInvoice.setInvoiceId(invoiceId);
            orderInvoice.setOrderNo(orderNo);
            orderInvoice.setCreateTime(date);
            orderInvoice.setLastUpdate(date);
            OrderInvoice oInvoice = orderInvoiceRoMapper.selectByOrderNo(orderNo);
            if (oInvoice != null) {
                throw new ServiceException(4199, "已开发票");
            }
            OrderExchange orderExchange = orderExchangeRoMapper.selectByOrderNo(orderNo);
            if (orderExchange != null) {
                LOGGER.info("订单在退换货中，不能开发票：{}", orderExchange);
                throw new ServiceException(4918);
            }

            int oInsert = orderInvoiceMapper.insert(orderInvoice);
            if (oInsert != 1) {
                LOGGER.info("新增失败：{}", orderInvoice);
                throw new ServiceException(4101);
            }
            //修改订单是否已开发票状态
            Order order = new Order();
            order.setIsInvoice(true);
            order.setUserId(invoiceBO.getUserId());
            order.setOrderNo(orderNo);
            int oUpd = orderMapper.update(order);
            if (oUpd != 1) {
                LOGGER.info("修改失败：{}", order);
                throw new ServiceException(4102);
            }
            OrderBO orderBO = orderRoMapper.selectById(orderNo);
            if (orderBO != null) {
                amount = amount + orderBO.getTotalPrice();
            }
        }
        invoiceBO.setAmount(amount);

        //查询地址信息，纸质发票时才查询地址
        if (invoiceBO.getProperty() != null && "1".equals(invoiceBO.getProperty())) {
            if (invoiceBO.getAddressId() != null && !"".equals(invoiceBO.getAddressId())) {
                UserAddressBO userAddress = userAddressRoMapper.selectById(invoiceBO.getAddressId());
                if (userAddress != null) {
                    String address = (userAddress.getProvinceName() + "-") +
                            userAddress.getCityName() + "-" +
                            userAddress.getAreaName() + "-" +
                            userAddress.getDetail();
                    invoiceBO.setConsignee(userAddress.getName());
                    invoiceBO.setContactNumber(userAddress.getPhone());
                    invoiceBO.setShippingAddress(address);
                }
            }
        }
        //发票抬头为个人时，去掉纳税人识别号等数据
        if (invoiceBO.getName() != null && "1".equals(invoiceBO.getName())) {
            invoiceBO.setNsrsbh(null);
            invoiceBO.setNsrmc(null);
            invoiceBO.setAddress(null);
            invoiceBO.setPhone(null);
            invoiceBO.setBank(null);
        }
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceBO, invoice);
        int ins = invoiceMapper.insert(invoice);

        if (ins != 1) {
            LOGGER.info("新增失败：{}", invoice);
            throw new ServiceException(4101);
        }
        insertInvoiceLog(invoiceId, invoiceBO.getUserId(), "索要发票");

        return invoiceBO;
    }

    private void insertInvoiceLog(String invoiceId, String userId, String opertion) {
        //加入发票操作日志
        InvoiceLog invoiceLog = new InvoiceLog();
        invoiceLog.setId(Utils.uuid());
        invoiceLog.setCreateTime(new Date());
        invoiceLog.setAction(opertion);
        invoiceLog.setInvoiceId(invoiceId);
        invoiceLog.setCreateUser(userId);
        int logInsert = invoiceLogMapper.insert(invoiceLog);
        if (logInsert != 1) {
            LOGGER.info("新增发票操作日志失败：{}", invoiceLog);
            throw new ServiceException(4185);
        }
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public List<InvoiceExcel> selectInvoicePrintExcelList(InvoiceBO invoice) {
        invoice.setStatus("2");
        List<InvoiceBO> invoiceBOList = invoiceRoMapper.selectList(invoice);
        List<InvoiceExcel> excelList = new ArrayList<>();
        if (invoiceBOList != null) {
            InvoiceExcel excel;
            for (InvoiceBO bo : invoiceBOList) {
                excel = new InvoiceExcel();
                excel.setInvoiceOrderNo(bo.getId());
                excel.setNsrsbh(bo.getNsrsbh());
                if ("1".equals(bo.getName())) {
                    excel.setNsrmc("个人");
                } else {
                    excel.setNsrmc(bo.getNsrmc());
                }

                excel.setCreateTime(DateUtils.dateToString(bo.getCreateTime()));
                excel.setContent(bo.getContentDetail());
                excel.setNum(1);
                excel.setAmount(bo.getAmount());
                excel.setAmountBig(bo.getAmount());
                excel.setAmountSmall(bo.getAmount());
                excel.setDrawer(Utils.getAdminInfo().getNickname());
                List<OrderBO> orderBOList = bo.getOrderBOList();
                StringBuilder remark = new StringBuilder();
                for (OrderBO orderBO : orderBOList) {
                    remark.append("订单号：").append(orderBO.getOrderNo());
                    remark.append("  ");
                    remark.append("支付方式：");
                    if ("WEIXIN".equals(orderBO.getPayMethod())) {
                        remark.append("微信");
                    } else if ("ALIPAY".equals(orderBO.getPayMethod())) {
                        remark.append("支付宝");
                    } else if ("POINTS".equals(orderBO.getPayMethod())) {
                        remark.append("积分");
                    }

                }
                excel.setRemark(remark.toString());
                excelList.add(excel);
            }
        }
        return excelList;
    }

    @Override
    public List<InvoiceExpressExcel> selectInvoiceExpressExcelList(InvoiceBO invoice) {
        //发票申请状态，1：待审批，2：已审批，3：已拒绝，4：已发货，5：已退票，6：已收货，7：待发货
        invoice.setStatus("7");
        List<InvoiceBO> invoiceBOList = invoiceRoMapper.selectList(invoice);
        List<InvoiceBO> invoiceDatas = invoiceRoMapper.selectList(invoice);
        List<InvoiceExpressExcel> excelList = new ArrayList<>();
        if (invoiceBOList != null) {
            InvoiceExpressExcel excel;
            for (InvoiceBO bo : invoiceBOList) {
                excel = new InvoiceExpressExcel();
                excel.setInvoiceOrderNo(bo.getId());
                //收货地址
                String address = bo.getShippingAddress();
                String phone = bo.getContactNumber();
                String linkman = bo.getConsignee();

                excel.setPhone(phone);
                excel.setAddress(address);
                boolean isAlike = false;
                StringBuilder invoiceNos = new StringBuilder();
                int num = 0;
                if (address != null && !"".equals(address) && phone != null && !"".equals(phone)) {
                    for (InvoiceBO data : invoiceDatas) {
                        String addressBuffer = data.getShippingAddress();
                        String phoneTemp = data.getContactNumber();
                        // 有地址和电话相同的
                        if (address.equals(addressBuffer) && phone.equals(phoneTemp)) {
                            isAlike = true;
                            //寄托货物信息合并
                            invoiceNos.append(data.getInvoiceNo());
                            invoiceNos.append(";");
                            num++;
                        }
                    }
                    //没有地址和电话相同的
                    if (!isAlike) {
                        //寄托货物
                        invoiceNos.append(bo.getInvoiceNo());
                        invoiceNos.append(";");
                    }
                }
                excel.setLinkman(linkman);
                excel.setCargoContent(invoiceNos.toString());
                excel.setContent(bo.getInvoiceNo());
                //寄托数量
                excel.setCargoNum(num);
                excel.setReceivingCompany(bo.getNsrmc());
                excelList.add(excel);
            }
        }
        return excelList;
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public void insertInvoiceExpressExcelList(List<InvoiceExpressExcel> expressExcelList, String expressCompId,
                                              HttpServletRequest request) {
        for (InvoiceExpressExcel expressExcel : expressExcelList) {
            Invoice invoice = new Invoice();
            invoice.setStatus("4");
            invoice.setId(expressExcel.getInvoiceOrderNo());
            if (expressExcel.getWaybillNum() != null && CharUtil.isChinese(expressExcel.getWaybillNum())) {
                LOGGER.warn("运单号不能存在中文：{}", expressExcel);
                throw new ServiceException(4102, expressExcel.getWaybillNum() + "运单号不能存在中文");
            }
            invoice.setWaybillNum(expressExcel.getWaybillNum());
            invoice.setExpressCompId(expressCompId);
            Invoice ce = new Invoice();
            ce.setId(expressExcel.getInvoiceOrderNo());
            ce.setStatus("7");
            //查询发票信息表状态
            Invoice invoiceTemp = invoiceRoMapper.selectByInvoiceOrderNo(ce);
            if (invoiceTemp == null) {
                LOGGER.info("发票不存在或发票已被使用：{}", expressExcel);
                throw new ServiceException(4913, "只有在已开票状态，该张发票才能被导入" + expressExcel.getInvoiceOrderNo());
            }

            int update = invoiceMapper.update(invoice);
            if (update != 1) {
                LOGGER.info("修改失败：{}", invoice);
                throw new ServiceException(4102);
            }

            //User user = userMapper.selectOne(invoiceTemp.getUserId());

            //发送消息
            ExpressComp expressComp = expressCompRoMapper.selectByPrimaryKey(expressCompId);
            if (expressComp == null) {
                LOGGER.warn("物流公司查询失败：{}", invoiceTemp.getExpressCompId());
                throw new ServiceException(4102, "物流公司查询失败");
            }
            //Message message = new Message();
            //message.setBusinessId(invoiceTemp.getId());
            //message.setBusiType(MessageConstant.ZZFPDD);
            //message.setType(MessageConstant.SYS_MESSAGE);
            String content = RemindConstant.IMPORT_COURIER_INFO.replaceAll("\\{#DATA.INVOICE\\}",
                    invoiceTemp.getId()).replaceAll("\\{#DATA.COMP\\}",
                    expressComp.getCompName()).replaceAll("\\{#DATA.EXPRESSNO\\}", expressExcel.getWaybillNum());
            //message.setUrl("<a href=\"" + SpringCtxHolder.getProperty("abc12366.api.url.uc") +
            //       "/userinfo/invoice/" + invoiceTemp.getId() + "\">" + MessageConstant.VIEW_DETAILS + "</a>");
            //message.setContent(content);
            //message.setUserId(invoiceTemp.getUserId());

            Map<String, String> map = new HashMap<>();
            //map.put("userId", user.getId());
            //map.put("openId", user.getWxopenid());
            map.put("first", "您好，您的订单已送出，请保持手机畅通，以便快递及时联系您！");
            map.put("remark", "感谢您的使用。");
            map.put("keyword1", invoiceTemp.getConsignee());
            map.put("keyword2", invoiceTemp.getContactNumber());
            map.put("keyword3", expressComp.getCompName());
            map.put("keyword4", expressExcel.getWaybillNum());
            map.put("keyword5", invoiceTemp.getInvoiceNo());
            String templateId = "lPhC6mRjGPBGSTq14Gwimpu61tvUA25OfmpxO4L8tas";
            //messageSendUtil.sendMsg(request, user, message, map, templateId);
            
            MessageSendBo messageSendBo =new MessageSendBo();
            messageSendBo.setType(MessageConstant.SYS_MESSAGE);
            messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_INVOICE);
            messageSendBo.setBusinessId(invoiceTemp.getId());
            messageSendBo.setSkipUrl("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") +
                           "/userinfo/invoice/" + invoiceTemp.getId() + "'>" + MessageConstant.VIEW_DETAILS + "</a>");
            messageSendBo.setWebMsg(content);
            messageSendBo.setPhoneMsg(content);
            messageSendBo.setTemplateid(templateId);
            messageSendBo.setDataList(map);
            messageSendBo.setWxNoChargeVip(true);
            messageSendBo.setMoblieNoChargeVip(true);
            
            List<String> userIds =new ArrayList<String>();
            userIds.add(invoiceTemp.getUserId());
            messageSendBo.setUserIds(userIds);
            
            msgSendV2Service.sendMsgV2(messageSendBo);

        }
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public void insertInvoicePrintExcelList(List<InvoiceExcel> invoiceList) {
        for (InvoiceExcel invoiceExcel : invoiceList) {
            InvoiceDetail temp = new InvoiceDetail();
            temp.setInvoiceNo(invoiceExcel.getInvoiceNo());
            temp.setInvoiceCode(invoiceExcel.getInvoiceCode());
            InvoiceDetail invoiceDetail = invoiceDetailRoMapper.selectByInvoiceNoAndCode(temp);
            if (invoiceDetail == null) {
                LOGGER.info("发票号码或发票代码不存在");
                throw new ServiceException(4913, "发票号码或发票代码不存在");
            }
            if (!"3".equals(invoiceDetail.getStatus())) {
                throw new ServiceException(4913, "发票号码：" + invoiceExcel.getInvoiceNo() + " 未签收。发票只有在已签收后才能被使用");
            }
            Invoice ce = new Invoice();
            ce.setId(invoiceExcel.getInvoiceOrderNo());
            ce.setStatus("2");
            //查询发票信息表状态
            InvoiceBO invoiceTemp = invoiceRoMapper.selectInvoice(ce);
            if (invoiceTemp == null) {
                LOGGER.info("只有在已审批状态，该张发票才能被导入：{}", invoiceDetail);
                throw new ServiceException(4913, "只有在已审批状态，该张发票才能被导入" + invoiceExcel.getInvoiceOrderNo());
            }
            String type = invoiceTemp.getType();
            String invoiceRepoId = invoiceDetail.getInvoiceRepoId();
            if (type != null && !invoiceRepoId.contains(type)) {
                LOGGER.info("发票导入信息与发票订单信息的发票种类不一致：{}", invoiceDetail);
                throw new ServiceException(4913, "发票导入信息与发票订单信息的发票种类不一致：" + invoiceExcel.getInvoiceOrderNo());
            }

            Invoice invoice = new Invoice();
            invoice.setStatus("7");
            invoice.setId(invoiceExcel.getInvoiceOrderNo());
            invoice.setInvoiceNo(invoiceDetail.getInvoiceNo());
            invoice.setInvoiceCode(invoiceDetail.getInvoiceCode());
            try {
                int update = invoiceMapper.update(invoice);
                if (update != 1) {
                    LOGGER.info("修改失败：{}", invoice);
                    throw new ServiceException(4102);
                }
            } catch (Exception e) {
                LOGGER.info("SQL执行异常：{}", invoice);
                throw new ServiceException(4900);
            }
            //修改发票详情表
            List<OrderBO> orderBOList = invoiceTemp.getOrderBOList();
            StringBuilder remark = new StringBuilder();
            for (OrderBO orderBO : orderBOList) {
                remark.append(orderBO.getOrderNo());
                remark.append("  ");
            }
            invoiceDetail.setStatus("2");
            invoiceDetail.setRemark(remark.toString());
            int dUpdate = invoiceDetailMapper.update(invoiceDetail);
            if (dUpdate != 1) {
                throw new ServiceException(4102, "修改发票详情失败");
            }
        }
    }

    @Override
    public void confirmInvoice(Invoice invoice) {
        Invoice data = invoiceRoMapper.selectByIdAndUserId(invoice);
        if (data != null && !"4".equals(data.getStatus())) {
            LOGGER.info("发票订单只有在已经发货的情况下，才能确认收货：{}", invoice);
            throw new ServiceException(4102, "发票订单只有在已经发货的情况下，才能确认收货");
        }
        int update = invoiceMapper.update(invoice);
        if (update != 1) {
            LOGGER.info("确认收货失败：{}", invoice);
            throw new ServiceException(4102, "确认收货失败");
        }
    }

    @Override
    public void automaticReceiptInvoice() {
        Date date = DateUtils.getAddDate(Constant.ORDER_RECEIPT_DAYS);
        //查询15天之前未确认的订单
        List<Invoice> orderList = invoiceRoMapper.selectReceiptInvoiceByDate(date);
        for (Invoice invoice : orderList) {
            invoice.setStatus("5");
            invoiceMapper.update(invoice);
            insertInvoiceLog(invoice.getId(), invoice.getUserId(), "系统自动确认收货");

            //发送消息
            /*Message message = new Message();
            message.setBusinessId(invoice.getOrderNo());
            message.setType(MessageConstant.SPDD);
            message.setContent(MessageConstant.AUTOMATIC_CONFIRMATION_RECEIPT+"<a href=\""+MessageConstant
            .ABCUC_URL+"/orderDetail/"+invoice.getOrderNo()+"\">"+invoice.getOrderNo()+"</a>");
            message.setUserId(invoice.getUserId());
            messageSendUtil.sendMessage(message);*/
        }
    }

    @Override
    public Integer selectTodoListCount() {
        return invoiceRoMapper.selectTodoListCount();
    }

    @Transactional(value = "db1TxManager", rollbackFor = {ServiceException.class, SQLException.class})
    @Override
    public void invoiceCheck() {
        Einvocie einvocie = new Einvocie();
        einvocie.setTBSTATUS("0");
        List<Einvocie> list = dzfpRoMapper.selectList(einvocie);
        for (Einvocie data : list) {
            //更新发票库存信息
            InvoiceDetail tail = new InvoiceDetail();
            tail.setInvoiceNo(data.getFP_HM());
            tail.setInvoiceCode(data.getFP_DM());
            tail.setSpUrl(data.getSP_URL());
            tail.setPdfUrl(data.getPDF_URL());
            //根据发票号码和发票代码查找发票详细信息表
            InvoiceDetail detail = invoiceDetailRoMapper.selectByInvoiceNoAndCode(tail);
            if (detail == null) {
                LOGGER.info("发票号码为XXX的未找到库存，请入库后再进行同步");
                throw new ServiceException(4186, "发票号码为" + data.getFP_HM() + "的未找到库存，请入库后再进行同步");
            }
            if (detail.getStatus() != null && !"3".equals(detail.getStatus())) {
                LOGGER.info("发票详情信息未签收，请签收后再进行同步：{}", tail);
                throw new ServiceException(4187, "发票号码为" + data.getFP_HM() + "的未领用完成，请签收后再进行同步");
            }

            //更新电子发票开票日志信息
            data.setTBSTATUS("1");

            int dz = invoiceDetailMapper.updateDZFP(data);
            if (dz != 1) {
                LOGGER.info("更新电子发票开票日志信息失败：{}", tail);
                throw new ServiceException(4187, "更新电子发票开票日志信息失败");
            }

            tail.setId(detail.getId());
            tail.setLastUpdate(new Date());
            tail.setStatus("2");
            int dUpdate = invoiceDetailMapper.update(tail);
            if (dUpdate != 1) {
                LOGGER.info("发票详情信息修改失败：{}", tail);
                throw new ServiceException(4187);
            }


        }
    }

    @Transactional(value = "db1TxManager")
    @Override
    public void invalid(InvoiceInvalidBO invoiceInvalidBO, HttpServletRequest request) {
        InvoiceBO invoiceBO = invoiceRoMapper.selectById(invoiceInvalidBO.getId());
        if(invoiceBO == null){
            throw new ServiceException(4104);
        }
        updateInvoiceAndOrder(invoiceInvalidBO.getId());
        //作废类型，0：发票订单，1：发票订单和发票
        if(invoiceInvalidBO.getType() == 1 && "2".equals(invoiceInvalidBO.getProperty())){
            invoiceCancel(invoiceInvalidBO.getId());
        }
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceBO,invoice);
//        invoice.setRemark(invoiceCheckBO.getRemark());
        invoice.setStatus("8");
        invoice.setLastUpdate(new Date());
        int update = invoiceMapper.update(invoice);
        if (update != 1) {
            LOGGER.info("修改失败：{}", invoice);
            throw new ServiceException(4102);
        }
    }

    /**
     * 发票作废
     */
    public void invoiceCancel(String invoiceId) {
        // 发票作废
        InvoiceBO invoiceBO = invoiceRoMapper.selectById(invoiceId);
        if (invoiceBO != null) {

            DzfpGetReq req = new DzfpGetReq();
            List<InvoiceXm> dataList = new ArrayList<>();
            if ("1".equals(invoiceBO.getProperty())) { // 纸质发票
                LOGGER.info("该会员订购已开具纸质发票，不能退订：{}");
                throw new ServiceException(4102, "该会员订购已开具纸质发票，不能退订");
            } else if ("2".equals(invoiceBO.getProperty())) { // 电子发票
                if ("1".equals(invoiceBO.getName())) { // 个人发票
                    req.setGmf_mc("个人");
                } else {
                    req.setGmf_mc(invoiceBO.getNsrmc());
                    req.setGmf_nsrsbh(invoiceBO.getNsrsbh());
                    req.setGmf_dzdh(invoiceBO.getAddress());
                    req.setGmf_yhzh(invoiceBO.getBank());
                    req.setGmf_sjh(invoiceBO.getPhone());
                }
                req.setFpqqlsh(DateUtils.getFPQQLSH());
                req.setKplx("1");
                req.setZsfs("0");
                req.setKpr(Utils.getAdminInfo().getNickname());
                req.setHylx("0");
                req.setYfp_dm(invoiceBO.getInvoiceCode());
                req.setYfp_hm(invoiceBO.getInvoiceNo());
                req.setGmf_dzyx(invoiceBO.getEmail());

                List<OrderBO> orderBOs = invoiceBO.getOrderBOList();
                StringBuilder buffer = new StringBuilder();
                if (orderBOs != null && orderBOs.size() > 0) {
                    for (OrderBO orderBO : orderBOs) {
                        List<OrderProductBO> productBOs = orderBO.getOrderProductBOList();
                        if (productBOs != null && productBOs.size() > 0) {
                            for (OrderProductBO pBO : productBOs) {
                                if (pBO.getTradingChannels() != null && "CSKT".equals(pBO.getTradingChannels())) {
                                    buffer.append("培训课程,");
                                } else {
                                    buffer.append(pBO.getName());
                                    buffer.append(",");
                                }
                            }
                        }
                    }
                    if (buffer.length() > 0) {
                        req.setBz(buffer.deleteCharAt(buffer.length() - 1).toString());
                    }
                }

                InvoiceXm xm = new InvoiceXm();
                xm.setFphxz("0");
                xm.setXmmc(selectFieldValue("invoicecontent", invoiceBO.getContent()));
                xm.setXmsl(-1.00);
                xm.setTotalAmt(-invoiceBO.getAmount());
                dataList.add(xm);

                req.setInvoiceXms(dataList);
            }
            Einvocie einvocie = null;
            try {
                einvocie = (Einvocie) DzfpClient.doSender("DFXJ1001", req.tosendXml(), Einvocie.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (einvocie != null) {

                if ("0000".equals(einvocie.getReturnCode())) { // 更新作废状态
                    InvoiceDetail id = invoiceDetailRoMapper.selectByInvoiceNo(invoiceBO.getInvoiceNo());
//                    if (id == null) {
//                        throw new ServiceException(4102, "查找发票详情错误");
//                    }
                    if (id != null) {
                        id.setStatus("3");
                        id.setLastUpdate(new Date());
                        id.setSpUrl(einvocie.getSP_URL());
                        id.setPdfUrl(einvocie.getPDF_URL());
                        invoiceDetailMapper.update(id);
                    }
                } else {
                    throw new ServiceException(einvocie.getReturnCode(), einvocie.getReturnMessage());
                }
            }
        }
    }

    private String selectFieldValue(String fieldValue, String value) {
        Dict dict = new Dict();
        dict.setDictId(fieldValue);
        dict.setFieldValue(value);
        dict = dictRoMapper.selectOne(dict);
        return dict != null ? dict.getFieldKey() : "";
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public InvoiceBackBO refund(InvoiceBackBO invoiceBackBO) {
        invoiceBackBO.setId(Utils.uuid());
        Date date = new Date();
        invoiceBackBO.setCreateTime(date);
        invoiceBackBO.setLastUpdate(date);
        //状态1，待审批
        invoiceBackBO.setStatus("1");
        InvoiceBack invoiceBack = new InvoiceBack();
        BeanUtils.copyProperties(invoiceBackBO, invoiceBack);
        int insert = invoiceBackMapper.insert(invoiceBack);
        if (insert != 1) {
            LOGGER.info("新增失败：{}", invoiceBack);
            throw new ServiceException(4101);
        }

        insertInvoiceLog(invoiceBackBO.getInvoiceId(), invoiceBackBO.getUserId(), "发票退票");
        return invoiceBackBO;
    }

    @Transactional(value = "db1TxManager", rollbackFor = {SQLException.class, ServiceException.class})
    @Override
    public InvoiceBackBO refundCheck(InvoiceBack invoiceBack) {
        Express express = expressRoMapper.selectByPrimaryKey(invoiceBack.getExpressId());
        if (express == null) {
            LOGGER.info("发票快递单信息查询错误：{}", invoiceBack);
            throw new ServiceException(4145);
        }
        Invoice temp = new Invoice();
        temp.setId(express.getInvoiceOrderNo());
        Invoice invoice = invoiceRoMapper.selectByInvoiceOrderNo(temp);
        if (invoice == null) {
            LOGGER.info("发票信息查询错误：{}", invoiceBack);
            throw new ServiceException(4146);
        } else {
            //修改发票状态
            int iUpdate = invoiceMapper.update(invoice);
            if (iUpdate != 1) {
                LOGGER.info("发票信息修改错误：{}", invoice);
                throw new ServiceException(4147);
            }
        }
        //退票状态=4：已收货时，修改订单状态
        if (invoiceBack.getStatus() != null && "4".equals(invoiceBack.getStatus())) {

            List<OrderInvoice> orderInvoiceList = orderInvoiceRoMapper.selectByInvoiceId(invoice.getId());
            Order order;
            for (OrderInvoice orderInvoice : orderInvoiceList) {
                order = new Order();
                order.setOrderNo(orderInvoice.getOrderNo());
                order.setIsInvoice(false);
                order.setLastUpdate(new Date());
                int oUpdate = orderMapper.update(order);
                if (oUpdate != 1) {
                    LOGGER.info("订单信息修改错误：{}", order);
                    throw new ServiceException(4148);
                }
            }
            //修改订单和发票对应关系
            orderInvoiceMapper.updateByInvoiceId(invoice.getId());

        }
        invoiceBack.setLastUpdate(new Date());
        int bUpdate = invoiceBackMapper.update(invoiceBack);
        if (bUpdate != 1) {
            LOGGER.info("发票退订信息修改错误：{}", invoiceBack);
            throw new ServiceException(4149);
        }
        InvoiceBackBO bo = new InvoiceBackBO();
        BeanUtils.copyProperties(invoice, bo);
        insertInvoiceLog(invoiceBack.getInvoiceId(), invoiceBack.getUserId(), "发票审核");
        return bo;
    }

    @Override
    public List<InvoiceBackBO> selectBOList(InvoiceBackBO invoiceBackBO) {
        return invoiceBackRoMapper.selectBOList(invoiceBackBO);
    }

    @Override
    public InvoiceBackBO selectBackOne(String id) {
        return invoiceBackRoMapper.selectBackOne(id);
    }

    @Override
    public InvoiceBO selectUserInvoice(Invoice invoice) {
        return invoiceRoMapper.selectUserInvoice(invoice);
    }

    @Transactional(value = "db1TxManager", rollbackFor = {ServiceException.class, SQLException.class})
    @Override
    public void billing(InvoiceCheckBO invoiceCheckBO, HttpServletRequest request) {
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceCheckBO, invoice);
        String invoiceNo = null;
        String invoiceCode = null;
        //是否通过
        InvoiceBO invoiceBO = invoiceRoMapper.selectById(invoiceCheckBO.getId());
        if (invoiceCheckBO.getIsBilling()) {
            //1：纸质发票，2：电子发票；电子发票直接把发票信息新增
            if (invoiceBO.getProperty() != null && "2".equals(invoiceBO.getProperty())) {

                String id = invoiceCheckBO.getId();
                String fpqqlsh = id.substring(5, id.length());
                Einvocie einvocie = dzfpRoMapper.selectOne(fpqqlsh);
                if (einvocie == null) {
                    //发票信息填充
                    DzfpGetReq dzfpGetReq = new DzfpGetReq();

                    dzfpGetReq.setFpqqlsh(fpqqlsh);
                    dzfpGetReq.setZsfs("0");
                    //开票0，退票1
                    dzfpGetReq.setKplx("0");
                    dzfpGetReq.setKpr(Utils.getAdminInfo().getNickname());

                    if ("1".equals(invoiceBO.getName())) {
                        dzfpGetReq.setGmf_mc("个人");
                    } else if ("2".equals(invoiceBO.getName())) {
                        dzfpGetReq.setGmf_mc(invoiceBO.getNsrmc());
                        dzfpGetReq.setGmf_nsrsbh(invoiceBO.getNsrsbh());
                    }
                    dzfpGetReq.setGmf_dzyx(invoiceBO.getEmail());

                    List<InvoiceXm> invoiceXmList = new ArrayList<>();
                    List<OrderBO> orderBOs = invoiceBO.getOrderBOList();
                    StringBuilder buffer = new StringBuilder();
                    if (orderBOs != null && orderBOs.size() > 0) {
                        for (OrderBO orderBO : orderBOs) {
                            InvoiceXm invoiceXm = new InvoiceXm();
                            invoiceXm.setXmmc(invoiceBO.getContentDetail());
                            //商品编码
//                            invoiceXm.setSpbm("3040201000000000000");
                            //价格
                            invoiceXm.setTotalAmt(orderBO.getTotalPrice());
                            //数量
                            invoiceXm.setXmsl(1d);
                            invoiceXm.setFphxz("0");
                            invoiceXm.setYhzcbs("0");
                            invoiceXmList.add(invoiceXm);

                            List<OrderProductBO> productBOs = orderBO.getOrderProductBOList();
                            if (productBOs != null && productBOs.size() > 0) {
                                for (OrderProductBO pBO : productBOs) {
                                    if (pBO.getTradingChannels() != null && "CSKT".equals(pBO.getTradingChannels())) {
                                        buffer.append("培训课程,");
                                    } else {
                                        buffer.append(pBO.getName());
                                        buffer.append(",");
                                    }
                                }
                            }
                        }
                        if (buffer.length() > 0) {
                            dzfpGetReq.setBz(buffer.deleteCharAt(buffer.length() - 1).toString());
                        }
                    }
                    dzfpGetReq.setInvoiceXms(invoiceXmList);
                    try {
                        einvocie = (Einvocie) DzfpClient.doSender("DFXJ1001", dzfpGetReq.tosendXml(), Einvocie.class);
                    } catch (Exception e) {
                        LOGGER.error("电子发票webservice调用异常,原因：", e);
                        throw new ServiceException(4908);
                    }
                    if (!"0000".equals(einvocie.getReturnCode())) {
                        LOGGER.error("发票开票异常：{}", einvocie);
                        throw new ServiceException(4908, einvocie.getReturnMessage());
                    }
                }
                invoiceNo = einvocie.getFP_HM();
                invoiceCode = einvocie.getFP_DM();
                invoiceBO.setInvoiceNo(invoiceNo);
                invoiceBO.setInvoiceCode(invoiceCode);
                //更新发票详情信息，更新成功就去修改开票日志表
                if (updInvoiceDetail(einvocie, invoiceBO.getId())) {
                    Einvocie temp = new Einvocie();
                    temp.setTBSTATUS("1");
                    temp.setFPQQLSH(fpqqlsh);
                    invoiceDetailMapper.updateDZFP(temp);
                }
                invoice.setStatus("5");
                sendDzfpMessage(request, invoiceBO);
            } else {
                if (invoiceCheckBO.getDetailId() != null && !"".equals(invoiceCheckBO.getDetailId())) {
                    InvoiceDetail invoiceDetail = invoiceDetailRoMapper.selectByPrimaryKey(invoiceCheckBO.getDetailId
                            ());
                    if (invoiceDetail == null) {
                        LOGGER.info("发票详情信息不能为空");
                        throw new ServiceException(4186);
                    }
                    invoiceDetail.setStatus("2");
                    int dUpdate = invoiceDetailMapper.update(invoiceDetail);
                    if (dUpdate != 1) {
                        LOGGER.info("发票详情信息修改失败：{}", invoiceDetail);
                        throw new ServiceException(4187);
                    }
                    invoiceNo = invoiceDetail.getInvoiceNo();
                    invoiceCode = invoiceDetail.getInvoiceCode();
                }
                invoice.setStatus("2");
                sendZzfpMessage(request, invoiceBO);
            }
        } else {
            //发票审核不通过
            checkInvoiceRefuse(invoiceCheckBO, request, invoice, invoiceBO);

        }
        invoice.setRemark(invoiceCheckBO.getRemark());
        invoice.setLastUpdate(new Date());
        invoice.setInvoiceNo(invoiceNo);
        invoice.setInvoiceCode(invoiceCode);
        invoice.setType(invoiceCheckBO.getType());
        int update = invoiceMapper.update(invoice);
        if (update != 1) {
            LOGGER.info("修改失败：{}", invoice);
            throw new ServiceException(4102);
        }
        //加入发票日志
        insertInvoiceLog(invoiceCheckBO.getId(), Utils.getAdminId(), invoiceCheckBO.getRemark());
    }

    /**
     * 获取微信红包字典信息
     */
    private String selectWechatPassword(String businessId) {
        Dict dict = new Dict();
        dict.setDictId("wechat_hongbao");
        dict = dictRoMapper.selectOne(dict);
        String password = "";
        if (dict != null) {
            password = dict.getFieldValue();
            WxRedEnvelopBO data = iActivityService.generateSecret(password, businessId);
            if (data != null && data.getSecret() != null) {
                password = data.getSecret();
            }
        }
        return password;
    }

    /**
     * 电子发票是否生成红包口令,0：否；1：是
     */
    private String selectFieldValue() {
        Dict dict = new Dict();
        dict.setDictId("dzfp_is_redpackage");
        dict = dictRoMapper.selectOne(dict);
        return dict != null ? dict.getFieldValue() : "";
    }

    /**
     * 更新发票详情信息
     *
     * @param einvocie
     * @param id
     */
    private boolean updInvoiceDetail(Einvocie einvocie, String id) {
        InvoiceDetail tail = new InvoiceDetail();
        tail.setInvoiceNo(einvocie.getFP_HM());
        tail.setInvoiceCode(einvocie.getFP_DM());
        tail.setSpUrl(einvocie.getSP_URL());
        tail.setPdfUrl(einvocie.getPDF_URL());
        tail.setRemark(id);
        //根据发票号码和发票代码查找发票详细信息表
        InvoiceDetail detail = invoiceDetailRoMapper.selectByInvoiceNoAndCode(tail);
        if (detail != null && detail.getStatus() != null && "3".equals(detail.getStatus())) {
            tail.setId(detail.getId());
            tail.setLastUpdate(new Date());
            tail.setStatus("2");
            int dUpdate = invoiceDetailMapper.update(tail);
            if (dUpdate != 1) {
                LOGGER.info("发票详情信息修改失败：{}", tail);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 纸质发票开具发送消息
     */
    private void sendZzfpMessage(HttpServletRequest request, InvoiceBO invoiceBO) {
        //User user = userMapper.selectOne(invoiceBO.getUserId());
        //发送消息
        //Message message = new Message();
        //message.setBusinessId(invoiceBO.getId());
        //message.setBusiType(MessageConstant.ZZFPDD);
        //message.setType(MessageConstant.SYS_MESSAGE);
        String content = RemindConstant.INVOICE_CHECK_ADOPT.replaceAll("\\{#DATA.INVOICE\\}", invoiceBO
                .getId());
        //message.setContent(content);
        //message.setUserId(invoiceBO.getUserId());

        Map<String, String> map = new HashMap<>();
        //map.put("userId", user.getId());
        //map.put("openId", user.getWxopenid());
        map.put("first", "您好，审核结果如下");
        map.put("remark", "感谢您的使用。");
        map.put("keyword1", invoiceBO.getId());
        map.put("keyword2", content);
        String templateId = "W1udf26l5sI7OReFNlchAiGFbOV3z3dKoHb1MGSMVAc";
        //messageSendUtil.sendMsg(request, user, message, map, templateId);
        
        MessageSendBo messageSendBo =new MessageSendBo();
        messageSendBo.setType(MessageConstant.SYS_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_INVOICE);
        messageSendBo.setBusinessId(invoiceBO.getId());
        messageSendBo.setWebMsg(content);
        messageSendBo.setPhoneMsg(content);
        messageSendBo.setTemplateid(templateId);
        messageSendBo.setDataList(map);
        
        List<String> userIds =new ArrayList<String>();
        userIds.add(invoiceBO.getUserId());
        messageSendBo.setUserIds(userIds);
        
        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    /**
     * 电子发票开具发送消息
     */
    private void sendDzfpMessage(HttpServletRequest request, InvoiceBO invoiceBO) {
        //User user = userMapper.selectOne(invoiceBO.getUserId());
        //查询是否发红包
        String isRedPackage = selectFieldValue();
        String redPackage = "";
        if (isRedPackage != null && "1".equals(isRedPackage)) {
            //获取微信红包信息
            redPackage = RemindConstant.RED_PACKAGE.replaceAll("\\{#DATA.PACKAGE\\}",
                    selectWechatPassword(invoiceBO.getId()));
        }

        //发送消息
        //Message message = new Message();
        //message.setBusinessId(invoiceBO.getId());
        //message.setBusiType(MessageConstant.ZZFPDD);
        //message.setType(MessageConstant.SYS_MESSAGE);

        String content = RemindConstant.ELECTRON_INVOICE_CHECK_ADOPT.replaceAll("\\{#DATA.INVOICE\\}",
                invoiceBO.getId()) + redPackage;
        //message.setContent(content);
        //message.setUrl("<a href=\"" + SpringCtxHolder.getProperty("abc12366.api.url.uc") +
        //        "/userinfo/invoice/" + invoiceBO.getId() + "\">" + MessageConstant.VIEW_DETAILS + "</a>");
        //message.setUserId(invoiceBO.getUserId());

        //发送微信消息
        Map<String, String> map = new HashMap<>();
        //map.put("userId", user.getId());
        //map.put("openId", user.getWxopenid());
        map.put("first", "您申请的电子发票已开具");
        map.put("remark", "请注意查收！" + redPackage);
        map.put("keyword1", invoiceBO.getInvoiceCode());
        map.put("keyword2", invoiceBO.getInvoiceNo());
        map.put("keyword3", String.valueOf(invoiceBO.getAmount()));
        map.put("keyword4", DateUtils.dateToStr(new Date()));
        String templateId = "8q_2E8_lBY0Djxg8uoQBfgP0W7yxhb8hmKOUcn8gZZM";
        //messageSendUtil.sendMsg(request, user, message, map, templateId);
        
        MessageSendBo messageSendBo =new MessageSendBo();
        messageSendBo.setType(MessageConstant.SYS_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_INVOICE);
        messageSendBo.setBusinessId(invoiceBO.getId());
        messageSendBo.setSkipUrl("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") +
                        "/userinfo/invoice/" + invoiceBO.getId() + "'>" + MessageConstant.VIEW_DETAILS + "</a>");
        messageSendBo.setWebMsg(content);
        messageSendBo.setPhoneMsg(content);
        messageSendBo.setTemplateid(templateId);
        messageSendBo.setDataList(map);
        messageSendBo.setWxNoChargeVip(true);
        messageSendBo.setMoblieNoChargeVip(true);
        
        List<String> userIds =new ArrayList<String>();
        userIds.add(invoiceBO.getUserId());
        messageSendBo.setUserIds(userIds);
        
        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    /**
     * 纸质发票审核不通过
     */
    private void checkInvoiceRefuse(InvoiceCheckBO invoiceCheckBO, HttpServletRequest request, Invoice invoice,
                                    InvoiceBO invoiceBO) {
        updateInvoiceAndOrder(invoiceCheckBO.getId());
        invoice.setStatus("3");
        //User user = userMapper.selectOne(invoiceBO.getUserId());
        //发送消息
        //Message message = new Message();
        //message.setBusinessId(invoiceBO.getId());
        //message.setBusiType(MessageConstant.DZFPDD);
        //message.setType(MessageConstant.SYS_MESSAGE);

        //1：纸质发票，2：电子发票；
        String content;
        if (invoiceBO.getProperty() != null && "2".equals(invoiceBO.getProperty())) {
            content = RemindConstant.ELECTRON_INVOICE_CHECK_REFUSE.replaceAll("\\{#DATA.INVOICE\\}",
                    invoiceBO.getId());
        } else {
            content = RemindConstant.INVOICE_CHECK_REFUSE.replaceAll("\\{#DATA.INVOICE\\}", invoiceBO
                    .getId());
        }

        //message.setContent(content);
        //message.setUrl("<a href=\"" + SpringCtxHolder.getProperty("abc12366.api.url.uc") +
        //        "/userinfo/invoice/" + invoiceBO.getId() + "\">" + MessageConstant.VIEW_DETAILS + "</a>");
        //message.setUserId(invoiceBO.getUserId());

        Map<String, String> map = new HashMap<>();
        //map.put("userId", user.getId());
        //map.put("openId", user.getWxopenid());
        map.put("first", "您好，审核结果如下");
        map.put("remark", "感谢您的使用。");
        map.put("keyword1", invoiceBO.getId());
        map.put("keyword2", content);
        String templateId = "W1udf26l5sI7OReFNlchAiGFbOV3z3dKoHb1MGSMVAc";
        //messageSendUtil.sendMsg(request, user, message, map, templateId);
        
        MessageSendBo messageSendBo =new MessageSendBo();
        messageSendBo.setType(MessageConstant.SYS_MESSAGE);
        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_INVOICE);
        messageSendBo.setBusinessId(invoiceBO.getId());
        messageSendBo.setSkipUrl("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") +
                "/userinfo/invoice/" + invoiceBO.getId() + "'>" + MessageConstant.VIEW_DETAILS + "</a>");
        messageSendBo.setWebMsg(content);
        messageSendBo.setPhoneMsg(content);
        messageSendBo.setTemplateid(templateId);
        messageSendBo.setDataList(map);
        
        List<String> userIds =new ArrayList<String>();
        userIds.add(invoiceBO.getUserId());
        messageSendBo.setUserIds(userIds);
        
        msgSendV2Service.sendMsgV2(messageSendBo);
    }

    /**
     * 修改订单和发票信息
     */
    private void updateInvoiceAndOrder(String invoiceId) {
        List<OrderInvoice> orderInvoiceList = orderInvoiceRoMapper.selectByInvoiceId(invoiceId);
        //修改订单是否已开发票状态
        for (OrderInvoice orderInvoice : orderInvoiceList) {
            Order order = new Order();
            order.setOrderNo(orderInvoice.getOrderNo());
            order.setIsInvoice(false);
            order.setLastUpdate(new Date());
            int oUpdate = orderMapper.update(order);
            if (oUpdate != 1) {
                LOGGER.info("订单信息修改错误：{}", order);
                throw new ServiceException(4148);
            }
        }
        //修改订单和发票对应关系
        orderInvoiceMapper.updateByInvoiceId(invoiceId);
    }

}
