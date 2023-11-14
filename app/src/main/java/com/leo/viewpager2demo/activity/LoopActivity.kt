package com.leo.viewpager2demo.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ConvertUtils
import com.leo.viewpager2demo.R
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
            /**
             * 实现自动滚动；如果不是无线循环模式下，在endIndex滑动下一条，会直接到第1条
             * */
            .isAutoLoop()
            .addLifecycleObserver()
            .setLoopTime(3000L)
            .setScrollTime(600L)
            .asGallery(ConvertUtils.dp2px(50f), ConvertUtils.dp2px(50f))
            .setPagerTransformer(SmartTransformer.TRANSFORMER_ALPHA_SCALE)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            //可以在这里初始化数据
            .addData(DataUtil.productDatas(0, isLoadMore = true, isGallery = true))
    }

    private lateinit var mBinding: ActivityLoopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoopBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initActionBar()
        //
        mBinding.viewPager2.adapter = mAdapter
    }

    private fun initActionBar() {
        supportActionBar?.title = "自动滚动的使用"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_loop, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.scroll_time_600 -> {
                mAdapter.setScrollTime(600L)
            }

            R.id.scroll_time_150 -> {
                mAdapter.setScrollTime(150L)
            }

            R.id.loop_time_3000 -> {
                mAdapter.setLoopTime(3000L)
            }

            R.id.loop_time_600 -> {
                mAdapter.setLoopTime(600L)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}