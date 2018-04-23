package com.abc12366.uc.wsbssoa.utils;

/**
 * @author JiangZuoWei
 * @createTime 2015年11月11日 下午2:14:22
 * @description
 */
public enum ConstantsUri {

    USER_LOGIN("用户登录接口", "login"),
    USER_CA_LOGIN("用户证书登录接口", "calogin"),
    GET_RSA_PK("获取RSA公钥", "pk"),
    CA_HELLO("纳税人用户证书服务器生成随机码", "cahello"),
    CA_LOGIN("纳税人用户证书登录", "caauthcode"),

    //发票验旧
    QUERY_FP_ZL("发票种类查询接口", "fp/fpyj/getfpzldm"),
    QUERY_FP_DM("发票种类查询发票代码接口", "fp/fpyj/getfpdm"),
    QUERY_FPLX_LIST("发票流向查询接口", "sssearch/queryfplx"),
    FP_FPYJ_INIT("初始化发票验旧", "fp/fpyj/init"),
    ADD_FPYJ("添加发票验旧接口", "fp/fpyj/add"),
    EDIT_FPYJ("修改发票验旧接口", "fp/fpyj/edit/{wsid}"),
    QUERY_FPYJ_INFO("查询发票验旧详细信息接口", "fp/fpyj/{wsid}"),
    DELETE_FPYJ("删除发票验旧信息接口", "fp/fpyj/delete/{wsid}"),
    AUTO_FPYJ("自动发票验旧信息接口", "fp/fpyj/auto"),
    //发票票种核定
    FP_PZHD_INIT("初始化发票票种核定接口", "fp/pzhd/init/{wszldm}"),
    QUERY_FP_PZHD_LIST("票种核定信息查询接口", "sssearch/sspzhd"),
    ADD_FP_PZHD("添加发票票种核定接口", "fp/pzhd/add"),
    EDIT_FP_PZHD("更新发票票种核定接口", "fp/pzhd/update"),
    DELETE_FP_PZHD("删除发票票种核定接口", "fp/pzhd/delete/{wsid}"),
    QUERY_FP_PZHD_INFO("查看发票票种核定", "fp/pzhd/select/{wsid}"),
    QUERY_FP_PZHD_DWSL("发票票种查询单位数量", "fp/pzhd/selectdwsl/{fpzlDm}"),
    //发票领用
    FP_LY_LIST("查询当前办税员填写的发票领用文书列表", "fp/ly/list"),
    FP_LY_INIT("初始化发票领用", "fp/ly/init"),
    ADD_FP_LY("添加发票领用", "fp/ly/add"),
    EDIT_FP_LY("更新发票领用", "fp/ly/edit/{wsid}"),
    DELETE_FP_LY("删除发票领用", "fp/ly/delete/{wsid}"),
    QUERY_FP_LY_INFO("查看发票领用", "fp/ly/select/{wsid}"),
    FP_LY_AUTO_FPYJ("获取自动验旧发票信息", "fp/ly/yjxx"),
    FP_LY_DO_FPYJ("发票领用的自动验旧", "fp/ly/zdyj"),

    FP_EXPRESS_INIT("初始化省市区县数据", "fp/express/init"),
    FP_EXPRESS_INSERT("添加快递地址", "fp/express/insert"),
    FP_EXPRESS_UPDATE("更新快递地址", "fp/express/update"),
    FP_EXPRESS_DELETE("删除快递地址", "fp/express/delete/{uuid}"),
    FP_EXPRESS_SELECT("查询快递地址", "fp/express/select"),
    FP_EXPRESS_SELECTONE("查询单个快递地址", "fp/express/selectOne/{uuid}"),
    FP_EXPRESS_UPDATEMr("设置默认地址", "fp/express/updateMr/{uuid}"),


    //印有本单位名称发票印制申请
    FP_YYBDWMC_YZ_INIT("初始化印有本单位名称发票印制申请", "fp/bdwfp/init"),
    ADD_FP_YYBDWMC_YZ("添加印有本单位名称发票印制申请", "fp/bdwfp/add"),
    EDIT_FP_YYBDWMC_YZ("更新印有本单位名称发票印制申请", "fp/bdwfp/update"),
    DELETE_FP_YYBDWMC_YZ("删除印有本单位名称发票印制申请", "fp/bdwfp/delete/{wsid}"),
    QUERY_FP_YYBDWMC_YZ_INFO("查看印有本单位名称发票印制申请", "fp/bdwfp/select/{wsid}"),
    QUERY_FP_YYBDWMC_FPZL("查询印有本单位名称发票印制申请中的发票种类", "fp/bdwfp/selectfp/{fpzlDm}"),

