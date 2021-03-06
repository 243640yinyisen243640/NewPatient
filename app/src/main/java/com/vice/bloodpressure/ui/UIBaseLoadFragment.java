package com.vice.bloodpressure.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.base.BaseApp;
import com.vice.bloodpressure.baseimp.HHSoftApplicationInterface;
import com.vice.bloodpressure.basemanager.DefaultTopViewManager;
import com.vice.bloodpressure.basemanager.LoadViewManager;
import com.vice.bloodpressure.utils.StatusBarUtils;


public abstract class UIBaseLoadFragment extends BaseFragment {
    private LinearLayout contentView;
    private FrameLayout containerView;
    private DefaultTopViewManager topViewManager;
    private LoadViewManager loadViewManager;
    private boolean isScreen = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = new LinearLayout(getPageContext());
        contentView.setOrientation(LinearLayout.VERTICAL);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (isNeedFullScreen()) {
            isScreen = StatusBarUtils.fullScreenWithStatusBarColor(getActivity(), R.color.transparent, false);
            topViewManager = new DefaultTopViewManager(getActivity(), isScreen);
        } else {
            topViewManager = new DefaultTopViewManager(getActivity(), false);
        }
        contentView.addView(topViewManager.topView(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        containerView = new FrameLayout(getPageContext());
        contentView.addView(containerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        loadViewManager = new LoadViewManager(initLoadMode() == null ? LoadViewManager.LoadMode.PROGRESS : initLoadMode(), containerView, () -> {
            onPageLoad();
        });
        onCreate();
        return contentView;
    }

    protected boolean isFullScreen() {
        return isScreen;
    }

    /**
     * ?????????????????????
     *
     * @return
     */
    protected LoadViewManager.LoadMode initLoadMode() {
        if (getActivity().getApplication() instanceof BaseApp) {
            BaseApp application = (BaseApp) getActivity().getApplication();
            HHSoftApplicationInterface applicationInfo = application.applicationInfo();
            return applicationInfo.appLoadMode() == null ? LoadViewManager.LoadMode.PROGRESS : applicationInfo.appLoadMode();
        }
        return LoadViewManager.LoadMode.PROGRESS;
    }

    /**
     * ????????????
     */
    protected abstract void onCreate();

    /**
     * ??????????????????
     */
    protected abstract void onPageLoad();

    /**
     * ???????????????
     *
     * @return
     */
    protected DefaultTopViewManager topViewManager() {
        return topViewManager;
    }

    /**
     * ???????????????
     *
     * @return
     */
    protected LoadViewManager loadViewManager() {
        return loadViewManager;
    }

    /**
     * ????????????????????????
     *
     * @return
     */
    protected LinearLayout contentView() {
        return contentView;
    }

    /**
     * ??????????????????????????????
     *
     * @return
     */
    protected FrameLayout containerView() {
        return containerView;
    }
}
