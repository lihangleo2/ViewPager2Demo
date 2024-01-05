package com.leo.viewpager2demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.databinding.FragmentImageBinding
import com.smart.adapter.interf.SmartFragmentImpl

/**
 * @Author leo
 * @Date 2023/9/1
 */
class ImageFragment : BaseFragment(), SmartFragmentImpl<SourceBean> {
    private lateinit var mBinding: FragmentImageBinding
    private lateinit var mSourceBean: SourceBean

    override fun initSmartFragmentData(bean: SourceBean) {
        this.mSourceBean = bean
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentImageBinding.inflate(inflater, container, false);
        return mBinding.root
    }

    override fun initView() {
        super.initView()
        LogUtils.dTag("假设这是视频页","1111","视频初始化及预加载位置")
        Glide.with(this).load(mSourceBean.image).into(mBinding.image)
        mBinding.txtIndex.text = "${mSourceBean.id}"
    }

    override fun lazyInit() {
        super.lazyInit()
        //懒加载位置
    }


    override fun onVisible() {
        super.onVisible()
        LogUtils.dTag("假设这是视频页","2222","页面可见时播放视频")
    }

    override fun onInVisible() {
        super.onInVisible()
        LogUtils.dTag("假设这是视频页","3333","页面不可见时暂停视频")//如果想每次回到视频都从第一帧开始，也可以在这里处理
    }




}