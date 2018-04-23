package com.abc12366.uc.service.admin;



import com.abc12366.uc.model.admin.bo.AdminServiceBo;

import java.util.List;
import java.util.Map;

public interface AdminServService {

    List<AdminServiceBo> selectList(Map<String, Object> map);

    AdminServiceBo save(AdminServiceBo adminServBo);

    AdminServiceBo selectAdminService(String adminServId);

    AdminServiceBo update(AdminServiceBo adminServBo);

    String delete(String adminServId);

}
