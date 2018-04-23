package com.abc12366.bangbang.model;

/**
 * 
 * 
 * 
 **/
public class KnowledgeAttachment {

	/**PK**/
	private String id;

	/**知识库id**/
	private String knowledgeId;

	/**文件名**/
	private String fileName;

	/**文件路径**/
	private String filePath;

	/*排序*/
	private Integer sort;

	public Integer getSort() {
		return sort;
	}

	public KnowledgeAttachment setSort(Integer sort) {
		this.sort = sort;
		return this;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setKnowledgeId(String knowledgeId){
		this.knowledgeId = knowledgeId;
	}

	public String getKnowledgeId(){
		return this.knowledgeId;
	}

	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	public String getFileName(){
		return this.fileName;
	}

	public void setFilePath(String filePath){
		this.filePath = filePath;
	}

	public String getFilePath(){
		return this.filePath;
	}

}
