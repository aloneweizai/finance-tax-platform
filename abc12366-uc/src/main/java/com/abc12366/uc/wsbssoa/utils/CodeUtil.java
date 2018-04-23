package com.abc12366.uc.wsbssoa.utils;


import org.castor.core.util.Base64Decoder;
import org.castor.core.util.Base64Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * 实现文件编码解码的工具类
 *
 * @author ziben
 * @version 1.0 11-3-29 上午11:33
 */
@Component("codeUtil2")
public class CodeUtil {
    protected Logger _log = LoggerFactory.getLogger(CodeUtil.class);

    /**
     * 对比特流进行Base64编码，返回编码后的字符串
     *
     * @param bytes 输入比特流
     * @return String
     */
    public static String encodeContent(byte[] bytes) {
        return CodeUtil.encodeContent("base64", bytes);
    }

    /**
     * 对比特流进行编码，返回编码后的字符串
     *
     * @param code  编码方式，目前只支持“BASE64”
     * @param bytes 输入比特流
     * @return String
     */
    public static String encodeContent(String code, byte[] bytes) {
        if (code.equalsIgnoreCase("BASE64")) {
            char[] chars = Base64Encoder.encode(bytes);
            return new String(chars);
        }
        return new String(bytes);
    }

    /**
     * 对字符串进行解码，返回解码后的比特流
     *
     * @param code    编码方式，目前只支持“BASE64”
     * @param content 输入字符串
     * @return byte[]
     */
    public static byte[] decodeContent(String code, String content) {
        byte[] bytes = new byte[0];
        if (code.equalsIgnoreCase("BASE64")) {
            bytes = Base64Decoder.decode(content);
        }
        return bytes;
    }

    /**
     * 对字符串进行"BASE64"解码，将解码后的比特流转换成字符串输出
     *
     * @param content 输入字符串
     * @return string 输出字符串
     */
    public static String decodeContent(String content) {
        return new String(CodeUtil.decodeContent("BASE64", content));
    }

    public char[] Base64Encode(String inFileName) {
        FileInputStream fis = null;
        try {
            File inFile = new File(inFileName);
            fis = new FileInputStream(inFileName);
            byte[] fb = new byte[(int) inFile.length()];
            fis.read(fb);
            fis.close();
            char[] chars = Base64Encoder.encode(fb);
            return chars;
        } catch (FileNotFoundException e) {
            _log.error(e.getMessage());
        } catch (IOException e) {
            _log.error(e.getMessage());
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                _log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return new char[0];
    }

    public void decodeContentToFile(String code, String content, File contentFile) {
        FileOutputStream fos = null;
        try {
            byte[] bytes = new byte[0];
            if (code.equals("BASE64")) {
                bytes = Base64Decoder.decode(content);
            }
            fos = new FileOutputStream(contentFile);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                _log.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
