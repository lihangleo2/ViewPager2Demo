package com.leo.viewpager2demo.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.leo.viewpager2demo.R
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
class GalleryUseActivity : AppCompatActivity() {


    private val mAdapter by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager2)
            .cancleOverScrollMode()
            /**
             * 实现画廊功能
             * */
            .asGallery(ConvertUtils.dp2px(50f),ConvertUtils.dp2px(50f))
            .setPagerTransformer(SmartTransformer.TRANSFORMER_ALPHA_SCALE)
            .setOffscreenPageLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            .addData(DataUtil.productDatas(0, isLoadMore = true, isGallery = true))
    }


    private lateinit var mBinding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initActionBar()
        //
        mBinding.viewPager2.adapter=mAdapter
    }


    private fun initActionBar(){
        supportActionBar?.title="画廊的使用"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_gallery,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
            R.id.add_front_data->{
                mAdapter.addFrontData(DataUtil.productImageFrontDatas(mAdapter,1))
                ToastUtils.showShort("添加成功")
            }
            R.id.add_data->{
                mAdapter.addData(DataUtil.productImageBackDatas(mAdapter,1))
                ToastUtils.showShort("添加成功")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}