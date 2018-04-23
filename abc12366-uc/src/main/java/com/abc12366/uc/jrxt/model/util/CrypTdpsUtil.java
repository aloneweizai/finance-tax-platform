package com.abc12366.uc.jrxt.model.util;


import com.google.code.springcryptoutils.core.cipher.symmetric.Cipherer;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Security;

@Component
public class CrypTdpsUtil implements InitializingBean {
    @Autowired
    private Cipherer symmetricEncrypter;

    @Autowired
    private Cipherer symmetricDecrypter;

    @Autowired
    private com.google.code.springcryptoutils.core.cipher.asymmetric.Base64EncodedCipherer b64AsymmetricEncrypter;

    @Autowired
    private com.google.code.springcryptoutils.core.cipher.asymmetric.Base64EncodedCipherer b64AsymmetricDecrypter;

    private static byte[] iv = {0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};

    public void afterPropertiesSet() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public int encryptFile(String key, String inFileName, String outFileName) {
        return -1;
    }

    public int decryptFile(String key, String inFileName, String outFileName) {
        return -1;
    }

    public String b64AsymmetricEncrypt(String message) {
        return this.b64AsymmetricEncrypter.encrypt(message);
    }

    public String b64AsymmetricDecrypt(String message) {
        return this.b64AsymmetricDecrypter.encrypt(message);
    }

    public byte[] symmetricEncrypt(byte[] key, byte[] message) {
        return this.symmetricEncrypter.encrypt(key, iv, message);
    }

    public byte[] symmetricDecrypt(byte[] key, byte[] message) {
        return this.symmetricDecrypter.encrypt(key, iv, message);
    }

    public byte[] encryptContent(byte[] byteContent, byte[] key) throws Exception {
        return this.symmetricEncrypt(key, byteContent);
    }

    public byte[] decryptContent(String password, String crypt, byte[] contentBytes) {
        byte[] key = password.getBytes();
        return this.symmetricDecrypt(key, contentBytes);
    }
}
