package com.threehalf.tucao.activity.register;

import java.util.HashMap;
import java.util.Map;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.RequestParams;
import com.threehalf.tucao.R;
import com.threehalf.tucao.entity.ret.RetObjEntity;
import com.threehalf.tucao.httpclient.LoadDatahandler;
import com.threehalf.tucao.httpclient.MyAsyncHttpResponseHandler;
import com.threehalf.tucao.httpclient.MyHttpClient;
import com.threehalf.tucao.util.CodeUtil;
import com.threehalf.tucao.util.UrlConstant;

public class StepSetPassword extends RegisterStep implements TextWatcher {

	private EditText mEtPwd;
	private EditText mEtRePwd;

	private boolean mIsChange = true;
	private String phone;
	private String country;
	private HashMap<String, Object> data;

	@SuppressWarnings("unchecked")
	public StepSetPassword(ActRegister activity, View contentRootView,
			Object object) {
		super(activity, contentRootView);
		if (object != null) {
			data = (HashMap<String, Object>) object;
			phone = data.get("phone").toString();
			country = data.get("country").toString();
		}
	}

	@Override
	public void initViews() {
		mEtPwd = (EditText) findViewById(R.id.reg_setpwd_et_pwd);
		mEtRePwd = (EditText) findViewById(R.id.reg_setpwd_et_repwd);
	}

	@Override
	public void initEvents() {
		mEtPwd.addTextChangedListener(this);
		mEtRePwd.addTextChangedListener(this);
	}

	@Override
	public void doNext() {
		mIsChange = false;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("country", country);
		map.put("pwd", mEtRePwd.getText().toString().trim());
		mOnNextActionListener.next(map);
		/*RequestParams params = new RequestParams();
		params.put("username", phone);
		params.put("pwd", mEtRePwd.getText().toString().trim());
		MyHttpClient.getInstance(mContext).post(
				UrlConstant.getHttpUrl(UrlConstant.url_user_regist),
				new MyAsyncHttpResponseHandler(new LoadDatahandler() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						showLoadingDialog("正在提交数据...");
					}

					@Override
					public void onSuccess(String data) {
						// TODO Auto-generated method stub
						super.onSuccess(data);
						dismissLoadingDialog();
						try {
							RetObjEntity retObjEntity = JSON.parseObject(data,
									RetObjEntity.class);

							if (retObjEntity.getRetCode() == CodeUtil.SUCCES_REGIST) {

							} else {
								showCustomToast(retObjEntity.getRetMsg());
							}

						} catch (Exception e) {
							// TODO: handle exception
						}
					}

					@Override
					public void onFailure(String error, String message) {
						// TODO Auto-generated method stub
						super.onFailure(error, message);
						dismissLoadingDialog();
					}
				}));*/

	}

	@Override
	public boolean validate() {
		String pwd = null;
		String rePwd = null;
		if (isNull(mEtPwd)) {
			showCustomToast("请输入密码");
			mEtPwd.requestFocus();
			return false;
		} else {
			pwd = mEtPwd.getText().toString().trim();
			if (pwd.length() < 6) {
				showCustomToast("密码不能小于6位");
				mEtPwd.requestFocus();
				return false;
			}
		}
		if (isNull(mEtRePwd)) {
			showCustomToast("请重复输入一次密码");
			mEtRePwd.requestFocus();
			return false;
		} else {
			rePwd = mEtRePwd.getText().toString().trim();
			if (!pwd.equals(rePwd)) {
				showCustomToast("两次输入的密码不一致");
				mEtRePwd.requestFocus();
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isChange() {
		return mIsChange;
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		mIsChange = true;
	}

}
