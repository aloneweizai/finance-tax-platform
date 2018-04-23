package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.curriculum.bo.CurriculumOrderBo;

import java.util.List;
import java.util.Map;

public interface CurrOrderService {

    List<CurriculumOrderBo> selectList(Map<String, Object> map);

    CurriculumOrderBo save(CurriculumOrderBo curriculumOrderBo);

    CurriculumOrderBo selectOrder(String orderId);

    CurriculumOrderBo update(CurriculumOrderBo curriculumOrderBo);

    String updateStatus(String orderId, String status);

    String delete(String orderId);

}