    //纳税人存款账户账号报告
    NSR_CKBG_INIT("初始化存款账户帐号", "ckbg/init"),
    NSR_CKBG_GET("查询存款账户帐号", "ckbg/get/{wsid}"),
    NSR_CKBG_ADD("新增存款账户帐号", "ckbg/add"),
    NSR_CKBG_EDIT("修改存款账户帐号", "ckbg/edit/{wsid}"),
    NSR_CKBG_DELETE("删除存款账户帐号", "ckbg/delete/{wsid}"),

    //外出经营活动税收管理
    WCJY_INIT("外出经营初始化", "zm/wcjy/init"),
    WCJY_GET("查询外出经营", "zm/wcjy/get/{wsid}"),
    WCJY_ADD("新增外出经营", "zm/wcjy/add"),
    WCJY_EDIT("修改外出经营", "zm/wcjy/edit/{wsid}"),
    WCJY_DELETE("删除外出经营", "zm/wcjy/delete/{wsid}"),
    WCJY_CY("网上查验外出经营证明", "zm/wcjy/cywcjyzm"),
    XZQH_INIT("初始化省市区县数据", "zm/wcjy/initxzqh"),

    //外出经营活动税收核销管理
    WCJYHX_LIST("获取当前纳税人核销文书列表", "zm/wcjyhx/querylist"),
    WCJYHX_INIT("外出经营初始化", "zm/wcjyhx/init/{wsid}"),
    WCJYHX_GET("查询外出经营", "zm/wcjyhx/get/{wsid}"),
    WCJYHX_ADD("新增外出经营", "zm/wcjyhx/add"),
    WCJYHX_EDIT("修改外出经营", "zm/wcjyhx/edit/{wsid}"),
    WCJYHX_DELETE("删除外出经营", "zm/wcjyhx/delete/{wsid}"),

    //评价
    ADD_EVALUATE("添加服务评价接口", "ggfw/fwpj/add"),
    QUERY_EVALUATE_LIST("服务评价查询列表接口", "ggfw/fwpj/getlist"),
    QUERY_EVALUATE_DETAIL("服务评价查询详细信息接口", "ggfw/fwpj/select"),
    QUERY_EVALUATE_COMP_SCORE("服务评价综合评分接口", "ggfw/fwpj/getzhpf"),

    //消息
    QUERY_MESSAGE_LIST("查询消息列表接口", "ggfw/msg/list"),
    QUERY_MESSAGE_DETAIL("查看消息详情接口", "ggfw/msg/read/{msgId}"),
    QUERY_MESSAGE_DELETE("删除消息接口", "ggfw/msg/delete"),

    //文书查询
    QUERY_WS_LSIT("获取文书列表接口", "ws/querylist"),
    //文书审批历史记录
    QUERY_WS_EXAM_RECORD("文书审批历史记录接口", "ws/spls/{wsid}"),
    QUERY_WS_EXAM_RECORD_SETUP("文书审批历史记录接口(设立登记)", "slswdj/spls/{wsid}"),

    //变更税务登记
    INIT_CHGTAXREG_INIT("初始化变更税务登记", "bgswdj/init"),
    ADD_CHGTAXREG_INFO("新增变更税务登记", "bgswdj/add"),
    UPDATE_CHGTAXREG_INFO("更新保存变更税务登记", "bgswdj/update"),
    DELETE_CHGTAXREG_INFO("删除变更税务登记", "bgswdj/delete/{wsid}"),
    QUERY_CHGTAXREG_INIT("变更税务登记查看", "bgswdj/select/{wsid}"),
    SELECT_CHOICE_CHGTAXREG_INFO("选择查看变更税务登记", "bgswdj/selectbgxm"),

