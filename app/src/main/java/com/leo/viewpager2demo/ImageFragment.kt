package com.leo.viewpager2demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.leo.viewpager2demo.databinding.FragmentImageBinding

/**
 * @Author leo
 * @Date 2023/9/1
 */
class ImageFragment : Fragment() {
    private lateinit var mBinding: FragmentImageBinding
    private lateinit var mSourceBean: SourceBean

    companion object Instance {
        fun newInstance(sourceBean: SourceBean): ImageFragment {
            val fragment = ImageFragment()
            fragment.mSourceBean = sourceBean
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentImageBinding.inflate(inflater, container, false);
        initView()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initView(){
//        mBinding.image.setImageResource(mSourceBean.image)
//        Glide.with(this).load("https://bcloud.aitici.com/ticidashi/33e97cd94159bad9e97fd180e2ba61aa571011693305952360.jpg").into(mBinding.image)
        Glide.with(this).load(mSourceBean.image).into(mBinding.image)
        LogUtils.dTag("我看看这个东西就应该知道了","onHiddenChanged----",mSourceBean.id)
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        LogUtils.dTag("我看看这个东西就应该知道了","onHiddenChanged",mSourceBean.id)
    }


}