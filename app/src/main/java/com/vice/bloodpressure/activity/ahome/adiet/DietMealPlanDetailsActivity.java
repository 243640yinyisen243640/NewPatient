package com.vice.bloodpressure.activity.ahome.adiet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.DietMealPlanWeekAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.model.MealInfo;
import com.vice.bloodpressure.utils.DataUtils;
import com.vice.bloodpressure.utils.TurnUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.view.CirclePercentView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:饮食方案详细
 */
public class DietMealPlanDetailsActivity extends UIBaseLoadActivity {
    private TextView zhushiTv, shucaiTv, roudanbaiTv, jiangruTv, youTv, yanTv;
    private TextView allkTextView;
    private PieChart allProportionRc;
    private CirclePercentView carbonProportionRc;
    private CirclePercentView proteinProportionRc;
    private CirclePercentView fatProportionRc;
    private TextView carbonTv;
    private TextView proteinTv;
    private TextView fatTv;
    private TextView moreTv;
    private RecyclerView sevenPlanRv;
    private TextView refreshTv;
    private TextView breakfastTv;
    private TextView lunchTv;
    private TextView dinnerTv;

    private MealInfo mealInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("饮食方案");
        topViewManager().moreTextView().setText("重新制定");
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), DietProgrammeBeginActivity.class));
        });
        initViews();
        initValues();
        loadViewManager().changeLoadState(LoadStatus.LOADING);
    }

    private void initValues() {
        Log.i("yys", "seven" + DataUtils.getSevendate());
        Log.i("yys", "week" + DataUtils.get7week());
        //        DietMealPlanWeekAdapter adapter = new DietMealPlanWeekAdapter();
    }


    @Override
    protected void onPageLoad() {

        Call<String> requestCall = HomeDataManager.getDietPlan(UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {
                mealInfo = (MealInfo) response.object;
                bindData();
            } else {
                //                ToastUtils.getInstance().showToast(getPageContext(), response.msg);

            }

        }, (call, t) -> {

            //            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("getDietPlan", requestCall);
    }

    private void bindData() {
        zhushiTv.setText(mealInfo.getDietNutritionVo().getCereals());
        shucaiTv.setText(mealInfo.getDietNutritionVo().getVegetable());
        roudanbaiTv.setText(mealInfo.getDietNutritionVo().getMeat());
        jiangruTv.setText(mealInfo.getDietNutritionVo().getDrinks());
        yanTv.setText(mealInfo.getDietNutritionVo().getSalt());
        youTv.setText(mealInfo.getDietNutritionVo().getOil());
        allkTextView.setText(mealInfo.getThreeMealVo().getSumCalorie());

        List<String> rateString = new ArrayList<>();
        rateString.add("50");
        rateString.add("20");
        rateString.add("10");
        rateString.add("20");
        showPieChart(allProportionRc, getPieChartData(rateString, null));
        allkTextView.setText(mealInfo.getThreeMealVo().getLowCarbsCalorie() + "千卡" + "—" + mealInfo.getThreeMealVo().getHighCarbsCalorie() + "千卡\n"
                + mealInfo.getThreeMealVo().getLowCarbs() + "g" + "—" + mealInfo.getThreeMealVo().getHighCarbs() + "g");
        proteinTv.setText(mealInfo.getThreeMealVo().getLowPro() + "千卡" + "—" + mealInfo.getThreeMealVo().getHighPro() + "千卡\n"
                + mealInfo.getThreeMealVo().getLowProCalorie() + "g" + "—" + mealInfo.getThreeMealVo().getHighProCalorie() + "g");

        fatTv.setText(mealInfo.getThreeMealVo().getFat() + "千卡+“   " + mealInfo.getThreeMealVo().getFatCalorie() + "g");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        sevenPlanRv.setLayoutManager(linearLayoutManager);
        DietMealPlanWeekAdapter adapter = new DietMealPlanWeekAdapter(getPageContext(), mealInfo.getExclusiveDietPlanVos(), new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {

            }

            @Override
            public void adapterClickListener(int position, int index, View view) {

            }
        });

        sevenPlanRv.setAdapter(adapter);

    }

    /**
     * 有关饼状图
     *
     * @param pieChart
     * @param pieList
     */
    private void showPieChart(PieChart pieChart, List<PieEntry> pieList) {
        PieDataSet dataSet = new PieDataSet(pieList, "");
        int[] intArray = getResources().getIntArray(R.array.diet_plan_colors);
        dataSet.setColors(intArray);
        PieData pieData = new PieData(dataSet);


        pieChart.setUsePercentValues(true);
        //设置使用百分比
        //设置描述
        pieChart.getDescription().setEnabled(false);
        //设置半透明圆环的半径, 0为透明
        pieChart.setTransparentCircleRadius(0f);

        pieChart.setHighlightPerTapEnabled(false);//点击是否放大

        pieChart.setCenterText("多少千卡");//设置环中的文字
        pieChart.setCenterTextSize(15f);//设置环中文字的大小
        pieChart.setDrawCenterText(false);//设置绘制环中文字
        //设置初始旋转角度
        pieChart.setRotationAngle(-15);

        //设置这个数字，越大，这个饼越细，越小，饼越胖
        pieChart.setHoleRadius(78f);
        //设置内圆和外圆的一个交叉园的半径，这样会凸显内外部的空间
        pieChart.setTransparentCircleRadius(31f);


        //数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(80f);

        // 设置饼块之间的间隔
        dataSet.setSliceSpace(1f);
        dataSet.setHighlightEnabled(true);
        // 显示图例
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        // 和四周相隔一段距离,显示数据
        pieChart.setExtraOffsets(0, 0, 0, 0);

        // 设置pieChart图表是否可以手动旋转
        pieChart.setRotationEnabled(false);
        // 设置piecahrt图表点击Item高亮是否可用
        pieChart.setHighlightPerTapEnabled(false);
        // 设置pieChart图表展示动画效果，动画运行1.4秒结束
        pieChart.animateY(1400, Easing.EaseInOutQuad);
        //设置pieChart是否只显示饼图上百分比不显示文字
        pieChart.setDrawEntryLabels(false);
        // 绘制内容value，设置字体颜色大小
        pieData.setDrawValues(false);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setData(pieData);
        // 更新 piechart 视图
        pieChart.postInvalidate();
    }

    /**
     * 有关饼状图
     *
     * @param list1
     * @param list2
     * @return
     */
    private List<PieEntry> getPieChartData(List<String> list1, List<String> list2) {
        List<PieEntry> mPie = new ArrayList<>();
        if (list1 != null && list2 != null) {
            for (int i = 0; i < list1.size(); i++) {
                // 参数1为 value，参数2为 data。
                // 如 PieEntry(0.15F, "90分以上");  表示90分以上的人占比15%。
                PieEntry pieEntry = new PieEntry(TurnUtils.getFloat(list1.get(i), 0), list2.get(i));
                mPie.add(pieEntry);
            }
        }
        return mPie;
    }

    private void initViews() {
        View view = View.inflate(getPageContext(), R.layout.activity_meal_plan_details, null);
        zhushiTv = view.findViewById(R.id.tv_zhushi_num);
        shucaiTv = view.findViewById(R.id.tv_shucai_num);
        roudanbaiTv = view.findViewById(R.id.tv_roudanbai_num);
        jiangruTv = view.findViewById(R.id.tv_jiangrulei_num);
        yanTv = view.findViewById(R.id.tv_yanlei_num);
        youTv = view.findViewById(R.id.tv_youlei_num);
        allkTextView = view.findViewById(R.id.tv_carbon_proportion_all);
        allProportionRc = view.findViewById(R.id.pc_all_proportion);
        carbonProportionRc = view.findViewById(R.id.cpv_carbon_proportion);
        proteinProportionRc = view.findViewById(R.id.cpv_protein_proportion);
        fatProportionRc = view.findViewById(R.id.cpv_fat_proportion);
        carbonTv = view.findViewById(R.id.tv_carbon_proportion);
        proteinTv = view.findViewById(R.id.tv_protein_proportion);
        fatTv = view.findViewById(R.id.tv_fat_proportion);
        moreTv = view.findViewById(R.id.tv_seven_more);
        sevenPlanRv = view.findViewById(R.id.rv_seven_plan);
        refreshTv = view.findViewById(R.id.tv_seven_refresh);
        breakfastTv = view.findViewById(R.id.tv_seven_breakfast);
        lunchTv = view.findViewById(R.id.tv_seven_lunch);
        dinnerTv = view.findViewById(R.id.tv_seven_dinner);
        containerView().addView(view);
    }

}
