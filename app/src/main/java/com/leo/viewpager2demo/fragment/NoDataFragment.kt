package com.leo.viewpager2demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leo.viewpager2demo.databinding.FragmentNoDataBinding

/**
 * @Author leo
 * @Address https://github.com/lihangleo2
 * @Date 2024/3/15
 */
class NoDataFragment : BaseFragment() {
    private lateinit var mBinding: FragmentNoDataBinding
    private lateinit var mTxtContent: String
    private var groundColor: Int = -1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentNoDataBinding.inflate(inflater, container, false);
        return mBinding.root
    }

    companion object Instance {
        fun newInstance(txtContent: String, groundColor: Int): NoDataFragment {
            val fragment = NoDataFragment()
            fragment.mTxtContent = txtContent
            fragment.groundColor = groundColor
            return fragment
        }
    }

    override fun initView() {
        super.initView()
        mBinding.txt.text = mTxtContent
        mBinding.back.setBackgroundColor(requireActivity().getColor(groundColor))
    }

    override fun lazyInit() {
        super.lazyInit()
    }
}