    //扣缴义务人
    INIT_TAXDEBIT_INFO("初始化扣缴义务人", "kjywrdj/init"),
    ADD_TAXDEBIT_INFO("新增扣缴义务人", "kjywrdj/add"),
    UPDATE_TAXDEBIT_INFO("更新扣缴义务人", "kjywrdj/update"),
    DELETE_TAXDEBIT_INFO("删除扣缴义务人", "kjywrdj/delete/{wsid}"),
    QUERY_TAXDEBIT_INFO("查看扣缴义务人", "kjywrdj/select/{wsid}"),

    //设立登记
    GETSWJG_SETUP_INFO("获取税务机关", "slswdj/getswjg"),
    INIT_SETUP_INFO("初始化税务登记", "slswdj/inswdjb"),

    ADD_SETUP_COMPANY("新增税务登记表(单位)", "slswdj/insertswdjdw"),
    ADD_SETUP_PERSON("新增税务登记表(个人)", "slswdj/insertswdjgt"),
    ADD_SETUP_TEMP("新增税务登记表(临时)", "slswdj/insertswdjlsnsr"),

    UPDATE_SETUP_COMPANY("更新税务登记表(单位)", "slswdj/updateswdjdw"),
    UPDATE_SETUP_PERSON("更新税务登记表(个人)", "slswdj/updateswdjgt"),
    UPDATE_SETUP_TEMP("更新税务登记表(临时)", "slswdj/updateswdjlsnsr"),

    LIST_SETUP_INFO("查询税务登记表", "slswdj/list"),
    QUERY_SETUP_INFO("查询税务登记表", "slswdj/selectswdj"),
    QUERY_SETUP_FK_INFO("查询税务登记表反馈信息", "slswdj/selectfkxx"),

    //财会制度软件备案
    INIT_FINICIAL_ACT_SOF_INFI("初始化财会制度软件备案", "cwkjzd/init"),
    ADD_FINICIAL_ACT_SOF_INFI("新增化财会制度软件备案", "cwkjzd/add"),
    UDATE__FINICIAL_ACT_SOF_INFI("修改化财会制度软件备案", "cwkjzd/update"),
    DELETE_FINICIAL_ACT_SOF_INFI("删除化财会制度软件备案", "cwkjzd/delete/{wsid}"),
    SELECT_FINICIAL_ACT_SOF_INFI("查看财会制度软件备案", "cwkjzd/select/{wsid}"),

    //税收优惠资格备案
    INIT_TAX_PREFER_REG("新增税收优惠资格备案", "rd/ssyhzgba/init"),
    ADD_TAX_PREFER_REG("新增税收优惠资格备案", "rd/ssyhzgba/add"),
    UDATE_TAX_PREFER_REG("修改税收优惠资格备案", "rd/ssyhzgba/edit"),
    DELETE_TAX_PREFER_REG("删除税收优惠资格备案", "rd/ssyhzgba/delete/{wsid}"),
    SELECT_TAX_PREFER_REG("查看税收优惠资格备案", "rd/ssyhzgba/{wsid}"),

    //一般纳税人资格登记
    INIT_TAX_PAYER_REG("一般纳税人资格登记新增", "rd/zzsybnsr/init"),
    ADD_TAX_PAYER_REG("一般纳税人资格登记新增", "rd/zzsybnsr/add"),
    UDATE_TAX_PAYER_REG("一般纳税人资格登记修改", "rd/zzsybnsr/edit/{wsid}"),
    DELETE_TAX_PAYER_REG("一般纳税人资格登记删除", "rd/zzsybnsr/delete/{wsid}"),
    SELECT_TAX_PAYER_REG("一般纳税人资格登记查询", "rd/zzsybnsr/{wsid}"),

    //优惠办理
    CHECK_TAX_REBATEPREFER("增值税即征即退优惠办理检查", "yh/tssq/check"),
    INIT_TAX_REBATEPREFER("增值税即征即退优惠办理初始化", "yh/tssq/init"),
    ADD_TAX_REBATEPREFER("增值税即征即退优惠办理新增", "yh/tssq/add"),
    UPDATE_TAX_REBATEPREFER("增值税即征即退优惠办理修改", "yh/tssq/edit"),
    QUERY_TAX_REBATEPREFER("增值税即征即退优惠办理详情", "yh/tssq/{wsid}"),
    DEL_TAX_REBATEPREFER("增值税即征即退优惠办理删除", "yh/tssq/delete/{wsid}"),

