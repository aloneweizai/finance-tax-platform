package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.FriendlinkMapper;
import com.abc12366.cms.mapper.db2.FriendlinkRoMapper;
import com.abc12366.cms.model.Friendlink;
import com.abc12366.cms.model.bo.FriendlinkBo;
import com.abc12366.cms.service.FriendlinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author xieyanmao
 * @create 2017-06-05 3:10 PM
 * @since 1.0.0
 */
@Service
public class FriendlinkServiceImpl implements FriendlinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FriendlinkServiceImpl.class);

    @Autowired
    private FriendlinkMapper friendlinkMapper;

    @Autowired
    private FriendlinkRoMapper friendlinkRoMapper;

    @Override
    public List<FriendlinkBo> selectList() {
        List<FriendlinkBo> friendlinkBoList = friendlinkRoMapper.selectList();
        LOGGER.info("{}", friendlinkBoList);
        return friendlinkBoList;
    }

    @Override
    public FriendlinkBo selectOneById(String friendlinkId) {
        Friendlink friendlink = friendlinkRoMapper.selectByPrimaryKey(friendlinkId);
        FriendlinkBo friendlinkBo = new FriendlinkBo();
        try {
            try {
                BeanUtils.copyProperties(friendlink, friendlinkBo);
            } catch (Exception e) {
                LOGGER.error("类转换异常：{}", e);
                throw new RuntimeException("类型转换异常：{}", e);
            }
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        LOGGER.info("{}", friendlinkBo);
        return friendlinkBo;
    }

    @Override
    public FriendlinkBo save(FriendlinkBo friendlinkBo) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        friendlinkBo.setFriendlinkId(uuid);
        Friendlink friendlink = new Friendlink();
        try {
            BeanUtils.copyProperties(friendlinkBo, friendlink);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        int count = friendlinkMapper.insert(friendlink);
        LOGGER.info("{}", count);
        return friendlinkBo;
    }

    @Override
    public FriendlinkBo update(FriendlinkBo friendlinkBo) {
        Friendlink friendlink = new Friendlink();
        try {
            BeanUtils.copyProperties(friendlinkBo, friendlink);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        int count = friendlinkMapper.updateByPrimaryKeySelective(friendlink);
        LOGGER.info("{}", count);
        return friendlinkBo;
    }

    @Override
    public String delete(String friendlinkId) {
        int r = friendlinkMapper.deleteByPrimaryKey(friendlinkId);
        LOGGER.info("{}", r);
        return "";
    }
}
