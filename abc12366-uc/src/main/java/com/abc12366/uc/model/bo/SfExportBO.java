package com.abc12366.uc.model.bo;

/**
 * 顺丰导出对象
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-10 5:59 PM
 * @since 1.0.0
 */
public class SfExportBO {

    // 订单号
    private String orderNo;
    // 公司名称
    private String company;
    // 收件人
    private String name;
    // 手机
    private String phone;
    // 电话
    private String tel;
    // 地址
    private String address;
    // 内容
    private String content;
    // 数量
    private Integer num;

    public SfExportBO() {
    }

    public SfExportBO(String orderNo, String company, String name, String phone, String tel, String address, String
            content, Integer num) {
        this.orderNo = orderNo;
        this.company = company;
        this.name = name;
        this.phone = phone;
        this.tel = tel;
        this.address = address;
        this.content = content;
        this.num = num;
    }

    private SfExportBO(Builder builder) {
        setOrderNo(builder.orderNo);
        setCompany(builder.company);
        setName(builder.name);
        setPhone(builder.phone);
        setTel(builder.tel);
        setAddress(builder.address);
        setContent(builder.content);
        setNum(builder.num);
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SfExportBO{" +
                "orderNo='" + orderNo + '\'' +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", num=" + num +
                '}';
    }

    public static final class Builder {
        private String orderNo;
        private String company;
        private String name;
        private String phone;
        private String tel;
        private String address;
        private String content;
        private Integer num;

        public Builder() {
        }

        public Builder orderNo(String val) {
            orderNo = val;
            return this;
        }

        public Builder company(String val) {
            company = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder tel(String val) {
            tel = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder num(Integer val) {
            num = val;
            return this;
        }

        public SfExportBO build() {
            return new SfExportBO(this);
        }
    }
}