    //发票代开
    //--增值税
    INIT_INVOICEAGENT("初始化增值税专用发票", "fp/fpdk/zzs/init"),
    ADD_INVOICEAGENT("新增增值税专用发票", "fp/fpdk/zzs/add"),
    DELETE_INVOICEAGENT("删除增值税专用发票", "fp/fpdk/zzs/delete/{wsid}"),
    UPDATE_INVOICEAGENT("更新增值税专用发票代开", "fp/fpdk/zzs/edit/{wsid}"),
    SELECT_INVOICEAGENT("查看增值税专用发票代开", "fp/fpdk/zzs/select/{wsid}"),
    QUERY_INVOICEAGENT("增值税发票代开列表", "fp/fpdk/list"),
    SELECT_INVOICEAGENTYNSK("获取发票代开应纳税款", "fp/fpdk/ynsk"),
    PAYTAX_INVOICEAGENT("发票代开三方协议缴税", "fp/fpdk/js/sfxy/{wsid}"),
    SELECT_INVOICEAGENTNSRXX("查询发票代开纳税人信息", "fp/fpdk/nsrxx/{nsrsbh}"),


    SELECT_HWLWLIST("查询货物劳务列表", "fp/fpdk/selecthwlwlist"),

    //--增值税普通发票
    INIT_INVOICEAGENTPT("初始化增值税普通发票", "fp/fpdk/pt/init"),
    ADD_INVOICEAGENTPT("新增增值税普通发票", "fp/fpdk/pt/add"),
    DELETE_INVOICEAGENTPT("删除增值税普通发票", "fp/fpdk/pt/delete/{wsid}"),
    UPDATE_INVOICEAGENTPT("更新增值税普通发票代开", "fp/fpdk/pt/edit/{wsid}"),
    SELECT_INVOICEAGENTPT("查看增值税普通发票代开", "fp/fpdk/pt/select/{wsid}"),

    //--货运发票
    INIT_INVOICEAGENT_TRANS("初始化货运专用发票", "fp/fpdk/hwys/init"),
    ADD_INVOICEAGENT_TRANS("新增货运专用发票", "fp/fpdk/hwys/add"),
    DELETE_INVOICEAGENT_TRANS("删除货运专用发票", "fp/fpdk/hwys/delete/{wsid}"),
    UPDATE_INVOICEAGENT_TRANS("更新货运专用发票代开", "fp/fpdk/hwys/edit/{wsid}"),
    SELECT_INVOICEAGENT_TRANS("查看货运专用发票代开", "fp/fpdk/hwys/select/{wsid}"),

    //简易申报
    SELECT_DECLARATCHECK_TRANS("获取简易申报用户核定信息", "dzsb/jysbhdxx"),
    CALCULAT_DECLARATCHECK_TRANS("获取简易申报税款计算", "dzsb/jysbjs"),
    DO_DECLARATCHECK_TRANS("发起简易申报", "dzsb/jysb"),
    QUERY_DECLARATCHECK_TRANS("查看简易申报详情", "dzsb/jysbxq"),

    //车购税申报
    INIT_VEHICLEPURTAX("初始化车购税预申报信息", "cgsysb/init"),
    SELECT_VEHICLEPURTAX("查询车购税预申报信息", "cgsysb/getlist"),
    ADD_VEHICLEPURTAX("添加车购税预申报信息", "cgsysb/insert"),
    UPDATE_VEHICLEPURTAX("更新车购税预申报信息", "cgsysb/update"),
    GET_VEHICLEPURTAX("查看车购税预申报信息", "cgsysb/get/{fpdm}/{fphm}"),
    DEL_VEHICLEPURTAX("删除车购税预申报信息", "cgsysb/delete/{id}"),
    GET_SUBTAXAGENCY("获取下一级税务机关", "cgsysb/getswjgj/{swjgDm}"),


    //地图导航
    MAP_NAVIG_INIT("地图导航初始化数据", "mapNavaig/mapNavaig_init"),

    QUERY_USER_INFO("获取纳税人用户信息", "user"),
    USER_MODINFO("修改纳税人用户信息", "user/edit"),
    LOG_OUT("登出", "logout"),

