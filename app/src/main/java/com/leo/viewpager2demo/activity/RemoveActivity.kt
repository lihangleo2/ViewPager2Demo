package com.leo.viewpager2demo.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.leo.viewpager2demo.R
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.databinding.ActivityRemoveBinding
import com.leo.viewpager2demo.fragment.ImageFragment
import com.leo.viewpager2demo.fragment.TextFragment
import com.leo.viewpager2demo.util.DataUtil
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.interf.OnRefreshLoadMoreListener
import com.smart.adapter.transformer.SmartTransformer

/**
 * @Author leo
 * @Address https://github.com/lihangleo2
 * @Date 2023/12/29
 */
class RemoveActivity : AppCompatActivity() {
    private val mAdapter by lazy {
        SmartViewPager2Adapter<SourceBean>(this, mBinding.viewPager2)
            .setOffscreenPageLimit(5)
            .setPreLoadLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            //可以在这里初始化数据
            .addData(DataUtil.productDatas(0, true))
    }


    private lateinit var mBinding: ActivityRemoveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRemoveBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initActionBar()
        initLoadMoreListener()
        mBinding.viewPager2.adapter = mAdapter
    }

    private fun initActionBar() {
        supportActionBar?.title = "remove的使用"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    private fun initLoadMoreListener(){
        //加载监听
        mAdapter.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(smartAdapter: SmartViewPager2Adapter<*>) {
                mBinding.viewPager2.postDelayed({
                    mAdapter.addFrontData(DataUtil.productFrontDatas(mAdapter))
                }, 2000)
            }

            override fun onLoadMore(smartAdapter: SmartViewPager2Adapter<*>) {
                mBinding.viewPager2.postDelayed({
                    mAdapter.addData(DataUtil.productBackDatas(mAdapter))
                }, 2000)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_remove,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.remove_data->{
                mAdapter.removeData(mBinding.viewPager2.currentItem)
                ToastUtils.showShort("移除成功")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}