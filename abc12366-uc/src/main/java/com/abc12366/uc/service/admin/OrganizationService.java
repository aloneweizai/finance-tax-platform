package com.abc12366.uc.service.admin;

import com.abc12366.uc.model.admin.Organization;
import com.abc12366.uc.model.admin.bo.OrganizationBO;
import com.abc12366.uc.model.admin.bo.OrganizationUpdateBO;

import java.util.List;

/**
 * @description：组织管理
 */
public interface OrganizationService {

    /**
     * 查询组织列表
     *
     * @param organization
     * @return
     */
    List<OrganizationBO> selectList(Organization organization);

    /**
     * 添加组织
     *
     * @param organizationBO
     */
    Organization addOrganization(OrganizationBO organizationBO);

    /**
     * 根据id查找组织
     *
     * @param id
     * @return
     */
    OrganizationBO selectOrganizationById(String id);

    /**
     * 更新组织
     *
     * @param organizationBO
     */
    OrganizationBO updateOrganization(OrganizationBO organizationBO);

    /**
     * 根据id删除组织
     *
     * @param id
     */
    void deleteOrganizationById(String id);

    void disableAll();

    void enable(OrganizationUpdateBO updateBO);

    List<OrganizationBO> selectChildOrg(String id);

    OrganizationBO selectOrganizationByName(String name);
}
