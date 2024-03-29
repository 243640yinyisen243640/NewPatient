package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.EducationInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class EducationIntelligenceChildAdapter extends RecyclerView.Adapter<EducationIntelligenceChildAdapter.ViewHolder> {
    private Context context;
    private List<EducationInfo> list;

    private IAdapterViewClickListener clickListener;
    private int parentPosition;
    //type 1:智能学习 显示还有多少节课未学完 学习状态显示 2搜索 不显示学习状态   3：我的收藏 显示收藏目录  学习状态不显示
    private String type;

    public EducationIntelligenceChildAdapter(Context context, List<EducationInfo> list, int position, String type, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
        this.parentPosition = position;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_education_intelligence_study_child, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EducationInfo info = list.get(position);

        holder.titleTextView.setText(info.getEssayName());

        if ("1".equals(type)) {
            holder.stateTextView.setVisibility(View.VISIBLE);
            switch (info.getStatus()) {
                case "0":
                    holder.stateTextView.setText("待学习");
                    holder.stateTextView.setTextColor(ContextCompat.getColor(context,R.color.red_ff));
                    holder.stateTextView.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_bg_white_red_90_1));
                    break;
                case "1":
                    holder.stateTextView.setText("学习中");
                    holder.stateTextView.setTextColor(ContextCompat.getColor(context,R.color.blue_00));
                    holder.stateTextView.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_white_blue_90_00aeff));
                    break;
                case "2":
                    holder.stateTextView.setText("已完成");
                    holder.stateTextView.setTextColor(ContextCompat.getColor(context,R.color.main_base_color));
                    holder.stateTextView.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_bg_white_green_90_1));
                    break;
                default:
                    break;
            }
        } else if ("2".equals(type)) {
            holder.stateTextView.setVisibility(View.GONE);
        } else {
            holder.stateTextView.setVisibility(View.GONE);
        }

        clickExpandOnClick expandOnClick = new clickExpandOnClick(position);
        holder.clickLinearLayout.setOnClickListener(expandOnClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView stateTextView;
        private LinearLayout clickLinearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_education_study_child_title);
            stateTextView = itemView.findViewById(R.id.tv_education_study_child_state);
            clickLinearLayout = itemView.findViewById(R.id.ll_education_study_child_click);
        }
    }


    private class clickExpandOnClick implements View.OnClickListener {
        private int position;

        public clickExpandOnClick(int position) {
            this.position = position;

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.adapterClickListener(parentPosition, position, v);
            }
        }
    }
}

