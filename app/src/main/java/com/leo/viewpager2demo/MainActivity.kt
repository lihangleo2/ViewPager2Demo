package com.leo.viewpager2demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.LogUtils
import com.leo.viewpager2demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val mAdapter by lazy {
        SmartViewPager2Adapter(this,mBinding.viewPager2).apply {
            addData(mutableListOf(
                SourceBean(1, "图1", R.mipmap.image_1, 1),
                SourceBean(2, "图2", R.mipmap.image_2, 1),
                SourceBean(3, "图3", R.mipmap.image_3, 1),
                SourceBean(4, "图4", R.mipmap.image_4, 1),
                SourceBean(5, "图5", R.mipmap.image_5, 1),
                SourceBean(6, "图6", R.mipmap.image_6, 1),
                SourceBean(7, "图7", R.mipmap.image_7, 1),
            ))
        }
    }

    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initViewPager2()
        //测试状态向上加载数据和向下加载数据
        mBinding.button.setOnClickListener {
//            mAdapter.addData(mutableListOf(SourceBean(21, "图8", R.mipmap.image_8, 1)))

            //向上加载
            var list = mutableListOf(SourceBean(22, "图9", R.mipmap.image_9, 1))
            mAdapter.addFrontData(list)
            mBinding.viewPager2.setCurrentItem(list.size+mBinding.viewPager2.currentItem,false)
            //注意如果式空数据的情况就要走下
//            mAdapter.notifyDataSetChanged()
        }
    }

    private fun initViewPager2() {
        mBinding.viewPager2.run {
            adapter = mAdapter
            offscreenPageLimit=3
        }

        mBinding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                LogUtils.dTag("这两个监听怎么走呢","=============来自Activity页面")
                if (state == ViewPager2.SCROLL_STATE_IDLE) {

                }
            }
        })
    }
}