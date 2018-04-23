package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.*;
import com.abc12366.bangbang.mapper.db2.*;
import com.abc12366.bangbang.model.MessageSendBo;
import com.abc12366.bangbang.model.curriculum.*;
import com.abc12366.bangbang.model.curriculum.bo.*;
import com.abc12366.bangbang.service.CurriculumBrowserWatchService;
import com.abc12366.bangbang.service.CurriculumService;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.Utils;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.abc12366.bangbang.service.FollowLecturerService;
import com.abc12366.bangbang.service.MessageSendUtil;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xieyanmao on 2017/8/10.
 */
@Service
public class CurriculumServiceImpl implements CurriculumService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurriculumServiceImpl.class);

	@Autowired
	private CurriculumMapper curriculumMapper;

	@Autowired
	private CurriculumRoMapper curriculumRoMapper;

	@Autowired
	private CurriculumLabelMapper curriculumLabelMapper;

	@Autowired
	private CurriculumMembergradeMapper curriculumMembergradeMapper;

	@Autowired
	private CurriculumLecturerGxMapper curriculumLecturerGxMapper;

	@Autowired
	private CurriculumLecturerRoMapper lecturerRoMapper;

	@Autowired
	private CurriculumLabelRoMapper curriculumLabelRoMapper;

	@Autowired
	private CurriculumMembergradeRoMapper curriculumMembergradeRoMapper;

	@Autowired
	private CurriculumLecturerGxRoMapper curriculumLecturerGxRoMapper;

	@Autowired
	private CurriculumChapterMapper chapterMapper;

	@Autowired
	private CurriculumChapterRoMapper chapterRoMapper;

	@Autowired
	private CurriculumCoursewareMapper coursewareMapper;

	@Autowired
	private CurriculumCoursewareRoMapper coursewareRoMapper;

	@Autowired
	private CurriculumUvipPriceMapper uvipPriceMapper;

	@Autowired
	private CurriculumGiftMapper curriculumGiftMapper;

	@Autowired
	private CurriculumGiftRoMapper curriculumGiftRoMapper;

	@Autowired
	private CurriculumUvipPriceRoMapper uvipPriceRoMapper;

	@Autowired
	private CurriculumBrowserWatchService curriculumBrowserWatchServiceImpl;

	@Autowired
	private FollowLecturerService followLecturerService;

	@Autowired
	private MessageSendUtil messageSendUtil;

	@Override
	public List<CurriculumListBo> selectList(Map<String, Object> map) {
		List<CurriculumListBo> curriculumListBoList;
		try {
			// 查询课程列表
			curriculumListBoList = curriculumRoMapper.selectList(map);
		} catch (Exception e) {
			LOGGER.error("查询课程列表信息异常：{}", e);
			throw new ServiceException(4320);
		}
		return curriculumListBoList;
	}

	@Override
	public List<CurriculumListsyBo> selectListNew(Map<String, Object> map) {
		List<CurriculumListsyBo> curriculumListBoList;
		try {
			// 查询最新课程列表
			curriculumListBoList = curriculumRoMapper.selectListNew(map);
		} catch (Exception e) {
			LOGGER.error("查询课程列表信息异常：{}", e);
			throw new ServiceException(4320);
		}
		return curriculumListBoList;
	}

	@Override
	public List<CurriculumListsyBo> selectListVIP(Map<String, Object> map) {
		List<CurriculumListsyBo> curriculumListBoList;
		try {
			// 查询会员专享课程列表
			curriculumListBoList = curriculumRoMapper.selectListVIP(map);
		} catch (Exception e) {
			LOGGER.error("查询课程列表信息异常：{}", e);
			throw new ServiceException(4320);
		}
		return curriculumListBoList;
	}

	@Override
	public List<CurriculumListsyBo> selectListWatch(Map<String, Object> map) {
		List<CurriculumListsyBo> curriculumListBoList;
		try {
			// 查询最热课程列表
			curriculumListBoList = curriculumRoMapper.selectListWatch(map);
		} catch (Exception e) {
			LOGGER.error("查询课程列表信息异常：{}", e);
			throw new ServiceException(4320);
		}
		return curriculumListBoList;
	}

	@Override
	public List<CurriculumListsyBo> selectRecommend(Map<String, Object> map) {
		List<CurriculumListsyBo> curriculumListBoList;
		try {
			// 查询推荐课程
			curriculumListBoList = curriculumRoMapper.selectRecommend(map);
		} catch (Exception e) {
			LOGGER.error("查询课程列表信息异常：{}", e);
			throw new ServiceException(4320);
		}
		return curriculumListBoList;
	}

	@Override
	public CurriculumSituationBo selectSituation(String curriculumId) {
		CurriculumSituationBo curriculumSituationBo;
		try {
			// 查询课程授课信息
			curriculumSituationBo = curriculumRoMapper.selectSituation(curriculumId);
		} catch (Exception e) {
			LOGGER.error("查询课程授课信息异常：{}", e);
			throw new ServiceException(4321);
		}
		return curriculumSituationBo;
	}

	@Override
	public List<CurriculumLabelBo> selectLabelList() {
		List<CurriculumLabelBo> CurriculumLabelBoList;
		try {
			// 查询课程标签列表
			CurriculumLabelBoList = curriculumLabelRoMapper.selectLabelList();
		} catch (Exception e) {
			LOGGER.error("查询课程标签信息异常：{}", e);
			throw new ServiceException(5000);
		}
		return CurriculumLabelBoList;
	}

	@Override
	public int selectCurrCntByGoodsId(String goodsId) {
		int cnt = 0;
		try {
			// 查询课程
			cnt = curriculumRoMapper.selectCurrCntByGoodsId(goodsId);
		} catch (Exception e) {
			LOGGER.error("查询课程信息异常：{}", e);
			throw new ServiceException(4321);
		}
		return cnt;
	}

	@Override
	public CurriculumBo save(CurriculumBo curriculumBo) {
		String goodsId = curriculumBo.getGoodsId();
		// if(goodsId == null){
		// goodsId = "";
		// }
		// if(1 == curriculumBo.getIsFree()){
		// curriculumBo.setGoodsId("");
		// }else{
		// int cnt = curriculumRoMapper.selectCurrCntByGoodsId(goodsId);
		// if(cnt>0){
		// //该商品已被课程使用，请重新选择商品
		// throw new ServiceException(4326);
		// }
		// }

		Map<String, Object> dataMap1 = new HashMap<>();
		dataMap1.put("title", curriculumBo.getTitle());
		int cnt1 = curriculumRoMapper.selectCurriculumCnt(dataMap1);
		if (cnt1 > 0) {
			throw new ServiceException(4329);
		}

		try {
			JSONObject jsonStu = JSONObject.fromObject(curriculumBo);
			LOGGER.info("新增课程信息:{}", jsonStu.toString());
			if (1 == curriculumBo.getStatus()) {
				curriculumBo.setIssueTime(new Date());
			}
			curriculumBo.setCreaterTime(new Date());
			curriculumBo.setUpdateTime(new Date());
			// 保存课程信息
			String curriculumId = UUID.randomUUID().toString().replace("-", "");

			String userId = Utils.getAdminId();

			Curriculum curriculum = new Curriculum();
			curriculumBo.setCurriculumId(curriculumId);
			curriculumBo.setCreaterId(userId);
			BeanUtils.copyProperties(curriculumBo, curriculum);
			curriculumMapper.insert(curriculum);

			List<CurriculumLabel> labelList = curriculumBo.getLabelList();

			if (labelList != null) {
				for (CurriculumLabel label : labelList) {
					label.setCurriculumId(curriculumId);
					curriculumLabelMapper.insert(label);
				}
			}

			List<CurriculumMembergrade> membergradeList = curriculumBo.getMembergradeList();

			if (membergradeList != null) {
				for (CurriculumMembergrade grade : membergradeList) {
					grade.setCurriculumId(curriculumId);
					curriculumMembergradeMapper.insert(grade);
				}
			}

			List<CurriculumLecturerGx> lecturerGxList = curriculumBo.getLecturerGxList();

			if (lecturerGxList != null) {
				for (CurriculumLecturerGx lecturerGx : lecturerGxList) {
					lecturerGx.setCurriculumId(curriculumId);
					curriculumLecturerGxMapper.insert(lecturerGx);
				}
			}

			// 会员价格表
			List<CurriculumUvipPriceBo> uvipPriceList = curriculumBo.getUvipPriceBoList();

			if (uvipPriceList != null) {
				for (CurriculumUvipPriceBo uvipPriceBo : uvipPriceList) {
                    String vipId = Utils.uuid();
					uvipPriceBo.setId(vipId);
					uvipPriceBo.setCurriculumId(curriculumId);
					CurriculumUvipPrice uvipPrice = new CurriculumUvipPrice();
					BeanUtils.copyProperties(uvipPriceBo,uvipPrice);
					uvipPriceMapper.insert(uvipPrice);
                    List<CurriculumGiftBo> giftBoList = uvipPriceBo.getCurriculumGiftBoList();
                    if(giftBoList != null && giftBoList.size() > 0){
                        for(CurriculumGiftBo giftBo:giftBoList){
                            giftBo.setId(Utils.uuid());
                            giftBo.setGiftId(vipId);
                            CurriculumGift gift = new CurriculumGift();
                            BeanUtils.copyProperties(giftBo,gift);
                            curriculumGiftMapper.insert(gift);
                        }
                    }
				}
			}

		} catch (Exception e) {
			LOGGER.error("新增课程信息异常：{}", e);
			throw new ServiceException(4322);
		}

		return curriculumBo;
	}

	@Override
	public CurriculumBo selectCurriculum(String curriculumId) {
		CurriculumBo curriculumBo = new CurriculumBo();
		try {
			LOGGER.info("查询单个课程信息:{}", curriculumId);
			// 查询课程信息
			Curriculum curriculum = curriculumRoMapper.selectByPrimaryKey(curriculumId);

			BeanUtils.copyProperties(curriculum, curriculumBo);

			// 标签
			List<CurriculumLabel> curriculumLabelList = curriculumLabelRoMapper.selectList(curriculumId);

			curriculumBo.setLabelList(curriculumLabelList);

			// 会员等级
			List<CurriculumMembergrade> curriculumMembergradeList = curriculumMembergradeRoMapper
					.selectList(curriculumId);

			curriculumBo.setMembergradeList(curriculumMembergradeList);

			// 讲师
			List<CurriculumLecturerGx> curriculumLecturerGxList = curriculumLecturerGxRoMapper.selectList(curriculumId);

			curriculumBo.setLecturerGxList(curriculumLecturerGxList);

			Map<String, Object> dataMap1 = new HashMap<>();
			dataMap1.put("curriculumId", curriculumId);
			// 查询章节列表
			List<CurriculumChapterBo> chapterBoList = chapterRoMapper.selectList(dataMap1);

			for (CurriculumChapterBo chapterBo : chapterBoList) {
				String chapterId = chapterBo.getChapterId();
				Map<String, Object> dataMap2 = new HashMap<>();
				dataMap2.put("chapterId", chapterId);
				// 查询课件
				List<CurriculumCoursewareBo> coursewareBoList = coursewareRoMapper.selectList(dataMap2);
				chapterBo.setCoursewareList(coursewareBoList);
			}

			curriculumBo.setChapterBoList(chapterBoList);

			// 会员价格
			List<CurriculumUvipPriceBo> uvipPriceList = uvipPriceRoMapper.selectList(curriculumId);

			curriculumBo.setUvipPriceBoList(uvipPriceList);

		} catch (Exception e) {
			LOGGER.error("查询单个课程信息异常：{}", e);
			throw new ServiceException(4321);
		}
		return curriculumBo;
	}

	@Override
	public CurriculumsyBo selectCurriculumsy(String curriculumId) {
		CurriculumsyBo curriculumBo;
		try {
			LOGGER.info("查询单个课程信息:{}", curriculumId);
			// 查询课程信息
			curriculumBo = curriculumRoMapper.selectCurriculum(curriculumId);

			if (curriculumBo == null) {
				return curriculumBo;
			}

			// 标签
			List<CurriculumLabel> curriculumLabelList = curriculumLabelRoMapper.selectList(curriculumId);

			curriculumBo.setLabelList(curriculumLabelList);

			// 会员等级
			List<CurriculumMembergrade> curriculumMembergradeList = curriculumMembergradeRoMapper
					.selectList(curriculumId);

			curriculumBo.setMembergradeList(curriculumMembergradeList);

			// 讲师
			List<CurriculumLecturerBo> lecturerBoList = lecturerRoMapper.selectListByCurr(curriculumId);

			if (lecturerBoList != null) {
				for (CurriculumLecturerBo lecturer : lecturerBoList) {
					int cnt = lecturerRoMapper.selectStudentCnt(lecturer.getLecturerId());
					lecturer.setStudentNum(cnt);
				}
			}

			curriculumBo.setLecturerList(lecturerBoList);

			Map<String, Object> dataMap1 = new HashMap<>();
			dataMap1.put("curriculumId", curriculumId);
			// 查询章节列表
			List<CurriculumChapterBo> chapterBoList = chapterRoMapper.selectList(dataMap1);

			for (CurriculumChapterBo chapterBo : chapterBoList) {
				String chapterId = chapterBo.getChapterId();
				Map<String, Object> dataMap2 = new HashMap<>();
				dataMap2.put("chapterId", chapterId);
				// 查询课件
				List<CurriculumCoursewareBo> coursewareBoList = coursewareRoMapper.selectList(dataMap2);
				chapterBo.setCoursewareList(coursewareBoList);
			}

			curriculumBo.setChapterBoList(chapterBoList);

			// 会员价格
			List<CurriculumUvipPriceBo> uvipPriceList = uvipPriceRoMapper.selectList(curriculumId);

			curriculumBo.setUvipPriceBoList(uvipPriceList);

			List<CurriculumListsyBo> curriculumListBoList;
			// 查询相关课程列表
			curriculumListBoList = curriculumRoMapper.selectListxgNew(curriculumId);

			curriculumBo.setCurriculumListBoList(curriculumListBoList);

		} catch (Exception e) {
			LOGGER.error("查询单个课程信息异常：{}", e);
			throw new ServiceException(4321);
		}
		return curriculumBo;
	}

	@Override
	public CurriculumsyBo selectCurriculumsy2(String curriculumId) {
		CurriculumsyBo curriculumBo;
		try {
			LOGGER.info("查询单个课程信息:{}", curriculumId);
			// 查询课程信息
			curriculumBo = curriculumRoMapper.selectCurriculum2(curriculumId);

			if (curriculumBo == null) {
				return curriculumBo;
			}

			// 标签
			List<CurriculumLabel> curriculumLabelList = curriculumLabelRoMapper.selectList(curriculumId);

			curriculumBo.setLabelList(curriculumLabelList);

			// 会员等级
			List<CurriculumMembergrade> curriculumMembergradeList = curriculumMembergradeRoMapper
					.selectList(curriculumId);

			curriculumBo.setMembergradeList(curriculumMembergradeList);

			// 讲师
			List<CurriculumLecturerBo> lecturerBoList = lecturerRoMapper.selectListByCurr(curriculumId);

			if (lecturerBoList != null) {
				for (CurriculumLecturerBo lecturer : lecturerBoList) {
					int cnt = lecturerRoMapper.selectStudentCnt(lecturer.getLecturerId());
					lecturer.setStudentNum(cnt);
				}
			}

			curriculumBo.setLecturerList(lecturerBoList);

			// 会员价格
			List<CurriculumUvipPriceBo> uvipPriceList = uvipPriceRoMapper.selectList(curriculumId);

			curriculumBo.setUvipPriceBoList(uvipPriceList);

			Map<String, Object> dataMap1 = new HashMap<>();
			dataMap1.put("curriculumId", curriculumId);
			// 查询章节列表
			List<CurriculumChapterBo> chapterBoList = chapterRoMapper.selectList(dataMap1);

			for (CurriculumChapterBo chapterBo : chapterBoList) {
				String chapterId = chapterBo.getChapterId();
				Map<String, Object> dataMap2 = new HashMap<>();
				dataMap2.put("chapterId", chapterId);
				// 查询课件
				List<CurriculumCoursewareBo> coursewareBoList = coursewareRoMapper.selectList(dataMap2);
				chapterBo.setCoursewareList(coursewareBoList);
			}

			curriculumBo.setChapterBoList(chapterBoList);

			List<CurriculumListsyBo> curriculumListBoList;
			// 查询相关课程列表
			curriculumListBoList = curriculumRoMapper.selectListxgNew(curriculumId);

			curriculumBo.setCurriculumListBoList(curriculumListBoList);

		} catch (Exception e) {
			LOGGER.error("查询单个课程信息异常：{}", e);
			throw new ServiceException(4321);
		}
		return curriculumBo;
	}

	@Override
	public CurriculumEvaluateTjBo selectEvaluateTj(String curriculumId) {
		CurriculumEvaluateTjBo evaluateTjBo;
		try {
			LOGGER.info("查询单个课程评价统计信息:{}", curriculumId);
			// 查询课程评价统计信息
			evaluateTjBo = curriculumRoMapper.selectEvaluateTj(curriculumId);

		} catch (Exception e) {
			LOGGER.error("查询单个课程评价统计信息异常：{}", e);
			throw new ServiceException(4321);
		}
		return evaluateTjBo;
	}

	@Override
	public CurriculumBo update(CurriculumBo curriculumBo,HttpServletRequest request) {
		Map<String, Object> dataMap1 = new HashMap<>();
		dataMap1.put("curriculumId", curriculumBo.getCurriculumId());
		dataMap1.put("title", curriculumBo.getTitle());
		int cnt1 = curriculumRoMapper.selectCurriculumCnt(dataMap1);
		if (cnt1 > 0) {
			throw new ServiceException(4329);
		}
		String curriculumId = curriculumBo.getCurriculumId();
		// String goodsId = curriculumBo.getGoodsId();
		// if(goodsId == null){
		// goodsId = "";
		// }

		// 1为发布
		if (curriculumBo.getStatus() == 1) {
			curriculumBo.setIssueTime(new Date());
			int cnt = curriculumRoMapper.selectCoursewareCnt(curriculumId);
			if (cnt == 0) {
				// 该课程下没有添加课件，不能发布
				throw new ServiceException(4328);
			}
		} else {
			curriculumBo.setIssueTime(null);
		}

		// 查询课程信息
		Curriculum curriculum1 = curriculumRoMapper.selectByPrimaryKey(curriculumId);

		// if(curriculumBo.getStatus() == 0){
		// if(1 == curriculumBo.getIsFree()){
		// curriculumBo.setGoodsId("");
		// }else{
		// if(!goodsId.equals(curriculum1.getGoodsId())){
		// int cnt = curriculumRoMapper.selectCurrCntByGoodsId(goodsId);
		// if(cnt>0){
		// //该商品已被课程使用，请重新选择商品
		// throw new ServiceException(4326);
		// }
		// }
		// }
		// }else{
		// if(!"".equals(goodsId)){
		// if(!"".equals(curriculum1.getGoodsId()) &&
		// !goodsId.equals(curriculum1.getGoodsId())){
		// //商品不能修改
		// throw new ServiceException(4327);
		// }
		// }else{
		// curriculumBo.setGoodsId(curriculum1.getGoodsId());
		// }
		//
		// }

		// 更新模型信息
		Curriculum curriculum = new Curriculum();
		try {
			JSONObject jsonStu = JSONObject.fromObject(curriculumBo);
			LOGGER.info("更新课程信息:{}", jsonStu.toString());

			if (1 == curriculumBo.getStatus()) {
				curriculumBo.setIssueTime(new Date());
			}
			curriculumBo.setUpdateTime(new Date());
			BeanUtils.copyProperties(curriculumBo, curriculum);
			curriculumMapper.updateByPrimaryKeySelective(curriculum);

			List<CurriculumLabel> labelList = curriculumBo.getLabelList();

			curriculumLabelMapper.deleteByPrimaryKey(curriculumId);
			if (labelList != null) {
				for (CurriculumLabel label : labelList) {
					label.setCurriculumId(curriculumId);
					curriculumLabelMapper.insert(label);
				}
			}

			List<CurriculumMembergrade> membergradeList = curriculumBo.getMembergradeList();

			curriculumMembergradeMapper.deleteByPrimaryKey(curriculumId);
			if (membergradeList != null) {
				for (CurriculumMembergrade grade : membergradeList) {
					grade.setCurriculumId(curriculumId);
					curriculumMembergradeMapper.insert(grade);
				}
			}

			List<CurriculumLecturerGx> lecturerGxList = curriculumBo.getLecturerGxList();

			curriculumLecturerGxMapper.deleteByPrimaryKey(curriculumId);
			if (lecturerGxList != null) {
				for (CurriculumLecturerGx lecturerGx : lecturerGxList) {
					lecturerGx.setCurriculumId(curriculumId);
					curriculumLecturerGxMapper.insert(lecturerGx);
				}
			}


            //先查会员价格表
            List<CurriculumUvipPriceBo> oldUvipPriceList = uvipPriceRoMapper.selectList(curriculumId);
            //删除老的会员价格表
			uvipPriceMapper.deleteByPrimaryKey(curriculumId);
            //删除老的会员表价格对应的赠送权益信息
            for(CurriculumUvipPriceBo priceBo:oldUvipPriceList){
                curriculumGiftMapper.deleteByGiftId(priceBo.getId());
            }
//			if (uvipPriceList != null) {
//				for (CurriculumUvipPriceBo bo : uvipPriceList) {
//					bo.setId(UUID.randomUUID().toString().replace("-", ""));
//					bo.setCurriculumId(curriculumId);
//					CurriculumUvipPrice price = new CurriculumUvipPrice();
//					BeanUtils.copyProperties(bo,price);
//					uvipPriceMapper.insert(price);
//				}
//			}
            // 会员价格表
            List<CurriculumUvipPriceBo> uvipPriceList = curriculumBo.getUvipPriceBoList();
            if (uvipPriceList != null) {
                for (CurriculumUvipPriceBo uvipPriceBo : uvipPriceList) {
                    String vipId = Utils.uuid();
                    uvipPriceBo.setId(vipId);
                    uvipPriceBo.setCurriculumId(curriculumId);
                    CurriculumUvipPrice uvipPrice = new CurriculumUvipPrice();
                    BeanUtils.copyProperties(uvipPriceBo,uvipPrice);
                    uvipPriceMapper.insert(uvipPrice);
                    List<CurriculumGiftBo> giftBoList = uvipPriceBo.getCurriculumGiftBoList();
                    if(giftBoList != null && giftBoList.size() > 0){
                        for(CurriculumGiftBo giftBo:giftBoList){
                            giftBo.setId(Utils.uuid());
                            giftBo.setGiftId(vipId);
                            CurriculumGift gift = new CurriculumGift();
                            BeanUtils.copyProperties(giftBo,gift);
                            curriculumGiftMapper.insert(gift);
                        }
                    }
                }
            }


            if (curriculumBo.getStatus() == 1) {
				
				// String teacheId = curriculum1.getLecturerId();

				List<CurriculumLecturerBo> lecturerBoList = lecturerRoMapper.selectListByCurr(curriculumId);

				if (lecturerBoList != null && lecturerBoList.size() > 0) {
					for (CurriculumLecturerBo lecturerBo : lecturerBoList) {
						List<String> userIds = followLecturerService
								.selectUserIdListByLecturerId(lecturerBo.getUserId());
						if (userIds != null && userIds.size() > 0) {
							new Thread(new Runnable() {							
								public void run() {
									sendMsg(userIds, lecturerBo.getLecturerName(), curriculumId, request,
											curriculum.getTitle(),curriculum.getUpdateTime());
								}
							}).start();
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("更新课程信息异常：{}", e);
			throw new ServiceException(4323);
		}
		return curriculumBo;
	}

	@Override
	public String updateStatus(String curriculumId, String status, HttpServletRequest request) {
		// 更新模型信息

		LOGGER.info("更新课程状态信息:{}", curriculumId + "," + status);
		Curriculum curriculum = curriculumRoMapper.selectByPrimaryKey(curriculumId);
		curriculum.setCurriculumId(curriculumId);
		curriculum.setStatus(Integer.parseInt(status));
		curriculum.setUpdateTime(new Date());
		// 1为发布
		if ("1".equals(status)) {
			curriculum.setIssueTime(new Date());
			int cnt = curriculumRoMapper.selectCoursewareCnt(curriculumId);
			if (cnt == 0) {
				// 该课程下没有添加课件，不能发布
				throw new ServiceException(4328);
			}
		} else {
			curriculum.setIssueTime(null);
		}
		try {
			int num = curriculumMapper.updateStatus(curriculum);
			if (num > 0 && "1".equals(status)) {
				
				// String teacheId = curriculum1.getLecturerId();

				List<CurriculumLecturerBo> lecturerBoList = lecturerRoMapper.selectListByCurr(curriculumId);

				if (lecturerBoList != null && lecturerBoList.size() > 0) {
					for (CurriculumLecturerBo lecturerBo : lecturerBoList) {
						List<String> userIds = followLecturerService
								.selectUserIdListByLecturerId(lecturerBo.getUserId());
						if (userIds != null && userIds.size() > 0) {
							
							new Thread(new Runnable() {							
								public void run() {
									sendMsg(userIds, lecturerBo.getLecturerName(), curriculumId, request,
											curriculum.getTitle(),curriculum.getUpdateTime());
								}
							}).start();
							
							
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("更新课程信息异常：{}", e);
			throw new ServiceException(4323);
		}
		return "";
	}

	/**
	 * 异步消息发送
	 * 
	 * @param userIds
	 */
	public void sendMsg(List<String> userIds, String lecturerName, String curriculumId, HttpServletRequest request,
			String title,Date updateTime) {
		Map<String, String> map = new HashMap<>();
		map.put("first", "您关注的" + lecturerName + "讲师有新课程上线了:");
		map.put("remark", "请及时登录系统查看课程详情，感谢您的参与！");
		map.put("keyword1", "新课程上线通知");
		map.put("keyword2", DateUtils.dateToStr(updateTime));
		map.put("keyword3", "《" + title + "》");

		MessageSendBo messageSendBo = new MessageSendBo();
		messageSendBo.setType(MessageConstant.USER_MESSAGE);
		messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_CLASS);
		messageSendBo.setBusinessId(curriculumId);
		messageSendBo.setWebMsg("您关注的" + lecturerName + "讲师有新课程上线了，快来围观吧！");
		messageSendBo
				.setSkipUrl("<a target='view_window' href='" + SpringCtxHolder.getProperty("abc12366.qd.sns.url")
						+ "/school/details/" + curriculumId + "?page=1'>" + MessageConstant.VIEW_DETAILS + "</a>");
		messageSendBo.setPhoneMsg("您关注的" + lecturerName + "讲师有新课程上线了,请及时登录系统查看课程详情！");
		messageSendBo.setTemplateid("jfQJ8Oh_8KRs6t6KqFrOag5p89kgOUXKHo-Z6rmo3wM");
		messageSendBo.setDataList(map);

		messageSendBo.setUserIds(userIds);

		messageSendUtil.sendMsgBySubscriptions(messageSendBo, request);
	}

	@Transactional("db1TxManager")
	@Override
	public String delete(String curriculumId) {
		try {
			LOGGER.info("删除课程信息:{}", curriculumId);
			curriculumLabelMapper.deleteByPrimaryKey(curriculumId);
			curriculumMembergradeMapper.deleteByPrimaryKey(curriculumId);
			curriculumLecturerGxMapper.deleteByPrimaryKey(curriculumId);
			coursewareMapper.deleteByCurriculumId(curriculumId);
			chapterMapper.deleteByCurriculumId(curriculumId);
			uvipPriceMapper.deleteByPrimaryKey(curriculumId);
			curriculumMapper.deleteByPrimaryKey(curriculumId);
		} catch (Exception e) {
			LOGGER.error("删除课程异常：{}", e);
			throw new ServiceException(4324);
		}
		return "";
	}

	@Transactional("db1TxManager")
	@Override
	public String deleteList(String[] curriculumIds) {
		for (int i = 0; i < curriculumIds.length; i++) {
			this.delete(curriculumIds[i]);
		}
		return "";
	}

	@Override
	public List<CurrMyStudyBo> selectStudyHistory(Map<String, Object> map) {
		List<CurrMyStudyBo> currMyStudyBoList;
		try {
			// 查询课程学习历史
			currMyStudyBoList = curriculumRoMapper.selectStudyHistory(map);
		} catch (Exception e) {
			LOGGER.error("查询课程列表信息异常：{}", e);
			throw new ServiceException(4320);
		}
		return currMyStudyBoList;
	}

	@Override
	public List<CurriculumListsyBo> selectListCollect(Map<String, Object> map) {
		List<CurriculumListsyBo> curriculumListBoList;
		try {
			// 查询收藏课程列表
			curriculumListBoList = curriculumRoMapper.selectListCollect(map);
		} catch (Exception e) {
			LOGGER.error("查询课程列表信息异常：{}", e);
			throw new ServiceException(4320);
		}
		return curriculumListBoList;
	}

	@Override
	public CurrMyStudyNumBo selectStudyNum(Map<String, Object> map) {
		CurrMyStudyNumBo currMyStudyNumBo = new CurrMyStudyNumBo();
		try {
			// 查询本月学习课程数
			int cnt1 = curriculumRoMapper.selectStudyNumByMonth(map);
			currMyStudyNumBo.setStudyNumMonth(cnt1);
			// 查询本年学习课程数
			int cnt2 = curriculumRoMapper.selectStudyNumByYear(map);
			currMyStudyNumBo.setStudyNumYear(cnt2);
			// 查询学习勤奋榜
			String cnt3 = curriculumRoMapper.selectStudyQfb(map);
			currMyStudyNumBo.setStudyQfb(cnt3);
		} catch (Exception e) {
			LOGGER.error("查询课程信息异常：{}", e);
			throw new ServiceException(4321);
		}
		return currMyStudyNumBo;
	}

	@Transactional("db1TxManager")
	@Override
	public String updateBrowsesDay(String curriculumId) {
		try {
			LOGGER.info("课程浏览信息:{}", curriculumId);
			curriculumMapper.updateBrowsesDay(curriculumId);
			curriculumBrowserWatchServiceImpl.updateBrowserNum(curriculumId);
		} catch (Exception e) {
			LOGGER.error("更新课程浏览量异常：{}", e);
			throw new ServiceException(4324);
		}
		return "";
	}

	@Override
	public List<CurriculumListBo> selectByKnowledgeId(String knowledgeId, int num) {
		return curriculumRoMapper.selectByKnowledgeId(knowledgeId, num);
	}

    @Override
    public boolean isOptional(List<CurriculumGiftBo> curriculumGiftBoList) {
        if(curriculumGiftBoList != null && curriculumGiftBoList.size() > 1){
            for(CurriculumGiftBo giftBo:curriculumGiftBoList){
                CurriculumGift gift = curriculumGiftRoMapper.selectByPrimaryKey(giftBo.getId());
                if(StringUtils.isNotEmpty(gift.getOperSymbol()) && "or".equals(gift.getOperSymbol())){
                    return false;
                }
            }
        }
        return true;
    }

}
