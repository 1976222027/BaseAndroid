package com.yanb.daqsoft.baselib.delegates;

import android.Manifest;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public abstract class PermissionCheckerSlidingFragment extends BaseSlidingFragment {
    //不是直接调用方法
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera() {
        //LatteCamera.start(this);
    }
}
