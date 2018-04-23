package com.abc12366.uc.util.wx;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.weixin.bo.gzh.WxJsConfig;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 微信服务器访问加密
 *
 * @author zhushuai 2017-7-26
 */
public class SignUtil {
    private static String charge = "false";

    /**
     * 签名校验
     * @param signature  签名
     * @param timestamp 时间戳
     * @param nonce 随机码
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp,
                                         String nonce) {
        return checkSignature(WxGzhClient.getInstance().getTokenStr(),signature, timestamp,nonce);
    }
    
    /**
     * 签名校验
     * @param signature  签名
     * @param timestamp 时间戳
     * @param nonce 随机码
     * @return
     */
	public static boolean checkSignature(String sign,String signature, String timestamp,
	            String nonce) {
			String[] arr = new String[]{sign, timestamp, nonce};
			Arrays.sort(arr);
			StringBuilder content = new StringBuilder();
			for (String s : arr) {
			content.append(s);
			}
			MessageDigest md = null;
			String tmpStr = null;
			try {
			md = MessageDigest.getInstance("SHA-1");
			
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
			} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			}
			return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}


    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }


    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
    
    
    public static WxJsConfig jsign(String appid,String jsapi_ticket, String url, String charges){
    	String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";
        charge=(charges==null?charge:charges);

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return new WxJsConfig(appid,timestamp,nonce_str,signature,url,charge);
    }
    
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），
     * 使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串
     */
    public static String objectSort(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            names.add(fields[i].getName());
        }
        if (names.size() > 0) {
            Collections.sort(names); // 排序
            String kvStr = "";
            for(String name : names) { // 拼接
                Object value = getFieldValueByName(name, obj);
                if (!StringUtils.isEmpty(value)) {
                    kvStr += name + "=" + value + "&";
                }
            }
            return kvStr.substring(0, kvStr.length() -1);
        }
        return null;
    }

    /**
     * 获取属性值
     */
    private static Object getFieldValueByName(String fieldName, Object obj) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = obj.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(obj, new Object[] {});
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 微信商户Key签名
     */
    public static String signKey(Object obj) {
        String str = objectSort(obj);
        try {
            return Utils.md5(str + "&key=" + SpringCtxHolder.getProperty("abc.mch_key")).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取UUID
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    //获取时间
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    /**
	 * 获取指定位数随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(52);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

}
