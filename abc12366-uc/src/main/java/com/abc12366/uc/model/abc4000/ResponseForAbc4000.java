package com.abc12366.uc.model.abc4000;

import com.abc12366.uc.model.BaseObject;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 10:02
 */
public class ResponseForAbc4000 extends BaseObject {
    private boolean success;
    private List<NSRXX> t_nsrxx;

    public ResponseForAbc4000() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<NSRXX> getT_nsrxx() {
        return t_nsrxx;
    }

    public void setT_nsrxx(List<NSRXX> t_nsrxx) {
        this.t_nsrxx = t_nsrxx;
    }
}
