package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.VipLevelMapper;
import com.abc12366.uc.mapper.db2.VipLevelRoMapper;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.VipLevel;
import com.abc12366.uc.model.bo.VipLevelBO;
import com.abc12366.uc.model.bo.VipLevelInsertBO;
import com.abc12366.uc.model.bo.VipLevelUpdateBO;
import com.abc12366.uc.service.VipLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 11:24
 */
@Service
public class VipLevelServiceImpl implements VipLevelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VipLevelServiceImpl.class);

    @Autowired
    private VipLevelMapper vipLevelMapper;

    @Autowired
    private VipLevelRoMapper vipLevelRoMapper;
    
    @Override
    public List<VipLevelBO> selectList(Map map) {
        return vipLevelRoMapper.selectList(map);
    }

    @Override
    public VipLevelBO selectOne(String id) {
        return vipLevelRoMapper.selectOne(id);
    }

    @Override
    public VipLevelBO insert(VipLevelInsertBO vipLevelInsertBO) {
        if (vipLevelInsertBO == null) {
            throw new ServiceException(4101);
        }
        //特权名称唯一性校验
        List<VipLevelBO> vipLevelBOList = vipLevelRoMapper.selectList(null);
        for (VipLevelBO vipLevelBO : vipLevelBOList) {
            if (vipLevelBO.getLevel().equals(vipLevelInsertBO.getLevel())) {
                LOGGER.warn("新增失败，参数：{}", vipLevelInsertBO);
                throw new ServiceException(4606);
            }
            if (vipLevelInsertBO.getLevelCode() != null) {
                if (vipLevelBO.getLevelCode().equals(vipLevelInsertBO.getLevelCode())) {
                    LOGGER.warn("新增失败，参数：{}", vipLevelInsertBO);
                    throw new ServiceException(4607);
                }
            }
        }

        VipLevelBO vipLevelQuery = vipLevelRoMapper.selectByLevel(vipLevelInsertBO.getLevel());
        if (vipLevelQuery != null) {
            LOGGER.warn("新增失败，参数：level=" + vipLevelInsertBO.getLevel());
            throw new ServiceException(4101);
        }
        VipLevel vipLevel = new VipLevel();
        BeanUtils.copyProperties(vipLevelInsertBO, vipLevel);
        Date date = new Date();
        vipLevel.setId(Utils.uuid());
        vipLevel.setLastUpdate(date);
        vipLevel.setCreateTime(date);
        int result = vipLevelMapper.insert(vipLevel);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：" + vipLevel.toString());
            throw new ServiceException(4101);
        }
        VipLevelBO vipLevelBOReturn = new VipLevelBO();
        BeanUtils.copyProperties(vipLevel, vipLevelBOReturn);
        return vipLevelBOReturn;
    }

    @Override
    public VipLevelBO update(VipLevelUpdateBO vipLevelUpdateBO, String id) {
        if (vipLevelUpdateBO == null) {
            LOGGER.warn("修改失败，参数：" + null);
            throw new ServiceException(4102);
        }

        //特权名称唯一性校验
        List<VipLevelBO> vipLevelBOList = vipLevelRoMapper.selectList(null);
        for (int i = 0; i < vipLevelBOList.size(); i++) {
            if ((vipLevelBOList.get(i)).getId().equals(id)) {
                vipLevelBOList.remove(i);
            }
        }
        for (VipLevelBO vipLevelBO : vipLevelBOList) {
            if (vipLevelBO.getLevel().equals(vipLevelUpdateBO.getLevel())) {
                LOGGER.warn("新增失败，参数：{}", vipLevelUpdateBO);
                throw new ServiceException(4606);
            }
            if (vipLevelUpdateBO.getLevelCode() != null) {
                if (vipLevelBO.getLevelCode().equals(vipLevelUpdateBO.getLevelCode())) {
                    LOGGER.warn("新增失败，参数：{}", vipLevelUpdateBO);
                    throw new ServiceException(4607);
                }
            }
        }

        VipLevel vipLevel = new VipLevel();
        BeanUtils.copyProperties(vipLevelUpdateBO, vipLevel);
        vipLevel.setId(id);
        vipLevel.setLastUpdate(new Date());
        int result = vipLevelMapper.update(vipLevel);
        if (result != 1) {
            LOGGER.warn("修改失败，参数为：" + vipLevel);
            throw new ServiceException(4102);
        }
        VipLevelBO vipLevelBOReturn = new VipLevelBO();
        BeanUtils.copyProperties(vipLevel, vipLevelBOReturn);
        
        return vipLevelBOReturn;
    }

    @Override
    public int delete(String id) {
        LOGGER.info("{}", id);
        //已经被使用的用户等级数据不允许删除
        List<User> userList = vipLevelRoMapper.selectUserByVipLevelCode(id);
        if (userList != null && userList.size() > 0) {
            throw new ServiceException(4636);
        }

        int result = vipLevelMapper.delete(id);
        if (result != 1) {
            LOGGER.warn("删除失败，参数为：id=" + id);
            throw new ServiceException(4103);
        }
        return 1;
    }

    @Override
    public void enableOrDisable(String id, String status) {
        LOGGER.info("{}:{}", id, status);
        if ((!"true".equals(status)) && (!"false".equals(status))) {
            throw new ServiceException(4614);
        }
        boolean modifyStatus = "true".equals(status);
        VipLevel vipLevel = new VipLevel();
        vipLevel.setId(id);
        vipLevel.setStatus(modifyStatus);
        vipLevel.setLastUpdate(new Date());
        int result = vipLevelMapper.enableOrDisable(vipLevel);
        if (result < 1) {
            if (modifyStatus) {
                throw new ServiceException(4617);
            }
            throw new ServiceException(4618);
        }
    }

    @Override
    public VipLevelBO selectByLevelCode(String levelCode) {
    	return vipLevelRoMapper.selectByLevelCode(levelCode);
    }
}
