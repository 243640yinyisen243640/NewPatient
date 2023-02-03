package com.vice.bloodpressure.ui.fragment;


import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.utils.StatusBarUtils;

public class MainFragment extends UIBaseFragment {
    public static MainFragment getInstance(){
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        StatusBarUtils.statusBarColor(getActivity(), ContextCompat.getColor(getPageContext(), R.color.main_base_color));

    }
}