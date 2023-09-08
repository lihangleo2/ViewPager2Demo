package com.leo.viewpager2demo;

import static com.leo.viewpager2demo.util.DataUtil.productDatas;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.leo.viewpager2demo.fragment.ImageFragment;
import com.smart.adapter.SmartViewPager2Adapter;
import com.smart.adapter.interf.OnLoadMoreListener;

/**
 * @Author leo
 * @Date 2023/9/8
 */
public class JavaUseActivity extends FragmentActivity {
    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setContentView(R.layout.activity_main);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        SmartViewPager2Adapter smartViewPager2Adapter = new SmartViewPager2Adapter(this,viewPager2)
                .cancleOverScrollMode()
                .setOffscreenPageLimit(5)
                .setPreLoadLimit(3)
                .setLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(@NonNull SmartViewPager2Adapter smartAdapter) {

                    }
                }).addFragment(1, ImageFragment.class)
                .addData(productDatas(1));

        ImageFragment imageFragment = new ImageFragment();
    }
}
