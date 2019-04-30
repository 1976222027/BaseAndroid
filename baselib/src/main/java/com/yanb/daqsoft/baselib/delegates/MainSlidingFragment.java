package com.yanb.daqsoft.baselib.delegates;

/**
 * 主要的
 */
public abstract class MainSlidingFragment extends PermissionCheckerSlidingFragment {
    @SuppressWarnings("unchecked")
    public <T extends MainSlidingFragment> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
