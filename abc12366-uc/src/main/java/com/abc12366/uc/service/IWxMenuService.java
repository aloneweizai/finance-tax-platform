package com.abc12366.uc.service;

import com.abc12366.uc.model.weixin.BaseWxRespon;
import com.abc12366.uc.model.weixin.bo.menu.Button;
import com.abc12366.uc.model.weixin.bo.menu.WxMenu;

public interface IWxMenuService {
    
	/**
	 * 创建菜单
	 * @param wxMenu 菜单
	 * @return 
	 */
    public BaseWxRespon creatWxMenu(WxMenu wxMenu);
    /**
     * 获取菜单
     * @return
     */
    public WxMenu getWxMenu();
    /**
     * 删除菜单
     * @return
     */
    public BaseWxRespon delWxMenu();
    /**
     * 数据库获取菜单
     * @return
     */
    public WxMenu getWxMenuDb();
    /**
     * 数据库获取单个菜单
     * @param id
     * @return
     */
    public Button selectOne(String id);
    /**
     * 插入单个菜单
     * @param button 菜单
     * @return
     */
    public Button insert(Button button);
    /**
     * 更新单个菜单
     * @param button 菜单
     * @return
     */
    public Button update(Button button);
    /**
     * 删除单个菜单
     * @param id 菜单id
     */
    public void delete(String id);
    /**
     * 数据库获取菜单
     * @return
     */
	public WxMenu getWxMenuDbShow();
}
