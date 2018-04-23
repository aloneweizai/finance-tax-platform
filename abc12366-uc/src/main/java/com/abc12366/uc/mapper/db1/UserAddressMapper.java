package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.UserAddress;
import org.apache.ibatis.annotations.Param;

public interface UserAddressMapper {

    int deleteByPrimaryKey(@Param("id") String id);

    int insert(UserAddress record);

    int update(UserAddress record);

    int deleteByIdAndUserId(UserAddress userAddress);
}