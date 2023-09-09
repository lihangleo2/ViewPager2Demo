package com.smart.adapter.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import java.lang.reflect.Field

/**
 * @类名
 * @描述
 * @作者 RinKamiuezono
 * @日期 2022/6/12
 * @版本 1.0
 */
object ViewPager2Util {
    @JvmStatic
    fun cancleViewPagerShadow(viewPager2:ViewPager2){
        try {
            //取消viewPager2的阴影
            val recyclerViewField: Field =
                ViewPager2::class.java.getDeclaredField("mRecyclerView")
            recyclerViewField.isAccessible = true
            val recyclerView = recyclerViewField[viewPager2] as RecyclerView
            recyclerView.overScrollMode = View.OVER_SCROLL_NEVER
        } catch (ignore: Exception) {
        }
    }

    @JvmStatic
    fun getRecycleFromViewPager2(viewPager2:ViewPager2) : RecyclerView? {
        return try {
            //取消viewPager2的阴影
            val recyclerViewField: Field =
                ViewPager2::class.java.getDeclaredField("mRecyclerView")
            recyclerViewField.isAccessible = true
            val recyclerView = recyclerViewField[viewPager2] as RecyclerView
            recyclerView
        } catch (ignore: Exception) {
            null
        }
    }
}