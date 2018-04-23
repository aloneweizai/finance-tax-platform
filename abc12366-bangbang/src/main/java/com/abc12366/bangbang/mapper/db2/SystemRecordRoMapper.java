package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.SystemRecord;
import com.abc12366.bangbang.model.bo.SystemRecordBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-22
 * @since 1.0.0
 */
public interface SystemRecordRoMapper {

    List<SystemRecordBO> selectList(Map map);

    List<SystemRecordBO> findStay(SystemRecord systemRecord);

    SystemRecordBO selectOne(SystemRecord systemRecord);

    /**
     * 查询当天是否有数据
     * @param yyyyMMdd
     * @return
     */
    int selectRecordCount(@Param("yyyyMMdd")String yyyyMMdd);
}
