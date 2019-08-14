package com.yanb.daqsoft.baselib.mvvmbase.binding.viewadapter.banner;


import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yanb.daqsoft.baselib.utils.banner.Banner;
import com.yanb.daqsoft.baselib.utils.glide.GlideImageLoader;

import java.util.List;

/**
 * Created by goldze on 2017/6/18.
 */
public final class ViewAdapter {
    @BindingAdapter(value = {"bannerImgListss"}, requireAll = false)
    public static void setBannerData(Banner banner, List<String> bannerImgListss) {
        if (bannerImgListss.size() > 0) {
            banner.setImages(bannerImgListss)
                    .setImageLoader(new GlideImageLoader())
                    .start();
        }
    }
}

