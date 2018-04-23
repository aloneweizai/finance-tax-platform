package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.uc.model.bo.RSAResponse;
import com.abc12366.uc.wsbssoa.utils.RSA;
import org.apache.commons.codec.binary.Hex;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-05
 * Time: 19:44
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class RSAController {
	
    /**
     * 获取组装PublicKey对象（公钥）所需参数接口
     * @return
     */
    @GetMapping(path = "/rsa/public")
    public ResponseEntity selectPublic() {
        RSAResponse rsaResponse = new RSAResponse();
        try {
            RSAPublicKey publicKey = RSA.getDefaultPublicKey();
            
            if (publicKey != null) {
                rsaResponse.setCode("2000");
                rsaResponse.setMessage("生成公钥成功");
                rsaResponse.setFormat(publicKey.getFormat());
                rsaResponse.setAlgorithm(publicKey.getAlgorithm());
                rsaResponse.setModulus(new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
                rsaResponse.setExponent(new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
            } else {
                rsaResponse.setCode("1000");
                rsaResponse.setMessage("生成公钥失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(rsaResponse);
    }


    /**
     * 获取组装PrivateKey对象（私钥）所需参数接口
     * @return
     */
    @GetMapping(path = "/rsa/private")
    public ResponseEntity selectPrivate() {
        RSAResponse rsaResponse = new RSAResponse();
        try {
            RSAPrivateKey privateKey = RSA.getDefaultPrivateKey();
        	if (privateKey != null) {
                rsaResponse.setCode("2000");
                rsaResponse.setMessage("生成私钥成功");
                rsaResponse.setFormat(privateKey.getFormat());
                rsaResponse.setAlgorithm(privateKey.getAlgorithm());
                rsaResponse.setModulus(new String(Hex.encodeHex(privateKey.getModulus().toByteArray())));
                rsaResponse.setExponent(new String(Hex.encodeHex(privateKey.getPrivateExponent().toByteArray())));
            } else {
                rsaResponse.setCode("1000");
                rsaResponse.setMessage("生成私钥失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(rsaResponse);
    }

    @GetMapping(path = "/rsa/{str}")
    public ResponseEntity decode(@PathVariable String str) throws Exception {
        RSAPublicKey publicKey = RSA.getDefaultPublicKey();
        RSAPrivateKey privateKey = RSA.getDefaultPrivateKey();

        byte[] bytes = RSA.encrypt(publicKey, str.getBytes());
        String s = new BASE64Encoder().encode(bytes);
        byte[] bytes2 = new BASE64Decoder().decodeBuffer(s);

        String response2 = new String(RSA.decrypt(privateKey, bytes2));
        System.out.println(response2);
        return ResponseEntity.ok(response2);
    }
    
    /**
     * 获取组装PublicKey对象（公钥）所需参数接口(新接口)
     * @return
     */
    @GetMapping(path = "/v2/rsa/public")
    public ResponseEntity<RSAResponse> selectPublicNew() {
        RSAResponse rsaResponse = new RSAResponse();
        try {
        	KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new ClassPathResource("tdps.jks").getInputStream(), "hnabc4000".toCharArray());
            
            Certificate certificate = keyStore.getCertificate("tdps");
            RSAPublicKey publicKey = (RSAPublicKey) certificate.getPublicKey();
            
            if (publicKey != null) {
                rsaResponse.setCode("2000");
                rsaResponse.setMessage("生成公钥成功");
                rsaResponse.setFormat(publicKey.getFormat());
                rsaResponse.setAlgorithm(publicKey.getAlgorithm());
                rsaResponse.setModulus(new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));
                rsaResponse.setExponent(new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray())));
            } else {
                rsaResponse.setCode("1000");
                rsaResponse.setMessage("生成公钥失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(rsaResponse);
    }


    /**
     * 获取组装PrivateKey对象（私钥）所需参数接口(新接口)
     * @return
     */
    @GetMapping(path = "/v2/rsa/private")
    public ResponseEntity<RSAResponse> selectPrivateNew() {
        RSAResponse rsaResponse = new RSAResponse();
        try {
        	KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new ClassPathResource("tdps.jks").getInputStream(), "hnabc4000".toCharArray());
        	
            RSAPrivateKey privateKey = (RSAPrivateKey) keyStore.getKey("tdps", "hnabc4000".toCharArray());
        	if (privateKey != null) {
                rsaResponse.setCode("2000");
                rsaResponse.setMessage("生成私钥成功");
                rsaResponse.setFormat(privateKey.getFormat());
                rsaResponse.setAlgorithm(privateKey.getAlgorithm());
                rsaResponse.setModulus(new String(Hex.encodeHex(privateKey.getModulus().toByteArray())));
                rsaResponse.setExponent(new String(Hex.encodeHex(privateKey.getPrivateExponent().toByteArray())));
            } else {
                rsaResponse.setCode("1000");
                rsaResponse.setMessage("生成私钥失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(rsaResponse);
    }

}
