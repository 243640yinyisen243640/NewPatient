package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.ServicePressureAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.HealthyDataAllInfo;
import com.vice.bloodpressure.model.HealthyDataChildInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:血压列表
 */
public class ServicePressureListActivity extends UIBaseListRecycleViewForBgActivity<HealthyDataChildInfo> implements View.OnClickListener {
    private static final int REQUEST_CODE_FOR_FREFRESH = 1;
    private ImageView backImageView;
    private LinearLayout addLinearLayout;
    private TextView startTextView;
    private TextView endTextView;
    private TextView sysLowTextView;
    private TextView sysHighTextView;
    private TextView diaLowTextView;
    private TextView diaHighTextView;

    private String startTime = "";
    private String endTime = "";

    private HealthyDataAllInfo pressureInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 0), false));
        mRecyclerView.setLayoutManager(layoutManager);
        loadViewManager().changeLoadState(LoadStatus.LOADING);

        setPublicBottom();
        initListener();
    }

    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_service_top_with_time, null);
        startTextView = topView.findViewById(R.id.tv_service_blood_data_start_time);
        endTextView = topView.findViewById(R.id.tv_service_blood_data_end_time);
        TextView titleTextView = topView.findViewById(R.id.tv_service_blood_data_title);
        backImageView = topView.findViewById(R.id.iv_service_blood_data_back);
        LinearLayout allLinearLayout = topView.findViewById(R.id.ll_service_pressure_all);
        sysLowTextView = topView.findViewById(R.id.tv_service_sys_pressure_low);
        sysHighTextView = topView.findViewById(R.id.tv_service_sys_pressure_high);
        diaLowTextView = topView.findViewById(R.id.tv_service_dia_pressure_low);
        diaHighTextView = topView.findViewById(R.id.tv_service_dia_pressure_high);
        titleTextView.setText("血压数据");
        allLinearLayout.setVisibility(View.VISIBLE);
        return topView;
    }

    private void setPublicBottom() {
        View view = View.inflate(getPageContext(), R.layout.include_save_bottom, null);
        addLinearLayout = view.findViewById(R.id.ll_service_base_bottom_sure);
        TextView textTextView = view.findViewById(R.id.tv_service_base_bottom_text);
        FrameLayout.LayoutParams f2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textTextView.setText("添加血压数据");
        f2.gravity = Gravity.BOTTOM;
        containerView().addView(view, f2);


    }

    private void initListener() {
        startTextView.setOnClickListener(this);
        endTextView.setOnClickListener(this);
        backImageView.setOnClickListener(this);
        addLinearLayout.setOnClickListener(this);
    }

    @Override
    protected void getListData(CallBack callBack) {
        Call<String> requestCall = ServiceDataManager.getPressureStatistic(UserInfoUtils.getArchivesId(getPageContext()), startTime, endTime, (call, response) -> {
            if ("0000".equals(response.code)) {
                pressureInfo = (HealthyDataAllInfo) response.object;
                getPressureList(callBack);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getPressureStatistic", requestCall);
    }

    private void getPressureList(CallBack callBack) {
        Call<String> requestCall = ServiceDataManager.getPressureList(UserInfoUtils.getArchivesId(getPageContext()), getPageIndex() + "", BaseDataManager.PAGE_SIZE+"", startTime, endTime, (call, response) -> {
            if ("0000".equals(response.code)) {
                sysLowTextView.setText(null == pressureInfo.getMixSbpValue() ? "" : pressureInfo.getMixSbpValue());
                sysHighTextView.setText(null == pressureInfo.getMaxSbpValue() ? "" : pressureInfo.getMaxSbpValue());
                diaLowTextView.setText(null == pressureInfo.getMixDbpValue() ? "" : pressureInfo.getMixDbpValue());
                diaHighTextView.setText(null == pressureInfo.getMaxDbpValue() ? "" : pressureInfo.getMaxDbpValue());
                callBack.callBack(response.object);
            } else {
                callBack.callBack(null);
            }
        }, (call, t) -> {
            callBack.callBack(null);
        });
        addRequestCallToMap("selectMonitorHtnList", requestCall);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<HealthyDataChildInfo> list) {
        return new ServicePressureAdapter(getPageContext(), list);
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_blood_data_start_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        startTime = String.valueOf(object);
                        startTextView.setText(object.toString());
                        if (!TextUtils.isEmpty(endTime)) {
                            setPageIndex(1);
                            onPageLoad();
                        }
                    }
                });
                break;
            case R.id.tv_service_blood_data_end_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, object -> {
                    if (XyTimeUtils.compareTwoTime(startTime, object.toString())) {
                        endTime = String.valueOf(object);
                        endTextView.setText(object.toString());
                        setPageIndex(1);
                        onPageLoad();
                    } else {
                        ToastUtils.getInstance().showToast(getPageContext(), "结束时间不能大于开始时间");
                    }
                });
                break;
            case R.id.iv_service_blood_data_back:
                finish();
                break;
            case R.id.ll_service_base_bottom_sure:
                Intent intent = new Intent(getPageContext(), ServicePressureAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_FREFRESH);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_FOR_FREFRESH) {
                setPageIndex(1);
                onPageLoad();
            }
        }
    }
}
