package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CheatsCollectMapper;
import com.abc12366.bangbang.mapper.db1.CheatsMapper;
import com.abc12366.bangbang.mapper.db2.CheatsCollectRoMapper;
import com.abc12366.bangbang.model.question.Cheats;
import com.abc12366.bangbang.model.question.CheatsCollect;
import com.abc12366.bangbang.model.question.bo.CheatsBo;
import com.abc12366.bangbang.service.CheatsCollectService;
import com.abc12366.bangbang.util.BangBangDtLogUtil;
import com.abc12366.bangbang.util.MapUtil;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/9/15.
 */
@Service
public class CheatsCollectServiceImpl implements CheatsCollectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheatsCollectServiceImpl.class);

    @Autowired
    private CheatsMapper cheatsMapper;

    @Autowired
    private CheatsCollectMapper collectMapper;

    @Autowired
    private CheatsCollectRoMapper collectRoMapper;

    @Autowired
    private BangBangDtLogUtil bangBangDtLogUtil;

    @Override
    public String insert(String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String userId = Utils.getUserId(request);


                CheatsCollect collect = new CheatsCollect();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        collect.setUserId(userId);
        collect.setCollectId(uuid);
        collect.setCheatsId(id);
        collect.setCollectTime(new Date());

        Map map = MapUtil.kv("cheatsId", id, "userId", userId);
        int cnt =  collectRoMapper.selectExist(map);

        if(cnt >0){
            throw new ServiceException(6116);
        }

        int collectCnt = collectRoMapper.selectCollectCnt(id)+1;

        Cheats cheats = new Cheats();
        cheats.setId(id);
        cheats.setCollectNum(collectCnt);

        cheatsMapper.updateByPrimaryKeySelective(cheats);

        int result = collectMapper.insert(collect);

        //帮邦日志记录表
        //日志类型,问题或者秘籍ID,回复ID,来源ID,用户ID,被关注用户ID
//        bangBangDtLogUtil.insertLog(8, collect.getCheatsId(), "", collect.getCollectId(), collect.getUserId(), "");


        return collectCnt+"";
    }

    @Override
    public String delete(String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String userId = Utils.getUserId(request);
        Map map = MapUtil.kv("cheatsId", id, "userId", userId);

        int collectCnt = collectRoMapper.selectCollectCnt(id)-1;

        Cheats cheats = new Cheats();
        cheats.setId(id);
        cheats.setCollectNum(collectCnt);

        cheatsMapper.updateByPrimaryKeySelective(cheats);

        collectMapper.delete(map);


        return collectCnt+"";
    }

    @Override
    public List<CheatsBo> selectList(String userId) {
        LOGGER.info("{}", userId);
        return collectRoMapper.selectList(userId);
    }

    @Override
    public String selectExist(String id, HttpServletRequest request) {
        LOGGER.info("{}", id);
        String userId = Utils.getUserId(request);
        Map map = MapUtil.kv("cheatsId", id, "userId", userId);
        String cnt = collectRoMapper.selectExist(map)+"";
        return cnt;
    }

}
