package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.OutDataManager;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.dialog.HHSoftDialogActionEnum;
import com.vice.bloodpressure.model.DoctorInfo;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyImageUtils;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:我的医生
 */
public class UserDoctorActivity extends UIBaseLoadActivity {
    private ImageView headImageView;
    private TextView nameTextView;
    private TextView postTextView;
    private TextView hospitalTextView;
    private TextView introduceTextView;
    private TextView breakTextView;

    /**
     * 1:我的医生  2：其他医生
     */
    private String type;
    /**
     * 医生id
     */
    private String doctorId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("我的医生");
        type = getIntent().getStringExtra("type");
        if ("2".equals(type)) {
            doctorId = getIntent().getStringExtra("doctorId");
        }
        initView();
        initlistener();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initlistener() {
        breakTextView.setOnClickListener(v -> {
            DialogUtils.showOperDialog(getPageContext(), "", "确认解绑该医生吗？", "我在想想", "确定", (dialog, which) -> {
                dialog.dismiss();
                if (HHSoftDialogActionEnum.POSITIVE == which) {

                }
            });
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_my_doctor, null);
        headImageView = view.findViewById(R.id.iv_user_my_doctor_head);
        nameTextView = view.findViewById(R.id.tv_user_my_doctor_name);
        postTextView = view.findViewById(R.id.tv_user_my_doctor_post);
        hospitalTextView = view.findViewById(R.id.tv_user_my_doctor_hos);
        introduceTextView = view.findViewById(R.id.tv_user_my_doctor_introduce);
        breakTextView = view.findViewById(R.id.tv_user_my_doctor_break);
        if ("1".equals(type)) {
            breakTextView.setText("解绑医生");
        } else {
            breakTextView.setText("绑定医生");
        }
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {
        if ("1".equals(type)) {
            Call<String> requestCall = UserDataManager.getSelectDoctorInfo(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
                if ("0000".equals(response.code)) {
                    loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                    DoctorInfo doctorInfo = (DoctorInfo) response.object;
                    bindData(doctorInfo);
                } else {
                    loadViewManager().changeLoadState(LoadStatus.FAILED);
                }
            }, (call, t) -> {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            });
            addRequestCallToMap("getSelectDoctorInfo", requestCall);
        } else {
            Call<String> requestCall = OutDataManager.getDeptDoctorInfo(doctorId, UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
                if ("0000".equals(response.code)) {
                    loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                    DoctorInfo doctorInfo = (DoctorInfo) response.object;
                    bindData(doctorInfo);
                } else {
                    loadViewManager().changeLoadState(LoadStatus.FAILED);
                }
            }, (call, t) -> {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            });
            addRequestCallToMap("getDeptDoctorInfo", requestCall);
        }
    }

    private void bindData(DoctorInfo doctorInfo) {
        XyImageUtils.loadCircleImage(getPageContext(), R.drawable.out_doctor_default_head_img, doctorInfo.getAvatar(), headImageView);
        nameTextView.setText(doctorInfo.getDoctorName());
        postTextView.setText(doctorInfo.getDeptName());
        introduceTextView.setText(doctorInfo.getProfile());
    }
}
