package com.daqsoft.customview.login;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daqsoft.customview.R;
import com.orhanobut.logger.Logger;
import com.yanb.daqsoft.baselib.utils.ToastUtils;

/**
 * 常用登陆自定义Veiw
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-8.10:35
 * @since JDK 1.8
 */

public class LoginView extends FrameLayout implements View.OnClickListener{
    private EditText mEtUser,mEtPasd;
    private ImageView mImgEyes,mImgCleanPasd,mImgCleanAccount,mImgTop;
    private TextView mTvTitle;
    private OnLoginListener mLoginListener;
    private Button mBtnLogin;
    /**
     * 顶部图标资源
     */
    private int topImageResource;
    /**
     * 顶部文字
     */
    private String topText;

    /**
     * 登录的接口
     */
    public interface OnLoginListener{
        void onLogin(String account,String password);
    }
    public void setLoginListener(OnLoginListener onLoginListener){
        this.mLoginListener = onLoginListener;
    }
    /**
     * 是否显示明文密码
     */
    private boolean flag = false;
    public LoginView(Context context) {
        this(context,null);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs) {
        // 获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LoginView);
        topImageResource = array.getResourceId(R.styleable.LoginView_loginIcon,-1);
        topText = array.getString(R.styleable.LoginView_loginTitle);
        View login = LayoutInflater.from(getContext()).inflate(R.layout.layout_customview_login,null);
        mEtUser = (EditText)login.findViewById(R.id.et_account);
        mEtPasd = (EditText)login.findViewById(R.id.et_password);
        mImgEyes = (ImageView) login.findViewById(R.id.iv_show_pwd);
        mImgCleanPasd = (ImageView) login.findViewById(R.id.clean_password);
        mImgCleanAccount = (ImageView) login.findViewById(R.id.iv_clean_account);
        mImgTop = (ImageView) login.findViewById(R.id.img_login_top);
        mTvTitle = (TextView) login.findViewById(R.id.tv_login_top);
        mBtnLogin = (Button) login.findViewById(R.id.btn_login);
        mImgCleanPasd.setOnClickListener(this);
        mImgEyes.setOnClickListener(this);
        mImgCleanAccount.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        // 设置自定义属性
        if (topImageResource==-1){
            mImgTop.setVisibility(GONE);
        }else {
            mImgTop.setVisibility(VISIBLE);
            mImgTop.setImageResource(topImageResource);
        }
        if (null==topText){
            mTvTitle.setVisibility(GONE);
        }else {
            mTvTitle.setVisibility(VISIBLE);
            mTvTitle.setText(topText);
        }
        initListener();
        this.addView(login,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.WRAP_CONTENT));
    }

    /**
     * 获取账号
     * @return
     */
    public String getAcount(){
        String acount = "";
        if (mEtUser==null){
            acount = "";
        }else {
            acount = mEtUser.getText().toString().trim();
        }
        return acount;
    }

    @Override
    public void onClick(View view) {
        // 密码是否明文显示
        if (view.getId()==R.id.iv_show_pwd){
            if (flag == true) {
                mEtPasd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                mImgEyes.setImageResource(R.mipmap.ic_eye_close);
                flag = false;
            } else {
                mEtPasd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                mImgEyes.setImageResource(R.mipmap.ic_eye_open);
                flag = true;
            }
            String pwd = mEtPasd.getText().toString();
            if (!TextUtils.isEmpty(pwd))
                mEtPasd.setSelection(pwd.length());
            // 清除密码
        }else if (view.getId()==R.id.clean_password){
            mEtPasd.setText("");
            // 清除账号
        }else if (view.getId()==R.id.iv_clean_account){
            mEtUser.setText("");
            // 登录
        }else if (view.getId() == R.id.btn_login){
            if (mLoginListener !=null){
                String acount = mEtUser.getText().toString().trim();
                String password = mEtPasd.getText().toString().trim();
                if (!acount.isEmpty()&&!password.isEmpty()){
                    mLoginListener.onLogin(acount,password);
                }else if (acount.isEmpty()){
                    ToastUtils.showCenterShort("账号不能为空!");
                }else if (password.isEmpty()){
                    ToastUtils.showCenterShort("密码不能为空!");
                }
            }
        }
    }

    /**
     * 添加监听
     */
    private void initListener() {
        mEtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mImgCleanAccount.getVisibility() == View.GONE) {
                    mImgCleanAccount.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mImgCleanAccount.setVisibility(View.GONE);
                }
            }
        });
        mEtPasd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mImgCleanPasd.getVisibility() == View.GONE) {
                    mImgCleanPasd.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mImgCleanPasd.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    ToastUtils.showLong("请输入数字或字母");
                    s.delete(temp.length() - 1, temp.length());
                    mEtPasd.setSelection(s.length());
                }
            }
        });
    }
}
