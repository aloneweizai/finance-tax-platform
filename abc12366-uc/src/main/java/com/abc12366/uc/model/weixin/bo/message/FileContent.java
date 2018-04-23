package com.abc12366.uc.model.weixin.bo.message;

import java.io.Serializable;
import java.util.List;
/**
 * 文件上传
 * @author zhushuai 2017-11-6
 *
 */
public class FileContent implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String fileName; //文件名
    private List<Byte> fileContent; //文件内容

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Byte> getFileContent() {
        return fileContent;
    }

    public void setFileContent(List<Byte> fileContent) {
        this.fileContent = fileContent;
    }


}
