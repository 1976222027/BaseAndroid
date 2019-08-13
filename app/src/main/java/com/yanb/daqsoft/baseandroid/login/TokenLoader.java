package com.yanb.daqsoft.baseandroid.login;



import com.yanb.daqsoft.baselib.utils.KLog;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-5-29.11:05
 * @since JDK 1.8
 */

public class TokenLoader {
    /**
     * AtomicBoolean来标记是否有刷新Token的请求正在执行，如果有，
     * 那么直接返回一个PublishSubject，否则就先发起一次刷新token的请求，并将PublishSubject作为该请求的订阅者。
     */
    private AtomicBoolean mRefreshing = new AtomicBoolean(false);
    /**
     * PublishSubject它既是作为Token请求的订阅者，同时又作为retryWhen函数所返回Observable的发送方
     * 因为retryWhen返回的Observable所发送的值就决定了是否需要重订阅：
     *
     * 如果Token请求返回正确，那么就会发送onNext事件，触发重订阅操作，使得我们可以再次触发一次重试操作。
     * 如果Token请求返回错误，那么就会放弃重订阅，使得整个请求的调用链结束。
     *
     */
    private PublishSubject<String> mPublishSubject;
    private Observable<String> mTokenObservable;
    private TokenLoader() {
        mPublishSubject = PublishSubject.create();
        mTokenObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Thread.sleep(1000);
                KLog.e("发送Token");
                e.onNext(String.valueOf(System.currentTimeMillis()));
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String token) throws Exception {
                KLog.e("存储Token=" + token);
                StorageToken.getInstance().setToken(token);
                mRefreshing.set(false);
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mRefreshing.set(false);
            }
        }).subscribeOn(Schedulers.io());
    }
    public static TokenLoader getInstance() {
        return Holder.INSTANCE;
    }
    private static class Holder {
        private static final TokenLoader INSTANCE = new TokenLoader();
    }
    public String getCacheToken() {
        return StorageToken.getInstance().getToken();
    }

    /**
     *来获得一个PublishSubject
     * 如果有多个请求都出现了因token失效而需要重新刷新token的情况，
     * 那么需要判断当前是否有另一个请求正在刷新token，如果有，
     * 那么就不要发起刷新token的请求，而是等待刷新token的请求返回后，直接进行重试。
     *
     * @return
     */
    public Observable<String> getNetTokenLocked() {
        if (mRefreshing.compareAndSet(false, true)) {
            KLog.e("没有请求，发起一次新的Token请求");
            startTokenRequest();
        } else {
            KLog.e("已经有请求，直接返回等待");
        }
        return mPublishSubject;
    }
    private void startTokenRequest() {
        mTokenObservable.subscribe(mPublishSubject);
    }
}
