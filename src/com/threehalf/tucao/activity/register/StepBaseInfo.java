package com.threehalf.tucao.activity.register;

import java.util.HashMap;
import java.util.Map;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.threehalf.tucao.R;

public class StepBaseInfo extends RegisterStep implements TextWatcher,
		OnCheckedChangeListener {

	private EditText mEtName;
	private RadioGroup mRgGender;
	private RadioButton mRbMale;
	private RadioButton mRbFemale;

	private boolean mIsChange = true;
	private boolean mIsGenderAlert;
	private String phone;
	private String country;
	private String pwd;
	private HashMap<String, Object> data;
	private String sex;

	// private BaseDialog mBaseDialog;

	@SuppressWarnings("unchecked")
	public StepBaseInfo(ActRegister activity, View contentRootView,
			Object object) {
		super(activity, contentRootView);
		if (object != null) {
			data = (HashMap<String, Object>) object;
			phone = data.get("phone").toString();
			country = data.get("country").toString();
			pwd = data.get("pwd").toString();
		}
	}

	@Override
	public void initViews() {
		mEtName = (EditText) findViewById(R.id.reg_baseinfo_et_name);
		mRgGender = (RadioGroup) findViewById(R.id.reg_baseinfo_rg_gender);
		mRbMale = (RadioButton) findViewById(R.id.reg_baseinfo_rb_male);
		mRbFemale = (RadioButton) findViewById(R.id.reg_baseinfo_rb_female);
	}

	@Override
	public void initEvents() {
		mEtName.addTextChangedListener(this);
		mRgGender.setOnCheckedChangeListener(this);
	}

	@Override
	public void doNext() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("country", country);
		map.put("pwd", pwd);
		map.put("name", mEtName.getText().toString().trim());
		map.put("sex", sex);
		mIsChange = false;
		mOnNextActionListener.next(map);
	}

	@Override
	public boolean validate() {
		if (isNull(mEtName)) {
			showCustomToast("请输入用户名");
			mEtName.requestFocus();
			return false;
		}
		if (mRgGender.getCheckedRadioButtonId() < 0) {
			showCustomToast("请选择性别");
			return false;
		}
		return true;
	}

	@Override
	public boolean isChange() {
		return mIsChange;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		mIsChange = true;
		if (!mIsGenderAlert) {
			mIsGenderAlert = true;
			// mBaseDialog = BaseDialog.getDialog(mContext, "提示",
			// "注册成功后性别将不可更改",
			// "确认", new DialogInterface.OnClickListener() {
			//
			// @Override
			// public void onClick(DialogInterface dialog, int which) {
			// dialog.dismiss();
			// }
			// });
			// mBaseDialog.show();
		}
		switch (checkedId) {
		case R.id.reg_baseinfo_rb_male:
			mRbMale.setChecked(true);
			sex = "1";
			break;

		case R.id.reg_baseinfo_rb_female:
			mRbFemale.setChecked(true);
			sex = "0";
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

}