    //纳税人查验
    QUERY_NSRCHECK("查询纳税人查验信息", "ggfw/nsrcy"),
    QUERY_NSRZT("查询纳税人状态信息", "tjcx/nsrzt"),
    QUERY_NSRZG("查询纳税人资格信息", "tjcx/ybnsrzg"),
    QUERY_NSRXX("查询纳税人登记信息", "tjcx/nsrxx"),

    //涉税查询
    QUERY_DECLARATIONINFO("查询申报信息", "sssearch/querysbxx"),
    QUERY_TAXINFO("查询缴税信息", "sssearch/queryjsxx"),
    QUERY_NSRBASEINFO("查询纳税人基本信息", "sssearch/nsrjcxx"),
    QUERY_NSRBASEINFO1("获取纳税基础信息", "cx/nsrxx/{djxh}"),
    QUERY_DOCUMENTPROGRESS("查询文书审批进度信息", "sssearch/querywssqxx"),
    QUERY_INVOICEBALANCE("查询发票结存信息", "sssearch/ssfpjc"),
    QUERY_TICKETTYPE("查询票种核定信息", "sssearch/sspzhd"),
    QUERY_ENCODETABLEWSZL("查询文书种类编码表信息", "common/code/wszl"),
    QUERY_ENCODETABLEWSZT("查询文书种类编码表信息", "common/code/wszt"),
    QUERY_TAXPAYERIDENTI("查询增值税一般纳税人认定信息", "sssearch/zzsybnsrrd"),
    QUERY_THREEPARTYQUERY("查询三方协议信息", "sssearch/sfxyxx"),
    QUERY_DOCUMENTCATEGORY("查询文书类别信息", "ws/wsfbzl"),
    QUERY_DOCUMENT("查询文书附件信息", "ws/wsfj"),

    //纳税人信用等级查询
    INIT_CREDITRATING("纳税人信用等级查询初始化", "xyjbcx/init"),
    QUERY_CREDITRATING("纳税人信用等级公共查询", "xyjbcx/ggcx"),
    QUERY_CREDITRATING_MM("纳税人信用等级涉密查询", "xyjbcx/cxjb"),

    GET_SOA_ACCESS_TOKEN("接口访问权限请求", "app/login"),

    //电子缴款凭证查询
    QUERY_TAXPAYEDPROOF("查询电子缴款凭证", "jkpz/list"),

    QUERY_TOBE_PAYEDTAX("查询待缴税", "sssearch/queryjsxx"),

    //查询在线缴税
    QUERY_ONLINEPAYTAX("查询在线缴税", "dzjs/jsxxlist"),
    //银联缴税
    DO_UNIONPAY("银联缴税", "dzjs/yljs"),
    //三方协议缴税
    DO_THREEPARTYPAY("三方协议缴税", "dzjs/sfxyjs"),
    //出口退税预申报
    SID_LOGIN("SID方式单点登录", "sid/{code}"),

    //查询文书状态编码表信息
    QUERY_CODE_TABLE("查询文书种类编码表信息", "common/code/{name}"),

    //税务行政许可申请表初始化
    INIT_XZXK_APPLY("税务行政许可申请表初始化", "swsq/init"),

    //查询给定税务机关的下级机关
    COMMON_GETCHILD_SWJG("查询给定税务机关的下级机关", "common/swjg"),

    //记录税收缴款凭证打印次数
    COUNT_TAXPROOF_PRINT("记录税收缴款凭证打印次数", "jkpz/print/{dzsphm}"),
    //延期申报核准信息
    INIT_DELAYDECLARATION("延期申报初始化", "swsq/yqsb/init"),
    ADD_DELAYDECLARATION("新增延期申报核准信息", "swsq/yqsb/insert"),
    DELETE_DELAYDECLARATION("删除延期申报核准信息", "swsq/yqsb/delete/{wsid}"),
    QUERY_DELAYDECLARATION_INIT("查看延期申报核准信息", "swsq/yqsb/get/{wsid}"),
    UPDATE_DELAYDECLARATION("更新延期申报核准信息", "swsq/yqsb/update/{wsid}"),

