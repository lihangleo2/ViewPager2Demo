package com.leo.viewpager2demo

import java.io.Serializable

/**
 * @Author leo
 * @Date 2023/9/1
 */
data class SourceBean(
    var id:Int,
    var text:String,
    var image:Int,
    var type:Int
):Serializable
