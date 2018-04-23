package com.abc12366.uc.service.admin;



import com.abc12366.uc.model.admin.bo.AdminServiceUserBo;

import java.util.List;
import java.util.Map;

public interface AdminServUserService {

    List<AdminServiceUserBo> selectList(Map<String, Object> map);

    AdminServiceUserBo save(AdminServiceUserBo adminServUserBo);

    AdminServiceUserBo selectAdminService(String id);

    AdminServiceUserBo update(AdminServiceUserBo adminServUserBo);

    String delete(String id);

}
