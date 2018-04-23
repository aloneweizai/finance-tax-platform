package com.abc12366.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * 投票模型
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-07 4:08 PM
 * @since 1.0.0
 */
public class Vote {

    private String id;

    // 投票名称
    @NotEmpty
    @Length(min = 2, max = 50)
    private String name;

    // 开始时间
    @NotNull
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startTime;

    // 截止时间
    @NotNull
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp endTime;

    // 是否只有登录用户可以投票
    @NotNull
    private Boolean login;

    // 投票类型
    @NotEmpty
    @Length(min = 1, max = 10)
    private String channel;

    // 快速投票
    private Boolean quickVote;

    // 隐私投票
    @Length(max = 10)
    private String privacyVote;

    // 隐私投票密码
    @Length(min = 4, max = 16)
    private String privacyPassword;

    // 每天投票
    private Boolean dayVote;

    // 投票后查看结果
    private Boolean showResult;

    // 活动介绍
    @Length(max = 200)
    private String startIntro;

    // 投票后显示的内容
    @Length(max = 200)
    private String endIntro;

    // 是否可以访问
    private Boolean access;

    // 页眉
    @Length(max = 128)
    private String header;

    // 页脚
    @Length(max = 200)
    private String footer;

    // 生成地址
    @Length(max = 128)
    private String url;

    // 发布状态
    @NotNull
    private Integer status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp lastUpdate;

    // 显示验证码
    private Boolean validateCode;

    // 隐藏投票结果
    private Boolean hiddenResult;

    private List<Subject> subjectList;

    private List<VoteAddition> additionList;

    // 参与人数（number of participants）
    private Integer nop;
    // 访问次数 (Number of visits)
    private Integer nov;

    public Vote() {
    }

