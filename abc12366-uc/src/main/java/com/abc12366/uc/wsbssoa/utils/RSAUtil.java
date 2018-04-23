package com.abc12366.uc.wsbssoa.utils;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/**
 * Created by Administrator on 2015-11-25.
 */
public class RSAUtil {
    protected static Logger _log = LoggerFactory.getLogger(RSAUtil.class);

    private static BouncyCastleProvider bouncyCastleProvider;

    private static String RSAKeyStore = "RSAKey.txt";

    /**
     * 生成密钥对
     *
     * @return KeyPair
     * @throws Exception
     */
    public static KeyPair generateKeyPair() throws Exception {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",
                    getInstance());
            //大小
            final int KEY_SIZE = 1024;
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            return keyPairGen.generateKeyPair();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static KeyPair generateKeyPair(String basePath) throws Exception {
        try {
            KeyPair keyPair = generateKeyPair();
            saveKeyPair(keyPair, basePath);
            return keyPair;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取密钥对
     *
     * @return 密钥对
     * @throws Exception
     */
    public static KeyPair getKeyPair(String basePath) throws Exception {
        File file = new File(basePath + RSAKeyStore);
        if (!file.exists()) {
            return null;
        }
        FileInputStream fis = new FileInputStream(StringUtils.isNotBlank(basePath) ? (basePath + RSAKeyStore) :
                RSAKeyStore);
        ObjectInputStream oos = new ObjectInputStream(fis);
        KeyPair kp = (KeyPair) oos.readObject();
        oos.close();
        fis.close();
        return kp;
    }

    /**
     * 保存密钥
     *
     * @param kp 密钥对
     * @throws Exception
     */
    public static void saveKeyPair(KeyPair kp, String basePath) throws Exception {
        File file = new File(basePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new Exception();
            } else {
                file = new File(basePath + RSAKeyStore);
                if (!file.exists()) {
                    if (!file.createNewFile()) {
                        throw new Exception();
                    }
                }
            }
        }
        FileOutputStream fos = new FileOutputStream(StringUtils.isNotBlank(basePath) ? (basePath + RSAKeyStore) :
                RSAKeyStore);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // 生成密钥
        oos.writeObject(kp);
        oos.close();
        fos.close();
    }

    /**
     * 生成公钥
     *
     * @param modulus        模数
     * @param publicExponent 指数
     * @return RSAPublicKey
     * @throws Exception
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
        KeyFactory keyFac;
        try {
            keyFac = KeyFactory.getInstance("RSA", getInstance());
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage());
        }
        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * 生成私钥
     *
     * @param modulus         模数
     * @param privateExponent 指数
     * @return RSAPrivateKey
     * @throws Exception
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception {
        KeyFactory keyFac;
        try {
            keyFac = KeyFactory.getInstance("RSA", getInstance());
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage());
        }
        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * 加密
     *
     * @param pk   加密的密钥
     * @param data 待加密的明文数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", getInstance());
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            // 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
            int blockSize = cipher.getBlockSize();
            // 加密块大小为127
            // byte,加密后为128个byte;因此共有2个加密块，第一个127
            // byte第二个为1个byte
            int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize) {
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                } else {
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                }
                i++;
            }
            return raw;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 解密
     *
     * @param pk  解密的密钥
     * @param raw 已经加密的数据
     * @return 解密后的明文
     * @throws Exception
     */
    public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", getInstance());
            cipher.init(Cipher.DECRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            if (raw.length <= blockSize) {
                int j = 0;
                while (raw.length - j * blockSize > 0) {
                    bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                    j++;
                }
            } else {
                bout.write(cipher.doFinal(raw, 0, raw.length));
            }
            return bout.toByteArray();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 解密方法
     *
     * @param paramStr 密文
     * @param basePath RSAKey.txt所在的文件夹路径
     */
    public static String decryptStr(String paramStr, String basePath) throws Exception {
        byte[] en_result = new BigInteger(paramStr, 16).toByteArray();
        byte[] de_result = decrypt(getKeyPair(basePath).getPrivate(), en_result);
        StringBuilder sb = new StringBuilder();
        sb.append(new String(de_result));
        //返回解密的字符串
        return sb.reverse().toString();
    }

    /**
     * 该方法专门解密前端加密数据
     *
     * @param pk       私钥
     * @param paramStr 密文
     * @param encode   前端字符编码
     * @return 明文
     * @throws Exception
     */
    public static String decryptFromJS(PrivateKey pk, String paramStr, String encode) throws Exception {
        byte[] en_result = new BigInteger(paramStr, 16).toByteArray();
        //System.out.println("转成byte[]" + new String(en_result));

        byte[] de_result = RSAUtil.decrypt(pk, en_result);
        //解密后的字节码需要反转顺序后才能获得正确明文，原因未知
        byte[] revers = new byte[de_result.length];
        for (int i = 0; i < de_result.length; i++) {
            revers[de_result.length - 1 - i] = de_result[i];
        }
        return URLDecoder.decode(new String(revers), encode);
    }


    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();
        String val = "1qazxsw2";
        byte[] bytes = encrypt(keyPair.getPublic(), val.getBytes());
        System.out.println(new String(decrypt(keyPair.getPrivate(), bytes), "utf-8"));

        RSAPublicKey publicKey = RSA.getDefaultPublicKey();
        RSAPrivateKey privateKey = RSA.getDefaultPrivateKey();
        byte[] bytes2 = encrypt(publicKey, val.getBytes());
        String str = new String(bytes2, "utf-8");
        System.out.println(new String(decrypt(privateKey, str.getBytes()), "utf-8"));
    }

    public static synchronized BouncyCastleProvider getInstance() {
        if (bouncyCastleProvider == null) {
            bouncyCastleProvider = new BouncyCastleProvider();
        }
        return bouncyCastleProvider;
    }
}
