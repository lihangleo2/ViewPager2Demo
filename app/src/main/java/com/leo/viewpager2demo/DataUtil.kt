package com.leo.viewpager2demo

import com.blankj.utilcode.util.LogUtils

/**
 * @类名
 * @描述
 * @作者 RinKamiuezono
 * @日期 2022/6/12
 * @版本 1.0
 */
object DataUtil {

    private val sourceImagRes by lazy {
        mutableListOf(R.mipmap.image_1, R.mipmap.image_2, R.mipmap.image_3, R.mipmap.image_4, R.mipmap.image_5, R.mipmap.image_6, R.mipmap.image_7, R.mipmap.image_8, R.mipmap.image_9, R.mipmap.image_10,
            R.mipmap.image_11, R.mipmap.image_12, R.mipmap.image_13, R.mipmap.image_14, R.mipmap.image_15, R.mipmap.image_16, R.mipmap.image_17, R.mipmap.image_18, R.mipmap.image_19, R.mipmap.image_20,
            R.mipmap.image_21, R.mipmap.image_22, R.mipmap.image_23)
    }

    //模拟生成加载数据
    @JvmStatic
    fun productDatas(index:Int): MutableList<SmartFragmentTypeExEntity> {
        var newDats = mutableListOf<SmartFragmentTypeExEntity>()
        LogUtils.dTag("我看看到底是多少",index,index+9)
        for (i in index..index + 9) {
            var realPos = i % sourceImagRes.size
            LogUtils.dTag("我看看到底是多少","------------- $realPos")
            newDats.add(SourceBean(i, "", sourceImagRes[realPos], 1))
        }
        return newDats
    }
}