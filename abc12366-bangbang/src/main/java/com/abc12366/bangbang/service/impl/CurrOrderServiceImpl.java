package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CurriculumOrderMapper;
import com.abc12366.bangbang.mapper.db2.CurriculumOrderRoMapper;
import com.abc12366.bangbang.model.curriculum.CurriculumOrder;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumOrderBo;
import com.abc12366.bangbang.service.CurrOrderService;
import com.abc12366.gateway.exception.ServiceException;
import net.sf.json.JSONObject;
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
 * Created by xieyanmao on 2017/8/11.
 */
@Service
public class CurrOrderServiceImpl implements CurrOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrOrderServiceImpl.class);

    @Autowired
    private CurriculumOrderMapper orderMapper;

    @Autowired
    private CurriculumOrderRoMapper orderRoMapper;

    @Override
    public List<CurriculumOrderBo> selectList(Map<String,Object> map) {
        List<CurriculumOrderBo> orderBoList;
        try {
            //查询课程订单列表
            orderBoList = orderRoMapper.selectList(map);
        } catch (Exception e) {
            LOGGER.error("查询课程订单列表信息异常：{}", e);
            throw new ServiceException(4380);
        }
        return orderBoList;
    }

    @Override
    public CurriculumOrderBo save(CurriculumOrderBo orderBo) {
        try {
            JSONObject jsonStu = JSONObject.fromObject(orderBo);
            LOGGER.info("新增课程订单信息:{}", jsonStu.toString());
            orderBo.setOrderTime(new Date());
            //保存课程订单信息
//            String uuid = UUID.randomUUID().toString().replace("-", "");
            CurriculumOrder order = new CurriculumOrder();
//            orderBo.setOrderId(uuid);
            BeanUtils.copyProperties(orderBo, order);
            orderMapper.insert(order);
        } catch (Exception e) {
            LOGGER.error("新增课程订单信息异常：{}", e);
            throw new ServiceException(4382);
        }

        return orderBo;
    }

    @Override
    public CurriculumOrderBo selectOrder(String orderId) {
        CurriculumOrderBo orderBo = new CurriculumOrderBo();
        try {
            LOGGER.info("查询单个课程订单信息:{}", orderId);
            //查询课程订单信息
            CurriculumOrder order = orderRoMapper.selectByPrimaryKey(orderId);
            BeanUtils.copyProperties(order, orderBo);
        } catch (Exception e) {
            LOGGER.error("查询单个课程订单信息异常：{}", e);
            throw new ServiceException(4381);
        }
        return orderBo;
    }

    @Override
    public CurriculumOrderBo update(CurriculumOrderBo orderBo) {
        //更新课程订单信息
        CurriculumOrder order = new CurriculumOrder();
        try {
            JSONObject jsonStu = JSONObject.fromObject(orderBo);
            LOGGER.info("更新课程订单信息:{}", jsonStu.toString());
            BeanUtils.copyProperties(orderBo, order);
            orderMapper.updateByPrimaryKeySelective(order);
        } catch (Exception e) {
            LOGGER.error("更新课程订单信息异常：{}", e);
            throw new ServiceException(4383);
        }
        return orderBo;
    }

    @Override
    public String updateStatus(String orderId,String status) {
        //更新课程订单信息
        try {
//            LOGGER.info("更新课程状态信息:{}", OrderId+","+status);
//            Curriculum curriculum = new Curriculum();
//            curriculum.setCurriculumId(curriculumId);
//            curriculum.setStatus(Integer.parseInt(status));
//            curriculum.setUpdateTime(new Date());
//            //1为发布
//            if("1".equals(status)){
//                curriculum.setIssueTime(new Date());
//            }else{
//                curriculum.setIssueTime(null);
//            }
//            OrderMapper.updateStatus(curriculum);
        } catch (Exception e) {
            LOGGER.error("更新课件信息异常：{}", e);
            throw new ServiceException(4383);
        }
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String orderId) {
        try {
            LOGGER.info("删除课程订单信息:{}", orderId);
            orderMapper.deleteByPrimaryKey(orderId);
        } catch (Exception e) {
            LOGGER.error("删除课程订单信息异常：{}", e);
            throw new ServiceException(4384);
        }
        return "";
    }

}
