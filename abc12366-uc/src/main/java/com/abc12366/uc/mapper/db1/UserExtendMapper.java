package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.UserExtend;
import com.abc12366.uc.model.bo.UserExtendListBO;

import java.util.List;
import java.util.Map;

/**
 * Created by lgy on 2017-05-05.
 */
public interface UserExtendMapper {
    int insert(UserExtend userExtend);

    int delete(String userId);

    int update(UserExtend userExtend);
    
    //以下合并查询主库
    UserExtend selectOne(String userId);

    List<UserExtendListBO> selectList(Map map);

    UserExtend isRealName(String userId);

    UserExtend selectOneForAdmin(String userId);

    /**
     * 查询认证状态为【待认证】数量
     * @return Integer 数量
     */
    Integer selectTodoListCount();
}
