package com.abc12366.uc.service.impl;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.QrcodeMapper;
import com.abc12366.uc.mapper.db2.QrcodeRoMapper;
import com.abc12366.uc.model.weixin.bo.qrcode.*;
import com.abc12366.uc.service.IWxQrcodeService;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxConnectFactory;
import com.abc12366.uc.util.wx.WxGzhClient;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-29 11:04 AM
 * @since 1.0.0
 */
@Service
public class WxQrcodeService implements IWxQrcodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxQrcodeService.class);

    @Autowired
    private QrcodeMapper qrcodeMapper;

    @Autowired
    private QrcodeRoMapper qrcodeRoMapper;

    @Override
    public List<Qrcode> selectList(Qrcode qrcode, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        qrcode.setStatus(true);
        return qrcodeRoMapper.selectList(qrcode);
    }

    @Override
    public Qrcode selectOne(String id) {
        return qrcodeRoMapper.selectOne(id);
    }

    @Override
    public Qrcode insert(Qrcode qrcode) throws UnsupportedEncodingException {
        qrcode.setId(Utils.uuid());
        qrcode.setStatus(true);
        Date now = new Date();
        qrcode.setCreateTime(now);
        qrcode.setLastUpdate(now);

        Map<String, String> tks = new HashMap<>();
        tks.put("access_token", WxGzhClient.getInstanceToken());

        Scene scene = new Scene();
        scene.setScene_str(qrcode.getId());

        Info action_info = new Info();
        action_info.setScene(scene);

        Action action = new Action();
        action.setAction_name("QR_LIMIT_STR_SCENE");
        action.setAction_info(action_info);

        TicketResp ticketResp = WxConnectFactory.post(WechatUrl.WXQRCODE_TICKET, tks, action, TicketResp.class);
        LOGGER.info("{}", ticketResp);
        if (ticketResp != null && !StringUtils.isEmpty(ticketResp.getTicket())) {
            qrcode.setTicket(ticketResp.getTicket());
            qrcode.setImage(Constant.WEIXIN_QRCODE + URLEncoder.encode(qrcode.getTicket(), "UTF-8"));
        }
        qrcodeMapper.insert(qrcode);
        return qrcode;
    }

    @Override
    public Qrcode update(Qrcode qrcode) throws UnsupportedEncodingException {
        Qrcode data = selectOne(qrcode.getId());
        if (data != null) {
            data.setName(qrcode.getName());
            data.setDescription(qrcode.getDescription());
            data.setType(qrcode.getType());
            data.setContent(qrcode.getContent());
            data.setLastUpdate(new Date());
            if (StringUtils.isEmpty(data.getTicket())) {
                Map<String, String> tks = new HashMap<>();
                tks.put("access_token", WxGzhClient.getInstanceToken());

                Scene scene = new Scene();
                scene.setScene_str(qrcode.getId());

                Info action_info = new Info();
                action_info.setScene(scene);

                Action action = new Action();
                action.setAction_name("QR_LIMIT_STR_SCENE");
                action.setAction_info(action_info);
                TicketResp ticketResp = WxConnectFactory.post(WechatUrl.WXQRCODE_TICKET, tks, action, TicketResp.class);
                LOGGER.info("{}", ticketResp);
                if (ticketResp != null && !StringUtils.isEmpty(ticketResp.getTicket())) {
                    data.setTicket(ticketResp.getTicket());
                    data.setImage(Constant.WEIXIN_QRCODE + URLEncoder.encode(data.getTicket(), "UTF-8"));
                }
            }
            qrcodeMapper.update(data);
        }
        return data;
    }

    @Override
    public void delete(String id) {
        Qrcode data = selectOne(id);
        data.setStatus(false);
        data.setLastUpdate(new Date());
        qrcodeMapper.update(data);
    }

	@Override
	public String getWxQrcode(String codeStr) throws UnsupportedEncodingException {
		    Map<String, String> tks = new HashMap<>();
	        tks.put("access_token", WxGzhClient.getInstanceToken());
		    Scene scene = new Scene();
	        scene.setScene_str(codeStr);

	        Info action_info = new Info();
	        action_info.setScene(scene);

	        Action action = new Action();
	        action.setAction_name("QR_STR_SCENE");
	        action.setAction_info(action_info);
	        action.setExpire_seconds(300000);

	        TicketResp ticketResp = WxConnectFactory.post(WechatUrl.WXQRCODE_TICKET, tks, action, TicketResp.class);
	        if (ticketResp != null && !StringUtils.isEmpty(ticketResp.getTicket())) {
                return Constant.WEIXIN_QRCODE + URLEncoder.encode(ticketResp.getTicket(), "UTF-8");
            }else{
            	return null;
            }
	}
}
