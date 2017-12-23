package com.projects.deus_ex_machina.clustereducation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Nikita Kiselov on 15-Dec-17.
 * ClusterEducation
 */

public class PollListView extends ListView {
    private android.view.ViewGroup.LayoutParams params;
    private int oldCount = 0;

    public PollListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED));

        int childHeight = (int) (getMeasuredHeight()*(1.04)) - (getListPaddingTop() + getListPaddingBottom() +  getVerticalFadingEdgeLength() * 2);

        // on a first run let's have a space for at least one child so it'll trigger remeasurement
        int fullHeight = getListPaddingTop() + getListPaddingBottom() + childHeight*(getCount());

        int newChildHeight = 0;
        for (int x = 0; x<getChildCount(); x++ ){
            View childAt = getChildAt(x);

            if (childAt != null) {
                int height = childAt.getHeight();
                newChildHeight += height;
            }
        }

        //on a second run with actual items - use proper size
        if (newChildHeight != 0)
            fullHeight = getListPaddingTop() + getListPaddingBottom() + newChildHeight;

        setMeasuredDimension(getMeasuredWidth(), fullHeight);
    }

}
