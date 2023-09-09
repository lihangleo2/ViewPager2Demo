package com.smart.adapter.transformer;

import static java.lang.Math.pow;

import android.animation.TimeInterpolator;
import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

/**
 * @Author leo123456
 * @Date 2023/9/9
 */
public class StereoPagerVerticalTransformer implements ViewPager2.PageTransformer {
    private static final float MAX_ROTATE_Y = -90;

    private static final TimeInterpolator sInterpolator = new TimeInterpolator() {
        @Override
        public float getInterpolation(float input) {
            if (input < 0.7) {
                return input * (float) pow(0.7, 3) * MAX_ROTATE_Y;
            } else {
                return (float) pow(input, 4) * MAX_ROTATE_Y;
            }
        }
    };

    private final float pageWidth;

    public StereoPagerVerticalTransformer(float pageWidth) {
        this.pageWidth = pageWidth;
    }

    public void transformPage(View view, float position) {

        view.setPivotX(view.getHeight() / 2);
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setPivotY(0);
            view.setRotationX(90);
        } else if (position <= 0) { // [-1,0]
            view.setPivotY(pageWidth);
            view.setRotationX(-sInterpolator.getInterpolation(-position));
        } else if (position <= 1) { // (0,1]
            view.setPivotY(0);
            view.setRotationX(sInterpolator.getInterpolation(position));
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setPivotY(0);
            view.setRotationX(90);
        }
    }
}
