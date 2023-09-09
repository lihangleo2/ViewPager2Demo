package com.leo.viewpager2demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.gyf.immersionbar.ktx.immersionBar
import com.leo.viewpager2demo.databinding.ActivityGalleryBinding
import com.leo.viewpager2demo.databinding.ActivityKotuseBinding
import com.leo.viewpager2demo.fragment.ImageFragment
import com.leo.viewpager2demo.fragment.TextFragment
import com.leo.viewpager2demo.util.DataUtil
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.interf.OnRefreshLoadMoreListener
import com.smart.adapter.transformer.SmartTransformer

/**
 * @Author leo123456
 * @Date 2023/9/9
 */
class GalleryUseActivity : AppCompatActivity() {


    private val mAdapter by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager2)
            .cancleOverScrollMode()
            .setPagerTransformer(SmartTransformer.TRANSFORMER_ALPHA_SCALE)
            .setOffscreenPageLimit(6)
            .setPreLoadLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            //可以在这里初始化数据
            .addData(DataUtil.productDatas(0, isLoadMore = true, isGallery = true))
    }


    private lateinit var mBinding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.viewPager2.adapter=mAdapter
    }


}