package com.abc12366.message.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * 文件上传
 **/
@SuppressWarnings("serial")
public class FjBo implements Serializable {

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
