package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.bo.CurriculumCollectBo;

import java.util.List;
import java.util.Map;

/**
 * 
 * CurriculumCollectMapper数据库操作接口类
 * 
 **/

public interface CurriculumCollectRoMapper {


    List<CurriculumCollectBo> selectList(String userId);

    int selectExist(Map map);


}