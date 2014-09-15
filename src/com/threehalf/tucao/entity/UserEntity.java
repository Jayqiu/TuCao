package com.threehalf.tucao.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {

	private Long userId;
	private String username;
	private String plainPassword;
	private String encryptedPassword;
	private String name;
	private String email;
	private String creationDate;
	private String modificationDate;
	private String avatarImg;
	private Integer sex;
	private Short birthyear;
	private Short birthmonth;
	private Short birthday;
	private String signature;
	private int tucaoNum;
	private int tucaoGrade;
	private int tucaoComments;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getAvatarImg() {
		return avatarImg;
	}

	public void setAvatarImg(String avatarImg) {
		this.avatarImg = avatarImg;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Short getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(Short birthyear) {
		this.birthyear = birthyear;
	}

	public Short getBirthmonth() {
		return birthmonth;
	}

	public void setBirthmonth(Short birthmonth) {
		this.birthmonth = birthmonth;
	}

	public Short getBirthday() {
		return birthday;
	}

	public void setBirthday(Short birthday) {
		this.birthday = birthday;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getTucaoNum() {
		return tucaoNum;
	}

	public void setTucaoNum(int tucaoNum) {
		this.tucaoNum = tucaoNum;
	}

	public int getTucaoGrade() {
		return tucaoGrade;
	}

	public void setTucaoGrade(int tucaoGrade) {
		this.tucaoGrade = tucaoGrade;
	}

	public int getTucaoComments() {
		return tucaoComments;
	}

	public void setTucaoComments(int tucaoComments) {
		this.tucaoComments = tucaoComments;
	}

}
