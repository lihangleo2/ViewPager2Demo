package com.leo.viewpager2demo.util

import com.blankj.utilcode.util.LogUtils
import com.leo.viewpager2demo.R
import com.leo.viewpager2demo.bean.SourceBean
import com.smart.adapter.SmartViewPager2Adapter
import com.smart.adapter.interf.SmartFragmentTypeExEntity
import kotlin.math.abs
import kotlin.random.Random

/**
 * @类名
 * @描述
 * @作者 RinKamiuezono
 * @日期 2022/6/12
 * @版本 1.0
 */
object DataUtil {

    private val ImageRes by lazy {
        mutableListOf(R.mipmap.image_17, R.mipmap.image_18, R.mipmap.image_19, R.mipmap.image_20,
            R.mipmap.image_21, R.mipmap.image_22, R.mipmap.image_23, R.mipmap.image_1, R.mipmap.gif_1, R.mipmap.image_2, R.mipmap.gif_2, R.mipmap.image_3, R.mipmap.image_4, R.mipmap.image_5, R.mipmap.gif_3, R.mipmap.image_6, R.mipmap.image_7, R.mipmap.image_8, R.mipmap.image_9, R.mipmap.image_10,
            R.mipmap.gif_4, R.mipmap.gif_5, R.mipmap.image_11, R.mipmap.gif_6, R.mipmap.gif_7, R.mipmap.image_12, R.mipmap.image_13, R.mipmap.image_14, R.mipmap.image_15, R.mipmap.image_16)
    }

    private val TextRes by lazy {
        mutableListOf("微凉徒眸意", "浅挚绊离兮", "万能ViewPager2Adapter", "好用的话给个star", "leo我又回来了", "滕王高阁临江渚", "佩玉鸣鸾罢歌舞", "画栋朝飞南浦云", "珠帘暮卷西山雨", "闲云潭影日悠悠",
            "物换星移几度秋", "阁中帝子今何在？", "槛外长江空自流")
    }

    private var random = Random(10)


    //模拟生成加载数据
    //isGallery 只生成图片数据
    @JvmStatic
    fun productDatas(index: Int, isLoadMore: Boolean, isGallery: Boolean = false,produceSize:Int=10): MutableList<SmartFragmentTypeExEntity> {
        var newDats = mutableListOf<SmartFragmentTypeExEntity>()

        if (isLoadMore) {
            for (i in index..index +(produceSize-1)) {
                var randomValue = random.nextInt()

                var type = if (randomValue % 2 == 0) {
                    1
                } else {
                    2
                }
                if (isGallery) {
                    //画廊数据返回图片
                    type = 1
                }
                if (type == 1) {
                    //图片
                    var realPos = i % ImageRes.size
                    newDats.add(SourceBean(i, "", ImageRes[realPos], type))
                } else {
                    //文字
                    var realPos = i % TextRes.size
                    newDats.add(SourceBean(i, TextRes[realPos], -1, type))
                }

            }
        } else {
            for (i in index - (produceSize-1)..index) {
                var randomValue = random.nextInt()
                var type = if (randomValue % 2 == 0) {
                    1
                } else {
                    2
                }

                if (isGallery) {
                    //画廊数据返回图片
                    type = 1
                }

                if (type == 1) {
                    //图片
                    var realPos = abs(i) % ImageRes.size
                    newDats.add(SourceBean(i, "", ImageRes[realPos], type))
                } else {
                    //文字
                    var realPos = abs(i) % TextRes.size
                    newDats.add(SourceBean(i, TextRes[realPos], -1, type))
                }
                LogUtils.dTag("为什么会这样的东西呢", i, abs(i))
            }
        }

        return newDats
    }


    @JvmStatic
    fun productFrontDatas(mAdapter: SmartViewPager2Adapter): MutableList<SmartFragmentTypeExEntity> {
        return productDatas((mAdapter.getItem(0) as SourceBean).id - 1, false)
    }

    @JvmStatic
    fun productBackDatas(mAdapter: SmartViewPager2Adapter): MutableList<SmartFragmentTypeExEntity> {
        return productDatas((mAdapter.getItem(mAdapter.itemCount - 1) as SourceBean).id + 1, true)
    }

    @JvmStatic
    fun productImageFrontDatas(mAdapter: SmartViewPager2Adapter,produceSize:Int=10): MutableList<SmartFragmentTypeExEntity> {
        return productDatas((mAdapter.getItem(0) as SourceBean).id - 1, false,true,produceSize)
    }

    @JvmStatic
    fun productImageBackDatas(mAdapter: SmartViewPager2Adapter,produceSize:Int=10): MutableList<SmartFragmentTypeExEntity> {
        return productDatas((mAdapter.getItem(mAdapter.itemCount - 1) as SourceBean).id + 1, true,true,produceSize)
    }
}