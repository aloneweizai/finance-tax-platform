package com.abc12366.uc.model;

import java.sql.Timestamp;

/**
 * IP地址归属记录
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-22 10:29 AM
 * @since 1.0.0
 */
public class Ip {

    private String id;
    private String ip;
    private String country;
    private String countryId;
    private String area;
    private String areaId;
    private String region;
    private String regionId;
    private String city;
    private String cityId;
    private String county;
    private String countyId;
    private String isp;
    private String ispId;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Ip() {
    }

    public Ip(String id, String ip, String country, String countryId, String area, String areaId, String region,
              String regionId, String city, String cityId, String county, String countyId, String isp, String ispId,
              Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.ip = ip;
        this.country = country;
        this.countryId = countryId;
        this.area = area;
        this.areaId = areaId;
        this.region = region;
        this.regionId = regionId;
        this.city = city;
        this.cityId = cityId;
        this.county = county;
        this.countyId = countyId;
        this.isp = isp;
        this.ispId = ispId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    private Ip(Builder builder) {
        setId(builder.id);
        setIp(builder.ip);
        setCountry(builder.country);
        setCountryId(builder.countryId);
        setArea(builder.area);
        setAreaId(builder.areaId);
        setRegion(builder.region);
        setRegionId(builder.regionId);
        setCity(builder.city);
        setCityId(builder.cityId);
        setCounty(builder.county);
        setCountyId(builder.countyId);
        setIsp(builder.isp);
        setIspId(builder.ispId);
        setCreateTime(builder.createTime);
        setUpdateTime(builder.updateTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getIspId() {
        return ispId;
    }

    public void setIspId(String ispId) {
        this.ispId = ispId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Ip{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", country='" + country + '\'' +
                ", countryId='" + countryId + '\'' +
                ", area='" + area + '\'' +
                ", areaId='" + areaId + '\'' +
                ", region='" + region + '\'' +
                ", regionId='" + regionId + '\'' +
                ", city='" + city + '\'' +
                ", cityId='" + cityId + '\'' +
                ", county='" + county + '\'' +
                ", countyId='" + countyId + '\'' +
                ", isp='" + isp + '\'' +
                ", ispId='" + ispId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public static final class Builder {
        private String id;
        private String ip;
        private String country;
        private String countryId;
        private String area;
        private String areaId;
        private String region;
        private String regionId;
        private String city;
        private String cityId;
        private String county;
        private String countyId;
        private String isp;
        private String ispId;
        private Timestamp createTime;
        private Timestamp updateTime;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder ip(String val) {
            ip = val;
            return this;
        }

        public Builder country(String val) {
            country = val;
            return this;
        }

        public Builder countryId(String val) {
            countryId = val;
            return this;
        }

        public Builder area(String val) {
            area = val;
            return this;
        }

        public Builder areaId(String val) {
            areaId = val;
            return this;
        }

        public Builder region(String val) {
            region = val;
            return this;
        }

        public Builder regionId(String val) {
            regionId = val;
            return this;
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public Builder cityId(String val) {
            cityId = val;
            return this;
        }

        public Builder county(String val) {
            county = val;
            return this;
        }

        public Builder countyId(String val) {
            countyId = val;
            return this;
        }

        public Builder isp(String val) {
            isp = val;
            return this;
        }

        public Builder ispId(String val) {
            ispId = val;
            return this;
        }

        public Builder createTime(Timestamp val) {
            createTime = val;
            return this;
        }

        public Builder updateTime(Timestamp val) {
            updateTime = val;
            return this;
        }

        public Ip build() {
            return new Ip(this);
        }
    }
}
