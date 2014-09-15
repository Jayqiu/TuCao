package com.threehalf.tucao.entity.ret;

import java.io.Serializable;
import java.util.ArrayList;

import com.threehalf.tucao.entity.NewesEntity;

/**
 * 最新
 * 
 * @author jayqiu
 * 
 */
public class RetNewesEntity implements Serializable {
	private int retCode;
	private String retMsg;
	private RetDate retDate;

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

	public RetDate getRetDate() {
		return retDate;
	}

	public void setRetDate(RetDate retDate) {
		this.retDate = retDate;
	}

	public class RetDate implements Serializable {
		private boolean isNextPage;
		private int totalPage;
		private ArrayList<NewesEntity> list;

	

		public boolean isNextPage() {
			return isNextPage;
		}

		public void setNextPage(boolean isNextPage) {
			this.isNextPage = isNextPage;
		}

		public int getTotalPage() {
			return totalPage;
		}

		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}

		public ArrayList<NewesEntity> getList() {
			return list;
		}

		public void setList(ArrayList<NewesEntity> list) {
			this.list = list;
		}

	}

}
