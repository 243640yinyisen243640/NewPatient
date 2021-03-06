package com.vice.bloodpressure;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vice.bloodpressure.fragment.MainFragment;
import com.vice.bloodpressure.ui.UIBaseActivity;
import com.vice.bloodpressure.version.VersionUtils;


public class MainActivity extends UIBaseActivity implements View.OnClickListener {

    private FragmentManager mFragManager;
    private Fragment mCurrentFragment;

    private TextView homeTextView;
    private TextView hospitalTextView;
    private TextView mallTextView;
    private TextView myTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        containerView().addView(initView());
        initValue();
        initListener();

        VersionUtils.getInstance().updateNewVersion(getPageContext(), this, false);
    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_main, null);
        homeTextView = view.findViewById(R.id.tv_main_home_page);
        hospitalTextView = view.findViewById(R.id.tv_main_home_hospital);
        mallTextView = view.findViewById(R.id.tv_main_mall);
        myTextView = view.findViewById(R.id.tv_main_my);
        return view;
    }

    private void initValue() {
        mFragManager = getSupportFragmentManager();
        checkFragment(R.id.tv_main_home_page);
        setTextViewColor(R.id.tv_main_home_page);
    }

    private void initListener() {
        homeTextView.setOnClickListener(this);
        hospitalTextView.setOnClickListener(this);
        mallTextView.setOnClickListener(this);
        myTextView.setOnClickListener(this);
    }

    /**
     * 设置选中的Fragment
     *
     * @param checkId
     */
    private void checkFragment(@IdRes int checkId) {
        FragmentTransaction transaction = mFragManager.beginTransaction();
        Fragment fragment = mFragManager.findFragmentByTag(checkId + "");
        if (fragment == null) {
            if (checkId == R.id.tv_main_home_page) {
                //首页
                fragment = new MainFragment();
            } else if (checkId == R.id.tv_main_home_hospital) {
                fragment = new MainFragment();
            } else if (checkId == R.id.tv_main_mall) {
                fragment = new MainFragment();
            } else {
                fragment = new MainFragment();
            }
            transaction.add(R.id.fl_main, fragment, checkId + "");
        }
        if (mCurrentFragment != null) {
            mCurrentFragment.onPause();
            transaction.hide(mCurrentFragment);
        }
        mCurrentFragment = fragment;
        transaction.show(mCurrentFragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 选中的id
     *
     * @param checkedId
     */
    private void setTextViewColor(@IdRes int checkedId) {
        if (checkedId == R.id.tv_main_home_page) {
            homeTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_gray));
            homeTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_selected, 0, 0);

            hospitalTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_black));
            hospitalTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_default, 0, 0);

            mallTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_black));
            mallTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_mall_default, 0, 0);

            myTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_black));
            myTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_me_default, 0, 0);
        } else if (checkedId == R.id.tv_main_home_hospital) {
            homeTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_black));
            homeTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_default, 0, 0);

            hospitalTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_gray));
            hospitalTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_selected, 0, 0);

            mallTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.main_base_color));
            mallTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_mall_default, 0, 0);

            myTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_black));
            myTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_me_default, 0, 0);
        } else if (checkedId == R.id.tv_main_mall) {
            homeTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_black));
            homeTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_default, 0, 0);

            hospitalTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_black));
            hospitalTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_default, 0, 0);

            mallTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_gray));
            mallTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_mall_selected, 0, 0);

            myTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_black));
            myTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_me_default, 0, 0);
        } else {
            homeTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_black));
            homeTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_default, 0, 0);

            hospitalTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_black));
            hospitalTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_default, 0, 0);

            mallTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_black));
            mallTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_mall_default, 0, 0);

            myTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.text_gray));
            myTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_me_selected, 0, 0);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_main_home_page:
            case R.id.tv_main_home_hospital:
            case R.id.tv_main_mall:
            case R.id.tv_main_my:
                setTextViewColor(view.getId());
                checkFragment(view.getId());
                break;
            default:
                break;
        }
    }
}