package com.abc12366.bangbang.model;
import java.io.Serializable;


/**
 * 
 * 用户对象
 * lizhongwei
 * 
 **/
@SuppressWarnings("serial")
public class User implements Serializable {

	/**用户ID**/
	private String id;

	/****/
	private String username;

	/**手机号码**/
	private String phone;

	/**密码**/
	private String password;

	/**注册邮箱**/
	private String regMail;

	/**注册IP**/
	private String regIP;

	/**密码干扰串用来和密码进行配合验证，防止被暴力破解**/
	private String salt;

	/**昵称**/
	private String nickname;

	/**激活状态**/
	private Integer status;

	/**创建时间**/
	private java.util.Date createTime;

	/**修改时间**/
	private java.util.Date lastUpdate;

	/**原始用户图片路径**/
	private String userPicturePath;

	/**大尺寸用户头像路径**/
	private String maxUserPicturePath;

	/**中尺寸用户头像路径**/
	private String midUserPicturePath;

	/**小尺寸用户头像路径**/
	private String minUserPicturePath;

	/**累计积分**/
	private Long points;

	/**累计经验值**/
	private Long exp;

	/**用户会员等级**/
	private String vipLevel;



	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setRegMail(String regMail){
		this.regMail = regMail;
	}

	public String getRegMail(){
		return this.regMail;
	}

	public void setRegIP(String regIP){
		this.regIP = regIP;
	}

	public String getRegIP(){
		return this.regIP;
	}

	public void setSalt(String salt){
		this.salt = salt;
	}

	public String getSalt(){
		return this.salt;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getNickname(){
		return this.nickname;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setLastUpdate(java.util.Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public java.util.Date getLastUpdate(){
		return this.lastUpdate;
	}

	public void setUserPicturePath(String userPicturePath){
		this.userPicturePath = userPicturePath;
	}

	public String getUserPicturePath(){
		return this.userPicturePath;
	}

	public void setMaxUserPicturePath(String maxUserPicturePath){
		this.maxUserPicturePath = maxUserPicturePath;
	}

	public String getMaxUserPicturePath(){
		return this.maxUserPicturePath;
	}

	public void setMidUserPicturePath(String midUserPicturePath){
		this.midUserPicturePath = midUserPicturePath;
	}

	public String getMidUserPicturePath(){
		return this.midUserPicturePath;
	}

	public void setMinUserPicturePath(String minUserPicturePath){
		this.minUserPicturePath = minUserPicturePath;
	}

	public String getMinUserPicturePath(){
		return this.minUserPicturePath;
	}

	public void setPoints(Long points){
		this.points = points;
	}

	public Long getPoints(){
		return this.points;
	}

	public void setExp(Long exp){
		this.exp = exp;
	}

	public Long getExp(){
		return this.exp;
	}

	public void setVipLevel(String vipLevel){
		this.vipLevel = vipLevel;
	}

	public String getVipLevel(){
		return this.vipLevel;
	}

}