    //延期缴税核准信息
    INIT_DELAYTAX("延期缴税初始化", "swsq/yqjnsk/init"),
    ADD_DELAYTAX("新增延期缴税核准信息", "swsq/yqjnsk/insert"),
    DELETE_DELAYTAX("删除延期缴税核准信息", "swsq/yqjnsk/delete/{wsid}"),
    QUERY_DELAYTAX_INIT("查看延期缴税核准信息", "swsq/yqjnsk/get/{wsid}"),
    UPDATE_DELAYTAX("更新延期缴税核准信息", "swsq/yqjnsk/update/{wsid}"),

    //初始化其他行政许可
    INIT_OTHER_PERMISSION("初始化其他行政许可", "swsq/qt/init"),

    //新增其他行政许可信息
    INSERT_OTHER_PERMISSION("新增其他行政许可", "swsq/qt/insert"),

    //修改其他行政许可信息
    UPDATE_OTHER_PERMISSION("修改其他行政许可", "swsq/qt/update/{wsid}"),

    //查看行政许可审批服务评价
    QUERY_XZXK_EVALUATE("查看行政许可审批服务评价", "swsq/mydpj/get/{wsid}"),
    //新增行政许可审批服务评价
    INSERT_XZXK_EVALUATE("新增行政许可审批服务评价", "swsq/mydpj/insert"),

    //查询行政许可信息
    QUERY_XZXK_INFO("查询行政许可信息", "swsq/qt/get/{wsid}"),

    //删除行政许可信息
    DELETE_XZXK_INFO("删除行政许可信息", "swsq/qt/delete/{wsid}"),

    //增值税专用发票
    VAT_LIMITED_INSERT("新增增值税专用发票最高限额信息", "swsq/zzsxe/insert"),
    VAT_LIMITED_DELETE("删除增值税专用发票最高限额信息", "swsq/zzsxe/delete/{wsid}"),
    VAT_LIMITED_QUERY("查询增值税专用发票最高限额信息", "swsq/zzsxe/get/{wsid}"),
    VAT_LIMITED_UPDATE("修改增值税专用发票最高限额信息", "swsq/zzsxe/update/{wsid}"),
    VAT_LIMITED_INIT("初始化增值税专用发票最高限额信息", "swsq/zzsxe/init"),

    //待办文书
    QUERY_BACKLOG("查询待办文书列表", "ws/dblist"),

    //企业所得税优惠事项
    VAT_OFF_QUERY_ITEM("查询企业所得税优惠事项备案管理目录", "yh/qysds/list"),
    VAT_OFF_INIT("初始化企业所得税优惠事项备案管理目录", "yh/qysds/init/{yhsxDm}"),
    VAT_OFF_INSERT("新增企业所得税优惠事项备案", "yh/qysds/add"),
    VAT_OFF_DELETE("删除企业所得税优惠事项备案", "yh/qysds/delete/{wsid}"),
    VAT_OFF_GET("查看企业所得税优惠事项备案", "yh/qysds/get/{wsid}"),
    VAT_OFF_UPDATE("企业所得税优惠事项备案", "yh/qysds/edit"),

    //实名认证
    QUERY_RENZHENGINFO("查询纳税人办税人员信息列表", "smrz/getgxrlist"),
    ADD_RENZHENGINFO("保存办税人员信息", "smrz/addgxr"),
    DEL_RENZHENGINFO("删除办税人员信息", "smrz/deletegxr/{gxruuid}"),
    QUERY_GXRFJ("获取干系人附件资料列表", "smrz/fjzl/selectfjzl/{gxruuid}"),
    SAVE_GXRFJ("保存或者修改干系人附件资料", "smrz/fjzl/edit"),
    DEL_GXRFJ("删除干系人附件资料", "smrz/fjzl/delete/{gxrfjzluuid}"),
    DEL_ALLGXRFJ("删除干系人所有附件资料", "smrz/fjzl/deleteallfj/{gxruuid}"),
    BAND_BANSHUIYUAN("绑定办税员", "user/bind/smrz"),
    GET_RENZHENGJIGUANFANWEI("获取认证机关范围", "rzpz"),

