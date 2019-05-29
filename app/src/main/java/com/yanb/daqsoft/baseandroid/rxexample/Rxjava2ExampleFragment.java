package com.yanb.daqsoft.baseandroid.rxexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yanb.daqsoft.baseandroid.R;
import com.yanb.daqsoft.baseandroid.login.TokenLoader;
import com.yanb.daqsoft.baselib.activities.BaseTitleFragment;
import com.yanb.daqsoft.baselib.activities.IBasePresenter;
import com.yanb.daqsoft.baselib.utils.adapter.BaseQuickAdapter;
import com.yanb.daqsoft.baselib.utils.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Rxjava2的使用介绍
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-29.14:08
 * @since JDK 1.8
 */

public class Rxjava2ExampleFragment extends BaseTitleFragment {
    @BindView(R.id.rxjava_rv)
    RecyclerView mRv;

    public static Rxjava2ExampleFragment newInstance() {
        Bundle args = new Bundle();
        Rxjava2ExampleFragment fragment = new Rxjava2ExampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String MSG_WAIT_SHORT = "wait_short";
    private static final String MSG_WAIT_LONG = "wait_long";
    private static final String ERROR_TOKEN = "ERROR_TOKEN";
    private static final String ERROR_RETRY = "ERROR_RETRY";
    private int mMsgIndex;
    private static final String[] MSG_ARRAY = new String[]{
            MSG_WAIT_SHORT,
            MSG_WAIT_SHORT,
            MSG_WAIT_LONG,
            MSG_WAIT_LONG
    };
    private CompositeDisposable mCompositeDisposable;

    @Override
    public Object getLayout() {
        return R.layout.fragment_rxjava2_example;
    }


    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        setTitleText("Rxjava2实例");
        initView();
        mCompositeDisposable = new CompositeDisposable();
    }

