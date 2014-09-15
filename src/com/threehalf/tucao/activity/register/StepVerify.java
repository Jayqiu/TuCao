package com.threehalf.tucao.activity.register;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.threehalf.tucao.R;
import com.threehalf.tucao.util.ConstantUtil;
import com.threehalf.tucao.view.baseview.BaseDialog;

public class StepVerify extends RegisterStep implements OnClickListener,
		TextWatcher {

	private TextView mHtvPhoneNumber;
	private EditText mEtVerifyCode;
	private Button mBtnResend;
	private TextView mHtvNoCode;

	private static final String PROMPT = "验证码已经发送到* ";
	private static final String DEFAULT_VALIDATE_CODE = "852369";

	private boolean mIsChange = true;
	private String mVerifyCode;

	private int mReSendTime = 60;
	private BaseDialog mBaseDialog;
	private Handler handler;
	private Handler sendMsgHandler;
	private String phone;
	private String country;
	private HashMap<String, Object> data;
	private TimeCount time;

	@SuppressWarnings("unchecked")
	public StepVerify(ActRegister activity, View contentRootView, Object object) {
		super(activity, contentRootView);

		if (object != null) {
			data = (HashMap<String, Object>) object;
			phone = data.get("phone").toString();
			country = data.get("country").toString();
		}

	}

	@Override
	public void initViews() {

		mHtvPhoneNumber = (TextView) findViewById(R.id.reg_verify_htv_phonenumber);
		mHtvPhoneNumber.setText(PROMPT + getPhoneNumber());
		mEtVerifyCode = (EditText) findViewById(R.id.reg_verify_et_verifycode);
		mBtnResend = (Button) findViewById(R.id.reg_verify_btn_resend);
		mBtnResend.setEnabled(false);
		mBtnResend.setText("重发(60)");
		mHtvNoCode = (TextView) findViewById(R.id.reg_verify_htv_nocode);
		// TextUtils.addUnderlineText(mContext, mHtvNoCode, 0, mHtvNoCode
		// .getText().toString().length());
		// TextUtils.addHyperlinks(mHtvNoCode, 0, mHtvNoCode
		// .getText().toString().length(), new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// System.out.println("123");
		// }
		// });
		// initHandler();
		// handler.sendEmptyMessage(0);
		if (time == null) {
			time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
			time.start();
		}

	}

	@Override
	public void initEvents() {
		mBtnResend.setOnClickListener(this);
		mHtvNoCode.setOnClickListener(this);
		mEtVerifyCode.addTextChangedListener(this);
		initSendMsgHandler();
		initSDK();
	}

	@Override
	public void doNext() {
		Log.i("StepVerify", country + "====" + phone);
		showLoadingDialog("正在验证...");
		dismissLoadingDialog();
		mIsChange = false;
		mOnNextActionListener.next(data);
		// SMSSDK.submitVerificationCode(country, phone, mVerifyCode);

	}

	@Override
	public boolean validate() {
		if (isNull(mEtVerifyCode)) {
			showCustomToast("请输入验证码");
			mEtVerifyCode.requestFocus();
			return false;
		}
		mVerifyCode = mEtVerifyCode.getText().toString().trim();
		return true;
	}

	@Override
	public boolean isChange() {
		return mIsChange;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.reg_verify_btn_resend:
			SMSSDK.getVerificationCode(country, phone);
			// handler.sendEmptyMessage(0);
			time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
			time.start();
			break;

		case R.id.reg_verify_htv_nocode:
			showCustomToast("抱歉,暂时不支持此操作");
			break;
		}
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

	private void initHandler() {
		if (handler != null) {
			handler.removeMessages(0);
		}
		handler = new Handler(mContext.getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == 0) {
					if (mReSendTime > 1) {
						mReSendTime--;
						mBtnResend.setEnabled(false);
						mBtnResend.setText("重发(" + mReSendTime + ")");
						handler.sendEmptyMessageDelayed(0, 1000);
					} else {

						mReSendTime = 60;
						mBtnResend.setEnabled(true);
						mBtnResend.setText("重     发");
					}
				}

			}
		};

	}

	private class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
		}

		@Override
		public void onFinish() {// 计时完毕时触发
			mBtnResend.setEnabled(true);
			mBtnResend.setText("重     发");
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程显示
			mBtnResend.setEnabled(false);
			mBtnResend.setText("重发(" + millisUntilFinished / 1000 + ")");

		}
	}

	private void initSDK() {
		// 初始化短信SDK
		SMSSDK.initSDK(mContext, ConstantUtil.MSM_APPKEY,
				ConstantUtil.MSM_APPSECRET);
		EventHandler eventHandler = new EventHandler() {
			public void afterEvent(int event, int result, Object data) {
				dismissLoadingDialog();
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				sendMsgHandler.sendMessage(msg);
			}
		};
		// 注册回调监听接口
		SMSSDK.registerEventHandler(eventHandler);
	}

	private void initSendMsgHandler() {
		sendMsgHandler = new Handler(mContext.getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				// mOnNextActionListener.next();
				int event = msg.arg1;
				int result = msg.arg2;
				Object data = msg.obj;
				Log.i("data", data + "");
				if (result == SMSSDK.RESULT_COMPLETE) {
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("phone", phone);
						map.put("country", country);
						map.put("mVerifyCode", mVerifyCode);
						mIsChange = false;
						mOnNextActionListener.next(data);
					} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
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
											"smssdk_error_desc_" + des);
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
