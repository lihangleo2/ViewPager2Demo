package com.leo.viewpager2demo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leo.viewpager2demo.databinding.ActivityMainBinding
import com.leo.viewpager2demo.kotlinEx.jumpToActivityWithClick

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.title = "SmartVp2Adapter的使用"

        mBinding.run {
            btnTiktokUse.jumpToActivityWithClick(TiktokActivity::class.java)
            btnGalleryUse.jumpToActivityWithClick(GalleryUseActivity::class.java)
            btn3dUse.jumpToActivityWithClick(Transformer3DActivity::class.java)
            btnInfinite.jumpToActivityWithClick(InfiniteActivity::class.java)
            btnLoop.jumpToActivityWithClick(LoopActivity::class.java)
            btnSide.jumpToActivityWithClick(SideUseActivity::class.java)
            btnIndicatorUse.jumpToActivityWithClick(IndicatorActivity::class.java)
            btnRemoveUse.jumpToActivityWithClick(RemoveActivity::class.java)
            btnNodataUse.jumpToActivityWithClick(NoDataActivity::class.java)
        }
    }

}