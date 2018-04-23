package com.abc12366.bangbang.web.curriculum;

import com.abc12366.bangbang.model.bo.IdsBo;
import com.abc12366.bangbang.model.curriculum.bo.*;
import com.abc12366.bangbang.service.CurriculumService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 学堂课程管理模块
 *
 * @author xieyanmao
 * @create 2017-08-10
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/curriculum", headers = Constant.VERSION_HEAD + "=1")
public class CurriculumController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurriculumController.class);

	@Autowired
	private CurriculumService curriculumService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 课程列表查询
	 * @param page 页数
	 * @param size 条数
	 * @param title 标题
	 * @param status 状态
	 * @param label 标签
	 * @param recommend 推荐类型
	 * @param classify 课程分类
	 * @param sortFieldName 排序条件
	 * @param sortName 排序方式
	 * @param isFree 是否收费
	 * @return
	 */
	@GetMapping
	public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "label", required = false) String label,
			@RequestParam(value = "recommend", required = false) String recommend,
			@RequestParam(value = "sortName", required = false) String sortName,
			@RequestParam(value = "isFree", required = false) String isFree,
			@RequestParam(value = "sortFieldName", required = false) String sortFieldName,
			@RequestParam(value = "classify", required = false) String classify) {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("status", status);// 状态
		dataMap.put("title", title);// 标题
		dataMap.put("label", label);// 标签
		dataMap.put("classify", classify);// 课程分类
		dataMap.put("sortName", sortName);
		dataMap.put("sortFieldName", sortFieldName);
		dataMap.put("isFree", isFree);
		if(StringUtils.isNotEmpty(recommend)){
            if("putong".equals(recommend)){
                dataMap.put("recommend", "");// 课程
            }else {
                dataMap.put("recommend", recommend);// 课程
            }
		}
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		List<CurriculumListBo> dataList = curriculumService.selectList(dataMap);
		return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

	}

	/**
	 * 最新课程查询(前端用无需登录)
	 */
	@GetMapping(path = "/selectListNew")
	public ResponseEntity selectListNew(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
			@RequestParam(value = "label", required = false) String label,
			@RequestParam(value = "classify", required = false) String classify,
			@RequestParam(value = "freeMember", required = false) String freeMember,
			@RequestParam(value = "isFree", required = false) String isFree) {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("label", label);// 标签
		dataMap.put("classify", classify);// 课程分类
		dataMap.put("freeMember", freeMember);// 会员等级
		dataMap.put("isFree", isFree);// 是否免费
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		List<CurriculumListsyBo> dataList = curriculumService.selectListNew(dataMap);
		return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

	}

	/**
	 * 会员专享课程查询(前端用无需登录)
	 */
	@GetMapping(path = "/selectListVIP")
	public ResponseEntity selectListVIP(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
			@RequestParam(value = "label", required = false) String label,
			@RequestParam(value = "classify", required = false) String classify,
			@RequestParam(value = "freeMember", required = false) String freeMember,
			@RequestParam(value = "isFree", required = false) String isFree) {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("label", label);// 标签
		dataMap.put("classify", classify);// 课程分类
		dataMap.put("freeMember", freeMember);// 会员等级
		dataMap.put("isFree", isFree);// 是否免费
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		List<CurriculumListsyBo> dataList = curriculumService.selectListVIP(dataMap);
		return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

	}

	/**
	 * 最热课程查询(前端用无需登录)
	 */
	@GetMapping(path = "/selectListWatch")
	public ResponseEntity selectListWatch(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
			@RequestParam(value = "label", required = false) String label,
			@RequestParam(value = "classify", required = false) String classify,
			@RequestParam(value = "freeMember", required = false) String freeMember,
			@RequestParam(value = "isFree", required = false) String isFree) {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("label", label);// 标签
		dataMap.put("classify", classify);// 课程分类
		dataMap.put("freeMember", freeMember);// 会员等级
		dataMap.put("isFree", isFree);// 是否免费
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		List<CurriculumListsyBo> dataList = curriculumService.selectListWatch(dataMap);
		return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

	}

	/**
	 * 推荐课程查询(前端用无需登录)
	 */
	@GetMapping(path = "/selectRecommend")
	public ResponseEntity selectRecommend(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
			@RequestParam(value = "label", required = false) String label,
			@RequestParam(value = "classify", required = false) String classify,
			@RequestParam(value = "freeMember", required = false) String freeMember,
			@RequestParam(value = "isFree", required = false) String isFree) {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("label", label);// 标签
		dataMap.put("classify", classify);// 课程分类
		dataMap.put("freeMember", freeMember);// 会员等级
		dataMap.put("isFree", isFree);// 是否免费
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		List<CurriculumListsyBo> dataList = curriculumService.selectRecommend(dataMap);
		return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

	}

	/**
	 * 推荐课程查询
	 */
	@GetMapping(path = "/selectRecommendForqt")
	public ResponseEntity selectRecommendForqt() {
		List<CurriculumListsyBo> list = null;
		if (redisTemplate.hasKey("Bangb_RecommendForqt")) {
			list = JSONArray.parseArray(redisTemplate.opsForValue().get("Bangb_RecommendForqt"),
					CurriculumListsyBo.class);
			LOGGER.info("从Redis获取数据:" + JSONArray.toJSONString(list));
			return ResponseEntity.ok(Utils.kv("dataList", list, "total", list.size()));
		} else {
			Map<String, Object> dataMap = new HashMap<>();
			PageHelper.startPage(1, 10, true).pageSizeZero(true).reasonable(true);
			list = curriculumService.selectRecommend(dataMap);
			redisTemplate.opsForValue().set("Bangb_RecommendForqt", JSONArray.toJSONString(list), RedisConstant.DAY_1,
					TimeUnit.DAYS);
			return ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
		}
	}

	/**
	 * 查询课程标签列表(前端用无需登录)
	 */
	@GetMapping(path = "/selectLabelList")
	public ResponseEntity selectLabelList() {
		// 查询课程标签列表信息
		List<CurriculumLabelBo> dataList = curriculumService.selectLabelList();
		return ResponseEntity.ok(Utils.kv("dataList", dataList));
	}

	/**
	 * 查询单个课程授课信息
	 */
	@GetMapping(path = "/selectSituation/{curriculumId}")
	public ResponseEntity selectSituation(@PathVariable String curriculumId) {
		// 查询课程信息
		CurriculumSituationBo curriculumSituationBo = curriculumService.selectSituation(curriculumId);
		return ResponseEntity.ok(Utils.kv("data", curriculumSituationBo));
	}

	/**
	 * 课程新增
	 */
	@PostMapping
	public ResponseEntity save(@Valid @RequestBody CurriculumBo curriculumBo, HttpServletRequest request) {
		// 新增课程信息
		String userId = request.getHeader(Constant.USER_TOKEN_HEAD);
		curriculumBo.setCreaterId(userId);
		curriculumBo = curriculumService.save(curriculumBo);
		redisTemplate.delete("Bangb_RecommendForqt");
		return ResponseEntity.ok(Utils.kv("data", curriculumBo));
	}

	/**
	 * 查询单个课程信息
	 */
	@GetMapping(path = "/{curriculumId}")
	public ResponseEntity selectOne(@PathVariable String curriculumId) {
		// 查询课程信息
		CurriculumBo curriculumBo = curriculumService.selectCurriculum(curriculumId);
		return ResponseEntity.ok(Utils.kv("data", curriculumBo));
	}

	/**
	 * 校验购买赠送活动
	 */
	@PostMapping(path = "/isOptional")
	public ResponseEntity isOptional(@Valid @RequestBody List<CurriculumGiftBo> curriculumGiftBoList) {
		// 查询课程信息
		boolean isOptional = curriculumService.isOptional(curriculumGiftBoList);
		return ResponseEntity.ok(Utils.kv("data", isOptional));
	}

	/**
	 * 查询商品是否已存在
	 */
	@GetMapping(path = "/selectCurrCntByGoodsId/{goodsId}")
	public ResponseEntity selectCurrCntByGoodsId(@PathVariable String goodsId) {
		// 查询课程信息
		int cnt = curriculumService.selectCurrCntByGoodsId(goodsId);
		return ResponseEntity.ok(Utils.kv("data", cnt));
	}

	/**
	 * 查询单个课程详情信息(前端用无需登录)
	 */
	@GetMapping(path = "/selectCurriculum/{curriculumId}")
	public ResponseEntity selectCurriculum(@PathVariable String curriculumId) {
		// 查询课程信息
		CurriculumsyBo curriculumBo = curriculumService.selectCurriculumsy(curriculumId);
		return ResponseEntity.ok(Utils.kv("data", curriculumBo));
	}

	/**
	 * 查询单个课程详情信息(查询下架课程)
	 */
	@GetMapping(path = "/selectCurriculum2/{curriculumId}")
	public ResponseEntity selectCurriculum2(@PathVariable String curriculumId) {
		// 查询课程信息
		CurriculumsyBo curriculumBo = curriculumService.selectCurriculumsy2(curriculumId);
		return ResponseEntity.ok(Utils.kv("data", curriculumBo));
	}

	/**
	 * 查询单个课程评价统计(前端用无需登录)
	 */
	@GetMapping(path = "/selectEvaluateTj/{curriculumId}")
	public ResponseEntity selectEvaluateTj(@PathVariable String curriculumId) {
		// 查询课程评价统计信息
		CurriculumEvaluateTjBo evaluateTjBo = curriculumService.selectEvaluateTj(curriculumId);
		return ResponseEntity.ok(Utils.kv("data", evaluateTjBo));
	}

	/**
	 * 更新课程信息
	 */
	@PutMapping(path = "/{curriculumId}")
	public ResponseEntity update(@PathVariable String curriculumId, @Valid @RequestBody CurriculumBo curriculumBo,HttpServletRequest request) {
		// 更新课程信息
		curriculumBo = curriculumService.update(curriculumBo,request);
		redisTemplate.delete("Bangb_RecommendForqt");
		return ResponseEntity.ok(Utils.kv("data", curriculumBo));
	}

	/**
	 * 更新课程状态
	 *
	 * @param status
	 * @param curriculumId
	 * @return
	 */
	@PutMapping(path = "/updateStatus/{curriculumId}")
	public ResponseEntity updateStatus(@Valid @RequestBody String status,
			@PathVariable("curriculumId") String curriculumId, HttpServletRequest request) {
		curriculumService.updateStatus(curriculumId, status, request);
		redisTemplate.delete("Bangb_RecommendForqt");
		return ResponseEntity.ok(Utils.kv("data", curriculumId));
	}

	/**
	 * 删除课程信息
	 */
	@DeleteMapping(path = "/{curriculumId}")
	public ResponseEntity delete(@PathVariable String curriculumId) {
		// 删除课程信息
		String rtn = curriculumService.delete(curriculumId);
		redisTemplate.delete("Bangb_RecommendForqt");
		return ResponseEntity.ok(Utils.kv("data", rtn));
	}

	/**
	 * 批量删除课程信息
	 */
	@PostMapping(path = "/deletelist")
	public ResponseEntity deleteList(@RequestBody IdsBo idsBo) {
		LOGGER.info("{}", idsBo);
		// 批量删除课程信息
		String rtn = curriculumService.deleteList(idsBo.getIds());
		LOGGER.info("{}", rtn);
		redisTemplate.delete("Bangb_RecommendForqt");
		return ResponseEntity.ok(Utils.kv("data", idsBo));
	}

	/**
	 * 查询课程学习历史
	 */
	@GetMapping(path = "/selectStudyHistory")
	public ResponseEntity selectStudyHistory(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
			@RequestParam(value = "userId", required = false) String userId) {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("userId", userId);// 用户ID
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		List<CurrMyStudyBo> dataList = curriculumService.selectStudyHistory(dataMap);
		return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

	}

	/**
	 * 查询课程收藏列表
	 */
	@GetMapping(path = "/selectListCollect")
	public ResponseEntity selectListCollect(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
			@RequestParam(value = "userId", required = false) String userId) {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("userId", userId);// 用户ID
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		List<CurriculumListsyBo> dataList = curriculumService.selectListCollect(dataMap);
		return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));

	}

	/**
	 * 查询学习课程数
	 */
	@GetMapping(path = "/selectStudyNum")
	public ResponseEntity selectStudyNum(@RequestParam(value = "userId", required = false) String userId) {
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("userId", userId);// 用户ID
		CurrMyStudyNumBo data = curriculumService.selectStudyNum(dataMap);
		return ResponseEntity.ok(Utils.kv("data", data));

	}

	/**
	 * 更新课程浏览量
	 */
	@PutMapping(path = "/updateBrowsesDay/{curriculumId}")
	public ResponseEntity updateBrowsesDay(@PathVariable String curriculumId) {
		// 更新课程浏览量
		String rtn = curriculumService.updateBrowsesDay(curriculumId);
		return ResponseEntity.ok(Utils.kv("data", rtn));
	}

	/**
	 * 根据知识库标签查询课程
	 */
	@GetMapping(path = "/selectListByKnowledgeId")
	public ResponseEntity selectListByKnowledgeId(@RequestParam(value = "knowledgeId") String knowledgeId,
			@RequestParam(value = "num", defaultValue = "3") int num) {
		List<CurriculumListBo> dataList = curriculumService.selectByKnowledgeId(knowledgeId, num);
		return ResponseEntity.ok(Utils.kv("dataList", dataList));
	}

}
