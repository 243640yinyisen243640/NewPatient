package com.vice.bloodpressure.fragment.fservice;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aservice.ServiceMedicineRemindAddActivity;
import com.vice.bloodpressure.adapter.service.ServiceMedicineAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.BaseDataManager;
import com.vice.bloodpressure.baseui.UIBaseListRecycleViewForBgFragment;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.dialog.HHSoftDialogActionEnum;
import com.vice.bloodpressure.model.ServiceInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:用药提醒
 */
public class ServiceMedicineRemindFragment extends UIBaseListRecycleViewForBgFragment<ServiceInfo> {
    private static final int REQUEST_CODE_FOR_FREFRESH = 1;
    private TextView startTimeTextView;
    private TextView endTimeTextView;

    private String startTime = "";
    private String endTime = "";

    @Override
    protected void onCreate() {
        super.onCreate();
        topViewManager().topView().removeAllViews();
        topViewManager().topView().addView(initTopView());
        GridLayoutManager layoutManager = new GridLayoutManager(getPageContext(), 1);
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(getPageContext(), 0), false));
        mRecyclerView.setLayoutManager(layoutManager);
        setPublicBottom();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private View initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_service_top_with_time_only, null);
        startTimeTextView = topView.findViewById(R.id.tv_service_medicine_start_time);
        endTimeTextView = topView.findViewById(R.id.tv_service_medicine_end_time);
        return topView;
    }

    @Override
    protected void getListData(CallBack callBack) {
        List<ServiceInfo> oxygenList = new ArrayList<>();
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称最长展示12个字"));
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称"));
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称最长"));
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称最长展示12"));
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称最长展示12个"));
        oxygenList.add(new ServiceInfo("2022-05-06", "30", "170", "药品名称最长展示"));
        callBack.callBack(oxygenList);
    }

    private void setPublicBottom() {
        View view = View.inflate(getPageContext(), R.layout.include_save_bottom, null);
        LinearLayout addLinearLayout = view.findViewById(R.id.ll_service_base_bottom_sure);
        TextView textTextView = view.findViewById(R.id.tv_service_base_bottom_text);
        FrameLayout.LayoutParams f2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textTextView.setText("添加新提醒");
        addLinearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), ServiceMedicineRemindAddActivity.class);
            intent.putExtra("type", "3");
            startActivityForResult(intent, REQUEST_CODE_FOR_FREFRESH);
        });
        f2.gravity = Gravity.BOTTOM;
        containerView().addView(view, f2);
    }

    @Override
    protected RecyclerView.Adapter instanceAdapter(List<ServiceInfo> list) {
        return new ServiceMedicineAdapter(getPageContext(), list, "2", new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                Intent intent;
                switch (view.getId()) {
                    case R.id.tv_item_service_medicine_delete:
                        DialogUtils.showOperDialog(getPageContext(), "", "确定要删除吗？", "取消", "确定", (dialog, which) -> {
                            dialog.dismiss();
                            if (HHSoftDialogActionEnum.POSITIVE == which) {

                            }
                        });
                        break;
                    case R.id.tv_item_service_medicine_edit:
                        intent = new Intent(getPageContext(), ServiceMedicineRemindAddActivity.class);
                        intent.putExtra("type", "1");
                        startActivity(intent);
                        break;
                    case R.id.tv_item_service_medicine_look:
                        intent = new Intent(getPageContext(), ServiceMedicineRemindAddActivity.class);
                        intent.putExtra("type", "2");
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {

            }
        });
    }

    @Override
    protected int getPageSize() {
        return BaseDataManager.PAGE_SIZE;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_FOR_FREFRESH) {
                setPageIndex(1);
                onPageLoad();
            }
        }
    }
}
