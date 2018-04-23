package com.abc12366.cms.model.bo;

/**
 * CMS评论表
 * add by xieyanmao on 2017-5-2
 **/
public class CommentSaveBo {
    private CommentBo comment;
    private CommentExtBo commentExt;

    public CommentBo getComment() {
        return comment;
    }

    public void setComment(CommentBo comment) {
        this.comment = comment;
    }

    public CommentExtBo getCommentExt() {
        return commentExt;
    }

    public void setCommentExt(CommentExtBo commentExt) {
        this.commentExt = commentExt;
    }
}
