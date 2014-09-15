package com.threehalf.tucao.activity.newes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.RequestParams;
import com.threehalf.tucao.R;
import com.threehalf.tucao.activity.BaseFragment;
import com.threehalf.tucao.adapter.HottestAdapter;
import com.threehalf.tucao.adapter.NewesAdapter;
import com.threehalf.tucao.application.AppFileCache;
import com.threehalf.tucao.entity.NewesEntity;
import com.threehalf.tucao.entity.ret.RetNewesEntity;
import com.threehalf.tucao.httpclient.LoadDatahandler;
import com.threehalf.tucao.httpclient.MyAsyncHttpResponseHandler;
import com.threehalf.tucao.httpclient.MyHttpClient;
import com.threehalf.tucao.util.UrlConstant;
import com.threehalf.tucao.view.xlistview.XListView;
import com.threehalf.tucao.view.xlistview.XListView.IXListViewListener;

@SuppressLint("ValidFragment")
public class NewestFragment extends BaseFragment implements IXListViewListener {
	private XListView mXListView;
	private Context mContext;
	private ArrayList<NewesEntity> newesEntityList;
	private boolean isNextPage = false;
	private NewesAdapter newesAdapter;
	private static NewestFragment newestFragment;
	private int page = 1;
	private Handler mHandler;

	public NewestFragment(Activity activity, Context context) {
		super(activity, context);
	}

	public static NewestFragment newInstance(Activity activity, Context context) {
		if (newestFragment == null) {
			newestFragment = new NewestFragment(activity, context);
		}

		return newestFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.mContext = getActivity();
		mView = inflater.inflate(R.layout.fragment_newest, null);
		this.initViews();
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		mHandler = new Handler();
		mXListView = (XListView) findViewById(R.id.xlv_home_newest);
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		mXListView.setPullLoadEnable(true);
		mXListView.setPullRefreshEnable(true);
		mXListView.setXListViewListener(this);
	}

	@Override
	protected void initData() {

		String cacheData = AppFileCache.getInstance(mContext).getNewsToCao();
		Log.e("cacheData",cacheData);
		if (cacheData != null && cacheData.length() > 0) {

			try {
				RetNewesEntity newesEntity = JSON.parseObject(cacheData,
						RetNewesEntity.class);
				if (newesEntity.getRetCode() == 1) {
					newesEntityList = newesEntity.getRetDate().getList();
					isNextPage = newesEntity.getRetDate().isNextPage();
					if (newesEntityList != null && newesEntityList.size() > 0) {
						newesAdapter = new NewesAdapter(mContext,
								newesEntityList);
						mXListView.setAdapter(newesAdapter);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("Exception", "cacheData数据解析出错");
			}
		} else {
			getData(page);
		}

	}

	private void getData(final int page) {
		RequestParams requestParams = new RequestParams();
		requestParams.put("page", page + "");
		MyHttpClient.getInstance(mContext).post(
				UrlConstant.getHttpUrl(UrlConstant.url_tucao_newset_list),
				requestParams,
				new MyAsyncHttpResponseHandler(new LoadDatahandler() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
					}

					@Override
					public void onSuccess(String content) {
						// TODO Auto-generated method stub
						super.onSuccess(content);
						try {
							RetNewesEntity newesEntity = JSON.parseObject(
									content, RetNewesEntity.class);
							if (page == 1) {

								if (newesEntity.getRetCode() == 1) {
									AppFileCache.getInstance(mContext)
											.saveNewsTuCao(content);
									newesEntityList = newesEntity.getRetDate()
											.getList();
									isNextPage = newesEntity.getRetDate()
											.isNextPage();
									if (newesEntityList != null
											&& newesEntityList.size() > 0) {
										newesAdapter = new NewesAdapter(
												mContext, newesEntityList);
										mXListView.setAdapter(newesAdapter);
									}
								}

							} else {
								if (newesEntity.getRetCode() == 1) {
									ArrayList<NewesEntity> newesEntityList = newesEntity
											.getRetDate().getList();
									isNextPage = newesEntity.getRetDate()
											.isNextPage();
									if (newesEntityList != null
											&& newesEntityList.size() > 0) {
										NewestFragment.this.newesEntityList
												.addAll(newesEntityList);
										newesAdapter.notifyDataSetChanged();
									}
								}

							}
						} catch (Exception e) {
							showCustomToast("数据解析出错");
						}
					}

					@Override
					public void onFailure(String error, String message) {
						// TODO Auto-generated method stub
						super.onFailure(error, message);
						Toast.makeText(mContext, message, Toast.LENGTH_SHORT)
								.show();
					}

				}));
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			public void run() {
				page++;
				getData(page);
				onLoad();
			}

		}, 2000);
	}

	private void onLoad() {
		if (!isNextPage) {
			mXListView.setPullLoadEnable(false);
		} else {
			mXListView.setPullLoadEnable(true);
		}
		mXListView.stopRefresh();
		mXListView.stopLoadMore();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String date = sdf.format(new java.util.Date());
		mXListView.setRefreshTime(date);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
