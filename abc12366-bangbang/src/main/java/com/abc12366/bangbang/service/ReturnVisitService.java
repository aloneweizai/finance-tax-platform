package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.ReturnVisit;
import com.abc12366.bangbang.model.bo.ReturnVisitBO;

import java.util.List;

/**
 * @Author lizhongwei
 * @Date 2017/9/21 13:39
 */
public interface ReturnVisitService {

    ReturnVisit add(ReturnVisit returnVisit);

    List<ReturnVisit> selectList(ReturnVisitBO returnVisitBO);

    void delete(String id);

    List<ReturnVisitBO> selectStatisList(ReturnVisitBO param);
}
