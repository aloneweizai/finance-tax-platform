package com.abc12366.uc.service;

import com.abc12366.uc.model.abc4000.ABC4000CallbackBO;
import com.abc12366.uc.model.abc4000.ResponseForAbc4000;
import com.abc12366.uc.model.abc4000.ResponseForAbc4000Simple;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 9:39
 */
public interface NsrABC4000Service {
    ResponseForAbc4000 selectList(String userId);

    ResponseForAbc4000Simple update(ABC4000CallbackBO data);
}
