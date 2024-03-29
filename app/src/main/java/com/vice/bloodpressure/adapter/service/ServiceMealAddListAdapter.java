package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.MealChildInfo;
import com.vice.bloodpressure.utils.TurnUtils;

import java.util.List;

public class ServiceMealAddListAdapter extends XyBaseAdapter<MealChildInfo> {
    private ImageView fireImageView;
    private TextView fireTextView;

    public ServiceMealAddListAdapter(Context context, List<MealChildInfo> list, ImageView fireImageView, TextView fireTextView) {
        super(context, list);
        this.fireImageView = fireImageView;
        this.fireTextView = fireTextView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_service_meal_add, null);
            holder.titleTextView = convertView.findViewById(R.id.tv_service_meal_add_title);
            holder.fireTextView = convertView.findViewById(R.id.tv_service_meal_add_fire);
            holder.numEditText = convertView.findViewById(R.id.ev_service_meal_add_num);
            holder.numEditText.setTag(position);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MealChildInfo info = getList().get(position);

        holder.numEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                float fixedWeight = TurnUtils.getFloat(info.getFixedWeight(), 0);
                float kcalval = TurnUtils.getFloat(info.getKcalval(), 0) / fixedWeight;
                String s1 = holder.numEditText.getText().toString().trim();
                int allK = (int) (TurnUtils.getFloat(s1, 0) * kcalval);
                String num = String.valueOf(allK);
                holder.fireTextView.setText(num);
                getList().get((Integer) holder.numEditText.getTag()).setNum(num);
                getList().get((Integer) holder.numEditText.getTag()).setNum1(s1);
                int nums = 0;
                for (int i = 0; i < getList().size(); i++) {
                    String num1 = getList().get(i).getNum();
                    if (TextUtils.isEmpty(num1)){
                        num1 = "0";
                    }
                    nums = nums + Integer.parseInt(num1);
                }
                fireTextView.setText(nums+"");
                fireImageView.setImageResource(R.drawable.service_meal_no_have_data);
            }
        });

        holder.titleTextView.setText(info.getFoodname());

        return convertView;
    }


    private class ViewHolder {
        TextView titleTextView;
        TextView fireTextView;
        EditText numEditText;
    }
}
