package com.threehalf.tucao.util;

public class UrlConstant {

	private final static String serverHttpUrl = "http://192.168.252.1:8081/WalkingService";
	public final static String url_tucao_newset_list = "/tucao/getnewsttucao";
	public final static String url_tucao_hottest_list = "/tucao/gethottesttucao";
	public final static String url_userlogin_url = "/user/userlogin";
	public final static String url_userlogin = "/user/userlogin";
	public final static String url_mypublish = "/user/mypublish";
	public final static String url_isregist = "/user/isregist";
	public final static String url_user_regist = "/user/userregist";

	public static String getHttpUrl(String url) {
		return serverHttpUrl + url;
	}

}
