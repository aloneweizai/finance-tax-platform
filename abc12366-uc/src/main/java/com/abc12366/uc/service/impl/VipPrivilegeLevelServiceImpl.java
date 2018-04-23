package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.VipPrivilegeLevelMapper;
import com.abc12366.uc.mapper.db2.VipLevelRoMapper;
import com.abc12366.uc.mapper.db2.VipPrivilegeLevelRoMapper;
import com.abc12366.uc.model.VipPrivilegeLevel;
import com.abc12366.uc.model.bo.VipLevelBO;
import com.abc12366.uc.model.bo.VipPrivilegeLevelBO;
import com.abc12366.uc.model.bo.VipPrivilegeLevelInsertBO;
import com.abc12366.uc.model.bo.VipPrivilegeLevelUpdateBO;
import com.abc12366.uc.service.VipPrivilegeLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 9:26
 */
@Service
public class VipPrivilegeLevelServiceImpl implements VipPrivilegeLevelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VipPrivilegeLevelServiceImpl.class);
    @Autowired
    private VipPrivilegeLevelRoMapper vipPrivilegeLevelRoMapper;

    @Autowired
    private VipPrivilegeLevelMapper vipPrivilegeLevelMapper;
    @Autowired
    private VipLevelRoMapper vipLevelRoMapper;

    //指定会员id 权益id 查询结果
    @Override
    public VipPrivilegeLevelBO selectLevelIdPrivilegeId(VipPrivilegeLevelBO obj) {
        if (obj.getLevelId() == null || obj.getLevelId().isEmpty() || obj.getPrivilegeId() == null || obj
                .getPrivilegeId().isEmpty()) {
            LOGGER.warn("操作失败，参数：" + obj);
            throw new ServiceException(4101);
        }
        return vipPrivilegeLevelRoMapper.selectLevelIdPrivilegeId(obj);
    }

    //先查询，不存在就新建 存在就修改
    @Override
    public VipPrivilegeLevelBO addOrUpdate(VipPrivilegeLevelBO obj) {
        VipPrivilegeLevelBO findObj = this.selectLevelIdPrivilegeId(obj);
        if (findObj == null) {
            VipPrivilegeLevelInsertBO vipPrivilegeLevelInsertBO = new VipPrivilegeLevelInsertBO();
            BeanUtils.copyProperties(obj, vipPrivilegeLevelInsertBO);

            return this.insert(vipPrivilegeLevelInsertBO);
        } else {
            VipPrivilegeLevelUpdateBO newObj = new VipPrivilegeLevelUpdateBO();
            BeanUtils.copyProperties(obj, newObj);
            return this.update(newObj, findObj.getId());
        }
    }

    @Override
    public Integer updates(List<VipPrivilegeLevelBO> list) {
        Integer fori = 0;
        for (VipPrivilegeLevelBO obj : list) {
            addOrUpdate(obj);
            fori++;   //暂时没考虑中间异常
        }
        return fori;
    }

    @Override
    public Integer adds(List<VipPrivilegeLevelBO> list) {
        Integer fori = 0;
        for (VipPrivilegeLevelBO obj : list) {
            VipPrivilegeLevelInsertBO vipPrivilegeLevelInsertBO = new VipPrivilegeLevelInsertBO();
            BeanUtils.copyProperties(obj, vipPrivilegeLevelInsertBO);

            this.insert(vipPrivilegeLevelInsertBO);
            fori++;   //暂时没考虑中间异常
        }
        return fori;
    }

    @Override
    public Integer updateByPrivilege(String privilege, List<VipPrivilegeLevelBO> list) {
        if (privilege == null) {
            LOGGER.warn("操作失败");
            throw new ServiceException(4101);
        }
        this.deleteByPrivilege(privilege);

        return adds(list);
    }

    @Override
    public Integer updateByLevel(String level, List<VipPrivilegeLevelBO> list) {
        if (level == null) {
            LOGGER.warn("操作失败");
            throw new ServiceException(4101);
        }
        this.deleteByLevel(level);
        return adds(list);
    }

    @Override
    public VipPrivilegeLevelBO selectOne(String id) {
        return vipPrivilegeLevelRoMapper.selectOne(id);
    }

    @Override
    public List<VipPrivilegeLevelBO> selectListbyPrivlege(String privilege) {
        return vipPrivilegeLevelRoMapper.selectListbyPrivlege(privilege);
    }

    @Override
    public VipPrivilegeLevelBO selectOneN(String id) {
        return vipPrivilegeLevelRoMapper.selectOneN(id);
    }

    @Override
    public List<List<VipPrivilegeLevelBO>> selectList() {
        List<VipLevelBO> list = vipLevelRoMapper.selectList(null);
        List<List<VipPrivilegeLevelBO>> returnList = new ArrayList<>();
        for (VipLevelBO vipLevelBO : list) {
            List<VipPrivilegeLevelBO> levelList = vipPrivilegeLevelRoMapper.selectList(vipLevelBO.getLevelCode());
            if (levelList != null && levelList.size() > 0) {
                returnList.add(levelList);
            }
        }
        return returnList;
    }

    @Override
    public List<VipPrivilegeLevelBO> selectListByLevelId(String levelId) {

        return vipPrivilegeLevelRoMapper.selectList(levelId);
    }

    @Override
    public List<VipPrivilegeLevelBO> selectListByLevelName(String levelname) {

        VipLevelBO obj = vipLevelRoMapper.selectByLevel(levelname);

        if (obj.getId() == null || obj.getId().isEmpty()) {
            LOGGER.warn("操作失败，参数：" + levelname);
            throw new ServiceException(4101);
        }
        return selectListByLevelId(obj.getId());
    }

    @Override
    public List<VipPrivilegeLevelBO> selectListN(Map map) {
        return vipPrivilegeLevelRoMapper.selectListN(map);
    }

    @Override
    public VipPrivilegeLevelBO insert(VipPrivilegeLevelInsertBO vipPrivilegeLevelInsertBO) {
        if (vipPrivilegeLevelInsertBO == null) {
            LOGGER.warn("新增失败，参数：" + null);
            throw new ServiceException(4101);
        }


        VipPrivilegeLevel vipPrivilegeLevel = new VipPrivilegeLevel();
        BeanUtils.copyProperties(vipPrivilegeLevelInsertBO, vipPrivilegeLevel);
        Date date = new Date();
        vipPrivilegeLevel.setId(Utils.uuid());
        vipPrivilegeLevel.setCreateTime(date);
        vipPrivilegeLevel.setLastUpdate(date);
        if (StringUtils.isEmpty(vipPrivilegeLevelInsertBO.getStatus())) {
            vipPrivilegeLevel.setStatus(true);
        }
        int result = vipPrivilegeLevelMapper.insert(vipPrivilegeLevel);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：" + vipPrivilegeLevel);
            throw new ServiceException(4101);
        }
        VipPrivilegeLevelBO vipPrivilegeBOReturn = new VipPrivilegeLevelBO();
        BeanUtils.copyProperties(vipPrivilegeLevel, vipPrivilegeBOReturn);
        return vipPrivilegeBOReturn;
    }

    @Override
    public VipPrivilegeLevelBO update(VipPrivilegeLevelUpdateBO vipPrivilegeLevelUpdateBO, String id) {
        if (vipPrivilegeLevelUpdateBO == null) {
            LOGGER.warn("修改失败，参数：" + null);
            throw new ServiceException(4102);
        }


        VipPrivilegeLevelBO vipPrivilegeLevelQuery = vipPrivilegeLevelRoMapper.selectOne(id);
        if (vipPrivilegeLevelQuery == null) {
            LOGGER.warn("修改失败，不存在可被修改的数据，参数：" + null);
            throw new ServiceException(4102);
        }
        VipPrivilegeLevel vipPrivilegeLevel = new VipPrivilegeLevel();
        BeanUtils.copyProperties(vipPrivilegeLevelUpdateBO, vipPrivilegeLevel);
        vipPrivilegeLevel.setId(id);
        vipPrivilegeLevel.setCreateTime(null);
        vipPrivilegeLevel.setLastUpdate(new Date());
        if (StringUtils.isEmpty(vipPrivilegeLevelUpdateBO.getStatus())) {
            vipPrivilegeLevel.setStatus(false);
        }
        int result = vipPrivilegeLevelMapper.update(vipPrivilegeLevel);
        if (result != 1) {
            LOGGER.warn("修改失败，参数：" + vipPrivilegeLevel);
            throw new ServiceException(4102);
        }
        VipPrivilegeLevelBO vipPrivilegeLevelBOReturn = new VipPrivilegeLevelBO();
        BeanUtils.copyProperties(vipPrivilegeLevel, vipPrivilegeLevelBOReturn);
        return vipPrivilegeLevelBOReturn;
    }

    @Override
    public int deleteByPrivilege(String privilegeId) {
        return vipPrivilegeLevelMapper.deleteByPrivilege(privilegeId);
    }

    @Override
    public int deleteByLevel(String levelId) {
        return vipPrivilegeLevelMapper.deleteByLevel(levelId);
    }

    @Override
    public boolean delete(String id) {
        VipPrivilegeLevelBO vipPrivilegeLevelBO = vipPrivilegeLevelRoMapper.selectOne(id);
        if (vipPrivilegeLevelBO == null) {
            LOGGER.warn("删除失败，不存在要被删除的数据,参数：" + id);
            throw new ServiceException(4103);
        }
        int result = vipPrivilegeLevelMapper.delete(id);
        if (result != 1) {
            LOGGER.warn("删除失败，参数：" + id);
            throw new ServiceException(4103);
        }
        return true;
    }

    @Override
    public void enableOrDisable(String id, String status) {
        LOGGER.info("{}:{}", id, status);
        if ((!status.equals("true")) && (!status.equals("false"))) {
            throw new ServiceException(4614);
        }
        boolean modifyStatus = status.equals("true");
        VipPrivilegeLevel vipPrivilegeLevel = new VipPrivilegeLevel();
        vipPrivilegeLevel.setId(id);
        vipPrivilegeLevel.setStatus(modifyStatus);
        vipPrivilegeLevel.setLastUpdate(new Date());
        int result = vipPrivilegeLevelMapper.update(vipPrivilegeLevel);
        if (result < 1) {
            if (modifyStatus) {
                throw new ServiceException(4623);
            }
            throw new ServiceException(4624);
        }
    }
}
