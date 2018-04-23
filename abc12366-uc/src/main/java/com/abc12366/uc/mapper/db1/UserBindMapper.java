package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.DzsbRegisterStat;
import com.abc12366.uc.model.NsrsbhPasswordLog;
import com.abc12366.uc.model.UserDzsb;
import com.abc12366.uc.model.UserHnds;
import com.abc12366.uc.model.UserHngs;
import com.abc12366.uc.model.abc4000.NSRXX;
import com.abc12366.uc.model.bo.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-15 10:18 PM
 * @since 2.0.0
 */
public interface UserBindMapper {
    int dzsbBind(UserDzsb userDzsb);

    int dzsbUnbind(String id);

    int hngsBind(UserHngs userHngs);

    int hngsUnbind(String id);

    int hndsBind(UserHnds userHnds);

    int hndsUnbind(String id);

    int update(UserDzsb userDzsb);

    int updateBatch(Map<String,Object> map);

    int updateHngs(UserHngs userHngs);
    
    
    UserDzsb userDzsbSelectById(String id);

    UserHngs userHngsSelectById(String id);

    UserHnds userHndsSelectById(String id);

    List<UserDzsbListBO> getUserDzsbBind(Map<String, String> map);

    List<UserHngsListBO> getUserhngsBind(Map<String, String> map);

    List<UserHndsBO> getUserhndsBind(Map<String, String> map);

    List<NSRXX> selectListByUserId(String userId);

    List<UserHngs> userHngsListExist(UserHngsInsertBO userHngsInsertBO);

    /**
     * 根据'用户ID，纳税人识别号或社会信用代码'查询是否存在绑定信息
     *
     * @param queryParam 纳税人识别号、社会信用代码必传
     * @return 纳税人绑定信息
     */
    List<UserDzsb> selectListByUserIdAndNsrsbhOrShxydm(UserDzsb queryParam);

    /**
     * 根据'用户ID，纳税人识别号'查询是否存在绑定信息
     *
     * @param queryParam 纳税人识别号必传
     * @return 纳税人绑定信息
     */
    List<UserDzsb> selectListByUserIdAndNsrsbh(UserDzsb queryParam);

    List<UserDzsb> dzsbCount(String userId);

    List<UserHngs> hngsCount(String userId);

    List<UserHnds> hndsCount(String userId);

    List<ShxydmBO> bindCount(String userId);

    List<String> selectListByDate(Date date);

    int deleteDzsb(String id);

	void insertRestPwdLog(NsrsbhPasswordLog passwordLog);

	List<NsrsbhPasswordLog> restPwdLogList(Map map);

	List<DzsbRegisterStat> dzsbRegisterStat(Map<String, String> param);

	List<DzsbRegisterStat> dzsbRegisterStatM(Map<String, String> param);

	List<Map<String, String>> dzsbRegisterStatInfo(String date);

	List<Map<String, String>> findBroup(String userId);

	int updateDzsbgroup(Map<String, String> map);
}
