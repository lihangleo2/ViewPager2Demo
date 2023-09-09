package com.smart.adapter.transformer;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

/**
 * @Author leo
 * @Date 2023/8/23
 */
public class TransAlphScaleFormer implements ViewPager2.PageTransformer {
    public static float MIN_ALPHA = 0.5f;
    public static float MIN_SCALE = 0.8f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1 || position > 1) {
            page.setAlpha(MIN_ALPHA);
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) { // [-1,1]
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            if (position < 0) {
                float scaleX = 1 + 0.2f * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            } else {
                float scaleX = 1 - 0.2f * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            }
            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        }
    }
}
