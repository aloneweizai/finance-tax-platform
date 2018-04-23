package com.abc12366.uc.model.weixin.bo;


import java.util.List;

/**
 * Created by relic5 on 2017/6/11.
 */
public class FileListDto {
    
    private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;
	
    private List<FileDto> dataList;

    public List<FileDto> getDataList() {
        return dataList;
    }

    public void setDataList(List<FileDto> dataList) {
        this.dataList = dataList;
    }
}
