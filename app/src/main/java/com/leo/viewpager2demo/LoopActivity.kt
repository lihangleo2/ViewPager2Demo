package com.leo.viewpager2demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.databinding.ActivityLoopBinding
import com.leo.viewpager2demo.fragment.ImageFragment
import com.leo.viewpager2demo.fragment.TextFragment
import com.leo.viewpager2demo.util.DataUtil
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.transformer.SmartTransformer

/**
 * @Author leo
 * @Date 2023/9/13
 */
class LoopActivity : AppCompatActivity() {

    private val mAdapter by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager2)
            .cancleOverScrollMode()
            .setInfinite(true)
            .isAutoLoop()
            .addLifecycleObserver()
            .setLoopTime(3000L)
            .asGallery(ConvertUtils.dp2px(50f), ConvertUtils.dp2px(50f))
            .setPagerTransformer(SmartTransformer.TRANSFORMER_ALPHA_SCALE)
            .setOffscreenPageLimit(6)
            .setPreLoadLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            //可以在这里初始化数据
            .addData(DataUtil.productDatas(0, isLoadMore = true, isGallery = true))
//            .addData(SourceBean(95,"", R.mipmap.image_15,1))
//            .addData(SourceBean(96,"", R.mipmap.image_16,1))
//            .addData(SourceBean(97,"", R.mipmap.image_17,1))
    }

    private lateinit var mBinding: ActivityLoopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoopBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.viewPager2.adapter = mAdapter

        mBinding.btnTest.setOnClickListener {
            mAdapter.addFrontData(SourceBean(98, "", R.mipmap.gif_7, 1))
            ToastUtils.showShort("添加成功")
        }

        mBinding.btnTestOffmit.setOnClickListener {
//            mBinding.viewPager2.currentItem = 3
//            mBinding.viewPager2.setCurrentItem(1,false)
            mAdapter.start()
        }

//        ScreenUtils.getAppScreenWidth()
    }

}