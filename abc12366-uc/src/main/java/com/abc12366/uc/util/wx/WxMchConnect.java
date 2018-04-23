package com.abc12366.uc.util.wx;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.uc.model.weixin.bo.message.FileContent;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.Map;

/**
 * wechat微信商户
 *
 * @param <T>
 * @author 2017-7-27
 */
public class WxMchConnect<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxMchConnect.class);

    protected Class<T> _class;
    private String requestUrl;
    private WechatUrl wechatUrl;
    private String requestMethod;
    private String outputStr = null;
    private String jsonStr;
    private Map<String, String> headParameters;
    private Object bodyParameters;
    private FileContent file;

    public WxMchConnect(WechatUrl url, String requestMethod, Map<String, String> headParameters,
                        Object bodyParameters, Class<T> _class) {
        this.wechatUrl = url;
        this.requestMethod = requestMethod;
        this.headParameters = headParameters;
        this.bodyParameters = bodyParameters;
        this._class = _class;
        this.requestUrl = SpringCtxHolder.getProperty("abc.mch_url") + wechatUrl.uri;
    }

    public void initXml() {
        if (bodyParameters != null) {
            LOGGER.info("body: {}", bodyParameters);
            if (bodyParameters instanceof String) {
                this.outputStr = (String) bodyParameters;
            } else {
                this.outputStr = MessageUtil.objToXml(bodyParameters);
            }
        }
    }

    public void httpsRequest() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        InputStream instream = new ClassPathResource("cer/apiclient_cert.p12").getInputStream();
        String mch_id = SpringCtxHolder.getProperty("abc.mch_id");
        try {
            keyStore.load(instream, mch_id.toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, mch_id.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            HttpPost httpPost = new HttpPost(this.requestUrl);
            httpPost.setEntity(new ByteArrayEntity(outputStr.getBytes()));

            LOGGER.info("Request Body: {}", outputStr);
            LOGGER.info("Executing Request" + httpPost.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    StringBuffer buffer = new StringBuffer();
                    while ((text = bufferedReader.readLine()) != null) {
                        buffer.append(text);
                    }
                    LOGGER.info(wechatUrl.describe + "->微信服务器返回信息:{}", buffer.toString());
                    this.setJsonStr(buffer.toString());
                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    T parseXmlObject() {
        try {
            T res = JSON.parseObject(MessageUtil.xml2JSON(this.jsonStr), _class);
            return res;
        } catch (Exception e) {
            LOGGER.error("微信服务器返回xml格式异常", e);
            e.printStackTrace();
            throw new ServiceException("-999", "SOA返回数据格式异常，请联系管理员");
        }

    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
