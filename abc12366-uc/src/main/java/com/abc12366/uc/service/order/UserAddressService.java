package com.abc12366.uc.service.order;

import com.abc12366.uc.model.order.UserAddress;
import com.abc12366.uc.model.order.bo.UserAddressBO;
import com.abc12366.uc.model.order.bo.UserAddressUpdateBO;

import java.util.List;

/**
 * Created by MY on 2017-05-15.
 * 用户地址信息接口类
 */
public interface UserAddressService {

    /**
     * 查询user所有地址
     *
     * @param userId
     * @return
     */
    List<UserAddress> selectList(String userId);

    /**
     * 查询地址详情
     *
     * @param userAddress
     * @return
     */
    UserAddressBO selectOne(UserAddress userAddress);

    /**
     * 更新地址信息
     *
     * @param userAddressBO
     * @return
     */
    UserAddressBO updateUserAddress(UserAddressBO userAddressBO);

    /**
     * 删除地址信息
     *
     * @param userAddressBO
     * @return
     */
    int deleteByIdAndUserId(UserAddressBO userAddressBO);

    /**
     * 新增地址信息
     *
     * @param userAddressBO
     * @return
     */
    UserAddressBO addUserAddress(UserAddressBO userAddressBO);

    UserAddressUpdateBO setDefaultAddrees(UserAddressUpdateBO userAddressBO);

    List<UserAddressBO> selectBOList(String userId);
}
