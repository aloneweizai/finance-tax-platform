package com.abc12366.cms.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc12366.cms.service.IProductSpreadService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.abc12366.cms.model.ProductSpread;

/**
 * 产品宣传页
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(path = "/product/spread", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ProductSpreadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductSpreadController.class);

	@Autowired
	private IProductSpreadService productSpreadService;

	/**
	 * 产品宣传列表查询
	 * 
	 * @param productname
	 *            名称
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping
	public ResponseEntity list(@RequestParam(required = false) String productname,
			@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("productname", productname);
		List<ProductSpread> productSpreads = productSpreadService.list(map, pageNum, pageSize);
		PageInfo<ProductSpread> pageInfo = new PageInfo<>(productSpreads);
		LOGGER.info("{}", JSONArray.toJSONString(pageInfo));
		return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
	}

	/**
	 * 产品宣传详情
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity selectone(@PathVariable String id) {
		ProductSpread productSpread = productSpreadService.selectone(id);
		LOGGER.info("{}", productSpread);
		return ResponseEntity.ok(Utils.kv("data", productSpread));
	}

	/**
	 * 新增产品宣传
	 * @param productSpread
	 * @param httpServletRequest
	 * @return
	 */
	@PostMapping
	public ResponseEntity save(@RequestBody ProductSpread productSpread, HttpServletRequest httpServletRequest) {
		productSpread.setId(Utils.uuid());
		Date date = new Date();
		productSpread.setCreateTime(date);
		productSpread.setLastUpdate(date);
		productSpread.setUpdateUserId(Utils.getAdminId());
		productSpreadService.save(productSpread);
		return ResponseEntity.ok(Utils.kv("data", productSpread));
	}
	
	/**
	 * 产品宣传修改
	 * @param productSpread
	 * @param httpServletRequest
	 * @return
	 */
	@PutMapping
	public ResponseEntity put(@RequestBody ProductSpread productSpread, HttpServletRequest httpServletRequest) {
		productSpread.setLastUpdate(new Date());
		productSpread.setUpdateUserId(Utils.getAdminId());
		productSpreadService.put(productSpread);
		return ResponseEntity.ok(Utils.kv("data", productSpread));
	}
	
	/**
	 * 删除产品宣传
	 * @param id
	 * @param httpServletRequest
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity del(@PathVariable String id, HttpServletRequest httpServletRequest) {
		productSpreadService.del(id);
		return ResponseEntity.ok(Utils.kv());
	}

}
