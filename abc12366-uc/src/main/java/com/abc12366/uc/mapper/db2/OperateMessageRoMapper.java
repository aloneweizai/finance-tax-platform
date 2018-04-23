package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.admin.bo.OperateMessageBO;
import com.abc12366.uc.model.admin.bo.YyxxLogBO;
import com.abc12366.uc.model.admin.bo.YyxxLogListBO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-19
 * Time: 14:45
 */
public interface OperateMessageRoMapper {
    List<OperateMessageBO> selectList(@Param("status")String status, @Param("name")String name, @Param("start")Date start, @Param("end")Date end, @Param("now") Date now);

    List<OperateMessageBO> selectValidList(Date date);

    List<String> selectTagIdList(String userId);

    List<YyxxLogBO> selectWebLogList(@Param("userId")String userId, @Param("messageId")String messageId, @Param("type")String type, @Param("start")Date start, @Param("end")Date end);

    OperateMessageBO selectOne(String id);

    List<YyxxLogListBO> operateMessageLog(@Param("userId")String userId, @Param("nickName")String nickName,@Param("messageId")String messageId);

    List<OperateMessageBO> selectFinishedList(@Param("name")String name, @Param("start")Date start, @Param("end")Date end, @Param("now") Date now);
}
