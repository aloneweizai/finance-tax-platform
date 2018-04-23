package com.abc12366.uc.web.wx;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.weixin.ShortUrl;
import com.abc12366.uc.service.Long2ShortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 长链接转短链接接口
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-05 1:52 PM
 * @since 1.0.0
 */
@RestController
public class Long2ShortController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxUserController.class);

    @Autowired
    private Long2ShortService long2ShortService;

    /**
     * 微信长链接转短链接接口
     *
     * @param url 长url地址
     * @return 短url地址
     */
    @GetMapping("/long2short")
    public ResponseEntity long2short(@RequestParam("url") String url) {
        LOGGER.info("{}", url);
        ShortUrl data = long2ShortService.long2short(url);
        if (data.getErrcode().equals(0) && "ok".equalsIgnoreCase(data.getErrmsg())) {
            return ResponseEntity.ok(Utils.kv("data", data.getShort_url()));
        } else {
            throw new ServiceException(data.getErrcode(), data.getErrmsg());
        }
    }

    /**
     * tiny长链接转短链接接口
     *
     * @param url 长url地址
     * @return 短url地址
     */
    @GetMapping("/tinyurl")
    public ResponseEntity tinyUrl(@RequestParam("url") String url) {
        LOGGER.info("{}", url);
        return ResponseEntity.ok(Utils.kv("data", long2ShortService.tinyUrl(url)));
    }
}
