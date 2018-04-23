package com.abc12366.uc.util.wx;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.uc.model.weixin.bo.WxUseToken;
import com.abc12366.uc.model.weixin.bo.gzh.GzhInfo;
import com.abc12366.uc.service.IWxGzhService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * 微信客户端
 * @author zhushuai 2017-11-6
 *
 */
@Component
public class WxGzhClient {
    private static IWxGzhService iWxGzhService;
    // token获取失败，重复获取次数
    private static GzhInfo gzhInfo = null;
    /**
     * 初始化参数
     * @return
     */
    public static GzhInfo getInstance(){    
        if (gzhInfo == null){
           synchronized(WxGzhClient.class){
                if (gzhInfo == null)
                	 iWxGzhService=(IWxGzhService) SpringCtxHolder.getApplicationContext().getBean("iWxGzhService");
                	 gzhInfo = iWxGzhService.wxgzhList(new GzhInfo(), 0, 1).get(0);
           }
        }
        return gzhInfo;
	}
    /**
     * 获取当前token
     * @return
     */
    public static String getInstanceToken(){
    	String id=getInstance().getId();
    	GzhInfo temp=iWxGzhService.selectOne(id);
    	if(temp.getUserTokenUpdate()==null || isBefore(temp.getUserTokenUpdate())){
    		 Map<String, String> tks = new HashMap<String, String>();
             tks.put("grant_type", "client_credential");
             tks.put("appid", gzhInfo.getAppid());
             tks.put("secret", gzhInfo.getSecret());
             WxUseToken token = WxConnectFactory.get(WechatUrl.WXUSETOKEN, tks, null,
                     WxUseToken.class);
             if (0 == token.getErrcode()) {
            	 gzhInfo.setUserToken(token.getAccess_token());
            	 gzhInfo.setUserTokenUpdate(new Date());
            	 iWxGzhService.updateUserToken(gzhInfo);
            	 return token.getAccess_token();
             }
    	}
    	return temp.getUserToken();
    }
    
    /**
     * 获取当前JS token
     * @return
     */
    public static String getInstanceJstiket(){
    	String id=getInstance().getId();
    	GzhInfo temp=iWxGzhService.selectOne(id);
    	System.out.println(JSON.toJSONString(temp));
    	if(temp.getJsapiTicketUpdate()==null || isBefore(temp.getJsapiTicketUpdate())){
    		 Map<String, String> tks = new HashMap<String, String>();
             tks.put("access_token", getInstanceToken());
             tks.put("type", "jsapi");
             WxUseToken token = WxConnectFactory.get(WechatUrl.WXJSTIECK_GET, tks, null,
                     WxUseToken.class);
             if (0 == token.getErrcode()) {
            	 gzhInfo.setJsapi_ticket(token.getTicket());
            	 gzhInfo.setJsapiTicketUpdate(new Date());
            	 iWxGzhService.updatejsapiTicket(gzhInfo);
            	 return token.getTicket();
             }
    	}
    	return temp.getJsapi_ticket();
    }
    
    /**
     * 是否在某个时间之前
     * @param date
     * @return
     */
    private static boolean isBefore(Date date){
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, -1);
        return date.before(calendar.getTime());
    }
}
