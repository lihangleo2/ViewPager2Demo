package com.leo.viewpager2demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ConvertUtils
import com.leo.viewpager2demo.databinding.Activity3duseBinding
import com.leo.viewpager2demo.databinding.ActivityGalleryBinding
import com.leo.viewpager2demo.fragment.ImageFragment
import com.leo.viewpager2demo.fragment.TextFragment
import com.leo.viewpager2demo.util.DataUtil
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.transformer.SmartTransformer

/**
 * @Author leo123456
 * @Date 2023/9/9
 */
class Transformer3DActivity : AppCompatActivity() {


    private val mAdapter by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager2)
            .setPagerTransformer(SmartTransformer.TRANSFORMER_3D)
            .setOffscreenPageLimit(6)
            .setPreLoadLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            //可以在这里初始化数据
            .addData(DataUtil.productDatas(0, isLoadMore = true, isGallery = true))
    }


    private lateinit var mBinding: Activity3duseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = Activity3duseBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.viewPager2.adapter=mAdapter
    }


}