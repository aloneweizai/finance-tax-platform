package com.abc12366.uc.service.admin;

import com.abc12366.uc.model.Dict;
import com.abc12366.uc.model.bo.DictBO;
import com.abc12366.uc.model.bo.DictUpdateBO;

import java.util.List;

/**
 * 数据字典服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-24 3:09 PM
 * @since 1.0.0
 */
public interface DictService {

    /**
     * 字典列表查询
     *
     * @param dict {@linkplain com.abc12366.uc.model.Dict Dict}
     * @return List<Dict>
     */
    List<Dict> selectList(Dict dict);

    /**
     * 查看单个字典实体
     *
     * @param dict {@linkplain com.abc12366.uc.model.Dict Dict}
     * @return DictBO {@linkplain com.abc12366.uc.model.bo.DictBO DictBO}
     */
    DictBO selectOne(Dict dict);

    /**
     * 查找字典第一级列表
     *
     * @return List<DictBO> {@linkplain com.abc12366.uc.model.bo.DictBO DictBO}
     */
    List<DictBO> selectFirstLevel();

    /**
     * 新增字典
     *
     * @param dictBO DictBO
     * @return DictBO
     * @see com.abc12366.uc.model.bo.DictBO
     */
    DictBO insert(DictBO dictBO);

    /**
     * 更新字典
     *
     * @param dictUpdateBO DictUpdateBO
     * @return DictBO
     * @see com.abc12366.uc.model.bo.DictUpdateBO
     * @see com.abc12366.uc.model.bo.DictBO
     */
    DictBO update(DictUpdateBO dictUpdateBO);

    /**
     * 根据ID删除字典
     *
     * @param id 字典主键
     * @return int 影响记录行数
     */
    int delete(String id);

    /**
     * 根据ID查询字典
     *
     * @param id 字典主键
     * @return Dict
     * @see com.abc12366.uc.model.Dict
     */
    Dict selectById(String id);

    /**
     * 查询字典列表
     *
     * @param dict dictId字典编码
     * @return List<DictBO>字典列表
     * @see com.abc12366.uc.model.bo.DictBO
     */
    List<DictBO> selectDictList(Dict dict);

    /**
     * 批量删除字典
     *
     * @param bo 字典主键字符串，逗号分隔ID
     */
    void batchDelete(Dict bo);

    /**
     * 根据字典名称查询字典
     *
     * @param dict 字典名称
     * @return List<Dict>
     * @see com.abc12366.uc.model.Dict
     */
    List<Dict> selectListByDictName(Dict dict);

    /**
     * 查询字典列表
     *
     * @param dictId dictId字典编码
     * @return List<Dict>
     * @see com.abc12366.uc.model.Dict
     */
    List<Dict> selectList(String dictId);

    /**
     * 根据字典名查找所有子节点
     * @param dictId 字典
     * @return
     */
    DictBO selectListByName(String dictId);
}
