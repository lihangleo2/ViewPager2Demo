package com.leo.viewpager2demo

import androidx.fragment.app.Fragment

/**
 * @Author leo
 * @Date 2023/9/7
 */
interface SmartFragmentImpl {
    fun newInstance(sourceBean: SmartFragmentTypeExEntity): Fragment
}