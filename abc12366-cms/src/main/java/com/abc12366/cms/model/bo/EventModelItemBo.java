package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS报名模型项表
 **/
@SuppressWarnings("serial")
public class EventModelItemBo implements Serializable {

    /**
     * 模型项ID**varchar(64)
     **/
    private String modelitemId;

    /**
     * 活动ID**varchar(64)
     **/
    private String eventId;

    /**
     * 字段**varchar(50)
     **/
    private String field;

    /**
     * 名称**varchar(100)
     **/
    private String itemLabel;

    /**
     * 排列顺序**int(11)
     **/
    private Integer priority;

    /**
     * 默认值**varchar(255)
     **/
    private String defValue;

    /**
     * 可选项**varchar(255)
     **/
    private String optValue;

    /**
     * 长度**varchar(20)
     **/
    private String textSize;

    /**
     * 校验方式**varchar(100)
     **/
    private String checkWay;

    /**
     * 文本行数**varchar(3)
     **/
    private String areaRows;

    /**
     * 文本列数**varchar(3)
     **/
    private String areaCols;

    /**
     * 帮助信息**varchar(255)
     **/
    private String help;

    /**
     * 帮助位置**varchar(1)
     **/
    private String helpPosition;

    /**
     * 数据类型**varchar(11)
     **/
    private String dataType;

    /**
     * 是否独占一行**tinyint(1)
     **/
    private Integer isSingle;

    /**
     * 是否显示**tinyint(1)
     **/
    private Integer isDisplay;

    /**
     * 是否必填项**tinyint(1)
     **/
    private Integer isRequired;

    /**
     * 图片宽度**int(11)
     **/
    private Integer imageWidth;

    /**
     * 图片高度**int(11)
     **/
    private Integer imageHeight;

    /**
     * 校验规则**varchar(500)
     **/
    private String checkRule;

    public String getModelitemId() {
        return this.modelitemId;
    }

    public void setModelitemId(String modelitemId) {
        this.modelitemId = modelitemId;
    }

    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getItemLabel() {
        return this.itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDefValue() {
        return this.defValue;
    }

    public void setDefValue(String defValue) {
        this.defValue = defValue;
    }

    public String getOptValue() {
        return this.optValue;
    }

    public void setOptValue(String optValue) {
        this.optValue = optValue;
    }

    public String getTextSize() {
        return this.textSize;
    }

    public void setTextSize(String textSize) {
        this.textSize = textSize;
    }

    public String getCheckWay() {
        return this.checkWay;
    }

    public void setCheckWay(String checkWay) {
        this.checkWay = checkWay;
    }

    public String getAreaRows() {
        return this.areaRows;
    }

    public void setAreaRows(String areaRows) {
        this.areaRows = areaRows;
    }

    public String getAreaCols() {
        return this.areaCols;
    }

    public void setAreaCols(String areaCols) {
        this.areaCols = areaCols;
    }

    public String getHelp() {
        return this.help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getHelpPosition() {
        return this.helpPosition;
    }

    public void setHelpPosition(String helpPosition) {
        this.helpPosition = helpPosition;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getIsSingle() {
        return this.isSingle;
    }

    public void setIsSingle(Integer isSingle) {
        this.isSingle = isSingle;
    }

    public Integer getIsDisplay() {
        return this.isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Integer getIsRequired() {
        return this.isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    public Integer getImageWidth() {
        return this.imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Integer getImageHeight() {
        return this.imageHeight;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getCheckRule() {
        return this.checkRule;
    }

    public void setCheckRule(String checkRule) {
        this.checkRule = checkRule;
    }

}
