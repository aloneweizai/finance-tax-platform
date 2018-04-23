package com.abc12366.uc.service;

import com.abc12366.uc.model.dzfp.Einvocie;

import java.util.List;

public interface IDzfpService {
	/**
	 * 获取单个电子发票信息
	 * @param FPQQLSH 请求流水号
	 * @return
	 */
     public Einvocie selectOne(String FPQQLSH);
     /**
      * 获取电子发票信息列表
      * @param einvocie 电子发票信息
      * @return
      */
     public List<Einvocie> selectList(Einvocie einvocie);
     /**
      * 插入电子发票信息
      * @param einvocie 电子发票信息
      */ 
     public void insert(Einvocie einvocie);
     /**
      * 更新电子发票信息
      * @param einvocie 电子发票信息
      * @return
      */
     public int update(Einvocie einvocie);
}
