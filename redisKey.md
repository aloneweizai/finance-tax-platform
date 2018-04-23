### UC后台存储redis的key命名规范：唯一id+下划线+内容英文

* 用户经验值信息(/experience/{userId})：userId + '_MyExperience'
* 电子申报绑定列表(/bind/dzsbs/{userId})：userId + '_DzsbList'
* 电子税局绑定列表(/bind/hngss/{userId})：userId + '_HngsList'
* 湖南地税绑定列表(/bind/hndss/{userId})：userId + '_HndsList'
* 字典管理-根据dictId查找(/dict/kv/{dictId})：dictId + '_Dict'
* 分管理-根据用户ID查询用户积分情(/points/{userId})：userId + '_Points'
* 地区管理-根据省ID查市(/city/{provinceId})：provinceId + "_City"
* 地区管理-根据市ID查区(/area/{cityId})：cityId + "_Area"
* 获取用户累计签到天数(/check/total)：userId + '_Check'
* 查看用户本月任务概况(/task/mytask/survey/{userId})：userId + '_Tasks'
* 根据等级编码查看会员等级(/uvip/level/bo/{levelCode})：levelCode + "_VipLevel"
* 个人消息(/user/msg/unread)：userId + '_UserMsgUnread'
* 业务消息服务(/business/unread)：userId + '_BusinessMsgUnread'
* 广告轮播图(/adpages)：CMS_AdpageListFqt
* 财税资讯列表(/content/selectListcszxwForqt)：CMS_SelectListcszxwFqt
* 通知公告(/noticesForqt)：CMS_NoticeListFqt
* 热点问题(/knowledgeBase/hotUnClassifyList)：Bangb_HotUnClassifyList
* 帮友热议(/questionbb/selectListryForqt)：CMS_SelectListryForqt
* 活动中心(/event/topone/{category})：category + "_ToponeForqt"
* 知识库分类(/knowledgeCategory/listAll)：Bangb_KnowledgeCategoryList
* 财税资讯类型(/contentType)：Cms_ContentTypeList
* 财税网首页 最新问题、知识(/knowledgeBase/nearestList)：Bangb_NearestLists
* 问题受理 统计查询(缓存)(/accepted/statiss)：Bangb_Accepted