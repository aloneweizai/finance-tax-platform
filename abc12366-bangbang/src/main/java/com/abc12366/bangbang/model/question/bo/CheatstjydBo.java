package com.abc12366.bangbang.model.question.bo;

import java.io.Serializable;


/**
 * 
 * 秘籍表
 * 
 **/
@SuppressWarnings("serial")
public class CheatstjydBo implements Serializable {

	/****varchar(64)**/
	private String id;

	/**标题**varchar(300)**/
	private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
