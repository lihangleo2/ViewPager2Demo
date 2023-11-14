package com.leo.viewpager2demo.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.leo.viewpager2demo.R
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.databinding.ActivityIndicatorBinding
import com.leo.viewpager2demo.fragment.ImageFragment
import com.leo.viewpager2demo.fragment.TextFragment
import com.leo.viewpager2demo.util.DataUtil
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.indicator.SmartIndicator

/**
 * @Author leo
 * @Address https://github.com/lihangleo2
 * @Date 2023/10/24
 */
class IndicatorActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityIndicatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityIndicatorBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initActionBar()
        //
        initIndicator1()
        initIndicator2()
        initIndicator3()
        initIndicator4()
    }

    /**
     * 1.api自带方式实现指示器（注意此方式需要viewPager2的父布局为ConstraintLayout）
     * * 圆形指示器，默认位置为居中底部
     * * .withIndicator(SmartIndicator.CIRCLE)
     * * 原型指示器，显示在左下角
     * * .withIndicator(SmartIndicator.CIRCLE,SmartGravity.LEFT_BOTTOM, ConvertUtils.dp2px(20f),ConvertUtils.dp2px(20f))
     * */
    private val mAdapter by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager1)
            //关键代码
            .withIndicator(SmartIndicator.CIRCLE)
            //设置指示器位置代码
//            .withIndicator(SmartIndicator.CIRCLE,SmartGravity.LEFT_BOTTOM, ConvertUtils.dp2px(20f),ConvertUtils.dp2px(20f))
            .setOffscreenPageLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            .addData(DataUtil.productDatas(0, isLoadMore = true, isGallery = true, 4))
    }


    private fun initIndicator1() {
        mBinding.viewPager1.adapter = mAdapter
    }




    /**
     * 2.在xml里使用指示器：超强自定义指示器
     * */
    private val mAdapter2 by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager2)
            .setInfinite(true)
            .isAutoLoop(true)
            //关键代码，xml里的指示器
            .withIndicator(mBinding.circleIndicator)
            .setOffscreenPageLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            .addData(DataUtil.productDatas(0, isLoadMore = true, isGallery = true, 4))
    }
    private fun initIndicator2() {
        mBinding.viewPager2.adapter = mAdapter2
    }


    /**
     * 2.api使用 线性指示器 lineIndicator
     * */
    private val mAdapter3 by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager3)
            //线性指示器
            .withIndicator(SmartIndicator.LINE)
            .setOffscreenPageLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            .addData(DataUtil.productDatas(0, isLoadMore = true, isGallery = true, 4))
    }
    private fun initIndicator3() {
        mBinding.viewPager3.adapter = mAdapter3
    }



    /**
     * 2.在xml里使用指示器：超强自定义指示器
     * */
    private val mAdapter4 by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager4)
            //关键代码，xml里的指示器
            .withIndicator(mBinding.lineIndicator)
            .setOffscreenPageLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            .addData(DataUtil.productDatas(0, isLoadMore = true, isGallery = true, 4))
    }
    private fun initIndicator4() {
        mBinding.viewPager4.adapter = mAdapter4
    }



    private fun initActionBar() {
        supportActionBar?.title = "指示器的使用"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_gallery, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.add_front_data -> {
                mAdapter.addFrontData(SourceBean((mAdapter.getItem(0) as SourceBean).id - 1, "", R.mipmap.image_15, 1))
                mAdapter2.addFrontData(SourceBean((mAdapter.getItem(0) as SourceBean).id - 1, "", R.mipmap.image_15, 1))
                mAdapter3.addFrontData(SourceBean((mAdapter.getItem(0) as SourceBean).id - 1, "", R.mipmap.image_15, 1))
                mAdapter4.addFrontData(SourceBean((mAdapter.getItem(0) as SourceBean).id - 1, "", R.mipmap.image_15, 1))
                ToastUtils.showShort("添加成功")
            }

            R.id.add_data -> {
                mAdapter.addData(SourceBean((mAdapter.getItem(mAdapter.itemCount - 1) as SourceBean).id + 1, "", R.mipmap.image_16, 1))
                mAdapter2.addData(SourceBean((mAdapter.getItem(mAdapter.itemCount - 1) as SourceBean).id + 1, "", R.mipmap.image_16, 1))
                mAdapter3.addData(SourceBean((mAdapter.getItem(mAdapter.itemCount - 1) as SourceBean).id + 1, "", R.mipmap.image_16, 1))
                mAdapter4.addData(SourceBean((mAdapter.getItem(mAdapter.itemCount - 1) as SourceBean).id + 1, "", R.mipmap.image_16, 1))
                ToastUtils.showShort("添加成功")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}