package com.leo.viewpager2demo.activity_genericity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.leo.viewpager2demo.R
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.databinding.FragmentImageBinding
import com.smart.adapter.interf.SmartFragmentImpl
import com.smart.adapter.interf.SmartFragmentImpl2
import com.smart.adapter.interf.SmartFragmentTypeExEntity

/**
 * @Author leo
 * @Date 2023/9/1
 */
class ImageGenFragment : Fragment(), SmartFragmentImpl2<SourceBean> {
    private lateinit var mBinding: FragmentImageBinding
    private lateinit var mSourceBean: SourceBean

    override fun initSmartFragmentData(bean: SourceBean) {
        this.mSourceBean = bean
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentImageBinding.inflate(inflater, container, false);

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        Glide.with(this).load(mSourceBean.image).into(mBinding.image)
        mBinding.txtIndex.text = "${mSourceBean.id}"

    }
}