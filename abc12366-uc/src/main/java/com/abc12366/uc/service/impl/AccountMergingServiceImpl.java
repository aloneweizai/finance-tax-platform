package com.abc12366.uc.service.impl;

import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.AccountMergingMapper;
import com.abc12366.uc.mapper.db1.InvoiceLogMapper;
import com.abc12366.uc.mapper.db1.OrderLogMapper;
import com.abc12366.uc.mapper.db2.UserExtendRoMapper;
import com.abc12366.uc.model.bo.PointsLogBO;
import com.abc12366.uc.model.bo.PointsRuleBO;
import com.abc12366.uc.model.bo.UserBO;
import com.abc12366.uc.model.invoice.InvoiceLog;
import com.abc12366.uc.model.invoice.bo.InvoiceBO;
import com.abc12366.uc.model.order.OrderLog;
import com.abc12366.uc.model.order.bo.OrderBO;
import com.abc12366.uc.service.IAccountMergingService;
import com.abc12366.uc.service.PointsLogService;
import com.abc12366.uc.service.UserService;
import com.abc12366.uc.service.invoice.InvoiceService;
import com.abc12366.uc.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountMergingServiceImpl implements IAccountMergingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountMergingServiceImpl.class);
	
	@Autowired
	private PointsLogService pointsLogService;
	
	@Autowired
	private AccountMergingMapper accountMergingMapper;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private OrderService orderService;
	
	@Autowired
	private OrderLogMapper orderLogMapper;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
    private InvoiceLogMapper invoiceLogMapper;
	
	@Autowired
    private UserExtendRoMapper userExtendRoMapper;
	 
	@SuppressWarnings("rawtypes")
	@Transactional("db1TxManager")
	public ResponseEntity merging(UserBO mergeUserBO, UserBO beMergeUserBO,
			PointsRuleBO bo) {
		//1.合并纳税人信息
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("newid", mergeUserBO.getId());
		map.put("oldid", beMergeUserBO.getId());
		
		int hngsnum=accountMergingMapper.updateHngs(map);
		LOGGER.info("合并国税绑定税号个数:" + hngsnum);
		int hndsnum=accountMergingMapper.updateHnds(map);
		LOGGER.info("合并地税绑定税号个数:" + hndsnum);
		int dzsbnum=accountMergingMapper.updateDzsb(map);
		LOGGER.info("合并电子申报绑定税号个数:" + dzsbnum);
		
		//2.合并订单,发票
		OrderBO order = new OrderBO();
		order.setUser(beMergeUserBO);
		List<OrderBO> orderBOs = orderService.selectOrderListByInvoice(order, 0, 0);
		if(orderBOs!=null && orderBOs.size()>0){
			 for(OrderBO orderBO:orderBOs){
				    OrderLog orderLog = new OrderLog();
			        orderLog.setId(Utils.uuid());
			        orderLog.setAction("账号合并");
			        orderLog.setOrderNo(orderBO.getOrderNo());
			        orderLog.setCreateTime(new Date());
			        orderLog.setCreateUser(mergeUserBO.getId());
			        orderLog.setRemark("账号合并，订单合并至新账号，原账号:"+beMergeUserBO.getUsername());
			        orderLog.setLogType("0");
			        orderLogMapper.insert(orderLog);
			 }
		}
		int ordernum=accountMergingMapper.updateOrder(map);
		LOGGER.info("合并订单信息个数:" + ordernum);
		
		
		InvoiceBO invoice = new InvoiceBO();
	    invoice.setUserId(beMergeUserBO.getId());
	    List<InvoiceBO> invoiceList = invoiceService.selectList(invoice);
	    if(invoiceList!=null && invoiceList.size()>0){
			 for(InvoiceBO invoiceBO:invoiceList){
				    InvoiceLog invoiceLog = new InvoiceLog();
			        invoiceLog.setId(Utils.uuid());
			        invoiceLog.setCreateTime(new Date());
			        invoiceLog.setAction("账号合并，发票信息合并至新账号，原账号:"+beMergeUserBO.getUsername());
			        invoiceLog.setInvoiceId(invoiceBO.getId());
			        invoiceLog.setCreateUser(mergeUserBO.getId());
			        invoiceLogMapper.insert(invoiceLog);
			 }
		}
		int invoicenum=accountMergingMapper.updateInvoice(map);
		LOGGER.info("合并发票信息个数:" + invoicenum);
		
        //3.合并积分
		Integer points=beMergeUserBO.getPoints();
		PointsLogBO spointsLog = new PointsLogBO();
        spointsLog.setUserId(mergeUserBO.getId());
        spointsLog.setRuleId(bo.getId());
        spointsLog.setRemark("账号合并，转入被合并账号:"+beMergeUserBO.getUsername()+"剩余积分.");
        spointsLog.setIncome(points);
        spointsLog.setOutgo(0);
        pointsLogService.insert(spointsLog);
        
        PointsLogBO rpointsLog = new PointsLogBO();
        rpointsLog.setUserId(beMergeUserBO.getId());
        rpointsLog.setRuleId(bo.getId());
        rpointsLog.setRemark("账号合并,将积分转入合并账号："+mergeUserBO.getUsername());
        rpointsLog.setIncome(0);
        rpointsLog.setOutgo(points);
        pointsLogService.insert(rpointsLog);
		
        //4.停用原来用户
        userService.enableOrDisable(beMergeUserBO.getId(), "false");
        
        return ResponseEntity.ok(Utils.kv());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, String>> canmerging(Map map) {
		return userExtendRoMapper.canmerging(map);
	}
}
