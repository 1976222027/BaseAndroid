package com.yanb.daqsoft.baselib.utils.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.yanb.daqsoft.baselib.utils.banner.transformer.AccordionTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.BackgroundToForegroundTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.CubeInTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.CubeOutTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.DefaultTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.DepthPageTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.FlipHorizontalTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.FlipVerticalTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.ForegroundToBackgroundTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.RotateDownTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.RotateUpTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.ScaleInOutTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.StackTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.TabletTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.ZoomInTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.ZoomOutSlideTransformer;
import com.yanb.daqsoft.baselib.utils.banner.transformer.ZoomOutTranformer;

public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
