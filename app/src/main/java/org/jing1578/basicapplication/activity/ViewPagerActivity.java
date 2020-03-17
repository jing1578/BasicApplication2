package org.jing1578.basicapplication.activity;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import org.jing1578.basicapplication.R;
import org.jing1578.basicapplication.applicattion.ActivitySupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jing1578 on 2017/9/15 15:03.
 */

public class ViewPagerActivity extends ActivitySupport {
    public static final String TAG = "MZModeBannerFragment";
    public static final String[] RESTEXT = new String[]{"1", "20", "300", "4000", "50000", "600000", "7000000"};
    public static final int[] RES = new int[]{R.mipmap.image5, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4, R.mipmap.image6, R.mipmap.image7, R.mipmap.image8};
    public static final int[] BANNER = new int[]{R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4, R.mipmap.banner5};
    private MZBannerView mMZBanner;
    private MZBannerView mNormalBanner;

    private int curIndex = 0;
    private TextView newsTitle;
    private LinearLayout dotGroup;

    private void initView() {
        newsTitle = (TextView) findViewById(R.id.newsTitle);
        dotGroup = (LinearLayout) findViewById(R.id.dotGroup);
        mMZBanner = (MZBannerView) findViewById(R.id.banner);
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(ViewPagerActivity.this, "click page:" + position, Toast.LENGTH_LONG).show();
            }
        });
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < RES.length; i++) {
            list.add(RES[i]);
        }

        List<Integer> bannerList = new ArrayList<>();
        for (int i = 0; i < BANNER.length; i++) {
            bannerList.add(BANNER[i]);
        }
        mMZBanner.setIndicatorVisible(true);
//        mMZBanner.setIndicatorRes(R.drawable.dot_unselect_image, R.drawable.dot_select_image);
        mMZBanner.setPages(bannerList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

        mNormalBanner = (MZBannerView) findViewById(R.id.banner_normal);
        mNormalBanner.setIndicatorVisible(false);
        initPositions(list);
        mNormalBanner.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "----->addPageChangeLisnter:" + position + "positionOffset:" + positionOffset + "positionOffsetPixels:" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "addPageChangeLisnter:" + position);
                if (dotGroup.getChildAt(curIndex) != null) {
                    ((ImageView) dotGroup.getChildAt(curIndex)).setImageResource(R.drawable.dot_unselect_image);
                }
                if (dotGroup.getChildAt(position) != null) {
                    ((ImageView) dotGroup.getChildAt(position)).setImageResource(R.drawable.dot_select_image);
                }
                curIndex = position;
                newsTitle.setText(RESTEXT[curIndex]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //用来实现在最后一个向右滑动回到第一页,在第一页向左滑动回到最后一页
            }
        });
        mNormalBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

    }

    private void initPositions(Collection collection) {
        for (int i = 0; i < collection.size(); i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20,20);
            layoutParams.setMargins(0,0,20,0);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(R.drawable.dot_unselect_image);
            dotGroup.addView(imageView);
        }
        ((ImageView)dotGroup.getChildAt(curIndex)).setImageResource(R.drawable.dot_select_image);
        newsTitle.setText(RESTEXT[curIndex]);


    }

    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initView();
    }


    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();
        mNormalBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();
        mNormalBanner.start();
    }
}
