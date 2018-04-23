package com.abc12366.uc.model.bo;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-27
 * Time: 17:10
 */
public class PointBatchAwardBO {
    List<PointAwardBO> pointAwardBOList;

    public PointBatchAwardBO() {
    }

    public List<PointAwardBO> getPointAwardBOList() {
        return pointAwardBOList;
    }

    public void setPointAwardBOList(List<PointAwardBO> pointAwardBOList) {
        this.pointAwardBOList = pointAwardBOList;
    }

    @Override
    public String toString() {
        return "PointBatchAwardBO{" +
                "pointAwardBOList=" + pointAwardBOList +
                '}';
    }
}
