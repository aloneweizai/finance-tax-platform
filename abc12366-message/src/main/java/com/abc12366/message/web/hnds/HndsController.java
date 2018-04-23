package com.abc12366.message.web.hnds;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.config.ApplicationConfig;
import com.abc12366.message.model.bo.HndsLoginBo;
import com.abc12366.message.service.IHndsBindService;
import com.alibaba.fastjson.JSONObject;
import com.hnds.security.HndsSecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * 国税地税网上办税
 *
 * @author zhushuai 2017-11-6
 */
@Controller
@RequestMapping(path = "/hnds", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class HndsController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HndsController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    protected ApplicationConfig cfg;

    @Autowired
    private IHndsBindService hndsBindService;
    
    @Autowired
	private RedisTemplate<String, String> redisTemplate;

    /**
     * 地税单点登录
     *
     * @param loginBo 登录对象
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked", "static-access"})
    @PostMapping("/login")
    public ResponseEntity wsbsLogin(@RequestBody HndsLoginBo loginBo) {
    	LOGGER.info("湖南地税登录: {}", JSONObject.toJSONString(loginBo));
        String taxurl = loginBo.toLoginStr(cfg.getHndsUrl());
        LOGGER.info("请求地址:" + taxurl);
        HttpEntity httpEntity = new HttpEntity(new HttpHeaders());
        JSONObject jsonObject = null;
        try {
            long startTime = System.currentTimeMillis();
            ResponseEntity<String> responseEntity = restTemplate.exchange(taxurl, HttpMethod.GET, httpEntity, String
                    .class, new Object());
            long endTime = System.currentTimeMillis();
            jsonObject = JSONObject.parseObject(responseEntity.getBody());
            LOGGER.info("湖南地税登录返回: {}, 耗时:{}", jsonObject, endTime - startTime);
            if ("00".equals(jsonObject.getString("retcode"))) {
                if (!loginBo.getNsrmc().equals(jsonObject.getString("nsrmc"))) {
                	LOGGER.info("湖南地税登录返回,纳税人名称不一致>>>传入纳税人名称:{},接口返回纳税人名称:{}", loginBo.getNsrmc(), jsonObject.getString("nsrmc"));
                    return ResponseEntity.ok(Utils.bodyStatus(9999, "当前纳税人识别号不允许单点登录"));
                }
                jsonObject.put("sign", HndsSecurityUtils.encodeDES(cfg.getHndsKey(), loginBo.getMainuserid(), loginBo
						.getChangePwd()));
                HashMap<String, Object> map = jsonObject.parseObject(responseEntity.getBody(), HashMap.class);
                map.put("username", loginBo.getMainuserid());
                map.put("userid", loginBo.getUserId());
                map.put("password", loginBo.getMm());
                if (!StringUtils.isEmpty(loginBo.getSubuserid())) {
                    map.put("subuserid", loginBo.getSubuserid());
                }
                //登录成功自动绑定
                LOGGER.info("湖南地税登录,开始绑定纳税人关系: {}", JSONObject.toJSONString(map));
                int n = hndsBindService.bindHnds(map);
                if (n == -1) {
                    LOGGER.info("湖南地税登录绑定关系已存在");
                }
                
                redisTemplate.delete(loginBo.getUserId()+"_HndsList");
                return ResponseEntity.ok(Utils.kv("data", jsonObject));
            } else {
                return ResponseEntity.ok(Utils.bodyStatus(jsonObject.getString("retcode"), jsonObject.getString
						("retinfo")));
            }
        } catch (Exception e) {
            LOGGER.error("RestClient调用服务出现异常: " + e.getMessage(), e);
            return ResponseEntity.ok(Utils.bodyStatus(9999, e.getMessage()));
        }
    }


}
