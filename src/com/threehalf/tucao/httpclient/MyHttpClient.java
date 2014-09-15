package com.threehalf.tucao.httpclient;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class MyHttpClient {
    
    private static AsyncHttpClient asyncHttpClient;
    
    public static AsyncHttpClient getInstance(Context context) {
        if (asyncHttpClient == null) {
            asyncHttpClient = new AsyncHttpClient();
            //            PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
            //            asyncHttpClient.setCookieStore(myCookieStore);
            asyncHttpClient.setTimeout(10000);
        }
        return asyncHttpClient;
    }
    
    public static void post(String url, MyAsyncHttpResponseHandler handler) {
        post(url, null, handler);
        
    }
    
    /** 
     * post 请求 
     *  
     * @param url 
     *            API 地址 
     * @param params 
     *            请求的参数 
     * @param handler 
     *            数据加载句柄对象 
     */
    public static void post(String url, RequestParams params, MyAsyncHttpResponseHandler handler) {
        System.out.println("进入post");
        asyncHttpClient.post(url, params, handler);
    }
    
}
