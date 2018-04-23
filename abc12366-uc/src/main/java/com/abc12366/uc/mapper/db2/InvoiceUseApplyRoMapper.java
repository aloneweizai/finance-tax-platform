package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.invoice.InvoiceUseApply;
import com.abc12366.uc.model.invoice.bo.InvoiceUseApplyBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * InvoiceUseApplyMapper数据库操作接口类
 **/

public interface InvoiceUseApplyRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    InvoiceUseApply selectByPrimaryKey(@Param("id") String id);

    List<InvoiceUseApplyBO> selectList(InvoiceUseApplyBO applyBO);

    InvoiceUseApplyBO selectInvoiceUseApply(@Param("id") String id);

    InvoiceUseApplyBO selectInvoiceRepoNum(String code);

    /**
     * 【待审核】发票领用数
     *
     * @return Integer
     */
    Integer selectTodoListCount();
}