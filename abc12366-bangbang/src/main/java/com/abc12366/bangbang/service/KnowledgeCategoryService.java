package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.KnowledgeCategory;
import com.abc12366.bangbang.model.bo.SortBO;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/2 20:59
 * 知识库分类 CRUD
 */
public interface KnowledgeCategoryService {


    List<KnowledgeCategory> listAll();


    KnowledgeCategory add(KnowledgeCategory knowledgeCategory);

    /* 修改分类 */
    KnowledgeCategory modify(KnowledgeCategory knowledgeCategory);

    /* 修改分类名称 */
    void modifyNameById(String id, String name);

    /* 修改分类排序 */
    void modifySort(List<SortBO> list);

    /* 删除分类 */
    void deleteById(String id);
}
