package com.vice.bloodpressure.activity.ahome.adiet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.view.NoScrollListView;


/**
 * 类名：一餐详情展示
 * 传参：meal
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/3 16:55
 */
public class DietMealDetailsActivity extends UIBaseActivity {
    /**
     * 早餐
     */
    private TextView mealTitleTv;

    /**
     * 早餐列表
     */
    private NoScrollListView mealTitleNlv;

    private String titleMeal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleMeal = getIntent().getStringExtra("meal");
        topViewManager().moreTextView().setText("换我想吃");
        initView();
        topViewManager().titleTextView().setText(titleMeal);
        initValues();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_diet_mall_details, null);
        containerView().addView(view);
        mealTitleTv = view.findViewById(R.id.tv_diet_mall_details_title);
        mealTitleNlv = view.findViewById(R.id.nlv_diet_mall_details);
    }

    private void initValues() {

        if ("早餐".equals(titleMeal)) {
            mealTitleTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.diet_bing_with_green, 0, 0, 0);
        }else if ("午餐".equals(titleMeal)){
            mealTitleTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.diet_jitui_with_green, 0, 0, 0);
        }else {
            mealTitleTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.diet_huacai_with_green, 0, 0, 0);
        }

    }
}