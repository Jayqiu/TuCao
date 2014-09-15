package com.threehalf.tucao.activity.register;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.RequestParams;
import com.threehalf.tucao.R;
import com.threehalf.tucao.entity.ret.RetObjEntity;
import com.threehalf.tucao.httpclient.LoadDatahandler;
import com.threehalf.tucao.httpclient.MyAsyncHttpResponseHandler;
import com.threehalf.tucao.httpclient.MyHttpClient;
import com.threehalf.tucao.util.CodeUtil;
import com.threehalf.tucao.util.ConstantUtil;
import com.threehalf.tucao.util.UrlConstant;

public class StepPhone extends RegisterStep implements OnClickListener,
		TextWatcher {

	private TextView mHtvAreaCode;
	private EditText mEtPhone;
	private TextView mHtvNotice;
	private TextView mHtvNote;

	private String mAreaCode = "86";
	// private SimpleListDialog mSimpleListDialog;
	private String[] mCountryCodes;

	private static final String DEFAULT_PHONE = "+8612345678901";
	private String mPhone;
	private boolean mIsChange = true;
	private boolean ready;
	private Handler handler;
	private String oldPhone;
	private boolean frequency = true;

	// private WebDialog mWebDialog;

	public StepPhone(ActRegister activity, View contentRootView) {
		super(activity, contentRootView);
	}

	public String getPhoneNumber() {
		return "(+" + mAreaCode + ")" + mPhone;
	}

	@Override
	public void initViews() {
		// initEventHandler();
		initHandler();
		initSDK();

		mHtvAreaCode = (TextView) findViewById(R.id.reg_phone_htv_areacode);
		mEtPhone = (EditText) findViewById(R.id.reg_phone_et_phone);
		mHtvNotice = (TextView) findViewById(R.id.reg_phone_htv_notice);
		mHtvNote = (TextView) findViewById(R.id.reg_phone_htv_note);
		// TextUtils.addHyperlinks(mHtvNote, 8, 15, this);
	}

	@Override
	public void initEvents() {
		mHtvAreaCode.setOnClickListener(this);
		mEtPhone.addTextChangedListener(this);
	}

	@Override
	public void doNext() {
		Map<String, Object> map = new HashMap<String, Object>();
		oldPhone = mPhone;
		map.put("phone", mPhone);
		map.put("country", mAreaCode);
		mIsChange = false;
		mOnNextActionListener.next(map);
		// geterificationCode();
		//
		// if (oldPhone.equals(mPhone)) {
		// Map<String, Object> map = new HashMap<String, Object>();
		// oldPhone = mPhone;
		// map.put("phone", mPhone);
		// map.put("country", mAreaCode);
		// mIsChange = true;
		// mOnNextActionListener.next(map);
		// } else {
		// geterificationCode();
		// }
	}

	private void geterificationCode() {
		RequestParams params = new RequestParams();
		params.put("username", mEtPhone.getText().toString().trim());
		MyHttpClient.getInstance(mContext).post(
				UrlConstant.getHttpUrl(UrlConstant.url_isregist), params,
				new MyAsyncHttpResponseHandler(new LoadDatahandler() {
					@Override
					public void onStart() {
						showLoadingDialog("正在验证手机号,请稍后...");
						super.onStart();

					}

					@Override
					public void onSuccess(String data) {
						// TODO Auto-generated method stub
						super.onSuccess(data);
						dismissLoadingDialog();
						RetObjEntity objEntity = JSON.parseObject(data,
								RetObjEntity.class);
						if (objEntity.getRetCode() == CodeUtil.SUCCES_NOT_REGIST) {
							Map<String, Object> map = new HashMap<String, Object>();
							oldPhone = mPhone;
							map.put("phone", mPhone);
							map.put("country", mAreaCode);
							mIsChange = false;
							mOnNextActionListener.next(map);
							// SMSSDK.getVerificationCode(mAreaCode,
							// mPhone);
						} else {
							showCustomToast(objEntity.getRetMsg());
						}
					}

					@Override
					public void onFailure(String error, String message) {
						// TODO Auto-generated method stub
						super.onFailure(error, message);
						dismissLoadingDialog();
					}
				}));

	}

	@Override
	public boolean validate() {
		mPhone = null;
		if (isNull(mEtPhone)) {
			showCustomToast("请填写手机号码");
			mEtPhone.requestFocus();
			return false;
		}
		String phone = mEtPhone.getText().toString().trim();
		if (matchPhone("+" + mAreaCode + phone)) {
			mPhone = phone;
			return true;
		}
		showCustomToast("手机号码格式不正确");
		mEtPhone.requestFocus();
		return false;
	}

	@Override
	public boolean isChange() {
		// if (frequency) {
		// oldPhone = mPhone;
		// mIsChange = true;
		// frequency = false;
		// } else {
		// if (oldPhone.equals(mPhone)) {
		// mIsChange = false;
		// } else {
		// mIsChange = true;
		// }
		// oldPhone = mPhone;
		// }

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
		if (s.toString().length() > 0) {
			mHtvNotice.setVisibility(View.VISIBLE);
			char[] chars = s.toString().toCharArray();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < chars.length; i++) {
				if (i % 4 == 2) {
					buffer.append(chars[i] + "  ");
				} else {
					buffer.append(chars[i]);
				}
			}
			mHtvNotice.setText(buffer.toString());
		} else {
			mHtvNotice.setVisibility(View.GONE);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.reg_phone_htv_areacode:

			break;

		default:
			// mWebDialog = new WebDialog(mContext);
			// mWebDialog.init("用户协议", "确认",
			// new DialogInterface.OnClickListener() {
			//
			// @Override
			// public void onClick(DialogInterface dialog, int which) {
			// mWebDialog.dismiss();
			// }
			// });
			// mWebDialog.setOnWebDialogErrorListener(StepPhone.this);
			// mWebDialog
			// .loadUrl(JniManager.getInstance().getAgreementDialogUrl());
			// mWebDialog.show();
			break;
		}
	}

	// @Override
	// public void onItemClick(int position) {
	// String text = TextUtils.getCountryCodeBracketsInfo(
	// mCountryCodes[position], mAreaCode);
	// mAreaCode = text;
	// mHtvAreaCode.setText(text);
	// }
	private void initSDK() {
		// 初始化短信SDK
		SMSSDK.initSDK(mContext, ConstantUtil.MSM_APPKEY,
				ConstantUtil.MSM_APPSECRET);
		EventHandler eventHandler = new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handler.sendMessage(msg);
			}
		};
		// 注册回调监听接口
		SMSSDK.registerEventHandler(eventHandler);
	}

	private void initHandler() {
		handler = new Handler(mContext.getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				// mOnNextActionListener.next();
				int event = msg.arg1;
				int result = msg.arg2;
				Object data = msg.obj;
				if (result == SMSSDK.RESULT_COMPLETE) {
					if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
						// 请求支持国家列表
					} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
						// 请求验证码后，跳转到验证码填写页面
						Map<String, Object> map = new HashMap<String, Object>();
						oldPhone = mPhone;
						map.put("phone", mPhone);
						map.put("country", mAreaCode);
						mIsChange = false;
						mOnNextActionListener.next(map);
					}
				} else {
					((Throwable) data).printStackTrace();
					Throwable throwable = (Throwable) data;
					Log.i("data", data + "");
					try {
						JSONObject object = new JSONObject(
								throwable.getMessage());
						//
						String des = object.optString("status");
						if (des != null && des.length() > 0) {
							int resId = cn.smssdk.framework.utils.R
									.getStringRes(mContext,
											"smssdk_error_detail_" + des);
							showCustomToast(resId);
							return;
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
			}
		};
	}
}
