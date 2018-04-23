package com.abc12366.uc.service.admin.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.OrganizationMapper;
import com.abc12366.uc.mapper.db2.AdminRoMapper;
import com.abc12366.uc.mapper.db2.OrganizationRoMapper;
import com.abc12366.uc.model.admin.Admin;
import com.abc12366.uc.model.admin.Organization;
import com.abc12366.uc.model.admin.bo.OrganizationBO;
import com.abc12366.uc.model.admin.bo.OrganizationUpdateBO;
import com.abc12366.uc.service.admin.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private static Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationRoMapper organizationRoMapper;

    @Autowired
    private AdminRoMapper adminRoMapper;

    @Override
    public List<OrganizationBO> selectList(Organization organization) {
        return organizationRoMapper.selectList(organization);
    }

    @Transactional("db1TxManager")
    @Override
    public Organization addOrganization(OrganizationBO organizationBO) {
        //验证部门信息
        validateOrg(organizationBO);
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationBO, organization);
        organization.setId(Utils.uuid());
        Date date = new Date();
        organization.setCreateTime(date);
        organization.setLastUpdate(date);
        int insert = organizationMapper.insert(organization);
        if (insert != 1) {
            LOGGER.warn("插入失败，参数：{}", organization.toString());
            throw new ServiceException(4101);
        }
        return organization;
    }

    /**
     * 验证部门信息
     *
     * @param organizationBO 部门对象
     */
    private void validateOrg(OrganizationBO organizationBO) {
        if (isOrgName(organizationBO.getName())) {
            LOGGER.warn("已存在该机构名称{}", organizationBO);
            throw new ServiceException(4099);
        }
        if (isOrgParentId(organizationBO.getParentId())) {
            LOGGER.warn("该机构已禁用{}", organizationBO);
            throw new ServiceException(4099);
        }

    }

    /**
     * 验证部门是否被禁用
     * @param parentId 父节点ID
     */
    public boolean isOrgParentId(String parentId) {
        OrganizationBO bo = organizationRoMapper.selectOrganizationById(parentId);
        return bo != null && bo.getStatus() != null && !bo.getStatus();
    }

    /**
     * 验证部门名称是否存在
     *
     * @param orgName 部门名称
     */
    public boolean isOrgName(String orgName) {
        OrganizationBO bo = organizationRoMapper.selectOrganizationByName(orgName);
        return bo != null;
    }

    @Override
    public OrganizationBO selectOrganizationById(String id) {
        return organizationRoMapper.selectOrganizationById(id);
    }

    @Transactional("db1TxManager")
    @Override
    public OrganizationBO updateOrganization(OrganizationBO organizationBO) {
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationBO, organization);
        Date date = new Date();
        organization.setLastUpdate(date);
        int update = organizationMapper.update(organization);
        if (update != 1) {
            LOGGER.warn("更新失败，参数：{}", organization.toString());
            throw new ServiceException(4102);
        }
        OrganizationBO bo = new OrganizationBO();
        BeanUtils.copyProperties(organizationBO, bo);
        bo.setLastUpdate(date);
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public void deleteOrganizationById(String id) {
        int del = organizationMapper.deleteByPrimaryKey(id);
        if (del != 1) {
            LOGGER.warn("删除失败，id：{}", id);
            throw new ServiceException(4103);
        }
    }

    @Transactional("db1TxManager")
    @Override
    public void enable(OrganizationUpdateBO updateBO) {
        String[] idArray = updateBO.getId().split(",");
        Organization organization = new Organization();
        organization.setLastUpdate(new Date());
        for (String orgId : idArray) {
            organization.setId(orgId);
            //查询是否存在该部门
            OrganizationBO organizationBO = organizationRoMapper.selectOrganizationById(orgId);
            if(organizationBO == null){
                throw new ServiceException(4999,"部门不存在");
            }
            Boolean status = updateBO.getStatus();
            organization.setStatus(status);
            //停用时
            if(!status){
                //查询部门下所有的用户
                List<Admin> admins = adminRoMapper.selectAdminByOrgId(orgId);
                if(admins != null && admins.size() > 0){
                    throw new ServiceException(4999,"需要先停用"+organizationBO.getName()+"部门下的用户，再停用该部门");
                }
            }
            int update = organizationMapper.update(organization);
            if (update != 1) {
                LOGGER.warn("修改失败，id：{}", organization.toString());
                throw new ServiceException(4103);
            }
            //查找子节点
            List<OrganizationBO> boList = organizationRoMapper.selectChildOrg(updateBO.getId());
            for (OrganizationBO bo : boList) {
                bo.setStatus(status);
                Organization org = new Organization();
                BeanUtils.copyProperties(bo, org);
                int upd = organizationMapper.update(org);
                if (upd != 1) {
                    LOGGER.warn("修改子节点状态失败{}", upd);
                    throw new ServiceException(4097);
                }
            }
        }
    }

    @Override
    public List<OrganizationBO> selectChildOrg(String id) {
        return organizationRoMapper.selectChildOrg(id);
    }

    @Override
    public OrganizationBO selectOrganizationByName(String name) {
        return organizationRoMapper.selectOrganizationByName(name);
    }

    @Transactional("db1TxManager")
    @Override
    public void disableAll() {
        Organization organization = new Organization();
        List<OrganizationBO> organizationBOs = organizationRoMapper.selectList(organization);
        for (OrganizationBO temp : organizationBOs) {
            organization.setId(temp.getId());
            organization.setStatus(false);
            int enable = organizationMapper.update(organization);
            if (enable != 1) {
                LOGGER.warn("修改失败，id：{}", organization.toString());
                throw new ServiceException(4102);
            }
        }
    }
}
