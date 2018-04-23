package com.abc12366.uc.web.wx;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.weixin.bo.qrcode.Qrcode;
import com.abc12366.uc.service.IWxQrcodeService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 微信渠道二维码
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-29 4:02 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/qrcode", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class WxQrcodeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxQrcodeController.class);

    @Autowired
    IWxQrcodeService iWxQrcodeService;

    /**
     * 获取微信二维码
     *
     * @param codeStr 二维码字符串
     * @return ResponseEntity
     */
    @GetMapping("/getcode")
    private ResponseEntity getWxQrcode(@RequestParam String codeStr) {
        try {
            String url = iWxQrcodeService.getWxQrcode(codeStr);
            if (url != null) {
                return ResponseEntity.ok(Utils.kv("data", url));
            } else {
                return ResponseEntity.ok(Utils.bodyStatus(9999, "获取二维码异常"));
            }
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.ok(Utils.bodyStatus(9999, "获取二维码异常"));
        }
    }

    /**
     * 查询渠道二维码列表
     *
     * @param name 二维码名称
     * @param page 当前页
     * @param size 每页大小
     * @return ResponseEntity
     */
    @GetMapping()
    public ResponseEntity selectList(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{},{},{}", name, page, size);

        Qrcode qrcode = new Qrcode.Builder().name(name).build();

        List<Qrcode> dataList = iWxQrcodeService.selectList(qrcode, page, size);
        PageInfo<Qrcode> pageInfo = new PageInfo<>(dataList);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                "total", pageInfo.getTotal()));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 查看二维码
     *
     * @param id PK
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity selectOne(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        Qrcode data = iWxQrcodeService.selectOne(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));

        LOGGER.info("{}", responseEntity);
        return responseEntity;

    }

    /**
     * 新增二维码
     *
     * @param qrcode Qrcode
     * @return ResponseEntity
     * @throws UnsupportedEncodingException 编码异常
     */
    @PostMapping()
    public ResponseEntity insert(@Valid @RequestBody Qrcode qrcode) throws UnsupportedEncodingException {
        LOGGER.info("{}", qrcode);

        Qrcode data = iWxQrcodeService.insert(qrcode);

        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 更新二维码
     *
     * @param id     PK
     * @param qrcode Qrcode
     * @return ResponseEntity
     * @throws UnsupportedEncodingException 编码异常
     */
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @Valid @RequestBody Qrcode qrcode) throws
            UnsupportedEncodingException {
        LOGGER.info("{},{}", id, qrcode);

        qrcode.setId(id);
        Qrcode data = iWxQrcodeService.update(qrcode);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 删除二维码
     *
     * @param id PK
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        iWxQrcodeService.delete(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
}
