package com.threehalf.tucao.activity.register;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.threehalf.tucao.R;
import com.threehalf.tucao.httpclient.LoadDatahandler;
import com.threehalf.tucao.httpclient.MyAsyncHttpResponseHandler;
import com.threehalf.tucao.httpclient.MyHttpClient;
import com.threehalf.tucao.util.DateUtils;
import com.threehalf.tucao.util.TextUtils;
import com.threehalf.tucao.util.UrlConstant;

public class StepBirthday extends RegisterStep implements OnDateChangedListener {

	private TextView mHtvConstellation;
	private TextView mHtvAge;
	private DatePicker mDpBirthday;

	private Calendar mCalendar;
	private Date mMinDate;
	private Date mMaxDate;
	private Date mSelectDate;
	private static final int MAX_AGE = 100;
	private static final int MIN_AGE = 12;
	private String phone;
	private String country;
	private String pwd;
	private HashMap<String, Object> data;
	private String sex;
	private String name;

	public StepBirthday(ActRegister activity, View contentRootView,
			Object object) {
		super(activity, contentRootView);
		initData();
		if (object != null) {
			data = (HashMap<String, Object>) object;
			phone = data.get("phone").toString();
			country = data.get("country").toString();
			pwd = data.get("pwd").toString();
			name = data.get("name").toString();
			sex = data.get("sex").toString();
		}

	}

	private void flushBirthday(Calendar calendar) {
		String constellation = TextUtils.getConstellation(
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		mSelectDate = calendar.getTime();
		mHtvConstellation.setText(constellation);
		int age = TextUtils.getAge(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		mHtvAge.setText(age + "");
	}

	private void initData() {
		mSelectDate = DateUtils.getDate("19900101");

		Calendar mMinCalendar = Calendar.getInstance();
		Calendar mMaxCalendar = Calendar.getInstance();

		mMinCalendar.set(Calendar.YEAR, mMinCalendar.get(Calendar.YEAR)
				- MIN_AGE);
		mMinDate = mMinCalendar.getTime();
		mMaxCalendar.set(Calendar.YEAR, mMaxCalendar.get(Calendar.YEAR)
				- MAX_AGE);
		mMaxDate = mMaxCalendar.getTime();

		mCalendar = Calendar.getInstance();
		mCalendar.setTime(mSelectDate);
		flushBirthday(mCalendar);
		mDpBirthday.init(mCalendar.get(Calendar.YEAR),
				mCalendar.get(Calendar.MONTH),
				mCalendar.get(Calendar.DAY_OF_MONTH), this);
	}

	@Override
	public void initViews() {
		mHtvConstellation = (TextView) findViewById(R.id.reg_birthday_htv_constellation);
		mHtvAge = (TextView) findViewById(R.id.reg_birthday_htv_age);
		mDpBirthday = (DatePicker) findViewById(R.id.reg_birthday_dp_birthday);
	}

	@Override
	public void initEvents() {

	}

	@Override
	public void doNext() {
		Log.i("Calendar", mCalendar.getTimeInMillis() + "");
		// mActivity.finish();
		userRegist();
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public boolean isChange() {
		return false;
	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		mCalendar = Calendar.getInstance();
		mCalendar.set(year, monthOfYear, dayOfMonth);
		if (mCalendar.getTime().after(mMinDate)
				|| mCalendar.getTime().before(mMaxDate)) {
			mCalendar.setTime(mSelectDate);
			mDpBirthday.init(mCalendar.get(Calendar.YEAR),
					mCalendar.get(Calendar.MONTH),
					mCalendar.get(Calendar.DAY_OF_MONTH), this);
		} else {
			flushBirthday(mCalendar);
		}
	}

	private void userRegist() {
		RequestParams requestParams = new RequestParams();
		requestParams.put("username", phone);
		requestParams.put("pwd", pwd);
		requestParams.put("name", name);
		requestParams.put("sex", sex);
		requestParams.put("birthyear",
				String.valueOf(mCalendar.get(Calendar.YEAR)));
		requestParams.put("birthmonth",
				String.valueOf(mCalendar.get(Calendar.MONTH) + 1));
		requestParams.put("birthday",
				String.valueOf(mCalendar.get(Calendar.DAY_OF_MONTH)));
		MyHttpClient.getInstance(mContext).post(
				UrlConstant.getHttpUrl(UrlConstant.url_user_regist),
				requestParams,
				new MyAsyncHttpResponseHandler(new LoadDatahandler() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
					}

					@Override
					public void onSuccess(String data) {
						// TODO Auto-generated method stub
						super.onSuccess(data);
					}

					@Override
					public void onFailure(String error, String message) {
						// TODO Auto-generated method stub
						super.onFailure(error, message);
					}
				}));

	}

}
