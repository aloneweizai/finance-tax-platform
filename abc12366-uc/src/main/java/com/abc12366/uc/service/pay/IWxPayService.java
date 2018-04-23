package com.abc12366.uc.service.pay;

import com.abc12366.uc.model.pay.WxRefund;
import com.abc12366.uc.model.pay.WxRefundRsp;
import com.abc12366.uc.model.pay.Wxrefundquery;
import com.abc12366.uc.model.pay.WxrefundqueryRsp;

public interface IWxPayService {
     public WxRefundRsp doWxRefund(WxRefund wxrefund);

	public WxrefundqueryRsp doWxRefundQuery(Wxrefundquery wxrefundquery);
}
