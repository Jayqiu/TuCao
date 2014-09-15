package com.threehalf.tucao.activity.hottest;

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
import com.threehalf.tucao.activity.newes.NewestFragment;
import com.threehalf.tucao.adapter.HottestAdapter;
import com.threehalf.tucao.application.AppFileCache;
import com.threehalf.tucao.entity.NewesEntity;
import com.threehalf.tucao.entity.ret.RetNewesEntity;
import com.threehalf.tucao.httpclient.LoadDatahandler;
import com.threehalf.tucao.httpclient.MyAsyncHttpResponseHandler;
import com.threehalf.tucao.httpclient.MyHttpClient;
import com.threehalf.tucao.util.UrlConstant;
import com.threehalf.tucao.view.xlistview.XListView;
import com.threehalf.tucao.view.xlistview.XListView.IXListViewListener;

/**
 * 
 * 
 * @author jayqiu
 * 
 */
@SuppressLint("ValidFragment")
public class HottestFragment extends BaseFragment implements IXListViewListener {

	private static HottestFragment foundFragment;
	private XListView mXListView;
	private ArrayList<NewesEntity> newesEntityList;
	private boolean isNextPage = false;
	private HottestAdapter hottestAdapter;
	private int page = 1;
	private Handler mHandler;

	public HottestFragment(Activity activity, Context context) {
		super(activity, context);
	}

	public static HottestFragment newInstance(Activity activity, Context context) {
		if (foundFragment == null) {
			foundFragment = new HottestFragment(activity, context);
		}
		return foundFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_hottest, container, false);
		Log.i("HottestFragment", "onCreateView");

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.i("HottestFragment", "onDestroyView");
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub
		mHandler = new Handler();
		mXListView = (XListView) findViewById(R.id.xlv_hottest_list);

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

		String cacheData = AppFileCache.getInstance(mContext).getHottesToCao();
		Log.e("cacheData", cacheData);
		showCustomToast("hottestCacheData" + cacheData);
		if (cacheData != null && cacheData.length() > 0) {

			try {
				RetNewesEntity newesEntity = JSON.parseObject(cacheData,
						RetNewesEntity.class);
				if (newesEntity.getRetCode() == 1) {
					newesEntityList = newesEntity.getRetDate().getList();
					isNextPage = newesEntity.getRetDate().isNextPage();
					if (newesEntityList != null && newesEntityList.size() > 0) {
						hottestAdapter = new HottestAdapter(mContext,
								newesEntityList);
						mXListView.setAdapter(hottestAdapter);
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
		Log.i("url", UrlConstant.getHttpUrl(UrlConstant.url_tucao_hottest_list)
				+ requestParams.toString());
		MyHttpClient.getInstance(mContext).post(
				UrlConstant.getHttpUrl(UrlConstant.url_tucao_hottest_list),
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
											.saveHottesTuCao(content);
									newesEntityList = newesEntity.getRetDate()
											.getList();
									isNextPage = newesEntity.getRetDate()
											.isNextPage();
									if (newesEntityList != null
											&& newesEntityList.size() > 0) {
										hottestAdapter = new HottestAdapter(
												mContext, newesEntityList);
										mXListView.setAdapter(hottestAdapter);
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
										HottestFragment.this.newesEntityList
												.addAll(newesEntityList);
										hottestAdapter.notifyDataSetChanged();
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
