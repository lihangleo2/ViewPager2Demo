package com.leo.viewpager2demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.LogUtils
import com.leo.viewpager2demo.DataUtil.productDatas
import com.leo.viewpager2demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager2)
            .cancleOverScrollMode()
            .setOffscreenPageLimit(5)
            .setPreLoadLimit(3)
            .setLoadMoreListener(object : OnLoadMoreListener {
                override fun onLoadMore(smartAdapter: SmartViewPager2Adapter) {
                    LogUtils.dTag("数据预加载接口回调","加载更多----")
                }
            })
            .addData(productDatas(1))
    }


    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initViewPager2()

    }

    private fun initViewPager2() {
        mBinding.viewPager2.adapter = mAdapter

        //模拟向上加载数据
        mBinding.buttonFront.setOnClickListener {
            mAdapter.addFrontData(productDatas(mAdapter.getDataList().size+1))
        }

        //模拟向下加载数据
        mBinding.buttonBack.setOnClickListener {
            mAdapter.addData(productDatas(mAdapter.getDataList().size+1))
        }
    }

}