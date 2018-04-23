package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.PointBatchAwardBO;
import com.abc12366.uc.model.bo.PointCalculateBO;
import com.abc12366.uc.model.bo.PointsBO;
import com.abc12366.uc.service.PointsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 积分接口控制器
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-20
 * Time: 21:42
 */
@RestController
@RequestMapping(path = "/points", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class PointsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointsController.class);

    @Autowired
    private PointsService pointsService;

    /**
     * 根据用户ID查询用户积分情况
     * @param userId 用户ID
     * @return ResponseEntity {@linkplain com.abc12366.uc.model.bo.PointsBO}
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity selectOne(@PathVariable String userId) {
        LOGGER.info("{}", userId);
        PointsBO pointsBO = pointsService.selectOne(userId);
        LOGGER.info("{}", pointsBO);
        return ResponseEntity.ok(Utils.kv("data", pointsBO));
    }

    /**
     * 根据积分规则计算用户积分
     * 所需参数为：用户ID（userID），经验值规则编码（ruleCode）
     * ruleCode参考com.abc12366.gateway.util.UCConstan.java静态变量类
     */
    /**积分计算规则ID
    //1.帮帮批量奖励用户积分P-bpljljf
    public final static String POINT_RULE_BANGBANG_BATCH_AWARD_CODE = "P-bpljljf";
    //2.首次申报缴税
    public final static String POINT_RULE_SCSBJS_CODE = "P-sbjs";
    //3.首次邮箱认证
    public final static String POINT_RULE_SCYXRZ_CODE = "P-yxrz";
    //4.用户下单送积分P-xdsjf
    public final static String POINT_RULE_ORDER_CODE = "P-xdsjf";
    //5.首次消费
    public final static String POINT_RULE_SCXF_CODE = "P-yxrz";
    //6.首次修改登录密码
    public final static String POINT_RULE_SCXGDLMM_CODE = "P-xgjymm";
    //7.首次手机认证
    public final static String POINT_RULE_SCSJRZ_CODE = "P-scsjrz";
    //8.退单返还积分P-fhjf
    public final static String POINT_RULE_ORDER_RETURN_CODE = "P-fhjf";
    //每日签到
    public final static String POINT_RULE_CHECK_CODE = "P-mrqd";
    //12.用户等级升级奖励P-yhdjsjjl
    public final static String POINT_RULE_EXP_UP_CODE = "P-yhdjsjjl";
    //11.每日评论问答
    public final static String POINT_RULE_MRPLHD_CODE = "P-mrplwd";
    //12.每日评论课程
    public final static String POINT_RULE_MRPLKC_CODE = "P-mrplkc";
    //13.抽奖扣积分P-cjkjf
    public final static String POINT_RULE_LOTTERY_CODE = "P-cjkjf";
    //14.积分兑换（该条规则不用于积分计算，仅用于展示）
    public final static String POINT_RULE_EXCHANGE_CODE = "P-jfdh";
    //15.用户补签到
    public final static String POINT_RULE_RECHECK_CODE = "P-recheck";
    //16.一次性消费3000
    public final static String POINT_RULE_YCXXF3000_CODE = "P-ycxxx3";
    //17.每日评论
    public final static String POINT_RULE_MRPL_CODE = "P-mrpl";
    //18.一次性消费5000
    public final static String POINT_RULE_YCXXF5000_CODE = "P-ycxxx5";
    //19.一次性消费1000
    public final static String POINT_RULE_YCXXF1000_CODE = "P-ycxxf1";
    //19.首次下载安装ABC4000
    public final static String POINT_RULE_SCXZAZABC4000_CODE = "P-azabc";
    //20.关注财税专家公众号
    public final static String POINT_RULE_GZCSZJGZH_CODE = "P-cszj";
    //21.首次上传头像
    public final static String POINT_RULE_SCSCTX_CODE = "P-sctx";
    //22.首次实名认证
    public final static String POINT_RULE_SCSMRZ_CODE = "P-smrz";
     */
    @PostMapping(path = "/calculate")
    public ResponseEntity calculate(@Valid @RequestBody PointCalculateBO pointCalculateBO){
        int point = pointsService.calculate(pointCalculateBO);
        return ResponseEntity.ok(Utils.kv("data", point));
    }

    /**
     * 批量用户奖励积分接口,并且发送帮帮信息
     * @param pointBatchAwardBO {@linkplain com.abc12366.uc.model.bo.PointBatchAwardBO}
     * @return ResponseEntity
     */
    @PostMapping(path = "/batch/award")
    public ResponseEntity batchAward(@Valid @RequestBody PointBatchAwardBO pointBatchAwardBO){
        pointsService.batchAward(pointBatchAwardBO);
        return ResponseEntity.ok(Utils.kv());
    }
}
