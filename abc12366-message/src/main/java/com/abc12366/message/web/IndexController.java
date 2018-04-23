package com.abc12366.message.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-03-27 3:58 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class IndexController {

    @GetMapping("/")
    public ResponseEntity index() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok(Utils.kv("message", "OK"));
    }
}
