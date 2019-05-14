package com.daqsoft.imgselected.customimgup;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.daqsoft.imgselected.Matisse;
import com.daqsoft.imgselected.MimeType;
import com.daqsoft.imgselected.R;
import com.daqsoft.imgselected.engine.impl.GlideEngine;
import com.daqsoft.imgselected.filter.Filter;
import com.yanb.daqsoft.baselib.permission.RxPermissions;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * 图片选择view
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-14.13:39
 * @since JDK 1.8
 */

public class ImageSelectionView extends FrameLayout implements View.OnClickListener{
    /**
     * 图片最大选择数量
     */
    private int imgMax  = 5;
    /**
     * 图片返回标识
     */
    private static final int REQUEST_CODE_CHOOSE = 23;
    private Context mContent;
    /**
     * 输入框
     */
    private EditText edt_content;
    /**
     * 字数监听
     */
    private TextView tv_maxnum;
    /**
     * 图片布局
     */
    private RecyclerView mImgSelectedRv;
    /**
     * 图片选择的适配器
     */
    private CommentWriteAdapter mAdapter;

    public ImageSelectionView(@NonNull Context context) {
        this(context,null);
    }

    public ImageSelectionView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public ImageSelectionView(@NonNull Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View view) {

    }
    private void init(Context context) {
        this.mContent = context;
        View view = LayoutInflater.from(getContext()).inflate(R.layout
                .customview_layout_imgselected, null);
        mImgSelectedRv = (RecyclerView)view.findViewById(R.id.imgselected_rv);
        edt_content = (EditText)view.findViewById(R.id.edt_content);
        tv_maxnum = (TextView) view.findViewById(R.id.tv_maxnum);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(context, 4, GridLayoutManager
                .VERTICAL, false);
        mImgSelectedRv.setLayoutManager(manager);
        mAdapter = new CommentWriteAdapter(context, new CommentWriteAdapter.onAddPicClickListener() {

            @Override
            public void onAddPicClick(int currentImgSize) {
                selectedImg(currentImgSize);
            }
        });
        mImgSelectedRv.setAdapter(mAdapter);
        tv_maxnum.setText("0/200");
        edt_content.addTextChangedListener(new MyTextWatcher(tv_maxnum, 200, edt_content));
        this.addView(view,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.WRAP_CONTENT));
    }

    /**
     * 启动图片选择功能
     * @param currentImgSize
     */
    private void selectedImg(final int currentImgSize){
        RxPermissions rxPermissions = new RxPermissions((FragmentActivity) mContent);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            Matisse.from((Activity) mContent)
                                    .choose(MimeType.ofImage())
                                    .theme(R.style.Matisse_Dracula)
                                    .countable(false)
                                    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                    .maxSelectable(imgMax-currentImgSize)
                                    .originalEnable(true)
                                    .maxOriginalSize(10)
                                    .imageEngine(new GlideEngine())  // for glide-V3
                                    .forResult(REQUEST_CODE_CHOOSE);
                        }
                    }
                });
    }

    /**
     * 添加数据
     * @param imglist
     */
    public void setImglist(List<String> imglist){
        mAdapter.setList(imglist);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 输入框的监听事件
     */
    public class MyTextWatcher implements TextWatcher {
        public TextView countView;
        public int count;
        private EditText editText;

        public MyTextWatcher(TextView countView, int count, EditText editText) {
            this.countView = countView;
            this.count = count;
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int len = 0;
            String value = editText.getText().toString();
            if (!TextUtils.isEmpty(value)) {
                len = value.length();
            }
            countView.setText(len + "/" + MyTextWatcher.this.count);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
