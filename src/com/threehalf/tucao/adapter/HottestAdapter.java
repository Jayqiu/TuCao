package com.threehalf.tucao.adapter;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.threehalf.tucao.R;
import com.threehalf.tucao.entity.NewesEntity;
import com.threehalf.tucao.util.ConstantUtil;
import com.threehalf.tucao.util.UrlConstant;
import com.threehalf.tucao.view.CircleImageView;

/**
 * 最新
 * 
 * @author jayqiu
 * 
 */
public class HottestAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<NewesEntity> newesEntityList;
	DisplayImageOptions options;
	protected ImageLoader imageLoader;
	DisplayImageOptions avatarOptions;
	protected ImageLoader avatarImageLoader;

	public HottestAdapter(Context context,
			ArrayList<NewesEntity> newesEntityList) {
		this.mContext = context;
		this.newesEntityList = newesEntityList;
		File cacheDir = StorageUtils.getOwnCacheDirectory(
				context.getApplicationContext(), ConstantUtil.IMG_PATH);
		options = new DisplayImageOptions.Builder()
				// 正在加载图片
				.showImageOnLoading(R.color.home_img_bg_color)
				// url 地址为空
				.showImageForEmptyUri(R.color.home_img_bg_color)
				// 图片下载失败
				.showImageOnFail(R.color.home_img_bg_color).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20))// 图片圆角
				.build();
		imageLoader = ImageLoader.getInstance();
		@SuppressWarnings("deprecation")
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context.getApplicationContext())
				.discCache(new UnlimitedDiscCache(cacheDir))
				.defaultDisplayImageOptions(options).build();
		imageLoader.init(config);

		File avatarCacheDir = StorageUtils.getOwnCacheDirectory(
				context.getApplicationContext(), ConstantUtil.AVATAR_IMG_PATH);
		avatarOptions = new DisplayImageOptions.Builder()
				// 正在加载图片
				.showImageOnLoading(R.color.home_img_bg_color)
				// url 地址为空
				.showImageForEmptyUri(R.color.home_img_bg_color)
				// 图片下载失败
				.showImageOnFail(R.color.home_img_bg_color).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true).build();
		avatarImageLoader = ImageLoader.getInstance();
		@SuppressWarnings("deprecation")
		ImageLoaderConfiguration avatarConfig = new ImageLoaderConfiguration.Builder(
				context.getApplicationContext())
				.discCache(new UnlimitedDiscCache(avatarCacheDir))
				.defaultDisplayImageOptions(avatarOptions).build();
		avatarImageLoader.init(avatarConfig);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newesEntityList == null ? 0 : newesEntityList.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return newesEntityList == null ? 0 : newesEntityList.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_hottest_list, parent, false);
			viewHolder = new ViewHolder();

			viewHolder.mIvTocaoImg = (ImageView) convertView
					.findViewById(R.id.tv_item_tucaoimg);
			viewHolder.mTvCommentnum = (TextView) convertView
					.findViewById(R.id.tv_item_hotttest_commentnum);
			viewHolder.mTvUserName = (TextView) convertView
					.findViewById(R.id.tv_item_name);
			viewHolder.mTvContent = (TextView) convertView
					.findViewById(R.id.tv_item_tucaocontent);
			viewHolder.mTvPraisenum = (TextView) convertView
					.findViewById(R.id.tv_itme_hottest_praise_num);
			viewHolder.mIvAvatar = (CircleImageView) convertView
					.findViewById(R.id.iv_itme_hottest_avater);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		NewesEntity newesEntity = newesEntityList.get(position);

		viewHolder.mTvCommentnum.setText(newesEntity.getCommentnum() + " 吐槽");
		viewHolder.mTvUserName.setText(newesEntity.getName());
		viewHolder.mTvContent.setText(newesEntity.getTucaocontent());
		viewHolder.mTvPraisenum.setText(newesEntity.getPraisenmu() + " 赞");
		Log.i("url", UrlConstant.getHttpUrl(newesEntity.getTucaoimg()));
		imageLoader.displayImage(
				UrlConstant.getHttpUrl(newesEntity.getTucaoimg()),
				viewHolder.mIvTocaoImg, options);
		avatarImageLoader.displayImage(
				UrlConstant.getHttpUrl(newesEntity.getAvatarImg()),
				viewHolder.mIvAvatar, avatarOptions);
		return convertView;
	}

	private class ViewHolder {
		private RelativeLayout mRlItineraryImg;
		private ImageView mIvTocaoImg;
		private CircleImageView mIvAvatar;
		private TextView mTvUserName;
		private TextView mTvContent;
		private TextView mTvCommentnum;
		private TextView mTvPraisenum;
	}

}
