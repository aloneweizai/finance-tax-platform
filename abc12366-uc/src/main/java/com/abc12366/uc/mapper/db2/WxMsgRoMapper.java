package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.weixin.bo.message.News;
import com.abc12366.uc.model.weixin.bo.message.ReturnMsg;

import java.util.List;

public interface WxMsgRoMapper {

    List<News> getNews(News news);

    ReturnMsg getReMsgOneBysetting(String setting);

    List<ReturnMsg> selectList(ReturnMsg returnMsg);

    ReturnMsg selectOneWxremsg(String id);

    News selectOne(String id);

	ReturnMsg getReMsgOneBykeyString(String key);

	/**
	 * 获取所有关键字回复信息
	 * @return
	 */
	List<ReturnMsg> getAllkeyString();

}
