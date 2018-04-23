package com.abc12366.gateway.util;

/**
 * 消息提醒相关常量定义
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-03-28 5:12 PM
 * @since 1.0.0
 */
public class RemindConstant {

    /**实物商品**/
    //发货
    public static final String DELIVER_GOODS_PREFIX = "您的宝贝已发出，订单号：";
    public static final String DELIVER_GOODS_YDH = "，运单号：";
    public static final String DELIVER_GOODS_PREFIX_NO = "您的宝贝已发出，订单号：";
    public static final String SUFFIX = "，如有疑问请联系客服咨询4008873133；";

    //退货审核通过
    public static final String  RETREAT_CHECK_ADOPT = "恭喜您！您的退货审核已通过，订单号：{#DATA.ORDER}，请按退货具体要求退货。";
    //退货审核拒绝
    public static final String  RETREAT_CHECK_REFUSE = "很抱歉！您的退货审核未通过，订单号：{#DATA.ORDER}，具体原因请至退货申请详情里查询；";
    //换货审核通过
    public static final String  EXCHANGE_CHECK_ADOPT = "恭喜您！您的换货审核已通过，订单号：{#DATA.ORDER}，请按换货具体要求换货；";
    //换货审核拒绝
    public static final String  EXCHANGE_CHECK_REFUSE = "很抱歉！您的换货审核未通过，订单号：{#DATA.ORDER}，具体原因请至换货申请详情里查询；";
    //换货发货
    public static final String EXCHANGE_DELIVER_GOODS_PREFIX = "您更换的宝贝已发出，订单号：{#DATA.ORDER}，运单号：（{#DATA.COMP}+{#DATA.EXPRESSNO}），如有疑问请联系客服咨询4008873133；";

    //退款
    public static final String REFUND_PREFIX = "您有一笔退款已完成，返回支付宝或微信";
    public static final String REFUND_SUFFIX = "元，请注意查收，订单号：";

    /**纸质发票**/
    //导入快递信息
    public static final String IMPORT_COURIER_INFO = "您申请的纸质发票已寄出，发票订单号：{#DATA.INVOICE}，运单号：（{#DATA.COMP}+{#DATA.EXPRESSNO}），如有疑问请联系客服咨询4008873133；";
    //审批通过
    public static final String INVOICE_CHECK_ADOPT = "您申请的纸质发票已审批通过，发票订单号：{#DATA.INVOICE}，敬请关注发票寄送信息；";
    //审批拒绝
    public static final String INVOICE_CHECK_REFUSE = "很抱歉！您的纸质发票申请审核未通过，发票订单号：{#DATA.INVOICE}，具体原因请至发票申请详情里查询；";
    /**电子发票**/
    //审批通过
    public static final String ELECTRON_INVOICE_CHECK_ADOPT = "您申请的电子发票已开具，发票订单号：{#DATA.INVOICE}，请至发票申请详情里下载发票；";
    public static final String RED_PACKAGE = "您的微信红包口令是：{#DATA.PACKAGE}，请前往官方《财税专家》微信公众号上领取红包。";


    //审批拒绝
    public static final String ELECTRON_INVOICE_CHECK_REFUSE = "很抱歉！您的电子发票申请审核未通过，发票订单号：{#DATA.INVOICE}，具体原因请至发票申请详情里查询；";
    /**虚拟商品**/
    //购买会员
    public static final String BUYING_MEMBERS_PREFIX = "恭喜您！您已成功升级为{#DATA.VIP}，请及时了解{#DATA.VIP}相关权益";
    public static final String BUYING_MEMBERS_SUFFIX = "查看详细会员权益";
    //积分充值
    public static final String INTEGRAL_RECHARGE = "恭喜您！您的积分已充值成功，当前积分为{#DATA.POINT}分，详情请查看积分明细；";
    
    
    //会员到期消息
    public static final String HYDQMSG="您的财税专家会员（{#DATA.LEVEL}）即将过期，过期时间:{#DATA.DATE}，为不影响您正常使用，请您及时续费";
    
    public static final String SBQXXTMSG="财税专家用户提醒，本月纳税申报可申报的报表种类：增.值.税、消.费.税、所.得.税、财务报表，申报期限：{#DATA.DATE}，实际申报种类以税务局核定信息为准，请您及时进行申报缴税！";
    
    public static final String SBQXSJMSG="本月纳税申报可申报的报表种类：增.值.税、消.费.税、所.得.税、财务报表，申报期限：{#DATA.DATE}，实际申报种类以税务局核定信息为准，请您及时进行申报缴税";

    //修改密码成功消息
    public static final String UPDATE_PWD_SUCCESS_SYS = "您的密码于{#DATA.DATE}被修改，请确认为本人操作，如有疑问请联系客服咨询4008873133。";

    public static final String UPDATE_PWD_SUCCESS_WX_1 = "您好,您的密码已通过";
    public static final String UPDATE_PWD_SUCCESS_WX_4 = "完成修改，请确认为本人操作，如有疑问请联系客服咨询4008873133。";

    public static final String UPDATE_PWD_SUCCESS_DX = "您的密码于{#DATA.DATE}被修改，请确认为本人操作";

    //未实名认证用户每天第一天登录发消息提醒其尽快进行认证
    public static final String UNREALNAME_SYS = "根据国家网信办相关规定，互联网平台的注册用户必须实名认证，您还未进行实名认证,请尽快完成实名认证，否则部分功能将无法使用!";

    public static final String UNREALNAME_WX_1 = "根据国家网信办相关规定，互联网平台的注册用户必须实名认证,";
    public static final String UNREALNAME_WX_2 = "未认证";
    public static final String UNREALNAME_WX_4 = "请尽快完成实名认证，否则部分功能将无法使用。";

    public static final String UNREALNAME_DX = "根据国家网信办相关规定，互联网平台的注册用户必须实名认证，您还未进行实名认证，请尽快完成实名认证，否则部分功能将无法使用。";

    //用户每天第一天登录发送未完成任务通知
    public static final String UNDO_TASK_SYS = "您还有{#DATA}个任务没有完成，快去做任务赢取积分和经验值吧!";

    //用户每天第一天登录发送未签到通知
    public static final String UNDO_CHECK_SYS = "签到赢积分换好礼，您今天的签到打卡任务未完成，请及时签到!";

    //用户等级提升提醒
    public static final String EXP_LEVEL_UP_SYS = "恭喜您！您的经验值等级提升了，详情请查看经验值明细!";

    //实名认证审核结果通知
    public static final String REALNAME_VALIDATE_SYS = "您好，你提交的实名认证信息已审核,审核结果：{#DATA.RESULT},时间：{#DATA.DATE},详情请登录财税专家软件查询！";
    public static final String REALNAME_VALIDATE_WX_1 = "您好，你提交的实名认证信息已审核";
    public static final String REALNAME_VALIDATE_WX_4 = "详情请登录财税专家软件查询！";
    public static final String REALNAME_VALIDATE_DX = "您好，你提交的实名认证信息已审核,审核结果：{#DATA.RESULT},时间：{#DATA.DATE}";

    //运营消息微信消息first
    public static final String YYXX_WX_FIRST = "财税专家提醒";
    public static final String YYXX_WX_KEY1 = "业务提醒";
    //运营消息微信消息remark
    public static final String YYXX_WX_REMARK = "详情请登录软件查询，咨询4008873133";

    //活动消息
    public static final String COUPON_YYXX = "您好，你已经领用《{#DATA.COUPON}》活动的优惠卷，请及时使用；";
}
