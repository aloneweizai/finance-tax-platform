package com.abc12366.uc.model.bo;

import java.io.Serializable;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-03-27 4:20 PM
 * @since 1.0.0
 */
public class TokenBO implements Serializable {

    private final String token;

    public TokenBO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
