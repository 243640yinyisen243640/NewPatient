package com.vice.bloodpressure.basemanager;

import android.view.View;

import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseimp.LoadViewInterface;
import com.vice.bloodpressure.basemodel.LoadViewConfig;


public class LoadViewManager {
    public static final LoadViewConfig mLoadViewConfig = new LoadViewConfig();

    /**
     * 加载页面模式
     */
    public enum LoadMode {
        DRAWABLE,
        PROGRESS
    }

    private LoadViewInterface mAvalibleLoadViewInterface;

    /**
     * 加载布局管理器初始化
     *
     * @param loadMode   加载页面模式
     * @param parentView 必须是帧布局
     */
    public LoadViewManager(LoadMode loadMode, View parentView, IPageLoad pageLoad) {
        if (parentView == null) {
            new Throwable("parentView is not null");
        }
        if (LoadMode.DRAWABLE == loadMode) {
            mAvalibleLoadViewInterface = new DefaultLoadViewManager(parentView.getContext(), parentView, pageLoad);
        } else {
            mAvalibleLoadViewInterface = new ProgressLoadViewManager(parentView.getContext(), parentView, pageLoad);
        }
        mAvalibleLoadViewInterface.init(mLoadViewConfig);
    }

    public LoadViewInterface loadViewInterface() {
        return mAvalibleLoadViewInterface;
    }

    /**
     * 在调用加载中方法之前调用该方法
     *
     * @param config
     */
    public void initLoadView(LoadViewConfig config) {
        mAvalibleLoadViewInterface.init(config);
    }

    public void changeLoadState(LoadStatus status) {
        mAvalibleLoadViewInterface.changeLoadState(status);
    }

    public void changeLoadStateWithHint(LoadStatus status, String hint) {
        mAvalibleLoadViewInterface.changeLoadStateWithHint(status, hint);
    }

    public void setOnClickListener(LoadStatus status, View.OnClickListener listener) {
        mAvalibleLoadViewInterface.setOnClickListener(status, listener);
    }

    public interface IPageLoad {
        void onPageLoad();
    }
}
