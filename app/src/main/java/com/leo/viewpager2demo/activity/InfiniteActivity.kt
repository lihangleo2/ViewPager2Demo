package com.leo.viewpager2demo.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.leo.viewpager2demo.R
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.databinding.ActivityInfiniteBinding
import com.leo.viewpager2demo.fragment.ImageFragment
import com.leo.viewpager2demo.fragment.TextFragment
import com.leo.viewpager2demo.util.DataUtil
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.transformer.SmartTransformer

/**
 * @Author leo
 * @Date 2023/10/9
 */
class InfiniteActivity : AppCompatActivity() {
    private val mAdapter by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager2)
            .cancleOverScrollMode()
            /**
             * 实现无线循环模式
             * */
            .setInfinite(true)
            .setOffscreenPageLimit(5)
            .asGallery(ConvertUtils.dp2px(50f), ConvertUtils.dp2px(50f))
            .setPagerTransformer(SmartTransformer.TRANSFORMER_ALPHA_SCALE)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            .addData(DataUtil.productDatas(0, isLoadMore = true, isGallery = true, 1))
    }

    private lateinit var mBinding: ActivityInfiniteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityInfiniteBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initActionBar()
        //
        mBinding.viewPager2.adapter = mAdapter
    }


    private fun initActionBar() {
        supportActionBar?.title = "无线循环的使用"
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
                mAdapter.addFrontData(SourceBean(( mAdapter.getItem(0) as SourceBean).id-1, "", R.mipmap.image_15, 1))
                ToastUtils.showShort("添加成功")
            }

            R.id.add_data -> {
                mAdapter.addData(SourceBean((mAdapter.getItem(mAdapter.itemCount-1) as SourceBean).id+1, "", R.mipmap.image_16, 1))
                ToastUtils.showShort("添加成功")
            }
        }
        return super.onOptionsItemSelected(item)
    }

}