    //查询办税大厅导航
    QUERY_BSDT_LIST("查询办税大厅导航", "ggfw/bstdh/getlist"),
    //查询税务机关
    QUERY_SWJG_INFO_LIST("查询税务机关", "common/swjg"),
    //查询热点咨询列表
    QUERY_HOTSPOTS_LIST("查询热点咨询列表", "ggfw/rdwd/getlist"),
    //查询热点咨询详情
    QUERY_HOTSPOTS_DETAIL_ID("查询热点咨询详情", "ggfw/rdwd/get/{qId}"),
    QUERY_HOTSPOTS_DETAIL("查询热点咨询详情", "ggfw/rdwd/get"),
    //查询公告通知列表
    QUERY_NOTICE_LIST("查询公告通知列表", "ggfw/tzgg/list"),
    //查询公告通知详情
    QUERY_NOTICE_DETAIL("查询公告通知详情", "ggfw/tzgg/get"),
    QUERY_NOTICE_DETAIL_ID("查询公告通知详情", "ggfw/tzgg/get/{afficheId}"),
    //查询政策法规列表
    QUERY_LAWS_LIST("查询政策法规列表", "ggfw/zcfg/weblist"),
    //查询政策法规详情
    QUERY_LAWS_DETAIL_ID("查询政策法规详情", "ggfw/zcfg/get/{id}"),
    QUERY_LAWS_DETAIL("查询政策法规详情", "ggfw/zcfg/get"),
    //查询办税指南列表
    QUERY_TAXGUIDE_LIST("查询办税指南列表", "ggfw/bszn/querylist"),
    //查询办税指南详情
    QUERY_TAXGUIDE_DETAIL_ID("查询办税指南详情", "ggfw/bszn/get/{taxGuideId}"),
    QUERY_TAXGUIDE_DETAIL("查询办税指南详情", "ggfw/bszn/get"),

    QUERY_PUBLIC_REFRESH_TIME("查询应用服务最新的操作时间", "ggfw/new"),

    //获取办税日历信息
    QUERY_TAXDATE_LIST("查询某年办税日历", "ggfw/bsrl/getsbrq"),

    DOWNLOAD_SOFT("外网下载查询列表", "ggfw/download/getlist"),

    DOWNLOAD_SOFT_DETAIL("外网下载地址", "ggfw/download/get/{downId}"),

    MESSAGE_DY_LIST_INIT("消息订阅初始化列表", "ggfw/msg/orderlist"),

    MESSAGE_DY_FSFS_INIT("初始化获取消息发送方式代码集合", "ggfw/msg/init"),

    MESSAGE_DY("订阅消息", "ggfw/msg/updateorder"),

    RESERVETAX_DISTRICT_QUERY("办税厅导航查询", "ggfw/bstdh/ssjklist"),
    RESERVETAX_BUSSINESS_QUERY("业务分组查询", "ggfw/yybs/yyfl/{dtDm}"),
    //	RESERVETAX_BUSSINESS_TIME_QUERY("业务分组时间段查询","ggfw/yybs/ywfzxx"),//过时,改为获取预约时间段
    RESERVETAX_BUSSINESS_INSERT("预约办税", "ggfw/yybs/addyybs"),
    RESERVETAX_YYXXRECORD_QUERY("预约记录查询", "ggfw/yybs/yyjl/{sjh}"),
    RESERVETAX_YYXXRECORD_CANCEL("取消预约记录", "ggfw/yybs/qxyy"),
    RESERVETAX_REALTIMESITUATION_QUERY("办税厅实时监控信息", "ggfw/bstdh/ssjk/{taxDhId}"),
    RESERVETAX_RESERVETIME_QUERY("获取预约时间段", "ggfw/yybs/dtyysjd"),
    RESERVETAX_QUEUENUM_QUERY("票号查询排队情况", "ggfw/yybs/getyybyph"),

    GET_SWJGMC_BY_SWJGDM("根据税务机关代码查询税务机关名称", "common/code/swjg"),

    APPRANK_QUERY("查询应用排行", "ggfw/yyfw/yyfwpm"),

    STATITICS_PUPPET_QUERY("查询码表", "common/code/yyfw"),

    JOINT_TAX_GO("网厅页面请求跳转", "/lhwtverify.do");

    public String describe;

    public String uri;

    private ConstantsUri(String describe, String uri) {
        this.describe = describe;
        this.uri = uri;
    }

    public String toString() {
        return this.uri;
    }
}
