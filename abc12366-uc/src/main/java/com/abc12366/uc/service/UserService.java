package com.abc12366.uc.service;


import com.abc12366.uc.model.User;
import com.abc12366.uc.model.UserSubscriptionInfo;
import com.abc12366.uc.model.bo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-22 10:15 AM
 * @since 1.0.0
 */
public interface UserService {

    /**
     * 后台查询用户列表
     *
     * @param map  用户信息
     * @param page 当前页
     * @param size 每页大小
     * @return 用户列表List
     */
    List<UserListBO> selectList(Map<String, Object> map, int page, int size);

    User selectUser(String userId);

    Map selectOne(String userId);

    UserBO update(UserUpdateBO userUpdateBO);

    UserBO selectByUsernameOrPhone(String usernameOrPhone);

    UserBO delete(String userId);

    UserBO authAndRefreshToken(String token);

    Boolean updatePassword(PasswordUpdateBO passwordUpdateBO, HttpServletRequest request);

    void enableOrDisable(String id, String status);

    void updateUserVipInfo(String userId, String vipLevel, boolean isGive);

    UserBO selectByopenid(String openid);

    /**
     * 自动会员失效
     */
    void automaticUserCancel();

    /**
     * 用户更换手机号码
     */
    UserBO updatePhone(UserPhoneBO bo);

    /**
     * 用户未登录状态下获取用户简单信息：用户编号，用户昵称，用户头像，擅长领域
     *
     * @param userId
     * @return
     */
    UserSimpleInfoBO selectSimple(String userId);

    /**
     * 用户登录状态下发送短信接口
     *
     * @param sendCodeBO
     */
    void loginedSendCode(LoginedSendCodeBO sendCodeBO);

    /**
     * 用户已登录状态下通过用户ID和验证码校验验证码
     *
     * @param verifyCodeBO
     */
    void loginedVerifyCode(LoginedVerifyCodeBO verifyCodeBO);

    /**
     * 用户绑定手机号码
     *
     * @param bindPhoneBO
     */
    void bindPhone(BindPhoneBO bindPhoneBO);

    /**
     * 用户手机+验证码登录专用接口
     *
     * @param sendCodeBO
     */
    void phoneLoginSendCode(SendPhoneCodeParam sendCodeBO);

    /**
     * 旧手机号码有效性校验
     *
     * @param oldPhone
     */
    void verifyOldPhone(oldPhoneBO oldPhone);

    /**
     * 用户是否实名认证
     *
     * @return
     */
    IsRealNameBO isRealName();

    /**
     * 根据用户ID获取用户信息和用户扩展信息（供后台管理系统使用，敏感信息不做模糊化处理）
     *
     * @param id
     * @return
     */
    Map selectOneForAdmin(String id);

    /**
     * 获取正常用户总数
     *
     * @return
     */
    int getAllNomalCont();

    /**
     * 分页获取正常用户信息
     *
     * @param map
     * @return
     */
    List<UserBO> getNomalList(Map<String, Object> map);

    /**
     * 微信绑定关系修改
     *
     * @param userUpdateDTO
     * @return
     */
    int changeWxBdxx(UserUpdateBO userUpdateDTO);

    /**
     * 通过绑定的电子申报纳税人识别号查询用户信息
     *
     * @param nsrsbh
     * @return
     */
    List<User> findByDzsbNsrsbh(String nsrsbh);
    
    /**
     * 根据手机号码查询用户
     *
     * @param phone 手机号码
     * @return UserBO
     */
    UserBO selectOneByPhone(String phone);

    /**
     * 根据用户ID查询用户基本表信息
     *
     * @param user 用户条件
     * @return 用户基本表信息
     */

    User selectUserById(User user);
    /**
     * 统计用户，统计维度为【月份】
     * @param map
     * @return
     */
    List<UserStatisBO> statisUserByMonth(Map<String, Object> map);

    /**
     * 用户流失率统计
     * @param map
     * @return
     */
    UserLossRateBO statisUserLossRate(Map<String, Object> map);

    /**
     * 用户活跃度统计(明细)接口
     * @return Object
     */
    Object userLivenessDetail(String type, String start, String end);
    /**
     * 用户会员等级统计接口
     * @return ExpLevelStatistic
     */
    List<ExpLevelStatistic> userExpLevel(String year,int page,int size);
    /**
     * 用户活跃度统计(概况)接口
     * @return UserLivenessSurveyBO
     */
    UserLivenessSurveyBO userLivenessSurvey();
    /**
     * 用户会员等级统计接口
     * @return VipLevelStatistic
     */
    List<VipLevelStatistic> userVip(String year);

    /**
     *用户留存率统计
     * @param map
     * @return
     */
    List<UserRetainedRateListBO> statisUserRetainedRate(Map<String, Object> map);

    /**
     * 用户消费能力分析
     * @param map
     * @return
     */
    List<UserExprotInfoBO>  statisUserConsumeLevel(Map<String, Object> map);

    UserRFMBO statisUserRFM(Map<String, Object> map);

    /**
     * 用户活跃度统计详情中包含的用户信息接口
     * @param timeInterval
     * @param page
     * @param size
     * @return
     */
    List<UserListBO> userLivenessDetailUinfo(String timeInterval, int page, int size);

    /**
     * 用户年龄分布统计
     * @param map
     * @return
     */
    List<UserAgeBO> statisUserAge(Map<String, Object> map);

    /**
     * 用户性别分布统计
     * @param map
     * @return
     */
    List<UserSexBO> statisUserSex(Map<String, Object> map);

    /**
     * 用户服务企业情况统计
     * @param map
     * @return
     */
    UserBindBO statisUserBind(Map<String, Object> map);

    /**
     * 用户服务企业情况统计-用户列表
     * @param map
     * @return
     */
    List<UserBO> statisUserBindList(Map<String, Object> map);

    /**
     * 用户年龄分布统计-用户列表
     * @param map
     * @param startAge
     * @param endAge
     * @return
     */
    List<UserBO> statisUserAgeList(Map<String, Object> map, Integer startAge, Integer endAge);

    /**
     *用户性别分布统计-用户列表
     * @param map
     * @return
     */
    List<UserBO> statisUserSexList(Map<String, Object> map);

    /**
     * 重置用户密码
     * @param resetPwdBO
     */
    void resetPassword(UserResetPwdBO resetPwdBO);

    /**
     * 用户建议信息查询
     * @param usernameOrPhone
     * @return
     */
	UserJyxx selectByUnameOrPhone(String usernameOrPhone);

	/**
	 * 发送消息时查询单条订阅消息设置
	 * @param userId
	 * @param type
	 * @param busiType
	 * @return
	 */
	List<UserSubscriptionInfo> selectUserSubscriptionList(Map<String, Object> param);
}