    private void initView() {
        List<String> mDatas = new ArrayList<>();
        mDatas.add("retryWhen重订阅用法");
        mDatas.add("token失效解决方案");
        mRv.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRv.setAdapter(new BaseQuickAdapter<String,BaseViewHolder>(R.layout.common_item_single_text,mDatas) {

            @Override
            protected void convert(final BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_content,item);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (helper.getAdapterPosition()){
                            // retryWhen用法
                            case 0:
                                startRetryRequest();
                                break;
                            case 1:
                                /**
                                 * 为了模拟多线程请求刷新token的情况，我们在发起一个请求500ms之后，
                                 * 立刻发起另一个请求，当第二个请求决定是否要重订阅时，第一个请求正在进行刷新token的操作
                                 */
                                startCheckToken(0);
//                                try {
//                                    Thread.sleep(500);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                startCheckToken(1);
                                break;
                        }
                    }
                });
            }
        });
    }

    /**
     * retryWhen用法
     * <p>
     * 上游通知是否重新订阅 以onError事件触发
     * retryWhen根据onErroe的类型决定是否需要重订阅，返回ObservableSource<?>来通知，若返回onComplete/onError
     * 不会重订阅，如果发送onNext那么重订阅
     */
    private void startRetryRequest() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                int msglen = MSG_ARRAY.length;
                doWork();
                // 模拟请求的结果，前4次都返回失败，并将失败信息传递给retrywhen
                if (mMsgIndex < msglen) {
                    // 模拟请求失败的情况
                    e.onError(new Throwable(MSG_ARRAY[mMsgIndex]));
                    mMsgIndex++;
                } else {
                    // 模拟请求成功的情况
                    e.onNext("请求成功!");
                    e.onComplete();
                }
            }
            /**
             * Function输入Observable<Throwable> 输出ObservableSource<?>
             * 根据Throwable错误类型进行响应的处理
             * 如果输出的Observable发送onComplete或者onError表示不需要重订阅结束整个流程否则重订阅触发
             * 也就是说，它 仅仅是作为一个是否要触发重订阅的通知，onNext发送的是什么数据并不重要
             * 对于每一次订阅的数据流 Function 函数只会回调一次，并且是在onError(Throwable throwable)的时候触发，它不会收到任何的onNext事件。
             * 在Function函数中，必须对输入的 Observable<Object>进行处理，这里我们使用的是flatMap操作符接收上游的数据
             */
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            private int mRetryCount;

            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws
                    Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        String erroeMsg = throwable.getMessage();
                        long waitTime = 0;
                        switch (erroeMsg) {
                            case MSG_WAIT_SHORT:
                                waitTime = 2000;
                                break;
                            case MSG_WAIT_LONG:
                                waitTime = 4000;
                                break;
                            default:
                                break;
                        }

                        Logger.e("发生错误，尝试等待时间->" + waitTime + "当前重试次数=" + mRetryCount);
                        mRetryCount++;
                        return waitTime > 0 && mRetryCount <= 4 ? Observable.timer(waitTime,
                                TimeUnit.MILLISECONDS) : Observable.error(throwable);
                    }
                });
            }
        });
        DisposableObserver<String> disposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Logger.e("执行onNext=" + s);
            }

            @Override
            public void onError(Throwable e) {
                Logger.e("执行onError=" + e);
            }

            @Override
            public void onComplete() {
                Logger.e("执行onComplete");
            }
        };
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        mCompositeDisposable.add(disposableObserver);
    }

    /**
     * 操作
     */
    private void doWork() {
        long workTime = (long) (Math.random() * 500) + 500;
        try {
            Logger.e("doWork start,  threadId=" + Thread.currentThread().getId());
            Thread.sleep(workTime);
            Logger.e("doWork finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------------------------------token失效处理
    private void startCheckToken(final int index){
        /**
         * defer
         * 读取缓存中的token信息，这里调用了TokenLoader中读取缓存的接口，而这里使用defer操作符，
         * 是为了在重订阅时，重新创建一个新的Observable，以读取最新的缓存token信息
         */
        Observable<String> observable = Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                String cacheToken = TokenLoader.getInstance().getCacheToken();
                Logger.e("获取缓存Token-->"+cacheToken);
                return Observable.just(cacheToken);
            }
            /**
             * flatMap：通过token信息，请求必要的接口
             */
        }).flatMap(new Function<String, ObservableSource<String>>() {

            @Override
            public ObservableSource<String> apply(String token) throws Exception {
                return getUserObservable(index,token);
            }
            /**
             * retryWhen：使用重订阅的方式来处理token失效时的逻辑，这里分为三种情况：
             * 重试次数到达，那么放弃重订阅，直接返回错误；请求token接口，
             * 根据token请求的结果决定是否重订阅；其它情况直接放弃重订阅。
             */
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            private int mRetryCount = 0;
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws
                    Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        Logger.e("发生错误="+throwable+"重试次数-->"+mRetryCount);
                        if (mRetryCount>0){
                            return Observable.error(new Throwable(ERROR_RETRY));
                        }else if (ERROR_TOKEN.equals(throwable.getMessage())){
                            mRetryCount++;
                            return TokenLoader.getInstance().getNetTokenLocked();
                        }else {
                            return Observable.error(throwable);
                        }
                    }
                });
            }
        });
        DisposableObserver<String> observer = new DisposableObserver<String>() {

            @Override
            public void onNext(String value) {
                Logger.e(index + ":" + "收到信息=" + value);
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(index + ":" + "onError=" + e);
            }

            @Override
            public void onComplete() {
                Logger.e(index + ":" + "onComplete");
            }
        };
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    /**
     * 获取用户
     * @param index 模拟请求次数
     * @param token
     * @return
     */
    private Observable<String> getUserObservable(final int index, final String token){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Logger.e("使用token"+token+"请求用户信息");
                // 模拟根据token去请求信息的过程
                if (!TextUtils.isEmpty(token) && System.currentTimeMillis()- Long.valueOf(token) < 2000){
                    e.onNext(index+":"+token+"的用户信息");
                }else {
                    e.onError(new Throwable(ERROR_TOKEN));
                }
            }
        });
    }

}
