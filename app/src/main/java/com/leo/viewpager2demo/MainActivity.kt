package com.leo.viewpager2demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.util.DataUtil.productDatas
import com.leo.viewpager2demo.databinding.ActivityMainBinding
import com.leo.viewpager2demo.fragment.ImageFragment
import com.leo.viewpager2demo.fragment.TextFragment
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.interf.OnLoadMoreListener

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
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            //可以在这里初始化数据
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
            var list = productDatas(mAdapter.getDataList().size+1)
            list.add(0, SourceBean(99, "一段文字", -1, 2))
            list.add(0, SourceBean(99, "浅挚绊离兮", -1, 2))
            list.add(0, SourceBean(99, "微凉徒眸意", -1, 2))
            mAdapter.addData(list)
        }
    }

}