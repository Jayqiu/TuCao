package com.threehalf.tucao.entity.ret;

import java.io.Serializable;

public class RetObjEntity implements Serializable {
	private Object retDate;
	private int retCode;
	private String retMsg;

	public Object getRetDate() {
		return retDate;
	}

	public void setRetDate(Object retDate) {
		this.retDate = retDate;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

}
