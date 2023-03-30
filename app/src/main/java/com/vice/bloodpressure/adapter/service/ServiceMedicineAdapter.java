package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.ServiceInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ServiceMedicineAdapter extends RecyclerView.Adapter<ServiceMedicineAdapter.ViewHolder> {
    private Context context;
    private List<ServiceInfo> list;
    private IAdapterViewClickListener clickListener;


    public ServiceMedicineAdapter(Context context, List<ServiceInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_medicine, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceInfo info = list.get(position);
        holder.timeTextView.setText(info.getTime());
        holder.nameTextView.setText(info.getType());
        holder.rateTextView.setText(info.getRate());
        holder.valueTextView.setText(info.getData());
        clickOnClick clickOnClick = new clickOnClick(position);
        holder.editTextView.setOnClickListener(clickOnClick);
        holder.lookTextView.setOnClickListener(clickOnClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTextView;
        private TextView nameTextView;
        private TextView rateTextView;
        private TextView valueTextView;
        private TextView editTextView;
        private TextView lookTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_item_service_medicine_name);
            timeTextView = itemView.findViewById(R.id.tv_item_service_medicine_time);
            rateTextView = itemView.findViewById(R.id.tv_item_service_medicine_rate);
            valueTextView = itemView.findViewById(R.id.tv_item_service_medicine_value);
            editTextView = itemView.findViewById(R.id.tv_item_service_medicine_edit);
            lookTextView = itemView.findViewById(R.id.tv_item_service_medicine_look);
        }
    }

    private class clickOnClick implements View.OnClickListener {
        private int position;

        public clickOnClick(int position) {
            this.position = position;

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.adapterClickListener(position, v);
            }
        }
    }

}

