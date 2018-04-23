package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.bo.QuestionFactionRewardSettingBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionRewardSettingParamBo;

import java.util.List;

/**
 * 
 * QuestionFactionRewardSettingMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionRewardSettingRoMapper {

	/**
	 *
	 * 查询 帮派奖励列表
	 *
	 **/
	List<QuestionFactionRewardSettingBo> selectFactionRewardSettingList(QuestionFactionRewardSettingParamBo param);


}