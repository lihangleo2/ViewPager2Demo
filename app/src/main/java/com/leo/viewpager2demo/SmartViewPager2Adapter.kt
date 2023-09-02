package com.leo.viewpager2demo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.blankj.utilcode.util.LogUtils

/**
 * @ClassName
 * @Description InnerPagerState2Adapter
 * @Author Charity
 * @Date 2021/1/27
 * @Version 1.0
 */
class SmartViewPager2Adapter : FragmentStateAdapter {
    private val dataList = mutableListOf<SourceBean>()
    private lateinit var mViewPager2: ViewPager2

    constructor(fragmentActivity: FragmentActivity,bindViewPager2: ViewPager2) : super(fragmentActivity){
        this.mViewPager2 = bindViewPager2
        registerOnPageChange()
    }

    constructor(fragment: Fragment) : super(fragment)

    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(
        fragmentManager,
        lifecycle
    )



    override fun createFragment(position: Int): Fragment {
        var bean = dataList[position]
        return ImageFragment.newInstance(bean)
    }

    override fun getItemCount() = dataList.size

    override fun getItemId(position: Int): Long {
        return dataList[position].hashCode().toLong()
    }

    fun addData(list: MutableList<SourceBean>) {
        if (list.isNullOrEmpty()) {
            return
        }
        var lastIndex = dataList.size
        dataList.addAll(list)
        notifyItemRangeChanged(lastIndex,list.size)
    }

    fun addFrontData(list: MutableList<SourceBean>) {
        if (list.isNullOrEmpty()) {
            return
        }
        dataList.addAll(0, list)
    }

    public fun getDataList(): MutableList<SourceBean> {
        return dataList
    }

    private fun registerOnPageChange(){
        if (mViewPager2==null){
            throw IllegalArgumentException(
                    "the bindView viewPager2 can not be null");
        }

        //SmartViewPager2Adapter里的监听，用于处理预加载，以及在ViewPager2空闲的时候，处理加载向上插入的数据
        mViewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                LogUtils.dTag("这两个监听怎么走呢","----------来自Adapter")
            }

        })
    }

}