package com.abc12366.bangbang.model.curriculum.bo;
import java.io.Serializable;


/**
 * 
 * 课程评价表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumEvaluateTjBo implements Serializable {

    /**好评数**int**/
    private Integer evaluateNum45;

    /**中等评数**int**/
    private Integer evaluateNum23;

    /**差评论数**int**/
    private Integer evaluateNum1;

    /**总评论数**int**/
    private Integer evaluateNum;

    public Integer getEvaluateNum45() {
        return evaluateNum45;
    }

    public void setEvaluateNum45(Integer evaluateNum45) {
        this.evaluateNum45 = evaluateNum45;
    }

    public Integer getEvaluateNum23() {
        return evaluateNum23;
    }

    public void setEvaluateNum23(Integer evaluateNum23) {
        this.evaluateNum23 = evaluateNum23;
    }

    public Integer getEvaluateNum1() {
        return evaluateNum1;
    }

    public void setEvaluateNum1(Integer evaluateNum1) {
        this.evaluateNum1 = evaluateNum1;
    }

    public Integer getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(Integer evaluateNum) {
        this.evaluateNum = evaluateNum;
    }
}
