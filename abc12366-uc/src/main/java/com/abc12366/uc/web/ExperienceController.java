package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.ExpCalculateBO;
import com.abc12366.uc.model.bo.MyExperienceBO;
import com.abc12366.uc.service.ExperienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 用户经验值接口
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-25
 * Time: 16:22
 */
@RestController
@RequestMapping(path = "/experience", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ExperienceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserBindController.class);

    @Autowired
    private ExperienceService experienceService;

    /**
     * 获取我的经验值信息
     * @param userId 用户ID
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.bo.MyExperienceBO}
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity getMyExperience(@PathVariable String userId) {
        LOGGER.info("{}", userId);
        MyExperienceBO myExperienceBO = experienceService.getMyExperience(userId);
        return ResponseEntity.ok(Utils.kv("data", myExperienceBO));
    }

    /**
     * 根据经验值规则计算用户经验值
     * 所需参数为：用户ID（userID），经验值规则编码（ruleCode）
     * ruleCode参考com.abc12366.gateway.util.UCConstan.java静态变量类
     * @param expCalculateBO {@linkplain com.abc12366.uc.model.bo.ExpCalculateBO}
     * @return ResponseEntity
     */
     /**经验值计算规则编码
     //1.每日登录（由于登陆经验值计算是单做的，此条规则现在只用于展现）
     public final static String EXP_RULE_LOGIN_CODE = "E-mrdl";
     //2.网上申报
     public final static String EXP_RULE_WSSB_CODE = "E-WSSB";
     //3.预缴税款
     public final static String EXP_RULE_YJSK_CODE = "E-yjsk";
     //4.系统修复
     public final static String EXP_RULE_XTXF_CODE = "E-xtxf";
     //5.实名认证
     public final static String EXP_RULE_SMRZ_CODE = "E-smrz";
     //6.每日问答收藏
     public final static String EXP_RULE_MRWDSC_CODE = "E-mrwdsc";
     //7.下载企业信息
     public final static String EXP_RULE_XZQYXX_CODE = "E-qyxx";
     //8.每日问答分享
     public final static String EXP_RULE_MRWDFX_CODE = "E-mrwdfx";
     //9.每日分享课程
     public final static String EXP_RULE_MRFXKC_CODE = "E-mrfxrw";
     //10.绑定税号
     public final static String EXP_RULE_BDSH_CODE = "E-BDSH";
     //11.每日收藏课程
     public final static String EXP_RULE_MRSCKC_CODE = "E-mrsc";
     //12.每日课程学习
     public final static String EXP_RULE_MRKCXX_CODE = "E-KCXX1";
     //13.每日浏览资讯
     public final static String EXP_RULE_MRLLZX_CODE = "E-mrllzx";
     //14.介质申报
     public final static String EXP_RULE_JZSB_CODE = "E-jszb";
     //15.海关完税凭证采集
     public final static String EXP_RULE_HGWSPZCJ_CODE = "E-hg";
     //16.网上零申报
     public final static String EXP_RULE_WSLSB_CODE = "E-wslsb";
     //17.网上缴税
     public final static String EXP_RULE_WSJS_CODE = "E-wsjs";
     //18.作废报表
     public final static String EXP_RULE_ZFBB_CODE = "E-bbzf";
     //19.分支机构设置
     public final static String EXP_RULE_FZJGSZ_CODE = "E-fzjg";
     //20.获取申报结果
     public final static String EXP_RULE_HQSBJG_CODE = "E-sbjg";
     //21.打印完税凭证
     public final static String EXP_RULE_DYWSPZ_CODE = "E-wspz";
     //22.查询服务器数据
     public final static String EXP_RULE_CXFWQSJ_CODE = "E-fwqsj";
     //23.固定资产折旧管理
     public final static String EXP_RULE_GDZCZJGL_CODE = "E-gdzc";
     //24.在线解答
     public final static String EXP_RULE_ZXJD_CODE = "E-ZXJD";
     //25.在线提问
     public final static String EXP_RULE_ZXTW_CODE = "E-ZXTW";
     //26.每日回答问题
     public final static String EXP_RULE_MRHDWT_CODE = "E-hdwt";
     */
    @PostMapping(path = "/calculate")
    public ResponseEntity expCalculate(@RequestBody ExpCalculateBO expCalculateBO){
        LOGGER.info("{}", expCalculateBO);
        experienceService.calculate(expCalculateBO);
        return ResponseEntity.ok(Utils.kv());
    }
}
