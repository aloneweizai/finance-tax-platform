package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.UserBsrl;
import com.abc12366.uc.model.bo.UserBsrlBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author lizhongwei
 * @create 2017-01-02
 * UserBsrlMapper数据库操作接口类
 **/

public interface UserBsrlRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    UserBsrl selectByPrimaryKey(@Param("id") String id);

    /**
     * 列表查询
     *
     * @param map
     * @return
     */
    List<UserBsrl> selectList(Map<String, Object> map);

    /**
     * 单个查询
     * @param id
     * @return
     */
    UserBsrlBO selectById(String id);

    /**
     * 根据申报月份查询
     * @param month
     * @return
     */
    UserBsrlBO selectBySbyf(String month);
}