package com.vice.bloodpressure.ui;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.base.BaseApp;
import com.vice.bloodpressure.baseimp.HHSoftApplicationInterface;
import com.vice.bloodpressure.basemanager.DefaultTopViewManager;
import com.vice.bloodpressure.basemanager.LoadViewManager;
import com.vice.bloodpressure.utils.StatusBarUtils;


public abstract class UIBaseLoadActivity extends BaseActivity {
    private LinearLayout contentView;
    private FrameLayout containerView;
    private DefaultTopViewManager topViewManager;
    private LoadViewManager loadViewManager;
    private boolean isScreen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = new LinearLayout(getPageContext());
        contentView.setOrientation(LinearLayout.VERTICAL);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (isNeedFullScreen()) {
            isScreen = StatusBarUtils.fullScreenWithStatusBarColor(this, R.color.transparent, false);
            topViewManager = new DefaultTopViewManager(this, isScreen);
        } else {
            topViewManager = new DefaultTopViewManager(this, false);
        }
        contentView.addView(topViewManager.topView(), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        containerView = new FrameLayout(getPageContext());
        contentView.addView(containerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        setContentView(contentView);
        loadViewManager = new LoadViewManager(initLoadMode() == null ? LoadViewManager.LoadMode.PROGRESS : initLoadMode(), containerView, () -> {
            onPageLoad();
        });
    }

    /**
     * ?????????????????????
     *
     * @return
     */
    protected LoadViewManager.LoadMode initLoadMode() {
        if (getApplication() instanceof BaseApp) {
            BaseApp application = (BaseApp) getApplication();
            HHSoftApplicationInterface applicationInfo = application.applicationInfo();
            return applicationInfo.appLoadMode() == null ? LoadViewManager.LoadMode.PROGRESS : applicationInfo.appLoadMode();
        }
        return LoadViewManager.LoadMode.PROGRESS;
    }
    protected boolean isFullScreen() {
        return isScreen;
    }
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
