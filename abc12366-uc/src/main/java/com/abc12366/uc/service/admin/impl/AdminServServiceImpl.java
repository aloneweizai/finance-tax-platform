package com.abc12366.uc.service.admin.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.uc.mapper.db1.AdminServiceMapper;
import com.abc12366.uc.mapper.db2.AdminServiceRoMapper;
import com.abc12366.uc.model.admin.AdminService;
import com.abc12366.uc.model.admin.bo.AdminServiceBo;
import com.abc12366.uc.service.admin.AdminServService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by xieyanmao on 2017/9/25.
 */
@Service
public class AdminServServiceImpl implements AdminServService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServServiceImpl.class);

    @Autowired
    private AdminServiceMapper adminServMapper;

    @Autowired
    private AdminServiceRoMapper adminServRoMapper;


    @Override
    public List<AdminServiceBo> selectList(Map<String,Object> map) {
        List<AdminServiceBo> adminServiceBoList;
        try {
            //查询客服经理列表
            adminServiceBoList = adminServRoMapper.selectList(map);
        } catch (Exception e) {
            LOGGER.error("查询客服经理列表信息异常：{}", e);
            throw new ServiceException(6350);
        }
        return adminServiceBoList;
    }

    @Override
    public AdminServiceBo save(AdminServiceBo adminServBo) {

            JSONObject jsonStu = JSONObject.fromObject(adminServBo);
            LOGGER.info("新增客服经理信息:{}", jsonStu.toString());
            //保存客服经理信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            AdminService adminServ = new AdminService();
            adminServBo.setId(uuid);
            adminServBo.setCreateTime(new Date());

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userId", adminServBo.getUserId());
            int cnt = adminServRoMapper.selectCnt(dataMap);
            if(cnt >0){
                //同一用户只能创建一位客服经理信息
                throw new ServiceException(6355);
            }

        try {
            BeanUtils.copyProperties(adminServBo, adminServ);
            adminServMapper.insert(adminServ);
        } catch (Exception e) {
            LOGGER.error("新增客服经理信息异常：{}", e);
            throw new ServiceException(6352);
        }

        return adminServBo;
    }

    @Override
    public AdminServiceBo selectAdminService(String id) {
        AdminServiceBo adminServBo = new AdminServiceBo();
        try {
            LOGGER.info("查询单个客服经理信息:{}", id);
            //查询客服经理信息
            AdminService adminServ = adminServRoMapper.selectByPrimaryKey(id);
            BeanUtils.copyProperties(adminServ, adminServBo);
        } catch (Exception e) {
            LOGGER.error("查询单个客服经理信息异常：{}", e);
            throw new ServiceException(6351);
        }
        return adminServBo;
    }

    @Override
    public AdminServiceBo update(AdminServiceBo adminServBo) {
        //更新客服经理信息
        AdminService adminServ = new AdminService();

            JSONObject jsonStu = JSONObject.fromObject(adminServBo);
            LOGGER.info("更新客服经理信息:{}", jsonStu.toString());
            adminServBo.setLastUpdate(new Date());

        if(adminServBo.getUserId() !=null && !"".equals(adminServBo.getUserId())){
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userId", adminServBo.getUserId());
            dataMap.put("id", adminServBo.getId());
            int cnt = adminServRoMapper.selectCnt(dataMap);
            if(cnt >0){
                //同一用户只能创建一位客服经理信息
                throw new ServiceException(6355);
            }
        }



        try {
            BeanUtils.copyProperties(adminServBo, adminServ);
            adminServMapper.updateByPrimaryKeySelective(adminServ);
        } catch (Exception e) {
            LOGGER.error("更新客服经理信息异常：{}", e);
            throw new ServiceException(6353);
        }
        return adminServBo;
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String id) {

            LOGGER.info("删除客服经理信息:{}", id);
//            int cnt = adminServRoMapper.selectLecturerCnt(lecturerId);
//            if(cnt > 0){
//                //该客服经理名下有课程，不能删除
//                throw new ServiceException(4356);
//            }
        try {
            adminServMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("删除客服经理异常：{}", e);
            throw new ServiceException(6354);
        }
        return "";
    }

}
