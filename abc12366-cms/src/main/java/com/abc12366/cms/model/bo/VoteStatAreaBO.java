package com.abc12366.cms.model.bo;

/**
 * 投票统计IP归属区域结果
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-29 12:47 PM
 * @since 1.0.0
 */
public class VoteStatAreaBO {

    private String city;

    private String cityId;

    private Integer num;

    public VoteStatAreaBO() {
    }

    public VoteStatAreaBO(String city, String cityId, Integer num) {
        this.city = city;
        this.cityId = cityId;
        this.num = num;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "VoteStatAreaBO{" +
                "city='" + city + '\'' +
                ", cityId='" + cityId + '\'' +
                ", num=" + num +
                '}';
    }
}
