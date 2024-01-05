package com.leo.viewpager2demo.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.databinding.ActivitySideuseBinding
import com.leo.viewpager2demo.fragment.ImageFragment
import com.leo.viewpager2demo.fragment.TextFragment
import com.leo.viewpager2demo.util.DataUtil
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.interf.onSideListener

/**
 * @Author leo
 * @Address https://github.com/lihangleo2
 * @Date 2023/10/20
 */
class SideUseActivity : AppCompatActivity() {
    private val mAdapter by lazy {
        SmartViewPager2Adapter.Builder<SourceBean>(this)
            .setOffscreenPageLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            .build(mBinding.viewPager2)
            .addData(DataUtil.productDatas(0, isLoadMore = true, isGallery = true, 4))
            /**
             * 设置左右边界滑动监听
             * */
            .setOnSideListener(object : onSideListener {
                override fun onLeftSide() {
                    ToastUtils.showShort("触发左边缘事件")

                }
                override fun onRightSide() {
                    ToastUtils.showShort("触发右边缘事件")
                }

            })
    }

    private lateinit var mBinding: ActivitySideuseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySideuseBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initActionBar()
        //
        mBinding.viewPager2.adapter = mAdapter
    }

    private fun initActionBar() {
        supportActionBar?.title = "边界滑动监听的使用"
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