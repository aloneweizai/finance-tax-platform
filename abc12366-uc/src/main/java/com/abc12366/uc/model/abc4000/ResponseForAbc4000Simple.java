package com.abc12366.uc.model.abc4000;

import com.abc12366.uc.model.BaseObject;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 11:04
 */
public class ResponseForAbc4000Simple extends BaseObject {
    private boolean success;

    public ResponseForAbc4000Simple() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
