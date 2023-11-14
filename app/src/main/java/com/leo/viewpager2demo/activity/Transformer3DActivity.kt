package com.leo.viewpager2demo.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.leo.viewpager2demo.databinding.Activity3duseBinding
import com.leo.viewpager2demo.fragment.ImageFragment
import com.leo.viewpager2demo.fragment.TextFragment
import com.leo.viewpager2demo.util.DataUtil
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.transformer.SmartTransformer

/**
 * @Author leo2
 * @Date 2023/9/9
 */
class Transformer3DActivity : AppCompatActivity() {


    private val mAdapter by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager2)
            /**
             * 设置3D滑动效果
             * 你可以自定义，继承系统 ViewPager2.PageTransformer，然后通过 mViewPager2.setPageTransformer()，实现自定义效果
             * */
            .setPagerTransformer(SmartTransformer.TRANSFORMER_3D)
            .setOffscreenPageLimit(3)
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
        initActionBar()
        //
        mBinding.viewPager2.adapter=mAdapter
    }


    private fun initActionBar(){
        supportActionBar?.title="3dTransformer的使用"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}