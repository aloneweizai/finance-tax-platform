package com.abc12366.bangbang.model.event;

import java.io.Serializable;

/**
 * Created by stuy on 2017/9/14.
 */
public class EventApplyFieldBo  implements Serializable {

    private String fieldkey;
    private String fieldvalue;
    private int isRequired;

    public int getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(int isRequired) {
        this.isRequired = isRequired;
    }

    public String getFieldkey() {
        return fieldkey;
    }

    public void setFieldkey(String fieldkey) {
        this.fieldkey = fieldkey;
    }

    public String getFieldvalue() {
        return fieldvalue;
    }

    public void setFieldvalue(String fieldvalue) {
        this.fieldvalue = fieldvalue;
    }
}
