package com.abc12366.cms.model.bo;

/**
 * 投票统计浏览器结果
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-29 12:47 PM
 * @since 1.0.0
 */
public class VoteStatBrowserBO {

    private String userAgent;

    private Integer num;

    public VoteStatBrowserBO() {
    }

    public VoteStatBrowserBO(String userAgent, Integer num) {
        this.userAgent = userAgent;
        this.num = num;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "VoteStatBrowserBO{" +
                "userAgent='" + userAgent + '\'' +
                ", num=" + num +
                '}';
    }
}
