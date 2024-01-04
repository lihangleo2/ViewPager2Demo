package com.leo.viewpager2demo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ktx.immersionBar
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.databinding.ActivityKotuseBinding
import com.leo.viewpager2demo.fragment.ImageFragment
import com.leo.viewpager2demo.fragment.TextFragment
import com.leo.viewpager2demo.util.DataUtil
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.interf.OnLoadMoreListener
import com.smart.adapter.interf.OnRefreshListener

/**
 * @Author leo2
 * @Date 2023/9/8
 */
class TiktokActivity : AppCompatActivity() {


    private val mAdapter by lazy {
        SmartViewPager2Adapter<SourceBean>(this, mBinding.viewPager2)
              // 是否取消滑动最边上时出现的阴影
//            .cancleOverScrollMode()
            .setOffscreenPageLimit(5)
            .setPreLoadLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            //可以在这里初始化数据
//            .addData(DataUtil.productDatas(0, true))
    }


    private lateinit var mBinding: ActivityKotuseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersionBar {
            statusBarDarkFont(true)
        }
        mBinding = ActivityKotuseBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initViewPager2()

    }

    private fun initViewPager2() {
        mBinding.viewPager2.adapter = mAdapter

        //模拟向上加载数据
        mBinding.buttonFront.setOnClickListener {
            mAdapter.addFrontData(DataUtil.productFrontDatas(mAdapter))
            ToastUtils.showShort("向上加载数据成功..")
        }

        //模拟向下加载数据
        mBinding.buttonBack.setOnClickListener {
            mAdapter.addData(DataUtil.productBackDatas(mAdapter))
            ToastUtils.showShort("向下加载数据成功..")
        }

        //设置监听加载数据
//        mBinding.buttonAuto.setOnClickListener {
//            mBinding.layerBtn.visibility = View.GONE
//            ToastUtils.showShort("设置成功，请上下滑动")
//            mAdapter.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
//                override fun onRefresh(smartAdapter: SmartViewPager2Adapter<*>) {
//                    ToastUtils.showShort("触发向上监听 ++++ ，2s后加载数据")
//                    mBinding.viewPager2.postDelayed({
//                        mAdapter.addFrontData(DataUtil.productFrontDatas(mAdapter))
//                    }, 2000)
//                }
//
//                override fun onLoadMore(smartAdapter: SmartViewPager2Adapter<*>) {
//                    ToastUtils.showShort("触发向下监听 ---- ，2s后加载数据")
//                    mBinding.viewPager2.postDelayed({
//                        mAdapter.addData(DataUtil.productBackDatas(mAdapter))
//                    }, 2000)
//                }
//
//            })
//        }

        mBinding.buttonAuto.setOnClickListener {

            mAdapter.setOnLoadMoreListener(object :OnLoadMoreListener{
                override fun onLoadMore(smartAdapter: SmartViewPager2Adapter<*>) {
                    ToastUtils.showShort("触发向下监听 ---- ，2s后加载数据")
                }

            })

            mAdapter.setOnRefreshListener(object :OnRefreshListener{
                override fun onRefresh(smartAdapter: SmartViewPager2Adapter<*>) {
                    ToastUtils.showShort("触发向上监听 ++++ ，2s后加载数据")
                }
            })
        }

    }

}