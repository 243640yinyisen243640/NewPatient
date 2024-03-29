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
 * 类名：
 * 传参：
 * 描述: 糖尿病并发症
 * 作者: beauty
 * 创建日期: 2023/7/13 16:35
 */
public class EducationTangConcurrencyActivity extends UIBaseActivity implements View.OnClickListener {
    private ListView listView;
    private TextView tvTitle;
    private TextView tvMore;
    private EducationQuestionInvestigateRealAdapter adapter;
    private List<BaseLocalDataInfo> list = new ArrayList<>();

    private TextView tvUp;
    private TextView tvNext;
    private ProgressBar progressBar;

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
        intView();
        initValues();
        initListener();
    }

    private void initListener() {
        tvUp.setOnClickListener(this);
        tvNext.setOnClickListener(this);
    }

    private void intView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_content, null);
        progressBar = view.findViewById(R.id.pb_answer_content);
        tvTitle = view.findViewById(R.id.tv_answer_content_title);
        tvMore = view.findViewById(R.id.tv_answer_content_more);
        listView = view.findViewById(R.id.lv_answer_content_investigate);
        tvUp = view.findViewById(R.id.tv_answer_content_up);
        tvNext = view.findViewById(R.id.tv_answer_content_next);
        containerView().addView(view);

    }

    private void initValues() {
        progressBar.setProgress(page);
        progressBar.setMax(allPage);
        tvMore.setVisibility(View.VISIBLE);
        tvTitle.setText("您的糖尿病类型是什么？");

        list.add(new BaseLocalDataInfo("无", "1"));
        list.add(new BaseLocalDataInfo("糖尿病足", "2"));
        list.add(new BaseLocalDataInfo("糖尿病肾病", "3"));
        list.add(new BaseLocalDataInfo("糖尿病视网膜病变", "4"));
        list.add(new BaseLocalDataInfo("糖尿病神经病变", "5"));
        list.add(new BaseLocalDataInfo("糖尿病下肢血管病变", "6"));
        list.get(0).setCheck(true);
        adapter = new EducationQuestionInvestigateRealAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (list.get(position).getName().equals("无")) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setCheck(false);
                }
                list.get(position).setCheck(true);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName().equals("无")) {
                        list.get(i).setCheck(false);
                    }
                }
                list.get(position).setCheck(!list.get(position).isCheck());
            }

            adapter.notifyDataSetChanged();
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_answer_content_up:
                finish();
                break;
            //下一题
            case R.id.tv_answer_content_next:
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isCheck()) {
                        builder.append(list.get(i).getId());
                        builder.append(",");
                    }
                }
                builder.deleteCharAt(builder.length() - 1);
                answerInfo.setDmComplication(builder.toString());
                //跳页面
                Intent intent = new Intent(getPageContext(), EducationTangKnowledgeActivity.class);
                intent.putExtra("index", index);
                intent.putExtra("classList", (Serializable) classList);
                intent.putExtra("page", page + 1);
                intent.putExtra("allPage", allPage);
                intent.putExtra("answerInfo", answerInfo);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
