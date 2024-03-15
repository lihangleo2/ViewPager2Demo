package com.leo.viewpager2demo.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.leo.viewpager2demo.R
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.databinding.ActivityGalleryBinding
import com.leo.viewpager2demo.databinding.ActivityNoDataBinding
import com.leo.viewpager2demo.fragment.ImageFragment
import com.leo.viewpager2demo.fragment.NoDataFragment
import com.leo.viewpager2demo.fragment.TextFragment
import com.leo.viewpager2demo.util.DataUtil
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.indicator.SmartIndicator
import com.smart.adapter.transformer.SmartTransformer

/**
 * @Author leo
 * @Address https://github.com/lihangleo2
 * @Date 2024/3/15
 */
class NoDataActivity : AppCompatActivity() {
    private val mAdapter by lazy {
        SmartViewPager2Adapter.NoDataBuilder(this)
            .overScrollNever()
            .withIndicator()
            .asGallery(ConvertUtils.dp2px(50f), ConvertUtils.dp2px(50f))
            .setPagerTransformer(SmartTransformer.TRANSFORMER_ALPHA_SCALE)
            .build(mBinding.viewPager2)
            .setFragmentList(NoDataFragment.newInstance("首页", R.color.background_home),
                NoDataFragment.newInstance("文档", R.color.background_file),
                NoDataFragment.newInstance("管理", R.color.background_manager),
                NoDataFragment.newInstance("我的", R.color.background_mine))
    }


    private lateinit var mBinding: ActivityNoDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNoDataBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initActionBar()
        //
        mBinding.viewPager2.adapter = mAdapter
    }

    private fun initActionBar() {
        supportActionBar?.title = "无数据源的使用"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}