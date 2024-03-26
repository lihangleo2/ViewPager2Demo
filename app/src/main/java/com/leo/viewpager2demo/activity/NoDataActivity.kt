package com.leo.viewpager2demo.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.leo.viewpager2demo.R
import com.leo.viewpager2demo.databinding.ActivityNoDataBinding
import com.leo.viewpager2demo.fragment.NoDataFragment
import com.leo.viewpager2demo.fragment.TestApiFragment
import com.smart.adapter.SmartNoDataAdapter
import com.smart.adapter.SmartViewPager2Adapter

/**
 * @Author leo
 * @Address https://github.com/lihangleo2
 * @Date 2024/3/15
 */
class NoDataActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityNoDataBinding

    private val mAdapter by lazy {
        SmartViewPager2Adapter.NoDataBuilder(this)
            .overScrollNever()
            .canScroll(false)
            .smoothScroll(false)
            .bindViews(mBinding.tabHome, mBinding.tabFile, mBinding.tabManager, mBinding.tabMine)
            .build(mBinding.viewPager2)
            .setFragmentList(NoDataFragment.newInstance("首页", R.color.background_home),
                NoDataFragment.newInstance("文档", R.color.background_file),
                NoDataFragment.newInstance("管理", R.color.background_manager),
                TestApiFragment.newInstance("我的", R.color.background_mine))
                //如果切换tab需要额外逻辑，才能选中的话，可以实现以下方法，通过返回值true or false来确定是否切换tab
//            .setOnClickListener(object : SmartNoDataAdapter.OnClickListener {
//                override fun onClick(v: View): Boolean {
//                    when (v.id) {
//                        R.id.tab_mine -> {
//                            return if (isMemberFalg == 0) {
//                                ToastUtils.showLong("模拟需要登录: 请前往登录。再次点击既登录状态")
//                                isMemberFalg++
//                                false
//                            } else {
//                                ToastUtils.showLong("选中我的，根据返回值是否选中页面")
//                                true
//                            }
//                        }
//                    }
//                    return true
//                }
//            })
    }

    //模拟会员Flag
    private var isMemberFalg: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNoDataBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initActionBar()
        //
        mBinding.viewPager2.adapter = mAdapter
        testApi()
    }


    private fun testApi() {
        //以下代码会报错，因为setFragmens里NoDataFragment类型出现了多次
        //var mFragment = mAdapter.getFragment<NoDataFragment>()

        var mTestFragment = mAdapter.getFragment<TestApiFragment>()
        mTestFragment?.myLog()
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