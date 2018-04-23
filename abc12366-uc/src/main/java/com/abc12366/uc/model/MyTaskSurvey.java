package com.abc12366.uc.model;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-27
 * Time: 15:26
 */
public class MyTaskSurvey {
    //本月获取的经验值
    private String earnedExp;
    //本月获取的积分
    private String earnedPoint;
    //本月任务排名
    private String taskRange;
    //本月完成任务数量
    private String completedTaskCount;

    public MyTaskSurvey() {
    }

    public String getEarnedExp() {
        return earnedExp;
    }

    public void setEarnedExp(String earnedExp) {
        this.earnedExp = earnedExp;
    }

    public String getEarnedPoint() {
        return earnedPoint;
    }

    public void setEarnedPoint(String earnedPoint) {
        this.earnedPoint = earnedPoint;
    }

    public String getCompletedTaskCount() {
        return completedTaskCount;
    }

    public void setCompletedTaskCount(String completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }

    public String getTaskRange() {
        return taskRange;
    }

    public void setTaskRange(String taskRange) {
        this.taskRange = taskRange;
    }

    @Override
    public String toString() {
        return "MyTaskSurvey{" +
                "earnedExp='" + earnedExp + '\'' +
                ", earnedPoint='" + earnedPoint + '\'' +
                ", taskRange='" + taskRange + '\'' +
                ", completedTaskCount='" + completedTaskCount + '\'' +
                '}';
    }
}
