package com.abc12366.uc.service.admin.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.uc.mapper.db1.AdminServiceUserMapper;
import com.abc12366.uc.mapper.db2.AdminServiceUserRoMapper;
import com.abc12366.uc.model.admin.AdminServiceUser;
import com.abc12366.uc.model.admin.bo.AdminServiceUserBo;
import com.abc12366.uc.service.admin.AdminServUserService;
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
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/9/25.
 */
@Service
public class AdminServUserServiceImpl implements AdminServUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServUserServiceImpl.class);

    @Autowired
    private AdminServiceUserMapper adminServUserMapper;

    @Autowired
    private AdminServiceUserRoMapper adminServUserRoMapper;


    @Override
    public List<AdminServiceUserBo> selectList(Map<String,Object> map) {
        List<AdminServiceUserBo> adminServiceUserBoList;
        try {
            //查询客服经理关系列表
            adminServiceUserBoList = adminServUserRoMapper.selectList(map);
        } catch (Exception e) {
            LOGGER.error("查询客服经理关系列表信息异常：{}", e);
            throw new ServiceException(6360);
        }
        return adminServiceUserBoList;
    }

    @Override
    public AdminServiceUserBo save(AdminServiceUserBo adminServUserBo) {

            JSONObject jsonStu = JSONObject.fromObject(adminServUserBo);
            LOGGER.info("新增客服经理关系信息:{}", jsonStu.toString());
            //保存客服经理关系信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            AdminServiceUser adminServUser = new AdminServiceUser();
            adminServUserBo.setId(uuid);
        adminServUserBo.setCreateTime(new Date());

//            int cnt = adminServUserRoMapper.selectLecturerCnt(lecturerBo);
//            if(cnt >0){
//                //同一用户只能创建一位客服经理关系信息
//                throw new ServiceException(4355);
//            }

        try {
            BeanUtils.copyProperties(adminServUserBo, adminServUser);
            adminServUserMapper.insert(adminServUser);
        } catch (Exception e) {
            LOGGER.error("新增客服经理关系信息异常：{}", e);
            throw new ServiceException(6362);
        }

        return adminServUserBo;
    }

    @Override
    public AdminServiceUserBo selectAdminService(String id) {
        AdminServiceUserBo adminServUserBo = new AdminServiceUserBo();
        try {
            LOGGER.info("查询单个客服经理关系信息:{}", id);
            //查询客服经理关系信息
            AdminServiceUser adminServUser = adminServUserRoMapper.selectByPrimaryKey(id);
            BeanUtils.copyProperties(adminServUser, adminServUserBo);
        } catch (Exception e) {
            LOGGER.error("查询单个客服经理关系信息异常：{}", e);
            throw new ServiceException(6361);
        }
        return adminServUserBo;
    }

    @Override
    public AdminServiceUserBo update(AdminServiceUserBo adminServUserBo) {
        //更新客服经理关系信息
        AdminServiceUser adminServUser = new AdminServiceUser();

            JSONObject jsonStu = JSONObject.fromObject(adminServUserBo);
            LOGGER.info("更新客服经理关系信息:{}", jsonStu.toString());
        adminServUserBo.setLastUpdate(new Date());

//            int cnt = adminServUserRoMapper.selectLecturerCnt(lecturerBo);
//            if(cnt >0){
//                //同一用户只能创建一位客服经理关系信息
//                throw new ServiceException(4355);
//            }


        try {
            BeanUtils.copyProperties(adminServUserBo, adminServUser);
            adminServUserMapper.updateByPrimaryKeySelective(adminServUser);
        } catch (Exception e) {
            LOGGER.error("更新客服经理关系信息异常：{}", e);
            throw new ServiceException(6363);
        }
        return adminServUserBo;
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String id) {

            LOGGER.info("删除客服经理关系信息:{}", id);
//            int cnt = adminServUserRoMapper.selectLecturerCnt(lecturerId);
//            if(cnt > 0){
//                //该客服经理关系名下有课程，不能删除
//                throw new ServiceException(4356);
//            }
        try {
            adminServUserMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("删除客服经理关系异常：{}", e);
            throw new ServiceException(6364);
        }
        return "";
    }

}
