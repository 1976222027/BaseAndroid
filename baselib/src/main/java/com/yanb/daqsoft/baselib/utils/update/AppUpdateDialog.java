package com.yanb.daqsoft.baselib.utils.update;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanb.daqsoft.baselib.R;
import com.yanb.daqsoft.baselib.utils.dialog.BaseDialog;

public class AppUpdateDialog extends BaseDialog {

    public AppUpdateDialog(Context context,String versionCode,String content) {
        super(context);
        init(versionCode,content);
    }

    /**
     * 初始化
     */
    private void init(String versionCode,String content) {
        setContentView(R.layout.dialog_update);
        canceledOnTouchOutside(false);
        this.layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView tvtitle = findViewById(R.id.tv_title);
        TextView tvcontent = findViewById(R.id.tv_description);
        tvtitle.setText("发现新版v"+versionCode+"可以下载啦！");
        tvcontent.setText(content);
    }
}
