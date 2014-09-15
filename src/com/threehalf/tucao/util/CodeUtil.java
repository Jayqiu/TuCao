package com.threehalf.tucao.util;

public class CodeUtil {
	// 成功
	public static final int SUCCES_USER_LOGINS = 10001;
	public static final int SUCCES_PUBLISH_TUCAO = 10002;
	public static final int SUCCES_NOT_REGIST = 10003;
	public static final int SUCCES_REGIST = 10004;
	//失败
	public static final int FAILURE_LOGING_NOT_USER= 20001;// 用户不存在
	public static final int FAILURE_LOGING_PWD_ERROR = 20002;
	public static final int FAILURE_PUBLISH_TOCAO= 20003;
	public static final int FAILURE_TUCAO_NUM = 20004;
	public static final int FAILURE_REGIST_USER_EXISTS = 20005;//注册失败，用户已经存在
	public static final int FAILURE_REGIST= 20006;//注册失败
}
