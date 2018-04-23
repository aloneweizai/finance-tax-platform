package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.VipPrivilegeMapper;
import com.abc12366.uc.mapper.db2.VipPrivilegeRoMapper;
import com.abc12366.uc.model.VipPrivilege;
import com.abc12366.uc.model.bo.VipPrivilegeBO;
import com.abc12366.uc.service.VipPrivilegeLevelService;
import com.abc12366.uc.service.VipPrivilegeService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 特权服务实现类
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-11-08 4:02 PM
 * @since 1.0.0
 */
@Service
public class VipPrivilegeServiceImpl implements VipPrivilegeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VipPrivilegeServiceImpl.class);

    @Autowired
    private VipPrivilegeRoMapper vipPrivilegeRoMapper;

    @Autowired
    private VipPrivilegeMapper vipPrivilegeMapper;

    @Autowired
    private VipPrivilegeLevelService vipPrivilegeLevelService;

    @Override
    public List<VipPrivilege> selectList(Map map, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        return vipPrivilegeRoMapper.selectList(map);
    }

    @Override
    public VipPrivilege selectOne(String id) {
        return vipPrivilegeRoMapper.selectOne(id);
    }

    @Override
    public VipPrivilege insert(VipPrivilegeBO bo) {
        if (bo == null) {
            LOGGER.warn("新增失败，参数：" + null);
            throw new ServiceException(4101);
        }

        // 名称、代码是否唯一
        Map<String, String> map = new HashMap<>(2);
        map.put("name", bo.getName());
        List<VipPrivilege> dataList = vipPrivilegeRoMapper.selectList(map);
        if (dataList.size() > 0) {
            throw new ServiceException(4101);
        }

        map.clear();
        map.put("code", bo.getCode());
        dataList = vipPrivilegeRoMapper.selectList(map);
        if (dataList.size() > 0) {
            throw new ServiceException(4101);
        }

        VipPrivilege vipPrivilege = new VipPrivilege();
        BeanUtils.copyProperties(bo, vipPrivilege);
        Date date = new Date();
        vipPrivilege.setId(Utils.uuid());
        vipPrivilege.setCreateTime(date);
        vipPrivilege.setLastUpdate(date);
        if (StringUtils.isEmpty(bo.getStatus())) {
            vipPrivilege.setStatus(true);
        }
        int result = vipPrivilegeMapper.insert(vipPrivilege);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：" + vipPrivilege);
            throw new ServiceException(4101);
        }
        return vipPrivilege;
    }

    @Override
    public VipPrivilege update(VipPrivilegeBO bo) {
        if (bo == null) {
            LOGGER.warn("修改失败，参数：" + null);
            throw new ServiceException(4102);
        }

        VipPrivilege vipPrivilege = vipPrivilegeRoMapper.selectOne(bo.getId());
        if (vipPrivilege == null) {
            LOGGER.warn("修改失败，不存在可被修改的数据");
            throw new ServiceException(4102);
        }

        // 名称、代码是否唯一
        Map<String, String> map = new HashMap<>(2);
        map.put("name", bo.getName());
        List<VipPrivilege> dataList = vipPrivilegeRoMapper.selectList(map);
        if (dataList.size() > 0) {
            for (VipPrivilege vp : dataList) {
                if (!bo.getId().equals(vp.getId())) {
                    throw new ServiceException(4102);
                }
            }
        }

        map.clear();
        map.put("code", bo.getCode());
        dataList = vipPrivilegeRoMapper.selectList(map);
        if (dataList.size() > 0) {
            for (VipPrivilege vp : dataList) {
                if (!bo.getId().equals(vp.getId())) {
                    throw new ServiceException(4102);
                }
            }
        }

        vipPrivilege.setLastUpdate(new Date());
        vipPrivilege.setCode(bo.getCode());
        vipPrivilege.setName(bo.getName());
        vipPrivilege.setDescription(bo.getDescription());
        vipPrivilege.setIcon(bo.getIcon());
        vipPrivilege.setSort(bo.getSort());
        vipPrivilege.setStatus(bo.getStatus());

        int result = vipPrivilegeMapper.update(vipPrivilege);
        if (result != 1) {
            LOGGER.warn("修改失败，参数：" + vipPrivilege);
            throw new ServiceException(4102);
        }
        return vipPrivilege;
    }

    @Override
    public boolean delete(String id) {
        VipPrivilege vipPrivilege = vipPrivilegeRoMapper.selectOne(id);
        if (vipPrivilege == null) {
            LOGGER.warn("删除失败，不存在要被删除的数据,参数：" + id);
            throw new ServiceException(4103);
        }
        int result = vipPrivilegeMapper.delete(id);
        if (result != 1) {
            LOGGER.warn("删除失败，参数：" + id);
            throw new ServiceException(4103);
        }
        //这里把对应的会员权益删除
        int tmp = vipPrivilegeLevelService.deleteByPrivilege(id);
        LOGGER.info("deleteByPrivilege:{}", tmp);
        return true;
    }

    @Override
    public void enableOrDisable(String id) {
        VipPrivilege vipPrivilege = vipPrivilegeRoMapper.selectOne(id);
        if (vipPrivilege == null) {
            LOGGER.warn("删除失败，不存在要被删除的数据,参数：" + id);
            throw new ServiceException(4623);
        }
        vipPrivilege.setStatus(!vipPrivilege.getStatus());
        vipPrivilege.setLastUpdate(new Date());
        int result = vipPrivilegeMapper.update(vipPrivilege);
        if (result != 1) {
            throw new ServiceException(4623);
        }
    }
}
