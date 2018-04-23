package com.abc12366.uc.model.bo;

/**
 * 用户区域分布统计bo
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-08
 * Time: 14:45
 */
public class RigionStatisBO {
    /**
     * 区域名称
     */
    private String regionName;
    /**
     * 区域代码
     */
    private String regionCode;
    /**
     * 数量
     */
    private int allCount;

    /**
     * 时间区间
     */
    private String timeInterval;
    /**
     * 标签名
     */
    private String tagName;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "RigionStatisBO{" +
                "regionName='" + regionName + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", allCount=" + allCount +
                ", timeInterval='" + timeInterval + '\'' +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
