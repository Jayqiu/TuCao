package com.threehalf.tucao.activity;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.threehalf.tucao.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class ActTuCao extends BaseFragmentActivity implements TextWatcher {
	private EditText mEtContent;
	private TextView mTvTextNum;
	private final static int TEXT_SUM = 140;
	private MenuInflater menuInflater;
	private Menu mMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_tucao);
		initViews();
		initEvents();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		mEtContent = (EditText) findViewById(R.id.et_content);
		mTvTextNum = (TextView) findViewById(R.id.tv_textnum);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		mEtContent.addTextChangedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.mMenu = menu;
		menuInflater = getSupportMenuInflater();
		menuInflater.inflate(R.menu.tucao_menu, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_tucao:
			showCustomToast("吐槽");
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);

	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stu
		Log.i("ssss", s.length() + "");

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		int textNum = TEXT_SUM - mEtContent.length();
		Log.i("ssss", s.length() + "");
		if (textNum >= 0) {
			mTvTextNum.setText(mEtContent.length() + "/140");
		} else {
			mTvTextNum.setText(mEtContent.length() + "/140");
			showCustomToast("只能输入140字");
		}
	}

	private void tuCao() {

	}
}
