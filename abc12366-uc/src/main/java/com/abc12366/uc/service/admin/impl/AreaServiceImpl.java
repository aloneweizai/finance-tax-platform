package com.abc12366.uc.service.admin.impl;

import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.uc.mapper.db2.AreaRoMapper;
import com.abc12366.uc.mapper.db2.CityRoMapper;
import com.abc12366.uc.mapper.db2.ProvinceRoMapper;
import com.abc12366.uc.model.Area;
import com.abc12366.uc.model.City;
import com.abc12366.uc.model.Province;
import com.abc12366.uc.service.admin.AreaService;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AreaServiceImpl implements AreaService {

    private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    private AreaRoMapper areaRoMapper;

    @Autowired
    private CityRoMapper cityRoMapper;

    @Autowired
    private ProvinceRoMapper provinceRoMapper;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public List<Province> selectProvinceList(String provinceId) {
        return provinceRoMapper.selectProvinceList(provinceId);
    }

    @Override
    public List<City> selectCityList(String cityId) {
        return cityRoMapper.selectCityList(cityId);
    }

    @Override
    public List<City> selectCityByProId(String provinceId) {
        List<City> list;
        if (redisTemplate.hasKey(provinceId + "_City")) {
            list = JSONArray.parseArray(redisTemplate.opsForValue().get(provinceId + "_City"), City.class);
            logger.info("从Redis获取城市信息:" + JSONArray.toJSONString(list));
        } else {
            list = cityRoMapper.selectCityByProId(provinceId);
            redisTemplate.opsForValue().set(provinceId + "_City", JSONArray.toJSONString(list), RedisConstant
                    .DAY_30, TimeUnit.DAYS);
        }
        return list;
    }

    @Override
    public List<Area> selectAreaList(String areaId) {
        return areaRoMapper.selectAreaList(areaId);
    }

    @Override
    public List<Area> selectAreaByCityId(String cityId) {
        List<Area> list;
        if (redisTemplate.hasKey(cityId + "_Area")) {
            list = JSONArray.parseArray(redisTemplate.opsForValue().get(cityId + "_Area"), Area.class);
            logger.info("从Redis获取地区信息:" + JSONArray.toJSONString(list));
        } else {
            list = areaRoMapper.selectAreaByCityId(cityId);
            redisTemplate.opsForValue().set(cityId + "_Area", JSONArray.toJSONString(list), RedisConstant.DAY_30, TimeUnit.DAYS);
        }
        return list;
    }
}
