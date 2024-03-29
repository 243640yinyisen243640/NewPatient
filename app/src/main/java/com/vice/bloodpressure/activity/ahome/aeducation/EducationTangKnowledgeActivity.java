package com.vice.bloodpressure.activity.ahome.aeducation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationQuestionInvestigateRealAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.model.EducationAnswerInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:糖尿病知识
 * 传参:
 * 描述:
 */
public class EducationTangKnowledgeActivity extends UIBaseActivity {
    private EducationQuestionInvestigateRealAdapter adapter;
    private List<BaseLocalDataInfo> list = new ArrayList<>();
    private ListView listView;
    private TextView tvTitle;
    private ProgressBar progressBar;
    private TextView tvMoro;
    private List<Class> classList;
    private int index;
    private EducationAnswerInfo answerInfo;
    private int allPage;
    private int page;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定教育方案");
        classList = (List<Class>) getIntent().getSerializableExtra("classList");
        index = getIntent().getIntExtra("index", 0);
        answerInfo = (EducationAnswerInfo) getIntent().getSerializableExtra("answerInfo");
        allPage = getIntent().getIntExtra("allPage", 0);
        page = getIntent().getIntExtra("page", 0);

        init();
        initValues();
    }

    private void initValues() {
        //进度
        tvTitle.setText("是否了解糖尿病的基础知识？");
        tvMoro.setVisibility(View.GONE);
        progressBar.setMax(allPage);
        progressBar.setProgress(page);

        list.add(new BaseLocalDataInfo("是", "1"));
        list.add(new BaseLocalDataInfo("否", "2"));
        list.get(0).setCheck(true);
        answerInfo.setDmBasics("1");
        adapter = new EducationQuestionInvestigateRealAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setCheck(false);
            }
            list.get(position).setCheck(true);
            answerInfo.setDmBasics(list.get(position).getId());
            adapter.notifyDataSetChanged();

        });

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
            //          跳转页面
            Intent intent = new Intent(getPageContext(), EducationTangTimeActivity.class);
            intent.putExtra("index", index);
            intent.putExtra("classList", (Serializable) classList);
            intent.putExtra("answerInfo", answerInfo);
            intent.putExtra("page", page + 1);
            intent.putExtra("allPage", allPage);
            startActivity(intent);

        });
    }

}
