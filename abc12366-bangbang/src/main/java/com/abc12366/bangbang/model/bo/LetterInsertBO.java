package com.abc12366.bangbang.model.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户消息对象
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-27 11:21 AM
 * @since 1.0.0
 */
public class LetterInsertBO {


    @NotEmpty
    @Length(min = 1, max = 64)
    private String fromUserId;

    @NotEmpty
    @Length(min = 1, max = 64)
    private String toUserId;

    @NotEmpty
    @Length(min = 1, max = 500)
    private String content;

    @Length(min = 1, max = 1)
    private String type;

    public LetterInsertBO() {
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
