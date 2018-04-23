package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.FollowLecturer;
import com.abc12366.bangbang.model.bo.FollowLecturerBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * FollowLecturerMapper数据库操作接口类
 **/

public interface FollowLecturerRoMapper {

    /**
     * 单个查询
     */
    FollowLecturer selectOne(@Param("userId") String userId, @Param("lecturerId")String lecturerId);

    /**
     * 根据讲师UserId查询所有用户ID
     *
     * @param userId 讲师UserId
     * @return 所有用户ID
     */
    List<String> selectUserIdListByLecturerId(@Param("userId") String userId);


    /**
     * 列表查询
     *
     * @param map 查询条件
     * @return 用户关注见识列表
     */
    List<FollowLecturerBO> selectFollowLecturerList(Map<String, Object> map);
}