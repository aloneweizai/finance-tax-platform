package com.abc12366.uc.jrxt.model.util;

import org.castor.core.util.Base64Decoder;
import org.castor.core.util.Base64Encoder;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class CodeUtilTdps {
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
        } catch (IOException e) {
        }
        return new char[0];
    }

    public static String encodeContent(byte[] bytes) {
        return CodeUtil.encodeContent("base64", bytes);
    }

    public static String encodeContent(String code, byte[] bytes) {
        if (code.equalsIgnoreCase("BASE64")) {
            char[] chars = Base64Encoder.encode(bytes);
            return new String(chars);
        }
        return new String(bytes);
    }

    public static byte[] decodeContent(String code, String content) {
        byte[] bytes = new byte[0];
        if (code.equalsIgnoreCase("BASE64")) {
            bytes = Base64Decoder.decode(content);
        }
        return bytes;
    }

    public void decodeContentToFile(String code, String content, File contentFile) {
        try {
            byte[] bytes = new byte[0];
            if (code.equals("BASE64")) {
                bytes = Base64Decoder.decode(content);
            }
            FileOutputStream fos = new FileOutputStream(contentFile);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static String decodeContent(String content) {
        return new String(CodeUtil.decodeContent("BASE64", content));
    }
}
