package com.abc12366.uc.service;

import com.abc12366.uc.model.weixin.bo.qrcode.Qrcode;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 渠道二维码服务接口
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-29 10:51 AM
 * @since 1.0.0
 */
public interface IWxQrcodeService {

    /**
     * 查询渠道二维码列表
     *
     * @param qrcode Qrcode
     * @param page   当前页
     * @param size   每页大小
     * @return List<Qrcode>
     */
    List<Qrcode> selectList(Qrcode qrcode, int page, int size);

    /**
     * 查看二维码
     *
     * @param id PK
     * @return Qrcode
     */
    Qrcode selectOne(String id);

    /**
     * 新增二维码
     *
     * @param qrcode Qrcode
     * @return Qrcode
     * @throws UnsupportedEncodingException 编码异常
     */
    Qrcode insert(Qrcode qrcode) throws UnsupportedEncodingException;

    /**
     * 更新二维码
     *
     * @param qrcode Qrcode
     * @return Qrcode
     * @throws UnsupportedEncodingException 编码异常
     */
    Qrcode update(Qrcode qrcode) throws UnsupportedEncodingException;

    /**
     * 删除二维码
     *
     * @param id PK
     */
    void delete(String id);

    /**
     * 获取微信二维码
     *
     * @param codeStr 二维码字符串
     * @return String
     * @throws UnsupportedEncodingException 编码异常
     */
    String getWxQrcode(String codeStr) throws UnsupportedEncodingException;
}
