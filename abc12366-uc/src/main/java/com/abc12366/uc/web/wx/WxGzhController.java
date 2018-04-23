package com.abc12366.uc.web.wx;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.weixin.bo.WxUseToken;
import com.abc12366.uc.model.weixin.bo.gzh.GzhInfo;
import com.abc12366.uc.model.weixin.bo.gzh.WxJsConfig;
import com.abc12366.uc.model.weixin.bo.person.WxPerson;
import com.abc12366.uc.service.IWxGzhService;
import com.abc12366.uc.util.wx.SignUtil;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxConnectFactory;
import com.abc12366.uc.util.wx.WxGzhClient;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 微信公众号
 *
 * @author zhushuai 2017-8-1
 */
@Controller
public class WxGzhController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(WxGzhController.class);
    @Autowired
    private IWxGzhService iWxGzhService;

    /**
     * 公众号列表信息
     * @param gzhInfo 公众号参数
     * @param page  页数
     * @param size  大小
     * @return 
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxgzh/list")
    public ResponseEntity wxgzhList(GzhInfo gzhInfo,
                                    @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                    @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{},{},{}", gzhInfo, page, size);
        List<GzhInfo> dataList = iWxGzhService.wxgzhList(gzhInfo, page, size);

        PageInfo<GzhInfo> pageInfo = new PageInfo<GzhInfo>(dataList);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                "total", pageInfo.getTotal()));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 从数据库获取单个菜单信息
     * @param id 菜单id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxgzh/{id}")
    public ResponseEntity wxgzhInfo(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        GzhInfo gzhInfo = iWxGzhService.selectOne(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", gzhInfo));

        LOGGER.info("{}", responseEntity);
        return responseEntity;

    }

    /**
     * 新增微信公众号信息
     * @param gzhInfo 公众号
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PostMapping("/wxgzh/creat")
    public ResponseEntity wxgzhcreat(@Valid @RequestBody GzhInfo gzhInfo) {
        LOGGER.info("{}", gzhInfo);

        GzhInfo v = iWxGzhService.insert(gzhInfo);

        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 修改微信公众号
     * @param id  工作号ID
     * @param gzhInfo 公众号
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PutMapping("/wxgzh/{id}")
    public ResponseEntity wxmenudbEdit(@PathVariable("id") String id, @Valid @RequestBody GzhInfo gzhInfo) {
        LOGGER.info("{},{}", id, gzhInfo);

        gzhInfo.setId(id);
        GzhInfo v = iWxGzhService.update(gzhInfo);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 删除单个微信公众号信息
     * @param id 工作号ID
     * @return
     */
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/wxgzh/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        iWxGzhService.delete(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
    
    /**
     * 微信网页授权JS注册
     * @param charge 是否判断缓存
     * @param config 确认参数
     * @return
     */
    @PostMapping("/wxgzh/getwxJsConfig")
    public @ResponseBody WxJsConfig getwxJsConfig(@RequestParam(value="charge",required=false) String charge,@RequestBody WxJsConfig config){
    	LOGGER.info(JSON.toJSONString(config));
    	return SignUtil.jsign(WxGzhClient.getInstance().getAppid(), WxGzhClient.getInstanceJstiket(), config.getUrl(),charge);
    }
    
    /**
     * 通过微信授权code获取微信用户信息
     * @param code 微信code
     * @return
     */
    @SuppressWarnings("rawtypes")
	@GetMapping("/wxgzh/getuserinfo/{code}")
    public ResponseEntity getuserinfo(@PathVariable("code") String code){
    	Map<String, String> tks = new HashMap<String, String>();
        tks.put("appid", WxGzhClient.getInstance().getAppid());
        tks.put("secret", WxGzhClient.getInstance().getSecret());
        tks.put("code", code);
        tks.put("grant_type","authorization_code");
        WxUseToken respon = WxConnectFactory.get(WechatUrl.WXIMG_JSTOKEN, tks, null, WxUseToken.class);
        if(respon.getErrcode()==0){
        	Map<String, String> tks1 = new HashMap<String, String>();
            tks1.put("access_token", respon.getAccess_token());
            tks1.put("openid", respon.getOpenid());
            tks1.put("openid", "zh_CN");
            WxPerson person = WxConnectFactory.get(WechatUrl.WXJSUSEINFO, tks1, null, WxPerson.class);
            if(person.getErrcode()==0){
            	return ResponseEntity.ok(Utils.kv("data", person));
            }else{
            	return ResponseEntity.ok(Utils.bodyStatus(9999, person.getErrmsg()));
            }
        }else{
        	return ResponseEntity.ok(Utils.bodyStatus(9999, respon.getErrmsg()));
        }
        
    }
    
    /**
     * 通过微信授权code获取用户openid
     * @param code 微信code
     * @return
     */
    @SuppressWarnings("rawtypes")
	@GetMapping("/wxgzh/getuserid/{code}")
    public ResponseEntity getuserid(@PathVariable("code") String code){
    	Map<String, String> tks = new HashMap<String, String>();
        tks.put("appid", WxGzhClient.getInstance().getAppid());
        tks.put("secret", WxGzhClient.getInstance().getSecret());
        tks.put("code", code);
        tks.put("grant_type","authorization_code");
        WxUseToken respon = WxConnectFactory.get(WechatUrl.WXIMG_JSTOKEN, tks, null, WxUseToken.class);
        if(respon.getErrcode()==0){
        	return ResponseEntity.ok(Utils.kv("data", respon));
        }else{
        	return ResponseEntity.ok(Utils.bodyStatus(9999, respon.getErrmsg()));
        }
        
    }
}
