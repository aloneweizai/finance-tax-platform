package com.abc12366.uc.jrxt.model.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;

/**
 * 接入系统工具类
 * @author lijun
 * @version v1.0
 * @created 2014-3-7
 */
@Component
public class CommonUtils {

//    @Autowired
//    private ITcLogin tcLoginDao;

    protected static Logger _log = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * Generate password by yhxx's password & salt.
     * @param password
     * @param salt
     * @return md5 password
     */
    public static String generatePwd(String password, String salt) {
        String md5 = new MD5(password).compute();
        if(salt != null && !"".equals(salt)){
            md5 += salt;
        }
        return new MD5(md5).compute();
    }

    /**
     * 用户登录记录
     * @param yhbh
     * @param tiripPackage
     */
//    public void loginLog(String yhbh, TiripPackage tiripPackage) {
//        TcLogin tcLogin = new TcLogin();
//        tcLogin.setYHBH(yhbh);
//        tcLogin.setLOGIP(tiripPackage.getRouterSession().getParamList(1).getValue());
//        tcLogin.setLASTLOGTIME(new Date());
//        tcLoginDao.insert(tcLogin);
//    }

    public static String getRequestIpAddrs(HttpServletRequest request) {
        String ipAddress = null;
        //ipAddress = this.getRequest().getRemoteAddr();
        ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {

                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    _log.error(e.getMessage());
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }

        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 检查字符串是否为空
     * @param str
     * @return true：为空，false：不为空
     */
    public static boolean checkEmpty(String str){
        if(str == null || "".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    /**/
    public static boolean checkCollectionEmpty(Collection collection){
        if(collection == null || collection.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断中文字符
     * GENERAL_PUNCTUATION 判断中文的“号
     * CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
     * HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 计算字符串长度
     * @param strName
     * @return
     */
    public static int isChinese(String strName) {
        int len = 0;
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c) == true) {
                len = len + 2;
            } else {
                len = len + 1;
            }
        }
        return len;
    }

    public static byte[] getImgFromWeb(String picUrl) {
        byte[] data =null;

        ByteArrayOutputStream output = null;

        InputStream is = null;
        try {
            URL url = new URL(picUrl);
            is = url.openStream();

            output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = is.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
//            output.close();
//            is.close();
        } catch (Exception e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try{
                if(output!=null){
                    output.flush();
                    output.close();
                }
                if(is!=null){
                    is.close();
                }
            }catch (Exception e1){
                _log.error(e1.getMessage());
                e1.printStackTrace();
            }
        }
        return data;
    }

    public static byte[] getImgFromString(byte[] bytes){
        byte[] data =null;

        ByteArrayOutputStream output = null;
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(bytes);
            output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = is.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
//            output.close();
//            is.close();
        } catch (Exception e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try{
                if(output!=null){
                    output.flush();
                    output.close();
                }
                if(is!=null){
                    is.close();
                }
            }catch (Exception e1){
                _log.error(e1.getMessage());
                e1.printStackTrace();
            }
        }
        return data;
    }

    public static String nullToString(Object obj) {
            if(obj == null) {
                return "";
            } else {
                return obj.toString();
            }
    }

    public static boolean nullOrBlank(String param) {
        return (param == null || param.trim().equals("")) ? true : false;
    }

    public static String mapToString(Map<String, String> map){
        if(map.size() > 0) {
            Iterator iterator = map.entrySet().iterator();
            String str = "";
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                str += entry.getKey() + ":" + entry.getValue() + ",";
            }
            str = str.substring(0, str.length() - 1);
            return str;
        }else {
            return null;
        }
    }

    public static String generateTokenId(String yhmc){
        Long currentTime = new Date().getTime();
        if(yhmc != null) {
            return new MD5(String.valueOf(currentTime) + yhmc).compute();
        }else{
            return new MD5(String.valueOf(currentTime)).compute();
        }
    }

    public static String privateCode(String str, int from, int to){
        if(str != null) {
            String preStr = str.substring(0, from);
            String sufStr = str.substring(to, str.length());
            if (from <= str.length() && to <= str.length()) {
                for (int i = 0; i < to - from; i++) {
                    preStr += "*";
                }
                str = preStr + sufStr;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }else{
            throw new NullPointerException();
        }
        return str;
    }

    public static String encrypt(String content){
        CryptUtil crypt = new CryptUtil();
        CodeUtil code = new CodeUtil();

        byte[] bytes = new byte[0];
        try {
            bytes = crypt.encryptContent(content.getBytes(), BaseObject.getConfig("DB_CRYPT"));
        } catch (Exception e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        }
        return code.encodeContent(bytes);
    }

    public static String decrypt(String content){
        CodeUtil code = new CodeUtil();
        CryptUtil cu = new CryptUtil();

        byte[] bytes = code.decodeContent("BASE64", content);
        try{
            bytes = cu.decryptContent(bytes, BaseObject.getConfig("DB_CRYPT"));
        }catch (Exception e){
            _log.error(e.getMessage());
            e.printStackTrace();
        }
        return new String(bytes);
    }

    /**
     * Get timeout. If not set, default 30 min.
     * @return unix time
     */
    public static long getTimeout(){
        String timeout = BaseObject.getConfig("timeout");
        long defaultTimeout = 30 * 60 * 1000;
        if (null != timeout) {
            try {
                return Long.parseLong(timeout) * 60 * 1000;
            }catch (Exception e){
                _log.error(e.getMessage());
                return defaultTimeout;
            }
        } else {
            return defaultTimeout;
        }
    }
    
    public static String getUUID() {
    	UUID uuid = UUID.randomUUID();
		String a = uuid.toString();
		a = a.toUpperCase();
		return a.replaceAll("-", "");
    }
    
    public static double getDouble(String property) {
		return Double.parseDouble(property);
	}

	public static double getDouble(String property, double defaultValue) {
		return (property == null || property.equals("")) ? defaultValue
				: getDouble(property);
	}
    
    public static int totalPageCount(String totalCount, String pageSize) {
		int res = 0;
		try {
			res = (int) Math.ceil(CommonUtils.getDouble(totalCount)
					/ CommonUtils.getDouble(pageSize));
		} catch (Exception e) {
            _log.error(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

    public static String getRandom(){
        int[] array = {0,1,2,3,4,5,6,7,8,9};
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for(int i = 0; i < 8; i++){
            result = result * 10 + array[i];
        }
        if(result<10000000)
            result=result+10000000;
        return String.valueOf(result);
    }
}
