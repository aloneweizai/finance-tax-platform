package com.abc12366.uc.service.einvoice.impl;


import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.InvoiceDetailMapper;
import com.abc12366.uc.mapper.db2.DictRoMapper;
import com.abc12366.uc.mapper.db2.DzfpRoMapper;
import com.abc12366.uc.mapper.db2.InvoiceDetailRoMapper;
import com.abc12366.uc.mapper.db2.InvoiceRoMapper;
import com.abc12366.uc.model.Dict;
import com.abc12366.uc.model.dzfp.*;
import com.abc12366.uc.model.invoice.InvoiceDetail;
import com.abc12366.uc.model.invoice.bo.InvoiceBO;
import com.abc12366.uc.model.order.bo.OrderBO;
import com.abc12366.uc.service.einvoice.IEinvoiceService;
import com.abc12366.uc.webservice.DzfpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EinvoiceServiceImpl implements IEinvoiceService {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(EinvoiceServiceImpl.class);

    @Autowired
    private DzfpRoMapper dzfpRoMapper;

    @Autowired
    private DictRoMapper dictRoMapper;

    @Autowired
    private InvoiceDetailRoMapper invoiceDetailRoMapper;

    @Autowired
    private InvoiceDetailMapper invoiceDetailMapper;

    @Autowired
    private InvoiceRoMapper invoiceRoMapper;


	@Override
	public Einvocie getEinvoice(String fpqqlsh) {
		Einvocie einvocie= dzfpRoMapper.selectOne(fpqqlsh);
        if(einvocie == null){
            throw new ServiceException(4011,"发票开票信息查询失败");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("invoiceNo",einvocie.getFP_HM());
        map.put("invoiceCode",einvocie.getFP_DM());
        invoiceCancel(map);
       /* DzfpGetReq dzfpGetReq = new DzfpGetReq();
        dzfpGetReq.setFpqqlsh(fpqqlsh);
        dzfpGetReq.setYfp_dm(einvocie.getFP_DM());
        dzfpGetReq.setYfp_hm(einvocie.getFP_HM());
        dzfpGetReq.setKplx("1");
		try {
			einvocie = (Einvocie) DzfpClient.doSender("DFXJ1001", dzfpGetReq.tosendXml(), Einvocie.class);
		} catch (Exception e) {
			LOGGER.error("电子发票webservice调用异常,原因：",e);
		}*/
		return einvocie;
	}

    /**
     * 发票作废
     */
    public void invoiceCancel(Map<String,Object> map) {
        // 发票作废
        InvoiceBO invoiceBO = invoiceRoMapper.selectByInvoiceNo(map);
        if (invoiceBO != null) {

            DzfpGetReq req = new DzfpGetReq();
            List<InvoiceXm> dataList = new ArrayList<>();
            if ("1".equals(invoiceBO.getProperty())) { // 纸质发票
                LOGGER.info("该会员订购已开具纸质发票，不能退订：{}");
                throw new ServiceException(4102, "该会员订购已开具纸质发票，不能退订");
            } else if ("2".equals(invoiceBO.getProperty())) { // 电子发票
                if ("1".equals(invoiceBO.getName())) { // 个人发票
                    req.setGmf_mc("个人");
                } else {
                    req.setGmf_mc(invoiceBO.getNsrmc());
                    req.setGmf_nsrsbh(invoiceBO.getNsrsbh());
                    req.setGmf_dzdh(invoiceBO.getAddress());
                    req.setGmf_yhzh(invoiceBO.getBank());
                    req.setGmf_sjh(invoiceBO.getPhone());
                }
                req.setFpqqlsh(DateUtils.getFPQQLSH());
                req.setKplx("1");
                req.setZsfs("0");
                req.setKpr(Utils.getAdminInfo().getNickname());
                req.setHylx("0");
                req.setYfp_dm(invoiceBO.getInvoiceCode());
                req.setYfp_hm(invoiceBO.getInvoiceNo());


                InvoiceXm xm = new InvoiceXm();
                xm.setFphxz("0");
                xm.setXmmc(selectFieldValue("invoicecontent", invoiceBO.getContent()));
                xm.setXmsl(-1.00);
                xm.setTotalAmt(-invoiceBO.getAmount());
                dataList.add(xm);

                req.setInvoiceXms(dataList);
            }
            Einvocie einvocie = null;
            try {
                einvocie = (Einvocie) DzfpClient.doSender("DFXJ1001", req.tosendXml(), Einvocie.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (einvocie != null) {

                if ("0000".equals(einvocie.getReturnCode())) { // 更新作废状态
                    InvoiceDetail id = invoiceDetailRoMapper.selectByInvoiceNo(invoiceBO.getInvoiceNo());
                    if (id == null) {
                        throw new ServiceException(4102, "查找发票详情错误");
                    }
                    id.setStatus("3");
                    id.setLastUpdate(new Date());
                    id.setSpUrl(einvocie.getSP_URL());
                    id.setPdfUrl(einvocie.getPDF_URL());
                    invoiceDetailMapper.update(id);
                } else {
                    throw new ServiceException(einvocie.getReturnCode(), einvocie.getReturnMessage());
                }
            }
        }
    }

    private String selectFieldValue(String fieldValue, String value) {
        Dict dict = new Dict();
        dict.setDictId(fieldValue);
        dict.setFieldValue(value);
        dict = dictRoMapper.selectOne(dict);
        return dict != null ? dict.getFieldKey() : "";
    }

	@Override
	public Einvocie queryEinvoice(DzfqQueryReq dzfpGetReq) {
		Einvocie einvocie=null;
		try {
			einvocie = (Einvocie) DzfpClient.doSender("DFXJ1004", dzfpGetReq.tosendXml(), Einvocie.class);
		} catch (Exception e) {
			LOGGER.error("电子发票webservice调用异常,原因：",e);
		}
		return einvocie;
	}

	@Override
	public EinvocieDown queryEinvoice(DzfpDownloadReq downloadReq) {
		EinvocieDown einvocieDown=null;
		try {
			einvocieDown = (EinvocieDown) DzfpClient.doSender("DFXJ1005", downloadReq.tosendXml(), EinvocieDown.class);
		} catch (Exception e) {
			LOGGER.error("电子发票webservice调用异常,原因：",e);
		}
		return einvocieDown;
	}
	
}
