package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.Area;
import com.abc12366.uc.model.City;
import com.abc12366.uc.model.Province;
import com.abc12366.uc.service.admin.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 省市区查询控制器
 *
 * @author lizhongwei
 * @create 2017-05-02 10:08 AM
 * @since 1.0.0
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class AreaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    /**
     * 省列表
     *
     * @param provinceId 省ID
     * @return ResponseEntity 省列表响应实体
     */
    @GetMapping(path = "/province")
    public ResponseEntity selectProvinceList(@RequestParam(value = "provinceId", required = false) String provinceId) {
        List<Province> provinceList = areaService.selectProvinceList(provinceId);
        LOGGER.info("{}", provinceList);
        return ResponseEntity.ok(Utils.kv("dataList", provinceList));
    }

    /**
     * 市列表
     *
     * @param cityId 市ID
     * @return ResponseEntity 市列表响应实体
     */
    @GetMapping(path = "/city")
    public ResponseEntity selectCityList(@RequestParam(value = "cityId", required = false) String cityId) {
        List<City> cityList = areaService.selectCityList(cityId);
        LOGGER.info("{}", cityList);
        return ResponseEntity.ok(Utils.kv("dataList", cityList));
    }

    /**
     * 根据省ID查市
     *
     * @param provinceId 省ID
     * @return ResponseEntity 市列表响应实体
     */
    @GetMapping(path = "/city/{provinceId}")
    public ResponseEntity selectCityByProId(@PathVariable("provinceId") String provinceId) {
        List<City> cityList = areaService.selectCityByProId(provinceId);
        LOGGER.info("{}", cityList);
        return ResponseEntity.ok(Utils.kv("dataList", cityList));
    }

    /**
     * 区列表
     *
     * @param areaId 区ID
     * @return ResponseEntity 区列表响应实体
     */
    @GetMapping(path = "/area")
    public ResponseEntity selectAreaList(@RequestParam(value = "areaId", required = false) String areaId) {
        List<Area> areaList = areaService.selectAreaList(areaId);
        LOGGER.info("{}", areaList);
        return ResponseEntity.ok(Utils.kv("dataList", areaList));
    }

    /**
     * 根据市ID查区
     *
     * @return ResponseEntity 区列表响应实体
     */
    @GetMapping(path = "/area/{cityId}")
    public ResponseEntity selectAreaByCityId(@PathVariable("cityId") String cityId) {
        List<Area> areaList = areaService.selectAreaByCityId(cityId);
        LOGGER.info("{}", areaList);
        return ResponseEntity.ok(Utils.kv("dataList", areaList));
    }


    /**
     * 查询所有省市区
     *
     * @return ResponseEntity 省市区列表响应实体
     */
    @GetMapping(path = "/provincecityarea")
    public ResponseEntity selectAll() {

        List<Province> provinceList = areaService.selectProvinceList("");
        List<City> cityList = areaService.selectCityList("");
        List<Area> areaList = areaService.selectAreaList("");
        LOGGER.info("{}", provinceList, cityList, areaList);
        return (provinceList == null) && (cityList == null) && (areaList == null) ?
                ResponseEntity.ok(null) :
                ResponseEntity.ok(Utils.kv("provinceList", provinceList, "cityList", cityList, "areaList", areaList));
    }

    /**
     * 根据省ID、市ID、区ID查询名称
     *
     * @param provinceId 省ID
     * @param cityId     市ID
     * @param areaId     区ID
     * @return ResponseEntity 省市区名称
     */
    @GetMapping("/provinceorcityorarea")
    public ResponseEntity selectProvinceOrCityOrArea(
            @RequestParam(value = "provinceId", required = false) String provinceId,
            @RequestParam(value = "cityId", required = false) String cityId,
            @RequestParam(value = "areaId", required = false) String areaId) {

        LOGGER.info("{},{},{}", provinceId, cityId, areaId);
        Province province = null;
        City city = null;
        Area area = null;
        if (!StringUtils.isEmpty(provinceId)) {
            List<Province> provinceList = areaService.selectProvinceList(provinceId);
            if (provinceList.size() == 1) {
                province = provinceList.get(0);
            }
        }
        if (!StringUtils.isEmpty(cityId)) {
            List<City> cityList = areaService.selectCityList(cityId);
            if (cityList.size() == 1) {
                city = cityList.get(0);
            }
        }
        if (!StringUtils.isEmpty(areaId)) {
            List<Area> areaList = areaService.selectAreaList(areaId);
            if (areaList.size() == 1) {
                area = areaList.get(0);
            }
        }
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("province", province, "city", city, "area", area));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
}
