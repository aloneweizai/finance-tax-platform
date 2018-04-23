package com.abc12366.uc.model.gift.bo;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 
 * 礼包审核BO
 * 
 **/
@SuppressWarnings("serial")
public class GiftCheckBO implements Serializable {


	/**申请单号**/
	private String applyId;

	/**状态，0：不通过，1：通过**/
	private int status;

	/**备注**/
    @Size(min = 0, max = 150)
	private String remark;

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "GiftCheckBO{" +
				"applyId='" + applyId + '\'' +
				", status=" + status +
				", remark='" + remark + '\'' +
				'}';
	}
}
