package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.event.EventApplyBbBo;
import com.abc12366.cms.model.event.EventApplyCentenBo;
import com.abc12366.cms.model.event.EventRecordBbBo;

/**
 * Created by stuy on 2017/9/14.
 */
public interface EventBangBangUpdateMapper {

    int saveEventRecord(EventRecordBbBo eventRecordBbBo);

    int saveEventApply(EventApplyBbBo eventApplyBbBo);

    int saveEventApplyAttr(EventApplyCentenBo eventApplyCentenBo);
}
