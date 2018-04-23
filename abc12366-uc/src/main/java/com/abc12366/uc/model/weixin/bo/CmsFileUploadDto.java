package com.abc12366.uc.model.weixin.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by relic5 on 2017/6/11.
 */
public class CmsFileUploadDto {

    private String directory;

    private List<FjDto> fjBo = new ArrayList<>();

    public String getDirectory() {
        return directory;
    }

    public List<FjDto> getFjBo() {
        return fjBo;
    }

    public void setFjBo(List<FjDto> fjBo) {
        this.fjBo = fjBo;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

}
