package com.abc12366.gateway.service;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.mapper.db1.IpSettingMapper;
import com.abc12366.gateway.mapper.db2.IpSettingRoMapper;
import com.abc12366.gateway.model.IpSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MY on 2017-05-26.
 */
@Service
public class IpSettingServiceImpl implements IpSettingService {

    @Autowired
    private IpSettingRoMapper ipSettingRoMapper;

    @Autowired
    private IpSettingMapper ipSettingMapper;

    @Override
    public IpSetting selectOne() {
        return ipSettingRoMapper.selectOne();
    }

    @Override
    public IpSetting update(IpSetting ipSetting) {
        int upd = ipSettingMapper.update(ipSetting);
        if (upd != 1) {
            throw new ServiceException(4102);
        }
        return ipSetting;
    }
}
