package com.abc12366.uc.jrxt.model.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;

/**
 * 加密解密工具类
 *
 * @author ziben
 * @version 1.0 11-3-29 上午11:36
 */
@Component("cryptUtil")
public class CryptUtil {

    protected Logger _log = LoggerFactory.getLogger(CryptUtil.class);

    ThreadLocal<Cipher> cipherThreadLocal = new ThreadLocal<Cipher>();
    ThreadLocal<SecretKeyFactory> secretKeyFactoryThreadLocal = new ThreadLocal<SecretKeyFactory>();

    /**
     * 此函数使用用户选择的加密算法对文件进行加密，目前为“DES”算法
     *
     * @param key         用户提供的加密算法的密钥,即申报密码,长度固定为8
     * @param inFileName  需要进行加密的文件名（全路径）
     * @param outFileName 指定的加密后输出文件（全路径）
     * @return 如果成功返回0；如果文件不能打开返回1；如果提供的密钥的长度不合法返回2；如果提供的密钥内容不合法返回3；如果选择的算法不合法返回4
     */
    public int encryptFile(String key, String inFileName, String outFileName) {
        if (key.length() == 8) {
            byte[] bytekey = key.getBytes();//getKeyByStr(key);
            File inFile = new File(inFileName);
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(inFile);

                byte[] byteIn = new byte[(int) inFile.length()];

                fis.read(byteIn);
//                fis.close();
                //加密
                byte[] bytOut = encryptByDES(byteIn, bytekey);
                fos = new FileOutputStream(outFileName);
                fos.write(bytOut);
//                fos.close();
                return 0;
            } catch (FileNotFoundException e) {
                _log.error(e.getMessage());
                return 1;
            } catch (IOException e) {
                _log.error(e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                _log.error(e.getMessage());
                e.printStackTrace();
            }finally {
                if(fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        _log.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
                if(fos!=null){
                    try {
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        _log.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } else {
            return 2;
        }

        return -1;
    }

    /**
     * 此函数对指定文件使用指定的加密算法进行解密，目前为“DES”算法
     *
     * @param key         用户提供的加密算法的密钥,即申报密码
     * @param inFileName  需要进行加密的文件名（全路径）
     * @param outFileName 指定的加密后输出文件（全路径）
     * @return 如果成功返回0；如果文件不能打开返回1；如果提供的密钥的长度不合法返回2；如果提供的密钥内容不合法返回3；如果选择的算法不合法返回4
     */
    public int decryptFile(String key, String inFileName, String outFileName) {
        if (key.length() == 8) {
            byte[] bytekey = key.getBytes();
            File inFile = new File(inFileName);
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(inFile);

                byte[] byteIn = new byte[(int) inFile.length()];

                fis.read(byteIn);
//                fis.close();
                //加密
                byte[] bytOut = decryptByDES(byteIn, bytekey);
                fos = new FileOutputStream(outFileName);
                fos.write(bytOut);
//                fos.close();
                return 0;
            } catch (FileNotFoundException e) {
                _log.error(e.getMessage());
                e.printStackTrace();
                return 1;
            } catch (IOException e) {
                _log.error(e.getMessage());
            } catch (Exception e) {
                _log.error(e.getMessage());
                e.printStackTrace();
            }finally {
                if(fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        _log.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
                if(fos!=null){
                    try {
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        _log.error(e.getMessage());
                        e.printStackTrace();
                    }
                }

            }
        } else {
            return 2;
        }

        return -1;
    }

    /**
     * 对传入的字节流进行加密
     *
     * @param byteContent
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] encryptContent(byte[] byteContent, String key) throws Exception {
        //TO-Do: 暂时不做任何加密操作，原样返回
      //  return byteContent;
        return this.encryptByDES(byteContent, key.getBytes());
    }

    /**
     * 对传入的字节流进行解密
     *
     * @param byteContent
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] decryptContent(byte[] byteContent, String key) throws Exception {
        return this.decryptByDES(byteContent, key.getBytes());
    }

    /**
     * 用DES方法加密输入的字节
     * bytKey需为8字节长，是加密的密码
     */
    private byte[] encryptByDES(byte[] bytP, byte[] bytKey) throws Exception {
        DESKeySpec desKS = new DESKeySpec(bytKey);

//        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKeyFactory skf = getSecretKeyFactory();

        SecretKey sk = skf.generateSecret(desKS);

//        Cipher cip = Cipher.getInstance("DES");
        Cipher cip = getCipher();
        cip.init(Cipher.ENCRYPT_MODE, sk);
        return cip.doFinal(bytP);
    }

    /**
     * 用DES方法解密输入的字节
     * bytKey需为8字节长，是解密的密码
     */
    private byte[] decryptByDES(byte[] bytE, byte[] bytKey) throws Exception {
        DESKeySpec desKS = new DESKeySpec(bytKey);

//        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKeyFactory skf = getSecretKeyFactory();

        SecretKey sk = skf.generateSecret(desKS);

//        Cipher cip = Cipher.getInstance("DES");
        Cipher cip = getCipher();

        cip.init(Cipher.DECRYPT_MODE, sk);
        return cip.doFinal(bytE);
    }

    /**
     * 输入密码的字符形式，返回字节数组形式。
     * 如输入字符串：AD67EA2F3BE6E5AD
     * 返回字节数组：{173,103,234,47,59,230,229,173}
     */
    private byte[] getKeyByStr(String str) {
        byte[] bRet = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            Integer itg = new Integer(16 * getChrInt(str.charAt(2 * i)) + getChrInt(str.charAt(2 * i + 1)));
            bRet[i] = itg.byteValue();
        }
        return bRet;
    }

    /**
     * 计算一个16进制字符的10进制值
     * 输入：0-F
     */
    private int getChrInt(char chr) {
        int iRet = 0;
        if (chr == "0".charAt(0)) {
            iRet = 0;
        }
        if (chr == "1".charAt(0)) {
            iRet = 1;
        }
        if (chr == "2".charAt(0)) {
            iRet = 2;
        }
        if (chr == "3".charAt(0)) {
            iRet = 3;
        }
        if (chr == "4".charAt(0)) {
            iRet = 4;
        }
        if (chr == "5".charAt(0)) {
            iRet = 5;
        }
        if (chr == "6".charAt(0)) {
            iRet = 6;
        }
        if (chr == "7".charAt(0)) {
            iRet = 7;
        }
        if (chr == "8".charAt(0)) {
            iRet = 8;
        }
        if (chr == "9".charAt(0)) {
            iRet = 9;
        }
        if (chr == "A".charAt(0)) {
            iRet = 10;
        }
        if (chr == "B".charAt(0)) {
            iRet = 11;
        }
        if (chr == "C".charAt(0)) {
            iRet = 12;
        }
        if (chr == "D".charAt(0)) {
            iRet = 13;
        }
        if (chr == "E".charAt(0)) {
            iRet = 14;
        }
        if (chr == "F".charAt(0)) {
            iRet = 15;
        }
        return iRet;
    }

    public File decryptContentToFile(String password, String crypt, byte[] contentBytes) {
        byte[] bytekey = password.getBytes();

        FileOutputStream fos =null;
        try {
            File f = File.createTempFile("wtdkdz_", ".zip");
            //解密
            byte[] bytOut = decryptByDES(contentBytes, bytekey);
            fos = new FileOutputStream(f);
            fos.write(bytOut);
//            fos.close();
            return f;
        } catch (Exception e) {
            _log.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try{
                if(fos!=null){fos.close();}
            }catch (Exception e){
                _log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    public byte[] decryptContent(String password, String crypt, byte[] contentBytes)throws Exception  {
    	//return contentBytes;
        byte[] bytekey = password.getBytes();
      //  try {
           byte[] bytOut = decryptByDES(contentBytes, bytekey);
           return bytOut;
      // } catch (Exception e) {
      //      e.printStackTrace();
     //   }
     // return null;

    }

    private Cipher getCipher() throws Exception{
        Cipher cipher = cipherThreadLocal.get();
        if (null == cipher) {
            cipher = Cipher.getInstance("DES");
            cipherThreadLocal.set(cipher);
        }
        return cipher;
    }

    private SecretKeyFactory getSecretKeyFactory() throws Exception{
        SecretKeyFactory secretKeyFactory = secretKeyFactoryThreadLocal.get();
        if (null == secretKeyFactory) {
            secretKeyFactory = SecretKeyFactory.getInstance("DES");
            secretKeyFactoryThreadLocal.set(secretKeyFactory);
        }
        return secretKeyFactory;
    }

}
