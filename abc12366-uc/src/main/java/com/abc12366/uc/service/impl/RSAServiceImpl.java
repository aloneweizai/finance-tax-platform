package com.abc12366.uc.service.impl;

import com.abc12366.uc.service.RSAService;
import com.abc12366.uc.wsbssoa.utils.RSA;
import com.google.code.springcryptoutils.core.cipher.asymmetric.Base64EncodedCipherer;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.KeyStore;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-11
 * Time: 16:55
 */
@Service
public class RSAServiceImpl implements RSAService {
	
	@Autowired
    @Qualifier("b64AsymmetricEncrypter")
    private Base64EncodedCipherer encrypter;

    @Autowired
    @Qualifier("b64AsymmetricDecrypter")
    private Base64EncodedCipherer decrypter;
    
    private static KeyStore keyStore;
    
    static{
		try {
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(new ClassPathResource("tdps.jks").getInputStream(), "hnabc4000".toCharArray());	    
		} catch (Exception e) {
			e.printStackTrace();
		}
    }    
	
	public String decodenNew(String str){
		return decrypter.encrypt(str);
	}
	
	public String decodeStringFromJsNew(String str){
		try {
			 byte[] en_data = Hex.decodeHex(new String(Base64.getDecoder().decode(str)).toCharArray());
			 RSAPrivateKey privateKey = (RSAPrivateKey) keyStore.getKey("tdps", "hnabc4000".toCharArray());
			 Cipher ci = Cipher.getInstance("RSA", new BouncyCastleProvider());
		     ci.init(Cipher.DECRYPT_MODE, privateKey);
		     return StringUtils.reverse(new String(ci.doFinal(en_data)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

    @Override
    public String decode(String str) {
        try {
            RSAPrivateKey privateKey = RSA.getDefaultPrivateKey();
            byte[] bytes = new BASE64Decoder().decodeBuffer(str);
            String base64 = new String(bytes);
            String[] test = base64.split(" ");
            String json = "";
            for (String s : test) {
                json += RSA.decryptString(privateKey, s);
            }
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String decodeStringFromJs(String str) {
        try {
            byte[] bytes = new BASE64Decoder().decodeBuffer(str);
            String base64 = new String(bytes);
            String[] test = base64.split(" ");
            String json = "";
            for (String s : test) {
                json += RSA.decryptStringByJs(s);
            }
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
