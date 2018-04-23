package com.abc12366.uc.web.wx;

import com.abc12366.uc.service.IWxMsgService;
import com.abc12366.uc.util.wx.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信主接入口
 *
 * @author zhushuai 2017-7-31
 */
@Controller
@RequestMapping(path = "/wechatserver")
public class WxIndexController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WxIndexController.class);
    @Autowired
    private IWxMsgService iWxMsgService;
    /**
     * 微信回调主入口 
     * @param model 自带model
     * @param request 请求上文
     * @param response 返回下文
     * @return
     */
    @RequestMapping(value="/init",produces = "application/json; charset=utf-8")
    public @ResponseBody String wechatVlidate(Model model, HttpServletRequest request, HttpServletResponse response) {
    	LOGGER.info("微信服务器回调!-----------");
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        if (isGet) {//微信服务器地址设置校验回调
        	LOGGER.info("微信服务器回调校验!");
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            if (signature != null && SignUtil.checkSignature(signature, timestamp, nonce)) {
                return echostr;
            } else {
                return null;
            }
        } else {//微信事件主动回调
            return iWxMsgService.exec(request);
        }
    }
}
