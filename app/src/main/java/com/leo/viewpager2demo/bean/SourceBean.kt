package com.leo.viewpager2demo.bean

import com.smart.adapter.interf.SmartFragmentTypeExEntity

/**
 * @Author leo
 * @Date 2023/9/1
 */
data class SourceBean(
    var id:Int,
    var text:String,
    var image:Int,
    var type:Int,
    var createTime:Long = System.currentTimeMillis()
): SmartFragmentTypeExEntity {
    override fun getFragmentType(): Int {
        return type
    }

}
