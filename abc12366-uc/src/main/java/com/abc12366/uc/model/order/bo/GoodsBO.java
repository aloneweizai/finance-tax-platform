package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


/**
 * 产品表
 **/
@SuppressWarnings("serial")
public class GoodsBO implements Serializable {

    private String id;
    @NotEmpty
    @Size(min = 1, max = 200)
    private String name;

    @Size(min = 1, max = 200)
    private String imageUrl;

    @Size(min = 1, max = 2000)
    private String introduction;

    @Size(min = 1, max = 4000)
    private String details;

    @NotEmpty
    @Size(min = 1, max = 64)
    private String categoryId;
    //分类名称
    private String categoryName;
    @NotNull
    private Boolean status;
    private java.util.Date createTime;
    private java.util.Date lastUpdate;

    @NotNull
    private Integer giftPoints;

    @NotNull
    private Integer sort;
    private String unit;

    @NotNull
    private String recommendType;

    @NotNull
    private Integer isShipping;
    @NotNull
    private Integer isFreeShipping;

    @NotEmpty
    private String tradeMethod;
    private List<ProductBO> productBOList;
    private Double totalStock;
    private Double totalPrice;
    /**
     * 商品类型
     **/
    private String goodsType;

    private ProductBO productBO;
    /**
     * 销售价
     **/
    private Double sellingPrice;

    /**
     * 库存开始值
     **/
    private Integer startRepo;
    /**
     * 库存结束值
     **/
    private Integer endRepo;

    /**
     * 发票内容：1.软件服务费 2.财税咨询费 3.技术服务费 4.财税培训费
     * @return
     */
    @NotEmpty
    private String invoiceContent;

    private String invoiceContentDetail;

    /**是否可退货，0，可退，1，不可退**/
    @NotEmpty
    private String isReturn;

    /**是否可换货，0：可换，1：不可换**/
    @NotEmpty
    private String isExchange;

    /**会员等级**/
    private String memberLevel;

    /**积分对比比例**/
    private Double prop;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(java.util.Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getGiftPoints() {
        return this.giftPoints;
    }

    public void setGiftPoints(Integer giftPoints) {
        this.giftPoints = giftPoints;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRecommendType() {
        return this.recommendType;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
    }

    public Integer getIsShipping() {
        return isShipping;
    }

    public void setIsShipping(Integer isShipping) {
        this.isShipping = isShipping;
    }

    public Integer getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(Integer isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public List<ProductBO> getProductBOList() {
        return productBOList;
    }

    public void setProductBOList(List<ProductBO> productBOList) {
        this.productBOList = productBOList;
    }

    public Double getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Double totalStock) {
        this.totalStock = totalStock;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getEndRepo() {
        return endRepo;
    }

    public void setEndRepo(Integer endRepo) {
        this.endRepo = endRepo;
    }

    public Integer getStartRepo() {
        return startRepo;
    }

    public void setStartRepo(Integer startRepo) {
        this.startRepo = startRepo;
    }

    public ProductBO getProductBO() {
        return productBO;
    }

    public void setProductBO(ProductBO productBO) {
        this.productBO = productBO;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    @Override
    public String toString() {
        return "GoodsBO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", introduction='" + introduction + '\'' +
                ", details='" + details + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", giftPoints=" + giftPoints +
                ", sort=" + sort +
                ", unit='" + unit + '\'' +
                ", recommendType='" + recommendType + '\'' +
                ", isShipping=" + isShipping +
                ", isFreeShipping=" + isFreeShipping +
                ", tradeMethod='" + tradeMethod + '\'' +
                ", productBOList=" + productBOList +
                ", totalStock=" + totalStock +
                ", totalPrice=" + totalPrice +
                ", goodsType='" + goodsType + '\'' +
                ", productBO=" + productBO +
                ", sellingPrice=" + sellingPrice +
                ", startRepo=" + startRepo +
                ", endRepo=" + endRepo +
                ", invoiceContent='" + invoiceContent + '\'' +
                '}';
    }

    public String getInvoiceContentDetail() {
        return invoiceContentDetail;
    }

    public void setInvoiceContentDetail(String invoiceContentDetail) {
        this.invoiceContentDetail = invoiceContentDetail;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public String getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(String isExchange) {
        this.isExchange = isExchange;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Double getProp() {
        return prop;
    }

    public void setProp(Double prop) {
        this.prop = prop;
    }
}
