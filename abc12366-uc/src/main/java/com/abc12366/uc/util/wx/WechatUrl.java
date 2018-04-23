package com.abc12366.uc.util.wx;

/**
 * 微信调用地址
 * @author zhushuai 2017-7-28
 */
public enum WechatUrl {

    WXUSETOKEN("微信token获取", "/cgi-bin/token"),
    WXUSELIST("公众号关注列表", "/cgi-bin/user/get"),
    WXUSEBATCH("批量获取用户信息", "/cgi-bin/user/info/batchget"),
    WXUSEINFO("关注用户详细信息", "/cgi-bin/user/info"),
    WXJSUSEINFO("用户详细信息", "/sns/userinfo"),
    WXMENUCREATE("自定义菜单创建", "/cgi-bin/menu/create"),
    WXMENUQUERY("自定义菜单查询", "/cgi-bin/menu/get"),
    WXMENUDEL("自定义菜单产删除", "/cgi-bin/menu/delete"),
    WXINDUSTRY_SET("设置所属行业", "/cgi-bin/template/api_set_industry"),
    WXINDUSTRY_GET("获取设置的行业信息", "/cgi-bin/template/get_industry"),
    TEMPLATEMSG_LIST("获取模板列表", "/cgi-bin/template/get_all_private_template"),
    TEMPLATEMSG_DEL("删除模板", "/cgi-bin/template/del_private_template"),
    TEMPLATEMSG_SEND("发送模板", "/cgi-bin/message/template/send"),
    WXJSTIECK_GET("获取微信JSticket","/cgi-bin/ticket/getticket"),
    MATERIAL_COUNT("素材总数", "/cgi-bin/get_materialcount"),
    MATERIAL_LIST("素材列表", "/cgi-bin/material/batchget_material"),
    MATERIAL_ADDNEWS("添加图文素材", "/cgi-bin/material/add_news"),
    MATERIAL_ADDMATE("添加素材", "/cgi-bin/material/add_material"),
    MATERIAL_NEWSIMG("添加图文素材图片", "/cgi-bin/media/uploadimg"),
    WXQRCODE_TICKET("获取二维码ticket", "/cgi-bin/qrcode/create"),
    WXIMG_DOWN("素材下载","/cgi-bin/media/get"),
    WXIMG_JSTOKEN("JSTOKEN","/sns/oauth2/access_token"),
    LONG2SHORT("长链接转短链接","/cgi-bin/shorturl"),
    // 商户平台接口
    SENDREDPACK("发放普通红包", "/mmpaymkttransfers/sendredpack"),
    GETHBINFO("查询红包记录", "/mmpaymkttransfers/gethbinfo"), 
    PAYORDER("微信统一下单", "/pay/unifiedorder"), 
    WXORDERQUERY("微信支付订单查询","/pay/orderquery"),
    WXREFUND("微信支付退款申请","/secapi/pay/refund"),
    WXREFUNDQUERY("微信支付退款查询","/pay/refundquery"),
    WXDOWNBILL("微信下载对账单","/pay/downloadbill");



    public String describe;

    public String uri;

    private WechatUrl(String describe, String uri) {
        this.describe = describe;
        this.uri = uri;
    }

    public String toString() {
        return this.uri;
    }
}
