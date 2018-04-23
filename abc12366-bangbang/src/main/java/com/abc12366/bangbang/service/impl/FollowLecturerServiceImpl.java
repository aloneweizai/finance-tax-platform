package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.FollowLecturerMapper;
import com.abc12366.bangbang.mapper.db2.FollowLecturerRoMapper;
import com.abc12366.bangbang.model.FollowLecturer;
import com.abc12366.bangbang.model.bo.FollowLecturerBO;
import com.abc12366.bangbang.service.FollowLecturerService;
import com.abc12366.gateway.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户关注讲师表服务实现类
 *
 * @author lizhongwei
 * @date 2017-03-12
 * @since 1.0.0
 */
@Service
public class FollowLecturerServiceImpl implements FollowLecturerService {

    @Autowired
    private FollowLecturerRoMapper followLecturerRoMapper;

    @Autowired
    private FollowLecturerMapper followLecturerMapper;


    @Override
    public boolean followOrUnfollow(FollowLecturer bo) {
        FollowLecturer data = followLecturerRoMapper.selectOne(bo.getUserId(), bo.getLecturerId());
        Date now = new Date();
        //查找是否有关注，是：修改，否：新增
        if (data == null) {
            data = new FollowLecturer();
            data.setId(Utils.uuid());
            data.setUserId(bo.getUserId());
            data.setLecturerId(bo.getLecturerId());
            data.setCreateTime(now);
            data.setLastUpdate(now);
            data.setStatus(true);
            followLecturerMapper.insert(data);
        } else {
            data.setStatus(!data.getStatus());
            data.setLastUpdate(now);
            followLecturerMapper.update(data);
        }
        return data.getStatus();
    }

    @Override
    public boolean selectIsFollow(FollowLecturer bo) {
        FollowLecturer data = followLecturerRoMapper.selectOne(bo.getUserId(), bo.getLecturerId());
        return data != null && data.getStatus();
    }

    @Override
    public List<String> selectUserIdListByLecturerId(String userId) {
        return followLecturerRoMapper.selectUserIdListByLecturerId(userId);
    }

    @Override
    public List<FollowLecturerBO> selectList(Map<String, Object> map) {
        return followLecturerRoMapper.selectFollowLecturerList(map);
    }
}
