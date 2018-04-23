package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.admin.Organization;
import com.abc12366.uc.model.admin.bo.OrganizationBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OrganizationMapper数据库操作接口类
 **/

public interface OrganizationRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    OrganizationBO selectOrganizationById(@Param("id") String id);

    List<OrganizationBO> selectList(Organization organization);

    List<OrganizationBO> selectChildOrg(String id);

    OrganizationBO selectOrganizationByName(String name);
}