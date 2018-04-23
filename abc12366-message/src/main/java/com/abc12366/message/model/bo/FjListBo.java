package com.abc12366.message.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * 文件上传
 **/
@SuppressWarnings("serial")
public class FjListBo implements Serializable {

    private String directory;

    private List<FjBo> fjBo;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public List<FjBo> getFjBo() {
        return fjBo;
    }

    public void setFjBo(List<FjBo> fjBo) {
        this.fjBo = fjBo;
    }
}
