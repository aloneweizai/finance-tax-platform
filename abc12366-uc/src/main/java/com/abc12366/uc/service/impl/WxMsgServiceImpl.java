package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db1.WxMsgMapper;
import com.abc12366.uc.mapper.db2.UserRoMapper;
import com.abc12366.uc.mapper.db2.WxGzhRoMapper;
import com.abc12366.uc.mapper.db2.WxMsgRoMapper;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.UserBO;
import com.abc12366.uc.model.weixin.bo.message.*;
import com.abc12366.uc.model.weixin.bo.person.WxPerson;
import com.abc12366.uc.model.weixin.bo.template.ImgMaterial;
import com.abc12366.uc.service.IWxMsgService;
import com.abc12366.uc.service.TodoTaskService;
import com.abc12366.uc.util.wx.MsgMap;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxConnectFactory;
import com.abc12366.uc.util.wx.WxGzhClient;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author zhushuai 2017-7-27
 */
@Service
public class WxMsgServiceImpl implements IWxMsgService {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(WxMsgServiceImpl.class);
    @Autowired
    private WxMsgMapper msgMapper;

    @Autowired
    private WxMsgRoMapper msgRoMapper;
    
    @Autowired
    private WxGzhRoMapper gzhRoMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private TodoTaskService todoTaskService;
    
    @Autowired
    private UserRoMapper userRoMapper;

    @Override
    public ImgMaterial uploadWxImag(FileContent fileContent) {
        Map<String, String> tks = new HashMap<String, String>();
        tks.put("access_token", WxGzhClient.getInstanceToken());
        return WxConnectFactory.postFile(WechatUrl.MATERIAL_NEWSIMG, tks, null,
                ImgMaterial.class, fileContent);
    }

    public String exec(HttpServletRequest request) {
        try {
            Map<String, String> map = parseXml(request);
            LOGGER.info("微信服务器推送信息:"+JSON.toJSONString(map));
            int msgCode = MsgMap.getMsgType(map.get("MsgType"));
            switch (msgCode) {
                case 0://文本
                    ReturnMsg keymsg = getReMsgOneBykeyString(map.get("Content"));
                    if (keymsg != null) {
                        return keymsg.toWxXml( map.get("FromUserName"),map.get("ToUserName"), System
                                .currentTimeMillis());
                    }
                case 1://图片消息
                case 2://语音
                case 3://视频
                case 4://小视频
                case 5://位置
                case 6://链接
                	//没查到关键字消息，转给多客服
                	ReturnMsg remsg=new ReturnMsg();
                	remsg.setMsgType("transfer_customer_service");
                	return remsg.toWxXml( map.get("FromUserName"),map.get("ToUserName"), System.currentTimeMillis());
                	
                   /* ReturnMsg remsg = getReMsgOneBysetting("1");
                    if (remsg != null) {
                        return remsg.toWxXml( map.get("FromUserName"),map.get("ToUserName"), System.currentTimeMillis
								());
                    }*/
                case 7:
                    int eventCode = MsgMap.getEventType(map.get("Event"));
                    switch (eventCode) {
                        case 0://关注
                        	/*if(map.get("Ticket")!=null){//扫公众号生成二维码关注
                        		String eventKey=map.get("EventKey");
                        		String[] infos=eventKey.split(",");
                        		LOGGER.info("用户扫码关注(EventKey):" +eventKey);
                        		if("qrscene_AA".equals(infos[0])){//用户关注以及自动绑定
                        			smbd(map,infos);
                        		}
                        	}else{*///其他渠道关注
                        	    //关注公众号，完成任务
		                       	UserBO userBO= userMapper.selectByopenid(map.get("FromUserName"));
		                       	if(userBO!=null){
		                       	     LOGGER.info("用户关注公众号，做任务，USERID:"+userBO.getId());
		                       		 todoTaskService.doTask(userBO.getId(), TaskConstant.SYS_TASK_GZCSZJGZH_CODE);
		                       	}
                        	
                        	
                        		ReturnMsg newmsg = getReMsgOneBysetting("0");
                                if (newmsg != null) {
                                    return newmsg.toWxXml( map.get("FromUserName"),map.get("ToUserName"), System
    										.currentTimeMillis());
                                }
                        	//}
                        	
                        	
                        	 
                        	
                        case 1://取消关注

                        case 2://微信已关注公众号并扫码
                        	String eventKey=map.get("EventKey");
                        	String[] infos=eventKey.split(",");
                        	if("qrscene_AA".equals(infos[0])){//用户已关注自动绑定
                    			smbd(map,infos);
                    		}
                        	break;
                        case 3:
                        case 4:
                            break;
                        case 5:
                            break;
                    }
                    return null;
            }
        } catch (Exception e) {
            LOGGER.error("解析微信消息失败:", e);
        }
        return null;
    }

