package com.yanb.daqsoft.baselib.net;

import com.yanb.daqsoft.baselib.net.callback.IError;
import com.yanb.daqsoft.baselib.net.callback.IFailure;
import com.yanb.daqsoft.baselib.net.callback.IRequest;
import com.yanb.daqsoft.baselib.net.callback.ISuccess;

import java.util.WeakHashMap;

/**
 *
 */
public class RequestClient {
    private static final WeakHashMap<String,Object> PARAMS = RequestCreator.getParams();
    private final String URL;
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;

    public RequestClient(String URL, IRequest IREQUEST, ISuccess ISUCCESS, IFailure IFAILURE, IError IERROR) {
        this.URL = URL;
        this.IREQUEST = IREQUEST;
        this.ISUCCESS = ISUCCESS;
        this.IFAILURE = IFAILURE;
        this.IERROR = IERROR;
    }
}
