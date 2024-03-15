package com.leo.viewpager2demo

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.leo.viewpager2demo.bean.SourceBean
import com.smart.adapter.SmartViewPager2Adapter

/**
 * @Author leo
 * @Address https://github.com/lihangleo2
 * @Date 2024/3/15
 */
object TestKotlin {
    fun testFun(fragment:Fragment,viewPager2: ViewPager2){
        var mAdapter = SmartViewPager2Adapter.Builder<SourceBean>(fragment)
            .build(viewPager2)

        var mOtherAdapter = SmartViewPager2Adapter.NoDataBuilder(fragment)
            .build(viewPager2)


    }
}