package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.FollowLecturer;
import com.abc12366.bangbang.model.bo.FollowLecturerBO;

import java.util.List;
import java.util.Map;

/**
 * 用户关注讲师表服务接口
 *
 * @author lizhongwei
 * @date 2017-03-12
 * @since 1.0.0
 */
public interface FollowLecturerService {

    /**
     * 根据讲师UserId查询所有用户ID
     *
     * @param userId 讲师UserId
     * @return 所有用户ID
     */
    List<String> selectUserIdListByLecturerId(String userId);

    /**
     * 关注或取消关注讲师
     */
    boolean followOrUnfollow(FollowLecturer bo);

    /**
     * 查询讲师是否被关注
     */
    boolean selectIsFollow(FollowLecturer bo);

    /**
     * 查询用户关注讲师列表
     *
     * @param map 查询条件
     * @return 关注列表
     */
    List<FollowLecturerBO> selectList(Map<String, Object> map);
}
