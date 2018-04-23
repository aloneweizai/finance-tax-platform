package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.User;
import com.abc12366.uc.model.UserLoginPasswordWrongCount;
import com.abc12366.uc.model.UserSubscriptionInfo;
import com.abc12366.uc.model.bo.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-20 4:18 PM
 * @since 1.0.0
 */
public interface UserMapper {

    int insert(User user);

    int update(User user);

    int delete(String id);

    int deleteContinuePwdWrong(String id);

    int insertContinuePwdWrong(UserLoginPasswordWrongCount wrongCount);

    int updateContinuePwdWrong(UserLoginPasswordWrongCount wrongCount);

    int updatePhone(User user);

	void qxwxbd(String wxopenid);

    int updateBatch(Map<String, Object> map);

    int updatePassword(User user);

    /**
     * 用户留存率
     * @param inMap
     * @return
     */
    List<UserRetainedRateBO> statisUserRetainedRate(Map<String, Object> inMap);

	User selectByUsernameOrPhone(LoginBO bo);
	
	/**
     * 后台用户列表查询
     *
     * @param map 查询参数
     * @return 用户列表List
     */
    List<UserListBO> selectList(Map map);
    
    
    
    //以下修改全从主库查询
    User selectOne(String id);



    UserBO selectOneByToken(String userToken);

    List<UserBO> selectListExcludedId(String id);

    User selectValidOne(String userId);

    UserBO selectByopenid(String openid);

    /**
     * 查询过期的会员列表
     *
     * @param date 目前定义为当前日期
     * @return List<User> 过期的用户列表
     */
    List<User> selectUserVipList(@Param("date") Date date);

    List<UserLoginPasswordWrongCount> selectContinuePwdWrong(String id);

    UserSimpleInfoBO selectSimple(String userId);

    int getAllNomalCont();

    List<UserBO> getNomalList(Map<String, Object> map);

    User selectByWxUserId(User users);

    List<User> findByHngsNsrsbh(String nsrsbh);

    UserBO selectOneByPhone(String phone);

    /**
     * 根据用户ID查询用户基本表信息
     *
     * @param user 用户条件
     * @return 用户基本表信息
     */
    User selectUserById(User user);

    int updateExp(User user);

    int updatePoints(User user);

	UserJyxx selectByUnameOrPhone(String usernameOrPhone);

	List<UserSubscriptionInfo> selectUserSubscriptionList(Map<String, Object> param);

}
