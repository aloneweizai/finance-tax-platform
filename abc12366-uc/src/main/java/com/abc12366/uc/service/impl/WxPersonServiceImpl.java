package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.uc.mapper.db1.WxPersonMapper;
import com.abc12366.uc.mapper.db2.WxGzhRoMapper;
import com.abc12366.uc.mapper.db2.WxPersonRoMapper;
import com.abc12366.uc.model.weixin.OpenIdRs;
import com.abc12366.uc.model.weixin.bo.person.WxPerson;
import com.abc12366.uc.service.IWxPersonService;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxConnectFactory;
import com.abc12366.uc.util.wx.WxGzhClient;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxPersonServiceImpl implements IWxPersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxPersonServiceImpl.class);

    private final Integer PERCOUNT = 10000;

    private boolean isSynchroing = false;
    @Autowired
    private WxPersonMapper personMapper;
    @Autowired
    private WxPersonRoMapper personRoMapper;
    @Autowired
    private WxGzhRoMapper gzhRoMapper;

    public boolean startUsersynchro() {
        if (!isSynchroing) {
            Thread thread = new Thread(new Usersynchro());
            thread.start();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<WxPerson> selectList(WxPerson person, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<WxPerson> persons = personRoMapper.selectList(person);
        return persons;
    }

    @Override
    public WxPerson selectOne(String openid) {
        WxPerson person = new WxPerson();
        try {
            LOGGER.info("查询单个微信用户信息:{}", openid);
            person = personRoMapper.selectOne(openid);
        } catch (Exception e) {
            LOGGER.error("查询单个微信用户异常：{}", e);
            throw new ServiceException(4234);
        }
        return person;
    }

    @Transactional("db1TxManager")
    public WxPerson synchroOne(String openid) {
        Map<String, String> tks1 = new HashMap<String, String>();
        tks1.put("access_token", WxGzhClient.getInstanceToken());
        tks1.put("openid", openid);
        WxPerson person = WxConnectFactory.get(WechatUrl.WXUSEINFO, tks1, null, WxPerson.class);
        person.setLastupdate(new Timestamp(new Date().getTime()));
        if (0 != person.getErrcode()) {
            LOGGER.error("同步单个微信用户异常：{}", person.getErrmsg());
            throw new ServiceException(person.getErrcode());
        } else {
            personMapper.updatePerson(person);
        }
        return person;
    }

    @Transactional("db1TxManager")
    private class Usersynchro implements Runnable {

        @Override
        public void run() {
            LOGGER.info("开始同步微信用户数据.......");
            isSynchroing = true;
            boolean isFirst = personRoMapper.countPersonNum(new WxPerson()) > 0 ? false : true;
            Map<String, String> headparamters = new HashMap<String, String>();
            while (true) {
                headparamters.put("access_token",  WxGzhClient.getInstanceToken());
                headparamters.put("next_openid", "");
                OpenIdRs listRs = WxConnectFactory.get(WechatUrl.WXUSELIST, headparamters, null, OpenIdRs.class);
                String[] ids = listRs.getData().getOpenid();
                for (int i = 0; i < ids.length; i++) {
                    Map<String, String> tks1 = new HashMap<String, String>();
                    tks1.put("access_token",  WxGzhClient.getInstanceToken());
                    tks1.put("openid", ids[i]);
                    WxPerson person = WxConnectFactory.get(WechatUrl.WXUSEINFO, tks1, null, WxPerson.class);
                    if (0 == person.getErrcode() && (isFirst ||
                            (!isFirst && personRoMapper.countPersonNum(person) == 0))) {
                        person.setLastupdate(new Timestamp(new Date().getTime()));
                        personMapper.savePerson(person);
                    } else {
                        LOGGER.error("获取微信用户({})数据异常.......", ids[i]);
                    }
                }
                headparamters.put("next_openid", listRs.getNext_openid());
                if (listRs.getCount() < PERCOUNT) {
                    break;
                }
            }
            LOGGER.info("同步微信用户数据处理完成.......");
            isSynchroing = false;
        }

    }

	@Override
	public WxPerson getOneFromWx(String openid) {
		Map<String, String> tks1 = new HashMap<String, String>();
        tks1.put("access_token", WxGzhClient.getInstanceToken());
        tks1.put("openid", openid);
        WxPerson person = WxConnectFactory.get(WechatUrl.WXUSEINFO, tks1, null, WxPerson.class);
		return person;
	}

}
