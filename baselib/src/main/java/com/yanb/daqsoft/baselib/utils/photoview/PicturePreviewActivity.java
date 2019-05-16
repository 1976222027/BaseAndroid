package com.yanb.daqsoft.baselib.utils.photoview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yanb.daqsoft.baselib.R;
import com.yanb.daqsoft.baselib.utils.viewpager.PhotoViewPager;

import java.util.List;

/**
 * 图片预览的界面
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-11.13:37
 * @since JDK 1.8
 */
public class PicturePreviewActivity extends AppCompatActivity {
    public static final String TAG = PicturePreviewActivity.class.getSimpleName();
    private PhotoViewPager mViewPager;
    private int currentPosition;
    private MyImageVpAdapter adapter;
    private TextView mTvImageCount;
    private List<String> Urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_preview);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("currentPosition", 0);
        Urls = intent.getStringArrayListExtra("imgList");

        adapter = new MyImageVpAdapter(Urls, this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentPosition, false);
        mTvImageCount.setText(currentPosition+1 + "/" + Urls.size());
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                mTvImageCount.setText(currentPosition + 1 + "/" + Urls.size());
            }
        });
    }

    private void initView() {
        mViewPager = (PhotoViewPager) findViewById(R.id.view_pager_photo);
        mTvImageCount = (TextView) findViewById(R.id.tv_img_num);
    }
}
