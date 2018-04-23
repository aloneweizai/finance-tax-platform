package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.weixin.bo.menu.Button;

import java.util.List;


public interface WxMenuRoMapper {

    List<Button> seletSec(String parentId);

    List<Button> seletFisrt();

    Button selectOne(String id);

	List<Button> seletFisrtShow();

	List<Button> seletSecShow(String id);

}
