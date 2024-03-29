package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.user.UserCollectVideoAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:饮食搜索
 */
public class ServiceMealVideoSearchActivity extends UIBaseListRecycleViewForBgActivity<MealExclusiveInfo> {
    private EditText contentEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 2);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 10), true));
        mRecyclerView.setLayoutManager(layoutManager);
        noDataText("暂时没有内容，换个关键字试试吧");
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    @Override
    protected void getListData(CallBack callBack) {
        String dashName = TextUtils.isEmpty(contentEditText.getText().toString().trim()) ? "" : contentEditText.getText().toString().trim();
        Call<String> requestCall = ServiceDataManager.getMealVideoList("", getPageIndex() + "", BaseDataManager.PAGE_SIZE + "", dashName, (call, response) -> {
            if ("0000".equals(response.code)) {
                callBack.callBack(response.object);
            } else {
                callBack.callBack(null);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("getMealVideoList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<MealExclusiveInfo> list) {
        return new UserCollectVideoAdapter(getPageContext(), list, (position, view) -> {
            switch (view.getId()) {
                case R.id.ll_user_collect_video_click:
                    Intent intent = new Intent(getPageContext(), ServiceMakeMealDetailsActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;

            }
        });
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }


    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_education_intelligence_search, null);
        ImageView backImageView = topView.findViewById(R.id.iv_education_study_search_back);
        contentEditText = topView.findViewById(R.id.et_education_class_search);
        TextView searchTextView = topView.findViewById(R.id.tv_education_class_search_sure);
        backImageView.setOnClickListener(v -> finish());
        String content = contentEditText.getText().toString().trim();
        searchTextView.setOnClickListener(v -> {
            setPageIndex(1);
            onPageLoad();
        });
        return topView;
    }


}
