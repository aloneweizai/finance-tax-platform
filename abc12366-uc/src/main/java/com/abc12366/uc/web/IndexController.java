package com.abc12366.uc.web;

import com.abc12366.gateway.util.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-03-27 3:58 PM
 * @since 1.0.0
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public ResponseEntity index() throws Exception {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok(Utils.kv("message", "OK"));
    }
}