    private ReturnMsg getReMsgOneBykeyString(String key) {
        try {
            //return msgRoMapper.getReMsgOneBykeyString(key);
        	List<ReturnMsg> msgs=msgRoMapper.getAllkeyString();
        	if(msgs!=null && msgs.size()>0){
        		for(ReturnMsg msg:msgs){
        			if("ALL".equals(msg.getSearchTp())){
        				String[] keywords=msg.getKeyString().split(",");
        				for(String keyword:keywords){
        					if(key.contains(keyword)) return msg;
        				}
        			}else if("PART".equals(msg.getSearchTp())){
        				String keyword=msg.getKeyString();
        				keyword=keyword.replaceAll("", "|");
        				keyword=(String) keyword.subSequence(1, keyword.length()-1);
        				Pattern p=Pattern.compile(keyword);
        			    Matcher m=p.matcher(key);
        			    if(m.find()) return msg;
        			}
        		}
        	}
        	return null;
        } catch (Exception e) {
            LOGGER.error("查询单个关键字类型信息失败：{}", e);
            return null;
        }
    }
    
    private void smbd(Map<String,String> map,String[] infos){
    	Map<String, String> tks1 = new HashMap<String, String>();
        tks1.put("access_token", WxGzhClient.getInstanceToken());
        tks1.put("openid", map.get("FromUserName"));
        WxPerson person = WxConnectFactory.get(WechatUrl.WXUSEINFO, tks1, null, WxPerson.class);
        if (0 != person.getErrcode()) {
            LOGGER.error("获取单个微信用户异常：{}", person.getErrmsg());
            throw new ServiceException(person.getErrcode());
        } else {
        	User user=new User();
        	user.setBdqudao(infos[1]);
        	user.setId(infos[2]);
        	user.setWxopenid(person.getOpenid());
        	user.setWxheadimg(person.getHeadimgurl());
        	user.setWxnickname(person.getNickname());
        	int result = userMapper.update(user);
            if (result != 1) {
                LOGGER.warn("绑定用户失败!");
                throw new ServiceException(4102);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> parseXml(HttpServletRequest request)
            throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        inputStream = null;

        return map;
    }

    @Override
    public WxNews add_news(WxNews news) {
        Map<String, String> tks = new HashMap<String, String>();
        tks.put("access_token", WxGzhClient.getInstanceToken());
        return WxConnectFactory.post(WechatUrl.MATERIAL_ADDNEWS, tks, news, WxNews.class);
    }

    @Override
    public ImgMaterial add_img(FileContent fileContent) {
        Map<String, String> tks = new HashMap<String, String>();
        tks.put("access_token", WxGzhClient.getInstanceToken());
        tks.put("type", "image");
        return WxConnectFactory.postFile(WechatUrl.MATERIAL_ADDMATE, tks, null,
                ImgMaterial.class, fileContent);
    }


    @Transactional("db1TxManager")
    public News insertNews(News news) {
        Timestamp now = new Timestamp(new Date().getTime());
        String msgId = Utils.uuid();
        news.setId(msgId);
        news.setCreatDate(now);
        msgMapper.insertNews(news);

        for (Article article : news.getArticles()) {
            article.setNewsId(msgId);
            msgMapper.insertArticle(article);
        }
        return news;
    }


    @Transactional("db1TxManager")
    public News updateNews(News news) {
        Timestamp now = new Timestamp(new Date().getTime());
        news.setLastUpdate(now);
        int update = msgMapper.updateNews(news);
        if (update != 1) {
            LOGGER.info("{修改图文消息失败}", update);
            throw new ServiceException(4102);
        } else {
            msgMapper.deleteArticle(news.getId());
            for (Article article : news.getArticles()) {
                article.setNewsId(news.getId());
                msgMapper.insertArticle(article);
            }
        }
        return news;
    }

    @Override
    public List<News> getWxnews(News news) {
        return msgRoMapper.getNews(news);
    }


    @Transactional("db1TxManager")
    public ReturnMsg insertReNews(ReturnMsg returnMsg) {
        if (!"2".equals(returnMsg.getSetting())) {
            ReturnMsg newmsg = msgRoMapper.getReMsgOneBysetting(returnMsg.getSetting());
            if (newmsg != null) {
                LOGGER.info("{修改自动回复消息失败:被添加回复或者自动回复只能存在一条记录}", returnMsg.getSetting());
                throw new ServiceException(4101);
            }
        }
        Timestamp now = new Timestamp(new Date().getTime());
        String msgId = Utils.uuid();
        returnMsg.setId(msgId);
        returnMsg.setCreateDate(now);

        ReturnMsg newMsg  = new ReturnMsg();
        newMsg.setSetting(returnMsg.getSetting());
        newMsg.setKeyString(returnMsg.getKeyString());
        List<ReturnMsg>  list = msgRoMapper.selectList(newMsg);
        if(list.size()>0){
            LOGGER.info("名称不能重复");
            throw new ServiceException(9999,"名称不能重复");
        }
        msgMapper.insertRemsg(returnMsg);
        return returnMsg;
    }


    @Transactional("db1TxManager")
    public void deleteNews(String id) {
        msgMapper.deleteArticle(id);
        msgMapper.deleteNews(id);
    }


    @Transactional("db1TxManager")
    public ReturnMsg updateReMsg(ReturnMsg returnMsg) {
        Timestamp now = new Timestamp(new Date().getTime());
        returnMsg.setLastUpdate(now);
        int update = msgMapper.updateRemsg(returnMsg);
        if (update != 1) {
            LOGGER.info("{修改自动回复消息失败}", update);
            throw new ServiceException(4421);
        }
        return returnMsg;
    }

    @Override
    public ReturnMsg getReMsgOneBysetting(String setting) {
        ReturnMsg newmsg = new ReturnMsg();
        try {
            LOGGER.info("查询单个类型自动回复信息:{}", setting);
            newmsg = msgRoMapper.getReMsgOneBysetting(setting);
        } catch (Exception e) {
            LOGGER.error("查询单个类型自动回复信息：{}", e);
            throw new ServiceException(4234);
        }
        return newmsg;
    }

    @Override
    public List<ReturnMsg> selectkeyList(ReturnMsg returnMsg, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<ReturnMsg> returnMsgs = msgRoMapper.selectList(returnMsg);
        return returnMsgs;
    }


    @Transactional("db1TxManager")
    public void deleteWxremsg(String id) {
        ReturnMsg newmsg = selectOneWxremsg(id);
        if (newmsg != null) {
            msgMapper.deleteWxremsg(id);
        } else {
            throw new ServiceException(4012);
        }
    }

    @Override
    public ReturnMsg selectOneWxremsg(String id) {
        ReturnMsg newmsg = new ReturnMsg();
        try {
            newmsg = msgRoMapper.selectOneWxremsg(id);
        } catch (Exception e) {
            LOGGER.error("查询单个自动回复信息信息异常：{}", e);
            throw new ServiceException(4102);
        }
        return newmsg;
    }

    @Override
    public News selectOne(String id) {
        News info = new News();
        try {
            LOGGER.info("查询单个模板消息:{}", id);
            info = msgRoMapper.selectOne(id);
        } catch (Exception e) {
            LOGGER.error("查询单个模板消息异常：{}", e);
            throw new ServiceException(4104);
        }
        return info;
    }

	
}
