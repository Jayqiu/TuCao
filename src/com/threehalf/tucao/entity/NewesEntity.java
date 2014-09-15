package com.threehalf.tucao.entity;

public class NewesEntity {
	private long commentnum;
	private long userID;
	private int sex;
	private String username;
	private String time;
	private String avatarImg;
	private long tucaoID;
	private int checkstatus;
	private String name;
	private String tucaocontent;
	private String tucaoimg;
	private int isanonymous;
	private int praisenmu;

	public long getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(long commentnum) {
		this.commentnum = commentnum;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAvatarImg() {
		return avatarImg;
	}

	public void setAvatarImg(String avatarImg) {
		this.avatarImg = avatarImg;
	}

	public long getTucaoID() {
		return tucaoID;
	}

	public void setTucaoID(long tucaoID) {
		this.tucaoID = tucaoID;
	}

	public int getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(int checkstatus) {
		this.checkstatus = checkstatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTucaocontent() {
		return tucaocontent;
	}

	public void setTucaocontent(String tucaocontent) {
		this.tucaocontent = tucaocontent;
	}

	public String getTucaoimg() {
		return tucaoimg;
	}

	public void setTucaoimg(String tucaoimg) {
		this.tucaoimg = tucaoimg;
	}

	public int getIsanonymous() {
		return isanonymous;
	}

	public void setIsanonymous(int isanonymous) {
		this.isanonymous = isanonymous;
	}

	public int getPraisenmu() {
		return praisenmu;
	}

	public void setPraisenmu(int praisenmu) {
		this.praisenmu = praisenmu;
	}

}
