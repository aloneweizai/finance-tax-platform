package com.abc12366.uc.util.wx;

import com.abc12366.uc.model.weixin.bo.message.FileContent;

import java.io.InputStream;
import java.util.Map;

/**
 * 微信调用工程封装
 * @author zhushuai 2017-7-28
 */
public class WxConnectFactory {
    private static final String HTTP_GET = "GET";
    private static final String HTTP_POST = "POST";


    /**
     * GET调用
     * @param url 地址
     * @param headparamters 头参数
     * @param bodyparamters 体参数
     * @param _class
     * @return
     */
    public static <T extends Object> T get(WechatUrl url, Map<String, String> headparamters, Object bodyparamters,
                                           Class<T> _class) {
        WxConnect<T> connect = new WxConnect<>(url, HTTP_GET, headparamters, bodyparamters, _class);
        return doConect(connect);
    }

    /**
     * POSt调用
     * @param url 地址
     * @param headparamters 头参数
     * @param bodyparamters 体参数
     * @param _class
     * @return
     */
    public static <T extends Object> T post(WechatUrl url, Map<String, String> headparamters, Object bodyparamters,
											Class<T> _class) {
        WxConnect<T> connect = new WxConnect<>(url, HTTP_POST, headparamters, bodyparamters, _class);
        return doConect(connect);
    }

    /**
     * POSt调用文件
     * @param url 地址
     * @param headparamters 头参数
     * @param bodyparamters 体参数
     * @param _class
     * @return
     */
    public static <T extends Object> T postFile(WechatUrl url, Map<String, String> headparamters, Object
			bodyparamters, Class<T> _class, FileContent file) {
        WxConnect<T> connect = new WxConnect<>(url, headparamters, bodyparamters, _class, file);
        connect.initJson();
        connect.httpPostFile();
        return connect.parseObject();
    }
    
    /**
     * 获取文件 
     * @param url 地址
     * @param headparamters 参数
     * @return
     */
    public static InputStream getWXFile(WechatUrl url, Map<String, String> headparamters){
    	WxConnect<Object> connect = new WxConnect<Object>(url, HTTP_GET, headparamters, null, null);
    	connect.initJson();
    	return connect.getWxFile();
    }

    /**
     * 通讯
     * @param connect
     * @return
     */
    private static <T extends Object> T doConect(WxConnect<T> connect) {
        connect.initJson();
        connect.httpsRequest();
        return connect.parseObject();
    }
}
