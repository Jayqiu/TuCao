package com.threehalf.tucao.activity.register;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.threehalf.tucao.R;
import com.threehalf.tucao.activity.BaseFragmentActivity;
import com.threehalf.tucao.activity.register.RegisterStep.onNextActionListener;
import com.threehalf.tucao.application.BaseApplication;
import com.threehalf.tucao.util.FileUtils;
import com.threehalf.tucao.util.PhotoUtils;
import com.threehalf.tucao.view.baseview.BaseDialog;

public class ActRegister extends BaseFragmentActivity implements
		OnClickListener, onNextActionListener {

	// private HeaderLayout mHeaderLayout;
	private ViewFlipper mVfFlipper;
	private Button mBtnPrevious;
	private Button mBtnNext;

	private BaseDialog mBackDialog;
	private RegisterStep mCurrentStep;
	private StepPhone mStepPhone;
	private StepVerify mStepVerify;
	private StepSetPassword mStepSetPassword;
	private StepBaseInfo mStepBaseInfo;
	private StepBirthday mStepBirthday;
	// private StepPhoto mStepPhoto;

	private int mCurrentStepIndex = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_register);
		initViews();
		mCurrentStep = initStep(null);
		initEvents();
		initBackDialog();
	}

	@Override
	protected void onDestroy() {
		// PhotoUtils.deleteImageFile();
		super.onDestroy();
	}

	@Override
	protected void initViews() {
		// mHeaderLayout = (HeaderLayout) findViewById(R.id.reg_header);
		// mHeaderLayout.init(HeaderStyle.TITLE_RIGHT_TEXT);
		mVfFlipper = (ViewFlipper) findViewById(R.id.reg_vf_viewflipper);
		mVfFlipper.setDisplayedChild(0);
		mBtnPrevious = (Button) findViewById(R.id.reg_btn_previous);
		mBtnNext = (Button) findViewById(R.id.reg_btn_next);
	}

	@Override
	protected void initEvents() {
		mCurrentStep.setOnNextActionListener(this);
		mBtnPrevious.setOnClickListener(this);
		mBtnNext.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		if (mCurrentStepIndex <= 1) {
			// mBackDialog.show();
		} else {
			doPrevious();
		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.reg_btn_previous:
			if (mCurrentStepIndex <= 1) {
				// mBackDialog.show();
			} else {
				doPrevious();
			}
			break;

		case R.id.reg_btn_next:
			if (mCurrentStepIndex < 5) {
				doNext();
			} else {
				if (mCurrentStep.validate()) {
					mCurrentStep.doNext();
				}
			}
			break;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case PhotoUtils.INTENT_REQUEST_CODE_ALBUM:
			if (data == null) {
				return;
			}
			if (resultCode == RESULT_OK) {
				if (data.getData() == null) {
					return;
				}
				if (!FileUtils.isSdcardExist()) {
					showCustomToast("SD卡不可用,请检查");
					return;
				}
				Uri uri = data.getData();
				String[] proj = { MediaStore.Images.Media.DATA };
				Cursor cursor = managedQuery(uri, proj, null, null, null);
				if (cursor != null) {
					int column_index = cursor
							.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					if (cursor.getCount() > 0 && cursor.moveToFirst()) {
						String path = cursor.getString(column_index);
						Bitmap bitmap = BitmapFactory.decodeFile(path);
						if (PhotoUtils.bitmapIsLarge(bitmap)) {
							// PhotoUtils.cropPhoto(this, this, path);
						} else {
							// mStepPhoto.setUserPhoto(bitmap);
						}
					}
				}
			}
			break;

		case PhotoUtils.INTENT_REQUEST_CODE_CAMERA:
			if (resultCode == RESULT_OK) {
				// // String path = mStepPhoto.getTakePicturePath();
				// Bitmap bitmap = BitmapFactory.decodeFile(path);
				// if (PhotoUtils.bitmapIsLarge(bitmap)) {
				// PhotoUtils.cropPhoto(this, this, path);
				// } else {
				// mStepPhoto.setUserPhoto(bitmap);
				// }
			}
			break;

		case PhotoUtils.INTENT_REQUEST_CODE_CROP:
			if (resultCode == RESULT_OK) {
				String path = data.getStringExtra("path");
				if (path != null) {
					Bitmap bitmap = BitmapFactory.decodeFile(path);
					if (bitmap != null) {
						// mStepPhoto.setUserPhoto(bitmap);
					}
				}
			}
			break;
		}
	}

	@Override
	public void next(Object object) {
		mCurrentStepIndex++;
		mCurrentStep = initStep(object);
		mCurrentStep.setOnNextActionListener(this);
		mVfFlipper.setInAnimation(this, R.anim.push_left_in);
		mVfFlipper.setOutAnimation(this, R.anim.push_left_out);
		mVfFlipper.showNext();
	}

	private RegisterStep initStep(Object object) {
		switch (mCurrentStepIndex) {
		case 1:

			if (mStepPhone == null ) {
				mStepPhone = new StepPhone(this, mVfFlipper.getChildAt(0));
			}
			// mHeaderLayout.setTitleRightText("注册新账号", null, "1/6");
			mBtnPrevious.setText("返    回");
			mBtnNext.setText("下一步");
			return mStepPhone;

		case 2:
			if (mStepVerify == null) {
				mStepVerify = new StepVerify(this, mVfFlipper.getChildAt(1),
						object);
			}
			// mHeaderLayout.setTitleRightText("填写验证码", null, "2/6");
			mBtnPrevious.setText("上一步");
			mBtnNext.setText("下一步");
			return mStepVerify;

		case 3:
			if (mStepSetPassword == null) {
				mStepSetPassword = new StepSetPassword(this,
						mVfFlipper.getChildAt(2), object);
			}
			mBtnPrevious.setText("上一步");
			mBtnNext.setText("下一步");
			return mStepSetPassword;

		case 4:
			if (mStepBaseInfo == null) {
				mStepBaseInfo = new StepBaseInfo(this,
						mVfFlipper.getChildAt(3), object);
			}
			mBtnPrevious.setText("上一步");
			mBtnNext.setText("下一步");
			return mStepBaseInfo;

		case 5:
			if (mStepBirthday == null) {
				mStepBirthday = new StepBirthday(this,
						mVfFlipper.getChildAt(4), object);
			}
			mBtnPrevious.setText("上一步");
			mBtnNext.setText("注    册");
			return mStepBirthday;
			//
			// case 6:
			// if (mStepPhoto == null) {
			// mStepPhoto = new StepPhoto(this, mVfFlipper.getChildAt(5));
			// }
			// mHeaderLayout.setTitleRightText("设置头像", null, "6/6");
			// mBtnPrevious.setText("上一步");
			// mBtnNext.setText("注    册");
			// return mStepPhoto;
		}
		return null;
	}

	private void doPrevious() {
		mCurrentStepIndex--;
		mCurrentStep = initStep(null);
		mCurrentStep.setOnNextActionListener(this);
		mVfFlipper.setInAnimation(this, R.anim.push_right_in);
		mVfFlipper.setOutAnimation(this, R.anim.push_right_out);
		mVfFlipper.showPrevious();
	}

	private void doNext() {
		if (mCurrentStep.validate()) {
			if (mCurrentStep.isChange()) {
				mCurrentStep.doNext();
			} else {
				next(null);
			}
		}
	}

	private void initBackDialog() {
		// mBackDialog = BaseDialog.getDialog(RegisterActivity.this, "提示",
		// "确认要放弃注册么?", "确认", new DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// dialog.dismiss();
		// finish();
		// }
		// }, "取消", new DialogInterface.OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// dialog.cancel();
		// }
		// });
		// mBackDialog.setButton1Background(R.drawable.btn_default_popsubmit);

	}

	// @Override
	// protected void putAsyncTask(AsyncTask<Void, Void, Boolean> asyncTask) {
	// super.putAsyncTask(asyncTask);
	// }

	@Override
	protected void showCustomToast(String text) {
		super.showCustomToast(text);
	}

	@Override
	protected void showCustomToast(int resId) {
		super.showCustomToast(resId);
	}

	@Override
	protected void showLoadingDialog(String text) {
		super.showLoadingDialog(text);
	}

	@Override
	protected void dismissLoadingDialog() {
		super.dismissLoadingDialog();
	}

	protected int getScreenWidth() {
		return mScreenWidth;
	}

	protected BaseApplication getBaseApplication() {
		return mApplication;
	}

	protected String getPhoneNumber() {
		if (mStepPhone != null) {
			return mStepPhone.getPhoneNumber();
		}
		return "";
	}

}
