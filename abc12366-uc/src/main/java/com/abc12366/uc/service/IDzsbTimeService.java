package com.abc12366.uc.service;

import com.abc12366.uc.model.job.DzsbTime;

/**
 * 电子申报信息查询录入时间
 * @author zhushuai 2017-11-9
 *
 */
public interface IDzsbTimeService {
	/**
	 * 新增电子申报信息录入时间
	 * @param dzsbTime 电子申报信息录入时间
	 */
     public void insert(DzsbTime dzsbTime);
     /**
      * 修改电子申报信息录入时间
      * @param dzsbTime 电子申报信息录入时间
      */
     public void update(DzsbTime dzsbTime);
     
     /**
      * 查询电子申报信息录入时间
      * @param ywlx 业务类型
      * @return
      */
     public DzsbTime select(String ywlx);
}
