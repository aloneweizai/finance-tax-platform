package com.abc12366.uc.model.bo;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-21
 * Time: 16:16
 */
public class MyTaskBO {
    private String currentPoints;
    private int unReceivePoints;
    private String taskRange;
    private String finishedTaskCount;

    public MyTaskBO() {
    }

    public String getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(String currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getUnReceivePoints() {
        return unReceivePoints;
    }

    public void setUnReceivePoints(int unReceivePoints) {
        this.unReceivePoints = unReceivePoints;
    }

    public String getTaskRange() {
        return taskRange;
    }

    public void setTaskRange(String taskRange) {
        this.taskRange = taskRange;
    }

    public String getFinishedTaskCount() {
        return finishedTaskCount;
    }

    public void setFinishedTaskCount(String finishedTaskCount) {
        this.finishedTaskCount = finishedTaskCount;
    }
}
