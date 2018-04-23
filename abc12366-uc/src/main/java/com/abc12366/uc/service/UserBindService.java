package com.abc12366.uc.service;

import com.abc12366.uc.model.NsrsbhPasswordLog;
import com.abc12366.uc.model.UserDzsb;
import com.abc12366.uc.model.UserHnds;
import com.abc12366.uc.model.UserHngs;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.model.tdps.TY21Xml2Object;
import com.abc12366.uc.wsbssoa.response.HngsNsrLoginResponse;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 电子申报、电子税局、湖南地税用户绑定相关服务
 *
 * @author liuguiyao 435720953@qq.com
 * @date 2017-07-25
 */
public interface UserBindService {

    /**
     * 用户绑定纳税人（电子申报）
     *
     * @param userDzsbInsertBO 纳税人信息
     * @param request          HttpServletRequest
     * @return 纳税人信息
     * @throws Exception 访问网络、解包异常
     */
    UserDzsbBO dzsbBind(UserDzsbInsertBO userDzsbInsertBO, HttpServletRequest request) throws Exception;

    /**
     * 用户解除纳税人绑定（电子申报）
     *
     * @param id 绑定表主键
     * @return 是否解绑成功
     */
    boolean dzsbUnbind(String id);

    /**
     * 绑定湖南国税用户
     *
     * @param userHngsInsertBO 湖南国税用户信息
     * @param request          HttpServletRequest
     * @return 国税用户登陆信息
     * @throws Exception 网络异常
     */
    UserHngsBO hngsBind(UserHngsInsertBO userHngsInsertBO, HttpServletRequest request) throws Exception;

    /**
     * 解绑湖南国税用户
     *
     * @param id 湖南国税绑定表主键
     * @return 是否解绑成功
     */
    boolean hngsUnbind(String id);

    /**
     * 绑定湖南地税用户
     *
     * @param userHndsInsertBO 地税用户信息
     * @param request          HttpServletRequest
     * @return 地税登陆信息
     */
    UserHndsBO hndsBind(UserHndsInsertBO userHndsInsertBO, HttpServletRequest request);

    /**
     * 解绑湖南地税用户
     *
     * @param id 地税绑定表主键
     * @return 是否解绑成功
     */
    boolean hndsUnbind(String id);

    /**
     * 根据用户ID查询电子申报绑定列表
     *
     * @param map 用户ID
     * @return 电子申报绑定列表
     */
    List<UserDzsbListBO> getUserDzsbBind(Map<String, String> map);

    /**
     * 根据用户ID查询电子税局绑定列表
     *
     * @param map 用户ID
     * @return 电子税局绑定列表
     */
    List<UserHngsListBO> getUserhngsBind(Map<String, String> map);

    /**
     * 根据用户ID查询湖南地税绑定列表
     *
     * @param map 用户ID
     * @return 湖南地税绑定列表
     */
    List<UserHndsBO> getUserhndsBind(Map<String, String> map);

    /**
     * 电子申报纳税人登录绑定
     *
     * @param login   登录信息
     * @param request HttpServletRequest
     * @return 纳税人信息
     * @throws Exception 访问网络、解包异常
     */
    TY21Xml2Object nsrLogin(NsrLogin login, HttpServletRequest request) throws Exception;

    /**
     * 重置电子申报用户密码
     *
     * @param data    重置密码信息
     * @param request HttpServletRequest
     * @throws IOException         网络异常
     * @throws MarshalException    解包异常
     * @throws ValidationException 验证异常
     */
    void resetPassword(NsrResetPwd data, HttpServletRequest request) throws IOException, MarshalException,
            ValidationException;

    /**
     * 修改电子申报密码
     *
     * @param data 修改密码信息
     * @throws ValidationException 验证异常
     */
    void updatePassword(UpdatePwd data, HttpServletRequest request) throws ValidationException;

    /**
     * 电子税局纳税人登录绑定
     *
     * @param userHngsInsertBO 登陆信息
     * @param request          HttpServletRequest
     * @return 用户登陆信息
     * @throws Exception 网络异常
     */
    HngsNsrLoginResponse loginWsbsHngs(UserHngsInsertBO userHngsInsertBO, HttpServletRequest request) throws Exception;

    /**
     * 调用电子税局查看用户是否实名认证
     *
     * @param sfzjhm  身份证件号码
     * @param xm      姓名
     * @param request HttpServletRequest
     * @return 是否实名认证
     */
    boolean isRealNameValidatedDzsj(String sfzjhm, String xm, HttpServletRequest request);

    /**
     * 取消电子申报绑定
     */
    void automaticBindCancel();

    /**
     * 供财税专家软件登录电子税局方法
     *
     * @param login   登录纳税密码等参数封装类
     * @param request HttpServletRequest
     * @return HngsNsrLoginResponse
     */
    HngsNsrLoginResponse nsrLoginDzsj(UserHngsInsertBO login, HttpServletRequest request);

    /**
     * 更新电子申报绑定关系
     * @param userId 用户id
     * @param nsrsbh 纳税人识别号
     * @return UserDzsbListBO
     */
    UserDzsbListBO updateDzsb(String userId, String nsrsbh);

    /**
     * 更新电子申报绑定关系
     * @param userId 用户id
     * @param ty21Object 电子申报返回税号信息
     * @return UserDzsb
     */
    UserDzsb updateDzsb(String id,String userId, TY21Xml2Object ty21Object,String bdgroup);

    /**
     * 查询电子申报绑定关系详情
     * @param id 绑定关系id
     * @return ResponseEntity
     */
    UserDzsb dzsbDetail(String id);

    /**
     * 查询湖南国税绑定关系详情
     * @param id 绑定关系id
     * @return ResponseEntity
     */
    UserHngs hngsDetail(String id);

    /**
     * 查询湖南地税绑定关系详情
     * @param id 绑定关系id
     * @return ResponseEntity
     */
    UserHnds hndsDetail(String id);
    
    /**
     * TY11解析函数
     * @param resMap
     * @param nsrsbh
     * @return
     * @throws ValidationException
     */
    TY21Xml2Object analyzeXmlTY11(Map resMap, String nsrsbh) throws ValidationException;

	List<NsrsbhPasswordLog> restPwdLogList(Map map,int page,int size);

	List<Map<String, String>> findBroup(String userId);

	int updateDzsbgroup(Map<String, String> map);
}
