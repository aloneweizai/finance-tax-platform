package com.abc12366.uc.web.einvoice;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.dzfp.*;
import com.abc12366.uc.service.IDzfpService;
import com.abc12366.uc.service.einvoice.IEinvoiceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 电子发票接口
 * @author zhushuai 2017-8-8
 *
 */
@Controller
@RequestMapping(path = "/einvocie")
public class EinvoiceController {
    
	@Autowired
	IEinvoiceService iEinvoiceService;
	
	@Autowired
    IDzfpService dzfpService;
	
	/**
	 * 电子发票开具查询
	 * @param fpqqlsh 请求流水号
	 * @param fp_hm 发票号码
	 * @param tbstatus 同步状态
	 * @param pageNum 页数
	 * @param pageSize 大小
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/querylist")
	public ResponseEntity einvoiceList(@RequestParam(required=false) String fpqqlsh,
			@RequestParam(required=false) String fp_hm,
			@RequestParam(required=false) String tbstatus,
			@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize){
		Einvocie einvocie=new Einvocie();
		if(!StringUtils.isEmpty(fpqqlsh)){einvocie.setFPQQLSH(fpqqlsh);}
		if(!StringUtils.isEmpty(fp_hm)){einvocie.setFP_HM(fp_hm);}
		if(!StringUtils.isEmpty(tbstatus)){einvocie.setTBSTATUS(tbstatus);}
		PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
		List<Einvocie> list=dzfpService.selectList(einvocie);
		return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list)
                        .getTotal()));
	}
	
	
	
	/**
	 * 电子发票开票/退票
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/{fpqqlsh}")
	public ResponseEntity getEinvoice(@PathVariable String fpqqlsh){
		Einvocie einvoice=iEinvoiceService.getEinvoice(fpqqlsh);
		if(einvoice==null){
			return ResponseEntity.ok(Utils.bodyStatus(9999, "电子发票开票/退票异常"));
		}else{
			if("0000".equals(einvoice.getReturnCode())){
				return ResponseEntity.ok(Utils.kv("data", einvoice));
			}else{
				return ResponseEntity.ok(Utils.bodyStatus(9999, einvoice.getReturnMessage()));
			}
		}
		
	}
	
	/**
	 * 电子发票查询
	 * @param dzfpGetReq
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/query")
	public ResponseEntity queryEinvoice(@Valid @RequestBody DzfqQueryReq dzfpGetReq){
		Einvocie einvoice=iEinvoiceService.queryEinvoice(dzfpGetReq);
		if(einvoice==null){
			return ResponseEntity.ok(Utils.bodyStatus(9999, "电子发票查询异常"));
		}else{
			if("0000".equals(einvoice.getReturnCode())){
				return ResponseEntity.ok(Utils.kv("data", einvoice));
			}else{
				return ResponseEntity.ok(Utils.bodyStatus(9999, einvoice.getReturnMessage()));
			}
		}
		
	}
	
	/**
	 * 电子发票下载地址查询
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/queryDown")
	public ResponseEntity queryDownEinvoice(@Valid @RequestBody DzfpDownloadReq downloadReq){
		EinvocieDown einvoice=iEinvoiceService.queryEinvoice(downloadReq);
		if(einvoice==null){
			return ResponseEntity.ok(Utils.bodyStatus(9999, "电子发票下载地址查询异常"));
		}else{
			if("0000".equals(einvoice.getReturnCode())){
				return ResponseEntity.ok(Utils.kv("data", einvoice));
			}else{
				return ResponseEntity.ok(Utils.bodyStatus(9999, einvoice.getReturnMessage()));
			}
		}
		
	}
	
	
	
}
