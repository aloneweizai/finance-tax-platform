package com.abc12366.uc.service.admin.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.MenuMapper;
import com.abc12366.uc.mapper.db2.MenuRoMapper;
import com.abc12366.uc.model.admin.Menu;
import com.abc12366.uc.model.admin.bo.MenuBO;
import com.abc12366.uc.model.admin.bo.MenuUpdateBO;
import com.abc12366.uc.service.admin.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhongwei
 * @date  2017-04-27 10:08 AM
 * @since 1.0.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    private static Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuRoMapper menuRoMapper;

    @Override
    public List<MenuBO> selectList(Menu menu) {
        return menuRoMapper.selectList(menu);
    }

    @Override
    public MenuBO selectByParentId(String parentId) {
        return recursiveTree(parentId);
    }

    /**
     * 递归算法解析成树形结构
     *
     * @param id id
     */
    public MenuBO recursiveTree(String id) {
        MenuBO node = menuRoMapper.selectByMenuId(id);
        List<MenuBO> treeNodes = menuRoMapper.selectByParentId(id);
        //遍历子节点
        for (MenuBO child : treeNodes) {
            MenuBO n = recursiveTree(child.getMenuId()); //递归
            node.getNodes().add(n);
        }
        return node;
    }

    @Override
    public MenuBO selectByMenuId(String menuId) {
        return menuRoMapper.selectByMenuId(menuId);
    }

    @Transactional("db1TxManager")
    @Override
    public MenuBO insert(MenuBO menuBO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuBO, menu);
        menu.setMenuId(Utils.uuid());
        menuMapper.insert(menu);
        BeanUtils.copyProperties(menu, menuBO);
        return menuBO;
    }

    @Transactional("db1TxManager")
    @Override
    public void delete(String menuId) {
        deleteTree(menuId);
    }

    /**
     * 递归算法删除树节点
     *
     * @param id id
     */
    public void deleteTree(String id) {
        List<MenuBO> treeNodes = menuRoMapper.selectByParentId(id);
        //遍历子节点
        for (MenuBO child : treeNodes) {
            deleteTree(child.getMenuId()); //递归
        }
        menuMapper.delete(id);
    }

    @Transactional("db1TxManager")
    @Override
    public MenuBO update(MenuBO menuBO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuBO, menu);
        int update = menuMapper.update(menu);
        if (update != 1) {
            LOGGER.warn("更新失败，参数：{}", menu.toString());
            throw new ServiceException(4002);
        }
        return menuBO;
    }

    @Override
    public List<MenuBO> selectFirstLevel() {
        List<Menu> menus = menuRoMapper.selectFirstLevel();
        if (menus == null || menus.size() == 0) {
            return null;
        }
        List<MenuBO> menuBOs = new ArrayList<>();
        for (Menu menu : menus) {
            MenuBO menuBO = new MenuBO();
            BeanUtils.copyProperties(menu, menuBO);
            menuBOs.add(menuBO);
        }
        return menuBOs;
    }

    @Transactional("db1TxManager")
    @Override
    public void enable(MenuUpdateBO updateBO) {
        String[] idArray = updateBO.getMenuId().split(",");
        Menu menu = new Menu();
        for (String menuId : idArray) {
            menu.setMenuId(menuId);
            menu.setStatus(updateBO.getStatus());
            int enable = menuMapper.update(menu);
            if (enable != 1) {
                LOGGER.warn("修改失败，id：{}", menu.toString());
                throw new ServiceException(4102);
            }
            //查找子节点
            List<MenuBO> boList = menuRoMapper.selectByParentId(menuId);
            for (MenuBO menuBO : boList) {
                menuBO.setStatus(updateBO.getStatus());
                Menu me = new Menu();
                BeanUtils.copyProperties(menuBO, me);
                int upd = menuMapper.update(me);
                if (upd != 1) {
                    LOGGER.warn("修改子节点状态失败，id：{}", me);
                    throw new ServiceException(4097);
                }
            }
        }
    }

    @Transactional("db1TxManager")
    @Override
    public void disableAll() {
        Menu menu = new Menu();
        List<MenuBO> menus = menuRoMapper.selectList(menu);
        for (MenuBO temp : menus) {
            menu.setMenuId(temp.getMenuId());
            menu.setStatus(false);
            int enable = menuMapper.update(menu);
            if (enable != 1) {
                LOGGER.warn("修改失败，id：{}", menu.toString());
                throw new ServiceException(4102);
            }
        }
    }

}
