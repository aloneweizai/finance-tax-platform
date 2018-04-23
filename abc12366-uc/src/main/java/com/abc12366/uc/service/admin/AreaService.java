package com.abc12366.uc.service.admin;

import com.abc12366.uc.model.Area;
import com.abc12366.uc.model.City;
import com.abc12366.uc.model.Province;

import java.util.List;

/**
 * 查询省市区列表、名称服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-24 3:09 PM
 * @since 1.0.0
 */
public interface AreaService {

    /**
     * 查询省列表
     *
     * @param provinceId 省ID
     * @return List<Province> {@link com.abc12366.uc.model.Province}
     */
    List<Province> selectProvinceList(String provinceId);

    /**
     * 查询市列表
     *
     * @param cityId 市ID
     * @return List<City> {@link com.abc12366.uc.model.City}
     */
    List<City> selectCityList(String cityId);

    /**
     * 根据省ID查询市列表
     *
     * @param provinceId 省ID
     * @return List<City> {@link com.abc12366.uc.model.City}
     */
    List<City> selectCityByProId(String provinceId);

    /**
     * 查询区列表
     *
     * @param areaId 区ID
     * @return List<Area> {@link com.abc12366.uc.model.Area}
     */
    List<Area> selectAreaList(String areaId);

    /**
     * 根据城市ID查区域
     *
     * @param cityId 市ID
     * @return List<Area> {@link com.abc12366.uc.model.Area}
     */
    List<Area> selectAreaByCityId(String cityId);
}
