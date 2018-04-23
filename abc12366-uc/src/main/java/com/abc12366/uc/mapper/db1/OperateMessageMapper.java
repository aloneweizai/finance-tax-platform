package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.admin.OperateMessage;
import com.abc12366.uc.model.admin.bo.OperateMessageBO;
import com.abc12366.uc.model.admin.bo.YyxxLogBO;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-18
 * Time: 15:13
 */
public interface OperateMessageMapper {
    void insert(OperateMessage operateMessage);

    void update(OperateMessageBO operateMessageBO);

    void yyxxLog(YyxxLogBO yyxxLogBO);

    void delete(String id);
}
