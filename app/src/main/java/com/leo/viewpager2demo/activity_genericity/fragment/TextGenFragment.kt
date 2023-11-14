package com.leo.viewpager2demo.activity_genericity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.leo.viewpager2demo.bean.SourceBean
import com.leo.viewpager2demo.databinding.FragmentTextBinding
import com.smart.adapter.interf.SmartFragmentImpl
import com.smart.adapter.interf.SmartFragmentImpl2
import com.smart.adapter.interf.SmartFragmentTypeExEntity

/**
 * @Author leo
 * @Date 2023/9/8
 */
class TextGenFragment : Fragment(), SmartFragmentImpl2<SourceBean> {
    private lateinit var mBinding: FragmentTextBinding
    private lateinit var mSourceBean: SourceBean

    override fun initSmartFragmentData(bean: SourceBean) {
        this.mSourceBean = bean
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentTextBinding.inflate(inflater, container, false);
        initView()
        return mBinding.root
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        mBinding.txt.text = mSourceBean.text
        mBinding.txtIndex.text = "${mSourceBean.id}"
    }


}