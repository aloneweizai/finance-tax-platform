package com.abc12366.uc.service.admin.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.AdminOperationMapper;
import com.abc12366.uc.mapper.db2.AdminOperationRoMapper;
import com.abc12366.uc.model.bo.AdminModifyUphoneLogList;
import com.abc12366.uc.model.bo.AdminModifyUserPhoneLogBO;
import com.abc12366.uc.service.admin.AdminOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-15
 * Time: 14:56
 */
@Service
public class AdminOperationServiceImpl implements AdminOperationService {

    @Autowired
    private AdminOperationMapper adminOperationMapper;

    @Autowired
    private AdminOperationRoMapper adminOperationRoMapper;

    @Override
    public AdminModifyUserPhoneLogBO insert(AdminModifyUserPhoneLogBO logBO) {
        logBO.setId(Utils.uuid());
        logBO.setCreateTime(new Date());
        int i = adminOperationMapper.insert(logBO);
        if(i<1){
            throw new ServiceException(4101);
        }
        return logBO;
    }

    @Override
    public List<AdminModifyUphoneLogList> selectList(String userId) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        return adminOperationRoMapper.selectList(map);
    }
}