    public Vote(String id, String name, Timestamp startTime, Timestamp endTime, Boolean login, String channel,
                Boolean quickVote, String privacyVote, String privacyPassword, Boolean dayVote, Boolean showResult,
                String startIntro, String endIntro, Boolean access, String header, String footer, String url,
                Integer status, Timestamp createTime, Timestamp lastUpdate, Boolean validateCode, Boolean hiddenResult,
                Integer nop, Integer nov) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.login = login;
        this.channel = channel;
        this.quickVote = quickVote;
        this.privacyVote = privacyVote;
        this.privacyPassword = privacyPassword;
        this.dayVote = dayVote;
        this.showResult = showResult;
        this.startIntro = startIntro;
        this.endIntro = endIntro;
        this.access = access;
        this.header = header;
        this.footer = footer;
        this.url = url;
        this.status = status;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
        this.validateCode = validateCode;
        this.hiddenResult = hiddenResult;
        this.nop = nop;
        this.nov = nov;
    }

    private Vote(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setStartTime(builder.startTime);
        setEndTime(builder.endTime);
        setLogin(builder.login);
        setChannel(builder.channel);
        setQuickVote(builder.quickVote);
        setPrivacyVote(builder.privacyVote);
        setPrivacyPassword(builder.privacyPassword);
        setDayVote(builder.dayVote);
        setShowResult(builder.showResult);
        setStartIntro(builder.startIntro);
        setEndIntro(builder.endIntro);
        setAccess(builder.access);
        setHeader(builder.header);
        setFooter(builder.footer);
        setUrl(builder.url);
        setStatus(builder.status);
        setCreateTime(builder.createTime);
        setLastUpdate(builder.lastUpdate);
        setValidateCode(builder.validateCode);
        setHiddenResult(builder.hiddenResult);
        setNop(builder.nop);
        setNov(builder.nov);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Boolean getQuickVote() {
        return quickVote;
    }

    public void setQuickVote(Boolean quickVote) {
        this.quickVote = quickVote;
    }

    public String getPrivacyVote() {
        return privacyVote;
    }

    public void setPrivacyVote(String privacyVote) {
        this.privacyVote = privacyVote;
    }

    public String getPrivacyPassword() {
        return privacyPassword;
    }

    public void setPrivacyPassword(String privacyPassword) {
        this.privacyPassword = privacyPassword;
    }

    public Boolean getDayVote() {
        return dayVote;
    }

    public void setDayVote(Boolean dayVote) {
        this.dayVote = dayVote;
    }

    public Boolean getShowResult() {
        return showResult;
    }

    public void setShowResult(Boolean showResult) {
        this.showResult = showResult;
    }

    public String getStartIntro() {
        return startIntro;
    }

    public void setStartIntro(String startIntro) {
        this.startIntro = startIntro;
    }

    public String getEndIntro() {
        return endIntro;
    }

    public void setEndIntro(String endIntro) {
        this.endIntro = endIntro;
    }

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Boolean getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(Boolean validateCode) {
        this.validateCode = validateCode;
    }

    public Boolean getHiddenResult() {
        return hiddenResult;
    }

    public void setHiddenResult(Boolean hiddenResult) {
        this.hiddenResult = hiddenResult;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<VoteAddition> getAdditionList() {
        return additionList;
    }

    public void setAdditionList(List<VoteAddition> additionList) {
        this.additionList = additionList;
    }

    public Integer getNop() {
        return nop;
    }

    public void setNop(Integer nop) {
        this.nop = nop;
    }

    public Integer getNov() {
        return nov;
    }

    public void setNov(Integer nov) {
        this.nov = nov;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", login=" + login +
                ", channel='" + channel + '\'' +
                ", quickVote=" + quickVote +
                ", privacyVote='" + privacyVote + '\'' +
                ", privacyPassword='" + privacyPassword + '\'' +
                ", dayVote='" + dayVote + '\'' +
                ", showResult=" + showResult +
                ", startIntro='" + startIntro + '\'' +
                ", endIntro='" + endIntro + '\'' +
                ", access=" + access +
                ", header='" + header + '\'' +
                ", footer='" + footer + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", validateCode=" + validateCode +
                ", hiddenResult=" + hiddenResult +
                ", subjectList=" + subjectList +
                ", nop=" + nop +
                ", nov=" + nov +
                '}';
    }


    public static final class Builder {
        private String id;
        private String name;
        private Timestamp startTime;
        private Timestamp endTime;
        private Boolean login;
        private String channel;
        private Boolean quickVote;
        private String privacyVote;
        private String privacyPassword;
        private Boolean dayVote;
        private Boolean showResult;
        private String startIntro;
        private String endIntro;
        private Boolean access;
        private String header;
        private String footer;
        private String url;
        private Integer status;
        private Timestamp createTime;
        private Timestamp lastUpdate;
        private Boolean validateCode;
        private Boolean hiddenResult;
        private Integer nop;
        private Integer nov;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder startTime(Timestamp val) {
            startTime = val;
            return this;
        }

        public Builder endTime(Timestamp val) {
            endTime = val;
            return this;
        }

        public Builder login(Boolean val) {
            login = val;
            return this;
        }

        public Builder channel(String val) {
            channel = val;
            return this;
        }

        public Builder quickVote(Boolean val) {
            quickVote = val;
            return this;
        }

        public Builder privacyVote(String val) {
            privacyVote = val;
            return this;
        }

        public Builder privacyPassword(String val) {
            privacyPassword = val;
            return this;
        }

        public Builder dayVote(Boolean val) {
            dayVote = val;
            return this;
        }

        public Builder showResult(Boolean val) {
            showResult = val;
            return this;
        }

        public Builder startIntro(String val) {
            startIntro = val;
            return this;
        }

        public Builder endIntro(String val) {
            endIntro = val;
            return this;
        }

        public Builder access(Boolean val) {
            access = val;
            return this;
        }

        public Builder header(String val) {
            header = val;
            return this;
        }

        public Builder footer(String val) {
            footer = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder status(Integer val) {
            status = val;
            return this;
        }

        public Builder createTime(Timestamp val) {
            createTime = val;
            return this;
        }

        public Builder lastUpdate(Timestamp val) {
            lastUpdate = val;
            return this;
        }

        public Builder validateCode(Boolean val) {
            validateCode = val;
            return this;
        }

        public Builder hiddenResult(Boolean val) {
            hiddenResult = val;
            return this;
        }

        public Builder nop(Integer val) {
            nop = val;
            return this;
        }

        public Builder nov(Integer val) {
            nov = val;
            return this;
        }

        public Vote build() {
            return new Vote(this);
        }
    }
}
