package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.adapter.home.EducationQuestionInvestigateAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:登录答题
 */
public class AnswerSecondActivity extends UIBaseActivity {
    private EducationQuestionInvestigateAdapter adapter;
    private List<EducationQuestionInvestigateModel> list = new ArrayList<>();
    private ListView listView;

    private ProgressBar progressBar;
    private TextView tvTitle;
    private TextView tvMoro;
    //是否多选
    private boolean isChooseMore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), MainActivity.class));
        });
        init();
        initValues();
    }

    private void initValues() {
        //进度
        list.add(new EducationQuestionInvestigateModel("无", "0", false));
        list.add(new EducationQuestionInvestigateModel("糖尿病足", "1", false));
        list.add(new EducationQuestionInvestigateModel("糖尿病肾病", "2", false));
        list.add(new EducationQuestionInvestigateModel("糖尿病眼病", "3", false));
        list.add(new EducationQuestionInvestigateModel("糖尿病神经病变", "4", false));
        list.add(new EducationQuestionInvestigateModel("糖尿病下肢血管病变", "5", false));

        adapter = new EducationQuestionInvestigateAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        isChooseMore = true;
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (isChooseMore) {
                if (list.get(position).getText().equals("无")) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setCheck(false);
                    }
                    list.get(position).setCheck(true);
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getText().equals("无")) {
                            list.get(i).setCheck(false);
                        }
                    }
                    list.get(position).setCheck(!list.get(position).isCheck());
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setCheck(false);
                }
                list.get(position).setCheck(true);
            }
            adapter.notifyDataSetChanged();
        });
        tvTitle.setText("是否患有糖尿病并发症？");
        tvMoro.setVisibility(View.VISIBLE);
        progressBar.setMax(12);
        progressBar.setProgress(3);
    }

    private void init() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_content, null);
        progressBar = view.findViewById(R.id.pb_answer_content);
        tvTitle = view.findViewById(R.id.tv_answer_content_title);
        tvMoro = view.findViewById(R.id.tv_answer_content_more);
        listView = view.findViewById(R.id.lv_answer_content_investigate);
        TextView tvUp = view.findViewById(R.id.tv_answer_content_up);
        TextView tvNext = view.findViewById(R.id.tv_answer_content_next);
        containerView().addView(view);

        tvUp.setOnClickListener(v -> finish());
        tvNext.setOnClickListener(v -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isCheck()) {
                    builder.append(list.get(i).getId());
                    //                    builder.append("}");
                    builder.append(",");
                }
            }
            Log.i("yys", "build.length==" + builder.length());
            if (builder.length() == 0) {
                ToastUtils.getInstance().showToast(getPageContext(), "请选择答案");
                return;
            }
            int position = getIntent().getIntExtra("position", 0);
            Intent intent = new Intent(getPageContext(), AnswerThirdActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }

}
