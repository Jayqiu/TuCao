package com.threehalf.tucao.activity;

import com.threehalf.tucao.R;
import com.threehalf.tucao.activity.register.ActRegister;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActLogin extends BaseFragmentActivity implements OnClickListener {
	private Button mBtnRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_login);
		initViews();
		initEvents();

	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		mBtnRegister = (Button) findViewById(R.id.btn_register);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		mBtnRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_register:
			startActivity(ActRegister.class, null);
			break;

		default:
			break;
		}

	}

}
