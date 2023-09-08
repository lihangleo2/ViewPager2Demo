package com.leo.viewpager2demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.databinding.FragmentTextBinding
import com.smart.adapter.interf.SmartFragmentImpl
import com.smart.adapter.interf.SmartFragmentTypeExEntity

/**
 * @Author leo
 * @Date 2023/9/8
 */
class TextFragment: Fragment(), SmartFragmentImpl {
    private lateinit var mBinding: FragmentTextBinding
    private lateinit var mSourceBean: SourceBean

    override fun initSmartFragmentData(bean: SmartFragmentTypeExEntity) {
        this.mSourceBean = bean as SourceBean
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentTextBinding.inflate(inflater, container, false);
        initView()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initView() {
        mBinding.txt.text = mSourceBean.text
    }
}