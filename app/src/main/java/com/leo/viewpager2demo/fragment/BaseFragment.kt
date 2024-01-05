package com.leo.viewpager2demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @Author leo
 * @Address https://github.com/lihangleo2
 * @Date 2024/1/5
 * 因为很多人并不知道放置视频时如何使用，故放上我在项目里的Base封装的几个重要的类
 */
abstract class BaseFragment : Fragment() {
    private var isLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }

        if (!isHidden) {
            onVisible()
        }
    }

    override fun onPause() {
        super.onPause()
        onInVisible()
    }

    /**
     * 初始化
     */
    protected open fun initView() {}

    /**
     * 懒加载
     */
    protected open fun lazyInit() {}

    /**
     * 显示
     */
    protected open fun onVisible() {}

    /**
     * 隐藏
     */
    protected open fun onInVisible() {}
}