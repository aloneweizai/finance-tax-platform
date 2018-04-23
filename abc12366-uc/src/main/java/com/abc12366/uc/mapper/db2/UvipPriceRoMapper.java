package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.UvipPrice;
import com.abc12366.uc.model.order.bo.UvipPriceBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UvipPriceMapper数据库操作接口类
 **/

public interface UvipPriceRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    UvipPrice selectByPrimaryKey(@Param("id") String id);


	List<UvipPriceBO> selectList(UvipPrice uvipPrice);

	UvipPrice selectByLevel(UvipPrice uvip);

}