package com.vice.bloodpressure.utils;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartUtils {

    /**
     * 单双曲线   yValues2为null时是单曲线
     *
     * @param yValues1   数据1
     * @param lineColor1 数据1曲线颜色
     * @param yValues2   数据2
     * @param lineColor2 数据2曲线颜色
     */
    public void setLineCharts(LineChart mChart, float max, float min, @NonNull ArrayList<Entry> yValues1, int lineColor1, ArrayList<Entry> yValues2, int lineColor2) {
        int maxSize;
        if (yValues2 != null) {
            maxSize = Math.max(yValues1.size(), yValues2.size());
        } else {
            maxSize = yValues1.size();
        }
        setXAxis(mChart, maxSize);
        setLineChart(mChart);
        setYAxis(mChart, max, min);
        setData(mChart, yValues1, lineColor1, yValues2, lineColor2);
    }

    /**
     * @param mChart
     * @param max
     * @param min
     * @param yValues1
     * @param lineColor1
     * @param yValues2
     * @param lineColor2
     * @param yValues3
     * @param lineColor3
     * @param yValues4
     * @param lineColor4
     * @param yValues5
     * @param lineColor5
     * @param yValues6
     * @param lineColor6
     * @param yValues7
     * @param lineColor7
     * @param yValues8
     * @param lineColor8
     */
    public void setLineCharts(LineChart mChart, float max, float min, @NonNull ArrayList<Entry> yValues1, int lineColor1, ArrayList<Entry> yValues2, int lineColor2, @NonNull ArrayList<Entry> yValues3, int lineColor3, @NonNull ArrayList<Entry> yValues4, int lineColor4,
                              @NonNull ArrayList<Entry> yValues5, int lineColor5, @NonNull ArrayList<Entry> yValues6, int lineColor6, @NonNull ArrayList<Entry> yValues7, int lineColor7, @NonNull ArrayList<Entry> yValues8, int lineColor8) {
        int maxSize;
        if (yValues2 != null) {
            maxSize = Math.max(yValues1.size(), yValues2.size());
        } else {
            maxSize = yValues1.size();
        }
        setXAxis(mChart, maxSize);
        setLineChart(mChart);
        setYAxis(mChart, max, min);
        setData(mChart, yValues1, lineColor1, yValues2, lineColor2, yValues3, lineColor3, yValues4, lineColor4, yValues5, lineColor5, yValues6, lineColor6, yValues7, lineColor7, yValues8, lineColor8);
    }

    public void setLineChart(LineChart mChart) {
        //设置是否绘制chart边框的线
        mChart.setDrawBorders(false);
        //设置chart边框线颜色
        //        mChart.setBorderColor(Color.RED);
        //设置chart边框线宽度
        //        mChart.setBorderWidth(1f);
        //设置chart是否可以触摸
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);//默认0.9

        // //设置是否可以拖拽
        mChart.setDragEnabled(true);
        //        设置是否可以缩放 x和y，默认true
        mChart.setScaleEnabled(false);

        //设置是否可以通过双击屏幕放大图表。默认是true
        mChart.setDoubleTapToZoomEnabled(false);
        //是否启用网格背景
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);

        mChart.setPinchZoom(true);

        //描述信息
        Description description = new Description();
        description.setText("");
        //设置描述信息
        mChart.setDescription(description);
        //设置没有数据时显示的文本
        mChart.setNoDataText("没有数据...");
        // get the legend (only possible after setting data)
        mChart.getLegend().setEnabled(false);

    }

    private void setXAxis(LineChart mChart, int maxSize) {
        XAxis xAxis = mChart.getXAxis();
        //        xAxis.setTypeface(mTfLight);

        //        xAxis.setLabelCount(8, true);
        //X轴显示的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //X轴数据的颜色
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.parseColor("#9F9F9F"));
        //是否绘制X轴的网格线
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        xAxis.setAxisMinimum(1);
        xAxis.setAxisMaximum(Math.max(maxSize, 8));
        xAxis.setLabelCount(8, true);
        mChart.setVisibleXRangeMaximum(7);
        mChart.setExtraRightOffset(25f);//右间距

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });


    }

    private void setYAxis(LineChart mChart, float max, float min) {//左侧Y轴属性设置
        YAxis leftAxis = mChart.getAxisLeft();
        //        leftAxis.setTypeface(mTfLight);
        //Y轴数据的字体颜色
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        //左侧Y轴最大值
        leftAxis.setAxisMaximum(max);
        leftAxis.setLabelCount(6);
        //左侧Y轴最小值
        leftAxis.setAxisMinimum(min);
        //是否绘制Y轴网格线
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(Color.parseColor("#E3EEEC"));

        leftAxis.setDrawAxisLine(false);
        //        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //TODO 什么属性?
        leftAxis.setGranularityEnabled(true);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setTextSize(11f);
        leftAxis.setTextColor(Color.parseColor("#9F9F9F"));
        //左边Y轴添加限制线
        //        leftAxis.addLimitLine(limitLine);
        //右侧Y轴坐标
        mChart.getAxisRight().setEnabled(false);
    }


    private void setData(LineChart mChart, ArrayList<Entry> yValues1, int lineColor1, ArrayList<Entry> yValues2, int lineColor2) {
        LineDataSet set1, set2;
        LineData data;

        // create a dataset and give it a type
        set1 = new LineDataSet(yValues1, "数据1");

        //        set1.setCubicIntensity(1.0f);
        //数据对应的是左边还是右边的Y值
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        //线条的颜色
        set1.setColor(lineColor1);
        //表中数据圆点的颜色
        set1.setCircleColor(Color.WHITE);
        //表中数据线条的宽度
        set1.setLineWidth(1.5f);
        //表中数据圆点的半径
        set1.setCircleRadius(5f);
        //设置线面部分是否填充
        set1.setDrawFilled(false);
        //        //填充的颜色透明度
        //        set1.setFillAlpha(255);
        //        //填充颜色
        //        set1.setFillColor(ColorTemplate.rgb("ffffff"));
        set1.setHighLightColor(Color.parseColor("#D5D5D5"));
        //绘制的数据的圆点是否是实心还是空心
        set1.setDrawCircleHole(true);
        //        set1.setFillFormatter(new MyFillFormatter(0f));
        set1.setDrawHorizontalHighlightIndicator(false);
        set1.setCircleHoleColor(lineColor1);
        set1.setCircleHoleRadius(3f);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        // create a dataset and give it a type

        if (yValues2 != null) {
            set2 = new LineDataSet(yValues2, "数据2");
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            //线条的颜色
            set2.setColor(lineColor2);
            //表中数据圆点的颜色
            set2.setCircleColor(Color.WHITE);
            //表中数据线条的宽度
            set2.setLineWidth(1.5f);
            //表中数据圆点的半径
            set2.setCircleRadius(5f);
            //设置线面部分是否填充
            set2.setDrawFilled(false);
            //        //填充的颜色透明度
            //        set1.setFillAlpha(255);
            //        //填充颜色
            //        set1.setFillColor(ColorTemplate.rgb("ffffff"));
            set2.setHighLightColor(Color.parseColor("#D5D5D5"));
            //绘制的数据的圆点是否是实心还是空心
            set2.setDrawCircleHole(true);
            //        set1.setFillFormatter(new MyFillFormatter(0f));
            set2.setDrawHorizontalHighlightIndicator(false);
            set2.setCircleHoleColor(lineColor2);
            set2.setCircleHoleRadius(3f);
            set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            data = new LineData(set1, set2);
        } else {
            data = new LineData(set1);
        }
        //设置图标中显示数字的颜色
        //        data.setValueTextColor(Color.RED);
        data.setDrawValues(false);
        //设置图标中显示数字的大小
        //        data.setValueTextSize(9f);
        // set data
        mChart.setData(data);
    }


    private void setData(LineChart mChart, ArrayList<Entry> yValues1, int lineColor1, ArrayList<Entry> yValues2, int lineColor2, ArrayList<Entry> yValues3, int lineColor3,
                         ArrayList<Entry> yValues4, int lineColor4, ArrayList<Entry> yValues5, int lineColor5, ArrayList<Entry> yValues6, int lineColor6, ArrayList<Entry> yValues7,
                         int lineColor7, ArrayList<Entry> yValues8, int lineColor8) {
        LineDataSet set1, set2, set3, set4, set5, set6, set7, set8;
        LineData data;

        // create a dataset and give it a type
        set1 = new LineDataSet(yValues1, "数据1");

        //        set1.setCubicIntensity(1.0f);
        //数据对应的是左边还是右边的Y值
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        //线条的颜色
        set1.setColor(lineColor1);
        //表中数据圆点的颜色
        set1.setCircleColor(Color.WHITE);
        //表中数据线条的宽度
        set1.setLineWidth(1.5f);
        //表中数据圆点的半径
        set1.setCircleRadius(5f);
        //设置线面部分是否填充
        set1.setDrawFilled(false);
        //        //填充的颜色透明度
        //        set1.setFillAlpha(255);
        //        //填充颜色
        //        set1.setFillColor(ColorTemplate.rgb("ffffff"));
        set1.setHighLightColor(Color.parseColor("#D5D5D5"));
        //绘制的数据的圆点是否是实心还是空心
        set1.setDrawCircleHole(true);
        //        set1.setFillFormatter(new MyFillFormatter(0f));
        set1.setDrawHorizontalHighlightIndicator(false);
        set1.setCircleHoleColor(lineColor1);
        set1.setCircleHoleRadius(3f);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        // create a dataset and give it a type

        if (yValues2 != null) {
            set2 = new LineDataSet(yValues2, "数据2");
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            //线条的颜色
            set2.setColor(lineColor2);
            //表中数据圆点的颜色
            set2.setCircleColor(Color.WHITE);
            //表中数据线条的宽度
            set2.setLineWidth(1.5f);
            //表中数据圆点的半径
            set2.setCircleRadius(5f);
            //设置线面部分是否填充
            set2.setDrawFilled(false);
            //        //填充的颜色透明度
            //        set1.setFillAlpha(255);
            //        //填充颜色
            //        set1.setFillColor(ColorTemplate.rgb("ffffff"));
            set2.setHighLightColor(Color.parseColor("#D5D5D5"));
            //绘制的数据的圆点是否是实心还是空心
            set2.setDrawCircleHole(true);
            //        set1.setFillFormatter(new MyFillFormatter(0f));
            set2.setDrawHorizontalHighlightIndicator(false);
            set2.setCircleHoleColor(lineColor2);
            set2.setCircleHoleRadius(3f);
            set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            data = new LineData(set1, set2);
        } else {
            data = new LineData(set1);
        }

        if (yValues3 != null) {
            set3 = new LineDataSet(yValues3, "数据2");
            set3.setAxisDependency(YAxis.AxisDependency.LEFT);
            //线条的颜色
            set3.setColor(lineColor3);
            //表中数据圆点的颜色
            set3.setCircleColor(Color.WHITE);
            //表中数据线条的宽度
            set3.setLineWidth(1.5f);
            //表中数据圆点的半径
            set3.setCircleRadius(5f);
            //设置线面部分是否填充
            set3.setDrawFilled(false);
            //        //填充的颜色透明度
            //        set1.setFillAlpha(255);
            //        //填充颜色
            //        set1.setFillColor(ColorTemplate.rgb("ffffff"));
            set3.setHighLightColor(Color.parseColor("#D5D5D5"));
            //绘制的数据的圆点是否是实心还是空心
            set3.setDrawCircleHole(true);
            //        set1.setFillFormatter(new MyFillFormatter(0f));
            set3.setDrawHorizontalHighlightIndicator(false);
            set3.setCircleHoleColor(lineColor3);
            set3.setCircleHoleRadius(3f);
            set3.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            data = new LineData(set1, set3);
        } else {
            data = new LineData(set1);
        }

        if (yValues4 != null) {
            set4 = new LineDataSet(yValues4, "数据2");
            set4.setAxisDependency(YAxis.AxisDependency.LEFT);
            //线条的颜色
            set4.setColor(lineColor2);
            //表中数据圆点的颜色
            set4.setCircleColor(Color.WHITE);
            //表中数据线条的宽度
            set4.setLineWidth(1.5f);
            //表中数据圆点的半径
            set4.setCircleRadius(5f);
            //设置线面部分是否填充
            set4.setDrawFilled(false);
            //        //填充的颜色透明度
            //        set1.setFillAlpha(255);
            //        //填充颜色
            //        set1.setFillColor(ColorTemplate.rgb("ffffff"));
            set4.setHighLightColor(Color.parseColor("#D5D5D5"));
            //绘制的数据的圆点是否是实心还是空心
            set4.setDrawCircleHole(true);
            //        set1.setFillFormatter(new MyFillFormatter(0f));
            set4.setDrawHorizontalHighlightIndicator(false);
            set4.setCircleHoleColor(lineColor2);
            set4.setCircleHoleRadius(3f);
            set4.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            data = new LineData(set1, set4);
        } else {
            data = new LineData(set1);
        }
        //设置图标中显示数字的颜色
        //        data.setValueTextColor(Color.RED);
        data.setDrawValues(false);
        //设置图标中显示数字的大小
        //        data.setValueTextSize(9f);
        // set data
        mChart.setData(data);
    }

    private void setData(LineChart mChart, ArrayList<Entry> yValues, String dataTitle, int color) {

        if (yValues != null) {
            LineDataSet set = new LineDataSet(yValues, dataTitle);
            set.setAxisDependency(YAxis.AxisDependency.LEFT);
            //线条的颜色
            set.setColor(color);
            //表中数据圆点的颜色
            set.setCircleColor(Color.WHITE);
            //表中数据线条的宽度
            set.setLineWidth(1.5f);
            //表中数据圆点的半径
            set.setCircleRadius(5f);
            //设置线面部分是否填充
            set.setDrawFilled(false);
            //        //填充的颜色透明度
            //        set1.setFillAlpha(255);
            //        //填充颜色
            //        set1.setFillColor(ColorTemplate.rgb("ffffff"));
            set.setHighLightColor(Color.parseColor("#D5D5D5"));
            //绘制的数据的圆点是否是实心还是空心
            set.setDrawCircleHole(true);
            //        set1.setFillFormatter(new MyFillFormatter(0f));
            set.setDrawHorizontalHighlightIndicator(false);
            set.setCircleHoleColor(color);
            set.setCircleHoleRadius(3f);
            set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            LineData data = new LineData(set);
            //设置图标中显示数字的颜色
            //        data.setValueTextColor(Color.RED);
            data.setDrawValues(false);
            //设置图标中显示数字的大小
            //        data.setValueTextSize(9f);
            // set data
            mChart.setData(data);
        }
    }

}
