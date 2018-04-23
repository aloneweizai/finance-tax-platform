package com.abc12366.uc.wsbssoa.response;

/**
 * RSA公钥
 * Created by zhouzhi on 2015-11-24.
 */
public class RSAPkResponse extends BaseResponse {
    private byte[] pk;

    public byte[] getPk() {
        return pk;
    }

    public void setPk(byte[] pk) {
        this.pk = pk;
    }
}
