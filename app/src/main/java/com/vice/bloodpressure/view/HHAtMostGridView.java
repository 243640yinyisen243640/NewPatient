package com.vice.bloodpressure.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 重写GridView，实现GridView的高度为GridView的行数高度*GridView的行数
 * @author yuan
 *
 */
public class HHAtMostGridView extends GridView 
{

	public HHAtMostGridView(Context context, AttributeSet attrs, int defStyle) 
	{
		super(context, attrs, defStyle);
	}
	public HHAtMostGridView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
	}
	public HHAtMostGridView(Context context) 
	{
		super(context);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
