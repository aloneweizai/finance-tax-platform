package com.abc12366.uc.model.weixin.bo;

import java.util.List;

/**
 * Created by relic5 on 2017/6/11.
 */
public class FjDto {

    private String fileName;

    private List<Byte> content;
    
    private String fileContent;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Byte> getContent() {
        return content;
    }

    public void setContent(List<Byte> content) {
        this.content = content;
    }

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
}
