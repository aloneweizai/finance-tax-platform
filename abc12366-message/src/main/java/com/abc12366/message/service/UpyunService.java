package com.abc12366.message.service;

import com.abc12366.message.model.UpyunTemplate;

import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-08
 * Time: 15:56
 */
public interface UpyunService {
    /**
     * 查询又拍云短信内容模板列表接口
     * @return {@linkplain com.abc12366.message.model.UpyunTemplate}
     */
    List<UpyunTemplate> selectList(Map<String, String> map);

    /**
     * 同步又拍云配置的短信模板到本地
     */
    void synchronizeTemplate();

}
