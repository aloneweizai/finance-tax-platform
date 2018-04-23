package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.ExpressComp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ExpressCompMapper数据库操作接口类
 **/

public interface ExpressCompRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    ExpressComp selectByPrimaryKey(@Param("id") String id);


    List<ExpressComp> selectList(ExpressComp expressComp);

    ExpressComp selectByCompName(@Param("compName")String compName);

    ExpressComp selectByCompCode(@Param("compCode")String compCode);
}