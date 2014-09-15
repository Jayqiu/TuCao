package com.threehalf.tucao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.threehalf.tucao.R;
import com.threehalf.tucao.adapter.MainFragmentAdapter;
import com.threehalf.tucao.view.pageindicator.TabPageIndicator;

public class ActMain extends BaseFragmentActivity {
	private MainFragmentAdapter adapter;
	android.support.v4.app.FragmentManager mFragmentManager;
	android.support.v4.app.FragmentTransaction mFragmentTransaction;
	private MenuInflater menuInflater;
	private Menu mMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		this.initViews();
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		adapter = new MainFragmentAdapter(getSupportFragmentManager(),
				ActMain.this, this);
		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.main_indicator);
		ViewPager pager = (ViewPager) findViewById(R.id.main_pager);
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(3);
		indicator.setViewPager(pager);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.mMenu = menu;
		menuInflater = getSupportMenuInflater();
		menuInflater.inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_edit:
			 Intent intent = new Intent(ActMain.this,
			 ActTuCao.class);
			 startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);

	}

}
