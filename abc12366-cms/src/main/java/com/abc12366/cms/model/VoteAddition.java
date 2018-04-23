package com.abc12366.cms.model;

import org.hibernate.validator.constraints.Length;

/**
 * 投票附加信息
 * <p>
 * Created by lijun on 6/27/17.
 */
public class VoteAddition {
    private String id;

    private String voteId;

    private String dictId;

    private Boolean required;

    @Length(max = 200)
    private String content;

    public VoteAddition() {
    }

    public VoteAddition(String id, String voteId, String dictId, Boolean required, String content) {
        this.id = id;
        this.voteId = voteId;
        this.dictId = dictId;
        this.required = required;
        this.content = content;
    }

    private VoteAddition(Builder builder) {
        setId(builder.id);
        setVoteId(builder.voteId);
        setDictId(builder.dictId);
        setRequired(builder.required);
        setContent(builder.content);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "VoteAddition{" +
                "id='" + id + '\'' +
                ", voteId='" + voteId + '\'' +
                ", dictId='" + dictId + '\'' +
                ", required=" + required +
                ", content='" + content + '\'' +
                '}';
    }

    public static final class Builder {
        private String id;
        private String voteId;
        private String dictId;
        private Boolean required;
        private String content;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder voteId(String val) {
            voteId = val;
            return this;
        }

        public Builder dictId(String val) {
            dictId = val;
            return this;
        }

        public Builder required(Boolean val) {
            required = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public VoteAddition build() {
            return new VoteAddition(this);
        }
    }
}
